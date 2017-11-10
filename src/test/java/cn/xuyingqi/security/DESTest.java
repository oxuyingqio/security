package cn.xuyingqi.security;

import org.junit.Test;

import cn.xuyingqi.security.util.SecurityUtil;

public class DESTest {

	@Test
	public void test() {

		byte[] data = "010037063625270636252011010150".getBytes();
		byte[] key = "test_key".getBytes();

		byte[] security = DES.encrypt(SecurityUtil.padding(data), key);
		for (int i = 0; i < security.length; i++) {
			System.out.println(Integer.toHexString(security[i]));
		}

		String str = new String(DES.decrypt(security, key));
		System.out.println(str);
	}
}
