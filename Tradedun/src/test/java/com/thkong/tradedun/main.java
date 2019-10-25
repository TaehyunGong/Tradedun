package com.thkong.tradedun;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

import com.thkong.tradedun.Auction.vo.ItemDetail;
import com.thkong.tradedun.Common.DnfApiLib;
import com.thkong.tradedun.Common.httpConnection;

public class main {

	public static void main(String[] args) throws IOException {
		
		ApplicationContext context = new GenericXmlApplicationContext("/SpringConfig/config/root-context.xml");
		DnfApiLib dnfapi = context.getBean("dnfapi", DnfApiLib.class);
		httpConnection conn = context.getBean("conn", httpConnection.class);
		ObjectMapper mapper = context.getBean("mapper", ObjectMapper.class);
		
		
		//노란빛
		String search = conn.URLencoder("플래티넘 엠블렘");
		String rarity = conn.URLencoder("커먼");
		
		String url = "https://api.neople.co.kr/df/items?itemName=" + search
				+ "&q=rarity:" + rarity + ",trade:true"
				+ "&limit=30"
				+ "&wordType=front"
				+ "&apikey=P4GiGs1KtJyD3VoMB3jkgzDsMI4tDNGi";
		String json = conn.HttpGetConnection(url).toString();
		TempItemDetailVo list = mapper.readValue(json, TempItemDetailVo.class);
		
		System.out.println("-------------------------------------------------------------------------------------");
		for(ItemDetail d : list.getRows()) {
			System.out.println("INSERT INTO ItemDetail (itemId, itemName, itemRarity, itemType, itemTypeDetail, itemAvailableLevel, itemRarityColor)"
					+ "VALUES('"+d.getItemId()+"', '"+d.getItemName()+"', '"+d.getItemRarity()+"', '"+d.getItemType()+"', '"+d.getItemTypeDetail()+"', '"+d.getItemAvailableLevel()+"', NULL);");
		}
	}
}
