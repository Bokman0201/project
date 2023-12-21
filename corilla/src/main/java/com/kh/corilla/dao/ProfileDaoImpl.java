package com.kh.corilla.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kh.corilla.dto.AttachDto;
import com.kh.corilla.dto.MemberProfileDto;

@Repository
public class ProfileDaoImpl implements ProfileDao{
	@Autowired
	private SqlSession sqlSession;

	@Override
	public int sequence() {
		return sqlSession.selectOne("profile.sequence");
	}

	@Override
	public void addProfile(MemberProfileDto memberProfileDto) {
		sqlSession.insert("profile.add",memberProfileDto);
		
	}

	@Override
	public void connectProfile(int profileNo, int attachNo) {
		Map<String, Object> params = Map.of("profileNo",profileNo,"attachNo",attachNo);
		sqlSession.insert("profile.connect",params);
	}

	@Override
	public void updateProfile(MemberProfileDto memberProfileDto, String memberId) {
		Map<String, Object> params = Map.of("memberProfileDto", memberProfileDto, "memberId", memberId);
		
		System.out.println(memberProfileDto);
		
		sqlSession.update("profile.update",params);
		
	}

	@Override
	public AttachDto find(String memberId) {
		
		System.out.println("memberId"+memberId);
		
		return sqlSession.selectOne("profile.find",memberId);
	}
	
	@Override
	public MemberProfileDto selectOne(String memberId) {
		return sqlSession.selectOne("profile.selectOne",memberId);
	}
	
	@Override
	public List<AttachDto> findMemberImage(String memberId) {
		return sqlSession.selectList("profile.findMemberImage",memberId);
	}
	

}
