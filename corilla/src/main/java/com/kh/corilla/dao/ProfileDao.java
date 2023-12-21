package com.kh.corilla.dao;

import java.util.List;

import com.kh.corilla.dto.AttachDto;
import com.kh.corilla.dto.MemberProfileDto;

public interface ProfileDao {

	int sequence();

	void addProfile(MemberProfileDto memberProfileDto);

	void connectProfile(int profileNo, int attachNo);

	void updateProfile(MemberProfileDto memberProfileDto, String memberId);

	AttachDto find(String memberId);

	MemberProfileDto selectOne(String memberId);

	List<AttachDto> findMemberImage(String memberId);

}
