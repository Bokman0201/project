package com.hee.aproject.VO;

import java.io.IOException;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @Builder @NoArgsConstructor @AllArgsConstructor
public class RoomVO {
	private int roomNo;
	private String roomName;
	@Builder.Default
	private Set<WebSocketSession> users = new CopyOnWriteArraySet<>();
	
	public void enter(WebSocketSession session) {
		users.add(session);
	}
	public void exit(WebSocketSession session) {
		users.remove(session);
	}
	public void send(WebSocketSession session, TextMessage message) throws IOException {
		for(WebSocketSession user : users) {
			user.sendMessage(message);
		}
	}
}