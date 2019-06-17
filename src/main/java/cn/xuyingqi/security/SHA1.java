package cn.xuyingqi.security;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * SHA1
 * 
 * @author XuYQ
 *
 */
public final class SHA1 {

	/**
	 * 默认消息摘要算法
	 */
	private transient static final String DEFAULT_MESSAGE_DIGEST_ALGORITHM = "SHA1";
	/**
	 * 默认摘要
	 */
	private transient static final String DEFAULT_DIGEST = "0123456789abcdef";

	/**
	 * 私有构造方法
	 */
	private SHA1() {

	}

	/**
	 * 签名
	 * 
	 * @param data
	 *            数据
	 * @param digest
	 *            摘要
	 * @return
	 */
	public static final byte[] sign(byte[] data, byte[] digest) {

		if (data.length == 0) {

			return null;
		}

		try {

			MessageDigest md = MessageDigest.getInstance(DEFAULT_MESSAGE_DIGEST_ALGORITHM);
			md.update(data);

			byte[] mdd = md.digest();
			byte[] sha1 = new byte[mdd.length * 2];
			int index = 0;
			for (int i = 0; i < mdd.length; i++) {

				byte byte0 = mdd[i];
				sha1[index++] = digest[byte0 >>> 4 & 0xf];
				sha1[index++] = digest[byte0 & 0xf];
			}

			return sha1;
		} catch (NoSuchAlgorithmException e) {

			e.printStackTrace();
		}

		return null;
	}

	/**
	 * 签名
	 * 
	 * @param data
	 *            数据
	 * @return
	 */
	public static final byte[] sign(byte[] data) {

		return SHA1.sign(data, DEFAULT_DIGEST.getBytes());
	}

	/**
	 * Main函数测试
	 * 
	 * @param args
	 */
	public static void main(String[] args) {

		StringBuffer sb = new StringBuffer();
		sb.append(
				"<?xml version=\"1.0\" encoding=\"UTF-8\"?><wxlifepay><head><err_msg/><merchantid>1480786242</merchantid><ret_code>0</ret_code><trancode>querycallback</trancode><transeqnum>1</transeqnum><version/></head><info><bill_key>1</bill_key><company_id>1480786242</company_id><data><attach/><balance>0</balance><begin_date null=\"true\"/><customer_name>菏泽测试用户22</customer_name><end_date null=\"true\"/><pay_amount>678</pay_amount></data><total_num>1</total_num></info></wxlifepay>");
		sb.append("hezekunlunranqi");
		System.out.println(sb.toString());

		System.out.println(new String(SHA1.sign(sb.toString().getBytes(), "0123456789abcdef".getBytes())));
	}
}