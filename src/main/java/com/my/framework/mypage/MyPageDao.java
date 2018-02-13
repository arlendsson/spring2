package com.my.framework.mypage;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class MyPageDao {

	@Autowired
	private SqlSession sqlSession;

	public String selectDual(String param) throws Exception {
		return sqlSession.selectOne("MyPageMapper.selectDual", param);
	}

	public MyPageVo selectUser(MyPageVo param) throws Exception {
		return sqlSession.selectOne("MyPageMapper.selectUser", param);
	}

	public List<BoardVo> selectBoardList(BoardVo param) throws Exception {
		return sqlSession.selectList("MyPageMapper.selectBoardList", param);
	}

	public int insertBoard(BoardVo param) throws Exception {
		return sqlSession.insert("MyPageMapper.insertBoard", param);
	}

	public int updateBoard(BoardVo param) throws Exception {
		return sqlSession.update("MyPageMapper.updateBoard", param);
	}

	public int selectBoardCount(BoardVo param) throws Exception {
		return sqlSession.selectOne("MyPageMapper.selectBoardCount", param);
	}
}
