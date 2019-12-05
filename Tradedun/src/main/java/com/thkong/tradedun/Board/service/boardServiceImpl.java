package com.thkong.tradedun.Board.service;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thkong.tradedun.Board.dao.boardDao;
import com.thkong.tradedun.Board.vo.Board;
import com.thkong.tradedun.Common.FileLib;

@Transactional
@Service
public class boardServiceImpl implements boardService{
	
	@Autowired
	private FileLib fileLib;
	
	@Autowired
	private boardDao dao;
	
	/**
	 * @description 이미지 업로드 로직
	 * @param file
	 * @return
	 */
	@Override
	public Map<String, String> uploadFile(File file) throws IOException{
		String fileName= fileLib.uploadFile(file);
		
		// json 데이터로 등록
        // {"uploaded" : 1, "fileName" : "test.jpg", "url" : "/img/test.jpg"}
		Map<String, String> jsonMap = new HashMap<String, String>();
		jsonMap.put("uploaded", "1");
		jsonMap.put("fileName", fileName);
		jsonMap.put("url", "/upImage/board/"+fileName);
		
		return jsonMap;
	}

	/**
	 * @description 게시글 작성 insert
	 * @param title
	 * @param contents
	 * @param categoryCode
	 * @return
	 */
	@Override
	public void insertBoard(String title, String contents, String categoryCode) throws Exception {
		int boardNo = dao.selectBoardNo(categoryCode);
		
		Board board = new Board();
		board.setBoardNo(boardNo);
		board.setTitle(title);
		board.setContents(contents);
		board.setCategoryCode(categoryCode);
		
		int result = dao.insertBoard(board);
		System.out.println(result);
	}
	
}
