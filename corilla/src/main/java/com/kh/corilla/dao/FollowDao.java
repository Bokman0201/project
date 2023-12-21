package com.kh.corilla.dao;

import java.util.List;

import com.kh.corilla.dto.FollowDto;

public interface FollowDao {

	void addFollow(FollowDto followDto);

	List<FollowDto> find(String followId);

	List<FollowDto> followingList(String myId);

	List<FollowDto> followerList(String followId);

	void deleteFollow(FollowDto followDto);

}
