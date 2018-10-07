package com.zeng.ezsh.wechatpay.uitls;

import java.util.HashMap;
import java.util.Map;

import org.aspectj.weaver.AjAttribute.PrivilegedAttribute;

public class OrderqueryReqParam {
	// 应用APPID
	private String appid;

	// 商户号
	private String mch_id;

	// 微信订单号
	private String transaction_id;

	// 商户订单号
	private String out_trade_no;

	// 随机字符串
	private String nonce_str;

	public String getAppid() {
		return appid;
	}

	public void setAppid(String appid) {
		this.appid = appid;
	}

	public String getMch_id() {
		return mch_id;
	}

	public void setMch_id(String mch_id) {
		this.mch_id = mch_id;
	}

	public String getTransaction_id() {
		return transaction_id;
	}

	public void setTransaction_id(String transaction_id) {
		this.transaction_id = transaction_id;
	}

	public String getOut_trade_no() {
		return out_trade_no;
	}

	public void setOut_trade_no(String out_trade_no) {
		this.out_trade_no = out_trade_no;
	}

	public String getNonce_str() {
		return nonce_str;
	}

	public void setNonce_str(String nonce_str) {
		this.nonce_str = nonce_str;
	}

	public Map<String, String> gtReqMap() {
		Map<String, String> retMap = new HashMap<String, String>();

		if (this.appid != null) {
			retMap.put("appid", this.appid);
		}

		if (this.mch_id != null) {
			retMap.put("partnerid", this.mch_id);
		}

		// 微信订单号 和 商户订单号 二选一
		if (this.transaction_id != null) {
			retMap.put("transaction_id", this.transaction_id);
		} else if (this.out_trade_no != null) {
			retMap.put("out_trade_no", this.out_trade_no);
		}

		retMap.put("noncestr", WXPayUtil.generateNonceStr());

		return retMap;
	}
}
