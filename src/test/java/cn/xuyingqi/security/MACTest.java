package cn.xuyingqi.security;

import org.junit.Test;

public class MACTest {

	@Test
	public void test() {

		byte[] data = new byte[] { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23,
				24 };
		byte[] key = new byte[] { 1, 2, 3, 4, 5, 6, 7, 8 };
		byte[] vector = new byte[] { 0, 0, 0, 0, 0, 0, 0, 0 };
		byte[] mac = MAC.mac(data, key, vector);

		for (int i = 0; i < mac.length; i++) {
			System.out.println(Integer.toHexString(mac[i]));
		}
	}
}
