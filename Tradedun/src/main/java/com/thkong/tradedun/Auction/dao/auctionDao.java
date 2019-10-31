package com.thkong.tradedun.Auction.dao;

import java.util.List;

import com.thkong.tradedun.Auction.vo.AuctionBoard;
import com.thkong.tradedun.Auction.vo.AuctionBoardCharBox;
import com.thkong.tradedun.Auction.vo.ItemDetail;

public interface auctionDao {

	public List<ItemDetail> selectItemDetailList();

	public int selectBoardNo();

	public int insertAuctionBoard(AuctionBoard auctionBoard);

	public void insertAuctionBoardCharBox(AuctionBoardCharBox auctionBoardCharBox);
}
