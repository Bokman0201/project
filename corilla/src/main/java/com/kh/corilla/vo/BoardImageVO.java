package com.kh.corilla.vo;

import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.kh.corilla.dto.BoardDto;

import lombok.Data;

@Data
public class BoardImageVO {
	private MultipartFile[] attach;
	private int boardNo;
	private String boardContent;
	private String boardWriter;
	
	
	@JsonIgnore
	public BoardDto getBoardDto() {
		return BoardDto.builder()
				.boardNo(boardNo)
				.boardContent(boardContent)
				.boardWriter(boardWriter)
				.build();
	}

}
