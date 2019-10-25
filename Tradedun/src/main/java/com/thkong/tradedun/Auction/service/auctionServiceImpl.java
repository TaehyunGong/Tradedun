package com.thkong.tradedun.Auction.service;

import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thkong.tradedun.Auction.vo.Auction;
import com.thkong.tradedun.Auction.vo.AuctionCharacterDetail;
import com.thkong.tradedun.Auction.vo.Auctions;
import com.thkong.tradedun.Auction.vo.Avatar;
import com.thkong.tradedun.Auction.vo.Characters;
import com.thkong.tradedun.Common.DnfApiLib;

@Transactional
@Service
public class auctionServiceImpl implements auctionService {
	
	@Value("#{props['dnf.RestKey']}")
	private String dnfRestKey;
	
	@Autowired 
	VelocityEngine velocityEngine;
	
	@Autowired
	private DnfApiLib dnfapi;

	/**
	 * @description 캐릭터명으로 조회후 해당 캐릭에 match되는 리스트 뿌려줌, 리펙토링 필요
	 * @createDate 2019. 10. 23.
	 * @param server
	 * @param character
	 * @param number
	 * @return
	 * @throws IOException
	 */
	@Override
	public String charSeachList(String server, String character, String number) throws IOException {
		Characters characters = dnfapi.characters(server,character);
		
		Template template = velocityEngine.getTemplate("AuctionCharacterSelectForm.vm");
        
		VelocityContext velocityContext = new VelocityContext(); 
		velocityContext.put("list", characters.getRows());
		velocityContext.put("server", server);
		velocityContext.put("number", number);

		StringWriter stringWriter = new StringWriter(); 
		template.merge(velocityContext, stringWriter);
		
		return stringWriter.toString();
	}

	/**
	 * @description 캐릭터id를 가져와 해당 아바타를 상세하게 뿌려줌, 리펙토링 필요
	 * @createDate 2019. 10. 23.
	 * @param server
	 * @param character
	 * @param number
	 * @return
	 * @throws IOException
	 */
	@Override
	public String charAvatarSeach(String server, String character, String number, String kind) throws IOException {
		AuctionCharacterDetail detail = dnfapi.charactersAvatar(server, character);
		List<Auctions> avatarList = new ArrayList<Auctions>();
		int availAvatar = 0;	// 경매장에서 조회된 아바타 갯수
		int minTotalSales = 0;	// 경매장에서 조회돤 최저가 아바타의 가격 합
		
		//임시용
		List<String> parts = Arrays.asList(
				new String[]{"HEADGEAR", "HAIR", "FACE", "JACKET", "PANTS", "SHOES", "BREAST", "WAIST", "SKIN"});
		
		//만약 노압일경우 없는상태라면 경고창으로 반환
		if(detail.getAvatar().size() == 0)
			return "noAvatar";
		
		//아바타가 9피스가 아닐경우 슬롯을 자동 삽입
		List<Avatar> wearAvatar = new ArrayList<Avatar>();
		for(String part : parts) {
			Avatar avat = new Avatar(); 
			avat.setSlotId(part);
			
			for(Avatar avatar : detail.getAvatar()) {
				if(part.contains(avatar.getSlotId())) {
					avat = avatar;
					break;
				}
			}
			wearAvatar.add(avat);
		}
		
		//모자 부터 피부까지 총 8부위만 조회한다.
		for(Avatar avatar : wearAvatar) {
			String itemId;
			if(kind.equals("wear") || avatar.getSlotId().equals("SKIN")) {
				itemId = avatar.getItemId();
			}
			else {
				itemId = avatar.getClone().getItemId();
				avatar.setItemId(avatar.getClone().getItemId());
				avatar.setItemName(avatar.getClone().getItemName());
				avatar.setEmblems(null);
				avatar.setOptionAbility(null);
			}
			Auctions auctions = dnfapi.auction(itemId);
			//경매장에 조회된 아바타 갯수 구하기
			if(auctions.getRows().size() != 0) {
				availAvatar += 1;
				minTotalSales += auctions.getRows().get(0).getCurrentPrice();
			}
			avatarList.add(auctions);
		}
		
		VelocityContext velocityContext = new VelocityContext();
		velocityContext.put("number", number);
		velocityContext.put("wearAvatar", wearAvatar);		// 착용중인 아바타
		velocityContext.put("avatarList", avatarList);		// 경매장으로 뽑은 아바타 리스트
		velocityContext.put("availAvatar", availAvatar);	// 경매장에서 조회된 아바타 갯수
		velocityContext.put("minTotalSales", minTotalSales);// 경매장에서 조회돤 최저가 아바타의 가격 합
		
		velocityContext.put("server", server);
		velocityContext.put("characterId", detail.getCharacterId());
		velocityContext.put("characterName", detail.getCharacterName());
		velocityContext.put("jobGrowName", detail.getJobGrowName());
		
		Template template = velocityEngine.getTemplate("AuctionAvatarListForm.vm");
		StringWriter stringWriter = new StringWriter(); 
		template.merge(velocityContext, stringWriter);
		
		return stringWriter.toString();
	}
	
	@Override
	public String addCharBox(String number) throws IOException {
		VelocityContext velocityContext = new VelocityContext();
		velocityContext.put("number", number);
		
		Template template = velocityEngine.getTemplate("AuctionAddCharBoxForm.vm");
		StringWriter stringWriter = new StringWriter(); 
		template.merge(velocityContext, stringWriter);
		
		return stringWriter.toString();
	}
}
