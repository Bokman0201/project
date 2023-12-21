package com.kh.corilla.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kh.corilla.dto.AttachDto;
import com.kh.corilla.vo.AttachVO;

@Repository
public class AttachDaoImpl implements AttachDao{
	@Autowired
	private SqlSession sqlSession;
	
	@Override
	public int sequence() {
		return sqlSession.selectOne("attach.sequence");
	}

	//이미지 업로드
	@Override
	public void insert(AttachDto attachDto) {
		sqlSession.insert("attach.save", attachDto);
	}

	@Override
	public List<AttachVO> imageList() {
		
		return sqlSession.selectList("image.imageList");
	}

	@Override
	public void delete(int attachNo) {
		
		sqlSession.delete("attach.delete",attachNo);
		
	}
	@Override
	public List<AttachDto> listByMember(String memberId) {
		return sqlSession.selectList("attach.listByMember",memberId);
	}

	@Override
	public void deleteWithBoard(int boardNo) {
		sqlSession.delete("attach.deleteWithBoard",boardNo);
	}



}
