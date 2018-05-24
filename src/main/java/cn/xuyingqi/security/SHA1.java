package cn.xuyingqi.security;

import java.security.MessageDigest;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayTradeAppPayRequest;
import com.alipay.api.response.AlipayTradeAppPayResponse;

import cn.xuyingqi.util.UUIDUtils;
import cn.xuyingqi.util.util.MapFactory;

/**
 * SHA1加密算法
 * 
 * @author XuYQ
 *
 */
public final class SHA1 {

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

			MessageDigest md = MessageDigest.getInstance("SHA1");
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
	 * @return
	 */
	public static final byte[] encrypt(byte[] data) {

		return SHA1.encrypt(data, DEFAULT_DIGEST.getBytes());
	}

	/**
	 * Main函数测试
	 * 
	 * @param args
	 */
	public static void main(String[] args) {

		Map<String, Object> initParams = MapFactory.newInstance();
		initParams.put("subject", "测试订单标题");
		initParams.put("out_trade_no", UUIDUtils.get32UUID());
		initParams.put("total_amount", "100");
		initParams.put("seller_id", "");
		initParams.put("quit_url",
				"http://218.28.133.181:13282/aggregatePayment/showAggregatePayment.action?merchant=xuyq&money=100");
		initParams.put("product_code", "QUICK_WAP_WAY");

		AlipayClient alipayClient = new DefaultAlipayClient("https://openapi.alipay.com/gateway.do", "2016081600254862",
				"MIIEuwIBADANBgkqhkiG9w0BAQEFAASCBKUwggShAgEAAoIBAQCrv/as21bz/zMCu/yYF1IFgYpAByOyAzsQinHCc1s5nTpKl8oyUiuaNdaUjU9NPKcgISWCupN2ecbTW4EjiD0R2UwD1qgCeYE4pMRQaiwX4n/V6flM6FhTjEHTBf3A0lgaHqLd3PI4F15KD91PNY5+RYpViVc3ZZ/lPlt0Po9VY5XMq3Q4M9FVVd8WtOwWzBNiRanq8i9vfYDDeXmcNKn6Jabf4TgOtL1AvBgIMWyfkDmK9i2Z5dEGqGlgmxT6OAhaPG54DVq2ZPzxdZrgGvRp56iqXV8Pxhs7tS8CrDdixD9T8qICtDVYDz6Xm2zc9K6rA2hYXeiHqQMnqJ0djINdAgMBAAECggEAQVns2b8oKfWcCllPduvAuPmeilhtG6ohCl0zhiLwvk52RLXXKSidELOVr7bJSwFeMlqKdGDcBYO2xOuXitanLih5xSgji7WCFvSTAmoz1u7HZ7T8uGfcXAYNcedgs8MV5PJtVHBi9jbmGNZKhn5Bdwis53k354tSuR7uG6BGV93HTdJElIVKCnL665DjbjNMut5R59pYU4rInBBwkSykbwK7xQCyNupC5FaPyCxFb1AkmIuAJvEtNl4NFDlEObfZGAlPCELy8vJ6FJUdjamrjKpLYvlPTCX8pvNfDZ6VJlv7q5OuKUBo22i4SWXmcRhilud9l15TZhODKo9fXQFdoQKBgQD9VDRSEXz7/zMOMtl1yPRdaFFht11V7fhdfAZ0Ue9OjlCapLvdzkegq/sxBWV6i4od09oSJhCHBCLGqGahKOEqMXI5EM7oscbrhrWTL6Pvwr3O47LiYLKdysU+BRpXeoFw0c7PLaPkQEjyWLoAtejegxulNJF36XWWc3XStvoZSQKBgQCtj47FhkKObCqVXOoUrLuahhKUNU20j2s+5/dHl1Bq3/qCnAfI3DIVukKmx7StSnfVoiVZgU8ST9ojwjspY+zIQM7RORv7DuZEgnZXGRzm9ezCCeE2LFZdKnjbc+sjhNWrfvl1gYZFP3eACSWmraTMYASK7OXHSeDARxERaoRNdQJ/Q0oam7IJgOZIqXzYRnEad7U96LbMFAch8bMAA+W/qx4iSvF29XPVktgb4OmOCv8FsOaVBj0WJsbyddJFcIlrRcncjUvpqeWO8QDXOkFtSkJwKIRS/86vozo5KkAwvwvLVixkwqNc5UQuHDF1NrsbH/+zWC6edTuOAGiq4023IQKBgFNBwE1EoHzNqofH8IpGaiAwHI14HHR3MA0XHWn8ThlWpEcvLhTOfEAr/3kd57ARPvb+N0h+XT39jDkHHXY3dwiA/jUjXibK+O74XmhdpZ2tbwuNzbk8/5jlnOA49R0uxYjk+inCHnTtgxeqS6NvPNTFYqh6B6fX6raR4MAoBptZAoGBAIpQco7dqjV9PSP/6t8HKXIlU2ZVN/lAvkV2FlAjpB2ZJt2gYAv4XLWYycpkLSzXT9t0GLCdUc8SXWmaHI6wHGXpiXccg+31dxrvN4w5a54EPMb12us0SFMRc5mczNsRKxrBCluNkkfKUplGMGBB+TpuoeYs1gcMT+Ql4VAZuZ6D",
				"json", "utf-8",
				"MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAq7/2rNtW8/8zArv8mBdSBYGKQAcjsgM7EIpxwnNbOZ06SpfKMlIrmjXWlI1PTTynICElgrqTdnnG01uBI4g9EdlMA9aoAnmBOKTEUGosF+J/1en5TOhYU4xB0wX9wNJYGh6i3dzyOBdeSg/dTzWOfkWKVYlXN2Wf5T5bdD6PVWOVzKt0ODPRVVXfFrTsFswTYkWp6vIvb32Aw3l5nDSp+iWm3+E4DrS9QLwYCDFsn5A5ivYtmeXRBqhpYJsU+jgIWjxueA1atmT88XWa4Br0aeeoql1fD8YbO7UvAqw3YsQ/U/KiArQ1WA8+l5ts3PSuqwNoWF3oh6kDJ6idHYyDXQIDAQAB/6FTFY99uhpiq0qadD/uSzQsefWo0aTvP/65zi3eof7TcZ32oWpwIDAQAB",
				"RSA");
		AlipayTradeAppPayRequest request = new AlipayTradeAppPayRequest();
		request.setBizContent(JSON.toJSONString(initParams));
		try {

			AlipayTradeAppPayResponse response = alipayClient.pageExecute(request);

			if (response.isSuccess()) {

				System.out.println("调用成功");
				System.out.println(response.getBody());
				System.out.println(response.getCode());
				System.out.println(response.getMsg());
				System.out.println(response.getOutTradeNo());
				System.out.println(response.getSellerId());
				System.out.println(response.getSubCode());
				System.out.println(response.getSubMsg());
				System.out.println(response.getTotalAmount());
				System.out.println(JSON.toJSONString(response.getParams()));
			} else {

				System.out.println("调用失败");
			}
		} catch (AlipayApiException e) {

			e.printStackTrace();
		}
	}
}