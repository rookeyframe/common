package com.common.security;

import java.security.Key;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.Signature;
import java.security.interfaces.DSAPrivateKey;
import java.security.interfaces.DSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.HashMap;
import java.util.Map;

public abstract class DSACoder {
	
	/**
	 * 数字签名
	 * 密匙算法
	 */
	public static final String KEY_ALGORITHM = "DSA";
	/**
	 * 数字签名
	 * 签名/算法验算
	 */
	public static final String SIGNATURE_ALGORITHM="SHA1withDSA";
	//公钥
	private static final String PUBLIC_KEY = "DSAPublicKey";
	//私钥
	private static final String PRIVATE_KEY = "DSAPrivateKey";
	
	/**
	 * DSA 密钥长度 默认威1024位
	 * 密钥长度必须是64的倍数
	 * 范围在512~65536
	 */
	private static final int KEY_SIZE=1024;
	
	/**
	 * 签名
	 * @param data 待签名的数据
	 * @param privateKey 私钥
	 * @return byte[] 数字签名
	 * @throws Exception
	 */
	public static byte[] sign(byte[] data,byte[] privateKey) throws Exception{
		//转换私钥材料
		PKCS8EncodedKeySpec pkcs8KeySpec=new PKCS8EncodedKeySpec(privateKey);
		//实例化密钥工厂
		KeyFactory keyFactory=KeyFactory.getInstance(KEY_ALGORITHM);
		//取私钥匙对象
		PrivateKey priKey=keyFactory.generatePrivate(pkcs8KeySpec);
		//实例化Signature
		Signature signature=Signature.getInstance(SIGNATURE_ALGORITHM);
		//init signature
		signature.initSign(priKey);
		//更新
		signature.update(data);
		return signature.sign();
	}
	
	/**
	 * 校验
	 * @param data 待校验数据
	 * @param publicKey 公钥
	 * @param sign 数字签名
	 * @return boolean 校验成功返回true 失败返回false
	 * @throws Exception
	 */
	public static boolean verify(byte[] data,byte[] publicKey,byte[] sign) throws Exception{
		//转换公钥材料
		X509EncodedKeySpec keySpec=new X509EncodedKeySpec(publicKey);
		//实例化密钥工厂
		KeyFactory keyFactory=KeyFactory.getInstance(KEY_ALGORITHM);
		
		//生成公钥
		PublicKey pubKey=keyFactory.generatePublic(keySpec);
		//实例化Signature
		Signature signature=Signature.getInstance(SIGNATURE_ALGORITHM);
		//init signature
		signature.initVerify(pubKey);
		//更新
		signature.update(data);
		//验证
		return signature.verify(sign);
	}
	
	/**
	 * 取得私钥
	 * @param keyMap 密钥Map
	 * @return byte[] 私钥
	 * @throws Exception
	 */
	public static byte[] getPrivateKey(Map<String, Object> keyMap)throws Exception {
		Key key = (Key) keyMap.get(PRIVATE_KEY);
		return key.getEncoded();
	}
	
	/**
	 * 取得公钥
	 * @param keyMap 密钥Map
	 * @return byte[] 公钥
	 * @throws Exception
	 */
	public static byte[] getPublicKey(Map<String, Object> keyMap)
			throws Exception {
		Key key = (Key) keyMap.get(PUBLIC_KEY);
		return key.getEncoded();
	}

	/**
	 * 初始化密钥
	 * @return Map 密钥Map
	 * @throws Exception
	 */
	public static Map<String, Object> initKey() throws Exception {
		//实例化密钥生成器
		KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance(KEY_ALGORITHM);
		//初始化密钥对生成
		keyPairGen.initialize(KEY_SIZE,new SecureRandom());
		//生成密钥对
		KeyPair keyPair = keyPairGen.generateKeyPair();
		//公钥
		DSAPublicKey publicKey = (DSAPublicKey) keyPair.getPublic();
		//私钥
		DSAPrivateKey privateKey = (DSAPrivateKey) keyPair.getPrivate();
		//封装密钥
		Map<String, Object> keyMap = new HashMap<String, Object>(2);
		keyMap.put(PUBLIC_KEY, publicKey);
		keyMap.put(PRIVATE_KEY, privateKey);
		return keyMap;
	}

}
