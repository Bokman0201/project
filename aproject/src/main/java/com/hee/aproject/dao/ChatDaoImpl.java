package com.hee.aproject.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.hee.aproject.dto.ChatGroupDto;
import com.hee.aproject.dto.ChatMessageDto;

@Repository
public class ChatDaoImpl implements ChatDao {
	@Autowired 
	private SqlSession sqlSession;
	@Override
	public void sendMessage(ChatMessageDto chatMessageDto) {
		
		sqlSession.insert("chat.sendMessage",chatMessageDto);
	}
	
	@Override
	public int sequence() {
		return sqlSession.selectOne("chat.sequence");
		
		
		
	}

	@Override
	public void addmember(int chatRoomNo, String chatMember) {
		Map<String, Object> params = Map.of("chatRoomNo", chatRoomNo, "chatGroupMember", chatMember);

		System.out.println("params="+params);
		
		
		sqlSession.insert("chat.addMember",params);
	}
	
	@Override
	public void createRoom(int chatRoomNo) {
		sqlSession.insert("chat.createRoom",chatRoomNo);
	}
	@Override
	public List<ChatGroupDto> roomList(String chatGroupMember) {
		return sqlSession.selectList("chat.roomList",chatGroupMember);
	}
}
