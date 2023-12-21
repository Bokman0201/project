package com.kh.corilla.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kh.corilla.dto.ReplyDto;

@Repository
public class ReplyDaoImpl implements ReplyDao{

	@Autowired
	private SqlSession sqlSession;
	@Override
	public void writeReply(ReplyDto replyDto) {
		sqlSession.insert("reply.writeReply",replyDto);
	}

	@Override
	public List<ReplyDto> replyList(int boardNo) {
		return sqlSession.selectList("reply.replyList", boardNo);
		
	}
	

}
