package com.zeng.ezsh.wechatpay.uitls;

import java.util.HashMap;
import java.util.Map;

public class WechatPayAsynRetJson {
	String return_code;
	
	String return_msg;

	public String getReturn_code() {
		return return_code;
	}

	public void setReturn_code(String return_code) {
		this.return_code = return_code;
	}

	public String getReturn_msg() {
		return return_msg;
	}

	public void setReturn_msg(String return_msg) {
		this.return_msg = return_msg;
	}
	
	public Map<String, String> gtRetMap(){
		Map<String,String> retMap = new HashMap<String,String>();
		retMap.put("return_code", this.return_code);
		retMap.put("return_msg", this.return_msg);
		return retMap;
	}
	
}
