package com.kh.corilla.dao;

import java.util.List;

import com.kh.corilla.dto.ReplyDto;

public interface ReplyDao {

	void writeReply(ReplyDto replyDto);

	List<ReplyDto> replyList(int boardNo);

}
