package com.thkong.tradedun.Common;

import java.io.IOException;

import org.codehaus.jackson.map.ObjectMapper;

import com.thkong.tradedun.Auction.vo.Auction;
import com.thkong.tradedun.Auction.vo.AuctionCharacterDetail;
import com.thkong.tradedun.Auction.vo.Auctions;
import com.thkong.tradedun.Auction.vo.Characters;
import com.thkong.tradedun.Auction.vo.DnfApiError;

public class DnfApiLib {
	
	private ObjectMapper mapper;
	private httpConnection conn;
	private String dnfRestKey;
	
	/**
	 * @description 이 모듈을 사용하기 위해서는 해당 생성자의 파라메터를 필수로 받아야함
	 * @param mapper
	 * @param conn
	 * @param dnfRestKey
	 */
	public DnfApiLib(ObjectMapper mapper, httpConnection conn, String dnfRestKey) {
		this.mapper = mapper;
		this.conn = conn;
		this.dnfRestKey = dnfRestKey;
	}
	
	/**
	 * @description 만약 response에서 error를 반환시 response 에러 json으로 throw 던진다. 
	 * @param obj
	 * @param json
	 * @throws IOException
	 */
	public void isResponseError(String json) throws IOException {
		DnfApiError error = mapper.readValue(json, DnfApiError.class);
		if(error.getError() != null) {
			throw new IOException(json);
		}
	}
	
	/**
	 * @description 캐릭터 검색 : 서버와 캐릭터명을 가져와 요청 던진 후 해당하는 vo로 반환
	 * @reference_URL https://developers.neople.co.kr/contents/apiDocs/df
	 * @param server
	 * @param character
	 * @return
	 * @throws IOException
	 */
	public Characters characters(String server, String character) throws IOException {
		character = conn.URLencoder(character);
		
		String url = String.format("https://api.neople.co.kr/df/servers/%s/characters?characterName=%s&wordType=full&apikey=%s"
				, server, character, dnfRestKey);
		String json = conn.HttpGetConnection(url).toString();
		Characters list = mapper.readValue(json, Characters.class);
		
		//json을 던져 eror json인지 체크 후 Error 라면 IOException throw 반환
		isResponseError(json);
		
		return list;
	}
	
	/**
	 * @description 아바타 검색 : 서버와 캐릭터명을 가져와 착용한 아바타를 해당하는 VO로 반환
	 * @param server
	 * @param character
	 * @return
	 * @throws IOException
	 */
	public AuctionCharacterDetail charactersAvatar(String server, String character) throws IOException {
		character = conn.URLencoder(character);
		
		String url = String.format("https://api.neople.co.kr/df/servers/%s/characters/%s/equip/avatar?apikey=%s"
				, server, character, dnfRestKey);
		String json = conn.HttpGetConnection(url).toString();
		AuctionCharacterDetail detail = mapper.readValue(json, AuctionCharacterDetail.class);
		
		//json을 던져 eror json인지 체크 후 Error 라면 IOException throw 반환
		isResponseError(json);		
		
		return detail;
	}
	
	/**
	 * @description 경매장 검색 : 아이템 id를 가져와 경매장에서 최저가 기준으로 최대 10개 까지만 해당하는 vo로 반환
	 * @param itemId
	 * @return
	 * @throws IOException
	 */
	public Auctions auction(String itemId) throws IOException {
		String url = String.format("https://api.neople.co.kr/df/auction?itemId=%s&sort=unitPrice:asc&limit=10&apikey=%s"
				, itemId, dnfRestKey);
		String json = conn.HttpGetConnection(url).toString();
		Auctions auctions = mapper.readValue(json, Auctions.class);
		
		//json을 던져 eror json인지 체크 후 Error 라면 IOException throw 반환
		isResponseError(json);		
		
		return auctions;
	}

}
