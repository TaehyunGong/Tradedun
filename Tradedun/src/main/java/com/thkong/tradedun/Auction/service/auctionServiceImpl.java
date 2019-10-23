package com.thkong.tradedun.Auction.service;

import java.io.IOException;
import java.io.StringWriter;
import java.util.List;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thkong.tradedun.Auction.vo.Auction;
import com.thkong.tradedun.Auction.vo.AuctionCharacterDetail;
import com.thkong.tradedun.Auction.vo.Auctions;
import com.thkong.tradedun.Auction.vo.Avatar;
import com.thkong.tradedun.Auction.vo.Character;
import com.thkong.tradedun.Auction.vo.Characters;
import com.thkong.tradedun.Common.httpConnection;

@Transactional
@Service
public class auctionServiceImpl implements auctionService {
	
	@Value("#{props['dnf.RestKey']}")
	private String dnfRestKey;
	
	@Autowired
	private ObjectMapper mapper;
	
	@Autowired 
	VelocityEngine velocityEngine;
	
	private httpConnection conn = httpConnection.getInstance();

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
		
		character = conn.URLencoder(character);
		
		String result = conn.HttpGetConnection("https://api.neople.co.kr/df/servers/bakal/characters?"
				+ "characterName=" + character + "&wordType=full&apikey="+dnfRestKey).toString();
		
		List<Character> list = mapper.readValue(result, Characters.class).getRows();
		
		Template template = velocityEngine.getTemplate("AuctionCharacterSelectForm.vm");
        
		VelocityContext velocityContext = new VelocityContext(); 
		velocityContext.put("list", list);
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
		character = conn.URLencoder(character);
		
		String url = "https://api.neople.co.kr/df/servers/"+server+"/characters/"+character+"/equip/avatar?apikey="+dnfRestKey;
		String result = conn.HttpGetConnection(url).toString();
		AuctionCharacterDetail detail = mapper.readValue(result, AuctionCharacterDetail.class);

		for(Avatar avartar : detail.getAvatar()) {
			String itemId = avartar.getItemId();
			if(itemId.length() == 0)
				itemId = avartar.getClone().getItemId();
			minPriceAuction(itemId);
		}
		
		return detail.toString();
	}
	
	public Auction minPriceAuction(String itemId) throws IOException {
		
		String url = "https://api.neople.co.kr/df/auction?itemId="+itemId+"&sort=unitPrice:asc&limit=10&apikey="+dnfRestKey;
		System.out.println("url : " + url);
		String result = conn.HttpGetConnection(url).toString();
		System.out.println("result : " + result);
		Auctions detail = mapper.readValue(result, Auctions.class);
		System.out.println(detail.toString());
		
		return null;
	}
}
