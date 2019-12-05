package com.thkong.tradedun.Board.dao;

import com.thkong.tradedun.Board.vo.Board;

public interface boardDao {

	int insertBoard(Board board);

	int selectBoardNo(String categoryCode);

	
}
