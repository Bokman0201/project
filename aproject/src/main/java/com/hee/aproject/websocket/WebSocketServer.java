package com.hee.aproject.websocket;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hee.aproject.VO.ClientVO;
import com.hee.aproject.dao.ChatDao;
import com.hee.aproject.dto.ChatMessageDto;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class WebSocketServer extends TextWebSocketHandler {

	private Set<ClientVO> clients = new CopyOnWriteArraySet<>();
	private Set<ClientVO> members = new CopyOnWriteArraySet<>();
	private ObjectMapper mapper = new ObjectMapper(); // JSON 변환기
	
	Map<String, List<ClientVO>> chatRooms =new HashMap<>();

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
	
	
	@Autowired
	private ChatDao chatDao;
	
	@Override
	protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
		Map params = mapper.readValue(message.getPayload(), Map.class);
		String type = (String) params.get("type");
		String content = (String) params.get("content");
		String memberEmail = (String) params.get("memberEmail");
		String memberLevel = (String) params.get("memberLevel");
		String chatRoomNo = (String) params.get("chatRoomNo");
		

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
			int roomNo = Integer.parseInt(chatRoomNo);
		    for (List<ClientVO> room : chatRooms.values()) {
		        Iterator<ClientVO> iterator = room.iterator();
		        while (iterator.hasNext()) {
		            ClientVO clientInRoom = iterator.next();
		            
		            if (clientInRoom.getMemberEmail().equals(memberEmail)) {
		                iterator.remove();
		                log.debug("Client {} removed from room", memberEmail);
		            }
		        }
		    }
		    
			
			if(chatRooms.containsKey(chatRoomNo)) {
				List<ClientVO> room = chatRooms.get(chatRoomNo);
				room.add(client);
				log.debug("chatRoomNo={}{}",chatRoomNo,room);
			}
			else {
				List<ClientVO> newRoom = new ArrayList<>();
				newRoom.add(client);
				
				chatRooms.put(chatRoomNo, newRoom);
				log.debug("rooms={}",chatRooms);
			}
		}
		else if ("message".equals(type)) {
			log.debug("메세지입니다");
			log.debug("boolean={}",client.isMember());
			
			
			if (client.isMember() == false)
				return;
			
			
			
		
			Map<String, Object> map = new HashMap<>();
			map.put("chatRoomNo", chatRoomNo);
			map.put("memberEmail", client.getMemberEmail());
			map.put("memberLevel", client.getMemberLevel());
			map.put("content", content);
			
			
			String messageJson = mapper.writeValueAsString(map);
			
			TextMessage tm = new TextMessage(messageJson);
			
			log.debug("message={}",tm.getPayload());
			String roomNoStr = (String) map.get("chatRoomNo");
			
			chatDao.sendMessage(ChatMessageDto.builder()
					.chatMessageSender(client.getMemberEmail())
					.chatMessageContent(content)
					.chatRoomNo(Integer.parseInt(roomNoStr))
					.build());
			
			List<ClientVO> clientsInRoom = chatRooms.get(chatRoomNo);

			if (clientsInRoom != null) {
			    for (ClientVO c : clientsInRoom) {
			        c.send(tm);
			    }
			}
			

		}

	}

}
