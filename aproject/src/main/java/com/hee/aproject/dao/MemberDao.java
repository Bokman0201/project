package com.hee.aproject.dao;

import java.util.List;

import com.hee.aproject.dto.MemberDto;

public interface MemberDao {

	void memberJoin(MemberDto memberDto);

	List<MemberDto> overlapEmail();

}
