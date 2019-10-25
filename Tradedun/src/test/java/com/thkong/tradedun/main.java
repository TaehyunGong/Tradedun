package com.thkong.tradedun;

import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.collections.set.SynchronizedSet;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

import com.thkong.tradedun.Auction.vo.Auction;
import com.thkong.tradedun.Auction.vo.AuctionCharacterDetail;
import com.thkong.tradedun.Auction.vo.Auctions;
import com.thkong.tradedun.Auction.vo.Avatar;
import com.thkong.tradedun.Common.DnfApiLib;

public class main {

	public static void main(String[] args) throws IOException {
		
		ApplicationContext context = new GenericXmlApplicationContext("/SpringConfig/config/root-context.xml");
		DnfApiLib dnfapi = context.getBean("dnfapi", DnfApiLib.class);
		String kind = "wear";
		
		AuctionCharacterDetail detail = dnfapi.charactersAvatar("bakal", "86d4ad8857d077fd4f7fb73aaf6d672c");
		List<Auctions> avatarList = new ArrayList<Auctions>();
		
		//임시용
		List<String> parts = Arrays.asList(
				new String[]{"HEADGEAR", "HAIR", "FACE", "JACKET", "PANTS", "SHOES", "BREAST", "WAIST", "SKIN"});
		
		//만약 노압일경우 없는상태라면 경고창으로 반환
		if(detail.getAvatar().size() == 0) {
			System.err.println("노압임");
			return;
		}
		
		List<Avatar> list = new ArrayList<Avatar>();
		for(String part : parts) {
			Avatar avat = new Avatar(); avat.setSlotId(part);
			
			for(Avatar avatar : detail.getAvatar()) {
				if(part.contains(avatar.getSlotId())) {
					avat = avatar;
					break;
				}
			}
			list.add(avat);
		}
		
		//모자 부터 피부까지 총 8부위만 조회한다.
		//모자 부터 피부까지 총 8부위만 조회한다.
		for(Avatar avatar : list) {
			String itemId;
			if(kind.equals("wear") || avatar.getSlotId().equals("SKIN")) {
				itemId = avatar.getItemId();
			}
			else {
				itemId = avatar.getClone().getItemId();
				avatar.setItemId(avatar.getClone().getItemId());
				avatar.setItemName(avatar.getClone().getItemName());
			}
			avatarList.add(dnfapi.auction(itemId));
		}
		
		//피부의 경우 착용하고있는 아바타로만 뽑아준다.
//		avatarList.add(dnfapi.auction(detail.getAvatar().get(8).getItemId()));
		for(int num=0; num < avatarList.size(); num++) {
			System.out.println(avatarList.get(num).getRows());
		}
	}
}
