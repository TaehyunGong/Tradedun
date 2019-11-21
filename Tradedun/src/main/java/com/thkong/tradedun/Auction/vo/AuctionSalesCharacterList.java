package com.thkong.tradedun.Auction.vo;

import java.util.List;

import lombok.Data;

@Data
public class AuctionSalesCharacterList {

	private String server;
	private String charId;
	private String charName;
	private String jobId;
	private String jobGrowId;
	private List<Avatar> avatar;
	private int resultPrice;
	private String category;
	private String comment;
	
}
