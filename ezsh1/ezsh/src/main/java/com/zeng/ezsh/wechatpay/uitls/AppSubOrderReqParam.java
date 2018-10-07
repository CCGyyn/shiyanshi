package com.zeng.ezsh.wechatpay.uitls;

import java.util.HashMap;
import java.util.Map;

public class AppSubOrderReqParam {
	public String appid;

	public String partnerid;

	public String prepayid;

	public String noncestr;

	public String timestamp;

	public String signType;
	
	public String sign;

	public String getAppid() {
		return appid;
	}

	public void setAppid(String appid) {
		this.appid = appid;
	}

	public String getPartnerid() {
		return partnerid;
	}

	public void setPartnerid(String partnerid) {
		this.partnerid = partnerid;
	}

	public String getSignType() {
		return signType;
	}
	
	public void setSignType(String signType) {
		this.signType = signType;
	}
	
	public String getPrepayid() {
		return prepayid;
	}

	public void setPrepayid(String prepayid) {
		this.prepayid = prepayid;
	}

	public String getNoncestr() {
		return noncestr;
	}

	public void setNoncestr(String noncestr) {
		this.noncestr = noncestr;
	}

	public String getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}

	public String getSign() {
		return sign;
	}

	public void setSign(String sign) {
		this.sign = sign;
	}

	public Map<String, String> gtAppReqMap() {
		Map<String, String> retMap = new HashMap<String, String>();

		if (this.appid != null) {
			retMap.put("appid", this.appid);
		}

		if (this.partnerid != null) {
			retMap.put("partnerid", this.partnerid);
		}

		if (this.prepayid != null) {
			retMap.put("prepayid", this.prepayid);
		}

		retMap.put("package", "Sign=WXPay");

		retMap.put("noncestr", WXPayUtil.generateNonceStr());

		retMap.put("timestamp",
				String.valueOf(WXPayUtil.getCurrentTimestamp()));

		return retMap;
	}
	
	public Map<String, String> gtH5ReqMap(){
		Map<String , String> retMap = new HashMap<>();
		
		if (this.appid != null) {
			retMap.put("appId", this.appid);
		}

		if (this.partnerid != null) {
			retMap.put("partnerId", this.partnerid);
		}

		if (this.prepayid != null) {
			retMap.put("package", "prepay_id="+this.prepayid);
		}
		
		retMap.put("signType", this.signType);
		
		retMap.put("nonceStr", WXPayUtil.generateNonceStr());

		retMap.put("timeStamp",
				String.valueOf(WXPayUtil.getCurrentTimestamp()));

		return retMap;
	}
}
