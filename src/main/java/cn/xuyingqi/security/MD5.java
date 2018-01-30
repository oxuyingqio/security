package cn.xuyingqi.security;

import java.security.MessageDigest;

/**
 * MD5加密算法
 * 
 * @author XuYQ
 *
 */
public class MD5 {

	/**
	 * MD5默认摘要
	 */
	private transient static final String DEFAULT_DIGEST = "0123456789abcdef";

	/**
	 * 加密
	 * 
	 * @param data
	 *            加密数据
	 * @param digest
	 *            摘要
	 * @return
	 */
	public static final byte[] encrypt(byte[] data, byte[] digest) {

		if (data.length == 0) {

			return null;
		}

		try {

			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(data);

			byte[] mdd = md.digest();
			byte[] sha1 = new byte[mdd.length * 2];
			int k = 0;
			for (int i = 0; i < mdd.length; i++) {

				byte byte0 = mdd[i];
				sha1[k++] = digest[byte0 >>> 4 & 0xf];
				sha1[k++] = digest[byte0 & 0xf];
			}

			return sha1;
		} catch (Exception e) {

			return null;
		}
	}

	/**
	 * 加密
	 * 
	 * @param data
	 *            加密数据
	 * @param digest
	 *            摘要
	 * @return
	 */
	public static final byte[] encrypt(byte[] data) {

		return MD5.encrypt(data, DEFAULT_DIGEST.getBytes());
	}

	/**
	 * Main函数测试
	 * 
	 * @param args
	 */
	public static void main(String[] args) {

		System.out.println(
				new String(MD5.encrypt("eeeeeeeeeeeeeewrw213123122222222222222222222222213123213213213erwer".getBytes(),
						"0123456789abcdef".getBytes())));
	}
}