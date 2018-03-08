package cn.xuyingqi.security;

import java.io.UnsupportedEncodingException;

import org.junit.Test;

public class DESTest {

	@Test
	public void test() {

		try {

			System.out.println(new String("鑷姩鍦ㄧ幆澧冧腑鏌ユ壘鐩爣鎺ュ彛鐨勬墍鏈夊璞�".getBytes("GBK"), "UTF-8"));
		} catch (UnsupportedEncodingException e) {

			e.printStackTrace();
		}
	}
}
