package com.kh.corilla.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kh.corilla.dto.MemberDto;
import com.kh.corilla.vo.MemberMypageVO;

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
	
	@Override
	public MemberMypageVO findInfo(String memberId) {
		return sqlSession.selectOne(memberId);
	}

	@Override
	public List<MemberDto> searchMember(MemberDto memberDto) {
		
		System.out.println("memberDto = "+memberDto);
		
		return sqlSession.selectList("member.search", memberDto);
	}

}
