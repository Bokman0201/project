package com.hee.aproject.dao;

import java.util.List;

import com.hee.aproject.dto.ChatGroupDto;
import com.hee.aproject.dto.ChatMessageDto;

public interface ChatDao {

	void sendMessage(ChatMessageDto chatMessageDto);

	int sequence();

	void addmember(int chatRoomNo, String chatMember);

	void createRoom(int chatRoomNo);

	List<ChatGroupDto> roomList(String chatGroupMember);
	
}
