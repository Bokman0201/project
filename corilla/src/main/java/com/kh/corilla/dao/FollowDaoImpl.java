package com.kh.corilla.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kh.corilla.dto.FollowDto;

@Repository
public class FollowDaoImpl implements FollowDao{
	@Autowired private SqlSession sqlSession ;
	@Override
	public void addFollow(FollowDto followDto) {
		sqlSession.insert("follow.addFollow",followDto);
	}
	@Override
	public List<FollowDto> find(String followId) {
		return sqlSession.selectList("follow.find",followId);
	}
	@Override
	public List<FollowDto> followingList(String myId) {
		return sqlSession.selectList("follow.followingList",myId);
	}
	@Override
	public List<FollowDto> followerList(String followId) {
		return sqlSession.selectList("follow.followerList",followId);
	}
	@Override
	public void deleteFollow(FollowDto followDto) {
		System.out.println("실행");
		sqlSession.delete("follow.deleteFollow", followDto);
	}
}
