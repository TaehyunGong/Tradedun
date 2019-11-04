package com.thkong.tradedun.Auction.vo;

import java.util.Date;

import lombok.Data;

@Data
public class AuctionBoard {

	private int boardNo;
	private String subject;
	private String userNo;
	private Date createDT;
	private Date deleteDT;
	private Date modifyDT;
	
}
