package com.hee.aproject.websocket;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

import org.springframework.stereotype.Service;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.JsonProcessingException;

import com.hee.aproject.VO.ClientVO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class WebSocketServer extends TextWebSocketHandler {

	private Set<ClientVO> clients = new CopyOnWriteArraySet<>();
	private Set<ClientVO> members = new CopyOnWriteArraySet<>();
	private ObjectMapper mapper = new ObjectMapper(); // JSON 변환기

	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {

		// 접속자에게 메세지 내역 전송

	}

	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
		ClientVO client = new ClientVO(session);
		clients.remove(client);
		log.debug("사용자{}명", clients.size());
		

		if (client.isMember()) {
			members.remove(client);
		}
		// clients.remove(session);

	}

	@Override
	protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
		Map params = mapper.readValue(message.getPayload(), Map.class);
		String type = (String) params.get("type");
		String content = (String) params.get("content");
		String memberEmail = (String) params.get("memberEmail");
		String memberLevel = (String) params.get("memberLevel");

		ClientVO client = new ClientVO(session);
		client.setMemberEmail(memberEmail);
		client.setMemberLevel(memberLevel);
		clients.add(client);

		log.debug("email={}", client.getMemberEmail());
		log.debug("level={}", client.getMemberLevel());
		if (client.isMember()) {
			members.add(client);
		}
		log.debug("members={}", members);

		if ("enterRoom".equals(type)) {

		} else if ("message".equals(type)) {

			if (client.isMember() == false)
				return;
			
			Map<String, Object> map = new HashMap<>();
			map.put("memberEmail", client.getMemberEmail());
			map.put("memberLevel", client.getMemberLevel());
			map.put("content", content);
			
			String messageJson = mapper.writeValueAsString(map);
			
			TextMessage tm = new TextMessage(messageJson);
			
			for(ClientVO c : members) {
				c.send(tm);
			}
			

		}

	}

}
