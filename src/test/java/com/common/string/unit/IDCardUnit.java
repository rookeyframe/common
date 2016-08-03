package com.common.string.unit;

import org.junit.Test;

import com.common.string.IDCard;

public class IDCardUnit {
	
	@Test
	public void idCardTest(){
		IDCard test = new IDCard();
		String idCardStr = "350425199208152013";
		System.out.println("身份证" + idCardStr + "验证合格？ " 
				+ test.Verify(idCardStr));
		idCardStr = "440524188001010014";
		System.out.println("身份证" + idCardStr + "验证合格？ "  
				+ test.Verify(idCardStr));
	}

}
