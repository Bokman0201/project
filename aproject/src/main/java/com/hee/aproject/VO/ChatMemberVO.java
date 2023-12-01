package com.hee.aproject.VO;

import com.hee.aproject.dto.ChatGroupDto;
import com.hee.aproject.dto.ChatRoomDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class ChatMemberVO {
	private ChatGroupDto chatGourpDto;
	private ChatRoomDto chatRoomDto;

}
