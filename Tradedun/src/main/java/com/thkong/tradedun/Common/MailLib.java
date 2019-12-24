package com.thkong.tradedun.Common;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Address;
import javax.mail.Authenticator;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.Message.RecipientType;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class MailLib {

	Message msg;   
	
	/**
	 * @description 생성자로 smtp를 사용하기 위한 계정 및 기타 정보를 받는다.
	 * @param user
	 * @param password
	 * @param host
	 * @param port
	 * @param starttls
	 */
	public MailLib(String user, String password, String host, String port, String starttls, String auth) {
		
		Properties props = new Properties();
		props.setProperty("mail.smtp.user", user);
		props.setProperty("mail.smtp.password", password);
		props.setProperty("mail.smtp.host", host);
		props.setProperty("mail.smtp.port", port);
		props.setProperty("mail.smtp.starttls.enable", starttls);
		props.setProperty("mail.smtp.auth", auth);
		
		Session session = Session.getInstance(props, new Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(props.getProperty("mail.smtp.user"), props.getProperty("mail.smtp.password"));
			}
		});

		this.msg = new MimeMessage(session);
	}

	/**
	 * @description 메일을 발송하기 위해 메세지 객체를 생성한다. (빌더)
	 * @return
	 */
	public MessageBuilder getInstance() {
		return new MessageBuilder(msg);
	}
	
	public class MessageBuilder{
		
		Message msg;   
		Multipart multipart;
		
		public MessageBuilder(Message msg) {
			this.msg = msg;
			this.multipart = new MimeMultipart();
		}

		/**
		 * @description 이메일 문자열을 Address 객체로 변환
		 * @param address
		 * @return
		 * @throws AddressException
		 */
		public Address convertAddress(String address) throws AddressException {
			return new InternetAddress(address);
		}
		
		/**
		 * @description 이메일 문자열과 수신자명을 Address 객체로 변환
		 * @param address
		 * @param recipentName
		 * @return
		 * @throws AddressException
		 * @throws UnsupportedEncodingException
		 */
		public Address convertAddress(String address, String recipentName) throws Exception {
			return new InternetAddress(address, recipentName);
		}
		
		/**
		 * @description 이메일 문자열 리스트를 Address 배열로 변환
		 * @param addressList
		 * @return
		 * @throws AddressException
		 */
		public Address[] convertAddress(List<String> addressList) throws AddressException {
			Address[] address = new Address[addressList.size()]; 
			
			for(int i=0; i<addressList.size(); i++){
				address[i] = new InternetAddress(addressList.get(i));
			}
			
			return address;
		}	
		
		/**
		 * @description 이메일을 제목을 설정한다.
		 * @param subject
		 * @return
		 * @throws MessagingException
		 */
		public MessageBuilder setSubject(String subject) throws MessagingException {
			this.msg.setSubject(subject);
			return this;
		}
		
		/**
		 * @description 이메일 본문을 작성한다. htmlYN이 true라면 html형식으로 false면 text로 적용
		 * @param content
		 * @return
		 * @throws MessagingException
		 */
		public MessageBuilder setContent(String content) throws MessagingException {
			BodyPart htmlPart = new MimeBodyPart();
			htmlPart.setContent(content, "text/html; charset=UTF-8");
			multipart.addBodyPart(htmlPart, 0);
		   
			return this;
		}
		
		/**
		 * @description 수신자의 이메일주소를 받는다.
		 * @param address
		 * @return
		 * @throws MessagingException
		 * @throws UnsupportedEncodingException
		 */
		public MessageBuilder setRecipient(String address) throws MessagingException, UnsupportedEncodingException {
			this.msg.setRecipient(RecipientType.TO, convertAddress(address));
			return this;
		}		
		
		/**
		 * @description 수신자의 이메일주소와 수신자 이름을 받는다.
		 * @param address
		 * @param recipentName
		 * @return
		 * @throws Exception
		 */
		public MessageBuilder setRecipient(String address, String recipentName) throws Exception {
			this.msg.setRecipient(RecipientType.TO, convertAddress(address, recipentName));
			return this;		
		
		}
		
		/**
		 * @description 수신자들의 이메일 주소 리스트를 받는다.
		 * @param address
		 * @return
		 * @throws MessagingException
		 * @throws UnsupportedEncodingException
		 */
		public MessageBuilder setRecipient(List<String> address) throws MessagingException, UnsupportedEncodingException {
			this.msg.setRecipients(RecipientType.TO, convertAddress(address));
			return this;
		}
		
		/**
		 * @description 발신자 이메일주소를 받는다.
		 * @param address
		 * @return
		 * @throws MessagingException
		 */
		public MessageBuilder setFrom(String address) throws MessagingException{
			this.msg.setFrom(convertAddress(address));
			return this;
		}
		   
		/**
		 * @description 발신자 이메일주소를 받는다.
		 * @param address
		 * @param fromName
		 * @return
		 * @throws Exception
		 */
		public MessageBuilder setFrom(String address, String fromName) throws Exception{
			this.msg.setFrom(convertAddress(address, fromName));
			return this;
		}
		   
		/**
		 * @description 이메일에 첨부파일을 추가한다. 중복가능
		 * @param file
		 * @return
		 * @throws MessagingException
		 * @throws IOException
		 */
		public MessageBuilder addAttach(File file) throws MessagingException, IOException{
			BodyPart attachPart = new MimeBodyPart();
			DataSource source = new FileDataSource(file);
			attachPart.setDataHandler(new DataHandler(source));
			attachPart.setFileName(file.getName());
	   
			multipart.addBodyPart(attachPart);

			return this;
		}
			

		/**
		 * @description 이메일에 List로 여러개를 한꺼번에 첨부파일로 추가한다.
		 * @param files
		 * @return
		 * @throws MessagingException
		 * @throws IOException
		 */
		public MessageBuilder addAttach(List<File> files) throws MessagingException, IOException{
				
			for(File file : files){
				BodyPart attachPart = new MimeBodyPart();
				DataSource source = new FileDataSource(file);
				attachPart.setDataHandler(new DataHandler(source));
				attachPart.setFileName(file.getName());

				multipart.addBodyPart(attachPart);
			}
			
			return this;
		}
		
		/**
		 * @description MessageBuilder의 모든 컨텐츠가 입력되고 최종 인스턴스를 반환.
		 * @return
		 * @throws MessagingException
		 */
		public Message build() throws MessagingException {
			this.msg.setContent(multipart);
			return this.msg;
		}
	}
	
	/**
	 * @description 최종적으로 생성된 메세지를 받아 전송한다.
	 * @param msg
	 * @throws MessagingException
	 */
	public void send(Message msg) throws MessagingException{
		Transport.send(msg);
	}
}
