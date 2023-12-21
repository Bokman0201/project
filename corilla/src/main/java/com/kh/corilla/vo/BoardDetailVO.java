package com.kh.corilla.vo;

import com.kh.corilla.dto.BoardDto;
import com.kh.corilla.dto.MemberDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor @AllArgsConstructor @Builder 
public class BoardDetailVO {
	private BoardDto boardDto;
	private MemberDto memberDto; 

}
