package com.kh.corilla.restcontroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kh.corilla.dao.ReplyDao;
import com.kh.corilla.dto.ReplyDto;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
@Tag(name = "댓글")
@Slf4j
@RestController
@CrossOrigin
@RequestMapping("reply")
public class ReplyRestController {
	@Autowired
	private ReplyDao replyDao;
	
	@GetMapping("/list/{boardNo}")
	public List<ReplyDto> replyList(@PathVariable int boardNo){
		return replyDao.replyList(boardNo);
	} 
	
	@PostMapping("/write")
	public ResponseEntity<?> writeReply( @RequestBody ReplyDto replyDto){
		
		if(replyDto.getBoardNo() == 0 || replyDto.getReplyContent()==null) {
			return ResponseEntity.notFound().build();
		}
		replyDao.writeReply(replyDto);
		return ResponseEntity.ok(200);
		
	}
}
