package com.thkong.tradedun.Auction.vo;

import java.util.Date;

import lombok.Data;

@Data
public class AuctionSalesBoard {
	
	private String boardNo;
	private String subject;
	private String jobName;
	private String jobGrowName;
	private String imageName;
	private String totalPrice;
	private String category;
	private String charBox;
	private Date createDT;
	
}
