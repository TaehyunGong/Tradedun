package com.thkong.tradedun.Auction.dao;

import java.util.List;

import com.thkong.tradedun.Auction.vo.AuctionAvatarList;
import com.thkong.tradedun.Auction.vo.AuctionBoard;
import com.thkong.tradedun.Auction.vo.AuctionBoardCharBox;
import com.thkong.tradedun.Auction.vo.Category;
import com.thkong.tradedun.Auction.vo.ItemDetail;

public interface auctionDao {

	public List<ItemDetail> selectItemDetailList();

	public int selectBoardNo();

	public void insertAuctionBoard(AuctionBoard auctionBoard);

	public void insertAuctionBoardCharBox(AuctionBoardCharBox auctionBoardCharBox);

	public void insertAuctionAvatarList(List<AuctionAvatarList> auctionAvatarList);
	
	public List<Category> selectAvatarCategory();
}
