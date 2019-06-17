package cn.xuyingqi.security.model;

import java.io.File;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * security.xml
 * 
 * @author XuYQ
 *
 */
@XmlRootElement(name = "security")
@XmlAccessorType(XmlAccessType.FIELD)
public final class SecurityXml {

	/**
	 * 配置文件
	 */
	private static final String CONFIG_FILE = "security.xml";

	/**
	 * XML
	 */
	private static SecurityXml xml;

	/**
	 * AES
	 */
	@XmlElement(name = "AES", type = AES.class)
	private AES aes;
	/**
	 * DES
	 */
	@XmlElement(name = "DES", type = DES.class)
	private DES des;
	/**
	 * MAC
	 */
	@XmlElement(name = "MAC", type = MAC.class)
	private MAC mac;
	/**
	 * RSA
	 */
	@XmlElement(name = "RSA", type = RSA.class)
	private RSA rsa;

	/**
	 * 私有构造方法
	 */
	private SecurityXml() {

	}

	/**
	 * 获取third.party.xml配置实例
	 * 
	 * @return
	 */
	public static final synchronized SecurityXml getInstance() {

		if (xml == null) {

			try {

				JAXBContext jc = JAXBContext.newInstance(SecurityXml.class);
				Unmarshaller u = jc.createUnmarshaller();
				xml = (SecurityXml) u.unmarshal(
						new File(SecurityXml.class.getClassLoader().getResource(SecurityXml.CONFIG_FILE).getPath()));
			} catch (JAXBException e) {

				e.printStackTrace();
			}
		}

		return xml;
	}

	/**
	 * 获取AES
	 * 
	 * @return
	 */
	public AES getAes() {

		return this.aes;
	}

	/**
	 * 获取DES
	 * 
	 * @return
	 */
	public DES getDes() {

		return this.des;
	}

	/**
	 * 获取MAC
	 * 
	 * @return
	 */
	public MAC getMac() {

		return this.mac;
	}

	/**
	 * 获取RSA
	 * 
	 * @return
	 */
	public RSA getRsa() {

		return this.rsa;
	}

	/**
	 * AES
	 * 
	 * @author XuYQ
	 *
	 */
	public static final class AES {

		/**
		 * 密钥
		 */
		@XmlAttribute(name = "key")
		private String key;

		/**
		 * 获取密钥
		 * 
		 * @return
		 */
		public String getKey() {

			return this.key == null ? "" : this.key.trim();
		}
	}

	/**
	 * DES
	 * 
	 * @author XuYQ
	 *
	 */
	public static final class DES {

		/**
		 * 密钥
		 */
		@XmlAttribute(name = "key")
		private String key;

		/**
		 * 获取密钥
		 * 
		 * @return
		 */
		public String getKey() {

			return this.key == null ? "" : this.key.trim();
		}
	}

	/**
	 * MAC
	 * 
	 * @author XuYQ
	 *
	 */
	public static final class MAC {

		/**
		 * 密钥
		 */
		@XmlAttribute(name = "key")
		private String key;

		/**
		 * 获取密钥
		 * 
		 * @return
		 */
		public String getKey() {

			return this.key == null ? "" : this.key.trim();
		}
	}

	/**
	 * RSA
	 * 
	 * @author XuYQ
	 *
	 */
	public static final class RSA {

		/**
		 * 私钥
		 */
		@XmlAttribute(name = "privateKey")
		private String privateKey;

		/**
		 * 公钥
		 */
		@XmlAttribute(name = "publicKey")
		private String publicKey;

		/**
		 * 获取私钥
		 * 
		 * @return
		 */
		public String getPrivateKey() {

			return this.privateKey == null ? "" : this.privateKey.trim();
		}

		/**
		 * 获取公钥
		 * 
		 * @return
		 */
		public String getPublicKey() {

			return this.publicKey == null ? "" : this.publicKey.trim();
		}
	}

	/**
	 * Main函数测试
	 * 
	 * @param args
	 */
	public static void main(String[] args) {

		System.out.println(SecurityXml.getInstance().getAes().getKey());
		System.out.println(SecurityXml.getInstance().getDes().getKey());
		System.out.println(SecurityXml.getInstance().getMac().getKey());
		System.out.println(SecurityXml.getInstance().getRsa().getPrivateKey());
		System.out.println(SecurityXml.getInstance().getRsa().getPublicKey());
	}
}