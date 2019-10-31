package com.thkong.tradedun;

import java.io.IOException;
import java.util.List;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

import com.thkong.tradedun.Auction.vo.AuctionSalesCharacterList;

public class main {

	public static void main(String[] args) throws IOException {
		
		ApplicationContext context = new GenericXmlApplicationContext("/SpringConfig/config/root-context.xml");
//		ApplicationContext sessionContext = new GenericXmlApplicationContext("/SpringConfig/config/database-context.xml");
//		DnfApiLib dnfapi = context.getBean("dnfapi", DnfApiLib.class);
//		httpConnection conn = context.getBean("conn", httpConnection.class);
//		SqlSessionTemplate session = sessionContext.getBean("sqlSession", SqlSessionTemplate.class);
		ObjectMapper mapper = context.getBean("mapper", ObjectMapper.class);
		String submitJson = "[{\"server\":\"bakal\",\"charId\":\"7c2125f5b657fa7da5c8211253a3b70b\",\"charName\":\"군원\",\"avatar\":[{\"slotName\":\"모자\",\"emblemName\":\"찬란한 붉은빛 엠블렘[정신력],찬란한 붉은빛 엠블렘[정신력],\",\"optionAbility\":\"정신력 55 증가\",\"itemId\":\"beddbe6f0c0a7800206c8ba3bdfc9d01\",\"itemName\":\"순백의 미카엘 은빛 깃털 장식\"},{\"slotName\":\"머리\",\"emblemName\":\"찬란한 붉은빛 엠블렘[정신력],찬란한 붉은빛 엠블렘[정신력],\",\"optionAbility\":\"정신력 55 증가\",\"itemId\":\"9525b2b1e98a8ef75e87721ff3223f91\",\"itemName\":\"순백의 미카엘 골드 올백 헤어\"},{\"slotName\":\"얼굴\",\"emblemName\":\"화려한 노란빛 엠블렘[캐스트속도],화려한 노란빛 엠블렘[캐스트속도],\",\"optionAbility\":\"공격 속도 6.0% 증가\",\"itemId\":\"e13ef25cbc7e702f71f718970b1d1af8\",\"itemName\":\"순백의 미카엘 은빛 깃털 이어링\"},{\"slotName\":\"상의\",\"emblemName\":\"모험가의 플래티넘 엠블렘,찬란한 그린 엠블렘[정신력],찬란한 그린 엠블렘[정신력],\",\"optionAbility\":\"아포칼립스 스킬Lv +1\",\"itemId\":\"1462d6811c7d8735b2ccb43ea79c8683\",\"itemName\":\"순백의 미카엘 샤이닝 엔젤릭 코트\"},{\"slotName\":\"하의\",\"emblemName\":\"모험가의 플래티넘 엠블렘,찬란한 그린 엠블렘[정신력],찬란한 그린 엠블렘[정신력],\",\"optionAbility\":\"HP MAX 400 증가\",\"itemId\":\"7c74d63024a57efb0a349708a72d31f7\",\"itemName\":\"순백의 미카엘 샤이닝 엔젤릭 팬츠\"},{\"slotName\":\"신발\",\"emblemName\":\"화려한 푸른빛 엠블렘[이동속도],화려한 푸른빛 엠블렘[이동속도],\",\"optionAbility\":\"이동 속도 6.0% 증가\",\"itemId\":\"33889dff8ab8fae0739d41b7c9b1bc6c\",\"itemName\":\"순백의 미카엘 샤이닝 엔젤릭 슈즈\"},{\"slotName\":\"목가슴\",\"emblemName\":\"찬란한 노란빛 엠블렘[캐스트속도],찬란한 노란빛 엠블렘[캐스트속도],\",\"optionAbility\":\"공격 속도 6.0% 증가\",\"itemId\":\"dfdc67d0c645150bc8f9d5bfbd057093\",\"itemName\":\"순백의 미카엘 은빛 날개\"},{\"slotName\":\"허리\",\"emblemName\":\"화려한 푸른빛 엠블렘[이동속도],화려한 푸른빛 엠블렘[이동속도],\",\"optionAbility\":\"최대 무게 14.000kg 증가\",\"itemId\":\"2d9628dcfed644877f70f31ab4cf681d\",\"itemName\":\"순백의 미카엘 금빛 벨트\"},{\"slotName\":\"스킨\",\"emblemName\":\"찬란한 붉은빛 엠블렘[정신력],찬란한 붉은빛 엠블렘[정신력],\",\"optionAbility\":\"물리피해 추가감소 3.5% 증가\",\"itemId\":\"b0c784306b2639b1510d4b941c51e581\",\"itemName\":\"아라드를 걷는 선비 프리스트 피부[A타입]\"}],\"resultPrice\":\"3425\"}]";
		AuctionSalesCharacterList[] salesList = mapper.readValue(submitJson, AuctionSalesCharacterList[].class);
		System.out.println(salesList[0]);
		System.out.println(salesList[0].getAvatar());
	}
}