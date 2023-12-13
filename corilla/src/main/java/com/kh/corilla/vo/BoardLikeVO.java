package com.kh.corilla.vo;

import com.kh.corilla.dto.BoardDto;
import com.kh.corilla.dto.LikeContentDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class BoardLikeVO {
	private BoardDto boardDto;
	private LikeContentDto likeContentDto;

}
