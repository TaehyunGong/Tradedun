package com.thkong.tradedun.Auction.vo;

import java.util.Date;

import lombok.Data;

@Data
public class Category {

	private String categoryCode;
	private String 	categoryKind;
	private String categoryName;
	private Date deleteDT;
	private int sort;
	
}
