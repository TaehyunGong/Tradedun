package com.thkong.tradedun.Auction.service;

import java.io.IOException;
import java.io.StringWriter;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.tools.generic.NumberTool;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thkong.tradedun.Auction.dao.auctionDao;
import com.thkong.tradedun.Auction.vo.AuctionAvatarList;
import com.thkong.tradedun.Auction.vo.AuctionBoard;
import com.thkong.tradedun.Auction.vo.AuctionBoardCharBox;
import com.thkong.tradedun.Auction.vo.AuctionCharacterDetail;
import com.thkong.tradedun.Auction.vo.AuctionSalesCharacterList;
import com.thkong.tradedun.Auction.vo.Auctions;
import com.thkong.tradedun.Auction.vo.Avatar;
import com.thkong.tradedun.Auction.vo.Characters;
import com.thkong.tradedun.Auction.vo.ItemDetail;
import com.thkong.tradedun.Common.DnfApiLib;
import com.thkong.tradedun.User.vo.User;

@Transactional
@Service
public class auctionServiceImpl implements auctionService {
	
	@Value("#{props['dnf.RestKey']}")
	private String dnfRestKey;
	
	@Autowired 
	VelocityEngine velocityEngine;
	
	@Autowired
	private DnfApiLib dnfapi;
	
	@Autowired
	private ObjectMapper mapper;
	
	@Autowired
	private auctionDao dao;

	//아바타의 부위별 id, 변동될 일이 없기 때문에 고정적으로 박아준다.
	private List<String> parts = Arrays.asList(
			new String[]{"모자", "머리", "얼굴", "상의", "하의", "신발", "목가슴", "허리", "스킨"});
	
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
		
		Map<String, Object> contextValialbe = new HashMap<String, Object>();
		contextValialbe.put("list", characters.getRows());
		contextValialbe.put("server", server);
		contextValialbe.put("number", number);
		return renderTemplate(contextValialbe, "AuctionCharacterSelectForm.vm");
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
		DecimalFormat formatter = new DecimalFormat("#,##0.00");
		int availAvatar = 0;	// 경매장에서 조회된 아바타 갯수
		int minTotalSales = 0;	// 경매장에서 조회돤 최저가 아바타의 가격 합
		
		//만약 노압일경우 없는상태라면 경고창으로 반환
		if(detail.getAvatar().size() == 0) {
			Map<String, Object> contextValialbe = new HashMap<String, Object>();
			contextValialbe.put("number", number);
			return renderTemplate(contextValialbe, "AuctionCharacterNoAvatar.vm");
		}
		
		//아바타가 9피스가 아닐경우 9피스가 되도록 비어있는 슬롯을 자동 삽입
		List<Avatar> wearAvatar = fixNinePieceAvatar(detail, kind);
		
		//모자 부터 피부까지 총 8부위만 조회한다.
		List<Auctions> avatarList = searchAuctionAvatarList(wearAvatar, kind);
		
		//경매장에 조회된 아바타 갯수 구하기
		for(Auctions auctions : avatarList) {
			if(auctions.getRows().size() != 0) {
				availAvatar += 1;
				minTotalSales += auctions.getRows().get(0).getCurrentPrice();
			}
		}
		
		Map<String, Object> contextValialbe = new HashMap<String, Object>();
		contextValialbe.put("numberTool", new NumberTool());
		contextValialbe.put("number", number);
		contextValialbe.put("wearAvatar", wearAvatar);		// 착용중인 아바타
		contextValialbe.put("avatarList", avatarList);		// 경매장으로 뽑은 아바타 리스트
		contextValialbe.put("availAvatar", availAvatar);	// 경매장에서 조회된 아바타 갯수
		contextValialbe.put("minTotalSales", formatter.format(minTotalSales));// 경매장에서 조회돤 최저가 아바타의 가격 합
		
		contextValialbe.put("server", server);
		contextValialbe.put("characterId", detail.getCharacterId());
		contextValialbe.put("characterName", detail.getCharacterName());
		contextValialbe.put("jobGrowName", detail.getJobGrowName());
		
		//유저가 선택한 항목에 따라 다른 템플릿을 돌린다.
		String templateName = null;
		switch(kind) {
			case "clone" : templateName = "AuctionCloneAvatarListForm.vm"; break; //코디아바타
			case "buff" : templateName = "AuctionBuffAvatarListForm.vm"; break; //버프강화 아바타
			default : templateName = "AuctionAvatarListForm.vm"; // 기본값은 착용압
		}
		
