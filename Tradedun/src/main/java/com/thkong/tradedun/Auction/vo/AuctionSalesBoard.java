package com.thkong.tradedun.Auction.vo;

import java.util.Date;

import lombok.Data;

@Data
public class AuctionSalesBoard {
	
	private String boardNo;
	private String subject;
	private String comment;
	private String jobName;
	private String jobGrowId;
	private String jobGrowName;
	private String imageName; 
	private String totalPrice;
	private String category;
	private String categoryName;
	private String charBox;
	private Date createDT;
	
}
