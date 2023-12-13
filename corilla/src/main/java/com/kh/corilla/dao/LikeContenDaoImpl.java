package com.kh.corilla.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kh.corilla.dto.LikeContentDto;

@Repository
public class LikeContenDaoImpl implements LikeContenDao {
	@Autowired
	private SqlSession sqlSession;
	@Override
	public List<LikeContentDto> find(String memberId) {
		return sqlSession.selectList("like.find",memberId);
	}
	@Override
	public void addContent(LikeContentDto likeContentDto) {
		sqlSession.insert("like.addContent",likeContentDto);
		
	}
	@Override
	public void removeContent(int boardNo) {
		sqlSession.delete("like.removeContent", boardNo);
		
	}
}
