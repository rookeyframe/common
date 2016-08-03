package com.common.email.unit;

import org.junit.Test;

import com.common.email.MailReceiver;
import com.common.email.MailReceiverInfo;

public class MailReceiverUnit {
	@Test
	public void mailReceiverUnitTest() throws Exception{
		
		MailReceiverInfo receiverInfo = new MailReceiverInfo();
		receiverInfo.setMailServerHost("pop.sina.cn");
		receiverInfo.setMailServerPort("110");
		receiverInfo.setValidate(true);
		receiverInfo.setUserName("lentr");
		receiverInfo.setPassword("*********");
		receiverInfo.setAttachmentDir("D:/temp/mail/");
		receiverInfo.setEmailDir("D:/temp/mail/");

		MailReceiver receiver = new MailReceiver(receiverInfo);
		receiver.receiveAllMail();
	}

}
