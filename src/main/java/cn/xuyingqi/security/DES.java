package cn.xuyingqi.security;

import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.util.Arrays;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;

import cn.xuyingqi.util.Base64Utils;

/**
 * DES
 * 
 * @author XuYQ
 *
 */
public final class DES {

	/**
	 * 默认
	 */
	private transient static final String DEFAULT_CIPHER_TRANSFORMATION = "DES/ECB/NoPadding";
	/**
	 * 默认
	 */
	private transient static final String DEFAULT_CIPHER_TRANSFORMATION_IV = "DES/CBC/PKCS5Padding";
	/**
	 * 默认密钥算法
	 */
	private transient static final String DEFAULT_KEY_ALGORITHM = "DES";

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
	 * @throws InvalidKeyException
	 * @throws InvalidKeySpecException
	 * @throws BadPaddingException
	 * @throws IllegalBlockSizeException
	 */
	public static final byte[] encrypt(byte[] data, byte[] key)
			throws InvalidKeyException, InvalidKeySpecException, IllegalBlockSizeException, BadPaddingException {

		try {

			// 获取DES加解密工具实例
			Cipher cipher = Cipher.getInstance(DEFAULT_CIPHER_TRANSFORMATION);
			// 密钥+随机数初始化DES加解密工具
			cipher.init(Cipher.ENCRYPT_MODE,
					SecretKeyFactory.getInstance(DEFAULT_KEY_ALGORITHM).generateSecret(new DESKeySpec(key)),
					new SecureRandom());

			// 加密
			return cipher.doFinal(data);
		} catch (NoSuchAlgorithmException e) {

			e.printStackTrace();
		} catch (NoSuchPaddingException e) {

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
	 * @throws InvalidKeyException
	 * @throws InvalidKeySpecException
	 * @throws BadPaddingException
	 * @throws IllegalBlockSizeException
	 */
	public static final byte[] decrypt(byte[] data, byte[] key)
			throws InvalidKeyException, InvalidKeySpecException, IllegalBlockSizeException, BadPaddingException {

		try {

			// 获取DES加解密工具实例
			Cipher cipher = Cipher.getInstance(DEFAULT_CIPHER_TRANSFORMATION);
			// 密钥+随机数初始化DES加解密工具
			cipher.init(Cipher.DECRYPT_MODE,
					SecretKeyFactory.getInstance(DEFAULT_KEY_ALGORITHM).generateSecret(new DESKeySpec(key)),
					new SecureRandom());

			// 解密
			return cipher.doFinal(data);
		} catch (NoSuchAlgorithmException e) {

			e.printStackTrace();
		} catch (NoSuchPaddingException e) {

			e.printStackTrace();
		}

		return null;
	}

	/**
	 * 加密
	 * 
	 * @param data
	 *            数据
	 * @param key
	 *            密钥
	 * @param iv
	 *            向量
	 * @return
	 * @throws InvalidKeyException
	 * @throws InvalidKeySpecException
	 * @throws IllegalBlockSizeException
	 * @throws BadPaddingException
	 */
	public static final byte[] encrypt(byte[] data, byte[] key, byte[] iv)
			throws InvalidKeyException, InvalidKeySpecException, IllegalBlockSizeException, BadPaddingException {

		try {

			// 获取DES加解密工具实例
			Cipher cipher = Cipher.getInstance(DEFAULT_CIPHER_TRANSFORMATION_IV);
			// 设置工作模式为加密模式，给出密钥和向量
			cipher.init(Cipher.ENCRYPT_MODE,
					SecretKeyFactory.getInstance(DEFAULT_KEY_ALGORITHM).generateSecret(new DESKeySpec(key)),
					new IvParameterSpec(iv));

			// 加密
			return cipher.doFinal(data);
		} catch (NoSuchAlgorithmException e) {

			e.printStackTrace();
		} catch (NoSuchPaddingException e) {

			e.printStackTrace();
		} catch (InvalidAlgorithmParameterException e) {

			e.printStackTrace();
		}

		return null;
	}

	/**
	 * 解密
	 * 
	 * @param data
	 *            数据
	 * @param key
	 *            密钥
	 * @param iv
	 *            向量
	 * @return
	 * @throws InvalidKeyException
	 * @throws InvalidKeySpecException
	 * @throws IllegalBlockSizeException
	 * @throws BadPaddingException
	 */
	public static final byte[] decrypt(byte[] data, byte[] key, byte[] iv)
			throws InvalidKeyException, InvalidKeySpecException, IllegalBlockSizeException, BadPaddingException {

		try {

			// 获取DES加解密工具实例
			Cipher cipher = Cipher.getInstance(DEFAULT_CIPHER_TRANSFORMATION_IV);
			// 设置工作模式为加密模式，给出密钥和向量
			cipher.init(Cipher.DECRYPT_MODE,
					SecretKeyFactory.getInstance(DEFAULT_KEY_ALGORITHM).generateSecret(new DESKeySpec(key)),
					new IvParameterSpec(iv));

			// 解密
			return cipher.doFinal(data);
		} catch (NoSuchAlgorithmException e) {

			e.printStackTrace();
		} catch (NoSuchPaddingException e) {

			e.printStackTrace();
		} catch (InvalidAlgorithmParameterException e) {

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

		try {

			byte[] pubKey = "cf43qbmy".getBytes("UTF-8");
			byte[] pubIv = "bhscf43q".getBytes("UTF-8");

			System.out.println(Arrays.toString(pubKey));
			System.out.println(Arrays.toString(pubIv));

			byte[] a1 = DES.encrypt("12345678".getBytes("UTF-8"), pubKey, pubIv);
			byte[] a2 = DES.encrypt(Base64Utils.encode(a1).getBytes("UTF-8"), pubKey, pubIv);
			System.out.println(Base64Utils.encode(a2));
		} catch (InvalidKeyException e) {
			e.printStackTrace();
		} catch (InvalidKeySpecException e) {
			e.printStackTrace();
		} catch (IllegalBlockSizeException e) {
			e.printStackTrace();
		} catch (BadPaddingException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}
}