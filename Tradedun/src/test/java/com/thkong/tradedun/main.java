package com.thkong.tradedun;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.apache.velocity.tools.generic.NumberTool;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

import com.thkong.tradedun.Auction.vo.AuctionCharacterDetail;
import com.thkong.tradedun.Auction.vo.Auctions;
import com.thkong.tradedun.Auction.vo.Avatar;
import com.thkong.tradedun.Auction.vo.Category;
import com.thkong.tradedun.Auction.vo.ItemDetail;
import com.thkong.tradedun.Common.DnfApiLib;

public class main {
	
	ApplicationContext context = new GenericXmlApplicationContext("/SpringConfig/config/*.xml");
	DnfApiLib dnfapi = context.getBean("dnfapi", DnfApiLib.class);
	ObjectMapper mapper = context.getBean("mapper", ObjectMapper.class);
	SqlSession session = context.getBean("sqlSession", SqlSession.class);
	
	public List<Avatar> fixNinePieceAvatar(List<Avatar> detail, String kind, List<String> fixParts) {
		//아바타가 9피스가 아닐경우 9피스가 되도록 비어있는 슬롯을 자동 삽입
		List<Avatar> wearAvatar = new ArrayList<Avatar>();
		for(String part : fixParts) {
			Avatar avat = new Avatar(); 
			avat.setSlotName(part);
			
			for(int avatarIndex=0; avatarIndex < detail.size(); avatarIndex++) {
				Avatar avatar = detail.get(avatarIndex);
				//avatar.getSlotName().split(" ")[0]) ex) "모자 아바타" -> "모자"
				if(part.contains(avatar.getSlotName().split(" ")[0])) {
					avat = avatar;
					avat.setSlotName(avat.getSlotName().split(" ")[0]);
					getMatchAvatarEmblem(avat);
					break;
				}
			}
			
			//클론압 선택시 아바타가 부위별로만 있다면 비어있는 객체를 넣어준다.
			if(kind.equals("clone") && avat.getClone() == null) {
				avat.setClone(new ItemDetail());
			}
			
			wearAvatar.add(avat);
		}
		
		return wearAvatar;
	}
	
	public List<Auctions> searchAuctionAvatarList(List<Avatar> wearAvatar, String kind) throws IOException {
		List<Auctions> avatarList = new ArrayList<Auctions>();
		
		for(Avatar avatar : wearAvatar) {
			String itemId;
			if(kind.equals("wear") || avatar.getSlotId().equals("스킨")) {
				itemId = avatar.getItemId();
			}
			else {
				itemId = avatar.getClone().getItemId();
				avatar.setItemId(avatar.getClone().getItemId());
				avatar.setItemName(avatar.getClone().getItemName());
				avatar.setEmblems(null);
				avatar.setOptionAbility(null);
			}
			
			Auctions auctions = null;
			if(itemId != null) {
				auctions = dnfapi.auction(itemId);
				
				//엠블렘을 매핑시켜주기 위한 별도의 메소드 작성
				getMatchEmblem(auctions);
			}
			
			avatarList.add(auctions);
		}
		
		return avatarList;
	}
	
	public Auctions getMatchEmblem(Auctions auctions) {
		//auctions의 rows size만큼 반복
		for(int auctionIndex=0; auctionIndex < auctions.getRows().size(); auctionIndex++) {
			Avatar avatar = auctions.getRows().get(auctionIndex).getAvatar();
			getMatchAvatarEmblem(avatar);
		}
		
		return auctions;
	}
	
	public Avatar getMatchAvatarEmblem(Avatar avatar) {
		// 엠블렘이 없으면 원본을 반환시킨다.
		if(avatar.getEmblems() == null) 
			return avatar;
		
		//엠블렘 map 리스트
		List<ItemDetail> emblems = session.selectList("selectItemDetailList");
		Map<String, ItemDetail> emblemMap = new HashMap<String, ItemDetail>();
		for(ItemDetail avatarEmblem : emblems) {
			emblemMap.put(avatarEmblem.getItemName(), avatarEmblem);
		}
		
		//avatar의 엠블렘의 수 만큼 반복 
		for(int emblemIndex=0; emblemIndex < avatar.getEmblems().size(); emblemIndex++) {
			ItemDetail emblem = avatar.getEmblems().get(emblemIndex);
			
			//DB에서 가져온 엠블렘이랑 일치하는 엠블렘 객체를 대입해준다.
			ItemDetail newEmblem = emblemMap.get(emblem.getItemName());
			
			//만약 map에 없는 엠블렘이라면 패스한다.
			if(newEmblem != null)
				avatar.getEmblems().set(emblemIndex, newEmblem);
		}
		
		return avatar;
	}
	
	public void process() throws IOException {
		//아바타의 부위별 id, 변동될 일이 없기 때문에 고정적으로 박아준다.
		List<String> parts = Arrays.asList(new String[]{"모자", "머리", "얼굴", "상의", "하의", "신발", "목가슴", "허리", "스킨"});
		
		AuctionCharacterDetail detail = dnfapi.charactersAvatar("bakal", "89c8fc267149d7827047dd9ad72e0f6e");
		List<Category> category = session.selectList("selectAvatarCategory",detail.getJobId());	//아바타의 카테고리 리스트
		DecimalFormat formatter = new DecimalFormat("#,##0.00");
		int availAvatar = 0;	// 경매장에서 조회된 아바타 갯수
		int minTotalSales = 0;	// 경매장에서 조회돤 최저가 아바타의 가격 합
		
		String kind = "clone";
		
		//만약 노압일경우 없는상태라면 경고창으로 반환
		if(detail.getAvatar().size() == 0) {
			System.out.println("노압임");
			return;
		}
		
		//아바타가 9피스가 아닐경우 9피스가 되도록 비어있는 슬롯을 자동 삽입
		List<Avatar> wearAvatar = fixNinePieceAvatar(detail.getAvatar(), kind, parts);
		
		
		//모자 부터 피부까지 총 8부위만 조회한다.
		List<Auctions> avatarList = searchAuctionAvatarList(wearAvatar, kind);
		
		//경매장에 조회된 아바타 갯수 구하기
		for(Auctions auctions : avatarList) {
			
			//클론의 경우 스킨을 못가져온다. 별도의 null 체크
			if(auctions == null) continue;
			if(auctions.getRows() != null && auctions.getRows().size() != 0) {
				availAvatar += 1;
				minTotalSales += auctions.getRows().get(0).getCurrentPrice();
			}
		}
		
		Map<String, Object> contextValialbe = new HashMap<String, Object>();
		contextValialbe.put("numberTool", new NumberTool());
		contextValialbe.put("categoryList", category);
		contextValialbe.put("wearAvatar", wearAvatar);		// 착용중인 아바타
		contextValialbe.put("avatarList", avatarList);		// 경매장으로 뽑은 아바타 리스트
		contextValialbe.put("availAvatar", availAvatar);	// 경매장에서 조회된 아바타 갯수
		contextValialbe.put("minTotalSales", formatter.format(minTotalSales));// 경매장에서 조회돤 최저가 아바타의 가격 합
		
		System.out.println(avatarList);
	}
	
	public static void main(String[] args) throws IOException {
		new main().process();
	}
}