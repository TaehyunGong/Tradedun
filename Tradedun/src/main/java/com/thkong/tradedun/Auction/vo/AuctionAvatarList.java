package com.thkong.tradedun.Auction.vo;

import java.util.Date;

import lombok.Data;

@Data
public class AuctionAvatarList {

	private int boardNo;
	private int charBox;
	private String avatarNo;
	private String avatarName;
	private String optionAbility;
	private String platinum;
	private String emblemOne;
	private String emblemTwo;
	private int price;
	private Date createDT;
	private Date modifyDT;
}
