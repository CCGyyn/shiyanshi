package com.zeng.ezsh.alipay.utils;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

import com.zeng.ezsh.alipay.config.AlipayConfig;

import net.sf.json.JSONObject;
/**
 * @description 支付宝支付请求参数中的业务参数实体类
 *
 * @author qwc
 */
public class BizContent {
	private String body;

	private String subject;

	private String out_trade_no;

	private String timeout_express;

	private String total_amount;

	private String product_code;

	private String scene;
	
	private String auth_code;
	
	private String seller_id;
	
	public String getScene() {
		return scene;
	}

	public void setScene(String scene) {
		this.scene = scene;
	}

	public String getAuth_code() {
		return auth_code;
	}

	public void setAuth_code(String auth_code) {
		this.auth_code = auth_code;
	}

	public String getSeller_id() {
		return seller_id;
	}

	public void setSeller_id(String seller_id) {
		this.seller_id = seller_id;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getOut_trade_no() {
		return out_trade_no;
	}

	public void setOut_trade_no(String out_trade_no) {
		this.out_trade_no = out_trade_no;
	}

	public String getTimeout_express() {
		return timeout_express;
	}

	public void setTimeout_express(String timeout_express) {
		this.timeout_express = timeout_express;
	}

	public String getTotal_amount() {
		return total_amount;
	}

	public void setTotal_amount(String total_amount) {
		this.total_amount = total_amount;
	}

	public String getProduct_code() {
		return product_code;
	}

	public void setProduct_code(String product_code) {
		this.product_code = product_code;
	}

	public String gt_biz_content() {
		Map<String, String> retMap = new HashMap<String, String>();
		retMap.put("body", this.body);
		retMap.put("out_trade_no", this.out_trade_no);
		retMap.put("product_code", "QUICK_MSECURITY_PAY");
		retMap.put("subject", this.subject);
		retMap.put("timeout_express", this.timeout_express);
		retMap.put("total_amount", this.total_amount);
		Map<String, String> sortMap = new TreeMap<String, String>();
		sortMap.putAll(retMap);
		return JSONObject.fromObject(sortMap).toString();
	}
	
	public String getBizContentWap(){
		Map<String, String> retMap = new HashMap<String, String>();
		retMap.put("body", this.body);
		retMap.put("out_trade_no", this.out_trade_no);
		retMap.put("product_code", "QUICK_WAP_PAY");
		retMap.put("subject", this.subject);
		retMap.put("timeout_express", this.timeout_express);
		retMap.put("total_amount", this.total_amount);
		retMap.put("seller_id", this.seller_id);
		retMap.put("quit_url", AlipayConfig.QUIT_URL);
		Map<String, String> sortMap = new TreeMap<String, String>();
		sortMap.putAll(retMap);
		return JSONObject.fromObject(sortMap).toString();
	}
	
	public String getBizContent2(){
		Map<String, String> retMap = new HashMap<String, String>();
		retMap.put("body", this.body);
		retMap.put("out_trade_no", this.out_trade_no);
		retMap.put("product_code", "QUICK_MSECURITY_PAY");
		retMap.put("subject", this.subject);
		retMap.put("timeout_express", this.timeout_express);
		retMap.put("total_amount", this.total_amount);
		retMap.put("auth_code", this.auth_code);
		retMap.put("scene", this.scene);
		retMap.put("seller_id", this.seller_id);
		Map<String, String> sortMap = new TreeMap<String, String>();
		sortMap.putAll(retMap);
		return JSONObject.fromObject(sortMap).toString();
	}
}
