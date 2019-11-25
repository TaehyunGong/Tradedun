package com.thkong.tradedun.Auction.vo;

import java.util.Date;
import java.util.List;

import lombok.Data;

@Data
public class AuctionBoardCharBox {

	private int boardNo;
	private int charBox;
	private String charId;
	private String charName;
	private String avatarKind;
	private String jobId;
	private String jobGrowId;
	private String jobGrowName;
	private String imageName;
	private int totalPrice;
	private char saleYN;
	private String category;
	private String categoryName;
	private String comment;
	private Date createDT;
	private Date modifyDT;
	
	private List<AuctionAvatarList> auctionAvatarList;
}
