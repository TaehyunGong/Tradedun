package com.thkong.tradedun.Auction.vo;

import java.util.Date;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AuctionBoardCharBox {

	private int boardNo;
	private int charBox;
	private String charId;
	private String jobName;
	private String jobGrowName;
	private String imageName;
	private int totalPrice;
	private char saleYN;
	private String category;
	private Date comment;
	private Date createDT;
	private Date modifyDT;
}
