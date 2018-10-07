package com.zeng.ezsh.alipay.utils;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.collections.map.HashedMap;

import com.zeng.ezsh.alipay.config.AlipayConfig;
import com.zeng.ezsh.wy.utils.DateUtil;
import com.zeng.ezsh.wy.utils.JsonUtil;

/**
 * @description 支付宝支付请求参数中的公共请求参数实体类
 * 
 * @author qwc
 */
public class AlipayTradeAppOrderInfoUtil {
	private String app_id;

	private String charset;

	private String format;

	private String method;

	private String return_url;
	
	private String notify_url;

	private String sign_type;

	private String timestamp;

	private String version;

	public String getApp_id() {
		return app_id;
	}

	public void setApp_id(String app_id) {
		this.app_id = app_id;
	}

	public String getCharset() {
		return charset;
	}

	public void setCharset(String charset) {
		this.charset = charset;
	}

	public String getFormat() {
		return format;
	}

	public void setFormat(String format) {
		this.format = format;
	}

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	public String getNotify_url() {
		return notify_url;
	}

	public void setNotify_url(String notify_url) {
		this.notify_url = notify_url;
	}

	public String getSign_type() {
		return sign_type;
	}

	public void setSign_type(String sign_type) {
		this.sign_type = sign_type;
	}

	public String getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getReturn_url() {
		return return_url;
	}

	public void setReturn_url(String return_url) {
		this.return_url = return_url;
	}

	public String gtAlipayTradeAppPayReqOrderInfo(BizContent bizContent) {
		this.version = "1.0";
		this.timestamp = DateUtil
				.dateToStr(new Date(), DateUtil.DATE_TIME_LINE);
		this.app_id = AlipayConfig.APPID;
		this.sign_type = "RSA2";
		this.method = "alipay.trade.app.pay";
		this.format = "json";
		this.charset = "utf-8";
		String orderInfo = "app_id=" + this.app_id + "&biz_content="
				+ bizContent.gt_biz_content() + "&charset=" + this.charset
				+ "&method=" + this.method + "&notify_url=" + this.notify_url
				+ "&sign_type=" + this.sign_type + "&timestamp="
				+ this.timestamp + "&version=" + this.version;
		return orderInfo;
	}
	
	public String gtAlipayTradeWapPayReqOrderInfo(BizContent bizContent) {
		this.version = "1.0";
		this.timestamp = DateUtil
				.dateToStr(new Date(), DateUtil.DATE_TIME_LINE);
		this.app_id = AlipayConfig.APPID;
		this.sign_type = "RSA2";
		this.method = "alipay.trade.wap.pay";
		this.format = "json";
		this.charset = "utf-8";
		
//		Map<String, String> map = new HashMap<>();
//		map.put("app_id", this.app_id);
//		map.put("biz_content", bizContent.getBizContentWap());
//		map.put("charset", this.charset);
//		map.put("method", this.method);
//		map.put("notify_url", this.notify_url);
//		map.put("sign_type", this.sign_type);
//		map.put("timestamp", this.timestamp);
//		map.put("version", this.version);
//		String orderInfo = JsonUtil.map2json(map);
		String orderInfo = "app_id=" + this.app_id 
				+ "&biz_content="+ bizContent.getBizContentWap() 
				+ "&charset=" + this.charset
				+ "&method=" + this.method 
				+ "&notify_url=" + this.notify_url
				+ "&return_url="+this.return_url
				+ "&sign_type=" + this.sign_type 
				+ "&timestamp="+ this.timestamp + "&version=" + this.version;
		return orderInfo;
	}
	
	// 用于F2F支付
	public String gtAlipayTradeAppPayReqOrderInfo2(BizContent bizContent) {
		this.version = "1.0";
		this.timestamp = DateUtil
				.dateToStr(new Date(), DateUtil.DATE_TIME_LINE);
		this.app_id = AlipayConfig.APPID;
		this.sign_type = "RSA2";
		this.method = "alipay.trade.pay";
		this.format = "json";
		this.charset = "utf-8";
		String orderInfo = "app_id=" + this.app_id + "&biz_content="
				+ bizContent.getBizContent2() + "&charset=" + this.charset
				+ "&method=" + this.method + "&notify_url=" + this.notify_url
				+ "&sign_type=" + this.sign_type + "&timestamp="
				+ this.timestamp + "&version=" + this.version;
		return orderInfo;
	}
}
