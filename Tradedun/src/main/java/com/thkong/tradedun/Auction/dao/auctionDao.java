package com.thkong.tradedun.Auction.dao;

import java.util.List;
import java.util.Map;

import com.thkong.tradedun.Auction.vo.AuctionAvatarList;
import com.thkong.tradedun.Auction.vo.AuctionBoard;
import com.thkong.tradedun.Auction.vo.AuctionBoardCharBox;
import com.thkong.tradedun.Auction.vo.AuctionSalesBoard;
import com.thkong.tradedun.Auction.vo.Avatar;
import com.thkong.tradedun.Auction.vo.AvatarMastar;
import com.thkong.tradedun.Auction.vo.Category;
import com.thkong.tradedun.Auction.vo.CodeTB;
import com.thkong.tradedun.Auction.vo.ItemDetail;
import com.thkong.tradedun.Auction.vo.JobGrow;

public interface auctionDao {

	public List<ItemDetail> selectItemDetailList();

	public int selectBoardNo();

	public void insertAuctionBoard(AuctionBoard auctionBoard);

	public void insertAuctionBoardCharBox(AuctionBoardCharBox auctionBoardCharBox);

	public void insertAuctionAvatarList(List<AuctionAvatarList> auctionAvatarList);
	
	public List<Category> selectAvatarCategory(String jobId);

	public List<CodeTB> selectAllJobList();

	public List<AvatarMastar> selectRareAvatarList();

	public List<Avatar> selectAvatarSet(AvatarMastar mastar);

	public List<JobGrow> selectJobGrowAllList();

	public List<AuctionSalesBoard> selectAuctionSalesBoard(Map<String, String> pageMap);

	public List<JobGrow> selectJobGrowList(String jobId);
	
	public AuctionBoardCharBox selectAuctionSalesBoardDetail(String boardNo);
}
