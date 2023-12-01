package com.hee.aproject.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class ChatMessageDto {
	private int chatRoomNo;
	private String chatMessageSender;
	private int chatMessageNo;
	private String chatMessageContent;
	private String chatMessageTime;
}
