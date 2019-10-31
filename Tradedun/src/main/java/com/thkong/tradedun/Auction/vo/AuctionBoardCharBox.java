package com.thkong.tradedun.Auction.vo;

import java.util.Date;
import java.util.List;

import lombok.Data;

@Data
public class AuctionBoardCharBox {

	private int boardNo;
	private int charBox;
	private String charId;
	private int totalPrice;
	private char saleYN;
	private String category;
	private Date comment;
	private Date createDT;
	private Date modifyDT;
	
	private List<AuctionAvatarList> avatarList;
}
