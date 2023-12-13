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
		
		log.debug("dto={}",memberDto.getMemberName());
//		String incodePw = encoder.encode(memberDto.getMemberPw());
		
		//memberDto.setMemberPw(incodePw);
		
		memberDao.memberJoin(memberDto);
		
		
	}
	@GetMapping()
	public List<MemberDto> overlapEmail(){
		return memberDao.overlapEmail();
		
	}
	
	@GetMapping("/list/")
	public List<MemberDto> memberList(){
		return memberDao.memberList();
	}
	
	@PostMapping("/login/")
	public void login(@PathVariable MemberDto memberDto){
		MemberDto findDto = memberDao.find(memberDto.getMemberEmail());
		
		
		
		
		
		
		
		

		
	}

}
