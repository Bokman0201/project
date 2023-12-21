package com.kh.corilla.vo;

import java.util.List;

import com.kh.corilla.dto.AttachDto;
import com.kh.corilla.dto.BoardDto;
import com.kh.corilla.dto.BoardImageDto;
import com.kh.corilla.dto.MemberDto;
import com.kh.corilla.dto.MemberProfileDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class MemberMypageVO {
	private MemberDto memberDto;
	private List<BoardDto> boardDto;
	private List<BoardImageDto> boardImageDto;
	private List<AttachDto> attachDto;
	private AttachDto memberAttachDto;
	private MemberProfileDto memberProfileDto;
}
