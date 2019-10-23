package com.thkong.tradedun.Auction.vo;

import java.util.List;

import lombok.Data;

@Data
public class AuctionCharacterDetail {

	private String characterId;
	private String characterName;
	private String level;
	private String jobId;
	private String jobGrowId;
	private String jobName;
	private String jobGrowName;
	private String adventureName;
	private String guildId;
	private String guildName;
	private List<Avatar> avatar;
	
}
