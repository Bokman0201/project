package com.kh.corilla.dao;

import java.util.List;

import com.kh.corilla.dto.AttachDto;
import com.kh.corilla.vo.AttachVO;

public interface AttachDao {

	int sequence();

	void insert(AttachDto attachDto);

	List<AttachVO> imageList();


	void delete(int attachNo);

	List<AttachDto> listByMember(String memberId);

	void deleteWithBoard(int boardNo);

}
