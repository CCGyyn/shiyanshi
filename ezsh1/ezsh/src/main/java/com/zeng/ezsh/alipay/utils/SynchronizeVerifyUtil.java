package com.zeng.ezsh.alipay.utils;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringWriter;
import java.security.KeyFactory;
import java.security.PublicKey;
import java.security.spec.X509EncodedKeySpec;
import java.util.logging.Logger;
import org.apache.commons.lang3.StringUtils;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayConstants;
import com.alipay.api.internal.util.StreamUtil;

import com.alipay.api.internal.util.codec.Base64;
/**
 * @description 支付宝交易数字签名验签工具
 *
 * @author qwc
 */
public class SynchronizeVerifyUtil {
	private static Logger logger = Logger.getLogger("SynchronizeVerifyUtil");

	/**
	 * @description 同步验证签名
	 *
	 * @param content 原始字符串
	 * @param sign 签名
	 * @param publicKey 支付宝公钥
	 * @param charset 字符集
	 * @param signType 签名类型
	 * @return boolean
	 * @throws AlipayApiException
	 */
	public static boolean rsaCheck(String content, String sign,
			String publicKey, String charset, String signType)
			throws AlipayApiException {

		if (AlipayConstants.SIGN_TYPE_RSA.equals(signType)) {

			return rsaCheckContent(content, sign, publicKey, charset);

		} else if (AlipayConstants.SIGN_TYPE_RSA2.equals(signType)) {

			return rsa256CheckContent(content, sign, publicKey, charset);

		} else {

			throw new AlipayApiException(
					"Sign Type is Not Support : signType=" + signType);
		}

	}

	/**
	 * @description RSA算法验证签名
	 *
	 * @param content
	 * @param sign
	 * @param publicKey
	 * @param charset
	 * @return boolean
	 * @throws AlipayApiException
	 */
	public static boolean rsaCheckContent(String content, String sign,
			String publicKey, String charset) throws AlipayApiException {
		try {
			PublicKey pubKey = getPublicKeyFromX509("RSA",
					new ByteArrayInputStream(publicKey.getBytes()));

			java.security.Signature signature = java.security.Signature
					.getInstance(AlipayConstants.SIGN_ALGORITHMS);

			signature.initVerify(pubKey);

			if (StringUtils.isEmpty(charset)) {
				signature.update(content.getBytes());
			} else {
				signature.update(content.getBytes(charset));
			}

			return signature.verify(Base64.decodeBase64(sign.getBytes()));
		} catch (Exception e) {
			throw new AlipayApiException("RSAcontent = " + content + ",sign="
					+ sign + ",charset = " + charset, e);
		}
	}

	/**
	 * @description RSA2 算法验证签名
	 *
	 * @param content
	 * @param sign
	 * @param publicKey
	 * @param charset
	 * @return boolean
	 * @throws AlipayApiException
	 */
	public static boolean rsa256CheckContent(String content, String sign,
			String publicKey, String charset) throws AlipayApiException {
		try {
			PublicKey pubKey = getPublicKeyFromX509("RSA",
					new ByteArrayInputStream(publicKey.getBytes()));

			java.security.Signature signature = java.security.Signature
					.getInstance(AlipayConstants.SIGN_SHA256RSA_ALGORITHMS);

			signature.initVerify(pubKey);

			if (StringUtils.isEmpty(charset)) {
				signature.update(content.getBytes());
			} else {
				signature.update(content.getBytes(charset));
			}

			return signature.verify(Base64.decodeBase64(sign.getBytes()));
		} catch (Exception e) {
			throw new AlipayApiException("RSAcontent = " + content + ",sign="
					+ sign + ",charset = " + charset, e);
		}
	}

	/**
	 * @description 获取公钥
	 *
	 * @param 2017年9月1日上午10:46:55
	 * @param ins
	 * @return PublicKey
	 * @throws Exception
	 */
	public static PublicKey getPublicKeyFromX509(String algorithm,
			InputStream ins) throws Exception {
		KeyFactory keyFactory = KeyFactory.getInstance(algorithm);

		StringWriter writer = new StringWriter();
		StreamUtil.io(new InputStreamReader(ins), writer);

		byte[] encodedKey = writer.toString().getBytes();

		encodedKey = Base64.decodeBase64(encodedKey);

		return keyFactory.generatePublic(new X509EncodedKeySpec(encodedKey));
	}
}
