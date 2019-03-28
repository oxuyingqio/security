package cn.xuyingqi.security;

import java.security.InvalidKeyException;
import java.security.spec.InvalidKeySpecException;
import java.util.Arrays;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;

import org.apache.commons.lang3.ArrayUtils;

import cn.xuyingqi.security.util.SecurityUtils;
import cn.xuyingqi.util.ByteUtils;
import cn.xuyingqi.util.exception.ByteArrayLengthErrorException;

/**
 * MAC
 * 
 * @author XuYQ
 *
 */
public final class MAC {

	/**
	 * 默认初始向量
	 */
	private transient static final byte[] DEFAULT_VECTOR = new byte[] { 0, 0, 0, 0, 0, 0, 0, 0 };

	/**
	 * 私有构造方法
	 */
	private MAC() {

	}

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
	 * @throws BadPaddingException
	 * @throws IllegalBlockSizeException
	 * @throws InvalidKeySpecException
	 * @throws InvalidKeyException
	 */
	public static final byte[] mac(byte[] data, byte[] key, byte[] vector)
			throws InvalidKeyException, InvalidKeySpecException, IllegalBlockSizeException, BadPaddingException {

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

	/**
	 * 获取MAC值
	 * 
	 * @param data
	 *            数据
	 * @param key
	 *            密钥
	 * @return
	 * @throws BadPaddingException
	 * @throws IllegalBlockSizeException
	 * @throws InvalidKeySpecException
	 * @throws InvalidKeyException
	 */
	public static final byte[] mac(byte[] data, byte[] key)
			throws InvalidKeyException, InvalidKeySpecException, IllegalBlockSizeException, BadPaddingException {

		return MAC.mac(data, key, DEFAULT_VECTOR);
	}

	/**
	 * Main函数测试
	 * 
	 * @param args
	 */
	public static void main(String[] args) {

		for (int i = 0; i < 100000; i++) {

			try {
				System.out.println((i + 1) + Arrays.toString(
						MAC.mac(new byte[] { 1, 2, 3, 4, 5, 6, 7, 8 }, new byte[] { 1, 2, 3, 4, 5, 6, 7, 8 })));
			} catch (InvalidKeyException e) {
				e.printStackTrace();
			} catch (InvalidKeySpecException e) {
				e.printStackTrace();
			} catch (IllegalBlockSizeException e) {
				e.printStackTrace();
			} catch (BadPaddingException e) {
				e.printStackTrace();
			}
		}
	}
}