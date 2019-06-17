package cn.xuyingqi.security.test;

import cn.xuyingqi.util.ByteUtils;

/**
 * 测试类
 * 
 * @author XuYQ
 *
 */
public class Test {

	public static byte[] longToByte(long number) {

		long temp = number;
		byte[] b = new byte[8];
		for (int i = 0; i < b.length; i++) {

			b[i] = new Long(temp & 0xff).byteValue();
			temp = temp >> 8;
		}

		return b;
	}

	/**
	 * Main函数测试
	 * 
	 * @param args
	 */
	public static void main(String[] args) {

		Long[] ls = new Long[] { 4666061507374724217l };
		for (Long l : ls) {

			System.out.println("update gas_terminal set terminal_work_key="
					+ ByteUtils.byteArray2Long(Test.longToByte(l)) + " where terminal_work_key=" + l + ";");
		}
	}
}