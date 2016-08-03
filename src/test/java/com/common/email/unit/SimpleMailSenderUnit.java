package com.common.email.unit;

import org.junit.Test;

import com.common.email.MailSenderInfo;
import com.common.email.SimpleMailSender;

public class SimpleMailSenderUnit {
	
	@Test
	public void simpleMailSenderTest(){
		// 创建邮件信息
		MailSenderInfo mailInfo = new MailSenderInfo();
		mailInfo.setMailServerHost("smtp.sina.com.cn");
		mailInfo.setMailServerPort("25");
		mailInfo.setValidate(true);
		mailInfo.setUserName("lentr@sina.cn");
		mailInfo.setPassword("*******");
		mailInfo.setFromAddress("lentr@sina.cn");
		mailInfo.setToAddress("1364529864@qq.com");
		mailInfo.setSubject("MyMail测试");
		mailInfo.setContent("我的邮件测试\n\rMy test mail\n\r");

		// 以文本格式发送邮件
		SimpleMailSender.sendTextMail(mailInfo);
		// 以HTML格式发送邮件
		SimpleMailSender.sendHtmlMail(mailInfo);
	}

}
