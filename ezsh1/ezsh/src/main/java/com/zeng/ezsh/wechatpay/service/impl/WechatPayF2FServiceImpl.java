package com.zeng.ezsh.wechatpay.service.impl;

import java.util.Iterator;
import java.util.Map;

import org.springframework.stereotype.Service;

import sun.print.resources.serviceui;

import com.github.wxpay.sdk.WXPayConstants.SignType;
import com.zeng.ezsh.wechatpay.config.WechatPayConfig;
import com.zeng.ezsh.wechatpay.service.WechatPayF2FService;
import com.zeng.ezsh.wechatpay.uitls.MicropayReqParam;
import com.zeng.ezsh.wechatpay.uitls.WXPayUtil;
import com.zeng.ezsh.wechatpay.uitls.WxPayRequestUtil;
import com.zeng.ezsh.wy.entity.ParkRecord;
@Service
public class WechatPayF2FServiceImpl implements WechatPayF2FService {

	@Override
	public String ParkingCostByBarCode(Map<String, Object> addtionsMap) {
		MicropayReqParam param = new MicropayReqParam();
		
		ParkRecord parkRecord = (ParkRecord) addtionsMap.get("parkRecord");
		String money = (String) addtionsMap.get("money");
		String payCode = (String) addtionsMap.get("payCode");
		String ip = (String) addtionsMap.get("ip");
	
		param.setBody(WechatPayConfig.APPNAME+"-停车费");
		param.setSpbill_create_ip(ip);
		param.setTotal_fee(1);//金额 单位（分）
		param.setOut_trade_no(parkRecord.getOrderNum());
		param.setAuth_code(payCode);
		
		try {
			Map<String, String> resultMap = WxPayRequestUtil.micropayReqF2F(param.gtReqMap());
			for (Map.Entry<String, String> entry : resultMap.entrySet()) {
				System.out.println("------------>Key:"+entry.getKey()+"--value:"+entry.getValue());
			}
			
			if (resultMap.get("return_code").equals("SUCCESS")) { //返回状态码
	
				if (WXPayUtil.isSignatureValid(resultMap, WechatPayConfig.KEY,
						 SignType.HMACSHA256)) {// 进行验签
					if(resultMap.get("result_code").equals("SUCCESS")){
						return "SUCCESS";
					}else{
						return "TRADEERROR";
					}
				} else {
					return "SIGNERROR";
				}
			} else {
				return resultMap.get("err_code");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "未知错误";
	}

}
