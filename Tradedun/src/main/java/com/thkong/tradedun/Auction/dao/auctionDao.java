package com.thkong.tradedun.Auction.dao;

import java.util.List;

import com.thkong.tradedun.Auction.vo.ItemDetail;

public interface auctionDao {

	public List<ItemDetail> selectItemDetailList();
}
