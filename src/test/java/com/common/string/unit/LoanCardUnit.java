package com.common.string.unit;

import org.junit.Assert;
import org.junit.Test;

import com.common.string.LoanCard;

public class LoanCardUnit {
	
	@Test
	public void loanCardTest(){
		LoanCard loanCard=new LoanCard();
		/**
		 * 3301220000001069
		 * 3301220000005644
		 * 3301220000001101
		 * 3301220000005644
		 * 3301220000001101
		 * 3301220000001649
		 * 3301220000005644
		 */
		String loanCardStr = "3301220000001069";
		System.out.println("贷款卡号" + loanCardStr + "验证合格？ " + loanCard.Verify(loanCardStr));
		loanCardStr = "3301220000001649";
		System.out.println("贷款卡号" + loanCardStr + "验证合格？ " + loanCard.Verify(loanCardStr));
		loanCardStr = "3301220000005645";
		System.out.println("贷款卡号" + loanCardStr + "验证合格？ " + loanCard.Verify(loanCardStr));
		
	}

}
