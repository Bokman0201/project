package com.kh.corilla.vo;

import java.sql.Date;

import com.kh.corilla.dto.AttachDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @Builder @NoArgsConstructor @AllArgsConstructor
public class AttachVO {
	private int attachNo;
	private String attachName;
	private long  attachSize;
	private String attachType;
	private Date attachTime;
	
	private int boardNo;
}