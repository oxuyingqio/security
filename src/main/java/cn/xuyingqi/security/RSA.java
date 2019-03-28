package cn.xuyingqi.security;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.SignatureException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.RSAPublicKeySpec;
import java.security.spec.X509EncodedKeySpec;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import cn.xuyingqi.util.Base64Utils;

/**
 * RSA
 * 
 * @author XuYQ
 *
 */
public final class RSA {

	/**
	 * 默认
	 */
	private transient static final String DEFAULT_CIPHER_TRANSFORMATION = "RSA/ECB/PKCS1Padding";
	/**
	 * 默认最大加密块
	 */
	private transient static final int DEFAULT_MAX_ENCRYPT_BLOCK = 117;
	/**
	 * 默认最大解密块
	 */
	private transient static final int DEFAULT_MAX_DECRYPT_BLOCK = 128;
	/**
	 * 默认
	 */
	private transient static final String DEFAULT_SIGNATURE_ALGORITHM = "MD5withRSA";
	/**
	 * 默认密钥算法
	 */
	private transient static final String DEFAULT_KEY_ALGORITHM = "RSA";

	/**
	 * 私有构造方法
	 */
	private RSA() {

	}

	/**
	 * 公钥加密
	 * 
	 * @param data
	 *            加密数据
	 * @param publicKey
	 *            公钥
	 * @return
	 * @throws InvalidKeyException
	 * @throws BadPaddingException
	 * @throws IllegalBlockSizeException
	 * @throws IOException
	 */
	public static final byte[] encrypt(byte[] data, PublicKey publicKey)
			throws InvalidKeyException, IllegalBlockSizeException, BadPaddingException, IOException {

		try {

			Cipher cipher = Cipher.getInstance(DEFAULT_CIPHER_TRANSFORMATION);
			// 编码前设定编码方式及密钥
			cipher.init(Cipher.ENCRYPT_MODE, publicKey);

			// 传入编码数据并返回编码结果
			int dataLength = data.length;
			ByteArrayOutputStream out = new ByteArrayOutputStream();
			int offSet = 0;
			byte[] cache;
			int index = 0;
			// 对数据分段加密
			while (dataLength - offSet > 0) {

				if (dataLength - offSet > DEFAULT_MAX_ENCRYPT_BLOCK) {

					cache = cipher.doFinal(data, offSet, DEFAULT_MAX_ENCRYPT_BLOCK);
				} else {

					cache = cipher.doFinal(data, offSet, dataLength - offSet);
				}
				out.write(cache, 0, cache.length);
				index++;
				offSet = index * DEFAULT_MAX_ENCRYPT_BLOCK;
			}

			byte[] encrypt = out.toByteArray();
			out.close();

			return encrypt;
		} catch (NoSuchAlgorithmException e) {

			e.printStackTrace();
		} catch (NoSuchPaddingException e) {

			e.printStackTrace();
		}

		return null;
	}

	/**
	 * 私钥解密
	 * 
	 * @param data
	 *            解密数据
	 * @param privateKey
	 *            私钥
	 * @return
	 * @throws InvalidKeyException
	 * @throws BadPaddingException
	 * @throws IllegalBlockSizeException
	 */
	public static final byte[] decrypt(byte[] data, PrivateKey privateKey)
			throws InvalidKeyException, IllegalBlockSizeException, BadPaddingException {

		try {

			Cipher cipher = Cipher.getInstance(DEFAULT_CIPHER_TRANSFORMATION);
			cipher.init(Cipher.DECRYPT_MODE, privateKey);

			return cipher.doFinal(data);
		} catch (NoSuchAlgorithmException e) {

			e.printStackTrace();
		} catch (NoSuchPaddingException e) {

			e.printStackTrace();
		}

		return null;
	}

	/**
	 * 私钥加密
	 * 
	 * @param data
	 *            加密数据
	 * @param privateKey
	 *            私钥
	 * @return
	 * @throws InvalidKeyException
	 * @throws BadPaddingException
	 * @throws IllegalBlockSizeException
	 * @throws IOException
	 */
	public static byte[] encrypt(byte[] data, PrivateKey privateKey)
			throws InvalidKeyException, IllegalBlockSizeException, BadPaddingException, IOException {

		try {

			Cipher cipher = Cipher.getInstance(DEFAULT_CIPHER_TRANSFORMATION);
			cipher.init(Cipher.ENCRYPT_MODE, privateKey);

			int dataLength = data.length;
			ByteArrayOutputStream out = new ByteArrayOutputStream();
			int offSet = 0;
			byte[] cache;
			int index = 0;
			// 对数据分段加密
			while (dataLength - offSet > 0) {

				if (dataLength - offSet > DEFAULT_MAX_ENCRYPT_BLOCK) {

					cache = cipher.doFinal(data, offSet, DEFAULT_MAX_ENCRYPT_BLOCK);
				} else {

					cache = cipher.doFinal(data, offSet, dataLength - offSet);
				}
				out.write(cache, 0, cache.length);
				index++;
				offSet = index * DEFAULT_MAX_ENCRYPT_BLOCK;
			}

			byte[] encrypt = out.toByteArray();
			out.close();

			return encrypt;
		} catch (NoSuchAlgorithmException e) {

			e.printStackTrace();
		} catch (NoSuchPaddingException e) {

			e.printStackTrace();
		}

		return null;
	}

