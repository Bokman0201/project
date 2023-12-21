package com.kh.corilla.dao;

import java.util.List;

import com.kh.corilla.dto.MemberDto;
import com.kh.corilla.vo.MemberMypageVO;

public interface MemberDao {

	void memberJoin(MemberDto memberDto);

	MemberDto find(String memberId);

	MemberMypageVO findInfo(String memberId);

	List<MemberDto> searchMember(MemberDto memberDto);


}
