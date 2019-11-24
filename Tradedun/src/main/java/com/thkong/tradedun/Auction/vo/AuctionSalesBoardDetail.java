package com.thkong.tradedun.Auction.vo;

import java.util.List;

import lombok.Data;

@Data
public class AuctionSalesBoardDetail {

	private String boardNo;
	private String subject;
	private String userNo;
	private List<AuctionBoardCharBox> auctionBoardCharBox;
	
}
