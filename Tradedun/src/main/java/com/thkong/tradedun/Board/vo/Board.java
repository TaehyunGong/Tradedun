package com.thkong.tradedun.Board.vo;

import java.util.Date;

import lombok.Data;

@Data
public class Board {

	private int boardNo;
	private String categoryCode;
	private String title;
	private String contents;
	private Date createDT;
	private Date modifyDT;
	private Date deleteDT;
}
