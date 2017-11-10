package cn.xuyingqi.security.util;

/**
 * Security工具
 * 
 * @author XuYQ
 *
 */
public class SecurityUtil {

	/**
	 * 填充字节数组,使其满足整块数,1块=8字节.
	 * 
	 * @param source
	 * @return
	 */
	public static final byte[] padding(byte[] source) {

		// 获取传入数据最后一块缺少的字节个数
		int miss = source.length % 8 == 0 ? 0 : (8 - (source.length % 8));

		// 声明填充后的字节数组.长度为原数组长度+缺少字节个数
		byte[] target = new byte[source.length + miss];
		// 将传入数据复制至需要计算MAC值的字节数组中
		System.arraycopy(source, 0, target, 0, source.length);
		// 将最后一块缺少的字节,补0
		for (int i = 0; i < miss; i++) {

			target[source.length + i] = 0;
		}

		// 返回
		return target;
	}
}