		return renderTemplate(contextValialbe, templateName);
	}
	
	/**
	 * @description DB에서 itemDetail의 엠블렘을 가져와 매핑시켜준다. 리펙토링 필요
	 * @createDate 2019. 10. 28.
	 * @param auctions
	 * @return
	 */
	public Auctions getMatchEmblem(Auctions auctions) {
		//auctions의 rows size만큼 반복
		for(int auctionIndex=0; auctionIndex < auctions.getRows().size(); auctionIndex++) {
			Avatar avatar = auctions.getRows().get(auctionIndex).getAvatar();
			getMatchAvatarEmblem(avatar);
		}
		
		return auctions;
	}
	
	/**
	 * @description DB에서 itemDetail의 엠블렘을 가져와 매핑시켜준다. 리펙토링 필요
	 * @param avatar
	 * @return
	 */
	public Avatar getMatchAvatarEmblem(Avatar avatar) {
		//엠블렘 map 리스트
		List<ItemDetail> emblems = dao.selectItemDetailList();
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
	
	/**
	 * @description 아바타가 없는 부위도 비어있는 객체를 주어 9개의 객체로 고정시킨다.
	 * @createDate 2019. 10. 30.
	 * @param detail
	 * @param kind
	 */
	public List<Avatar> fixNinePieceAvatar(AuctionCharacterDetail detail, String kind) {
		//아바타가 9피스가 아닐경우 9피스가 되도록 비어있는 슬롯을 자동 삽입
		List<Avatar> wearAvatar = new ArrayList<Avatar>();
		for(String part : parts) {
			Avatar avat = new Avatar(); 
			avat.setSlotName(part);
			
			for(int avatarIndex=0; avatarIndex < detail.getAvatar().size(); avatarIndex++) {
				Avatar avatar = detail.getAvatar().get(avatarIndex);
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
	
	/**
	 * @description 아바타 리스트를 가져와 경매장에 검색하여 나온 리스트들을 반환시킨다.
	 * @createDate 2019. 10. 30.
	 * @param wearAvatar
	 * @param kind
	 * @throws IOException 
	 */
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
			Auctions auctions = dnfapi.auction(itemId);
			
			//엠블렘을 매핑시켜주기 위한 별도의 메소드 작성
			getMatchEmblem(auctions);
			
			avatarList.add(auctions);
		}
		
		return avatarList;
	}
	
	/**
	 * @description charBox를 템플릿 엔진으로 랜더링 후 넘겨준다.
	 * @createDate 2019. 10. 30.
	 * @param number
	 * @return
	 * @throws IOException
	 */
	@Override
	public String addCharBox(String number) throws IOException {
		Map<String, Object> contextValialbe = new HashMap<String, Object>();
		contextValialbe.put("number", number);
		return renderTemplate(contextValialbe, "AuctionAddCharBoxForm.vm");
	}

	/**
	 * @description AuctionWriter.jsp에서 작성한 charBox 리스트들을 받고 보스에 insert 한다.
	 * @createDate 2019. 10. 30.
	 * @param submitJson
	 * @return
	 * @throws IOException
	 */
	@Override
	public String insertBoardWrite(String submitJson, String subject, User user) throws IOException {
		AuctionSalesCharacterList[] salesList = mapper.readValue(submitJson, AuctionSalesCharacterList[].class);
		Date sysdate = new Date();
		int boardNo = dao.selectBoardNo();
		
		AuctionBoard auctionBoard = new AuctionBoard();
		auctionBoard.setBoardNo(boardNo);
		auctionBoard.setSubject(subject);
		auctionBoard.setUserNo(user.getUserNo());
		auctionBoard.setCreateDT(sysdate);
		
		//글 번호 가져오기 (boardNo max +1)
		dao.insertAuctionBoard(auctionBoard);
		
		int charBoxNumber = 1;
		for(AuctionSalesCharacterList list : salesList) {
			AuctionBoardCharBox auctionBoardCharBox = AuctionBoardCharBox.builder()
														.boardNo(boardNo)
														.charBox(charBoxNumber)
														.category("guitar")
														.saleYN('N')
														.totalPrice(list.getResultPrice())
														.charId(list.getCharId())
														.createDT(sysdate).build();
			
			List<AuctionAvatarList> auctionAvatarList = new ArrayList<AuctionAvatarList>();
			for(Avatar avatar : list.getAvatar()) {
				AuctionAvatarList AuctionAvatar = AuctionAvatarList.builder()
													.boardNo(boardNo)
													.charBox(charBoxNumber)
													.slotName(avatar.getSlotName())
													.avatarNo(avatar.getItemId())
													.avatarName(avatar.getItemName())
													.optionAbility(avatar.getOptionAbility())
													.createDT(sysdate)
													.build();
				
				auctionAvatarList.add(AuctionAvatar);
			}
			dao.insertAuctionAvatarList(auctionAvatarList);	// 아바타 리스트 insert
			dao.insertAuctionBoardCharBox(auctionBoardCharBox);	// charBox insert
			charBoxNumber+=1;
		}
		
		return salesList.toString();
	}
	
	/**
	 * @description 템플릿 context를 받고 랜더링 후 Template 객체로 반환한다.
	 * @createDate 2019. 11. 1.
	 * @param context
	 * @param templateName
	 * @return
	 */
	public String renderTemplate(Map<String, Object> contextValiable, String templateName) {
		VelocityContext velocityContext = new VelocityContext();
		
		//map의 키+값 의갯수 만큼 context에 삽입
		for( Map.Entry<String, Object> elem : contextValiable.entrySet() ){
			velocityContext.put(elem.getKey(), elem.getValue());
        }
		
		Template template = velocityEngine.getTemplate(templateName);
		StringWriter stringWriter = new StringWriter(); 
		template.merge(velocityContext, stringWriter);
		
		return stringWriter.toString();
	}
}
