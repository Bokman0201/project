package com.kh.corilla.restcontroller;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
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
import com.kh.corilla.dao.LikeContenDao;
import com.kh.corilla.dto.AttachDto;
import com.kh.corilla.dto.BoardDto;
import com.kh.corilla.dto.BoardImageDto;
import com.kh.corilla.dto.LikeContentDto;
import com.kh.corilla.dto.MemberDto;
import com.kh.corilla.vo.AttachVO;
import com.kh.corilla.vo.BoarListWithImageVO;
import com.kh.corilla.vo.BoardImageVO;
import com.kh.corilla.vo.BoardLikeVO;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
@Tag(name = "borad")
@Slf4j
@CrossOrigin
@RestController
@RequestMapping("/board")
public class BoardRestController {
	
	@Autowired
	private BoardDao boardDao; 
	@Autowired
	private LikeContenDao likeContentDao;
	@Autowired
	private AttachDao attachDao;
	@Autowired
	private BoardImageDao boardImageDao;
	
//	@PostMapping(value = "/", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
//	public void boardWrite(@RequestBody BoardDto boardDto) {
//		
//		int boardNo = boardDao.sequence();
//		
//		boardDto.setBoardNo(boardNo);
//		
//		
//		boardDao.write(boardDto);
//		
//		
//	}
	@PostMapping(value = "/", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public void boardWrite(@ModelAttribute BoardImageVO boardImageVO) throws IllegalStateException, IOException {
		
		int boardNo = boardDao.sequence();
		
		//게시판 게시글 작성
		BoardDto boardDto = boardImageVO.getBoardDto();
		boardDto.setBoardNo(boardNo);
		boardDao.write(boardDto);
		
		
		//이미지 처리
		//log.debug("??={}",boardImageVO);
		MultipartFile[] attaches  = boardImageVO.getAttach();
		
		if(attaches==null) return;

		
		for (MultipartFile attach : attaches) {
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
		    BoardImageDto boardImageDto =BoardImageDto.builder().attachNo(attachNo).boardNo(boardNo).build();
		    boardImageDao.addImage(boardImageDto);
		}
	}
	
//	@PostMapping("/list")
//	public List<BoardDto> boardList(@RequestBody(required = false) MemberDto memberDto){
//		
//		List<BoardDto> list = boardDao.boardList(memberDto.getMemberId());
//		log.debug("list={}",list.get(0));
//		
//		return list;
//		
//	}
	@GetMapping("/list")
	public BoarListWithImageVO boardList(){
		List<BoardDto> boardDto = boardDao.boardList();
		List<AttachVO> attachVO = attachDao.imageList();
		
		BoarListWithImageVO boarListWithImageVO = BoarListWithImageVO.builder().boardDto(boardDto).attachVO(attachVO).build();
		
		//log.debug("VO={}",boarListWithImageVO);
		return boarListWithImageVO;
	}
	@PostMapping("/like")
	public void boardLike(@RequestBody BoardLikeVO boardLikeVO) {
		//likedto에 boardno가 없으면 insert랑 like+1 있으면 delete like-1 그럼 find만들고 찾아서 대조후에 if문 처리
		String memberId = boardLikeVO.getLikeContentDto().getMemberId();
		
		List<LikeContentDto> likeList = likeContentDao.find(memberId);//배열
		
		int target = boardLikeVO.getBoardDto().getBoardNo();
		log.debug("target={}",target);
		log.debug("likeList={}",likeList);

		boolean contains = false;

		for (LikeContentDto likeContentDto : likeList) {
		    if (likeContentDto.getBoardNo() == target) {
		        contains = true;
		        break;
		    }
		}
		if(!contains) {
			//없을때
			likeContentDao.addContent(boardLikeVO.getLikeContentDto());
			boardDao.likeUp(boardLikeVO.getBoardDto().getBoardNo());
		}
		else {
			//있을경우
			likeContentDao.removeContent(boardLikeVO.getLikeContentDto().getBoardNo());
			boardDao.likeDown(boardLikeVO.getBoardDto().getBoardNo());
		}
		
		
		
		
		
	}
	@GetMapping("/likeList/{memberId}")
	public List<LikeContentDto> likeList(@PathVariable String memberId){
		
		return likeContentDao.find(memberId);
		

	}
	
	@GetMapping("/image/{boardNo}")
	public List<AttachDto> findImage(@PathVariable int boardNo) {
		
		return boardImageDao.findImage(boardNo);
	}	
	
	
	@GetMapping("/boardList/{memberId}")
	public List<BoardDto> listByMember(@PathVariable String memberId){
		
		return boardDao.listByMember(memberId);
		
	}
	
	@GetMapping("/detail/{boardNo}")
	public BoardDto boardDetail(@PathVariable int boardNo) {
		log.debug("boardNO{}",boardNo);
		return boardDao.selectOne(boardNo);
	}

	@PutMapping("/update/{boardNo}")
	public void updateBoard(@PathVariable int boardNo , @RequestBody BoardDto boardDto) {
		boardDao.updateBoard(boardNo,boardDto);
	}
	
	@DeleteMapping("/delete/{boardNo}")
	public void deleteBoard(@PathVariable int boardNo) {
		attachDao.deleteWithBoard(boardNo);
		
		boardDao.delete(boardNo);
	}
}
