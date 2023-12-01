package com.hee.aproject.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor @AllArgsConstructor @Builder

public class ChatRoomDto {
	
	private int chatRoomNo;
	private String chatRoomName;

}
