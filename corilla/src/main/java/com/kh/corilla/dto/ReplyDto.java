package com.kh.corilla.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class ReplyDto {
	private int replyNo;
	private int boardNo;
	private int replyLikeCount;
	private String replyWriter;
	private String replyContent;
	private String replyTime;
	private String replyUpdateTime;

}
