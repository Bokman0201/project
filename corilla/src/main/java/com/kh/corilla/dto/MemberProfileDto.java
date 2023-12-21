package com.kh.corilla.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class MemberProfileDto {
	private int profileNo;
	private String profileContent;
	private String memberGender;
	private String privateStatus;
	private String memberId;

}
