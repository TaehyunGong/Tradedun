package com.thkong.tradedun.Board.service;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import com.thkong.tradedun.Auction.vo.Category;

public interface boardService {

	public Map<String, String> uploadFile(File file) throws IOException;

	public void insertBoard(String title, String contents, String categoryCode) throws Exception;

	public List<Category> selectBoardCategoryList();
}
