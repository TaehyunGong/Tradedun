package com.thkong.tradedun.Board.service;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thkong.tradedun.Auction.vo.Category;
import com.thkong.tradedun.Board.dao.boardDao;
import com.thkong.tradedun.Board.vo.Board;
import com.thkong.tradedun.Common.FileLib;
import com.thkong.tradedun.User.vo.User;

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
	public Map<String, String> uploadFile(File file, InputStream input) throws IOException{
		String fileName= fileLib.uploadFile("/upImage/", file, input);
		
		// json 데이터로 등록
        // {"uploaded" : 1, "fileName" : "test.jpg", "url" : "/img/test.jpg"}
		Map<String, String> jsonMap = new HashMap<String, String>();
		jsonMap.put("uploaded", "1");
		jsonMap.put("fileName", fileName);
		jsonMap.put("url", "/upLoad/upImage/"+fileName);
		
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
	public void insertBoard(Board board) throws Exception {
		
		//글 등록시 새로운 boardNo를 가져온다.
		int boardNo = dao.selectBoardNo(board.getCategoryCode());
		board.setBoardNo(boardNo);
		
		dao.insertBoard(board);
	}

	/**
	 * @description 게시글 카테고리 리스트 가져옴, 관리자가 아니라면 공지사항을 제외해서 보여준다.
	 * @param user
	 * @return
	 */
	@Override
	public List<Category> selectBoardCategoryList(User user) {
		List<Category> list = dao.selectBoardCategoryList();
		
		// 1192936782 <- 관리자 유저번호
		if(user == null || !user.getUserNo().equals("1192936782"))
			// 첫번째가 공지사항
			list.remove(0);
		
		return list;
	}

	/**
	 * @description 게시글 리스트 가져옴
	 * @return
	 */
	@Override
	public List<Board> selectBoardList() {
		return dao.selectBoardList();
	}

	/**
	 * @description 게시글 상세 리스트 가져온다.
	 * @param boardNo
	 * @param categoryCode
	 * @return
	 */
	@Override
	public Board selectBoard(int boardNo, String categoryCode) {
		Board board = new Board();
		board.setBoardNo(boardNo);
		board.setCategoryCode(categoryCode);
		
		return dao.selectBoard(board);
	}

	/**
	 * @description 작성글을 수정한다. 꼭 게시글 작성자와 수정자는 같은 사람이어야한다.
	 * @param boardNo
	 * @param user
	 * @return
	 */
	@Override
	public boolean updateBoard(Board board, User user) {
		Board compareBoard = dao.selectBoard(board);
		boolean chk = false;
		
		//수정할 글과 작성자가 동일해야지만 수정할수 있다. ex) 관리자는 별도로 가능
		if(compareBoard.getUserNo().equals(user.getUserNo()) || user.getUserNo().equals("1192936782")){
			dao.updateBoard(board);
			chk = true;
		}
		
		return chk;
	}

	/**
	 * @description 글삭제한다. 꼭 글 삭제 시 작성자와 수정자는 같은 유저이어야함. 관리자는 별도
	 * @param boardNo
	 * @param user
	 * @return 포워딩 시킬 페이지를 반환
	 */
	@Override
	public String deleteBoard(int boardNo, String categoryCode, User user) {
		
		String page = "/Exception/except";
		
		//비로그인 유저로 접근시 잘못된 경로라 알림
		if(user == null) {
			return page;
		}
		
		Board board = new Board();
		board.setBoardNo(boardNo);
		board.setCategoryCode(categoryCode);
		
		String writerUser = dao.selectBoard(board).getUserNo();
		
		if(writerUser.equals(user.getUserNo())) {
			if(dao.deleteBoard(board) == 1) {
				page = "redirect:/board/notice";
			}
		}
		
		return page;
	}

}
