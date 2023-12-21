package com.kh.corilla.vo;

import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.kh.corilla.dto.MemberProfileDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class MemberImageVO {
	private MultipartFile attach;
	private String memberId;
	private String profileContent;
	private String memberGender;
	private String privateStatus;
	
	@JsonIgnore
	public MemberProfileDto getMemberProfileDto() {
		return MemberProfileDto.builder()
				.memberId(memberId)
				.profileContent(profileContent)
				.memberGender(memberGender)
				.privateStatus(privateStatus)
				.build();
	}

}
