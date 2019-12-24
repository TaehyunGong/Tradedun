package com.thkong.tradedun.Contact.service;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import com.thkong.tradedun.Auction.vo.CodeTB;
import com.thkong.tradedun.Contact.vo.Contact;

public interface contactService {

	List<CodeTB> selectContactCodeList();

	boolean sendContact(Contact contact, File file, InputStream inputStream) throws Exception;

}
