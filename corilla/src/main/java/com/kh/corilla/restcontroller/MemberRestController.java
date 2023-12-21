package com.kh.corilla.restcontroller;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.kh.corilla.dao.AttachDao;
import com.kh.corilla.dao.BoardDao;
import com.kh.corilla.dao.BoardImageDao;
import com.kh.corilla.dao.MemberDao;
import com.kh.corilla.dao.ProfileDao;
import com.kh.corilla.dto.AttachDto;
import com.kh.corilla.dto.BoardDto;
import com.kh.corilla.dto.BoardImageDto;
import com.kh.corilla.dto.MemberDto;
import com.kh.corilla.dto.MemberProfileDto;
import com.kh.corilla.vo.MemberImageVO;
import com.kh.corilla.vo.MemberMypageVO;

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
	@Autowired
	private MemberDao memberDao;
	@Autowired 
	private AttachDao attachDao;
	@Autowired
	private ProfileDao profileDao;
	
	

	@PostMapping("/join")
	public void memberJoin(@RequestBody MemberDto memberDto) {

		String originPw = memberDto.getMemberPw();

		String encodePw = encoder.encode(originPw);

		log.debug("암호화={}", encodePw);

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
		// 이후 토큰발행

	}

	@GetMapping("/detail/{memberId}")
	public MemberDto detail(@PathVariable String memberId) {

		return memberDao.find(memberId);

	}
//회원가입시에 추가 
	@PostMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public void addPofile(@ModelAttribute MemberImageVO memberImageVO) throws IllegalStateException, IOException {

		int profileNo = profileDao.sequence();

		MemberProfileDto memberProfileDto = memberImageVO.getMemberProfileDto();
		memberProfileDto.setProfileNo(profileNo);
		profileDao.addProfile(memberProfileDto);
		
		MultipartFile attach = memberImageVO.getAttach();
		
		
		
		int attachNo = attachDao.sequence();

		String home = System.getProperty("user.home");
		File dir = new File(home, "upload");
		dir.mkdirs();
		File target = new File(dir, String.valueOf(attachNo));
		attach.transferTo(target);
		
		AttachDto attachDto = new AttachDto();
		attachDto.setAttachNo(attachNo);
		attachDto.setAttachName(attach.getOriginalFilename());
		attachDto.setAttachSize(attach.getSize());
		attachDto.setAttachType(attach.getContentType());
		attachDao.insert(attachDto);
		log.debug("attach={}",attachDto);
		
		log.debug("memberDto={}",memberProfileDto);
		
		profileDao.connectProfile(memberProfileDto.getProfileNo(), attachNo);


	}
	
	@PutMapping(value = "/profileUpdate/{memberId}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public ResponseEntity<String> updateProfile(@ModelAttribute MemberImageVO memberImageVO, @PathVariable String memberId) throws IllegalStateException, IOException{
		int attachNo = attachDao.sequence();			

		MultipartFile attach = memberImageVO.getAttach();
		
		
		log.debug("{}",attach);

		
		MemberProfileDto memberProfileDto = memberImageVO.getMemberProfileDto();
		
		
		log.debug("memberProfileDto={}",memberProfileDto);
		profileDao.updateProfile(memberProfileDto,memberId);
		
		if(memberImageVO.getAttach() == null) {
			return ResponseEntity.ok().body("수정완료");
		};
		
		if(!attach.isEmpty()) {
			
			log.debug("Attach={}",attach.getContentType());
			
			AttachDto attachDto = profileDao.find(memberId);
			
			log.debug("attachDto={}",attachDto);
			
			
			
			String home = System.getProperty("user.home");
			File dir = new File(home,"upload");
			
			
			if(attachDto != null ) {
				attachDao.delete(attachDto.getAttachNo());
				File target = new File(dir, String.valueOf(attachDto.getAttachNo()));
				target.delete();
			}
			
			File insertTarget = new File(dir,String.valueOf(attachNo));
			
			attach.transferTo(insertTarget);
			
			AttachDto insertDto = AttachDto.builder()
					.attachNo(attachNo)
					.attachName(attach.getOriginalFilename())
					.attachSize(attach.getSize())
					.attachType(attach.getContentType())
					.build();
			
			
			attachDao.insert(insertDto);
			
			
			
			MemberProfileDto dto = profileDao.selectOne(memberId);
			log.debug("no={} attachNo={}",dto.getProfileNo(),attachNo);
			
			
			profileDao.connectProfile(dto.getProfileNo(), attachNo);

		}
		
		
		
		
		
		return ResponseEntity.ok().body("수정완료");
	}
	
	@Autowired
	private BoardDao boardDao;
	@Autowired 
	private BoardImageDao boardImageDao;
	
	@GetMapping("/mypage/{memberId}")
	public MemberMypageVO mypage(@PathVariable String memberId) {
		
		//멤버 정보
		MemberDto memberDto = memberDao.find(memberId);
		//멤버의 게시물 정보
		List<BoardDto> boardDto = boardDao.listByMember(memberId);
		//게시물의 이미지 정보
		List<AttachDto> attachDto = attachDao.listByMember(memberId);
		//이미지 정보
		List<BoardImageDto> boardImageDto = boardImageDao.listByMember(memberId);
		//멤버 이미지 
		AttachDto memberAttachDto = profileDao.find(memberId);
		//멤버 이미지 정보
		MemberProfileDto memberProfileDto = profileDao.selectOne(memberId);
		
		return MemberMypageVO.builder()
				.memberDto(memberDto)
				.boardDto(boardDto)
				.boardImageDto(boardImageDto)
				.attachDto(attachDto)
				.memberAttachDto(memberAttachDto)
				.memberProfileDto(memberProfileDto)
				.build();
	}
	
	@GetMapping("/findProfile/{memberId}")
	public MemberProfileDto findProfile(@PathVariable String memberId) {
		return profileDao.selectOne(memberId);
	}
	
	@PostMapping("/searchMember")
	public List<MemberDto> searchMember(@RequestBody MemberDto memberDto){
		
		return memberDao.searchMember(memberDto);
		
		
	}
	

}
