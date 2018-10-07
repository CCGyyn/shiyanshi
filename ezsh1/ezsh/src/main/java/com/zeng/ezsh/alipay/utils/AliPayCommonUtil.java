package com.zeng.ezsh.alipay.utils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;
import java.util.logging.Logger;

import org.apache.commons.lang3.StringUtils;

import com.alipay.api.AlipayApiException;
import com.zeng.ezsh.alipay.config.AlipayConfig;
/**
 * @description 支付宝支付公共工具类
 *
 * @author qwc
 */
public class AliPayCommonUtil {
	private static Logger logger = Logger.getLogger("AliPayCommonUtil");
	/**
	 * @description 请求字符串转化成Map对象
	 *
	 * @auhtor qwc 2018年1月9日 下午8:13:07
	 * @param param
	 * @return Map<String,Object>
	 */
	public static Map<String, Object> getUrlParams(String param) {
		Map<String, Object> map = new HashMap<String, Object>(0);
		if (StringUtils.isBlank(param)) { return map; }
		String[] params = param.split("&");
		for (int i = 0; i < params.length; i++) {
			String[] p = params[i].split("=");
			if (p.length == 2) {
				map.put(p[0], p[1]);
			}
		}
		return map;
	}

	/**
	 * @description 请求字符串的所有一级value进行encode
	 *
	 * @auhtor qwc 2018年1月9日 下午8:12:36
	 * @param map
	 * @param charset 编码字符集
	 * @return String
	 */
	public static String getUrlParamsByMap(Map<String, Object> map,
			String charset) {
		if (map == null) { return ""; }
		StringBuffer sb = new StringBuffer();
		for (Map.Entry<String, Object> entry : map.entrySet()) {
			try {
				sb.append(entry.getKey() + "=" + URLEncoder
						.encode(entry.getValue().toString(), charset));
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
			sb.append("&");
		}
		String s = sb.toString();
		if (s.endsWith("&")) {
			s = org.apache.commons.lang.StringUtils.substringBeforeLast(s, "&");
		}
		return s;
	}

	/**
	 * @description 请求字符串的所有一级value进行encode(支付宝SDK中的方法)
	 *
	 * @auhtor qwc 2018年1月9日 下午7:10:37
	 * @param params
	 * @param charset 编码字符集
	 * @return
	 * @throws IOException String
	 */
	public static String buildQuery(Map<String, String> params, String charset)
			throws IOException {
		if (params == null || params.isEmpty()) { return null; }

		StringBuilder query = new StringBuilder();
		Set<Entry<String, String>> entries = params.entrySet();
		boolean hasParam = false;

		for (Entry<String, String> entry : entries) {
			String name = entry.getKey();
			String value = entry.getValue();
			// 忽略参数名或参数值为空的参数
			if (StringUtils.isNotEmpty(name) && StringUtils.isNotEmpty(value)) {
				if (hasParam) {
					query.append("&");
				} else {
					hasParam = true;
				}
				query.append(name).append("=")
						.append(URLEncoder.encode(value, charset));
			}
		}
		return query.toString();
	}

	/**
	 * @description 获取提交至支付宝的订单
	 *
	 * @auhtor qwc 2018年1月9日 下午8:27:47
	 * @param reqContent 请求参数内容
	 * @return String
	 */
	public static String gtOrderInfo(String reqContent) {
		String sign = null;
		try {
			sign = SignUtil.rsa256Sign(reqContent,
					AlipayConfig.AliPayPrivateKey,
					AlipayConfig.AliPaySignCharset);
		} catch (AlipayApiException e) {
			e.printStackTrace();
		}
		logger.info("sign>>" + sign);
		System.out.println("orderInfo" + reqContent);
		Map<String, Object> reqMap = new HashMap<String, Object>();
		reqMap = getUrlParams(reqContent);
		reqMap.put("sign", sign);
		String reqEncodeString = getUrlParamsByMap(reqMap,
				AlipayConfig.AliPaySignCharset);
		return reqEncodeString;
	}
}
