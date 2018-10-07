package com.zeng.ezsh.wy.utils;

import java.security.Key;
import java.util.Date;
import java.util.Map;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.slf4j.LoggerFactory;

import com.zeng.ezsh.wy.entity.ResidentialUser;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
/**
 * @description token操作类
 *
 * @author qwc
 */
public class AccessTokenUtil {
	private static Logger log = Logger.getLogger(AccessTokenUtil.class);

	/**
	 * @description 生成token签名秘钥使用HS256算法和Secret生成signKey
	 *
	 * @auhtor qwc 2017年8月1日下午9:31:39
	 * @return Key
	 */
	private static Key getKeyInstance() {
		SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
		byte[] apiKeySecretBytes = DatatypeConverter
				.parseBase64Binary("adfasjdkfjasld02388w8*@osfdsfasodfajsd");
		Key signingKey = new SecretKeySpec(apiKeySecretBytes,
				signatureAlgorithm.getJcaName());
		return signingKey;
	}

	/**
	 * @description Token生成方法 使用HS256签名算法和生成的signingKey最终的Token,claims中是有效载荷
	 *
	 * @auhtor qwc 2017年8月1日下午9:30:15
	 * @param claims
	 * @param minutes
	 * @return String
	 */
	public static String getAccessToken(Map<String, Object> claims,
			int minutes) {
		// int minutes=60*1000;
		long nowMillis = System.currentTimeMillis();// 系统当前时间
		Date now = new Date(nowMillis + 60 * 1000 * minutes);// 设置accessToken有效期
		return Jwts.builder().setIssuer("ezsh").setClaims(claims)
				.setExpiration(now)
				.signWith(SignatureAlgorithm.HS256, getKeyInstance()).compact();
	}

	/**
	 * @description Token验证方法，解析Token的同时验证token,验证失败返回 null
	 *
	 * @auhtor qwc 2017年8月1日下午9:29:54
	 * @param accessToken
	 * @return Map<String,Object>
	 */
	public static Map<String, Object> parserAccessTokenToMap(
			String accessToken) {
		try {
			Map<String, Object> jwtClaims = Jwts.parser()
					.setSigningKey(getKeyInstance()).parseClaimsJws(accessToken)
					.getBody();
			return jwtClaims;
		} catch (Exception e) {
			log.error("Token验证失败!");
			return null;
		}
	}

	/**
	 * @description Token验证方法，解析Token的同时验证token,验证失败返回null
	 *
	 * @auhtor qwc qwc 2017年8月1日下午9:29:54
	 * @param accessToken
	 * @return ResidentialUser
	 */
	public static ResidentialUser parserAccessTokenToModel(String accessToken) {
		try {
			Map<String, Object> jwtClaims = Jwts.parser()
					.setSigningKey(getKeyInstance()).parseClaimsJws(accessToken)
					.getBody();
			if (jwtClaims.containsKey("residentialUser")) {
				JSONObject json = JSONObject
						.fromObject(jwtClaims.get("residentialUser"));
				System.out.println("json" + json.toString());
				ResidentialUser residentialUserModel = (ResidentialUser) JSONObject
						.toBean(json, ResidentialUser.class);
				System.out.println("json1" + JSONObject
						.fromObject(residentialUserModel).toString());
				return residentialUserModel;
			} else {
				return null;
			}
		} catch (Exception e) {
			log.error("Token验证失败!");
			return null;
		}
	}
}
