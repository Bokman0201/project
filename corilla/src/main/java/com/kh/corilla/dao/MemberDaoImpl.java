package com.kh.corilla.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kh.corilla.dto.MemberDto;

@Repository
public class MemberDaoImpl implements MemberDao{
	@Autowired private SqlSession sqlSession;
	
	@Override
	public void memberJoin(MemberDto memberDto) {
		sqlSession.insert("member.join",memberDto);
	}
	
	@Override
	public MemberDto find(String memberId) {
		return sqlSession.selectOne("member.find",memberId);
	}

}
