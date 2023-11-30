package com.hee.aproject.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class MemberDto {
	
	private int memberNo;
	private String memberName;
	private String memberLevel;
	private String memberEmail;
	private String memberPw;
	private String memberConnect;
	private String memberJoin;
	private String memberBirth;
	

}
