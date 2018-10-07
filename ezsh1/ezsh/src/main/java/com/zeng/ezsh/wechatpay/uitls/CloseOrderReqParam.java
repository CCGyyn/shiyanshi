package com.zeng.ezsh.wechatpay.uitls;

import java.util.HashMap;
import java.util.Map;

public class CloseOrderReqParam {
	public String appid;
	
	public String mch_id;
	
	public String out_trade_no;
	
	public String nonce_str;

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
	
	public Map<String, String> gtReqMap(){
		Map<String, String> reqMap = new HashMap<String,String>();
		reqMap.put("appid", this.appid);
		reqMap.put("mch_id", this.mch_id);
		reqMap.put("out_trade_no", this.out_trade_no);
		reqMap.put("nonce_str", this.nonce_str);
		return reqMap;
	}
}
