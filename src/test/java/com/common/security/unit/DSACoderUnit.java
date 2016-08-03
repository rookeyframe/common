package com.common.security.unit;

import static org.junit.Assert.assertTrue;

import java.util.Map;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.binary.Hex;
import org.junit.Before;
import org.junit.Test;

import com.common.security.DSACoder;

public class DSACoderUnit {
	//公钥
		private byte[] publicKey;
		//私钥
		private byte[] privateKey;
		
		/**
		 * 初始化密钥
		 * @throws Exception
		 */
		@Before
		public void initKey() throws Exception{
			Map<String,Object> keyMap=DSACoder.initKey();
			publicKey=DSACoder.getPublicKey(keyMap);
			privateKey=DSACoder.getPrivateKey(keyMap);
			System.err.println("公钥:\n"+Base64.encodeBase64String(publicKey));
			System.err.println("私钥：\n"+Base64.encodeBase64String(privateKey));
		}
		
		@Test
		public void testSign() throws Exception{
			String inputStr="DSA数字签名";
			byte[] data=inputStr.getBytes();
			//产生签名 从服务器端加密以后，发送给客户端
			byte[] sign=DSACoder.sign(data, privateKey);
			System.err.println("签名：\n"+Hex.encodeHexString(sign));
			//验证签名
			boolean status=DSACoder.verify(data, publicKey, sign);
			System.err.println("状态：\n"+status);
			assertTrue(status);
		}
}
