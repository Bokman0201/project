package com.kh.corilla.vo;

import com.kh.corilla.dto.AttachDto;
import com.kh.corilla.dto.BoardDto;
import com.kh.corilla.dto.BoardImageDto;
import com.kh.corilla.dto.MemberDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class MemberMypageVO {
	private MemberDto memberDto;
	private BoardDto boardDto;
	private BoardImageDto boardImageDto;
	private AttachDto attachDto;

}
