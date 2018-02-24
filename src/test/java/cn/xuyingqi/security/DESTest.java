package cn.xuyingqi.security;

import java.io.UnsupportedEncodingException;

import org.junit.Test;

public class DESTest {

	@Test
	public void test() {

		try {

			System.out.println(new String("鏍￠獙鐢ㄦ埛鍚嶅瘑鐮�".getBytes("GBK"), "UTF-8"));
		} catch (UnsupportedEncodingException e) {

			e.printStackTrace();
		}
	}
}
