package cn.xuyingqi.security.util;

public class ByteUtils {

	/**
	 * 
	 * @param data
	 * @return
	 */
	public static byte[] padding(byte[] data) {

		// 获取传入数据最后一块缺少的字节个数
		int miss = data.length % 8 == 0 ? 0 : (8 - (data.length % 8));

		// 声明需要计算MAC值的字节数组,长度为数据长度+缺少的字节个数
		byte[] returnData = new byte[data.length + miss];
		// 将传入数据复制至需要计算MAC值的字节数组中
		System.arraycopy(data, 0, returnData, 0, data.length);
		// 将最后一块缺少的字节,补0
		for (int i = 0; i < miss; i++) {

			returnData[data.length + i] = 0;
		}

		// 返回
		return returnData;
	}
}
