package com.hee.aproject.restcontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hee.aproject.dao.MemberDao;
import com.hee.aproject.dto.MemberDto;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;


@Tag(name = "회원")
@RestController
@CrossOrigin
@Slf4j
@RequestMapping("/member")
public class MemberRestController {
	@Autowired
	private MemberDao memberDao;
	
	@PostMapping("/add/")
	public void memberJoin(@RequestBody MemberDto memberDto) {
		
		memberDao.memberJoin(memberDto);
		log.debug("dto={}",memberDto);
		
		
	}

}
