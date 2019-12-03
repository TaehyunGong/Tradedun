 package com.thkong.tradedun;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

import com.thkong.tradedun.Auction.vo.Avatar;
import com.thkong.tradedun.Auction.vo.Characters;
import com.thkong.tradedun.Auction.vo.CharactersEquipAvatar;
import com.thkong.tradedun.Auction.vo.ItemDetail;
import com.thkong.tradedun.Common.DnfApiLib;

public class main {
	
	ApplicationContext context = new GenericXmlApplicationContext("/SpringConfig/config/*.xml");
	DnfApiLib dnfapi = context.getBean("dnfapi", DnfApiLib.class);
	ObjectMapper mapper = context.getBean("mapper", ObjectMapper.class);
	SqlSession session = context.getBean("sqlSession", SqlSession.class);
	
	
	public void process() throws IOException {
		Characters ch = dnfapi.characters("bakal", "체원");
		String charId = ch.getRows().get(0).getCharacterId();
		
		CharactersEquipAvatar avatars = dnfapi.charactersAvatar("bakal", charId);
		
		Avatar cort = null;
		for(Avatar avatar : avatars.getAvatar()) {
			if(avatar.getSlotName().equals("상의 아바타")) {
				cort = avatar;
			}
		}
		
		
		for(ItemDetail detail : cort.getEmblems()) {
			String emblem = detail.getItemName();
			
			if(emblem.contains("플래티넘")) {
				int index = emblem.indexOf('[');
//				System.out.println(emblem.substring(index));
			}
		}
		
		List<ItemDetail> emblems;
		Map<String, ItemDetail> emblemMap = null;
		//별도로 사용시 select해서 가져와야한다.
		if(emblemMap == null) {
			//엠블렘 map 리스트
			emblems = session.selectList("selectItemDetailList");
			// map의 키값을 통해 매핑시키기 위해 list를 map으로 치환
			emblemMap = new HashMap<String, ItemDetail>();
			for(ItemDetail avatarEmblem : emblems) {
				emblemMap.put(avatarEmblem.getItemName(), avatarEmblem);
			}
		}
		
		//41f1c_platinum_emblem
		
		ItemDetail detail = emblemMap.get("41f1c_platinum_emblem");
		
	}
	
	public static void main(String[] args) throws IOException {
		new main().process();
	}
}
