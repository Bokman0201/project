package com.kh.corilla.restcontroller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kh.corilla.dao.BoardImageDao;
import com.kh.corilla.dto.AttachDto;

import io.swagger.v3.oas.annotations.tags.Tag;

@CrossOrigin
@Tag(name = "이미지", description = "이미지 조회(download)")
@RestController
@RequestMapping("/image")
public class AttachRestController {
	@Autowired BoardImageDao boardImageDao; 
	
	@GetMapping("/board/{boardNo}")
	public ResponseEntity<Map<Integer, String>> downloadProfileImages(@PathVariable int boardNo) {
	    List<AttachDto> attachDtoList = boardImageDao.findImage(boardNo);

	    if (attachDtoList.isEmpty()) {
	        // 이미지가 없을 경우에 대한 처리
	        return ResponseEntity.notFound().build();
	    }

	    Map<Integer, String> imageContentsMap = new HashMap<>();

	    for (AttachDto attachDto : attachDtoList) {
	        File target = new File(System.getProperty("user.home"), "upload/" + attachDto.getAttachNo());

	        try {
	            byte[] data = Files.readAllBytes(target.toPath());
	            String base64Encoded = Base64.getEncoder().encodeToString(data);

	            // 이미지의 attachNo를 키로 사용하여 추가
	            imageContentsMap.put(attachDto.getAttachNo(), base64Encoded);
	        } catch (IOException e) {
	            e.printStackTrace();
	            // 이미지 읽기 실패 시에 대한 처리
	            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
	        }
	    }

	    HttpHeaders headers = new HttpHeaders();
	    headers.setContentType(MediaType.APPLICATION_JSON);

	    return new ResponseEntity<Map<Integer, String>>(imageContentsMap, headers, HttpStatus.OK);
	}
	
	
	
//	@GetMapping("/member/{memberId}")
//	public ResponseEntity<ByteArrayResource> downLoadFrofile(@PathVariable String memberId){
//		
//		return ResponseEntity.status(null).build();
//	}
}