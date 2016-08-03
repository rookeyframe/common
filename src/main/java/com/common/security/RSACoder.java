package com.common.security;

import java.security.Key;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.Cipher;

import org.apache.commons.codec.binary.Hex;



/**
 * 
 * 功能描述：RSA 签名算法组件
 * @author linkepeng
 * @date :2016年6月12日
 * @version :1.0.0.0
 */
public abstract class RSACoder {
	/**
	 * 数字签名
	 * 密匙算法
	 */
	public static final String KEY_ALGORITHM = "RSA";
	/**
	 * 数字签名
	 * 签名/算法验算
	 */
	public static final String SIGNATURE_ALGORITHM="MD5withRSA";
	//公钥
	private static final String PUBLIC_KEY = "RSAPublicKey";
	//私钥
	private static final String PRIVATE_KEY = "RSAPrivateKey";
	
	/**
	 * RSA 密钥长度 默认威1024位
	 * 密钥长度必须是64的倍数
	 * 范围在512~65536
	 */
	private static final int KEY_SIZE=1024;
	
	

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
		keyPairGen.initialize(KEY_SIZE);
		//生成密钥对
		KeyPair keyPair = keyPairGen.generateKeyPair();
		//公钥
		RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();
		//私钥
		RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();
		//封装密钥
		Map<String, Object> keyMap = new HashMap<String, Object>(2);
		keyMap.put(PUBLIC_KEY, publicKey);
		keyMap.put(PRIVATE_KEY, privateKey);
		return keyMap;
	}
	
	/**
	 * 私钥签名
	 * @param data 待签名数据
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
	 * 私钥签名
	 * @param data 待签名数据
	 * @param privateKey 私钥
	 * @return String 十六进制的字符串
	 * @throws Exception
	 */
	public static String sign(byte[] data,String privateKey) throws Exception{
		byte[] sign=sign(data,getKey(privateKey));
		return Hex.encodeHexString(sign);
	}
	
	
	
	/**
	 * 公钥校验
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
	 * 公钥校验
	 * @param data 待校验数据
	 * @param publicKey 公钥
	 * @param sign 数字签名
	 * @return boolean 校验成功返回true 失败返回false
	 * @throws Exception
	 */
	public static boolean verify(byte[] data,String publicKey,String sign) throws Exception{
		return verify(data, getKey(publicKey), getKey(sign));
	}
	
	//加密与解密
	/**
	 * 私钥解密
	 * @param data 待解密数据
	 * @param key 私钥
	 * @return byte[] 解密数据
	 * @throws Exception
	 */
	public static byte[] decryptByPrivateKey(byte[] data,byte[] privateKey) throws Exception{
		//取得私钥材料
		PKCS8EncodedKeySpec pkcs8KeySpec=new PKCS8EncodedKeySpec(privateKey);
		//实例化密钥工厂
		KeyFactory keyFactory=KeyFactory.getInstance(KEY_ALGORITHM);
		//取私钥匙对象
		PrivateKey priKey=keyFactory.generatePrivate(pkcs8KeySpec);
		//对数据解密
		Cipher cipher=Cipher.getInstance(keyFactory.getAlgorithm());
		cipher.init(Cipher.DECRYPT_MODE, priKey);
		return cipher.doFinal(data);
	}
	
	/**
	 * 公钥解密
	 * @param data 待解密数据
	 * @param key 公钥
	 * @return byte[] 解密数据
	 * @throws Exception
	 */
	public static byte[] decryptByPublicKey(byte[] data,byte[] publicKey) throws Exception{
		//取得公钥
		X509EncodedKeySpec keySpec=new X509EncodedKeySpec(publicKey);
		//实例化密钥工厂
		KeyFactory keyFactory=KeyFactory.getInstance(KEY_ALGORITHM);
		//生成公钥
		PublicKey pubKey=keyFactory.generatePublic(keySpec);
		//对数据解密
		Cipher cipher=Cipher.getInstance(keyFactory.getAlgorithm());
		cipher.init(Cipher.DECRYPT_MODE, pubKey);
		return cipher.doFinal(data);
	}
	
	
	/**
	 * 公钥加密
	 * @param data 待解密数据
	 * @param key 公钥
	 * @return byte[] 加密数据
	 * @throws Exception
	 */
	public static byte[] encryptByPublicKey(byte[] data,byte[] publicKey) throws Exception{
		//取得公钥
		X509EncodedKeySpec keySpec=new X509EncodedKeySpec(publicKey);
		//实例化密钥工厂
		KeyFactory keyFactory=KeyFactory.getInstance(KEY_ALGORITHM);
		//生成公钥
		PublicKey pubKey=keyFactory.generatePublic(keySpec);
		//对数据解密
		Cipher cipher=Cipher.getInstance(keyFactory.getAlgorithm());
		cipher.init(Cipher.ENCRYPT_MODE, pubKey);
		return cipher.doFinal(data);
	}
	
	/**
	 * 私钥加密
	 * @param data 待解密数据
	 * @param key 私钥
	 * @return byte[] 加密数据
	 * @throws Exception
	 */
	public static byte[] encryptByPrivateKey(byte[] data,byte[] privateKey) throws Exception{
		//取得私钥材料
		PKCS8EncodedKeySpec pkcs8KeySpec=new PKCS8EncodedKeySpec(privateKey);
		//实例化密钥工厂
		KeyFactory keyFactory=KeyFactory.getInstance(KEY_ALGORITHM);
		//取私钥匙对象
		PrivateKey priKey=keyFactory.generatePrivate(pkcs8KeySpec);
		//对数据解密
		Cipher cipher=Cipher.getInstance(keyFactory.getAlgorithm());
		cipher.init(Cipher.ENCRYPT_MODE, priKey);
		return cipher.doFinal(data);
	}
	
	/**
	 * 私钥加密
	 * @param data 待解密数据
	 * @param key 私钥
	 * @return byte[] 加密数据
	 * @throws Exception
	 */
	public static byte[] encryptByPrivateKey(byte[] data,String privateKey) throws Exception{
		return encryptByPrivateKey(data, getKey(privateKey));
	}
	
	/**
	 * 公钥加密
	 * @param data 待解密数据
	 * @param key 公钥
	 * @return byte[] 加密数据
	 * @throws Exception
	 */
	public static byte[] encryptByPublicKey(byte[] data,String publicKey) throws Exception{
		return encryptByPublicKey(data,getKey(publicKey));
	}
	
	/**
	 * 私钥解密
	 * @param data 待解密数据
	 * @param key 私钥
	 * @return byte[] 解密数据
	 * @throws Exception
	 */
	public static byte[] decryptByPrivateKey(byte[] data,String privateKey) throws Exception{
		return decryptByPrivateKey(data,getKey(privateKey));
	}
	
	/**
	 * 公钥解密
	 * @param data 待解密数据
	 * @param key 公钥
	 * @return byte[] 解密数据
	 * @throws Exception
	 */
	public static byte[] decryptByPublicKey(byte[] data,String publicKey) throws Exception{
		return decryptByPublicKey(data, getKey(publicKey));
	}
	
	/**
	 * 取得私钥
	 * @param keyMap 密钥Map
	 * @return String 十六进制密钥
	 * @throws Exception
	 */
	public static String getPrivateKeyString(Map<String, Object> keyMap)throws Exception {
		return Hex.encodeHexString(getPrivateKey(keyMap));
	}
	
	/**
	 * 取得公钥
	 * @param keyMap 密钥Map
	 * @return String 十六进制密钥
	 * @throws Exception
	 */
	public static String getPublicKeyString(Map<String, Object> keyMap)
			throws Exception {
		return Hex.encodeHexString(getPublicKey(keyMap));
	}
	/**
	 * 获取密钥
	 * @param key 密钥
	 * @return byte[] 密钥
	 * @throws Exception
	 */
	public static byte[] getKey(String key) throws Exception{
		return Hex.decodeHex(key.toCharArray());
	}
	
	
}
