package cn.xuyingqi.security;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import cn.xuyingqi.util.ByteUtils;

/**
 * Hmac-SHA256
 * 
 * @author XuYQ
 *
 */
public final class HmacSHA256 {

	/**
	 * 默认算法
	 */
	private static final String DEFAULT_ALGORITHM = "HmacSHA256";

	/**
	 * 私有构造方法
	 */
	private HmacSHA256() {

	}

	/**
	 * 签名
	 * 
	 * @param data
	 * @param key
	 * @return
	 * @throws InvalidKeyException
	 */
	public static final byte[] sign(byte[] data, byte[] key) throws InvalidKeyException {

		try {

			Mac sha256_HMAC = Mac.getInstance(DEFAULT_ALGORITHM);
			sha256_HMAC.init(new SecretKeySpec(key, DEFAULT_ALGORITHM));
			return sha256_HMAC.doFinal(data);
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

			byte[] data = "appid=wxd543c206a8910bd4&body=测试商品&mch_id=1372585802&nonce_str=nonceStr&notify_url=http://it.newcapec.cn:8086/wechat/test/test.action&out_trade_no=1545959613308&spbill_create_ip=117.158.17.227&total_fee=320&trade_type=JSAPI&key=af12f9838a2cebbfda0ed011085e15a3"
					.getBytes("UTF-8");
			byte[] key = "af12f9838a2cebbfda0ed011085e15a3".getBytes("UTF-8");
			System.out.println(new String(MD5.sign(data)).toUpperCase());
			System.out.println(ByteUtils.byteArray2DoubleHexString(HmacSHA256.sign(data, key)));
		} catch (InvalidKeyException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}
}