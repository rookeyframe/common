package com.common.email.unit;

import org.junit.Test;

import com.common.email.MultiMailsender;
import com.common.email.MultiMailsender.MultiMailSenderInfo;

public class MultiMailsenderUnit {
	
	@Test
	public void multiMailsenderTest(){
		// 创建邮件信息
		MultiMailSenderInfo mailInfo = new MultiMailSenderInfo();
		mailInfo.setMailServerHost("smtp.sina.com.cn");
		mailInfo.setMailServerPort("25");
		mailInfo.setValidate(true);
		mailInfo.setUserName("***");
		mailInfo.setPassword("***");
		mailInfo.setFromAddress("***@sina.com");
		mailInfo.setToAddress("***@163.com");
		mailInfo.setSubject("MyMail测试");
		mailInfo.setContent("我的邮件测试\n\rMy test mail\n\r");

		String[] receivers = new String[]{"***@163.com", "***@tom.com"};
		String[] ccs = receivers;
		mailInfo.setReceivers(receivers);
		mailInfo.setCcs(ccs);
		
		MultiMailsender.sendMailtoMultiReceiver(mailInfo);
		MultiMailsender.sendMailtoMultiCC(mailInfo);
	}

}
