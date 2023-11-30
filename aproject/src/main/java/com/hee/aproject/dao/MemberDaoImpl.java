package com.hee.aproject.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.hee.aproject.dto.MemberDto;

@Repository
public class MemberDaoImpl implements MemberDao {
	@Autowired 
	private SqlSession sqlSession;
	
	
	@Override
	public void memberJoin(MemberDto memberDto) {
		sqlSession.insert("member.memberJoin",memberDto);
	}
}
