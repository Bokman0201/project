package com.kh.corilla.dao;

import com.kh.corilla.dto.MemberDto;

public interface MemberDao {

	void memberJoin(MemberDto memberDto);

	MemberDto find(String memberId);

}
