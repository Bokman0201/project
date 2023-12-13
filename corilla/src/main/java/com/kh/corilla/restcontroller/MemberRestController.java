package com.kh.corilla.restcontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kh.corilla.dao.MemberDao;
import com.kh.corilla.dto.MemberDto;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
@Tag(name = "member")
@Slf4j
@RestController
@CrossOrigin
@RequestMapping("/member")
public class MemberRestController {
	@Autowired
	private BCryptPasswordEncoder encoder;
	@Autowired MemberDao memberDao;
	
	@PostMapping("/join")
	public void memberJoin(@RequestBody MemberDto memberDto) {
		
		String originPw = memberDto.getMemberPw();
		
		String encodePw = encoder.encode(originPw);
		
		log.debug("암호화={}",encodePw);
		
		memberDto.setMemberPw(encodePw);
		
		memberDao.memberJoin(memberDto);
		
	}
	
	@PostMapping("/login")
	public boolean memberLogin(@RequestBody MemberDto memberDto) {
		String inputPw = memberDto.getMemberPw();
		MemberDto findDto = memberDao.find(memberDto.getMemberId());
		String memberPw = findDto.getMemberPw();
		
		boolean match = encoder.matches(inputPw, memberPw);
		return match;
		//이후 토큰발행
		
	}
	@GetMapping("/detail/{memberId}")
	public MemberDto mypage(@PathVariable String memberId) {
		
		return memberDao.find(memberId);
		
	}

}
