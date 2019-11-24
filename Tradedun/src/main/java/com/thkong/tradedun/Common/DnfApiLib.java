package com.thkong.tradedun.Common;

import java.io.IOException;
import java.util.List;

import com.thkong.tradedun.Auction.vo.Auctions;
import com.thkong.tradedun.Auction.vo.Characters;
import com.thkong.tradedun.Auction.vo.CharactersEquipAvatar;
import com.thkong.tradedun.Auction.vo.ItemDetail;
import com.thkong.tradedun.Auction.vo.ItemDetails;

import kong.unirest.HttpResponse;
import kong.unirest.Unirest;

public class DnfApiLib {
	
	//DNF REST KEY
	private String dnfRestKey;
	
	/**
	 * @description 이 모듈을 사용하기 위해서는 해당 생성자의 파라메터를 필수로 받아야함
	 * @param dnfRestKey
	 */
	public DnfApiLib(String dnfRestKey) {
		this.dnfRestKey = dnfRestKey;
	}
	
	/**
	 * @description rest Response가 200이 아니라면 이 메소드를 실행하여 에러 메세지를 출력한다.
	 * @param <T>
	 * @param res
	 */
	public <T> void exceptionMethod(HttpResponse<T> res) {
		System.out.println("Oh No! Status " + res.getStatus());
    	System.out.println("Parsing Exception: "+ res.getStatusText());
    	
    	res.getParsingError().ifPresent(e -> {
    		System.out.println("ifPresent Exception: "+ e);
    		System.out.println("Original body: " + e.getOriginalBody());
    	});
	}
	
	/**
	 * @description 캐릭터 검색 : 서버와 캐릭터명을 가져와 요청 던진 후 해당하는 vo로 반환
	 * @reference_URL https://developers.neople.co.kr/contents/apiDocs/df
	 * @param server
	 * @param character
	 * @return
	 * @throws IOException
	 */
	public Characters characters(String server, String characterName) {
		// Response to Object
		Characters character = Unirest.get("https://api.neople.co.kr/df/servers/{server}/characters?characterName={name}&wordType=full&apikey={dnfKey}")
								.routeParam("server", server)
								.routeParam("name", characterName)
								.routeParam("dnfKey", dnfRestKey)
								.asObject(Characters.class)
								.ifFailure(res -> exceptionMethod(res))
								.getBody();
		 
		return character;
	}
	
	/**
	 * @description 아바타 검색 : 서버와 캐릭터Id을 가져와 착용한 아바타를 해당하는 VO로 반환
	 * @param server
	 * @param character
	 * @return
	 * @throws IOException
	 */
	public CharactersEquipAvatar charactersAvatar(String server, String characterId) throws IOException {
		
		CharactersEquipAvatar detail = Unirest.get("https://api.neople.co.kr/df/servers/{server}/characters/{characterId}/equip/avatar?apikey={dnfKey}")
												.routeParam("server", server)
												.routeParam("characterId", characterId)
												.routeParam("dnfKey", dnfRestKey)
												.asObject(CharactersEquipAvatar.class)
												.ifFailure(res -> exceptionMethod(res))
												.getBody();
		return detail;
	}
	
	/**
	 * @description 경매장 검색 : 아이템 id를 가져와 경매장에서 최저가 기준으로 최대 10개 까지만 해당하는 vo로 반환
	 * @param itemId
	 * @return
	 * @throws IOException
	 */
	public Auctions auction(String itemId) throws IOException {
		Auctions auctions = Unirest.get("https://api.neople.co.kr/df/auction?itemId={itemId}&sort=unitPrice:asc&limit=30&apikey={dnfKey}")
									.routeParam("itemId", itemId)
									.routeParam("dnfKey", dnfRestKey)
									.asObject(Auctions.class)
									.ifFailure(res -> exceptionMethod(res))
									.getBody();
		return auctions;
	}
	
	/**
	 * @description 경매장 검색 : 아이템 Name를 가져와 경매장에서 최저가 기준으로 최대 30개 까지만 해당하는 vo로 반환
	 * @param itemName
	 * @return
	 * @throws IOException
	 */
	public Auctions auctionItemName(String itemName) throws IOException {
		Auctions auctions = Unirest.get("https://api.neople.co.kr/df/auction?itemName={itemName}&sort=unitPrice:asc&limit=30&apikey={dnfKey}")
									.routeParam("itemName", itemName)
									.routeParam("dnfKey", dnfRestKey)
									.asObject(Auctions.class)
									.ifFailure(res -> exceptionMethod(res))
									.getBody();
		return auctions;
	}

	/**
	 * @description 17. 아이템 검색 : 아이템 Name을 가져와 검색 후 반환 
	 * @param itemName
	 * @return
	 * @throws IOException
	 */
	public List<ItemDetail> searchItems(String itemName, boolean auctionYN) throws IOException {
		ItemDetails detailList = Unirest.get("https://api.neople.co.kr/df/items?itemName={itemName}&wordType=match&q=trade:{auctionYN}&apikey={dnfKey}")
										.routeParam("itemName", itemName)
										.routeParam("auctionYN", String.valueOf(auctionYN))
										.routeParam("dnfKey", dnfRestKey)
										.asObject(ItemDetails.class)
										.ifFailure(res -> exceptionMethod(res))
										.getBody();
		return detailList.getRows();
	}

}
