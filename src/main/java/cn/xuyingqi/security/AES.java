package cn.xuyingqi.security;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.Security;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;

import org.bouncycastle.jce.provider.BouncyCastleProvider;

import cn.xuyingqi.util.ByteUtils;

/**
 * AES
 * 
 * @author XuYQ
 *
 */
public final class AES {

	/**
	 * 默认 AES/ECB/PKCS7Padding
	 */
	private transient static final String DEFAULT_CIPHER_TRANSFORMATION = "AES/ECB/ZeroBytePadding";
	/**
	 * 默认密钥算法
	 */
	private transient static final String DEFAULT_KEY_ALGORITHM = "AES";

	/**
	 * AES
	 */
	private static AES aes;

	/**
	 * 私有构造方法
	 */
	private AES() {

		// 添加密钥算法支持
		Security.addProvider(new BouncyCastleProvider());
	}

	/**
	 * 获取AES实例
	 * 
	 * @return
	 */
	public static final synchronized AES getInstance() {

		// 判断是否为空
		if (aes == null) {

			// 实例化
			aes = new AES();
		}

		return aes;
	}

	/**
	 * 加密
	 * 
	 * @param data
	 * @param key
	 * @return
	 * @throws InvalidKeyException
	 * @throws IllegalBlockSizeException
	 * @throws BadPaddingException
	 */
	public final byte[] encrypt(byte[] data, byte[] key)
			throws InvalidKeyException, IllegalBlockSizeException, BadPaddingException {

		try {

			Cipher cipher = Cipher.getInstance(DEFAULT_CIPHER_TRANSFORMATION, "BC");
			cipher.init(Cipher.ENCRYPT_MODE, new SecretKeySpec(key, DEFAULT_KEY_ALGORITHM));

			return cipher.doFinal(data);
		} catch (NoSuchAlgorithmException e) {

			e.printStackTrace();
		} catch (NoSuchPaddingException e) {

			e.printStackTrace();
		} catch (NoSuchProviderException e) {

			e.printStackTrace();
		}

		return null;
	}

	/**
	 * 解密
	 * 
	 * @param data
	 * @param key
	 * @return
	 * @throws InvalidKeyException
	 * @throws IllegalBlockSizeException
	 * @throws BadPaddingException
	 */
	public final byte[] decrypt(byte[] data, byte[] key)
			throws InvalidKeyException, IllegalBlockSizeException, BadPaddingException {

		try {

			Cipher cipher = Cipher.getInstance(DEFAULT_CIPHER_TRANSFORMATION, "BC");
			cipher.init(Cipher.DECRYPT_MODE, new SecretKeySpec(key, DEFAULT_KEY_ALGORITHM));

			return cipher.doFinal(data);
		} catch (NoSuchAlgorithmException e) {

			e.printStackTrace();
		} catch (NoSuchPaddingException e) {

			e.printStackTrace();
		} catch (NoSuchProviderException e) {

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

			byte[] key = ByteUtils.doubleHexString2ByteArray("01020304050607080000000000000000");
			byte[] data = ByteUtils.doubleHexString2ByteArray("00112233445566778899aabb80000000".replace(" ", ""));

			byte[] a = AES.getInstance().encrypt(data, key);
			System.out.println(a.length);
			System.out.println(ByteUtils.byteArray2DoubleHexString(a));

		} catch (InvalidKeyException e) {

			e.printStackTrace();
		} catch (IllegalBlockSizeException e) {

			e.printStackTrace();
		} catch (BadPaddingException e) {

			e.printStackTrace();
		}
	}
}