	/**
	 * 公钥解密
	 * 
	 * @param data
	 * @param publicKey
	 * @return
	 * @throws InvalidKeyException
	 * @throws BadPaddingException
	 * @throws IllegalBlockSizeException
	 * @throws IOException
	 */
	public static final byte[] decrypt(byte[] data, PublicKey publicKey)
			throws InvalidKeyException, IllegalBlockSizeException, BadPaddingException, IOException {

		try {

			Cipher cipher = Cipher.getInstance(DEFAULT_CIPHER_TRANSFORMATION);
			cipher.init(Cipher.DECRYPT_MODE, publicKey);

			int dataLength = data.length;
			ByteArrayOutputStream out = new ByteArrayOutputStream();
			int offSet = 0;
			byte[] cache;
			int index = 0;
			// 对数据分段解密
			while (dataLength - offSet > 0) {

				if (dataLength - offSet > DEFAULT_MAX_DECRYPT_BLOCK) {

					cache = cipher.doFinal(data, offSet, DEFAULT_MAX_DECRYPT_BLOCK);
				} else {

					cache = cipher.doFinal(data, offSet, dataLength - offSet);
				}
				out.write(cache, 0, cache.length);
				index++;
				offSet = index * DEFAULT_MAX_DECRYPT_BLOCK;
			}

			byte[] decrypt = out.toByteArray();
			out.close();

			return decrypt;
		} catch (NoSuchAlgorithmException e) {

			e.printStackTrace();
		} catch (NoSuchPaddingException e) {

			e.printStackTrace();
		}

		return null;
	}

	/**
	 * 私钥签名
	 * 
	 * @param data
	 * @param privateKey
	 * @return
	 * @throws InvalidKeyException
	 * @throws SignatureException
	 */
	public static final byte[] sign(byte[] data, PrivateKey privateKey) throws InvalidKeyException, SignatureException {

		try {

			Signature signature = Signature.getInstance(DEFAULT_SIGNATURE_ALGORITHM);
			signature.initSign(privateKey);
			signature.update(data);

			return signature.sign();
		} catch (NoSuchAlgorithmException e) {

			e.printStackTrace();
		}

		return null;
	}

	/**
	 * 公钥验签
	 * 
	 * @param data
	 * @param publicKey
	 * @param sign
	 * @return
	 * @throws InvalidKeyException
	 * @throws SignatureException
	 */
	public static final boolean verify(byte[] data, byte[] sign, PublicKey publicKey)
			throws InvalidKeyException, SignatureException {

		try {

			Signature signature = Signature.getInstance(DEFAULT_SIGNATURE_ALGORITHM);
			signature.initVerify(publicKey);
			signature.update(data);

			return signature.verify(sign);
		} catch (NoSuchAlgorithmException e) {

			e.printStackTrace();
		}

		return false;
	}

	/**
	 * 随机生成RSA密钥对
	 * 
	 * @param keyLength
	 *            密钥长度(512-2048)
	 * @return
	 */
	public static final KeyPair generateRSAKeyPair(int keyLength) {

		try {

			KeyPairGenerator kpg = KeyPairGenerator.getInstance(DEFAULT_KEY_ALGORITHM);
			kpg.initialize(keyLength);

			return kpg.genKeyPair();
		} catch (NoSuchAlgorithmException e) {

			e.printStackTrace();
		}

		return null;
	}

	/**
	 * 随机生成长度为1024的RSA密钥对
	 * 
	 * @return
	 */
	public static final KeyPair generateRSAKeyPair() {

		return generateRSAKeyPair(1024);
	}

	/**
	 * 通过公钥byte[](publicKey.getEncoded())将公钥恢复，适用于RSA算法
	 * 
	 * @param encoded
	 * @return
	 * @throws InvalidKeySpecException
	 */
	public static final PublicKey recoveryPublicKey(byte[] encoded) throws InvalidKeySpecException {

		try {

			return KeyFactory.getInstance(DEFAULT_KEY_ALGORITHM).generatePublic(new X509EncodedKeySpec(encoded));
		} catch (NoSuchAlgorithmException e) {

			e.printStackTrace();
		}

		return null;
	}

	/**
	 * 通过私钥byte[]将私钥恢复，适用于RSA算法
	 * 
	 * @param encoded
	 * @return
	 * @throws InvalidKeySpecException
	 */
	public static final PrivateKey recoveryPrivateKey(byte[] encoded) throws InvalidKeySpecException {

		try {

			return KeyFactory.getInstance(DEFAULT_KEY_ALGORITHM).generatePrivate(new PKCS8EncodedKeySpec(encoded));
		} catch (NoSuchAlgorithmException e) {

			e.printStackTrace();
		}

		return null;
	}

