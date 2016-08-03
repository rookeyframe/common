package com.common.string.unit;

import org.junit.Test;

import com.common.string.IP2Long;



public class IP2LoginUnit {

	@Test
	public void ip2LongTest() {
		String ipStr = "192.168.0.1";
		long ipLong = IP2Long.ipToLong(ipStr);
		System.out.println("192.168.0.1 的整数形式为: " + ipLong);
		System.out.println("整数" + ipLong + "转化成字符串IP地址: "
				+ IP2Long.longToIP(ipLong));
		// IP地址转化成二进制形式输出
		System.out.println("192.168.0.1 的二进制形式为: "
				+ Long.toBinaryString(ipLong));
	}
}
