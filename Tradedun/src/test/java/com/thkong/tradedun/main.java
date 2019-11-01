package com.thkong.tradedun;


import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class main {

	public void saveImage(String image) {
		File outputFile = new File("C:\\Users\\John\\Desktop\\테스트\\"+image+".png");
		
		//업로드 이름 랜덤으로 생성
		String random = String.valueOf((Math.random()*1000000) + System.currentTimeMillis());
		String fileName = random + new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
		
		//확장자를 업로드 이름에 삽입
		String ext = image.substring( image.lastIndexOf(".") + 1 );
		fileName = fileName + "." + ext; //확장자
		
		System.out.println("파일 원본 이름 : " + image);
		System.out.println("업로드 파일 이름 : " + fileName);
		
	}
	
	public static void main(String[] args) throws IOException {
		
//		ApplicationContext context = new GenericXmlApplicationContext("/SpringConfig/config/root-context.xml");
//		ApplicationContext sessionContext = new GenericXmlApplicationContext("/SpringConfig/config/database-context.xml");
//		DnfApiLib dnfapi = context.getBean("dnfapi", DnfApiLib.class);
//		httpConnection conn = context.getBean("conn", httpConnection.class);
//		SqlSessionTemplate session = sessionContext.getBean("sqlSession", SqlSessionTemplate.class);
		
		new main().saveImage("43555446f17e37fcf3eb546ee312d0f8");
	}
}