	/**
	 * 使用N、e值还原公钥
	 * 
	 * @param modulus
	 * @param publicExponent
	 * @return
	 * @throws InvalidKeySpecException
	 */
	public static final PublicKey recoveryPublicKey(String modulus, String publicExponent)
			throws InvalidKeySpecException {

		try {

			return KeyFactory.getInstance(DEFAULT_KEY_ALGORITHM)
					.generatePublic(new RSAPublicKeySpec(new BigInteger(modulus), new BigInteger(publicExponent)));
		} catch (NoSuchAlgorithmException e) {

			e.printStackTrace();
		}

		return null;
	}

	/**
	 * 使用N、d值还原私钥
	 * 
	 * @param modulus
	 * @param privateExponent
	 * @return
	 * @throws InvalidKeySpecException
	 */
	public static final PrivateKey recoveryPrivateKey(String modulus, String privateExponent)
			throws InvalidKeySpecException {

		try {

			return KeyFactory.getInstance(DEFAULT_KEY_ALGORITHM)
					.generatePrivate(new RSAPublicKeySpec(new BigInteger(modulus), new BigInteger(privateExponent)));
		} catch (NoSuchAlgorithmException e) {

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

			// 公钥
			PublicKey publicKey = RSA.recoveryPublicKey(Base64Utils.decode(
					"MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCkAZBAFHP9XzqtX3wTXt3Sy6eP9Q9YRH8XLl4lZOmPqLeKvSUSKfzfTAlhe153f99rjgDt6uRh0b8kZeH6j39BYHJGQ50FCLvYtr0iAi5uVa33ipAWcBrTuVUCyPjQxa/DO4jNoZ8IChTkfH2J2pWWLTalvIrjsBReJgRajq8OZQIDAQAB"));
			// 私钥
			PrivateKey privateKey = RSA.recoveryPrivateKey(Base64Utils.decode(
					"MIICdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEAAoGBAKQBkEAUc/1fOq1ffBNe3dLLp4/1D1hEfxcuXiVk6Y+ot4q9JRIp/N9MCWF7Xnd/32uOAO3q5GHRvyRl4fqPf0FgckZDnQUIu9i2vSICLm5VrfeKkBZwGtO5VQLI+NDFr8M7iM2hnwgKFOR8fYnalZYtNqW8iuOwFF4mBFqOrw5lAgMBAAECgYALZsBsc/+MQa6Glvi9L/8jxFyluVeTv09GgUayOs2x2vzHfFazaI/XxiVwzW7Hx+/0Pi76WzX9RuOO2DZZgNhLyqj5XvMc98IUGy4Pg+y++j3VqGe0silaGW7GOZxU2ZhnL07woZA+sKPW1euhxKQijbLxsqieFAX167tKMmI9gQJBANou7DroFMoQ6ivwVyGjUgwL7qduwXPsETMiAg5wkLw4uT1HyM/lNLg2AIiHTeuMq0iyeYYtvfHuOfg9LmdQRm0CQQDAbrp1r/+XgDw86Wj+3wHSsKZRL24NmUff1CyI8RL6HGrhXZEKUNlWrFlnH9T6nYc4t3OYdAgJ4/P8Q1sIx0zZAkEAwo8/NpRIHZyRxVbPy/5Apt9pFMNOHRowiEFWRhwY5M9MWBoQN1sz9OSq+xR05oyNHQmSFpXpoLiH1xZvr89EzQJAJWzjosPqB8MO9CHJI9ineByXHC8HTmnDSsrvJmsMb8xbFR2necQjoPHAKYhNWbiNdgBn4PygG5xT9Xn8GIb6cQJBAJZpDOJPl6LV0Vt2ao2aRnp21ZRcwvY+l93xldOfOwWcf+UIVvKI9Hd/zWQpgbyptVCVQaQOCPpc2YCXXcnQxMI="));

			// 公钥加密,私钥解密
			System.out.println(new String(RSA.decrypt(RSA.encrypt("公钥加密,私钥解密".getBytes(), publicKey), privateKey)));
			// 私钥加密,公钥解密
			System.out.println(new String(RSA.decrypt(RSA.encrypt("私钥加密,公钥解密".getBytes(), privateKey), publicKey)));

			System.out.println(
					RSA.verify("私钥签名,公钥验签".getBytes(), RSA.sign("私钥签名,公钥验签".getBytes(), privateKey), publicKey));

			Base64Utils.encode(RSA.sign("私钥签名,公钥验签".getBytes(), RSA.recoveryPrivateKey(Base64Utils.decode(""))));

		} catch (InvalidKeyException e) {
			e.printStackTrace();
		} catch (IllegalBlockSizeException e) {
			e.printStackTrace();
		} catch (BadPaddingException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (InvalidKeySpecException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (SignatureException e) {
			e.printStackTrace();
		}
	}
}