package com.kh.corilla.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class MemberDto {
	
	private String memberId;
	private String memberPw;
	private String memberNick;
	private String memberEmail;
	private String memberName;
	private String memberJoin;

}
