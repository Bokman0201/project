package com.kh.corilla.dao;

import java.util.List;

import com.kh.corilla.dto.LikeContentDto;

public interface LikeContenDao {

	List<LikeContentDto> find(String memberId);

	void addContent(LikeContentDto likeContentDto);

	void removeContent(int boardNo);

}
