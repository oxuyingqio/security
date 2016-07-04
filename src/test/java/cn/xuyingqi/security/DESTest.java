package cn.xuyingqi.security;

import java.util.Arrays;

import org.junit.Test;

public class DESTest {

	@Test
	public void test() {

		byte[] data = new byte[] { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23,
				24 };
		byte[] key = new byte[] { 1, 2, 3, 4, 5, 6, 7, 8 };
		byte[] sec = DES.encrypt(data, key);
		System.out.println(Arrays.toString(sec));

	}
}
