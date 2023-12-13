package com.kh.corilla.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor @AllArgsConstructor @Builder 
public class BoardDto {
	private int boardNo;
	private String boardContent;
	private int boardLike;
	private int boardRead;
	private String boardWriter;
	private String boardWriteTime;
	private String boardUpdateTime;
}
