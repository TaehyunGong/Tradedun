package com.thkong.tradedun;


import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;

public class main {

	public void saveImage() {
		File outputFile = new File("C:\\Users\\John\\Desktop\\테스트\\images");
		 
		URL url = null;
		BufferedImage bi = null;
		        
		try {
		    url = new URL("https://img-api.neople.co.kr/df/servers/bakal/characters/669544118444dcfa72786e392e09304e");
		    bi = ImageIO.read(url);
		    ImageIO.write(bi, "png", outputFile);
		}
		catch(Exception ex) {
			
		}
		System.out.println("완료");
	}
	
	public static void main(String[] args) throws IOException {
		
//		ApplicationContext context = new GenericXmlApplicationContext("/SpringConfig/config/root-context.xml");
//		ApplicationContext sessionContext = new GenericXmlApplicationContext("/SpringConfig/config/database-context.xml");
//		DnfApiLib dnfapi = context.getBean("dnfapi", DnfApiLib.class);
//		httpConnection conn = context.getBean("conn", httpConnection.class);
//		SqlSessionTemplate session = sessionContext.getBean("sqlSession", SqlSessionTemplate.class);
		
		new main().saveImage();
	}
}