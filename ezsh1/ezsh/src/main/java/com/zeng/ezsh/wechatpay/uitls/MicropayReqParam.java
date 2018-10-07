package com.zeng.ezsh.wechatpay.uitls;

import java.util.HashMap;
import java.util.Map;

import com.zeng.ezsh.wechatpay.config.WechatPayConfig;

public class MicropayReqParam {
	
	public String appid; // 应用ID

	public String mch_id; // 商户号

	public String device_info; // 设备号

	public String nonce_str; // 随机字符串

	public String sign; // 签名

	public String sign_type; // 签名类型

	public String body; // 商品描述

	public String detail; // 商品详情

	public String attach; // 附加数据

	public String out_trade_no; // 商户订单号

	public String fee_type; // 货币类型

	public Integer total_fee; // 总金额

	public String spbill_create_ip; // 终端IP

	public String goods_tag; // 订单优惠标记
	
	public String limit_pay; // 指定支付方式

	public String time_start; // 交易起始时间

	public String time_expire; // 交易结束时间

	public String auth_code; // 交易类型

	public String scene_info; // 场景信息

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

	public String getDevice_info() {
		return device_info;
	}

	public void setDevice_info(String device_info) {
		this.device_info = device_info;
	}

	public String getNonce_str() {
		return nonce_str;
	}

	public void setNonce_str(String nonce_str) {
		this.nonce_str = nonce_str;
	}

	public String getSign() {
		return sign;
	}

	public void setSign(String sign) {
		this.sign = sign;
	}

	public String getSign_type() {
		return sign_type;
	}

	public void setSign_type(String sign_type) {
		this.sign_type = sign_type;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	public String getAttach() {
		return attach;
	}

	public void setAttach(String attach) {
		this.attach = attach;
	}

	public String getOut_trade_no() {
		return out_trade_no;
	}

	public void setOut_trade_no(String out_trade_no) {
		this.out_trade_no = out_trade_no;
	}

	public String getFee_type() {
		return fee_type;
	}

	public void setFee_type(String fee_type) {
		this.fee_type = fee_type;
	}

	public Integer getTotal_fee() {
		return total_fee;
	}

	public void setTotal_fee(Integer total_fee) {
		this.total_fee = total_fee;
	}

	public String getSpbill_create_ip() {
		return spbill_create_ip;
	}

	public void setSpbill_create_ip(String spbill_create_ip) {
		this.spbill_create_ip = spbill_create_ip;
	}

	public String getGoods_tag() {
		return goods_tag;
	}

	public void setGoods_tag(String goods_tag) {
		this.goods_tag = goods_tag;
	}

	public String getLimit_pay() {
		return limit_pay;
	}

	public void setLimit_pay(String limit_pay) {
		this.limit_pay = limit_pay;
	}

	public String getTime_start() {
		return time_start;
	}

	public void setTime_start(String time_start) {
		this.time_start = time_start;
	}

	public String getTime_expire() {
		return time_expire;
	}

	public void setTime_expire(String time_expire) {
		this.time_expire = time_expire;
	}

	public String getAuth_code() {
		return auth_code;
	}

	public void setAuth_code(String auth_code) {
		this.auth_code = auth_code;
	}

	public String getScene_info() {
		return scene_info;
	}

	public void setScene_info(String scene_info) {
		this.scene_info = scene_info;
	}

	public MicropayReqParam() {
		super();
		this.appid = WechatPayConfig.APP_ID;
		this.mch_id = WechatPayConfig.PARTNERID;
		this.nonce_str = WXPayUtil.generateNonceStr();
		this.sign_type = WechatPayConfig.HMACSHA256;	
	}
	
	public Map<String, String> gtReqMap() {
		Map<String, String> reqMap = new HashMap<String, String>();
		if (appid != null) {
			reqMap.put("appid", this.appid);
		}
		
		if (mch_id != null) {
			reqMap.put("mch_id", this.mch_id);
		}
		
		if (device_info != null) {
			reqMap.put("device_info", this.device_info);
		}
		
		if (nonce_str != null) {
			reqMap.put("nonce_str", this.nonce_str);
		}
		
		if (sign != null) {
			reqMap.put("sign", this.sign);
		}

		if (sign_type != null) {
			reqMap.put("sign_type", this.sign_type);
		}
		
		if (body != null) {
			reqMap.put("body", this.body);
		}
		
		if (detail != null) {
			reqMap.put("detail", this.detail);
		}
		
		if (attach != null) {
			reqMap.put("attach", this.attach);
		}
		
		if (out_trade_no != null) {
			reqMap.put("out_trade_no", this.out_trade_no);
		}
		
		if (fee_type != null) {
			reqMap.put("fee_type", this.fee_type);
		}
		
		if (total_fee != null) {
			reqMap.put("total_fee", String.valueOf(this.total_fee));
		}
		
		if (spbill_create_ip != null) {
			reqMap.put("spbill_create_ip", this.spbill_create_ip);
		}

		if (time_start != null) {
			reqMap.put("time_start", this.time_start);
		}
		
		if (time_expire != null) {
			reqMap.put("time_expire", this.time_expire);
		}

		if (goods_tag != null) {
			reqMap.put("goods_tag", this.goods_tag);
		}
					
		if (auth_code != null) {
			reqMap.put("auth_code", this.auth_code);
		}

		if (limit_pay != null) {
			reqMap.put("limit_pay", this.limit_pay);
		}
		
		if (limit_pay != null) {
			reqMap.put("scene_info", this.scene_info);
		}
		return reqMap;
	}
	
}
