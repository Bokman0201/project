package com.kh.corilla.dao;

import java.util.List;

import com.kh.corilla.dto.BoardDto;

public interface BoardDao {

	void write(BoardDto boardDto);

	int sequence();

	List<BoardDto> boardList();

	void likeDown(int boardNo);

	void likeUp(int boardNo);

	List<BoardDto> listByMember(String memberId);

	BoardDto selectOne(int boardNo);

	void updateBoard(int boardNo, BoardDto boardDto);

	void delete(int boardNo);

}
