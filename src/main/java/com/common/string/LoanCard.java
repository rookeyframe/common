package com.common.string;

/**
 * 
 * 功能描述：验证是否为贷款卡码
 * @author linkepeng
 * @date :2016年6月12日
 * @version :1.0.0.0
 */
public class LoanCard {
	public LoanCard(){}
	//加权因子
	private static final int[] weight=new int[]{1, 3, 5, 7, 11, 2, 13, 1, 1, 17, 19, 97, 23, 29};
	
	/**
	 * 验证是否为贷款卡码
	 * @param check 需要验证的贷款号码
	 * @return
	 */
	public boolean Verify(String loanCard){
		// 判断是否为十六位，并且是否传进来的为空
		if (loanCard == null || loanCard.length() != 16) {
			return false;
		}
		// 判断头三位是否为字母或者大写字母
		if (!loanCard.substring(0, 3).matches("^[0-9A-Z]{3}")) {
			return false;
		}
		// 判断是否为最后十三位是否为纯数字
		if (!loanCard.substring(3, 16).matches("^\\d{13}$")) {
			return false;
		}
		//截取后两位验证码
		String checkDigit=loanCard.substring(14, 16);
		//获取前十四位字符
		char[] chars=loanCard.substring(0,14).toCharArray();
		//计算权重总数值
		int sum=0;
		for(int i=0;i<weight.length;i++){
			char t=chars[i];
			if(String.valueOf(t).matches("^[A-Z]$")){
				//如果前三位有字母的话，转换为对应的数据
				int x=(int)t%65+10;
				sum+=x*weight[i];
			}else{
				sum+=Integer.parseInt(String.valueOf(t))*weight[i];
			}
		}
		int result=sum%97+1;
		return (result<=9)?checkDigit.equals("0"+result):checkDigit.equals(""+result);
	}
}
