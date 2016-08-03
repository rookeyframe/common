package com.common.email.unit;

import org.junit.Test;

import com.common.email.AttachmentMailSender;
import com.common.email.MailSenderInfo;

public class AttachmentMailSenderUnit {
	@Test
	public void attachmentMailSenderTest(){
		// 创建邮件信息
		MailSenderInfo mailInfo = new MailSenderInfo();
		mailInfo.setMailServerHost("smtp.sina.com.cn");
		mailInfo.setMailServerPort("25");
		mailInfo.setValidate(true);
		mailInfo.setUserName("***");
		mailInfo.setPassword("***");
		mailInfo.setFromAddress("***@sina.com");
		mailInfo.setToAddress("***@163.com");
		mailInfo.setSubject("MyMail测试");
		mailInfo.setContent("我的邮件测试\n\rMy test mail\n\r");

		String[] fileNames = new String[3];
		fileNames[0] = "C:/temp/new.txt";
		fileNames[1] = "C:/temp/test.wav";
		fileNames[2] = "C:/temp/mary_photo.jpg";
		mailInfo.setAttachFileNames(fileNames);
		
		AttachmentMailSender.sendMail(mailInfo);
	}
}
