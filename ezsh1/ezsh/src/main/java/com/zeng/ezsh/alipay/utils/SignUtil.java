package com.zeng.ezsh.alipay.utils;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayConstants;
import com.alipay.api.internal.util.StreamUtil;
import com.alipay.api.internal.util.StringUtils;
import com.alipay.api.internal.util.codec.Base64;
/**
 * @description 支付宝支付签名工具类
 *
 * @author qwc
 */
public class SignUtil {
	/**
	 * @description 签名
	 *
	 * @auhtor qwc 2018年2月10日 下午3:12:08
	 * @param content
	 * @param privateKey
	 * @param charset
	 * @param signType
	 * @return String
	 * @throws AlipayApiException
	 */
	public static String rsaSign(String content, String privateKey,
			String charset, String signType) throws AlipayApiException {

		if (AlipayConstants.SIGN_TYPE_RSA.equals(signType)) {

			return rsaSign(content, privateKey, charset);
		} else if (AlipayConstants.SIGN_TYPE_RSA2.equals(signType)) {

			return rsa256Sign(content, privateKey, charset);
		} else {

			throw new AlipayApiException(
					"Sign Type is Not Support : signType=" + signType);
		}

	}

	/**
	 * @description rsa2加签
	 *
	 * @param content
	 * @param privateKey
	 * @param charset
	 * @return String
	 * @throws AlipayApiException 
	 */
	public static String rsa256Sign(String content, String privateKey,
			String charset) throws AlipayApiException {

		try {
			PrivateKey priKey = getPrivateKeyFromPKCS8(
					AlipayConstants.SIGN_TYPE_RSA,
					new ByteArrayInputStream(privateKey.getBytes()));

			java.security.Signature signature = java.security.Signature
					.getInstance(AlipayConstants.SIGN_SHA256RSA_ALGORITHMS);

			signature.initSign(priKey);

			if (StringUtils.isEmpty(charset)) {
				signature.update(content.getBytes());
			} else {
				signature.update(content.getBytes(charset));
			}

			byte[] signed = signature.sign();

			return new String(Base64.encodeBase64(signed));
		} catch (Exception e) {
			throw new AlipayApiException(
					"RSAcontent = " + content + "; charset = " + charset, e);
		}

	}

	/**
	 * sha1WithRsa 加签
	 * 
	 * @param content
	 * @param privateKey
	 * @param charset
	 * @return
	 * @throws AlipayApiException
	 */
	public static String rsaSign(String content, String privateKey,
			String charset) throws AlipayApiException {
		try {
			PrivateKey priKey = getPrivateKeyFromPKCS8(
					AlipayConstants.SIGN_TYPE_RSA,
					new ByteArrayInputStream(privateKey.getBytes()));

			java.security.Signature signature = java.security.Signature
					.getInstance(AlipayConstants.SIGN_ALGORITHMS);

			signature.initSign(priKey);

			if (StringUtils.isEmpty(charset)) {
				signature.update(content.getBytes());
			} else {
				signature.update(content.getBytes(charset));
			}

			byte[] signed = signature.sign();

			return new String(Base64.encodeBase64(signed));
		} catch (InvalidKeySpecException ie) {
			throw new AlipayApiException("RSA私钥格式不正确，请检查是否正确配置了PKCS8格式的私钥", ie);
		} catch (Exception e) {
			throw new AlipayApiException(
					"RSAcontent = " + content + "; charset = " + charset, e);
		}
	}

	/**
	 * @description 获取PKCS8格式的私钥
	 *
	 * @param algorithm
	 * @param ins
	 * @return
	 * @throws Exception PrivateKey
	 */
	public static PrivateKey getPrivateKeyFromPKCS8(String algorithm,
			InputStream ins) throws Exception {
		if (ins == null || StringUtils.isEmpty(algorithm)) { return null; }

		KeyFactory keyFactory = KeyFactory.getInstance(algorithm);

		byte[] encodedKey = StreamUtil.readText(ins).getBytes();

		encodedKey = Base64.decodeBase64(encodedKey);

		return keyFactory.generatePrivate(new PKCS8EncodedKeySpec(encodedKey));
	}

}
