package com.thkong.tradedun;

import java.io.IOException;

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
		
		String rare = "찬란한";
		
		//노란빛
		String search = conn.URLencoder(rare+" 노란빛");
		String rarity = conn.URLencoder("");
		
		String url = "https://api.neople.co.kr/df/items?itemName=" + search
				+ "&q=rarity:" + rarity + ",trade:true"
				+ "&limit=30"
				+ "&wordType=front"
				+ "&apikey=P4GiGs1KtJyD3VoMB3jkgzDsMI4tDNGi";
		String json = conn.HttpGetConnection(url).toString();
		TempItemDetailVo list = mapper.readValue(json, TempItemDetailVo.class);
		
		//녹색빛
		search = conn.URLencoder(rare+" 녹색빛");
		
		url = "https://api.neople.co.kr/df/items?itemName=" + search
				+ "&q=rarity:" + rarity + ",trade:true"
				+ "&limit=30"
				+ "&wordType=front"
				+ "&apikey=P4GiGs1KtJyD3VoMB3jkgzDsMI4tDNGi";
		for(ItemDetail n : mapper.readValue(conn.HttpGetConnection(url).toString(), TempItemDetailVo.class).getRows()) {
			list.getRows().add(n);
		}
		
		//다색
		search = conn.URLencoder(rare+" 다색");
		
		url = "https://api.neople.co.kr/df/items?itemName=" + search
				+ "&q=rarity:" + rarity + ",trade:true"
				+ "&limit=30"
				+ "&wordType=front"
				+ "&apikey=P4GiGs1KtJyD3VoMB3jkgzDsMI4tDNGi";
		for(ItemDetail n : mapper.readValue(conn.HttpGetConnection(url).toString(), TempItemDetailVo.class).getRows()) {
			list.getRows().add(n);
		}
		
		//푸른빛
		search = conn.URLencoder(rare+" 푸른빛");
		
		url = "https://api.neople.co.kr/df/items?itemName=" + search
				+ "&q=rarity:" + rarity + ",trade:true"
				+ "&limit=30"
				+ "&wordType=front"
				+ "&apikey=P4GiGs1KtJyD3VoMB3jkgzDsMI4tDNGi";
		for(ItemDetail n : mapper.readValue(conn.HttpGetConnection(url).toString(), TempItemDetailVo.class).getRows()) {
			list.getRows().add(n);
		}
		
		//붉은빛
		search = conn.URLencoder(rare+" 붉은빛");
		
		url = "https://api.neople.co.kr/df/items?itemName=" + search
				+ "&q=rarity:" + rarity + ",trade:true"
				+ "&limit=30"
				+ "&wordType=front"
				+ "&apikey=P4GiGs1KtJyD3VoMB3jkgzDsMI4tDNGi";
		for(ItemDetail n : mapper.readValue(conn.HttpGetConnection(url).toString(), TempItemDetailVo.class).getRows()) {
			list.getRows().add(n);
		}
		
		//붉은빛
		search = conn.URLencoder(rare+" 듀얼");
		
		url = "https://api.neople.co.kr/df/items?itemName=" + search
				+ "&q=rarity:" + rarity + ",trade:true"
				+ "&limit=30"
				+ "&wordType=front"
				+ "&apikey=P4GiGs1KtJyD3VoMB3jkgzDsMI4tDNGi";
		for(ItemDetail n : mapper.readValue(conn.HttpGetConnection(url).toString(), TempItemDetailVo.class).getRows()) {
			list.getRows().add(n);
		}
		
		System.out.println("-------------------------------------------------------------------------------------");
		for(ItemDetail d : list.getRows()) {
			System.out.println(d.getItemId()+"\t"+d.getItemName()+"\t"+d.getItemRarity()+"\t"+d.getItemType()+"\t"+d.getItemTypeDetail()+"\t"+d.getItemAvailableLevel());
		}
	}
}