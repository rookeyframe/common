package com.common.string.unit;

import java.io.UnsupportedEncodingException;

import org.junit.Test;

import com.common.string.ChangeCharset;

public class ChangeCharsetUnit {
	
	@Test
	public void changeCharsetTest() throws UnsupportedEncodingException{
		//首先是了解自己的本地的编码 本地是UTF
		ChangeCharset test = new ChangeCharset();
		String str = "This is a 中文的 String!";
		System.out.println("str: " + str);
		String gbk = test.toGBK(str);
		System.out.println("转换成GBK码: " + gbk);
		System.out.println();
		String ascii = test.toASCII(str);
		System.out.println("转换成US-ASCII码: " + ascii);
		gbk = test.changeCharset(ascii,ChangeCharset.US_ASCII, ChangeCharset.GBK);
		System.out.println("再把ASCII码的字符串转换成GBK码: " + gbk);
		System.out.println();
		String iso88591 = test.toISO_8859_1(str);
		System.out.println("转换成ISO-8859-1码: " + iso88591);
		gbk = test.changeCharset(iso88591,ChangeCharset.ISO_8859_1, ChangeCharset.GBK);
		System.out.println("再把ISO-8859-1码的字符串转换成GBK码: " + gbk);
		System.out.println();
		String utf8 = test.toUTF_8(str);
		System.out.println("转换成UTF-8码: " + utf8);
		gbk = test.changeCharset(utf8,ChangeCharset.UTF_8, ChangeCharset.GBK);
		System.out.println("再把UTF-8码的字符串转换成GBK码: " + gbk);
		System.out.println();
		String utf16be = test.toUTF_16BE(str);
		System.out.println("转换成UTF-16BE码:" + utf16be);
		gbk = test.changeCharset(utf16be,ChangeCharset.UTF_16BE, ChangeCharset.GBK);
		System.out.println("再把UTF-16BE码的字符串转换成GBK码: " + gbk);
		System.out.println();
		String utf16le = test.toUTF_16LE(str);
		System.out.println("转换成UTF-16LE码:" + utf16le);
		gbk = test.changeCharset(utf16le,ChangeCharset.UTF_16LE, ChangeCharset.GBK);
		System.out.println("再把UTF-16LE码的字符串转换成GBK码: " + gbk);
		System.out.println();
		String utf16 = test.toUTF_16(str);
		System.out.println("转换成UTF-16码:" + utf16);
		gbk = test.changeCharset(utf16,ChangeCharset.UTF_16LE, ChangeCharset.GBK);
		System.out.println("再把UTF-16码的字符串转换成GBK码: " + gbk);
		String s = new String("中文".getBytes("UTF-8"),"UTF-8");
		System.out.println(s);
	}

}
