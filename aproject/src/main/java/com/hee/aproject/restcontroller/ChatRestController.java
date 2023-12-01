package com.hee.aproject.restcontroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hee.aproject.VO.ChatMemberVO;
import com.hee.aproject.dao.ChatDao;
import com.hee.aproject.dto.ChatGroupDto;
import com.hee.aproject.dto.ChatMessageDto;

import io.swagger.v3.oas.annotations.tags.Tag;
@Tag(name = "채팅")
@CrossOrigin
@RestController
@RequestMapping
public class ChatRestController {
	@Autowired
	private ChatDao chatDao;
	
	@PostMapping("/message")
	public void sendMessage(@RequestBody ChatMessageDto chatMessageDto) {
		
		chatDao.sendMessage(chatMessageDto);
	}
	
	@PostMapping("/createRoom")
	public void createRoom(@RequestBody ChatMemberVO chatMemberVO) {
		
		int chatRoomNo = chatDao.sequence();
		//방에 방번호
		chatMemberVO.getChatRoomDto().setChatRoomNo(chatRoomNo);
		
		chatDao.createRoom(chatRoomNo);
		
		
		
		//사람 배열로 받아서 넣기 
		
		String[] members = chatMemberVO.getChatGourpDto().getMemberEmail();
		for(int i=0 ; i<members.length; i++) {
			chatDao.addmember(chatRoomNo,members[i]);
		}
		
		
	}
	
	@GetMapping("/roomList/{chatGroupMember}")
	public List<ChatGroupDto> roomList(@PathVariable String chatGroupMember){
		return chatDao.roomList(chatGroupMember);
	}


}
