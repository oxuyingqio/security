package cn.xuyingqi.security;

import org.apache.commons.lang3.ArrayUtils;

import cn.xuyingqi.security.util.SecurityUtils;
import cn.xuyingqi.util.exception.ByteArrayLengthErrorException;
import cn.xuyingqi.util.util.ByteUtils;

/**
 * MAC消息认证码
 * 
 * @author XuYQ
 *
 */
public class MAC {

	/**
	 * 获取MAC值
	 * 
	 * @param data
	 *            数据
	 * @param key
	 *            密钥
	 * @param vector
	 *            初始向量
	 * @return
	 */
	public static byte[] mac(byte[] data, byte[] key, byte[] vector) {

		// 若密钥或初始向量长度不为8,则抛字节数组长度错误异常
		if (key.length != 8 || vector.length != 8) {

			throw new ByteArrayLengthErrorException();
		}

		// 声明需要计算MAC值的字节数组,并填充缺失数据
		byte[] macData = SecurityUtils.padding(data);

		// 获取对应块数
		int blockLength = macData.length / 8;
		// 遍历每一块
		for (int i = 0; i < blockLength; i++) {

			// 每一块与初始向量进行异或,并将结果赋予初始向量
			vector = ByteUtils.xor(ArrayUtils.subarray(macData, i * 8, (i + 1) * 8), vector);
			// 对初始向量进行DES加密
			vector = DES.encrypt(vector, key);
		}

		return vector;
	}
}