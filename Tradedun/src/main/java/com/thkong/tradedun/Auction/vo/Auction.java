package com.thkong.tradedun.Auction.vo;

import lombok.Data;

@Data
public class Auction {

	private String auctionNo;
	private String regDate;
	private String expireDate;
	private String itemId;
	private String itemName;
	private String itemAvailableLevel;
	private String itemRarity;
	private String itemType;
	private String itemTypeDetail;
	private int refine;
	private int reinforce;
	private String amplificationName;
	private int count;
	private int price;
	private int currentPrice;
	private int unitPrice;
	private int averagePrice;
	private String setItemName;
	private Avatar avatar;
	private String jobId;
	private String jobName;
	
}
