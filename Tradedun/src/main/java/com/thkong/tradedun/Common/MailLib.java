package com.thkong.tradedun.Common;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Transport;

public class MailLib {

	public void send(Message msg) throws MessagingException{
		Transport.send(msg);
	}
}
