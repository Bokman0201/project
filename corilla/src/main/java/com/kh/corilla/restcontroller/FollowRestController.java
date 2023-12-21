package com.kh.corilla.restcontroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kh.corilla.dao.FollowDao;
import com.kh.corilla.dto.FollowDto;

import io.micrometer.core.ipc.http.HttpSender.Response;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
@Tag(name = "follow")
@Slf4j
@RestController
@CrossOrigin
@RequestMapping("/follow")
public class FollowRestController {
	
	@Autowired 
	private FollowDao followDao;
	
	@PostMapping("/add")
	public ResponseEntity<?> addFollow(@RequestBody FollowDto followDto) {
		
		List<FollowDto> findDto = followDao.find(followDto.getMyId());
		log.debug("find={}",findDto);
		
		if(findDto.contains(followDto) || followDto.getMyId() == null || followDto.getFollowId()==null) {
			log.debug("ismatch?={}",findDto.contains(followDto));
			return ResponseEntity.status(400).build();
		}
		
		followDao.addFollow(followDto);
		return ResponseEntity.ok(200);
	}
	
	@GetMapping("/followingList/{myId}")
	public List<FollowDto> followingList(@PathVariable String myId){
		return followDao.followingList(myId);
	}
	
	@GetMapping("/followerList/{followId}")
	public List<FollowDto> followerList(@PathVariable String followId){
		return followDao.followerList(followId);
	}
	
	@PostMapping("/deleteFollow")
	public ResponseEntity<?> deleteFollow(@RequestBody FollowDto followDto){
		log.debug("go");
		log.debug("followDto1={}", followDto);

		if(followDto.getFollowId() == null || followDto.getMyId() ==null) {
			return ResponseEntity.notFound().build();
		}
		
		log.debug("followDto2={}", followDto);
		
		followDao.deleteFollow(followDto);
		return ResponseEntity.ok(200);
				
		
	}

}
