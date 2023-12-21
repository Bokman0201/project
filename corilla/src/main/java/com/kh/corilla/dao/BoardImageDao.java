package com.kh.corilla.dao;

import java.util.List;

import com.kh.corilla.dto.AttachDto;
import com.kh.corilla.dto.BoardImageDto;

public interface BoardImageDao {

	void addImage(BoardImageDto boardImageDto);

	List<AttachDto> findImage(int boardNo);

	List<BoardImageDto> listByMember(String memberId);

}
