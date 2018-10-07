package com.zeng.ezsh.wechatpay.service.impl;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.zeng.ezsh.wechatpay.config.WechatPayConfig;
import com.zeng.ezsh.wechatpay.service.WechatPayH5Service;
import com.zeng.ezsh.wechatpay.uitls.UnifiedorderReqParam;
import com.zeng.ezsh.wechatpay.uitls.WXPayUtil;
import com.zeng.ezsh.wechatpay.uitls.WxPayRequestUtil;
import com.zeng.ezsh.wy.dao.PlateManagementMapper;
import com.zeng.ezsh.wy.entity.ParkRecord;
import com.zeng.ezsh.wy.utils.SerializeUtil;
@Service
public class WeChatPayH5ServiceImpl implements WechatPayH5Service {
	
	private static Logger logger = LoggerFactory
			.getLogger(WeChatPayH5ServiceImpl.class);
	
	@Resource
	PlateManagementMapper plateManagementMapper;
	
	@Override
	public Map<String, String> parkingPayOfH5(Map<String, Object> additionMap) {
		Map<String, String> retMap;
		ParkRecord parkRecord = (ParkRecord) additionMap.get("parkRecord");
		//获取订单号
		String out_trade_no = SerializeUtil.OrderSerialize();
		String openid = (String) additionMap.get("openId");
		logger.debug("openid:"+openid);
		parkRecord.setOrderNum(out_trade_no);	//订单号
		double money = parkRecord.getSupposeParkCost();//应付金额
		int tempMoney=1;
		//将订单号存进数据库
		System.err.println("parkRecord:"+parkRecord);
		plateManagementMapper.updateParkRecord(parkRecord);
		
		//参数封装
		UnifiedorderReqParam unifiedorderReqParam = new UnifiedorderReqParam();
		
		unifiedorderReqParam.setAppid(WechatPayConfig.APP_ID_JSAPI);			// 设置应用ID,必填
		unifiedorderReqParam.setMch_id(WechatPayConfig.PARTNERID_H5);			// 设置商户号,必填
		unifiedorderReqParam.setNonce_str(WXPayUtil.generateNonceStr()); 		// 设置随机字符串,必填
		unifiedorderReqParam.setBody(WechatPayConfig.APPNAME + "-" + "停车费");	// 设置商品描述,必填
		unifiedorderReqParam.setOut_trade_no(out_trade_no);						// 设置商户订单号
		unifiedorderReqParam.setTotal_fee(tempMoney);							// 设置总金额,单位为分
		unifiedorderReqParam
				.setSpbill_create_ip(additionMap.get("realIp").toString());		// 设置终端IP，必填
		unifiedorderReqParam.setSign_type("MD5"); 						// 设置签名类型，选填
		unifiedorderReqParam
				.setNotify_url(WechatPayConfig.PARKING_PAY_NOTIFY_URL); 		// 设置异步通知回调地址，选填
		unifiedorderReqParam.setTrade_type("JSAPI");							// 设置交易类型
		unifiedorderReqParam.setOpenid(openid);
//		unifiedorderReqParam.setScene_info("{\"h5_info\": {\"type\":\"WAP\",\"wap_url\": \"http://www.baidu.com\",\"wap_name\": \"e众社区停车费\"}");
		//		{"h5_info": {"type":"WAP","wap_url": "http://","wap_name": "e众社区停车费"}}
		try{
			System.out.println("unifiedorderReqParam.gtReqMap():"+unifiedorderReqParam.gtReqMap());
			retMap = WxPayRequestUtil.unifiedorderReqH5(unifiedorderReqParam.gtReqMap());
			if(retMap==null){
				retMap=new HashMap<String, String>();
				return retMap;
			}else{
				return retMap;
			}
		}catch(Exception ex){
			ex.printStackTrace();
			
			return null;
		}
	}

}
