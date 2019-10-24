package com.thkong.tradedun.Auction.service;

import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayList;
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
	public String charAvatarSeach(String server, String character, String number) throws IOException {
		AuctionCharacterDetail detail = dnfapi.charactersAvatar(server, character);

		List<Auctions> avatarList = new ArrayList<Auctions>();
		List<Auctions> cloneAvatarList = new ArrayList<Auctions>();
		
		//모자 부터 피부까지 총 9부위만 조회한다.
		for(int n=0; n<9; n++) {
			Avatar avatar = detail.getAvatar().get(n);
			String itemId = avatar.getItemId();
			String cloneItemId = avatar.getClone().getItemId();
			
			avatarList.add(dnfapi.auction(itemId));
			cloneAvatarList.add(dnfapi.auction(cloneItemId));
		}
		
		Template template = velocityEngine.getTemplate("AuctionAvatarListForm.vm");
        
		VelocityContext velocityContext = new VelocityContext(); 
		velocityContext.put("wearAvatar", detail);
		velocityContext.put("avatarList", avatarList);
		velocityContext.put("cloneAvatarList", cloneAvatarList);
		
		StringWriter stringWriter = new StringWriter(); 
		template.merge(velocityContext, stringWriter);
		System.out.println(stringWriter.toString());
		return stringWriter.toString();
	}
	
	public Auction minPriceAuction(String itemId) throws IOException {
		Auctions detail = dnfapi.auction(itemId);

		if(detail.getRows().size() != 0) {
			String name = detail.getRows().get(0).getItemName();
			int price = detail.getRows().get(0).getCurrentPrice();
			
			System.out.println(name + " : " + price);
		}else {
			System.out.println(itemId + " : 없음");
		}
		
		return null;
	}
}
