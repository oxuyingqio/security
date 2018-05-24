package cn.xuyingqi.security;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.util.Arrays;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;

/**
 * DES加解密算法
 * 
 * @author XuYQ
 *
 */
public final class DES {

	/**
	 * 私有构造方法
	 */
	private DES() {

	}

	/**
	 * 加密
	 * 
	 * @param data
	 *            加密数据
	 * @param key
	 *            密钥
	 * @return
	 */
	public static final byte[] encrypt(byte[] data, byte[] key) {

		try {

			// 生成DES密钥
			DESKeySpec desKey = new DESKeySpec(key);
			// 获取DES密钥工厂实例
			SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
			// 依据DES密钥生成安全密钥
			SecretKey secureKey = keyFactory.generateSecret(desKey);

			// 获取DES加解密工具实例
			Cipher cipher = Cipher.getInstance("DES/ECB/NoPadding");
			// 创建加密随机数生成器
			SecureRandom random = new SecureRandom();
			// 密钥+随机数初始化DES加解密工具
			cipher.init(Cipher.ENCRYPT_MODE, secureKey, random);

			// 加密
			return cipher.doFinal(data);
		} catch (InvalidKeyException e) {

			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {

			e.printStackTrace();
		} catch (InvalidKeySpecException e) {

			e.printStackTrace();
		} catch (NoSuchPaddingException e) {

			e.printStackTrace();
		} catch (IllegalBlockSizeException e) {

			e.printStackTrace();
		} catch (BadPaddingException e) {

			e.printStackTrace();
		}

		return null;
	}

	/**
	 * 解密
	 * 
	 * @param data
	 *            解密数据
	 * @param key
	 *            密钥
	 * @return
	 */
	public static final byte[] decrypt(byte[] data, byte[] key) {

		try {

			// 生成DES密钥
			DESKeySpec desKey = new DESKeySpec(key);
			// 获取DES密钥工厂实例
			SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
			// 依据DES密钥生成安全密钥
			SecretKey secureKey = keyFactory.generateSecret(desKey);

			// 获取DES加解密工具实例
			Cipher cipher = Cipher.getInstance("DES/ECB/NoPadding");
			// 创建加密随机数生成器
			SecureRandom random = new SecureRandom();
			// 密钥+随机数初始化DES加解密工具
			cipher.init(Cipher.DECRYPT_MODE, secureKey, random);

			// 解密
			return cipher.doFinal(data);
		} catch (InvalidKeyException e) {

			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {

			e.printStackTrace();
		} catch (InvalidKeySpecException e) {

			e.printStackTrace();
		} catch (NoSuchPaddingException e) {

			e.printStackTrace();
		} catch (IllegalBlockSizeException e) {

			e.printStackTrace();
		} catch (BadPaddingException e) {

			e.printStackTrace();
		}

		return null;
	}

	/**
	 * Main函数测试
	 * 
	 * @param args
	 */
	public static void main(String[] args) {

		for (int i = 0; i < 100000; i++) {

			System.out.println((i + 1) + Arrays.toString(
					DES.encrypt(new byte[] { 1, 2, 3, 4, 5, 6, 7, 8 }, new byte[] { 1, 2, 3, 4, 5, 6, 7, 8 })));
		}
	}
}