package com.thkong.tradedun.Contact.dao;

import java.util.List;

import com.thkong.tradedun.Auction.vo.CodeTB;
import com.thkong.tradedun.Common.vo.Attach;
import com.thkong.tradedun.Contact.vo.Contact;

public interface contactDao {

	List<CodeTB> selectContactCodeList();

	int selectContactNo();

	int insertContact(Contact contact);

	int selectAttachNo();

	int insertAttach(Attach attach);

}
