package com.thkong.tradedun.Board.service;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

import com.thkong.tradedun.Auction.vo.Category;
import com.thkong.tradedun.Board.vo.Board;
import com.thkong.tradedun.User.vo.User;

public interface boardService {

	public Map<String, String> uploadFile(File file, InputStream inputStream) throws IOException;

	public void insertBoard(Board board) throws Exception;

	public List<Category> selectBoardCategoryList(User user);

	public List<Board> selectBoardList();

	public Board selectBoard(int boardNo, String categoryCode);

	public boolean updateBoard(Board board, User user);

	public String deleteBoard(int boardNo, String categoryCode, User user);

}
