package com.github.maojx0630.common.utils.rsa;

import com.github.maojx0630.common.utils.Base64Utils;

import javax.crypto.Cipher;
import java.security.*;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

/**
 *
 * @author leon
 *
 */
@SuppressWarnings("all")
public class RsaUtils {

	/**
	 * 生成一堆密钥
	 * @return Rsa对象，保存一对密钥
	 */
	public static RsaEntity createKeyPair() {
		//使用RSA算法获得密钥对生成器对象keyPairGenerator
		try {
			KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
			keyPairGenerator.initialize(1024);
			//生成密钥对
			KeyPair keyPair = keyPairGenerator.generateKeyPair();
			//获取公钥
			PublicKey publicKey = keyPair.getPublic();
			//获取私钥
			PrivateKey privateKey = keyPair.getPrivate();
			return new RsaEntity(publicKey, privateKey);
		} catch (NoSuchAlgorithmException e) {
			return null;
		}
	}

	/**
	 * RSA加密
	 * @param str 要加密参数
	 * @param publicKey 公钥
	 * @return 密文
	 */
	public static String encryptWithRSA(String str, String publicKey) {

		try {
			//获取一个加密算法为RSA的加解密器对象cipher。
			Cipher cipher = Cipher.getInstance("RSA");
			//设置为加密模式,并将公钥给cipher。
			cipher.init(Cipher.ENCRYPT_MODE, getPublicKey(publicKey));
			//获得密文
			byte[] secret = cipher.doFinal(str.getBytes());
			//进行Base64编码
			return Base64.encode(secret);
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * RSA解密
	 * @param secret 密文参数
	 * @param privateKey 私钥
	 * @return 解密后字符串
	 */
	public static String decryptWithRSA(String secret, String privateKey) {
		try {
			Cipher cipher = Cipher.getInstance("RSA");
			//传递私钥，设置为解密模式。
			cipher.init(Cipher.DECRYPT_MODE, getPrivateKey(privateKey));
			//解密器解密由Base64解码后的密文,获得明文字节数组
			byte[] b = cipher.doFinal(Base64.decode(secret));
			//转换成字符串
			return new String(b);
		} catch (Exception e) {
			return null;
		}

	}

	/**
	 * 将公钥转化为公钥对象
	 */
	public static PublicKey getPublicKey(String key) {
		try {
			byte[] keyBytes = Base64Utils.decode(key);
			X509EncodedKeySpec keySpec = new X509EncodedKeySpec(keyBytes);
			KeyFactory keyFactory = KeyFactory.getInstance("RSA");
			return keyFactory.generatePublic(keySpec);
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * 将私钥转化为私钥对象
	 */
	public static PrivateKey getPrivateKey(String key) {
		try {
			byte[] keyBytes = Base64Utils.decode(key);
			PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(keyBytes);
			KeyFactory keyFactory = KeyFactory.getInstance("RSA");
			return keyFactory.generatePrivate(keySpec);
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * RSA签名
	 *
	 * @param content 待签内容
	 * @param privatekey 私钥
	 * @return 签名
	 */
	public static String sign(String content, String privatekey) {
		//用私钥对信息生成数字签名
		try {
			Signature stool = Signature.getInstance("MD5WithRSA");
			stool.initSign(getPrivateKey(privatekey));
			stool.update(content.getBytes("UTF-8"));
			byte[] data = stool.sign();
			return Base64.encode(data);
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * 校验签名
	 *
	 * @param content 待验内容
	 * @param signature 签名
	 * @param publickey 公钥
	 * @return 是否有效签名
	 */
	public static boolean verify(String content, String signature, String publickey) {
		try {
			Signature stool = Signature.getInstance("MD5WithRSA");
			stool.initVerify(getPublicKey(publickey));
			stool.update(content.getBytes("UTF-8"));
			//验证签名是否正常
			return stool.verify(Base64.decode(signature));
		} catch (Exception e) {
			return false;
		}
	}
}