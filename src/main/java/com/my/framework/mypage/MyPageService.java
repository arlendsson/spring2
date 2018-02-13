package com.my.framework.mypage;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MyPageService {

	@Autowired
	private MyPageDao dao;

	public String selectDual(String param) throws Exception {
		return dao.selectDual(param);
	}

	public MyPageVo selectUser(MyPageVo param) throws Exception {
		return dao.selectUser(param);
	}

	public List<BoardVo> selectBoardList(BoardVo param) throws Exception {
		return dao.selectBoardList(param);
	}

	public int insertBoard(BoardVo param) throws Exception {
		return dao.insertBoard(param);
	}

	public int updateBoard(BoardVo param) throws Exception {
		return dao.updateBoard(param);
	}

	public int selectBoardCount(BoardVo param) throws Exception {
		return dao.selectBoardCount(param);
	}

}
