package com.kh.corilla.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kh.corilla.dto.AttachDto;
import com.kh.corilla.dto.BoardImageDto;

@Repository
public class BoardImageDaoImpl implements BoardImageDao{
	@Autowired private SqlSession sqlSession;
	@Override
	public void addImage(BoardImageDto boardImageDto) {
		sqlSession.insert("image.add",boardImageDto);
		
	}
	@Override
	public List<AttachDto> findImage(int boardNo) {
		// TODO Auto-generated method stub
		return sqlSession.selectList("image.findImage", boardNo);
	}

}
