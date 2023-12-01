package com.hee.aproject.dao;

import java.util.List;

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
		System.out.println(memberDto.getMemberName());
		sqlSession.insert("member.memberJoin",memberDto);
	}
	@Override
	public List<MemberDto> overlapEmail() {
		// TODO Auto-generated method stub
		return sqlSession.selectList("member.overlapEmail");
	}
	
	@Override
	public List<MemberDto> memberList() {
		// TODO Auto-generated method stub
		return sqlSession.selectList("member.memberList");
	}
}
