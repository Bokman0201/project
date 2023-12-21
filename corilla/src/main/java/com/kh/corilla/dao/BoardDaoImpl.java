package com.kh.corilla.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kh.corilla.dto.BoardDto;

@Repository
public class BoardDaoImpl implements BoardDao {
	@Autowired
	SqlSession sqlSession;

	@Override
	public void write(BoardDto boardDto) {
		sqlSession.insert("board.write", boardDto);
	}

	@Override
	public int sequence() {
		return sqlSession.selectOne("board.sequence");
	}

	@Override
	public List<BoardDto> boardList() {
		return sqlSession.selectList("board.list");
	}

	// Map<String, Object> params = Map.of("empNo",empNo ,"empDto",empDto);

	@Override
	public void likeDown(int boardNo) {
		sqlSession.update("board.likeDown",boardNo);
		
	}

	@Override
	public void likeUp(int boardNo) {
		sqlSession.update("board.likeUp",boardNo);
		

	}
	@Override
	public List<BoardDto> listByMember(String memberId) {
		
		return sqlSession.selectList("board.listByMember",memberId);
	}
	
	@Override
	public BoardDto selectOne(int boardNo) {
		return sqlSession.selectOne("board.selectOne", boardNo);
	}
	
	@Override
	public void updateBoard(int boardNo, BoardDto boardDto) {
		Map<String, Object> params = Map.of("boardDto", boardDto, "boardNo", boardNo);
				
		sqlSession.update("board.update",params);
	}
	@Override
	public void delete(int boardNo) {
		sqlSession.delete("board.deleteBoard",boardNo);
	}
}
