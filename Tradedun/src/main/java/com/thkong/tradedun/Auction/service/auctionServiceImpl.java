package com.thkong.tradedun.Auction.service;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.net.URL;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.servlet.ServletContext;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.tools.generic.NumberTool;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thkong.tradedun.Auction.dao.auctionDao;
import com.thkong.tradedun.Auction.vo.Auction;
import com.thkong.tradedun.Auction.vo.AuctionAvatarList;
import com.thkong.tradedun.Auction.vo.AuctionBoard;
import com.thkong.tradedun.Auction.vo.AuctionBoardCharBox;
import com.thkong.tradedun.Auction.vo.AuctionCharacterDetail;
import com.thkong.tradedun.Auction.vo.AuctionSalesCharacterList;
import com.thkong.tradedun.Auction.vo.Auctions;
import com.thkong.tradedun.Auction.vo.Avatar;
import com.thkong.tradedun.Auction.vo.AvatarMastar;
import com.thkong.tradedun.Auction.vo.Category;
import com.thkong.tradedun.Auction.vo.Characters;
import com.thkong.tradedun.Auction.vo.CodeTB;
import com.thkong.tradedun.Auction.vo.ItemDetail;
import com.thkong.tradedun.Auction.vo.JobGrow;
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
	private ServletContext servletContext;

	@Autowired
	private auctionDao dao;

	//아바타의 부위별 id, 변동될 일이 없기 때문에 고정적으로 박아준다.
	private List<String> parts = Arrays.asList(
			new String[]{"모자", "머리", "얼굴", "상의", "하의", "신발", "목가슴", "허리", "스킨"});

	//아바타의 부위별 id, 변동될 일이 없기 때문에 고정적으로 박아준다. 쇼룸의 경우 피부, 무기, 배경이 추가된다.
	private List<String> showRoomParts = Arrays.asList(
			new String[]{"모자", "머리", "얼굴", "상의", "하의", "신발", "목가슴", "허리", "피부", "무기", "배경"});
	
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
		List<Category> category = dao.selectAvatarCategory(detail.getJobId());	//아바타의 카테고리 리스트
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
		contextValialbe.put("number", number);
		contextValialbe.put("wearAvatar", wearAvatar);		// 착용중인 아바타
		contextValialbe.put("avatarList", avatarList);		// 경매장으로 뽑은 아바타 리스트
		contextValialbe.put("availAvatar", availAvatar);	// 경매장에서 조회된 아바타 갯수
		contextValialbe.put("minTotalSales", formatter.format(minTotalSales));// 경매장에서 조회돤 최저가 아바타의 가격 합
		
		contextValialbe.put("server", server);
		contextValialbe.put("characterId", detail.getCharacterId());
		contextValialbe.put("characterName", detail.getCharacterName());
		contextValialbe.put("jobName", detail.getJobName());
		contextValialbe.put("jobGrowName", detail.getJobGrowName());
		
		//유저가 선택한 항목에 따라 다른 템플릿을 돌린다.
		String templateName = null;
		switch(kind) {
//			case "clone" : templateName = "AuctionCloneAvatarListForm.vm"; break; //코디아바타
//			case "buff" : templateName = "AuctionBuffAvatarListForm.vm"; break; //버프강화 아바타
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
		// 엠블렘이 없으면 원본을 반환시킨다.
		if(avatar.getEmblems() == null) 
			return avatar;
		
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
				//클론아바타의 경우는 없는 필드를 수작성으로 생성해주어야한다.
				itemId = avatar.getClone().getItemId();
				avatar.setItemId(avatar.getClone().getItemId());
				avatar.setItemName(avatar.getClone().getItemName());
				avatar.setEmblems(null);
				avatar.setOptionAbility(null);
			}
			
			Auctions auctions = null;
			//빈 슬롯의 경우  null이기 때문에 넘겨준다.
			if(itemId != null) {
				auctions = dnfapi.auction(itemId);
				
				//엠블렘을 매핑시켜주기 위한 별도의 메소드 작성
				getMatchEmblem(auctions);
			}
			
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
														.jobName(list.getJobName())
														.jobGrowName(list.getJobGrowName())
														.category(list.getCategory())
														.comment(list.getComment())
														.createDT(sysdate).build();
			
			//charBox의 아바타 리스트를 list로 만들어줌
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
			auctionBoardCharBox.setImageName(saveCharacterImage(list.getCharId()));	//캐릭터 이미지 저장
			
			dao.insertAuctionAvatarList(auctionAvatarList);	// 아바타 리스트 insert
			dao.insertAuctionBoardCharBox(auctionBoardCharBox);	// charBox insert
			charBoxNumber+=1;
		}
		
		return "1";
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
	
	/**
	 * @description 캐릭터 id를 가져와 이미지로 저장한다.
	 * @createDate 2019. 11. 1.
	 * @param charId
	 * @return 
	 * @throws IOException 
	 */
	public String saveCharacterImage(String charId) throws IOException {
//		String path = resourceLoader.getResource("classpath:CharacterImages\\").getURI().getPath();
		String path = servletContext.getRealPath("/resources/upImage/CharacterImages/");
		String uploadFileName = fileNameGenerater(charId+".png");
		File outputFile = new File(path+uploadFileName);
		 
		URL url = new URL("https://img-api.neople.co.kr/df/servers/bakal/characters/"+charId);
		BufferedImage bi = ImageIO.read(url);
	    ImageIO.write(bi, "png", outputFile);
	    
		return uploadFileName;
	}
	
	/**
	 * @return String
	 * @description 업로드 하기 위해 랜덤한 파일명을 생성
	 */
	public String fileNameGenerater(String originFileName) {
		//업로드 이름 랜덤으로 생성
		String random = String.valueOf((Math.random()*1000000) + System.currentTimeMillis());
		String fileName = random + new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
		
		//확장자를 업로드 이름에 삽입
		String ext = originFileName.substring( originFileName.lastIndexOf(".") + 1 );
		fileName = fileName + "." + ext; //확장자
		
		return fileName;
	}

	/**
	 * @description jobId직군을 Code테이블에서 다 가져온다.
	 * @return List<CodeTB>
	 */
	@Override
	public List<CodeTB> selectAllJobList() {
		return dao.selectAllJobList();
	}

	/**
	 * @description 직군과 쇼룸의 복붙 정보을 가지고 경매장에서 검색하여 리스트를 반환해준다.
	 * @param jobId
	 * @param showroom
	 * @return
	 * @throws IOException 
	 */
	@Override
	public Map<String, Object> avatarShowroomSearch(String jobId, String showroom) throws IOException {
		
		List<Avatar> list = new ArrayList<Avatar>();
		int rowPriceSum = 0;
		
		//쇼룸 복붙 파싱 로직
		boolean vaildate = false;
		for(String token : showroom.trim().split("\\n")){
			token = token.trim();
			
			if(token.length() == 0)
				continue;
			
			if(vaildate && !showRoomParts.contains(token)) {
				Avatar avatar = new Avatar();
				avatar.setItemName(token);
				list.add(avatar);
			}else if(showRoomParts.contains(token)) 
				vaildate = true;
		}
		
//		int sb = 0;
//		for(Avatar avatar : list) {
//			List<ItemDetail> detailList = dnfapi.searchItems(avatar.getItemName(), true);
//			for(ItemDetail detail : detailList) {
//				System.out.println(detail.getItemId() + "\t" + detail.getItemName() + "\t" + .jobId + "\t" + jobId + "\t" + detail.getItemTypeDetail());
//				sb++;
//			}
//		}
//		Map<String, Object> mapList = new HashMap<String, Object>();
//		mapList.put("test", sb);
		
		List<Auctions> auctions = searchAuctionAvatarNameList(list, jobId);
		List<Avatar> avatarList = new ArrayList<Avatar>();
		
		//경매장에서 가져온 엠블렘을 매핑시켜서 돌려준다.
		for(Auctions auction : auctions) {
			getMatchEmblem(auction);
			
			if(auction.getRows().size() != 0) {
				avatarList.add(convertAuctionToAvatar(auction.getRows().get(0)));
				
				//각 파츠별 최저가의 합
				rowPriceSum += auction.getRows().get(0).getCurrentPrice();
			}
			
		}
		
		//경매장에서 조회된 아바타 파츠 마다 가져와 9파츠로 고정
		List<Avatar> choiceAvatar = fixNinePieceAvatar(avatarList, "null", parts);
		
		Map<String, Object> mapList = new HashMap<String, Object>();
		mapList.put("auctions", auctions);
		mapList.put("choiceAvatar", choiceAvatar);
		mapList.put("rowPriceSum", rowPriceSum);
		
		return mapList;
	}
	
	/**
	 * @description 아이템 이름을 리스트로 보내 경매장에서 응답요청을 리스트로 반환
	 * @param wearAvatar
	 * @param kind
	 * @return
	 * @throws IOException
	 */
	public List<Auctions> searchAuctionAvatarNameList(List<Avatar> Avatar, String jobId) throws IOException {
		List<Auctions> avatarList = new ArrayList<Auctions>();
		
		for(Avatar avatar : Avatar) {
			Auctions auctions = dnfapi.auctionItemName(avatar.getItemName());
			List<Auction> temp = new ArrayList<Auction>();
			
			//해당 직군과 일치도록 필터링
			for(Auction auction : auctions.getRows()) {
				if(auction.getJobId().equals(jobId)) {
					temp.add(auction);
				}
			}
			auctions.setRows(temp);
			
			avatarList.add(auctions);
		}
		
		return avatarList;
	}
	
	/**
	 * @description 경매장에서 추출한 데이터를 avatar vo로 변환
	 * @param auctions
	 * @return
	 */
	public Avatar convertAuctionToAvatar(Auction auction){
		Avatar avatar = new Avatar();
		
		//파츠 정보
		avatar.setSlotName(auction.getItemTypeDetail());
		avatar.setItemId(auction.getItemId());
		avatar.setItemName(auction.getItemName());
		
		avatar.setEmblems(new ArrayList<ItemDetail>());
		
		return avatar;
	}
	
	/**
	 * @description DB에서 레압 리스트를 가져온다.
	 * @return json String
	 * @throws IOException 
	 */
	@Override
	public String selectRareAvatarList() throws IOException {
		// cascading select를 위해 DB에서 가져온 레압리스트를 json으로 변경
		List<Map<String, Object>> job = selectRareAvatarMap();
		
		return mapper.writeValueAsString(job);
	}

	/**
	 * @description jobId와 레압셋 코드로 경매장에서 검색 후 리스트 가져와 뿌려줌
	 * @param jobId
	 * @param avatarSet
	 * @return
	 * @throws IOException 
	 */
	@Override
	public Map<String, Object> avatarCharacterSetSearch(String jobId, String categoryCode) throws IOException {
		AvatarMastar am = new AvatarMastar();
		am.setJobId(jobId);
		am.setCategoryCode(categoryCode);
		
		List<Avatar> list = dao.selectAvatarSet(am);
		if(list.size() == 0)
			throw new IOException("해당 아바타는 존재하지 않습니다.");
		
		int rowPriceSum = 0;
		int searchCount = 0;
		
		List<Auctions> auctions = searchAuctionAvatarNameList(list, jobId);
		List<Avatar> avatarList = new ArrayList<Avatar>();
		
		//경매장에서 가져온 엠블렘을 매핑시켜서 돌려준다.
		for(Auctions auction : auctions) {
			getMatchEmblem(auction);
			
			if(auction.getRows().size() != 0) {
				avatarList.add(convertAuctionToAvatar(auction.getRows().get(0)));
				
				//각 파츠별 최저가의 합
				rowPriceSum += auction.getRows().get(0).getCurrentPrice();
				searchCount += 1;
			}
			
		}
		
		//경매장에서 조회된 아바타 파츠 마다 가져와 9파츠로 고정
		List<Avatar> choiceAvatar = fixNinePieceAvatar(list, "null", parts);
		
		Map<String, Object> mapList = new HashMap<String, Object>();
		mapList.put("auctions", auctions);
		mapList.put("choiceAvatar", choiceAvatar);
		mapList.put("rowPriceSum", rowPriceSum);
		mapList.put("searchCount", searchCount);
		
		return mapList;
	}

	/**
	 * @description 필터링으로 걸러서 판매글 리스트를 가져와준다.
	 * @param jobId
	 * @param jobGrowId
	 * @param categoryCode
	 * @param price
	 * @return
	 * @throws IOException 
	 * @throws JsonMappingException 
	 * @throws JsonGenerationException 
	 */
	@Override
	public Map<String, Object> selectAuctionList(String jobId, String jobGrowId, String categoryCode, int price) throws IOException {

		//직군과 각 직군의 2차각성명을 JOIN하여 가져온다.
		List<JobGrow> jobGrow = dao.selectJobGrowList();
		
		Map<String, List<JobGrow>> jobGrowMapList = new HashMap<String, List<JobGrow>>();
		for(JobGrow jg : jobGrow) {
			JobGrow growTemp = new JobGrow();
			growTemp.setJobGrowId(jg.getJobGrowId());
			growTemp.setJobGrowName(jg.getJobGrowName());
			
			//jobGrowMapList에 해당 jobId키가 존재한다면 jobGrow를 넣어준다.
			if(jobGrowMapList.containsKey(jg.getJobId())){
				jobGrowMapList.get(jg.getJobId()).add(growTemp);
			}else {
				//해당 jobId가 없다면 리스트를 만들어준다.
				List<JobGrow> jobList = new ArrayList<JobGrow>();
				jobList.add(growTemp);
				
				jobGrowMapList.put(jg.getJobId(), jobList);
			}
		}
		
		//직군별 레어아바타 차수 리스트
		List<Map<String, Object>> jobList = selectRareAvatarMap();
		
		//직군과 2차각성명을 차수리스트의 Map에 통합시킨다.
		for(Map<String, Object> map : jobList) {
			if(jobGrowMapList.containsKey(map.get("jobId"))) {
				map.put("jobGrowList", jobGrowMapList.get(map.get("jobId")));
			}
		}
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("jobGrowAvatarList", mapper.writeValueAsString(jobList));
		
		return map;
	}
	
	/**
	 * @description DB에서 각 직군별 레어 아바타 리스트를 가져와 출력한다.
	 * @return
	 */
	public List<Map<String, Object>> selectRareAvatarMap() {
		List<AvatarMastar> avatarList = dao.selectRareAvatarList();
		List<String> existCheckList = new ArrayList<String>();
		
		// cascading select를 위해 DB에서 가져온 레압리스트를 json으로 변경
		List<Map<String, Object>> job = new ArrayList<Map<String, Object>>();
		
		for(AvatarMastar mst : avatarList) {
			Map<String, Object> nameAndValue = new HashMap<String, Object>();
			nameAndValue.put("categoryName", mst.getCategoryName());
			nameAndValue.put("categoryCode", mst.getCategoryCode());
			
			//DB에서 가져온 직군이 리스트에 없으면 해당 직군명으로 map을 생성, 있으면  해당 직군의 레압을 리스트로 넣어줌. 걍 출력 값 보면 알거야..
			if(existCheckList.contains(mst.getJobName())) {
				int index = existCheckList.indexOf(mst.getJobName());
				List<Object> listObj = (List<Object>) job.get(index).get("avatarList");
				listObj.add(nameAndValue);
			}else {
				existCheckList.add(mst.getJobName());
				
				List<Object> setList = new ArrayList<Object>();
				setList.add(nameAndValue);
				
				Map<String, Object> obj = new HashMap<String, Object>();
				obj.put("avatarList", setList);
				obj.put("jobId", mst.getJobId());
				obj.put("jobName", mst.getJobName());
				
				job.add(obj);
			}
		}
		
		return job;
	}
}
