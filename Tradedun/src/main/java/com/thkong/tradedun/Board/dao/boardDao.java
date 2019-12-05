package com.thkong.tradedun.Board.dao;

import java.util.List;

import com.thkong.tradedun.Auction.vo.Category;
import com.thkong.tradedun.Board.vo.Board;

public interface boardDao {

	int insertBoard(Board board);

	int selectBoardNo(String categoryCode);

	List<Category> selectBoardCategoryList();

	List<Board> selectBoardList();

	Board selectBoard(Board board);

	
}
