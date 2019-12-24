package com.thkong.tradedun.Common;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartException;

public class FileLib {

	private String filePath = "";

	public FileLib(String path) {
		System.out.println("File Path : " + path);
		this.filePath = path;
	}
	
	/**
	 * @param path
	 * @param file
	 * @param input 
	 * @return 파일 명 + 확장자
	 * @throws IOException 
	 * @throws FileNotFoundException 
	 */
	public String uploadFile(String path, File file, InputStream input) throws Exception, MultipartException {
		
		//파일 원본 이름
		String fileName = fileNameGenerater(file.getName());
		
		byte[] bytes = IOUtils.toByteArray(input);
		
		byte fileData[] = bytes;
		FileOutputStream fos = new FileOutputStream(filePath + path + fileName);
        fos.write(fileData);
		
        System.out.println("업로드 완료");
        
		return fileName;
	}
	
	/**
	 * @param fileBytes
	 * @param originFileName
	 * @return 파일 명 + 확장자
	 * @throws IOException
	 * @description MultiPartFile의 getBytes()을 사용후 업로드
	 */
	public String uploadFile(byte[] fileBytes, String originFileName) throws IOException {
		//파일 원본 이름
		String fileName = fileNameGenerater(originFileName);
		
		FileOutputStream fos = new FileOutputStream(filePath + fileName);
        fos.write(fileBytes);
		
        System.out.println("업로드 완료");
        
		return fileName;
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
		fileName = fileName + "." + fileExtenstion(originFileName); //확장자
		
		return fileName;
	}
	
	/**
	 * @description 파일의 확장자를 가져온다.
	 * @param originFileName
	 * @return
	 */
	public String fileExtenstion(String originFileName) {
		return originFileName.substring( originFileName.lastIndexOf(".") + 1 );
	}
	
	/**
	 * @return filePath
	 * @description 업로드 경로를 반환받는다. 
	 */
	public String getFilePath() {
		return filePath;
	}
	
	/**
	 * @param res
	 * @param uploadName
	 * @param originName
	 * @return Response
	 * @throws IOException
	 * @description 파일 다운로드
	 */
	public HttpServletResponse downloadFile(HttpServletResponse res, String uploadName, String originName) throws IOException {

		//가져올 파일 경로
		String realPath = getFilePath() + uploadName;
		
		//원본 파일명을 인코딩 해준다.
    	originName = URLEncoder.encode(originName, "UTF-8");
        
        //다운로드를 위해 헤더와 타입을 지정해준다.
        res.setContentType("application/octer-stream");
        res.setHeader("Content-Transfer-Encoding", "binary;");
        res.setHeader("Content-Disposition", "attachment; filename=\"" + originName + "\"");
        
        OutputStream os = null;
    	FileInputStream fis = null;
    	
        try {
        	os = res.getOutputStream();
        	fis = new FileInputStream(realPath);
        	
        	int ncount = 0;
        	byte[] bytes = new byte[512];
        	
        	while ((ncount = fis.read(bytes)) != -1 ) {
        		os.write(bytes, 0, ncount);
        	}
        }catch(Exception ex) {
        	throw ex;
        }finally {
        	
        	//혹여나 FileInputStream 에러 발생시 예외처리 후 os.close
        	try {
        		fis.close();
        		os.close();
        	}catch(Exception ex) {
        		throw ex;
        	}finally {
        		os.close();
        	}
        }
            
		return res;
	}
}
