package com.thkong.tradedun.Auction.vo;

import java.util.List;

import lombok.Data;

@Data
public class Avatar {

	private String slotId;
	private String slotName;
	private String itemId;
	private String itemName;
	private String itemRarity;
	private ItemDetail clone;
	private ItemDetail random;
	private String optionAbility;
	private String ability;
	private List<ItemDetail> emblems;
	
	//data-emblemName에서 가져오는 전용 필드
	private String emblemName;
	
}
