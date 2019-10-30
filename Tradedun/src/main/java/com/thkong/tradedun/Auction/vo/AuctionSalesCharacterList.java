package com.thkong.tradedun.Auction.vo;

import java.util.List;

import lombok.Data;

@Data
public class AuctionSalesCharacterList {

	private String server;
	private String charId;
	private String charName;
	private List<Avatar> avatar;
	private int resultPrice;
	
}
