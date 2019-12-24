package com.thkong.tradedun.Common;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class MailLib {

	Message msg;   
	Multipart multipart;
	
	/**
	 * @description 생성자로 smtp를 사용하기 위한 계정을 받는다.
	 * @param user
	 * @param password
	 */
	public MailLib(String user, String password) {
		this.multipart = new MimeMultipart();
		
		Properties props = new Properties();
		props.setProperty("mail.smtp.user", user);
		props.setProperty("mail.smtp.password", password);
		
		Session session = Session.getInstance(props, new Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(props.getProperty("mail.smtp.user"), props.getProperty("mail.smtp.password"));
			}
		});

		this.msg = new MimeMessage(session);
	}

	
	public void send(Message msg) throws MessagingException{
		Transport.send(msg);
	}
}
