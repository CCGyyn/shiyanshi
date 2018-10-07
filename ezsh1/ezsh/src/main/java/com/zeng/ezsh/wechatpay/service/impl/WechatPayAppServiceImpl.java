package com.zeng.ezsh.wechatpay.service.impl;

import java.util.Date;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.zeng.ezsh.wechatpay.config.WechatPayConfig;
import com.zeng.ezsh.wechatpay.service.WechatPayAppService;
import com.zeng.ezsh.wechatpay.uitls.CloseOrderReqParam;
import com.zeng.ezsh.wechatpay.uitls.OrderqueryReqParam;
import com.zeng.ezsh.wechatpay.uitls.UnifiedorderReqParam;
import com.zeng.ezsh.wechatpay.uitls.WXPayUtil;
import com.zeng.ezsh.wechatpay.uitls.WxPayRequestUtil;
import com.zeng.ezsh.wy.dao.BerthOrderMapper;
import com.zeng.ezsh.wy.dao.PlateManagementMapper;
import com.zeng.ezsh.wy.entity.BenefitRecord;
import com.zeng.ezsh.wy.entity.BerthOrder;
import com.zeng.ezsh.wy.entity.ChargeInfo;
import com.zeng.ezsh.wy.entity.GoodsOrder;
import com.zeng.ezsh.wy.entity.ParkRecord;
import com.zeng.ezsh.wy.entity.UserTeacherFee;
import com.zeng.ezsh.wy.utils.DateUtil;
import com.zeng.ezsh.wy.utils.SerializeUtil;
@Service
public class WechatPayAppServiceImpl implements WechatPayAppService {
	
	@Resource
	PlateManagementMapper plateManagementMapper;
	@Resource
	BerthOrderMapper berthOrderMapper;
	/**
	 * @description 关闭微信支付订单
	 */
	@Override
	public boolean closeOrder(CloseOrderReqParam closeOrderReqParam) {
		try {
			Map<String, String> retMap = WxPayRequestUtil
					.closeOrderReq(closeOrderReqParam.gtReqMap());
			if (retMap != null) {
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	/**
	 * @description 查询微信支付交易订单
	 */
	@Override
	public boolean orderquery(OrderqueryReqParam orderqueryReqParam) {
		try {
			Map<String, String> retMap = WxPayRequestUtil
					.OrderqueryReq(orderqueryReqParam.gtReqMap());
			if (retMap != null) {
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	/**
	 * 商品微信支付
	 */
	@Override
	public Map<String, String> goodsPayment(GoodsOrder goodsOrder,
			String goodsName, Map<String, Object> additionMap) {
		// TODO Auto-generated method stub
		// SignType signType= SignType.HMACSHA256; // 设置签名类型
		UnifiedorderReqParam unifiedorderReqParam = new UnifiedorderReqParam();
		unifiedorderReqParam.setAppid(WechatPayConfig.APP_ID);// 设置应用ID,必填
		unifiedorderReqParam.setMch_id(WechatPayConfig.PARTNERID);// 设置商户号,必填
		unifiedorderReqParam.setNonce_str(WXPayUtil.generateNonceStr()); // 设置随机字符串,必填
		unifiedorderReqParam.setBody(WechatPayConfig.APPNAME + "-" + goodsName); // 设置商品描述,必填
		unifiedorderReqParam.setOut_trade_no(goodsOrder.getOrderSerialNum());// 设置商户订单号
		unifiedorderReqParam.setTotal_fee(1);// 设置总金额,单位为分goodsOrder.getTotalPrice().intValue()*
		unifiedorderReqParam
				.setSpbill_create_ip(additionMap.get("realIp").toString());// 设置终端IP，必填
		unifiedorderReqParam.setSign_type(WechatPayConfig.HMACSHA256); // 设置签名类型，选填
		unifiedorderReqParam
				.setNotify_url(WechatPayConfig.GOODS_PAY_NOTIFY_URL); // 设置异步通知回调地址，选填
		unifiedorderReqParam.setTrade_type("APP"); // 设置交易类型
		try {
			System.out.println("unifiedorderReqParam.getReqMap()"
					+ unifiedorderReqParam.gtReqMap());
			return WxPayRequestUtil
					.unifiedorderReqApp(unifiedorderReqParam.gtReqMap());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}

	}

	/**
	 * 物业费支付
	 */
	@Override
	public Map<String, String> PropertyCosts(ChargeInfo chargeInfo,
			String totalPrice, Map<String, Object> additionMap) {
		// TODO Auto-generated method stub
		UnifiedorderReqParam unifiedorderReqParam = new UnifiedorderReqParam();
		unifiedorderReqParam.setAppid(WechatPayConfig.APP_ID);// 设置应用ID,必填
		unifiedorderReqParam.setMch_id(WechatPayConfig.PARTNERID);// 设置商户号,必填
		unifiedorderReqParam.setNonce_str(WXPayUtil.generateNonceStr()); // 设置随机字符串,必填
		unifiedorderReqParam.setBody(WechatPayConfig.APPNAME + "-" + "物业费"); // 设置商品描述,必填
		unifiedorderReqParam.setOut_trade_no(additionMap.get("outTradeNo").toString());// 设置商户订单号
		unifiedorderReqParam.setTotal_fee(1);// 设置总金额,单位为分goodsOrder.getTotalPrice().intValue()*
		unifiedorderReqParam
				.setSpbill_create_ip(additionMap.get("realIp").toString());// 设置终端IP，必填
		unifiedorderReqParam.setSign_type("HMAC-SHA256"); // 设置签名类型，选填
		unifiedorderReqParam
				.setNotify_url(WechatPayConfig.PROPERTY_PAY_NOTIFY_URL); // 设置异步通知回调地址，选填
		unifiedorderReqParam.setTime_start(
				DateUtil.dateToStr(new Date(), DateUtil.DATE_TIME_NO_SLASH));// 设置订单创建时间
		unifiedorderReqParam
				.setTime_expire(DateUtil.gtTimeAfterMinutesUpToNow(30));// 设置订单失效时间为30分钟
		unifiedorderReqParam.setTrade_type("APP"); // 设置交易类型
		try {
			System.out.println("unifiedorderReqParam.getReqMap()"
					+ unifiedorderReqParam.gtReqMap());
			return WxPayRequestUtil
					.unifiedorderReqApp(unifiedorderReqParam.gtReqMap());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 家长缴纳家教费用
	 */
	@Override
	public Map<String, String> teacherCosts(UserTeacherFee record,
			String totalPrice, Map<String, Object> additionMap) {
		UnifiedorderReqParam unifiedorderReqParam = new UnifiedorderReqParam();
		unifiedorderReqParam.setAppid(WechatPayConfig.APP_ID);// 设置应用ID,必填
		unifiedorderReqParam.setMch_id(WechatPayConfig.PARTNERID);// 设置商户号,必填
		unifiedorderReqParam.setNonce_str(WXPayUtil.generateNonceStr()); // 设置随机字符串,必填
		unifiedorderReqParam.setBody(WechatPayConfig.APPNAME + "-" + "家教平台使用费"); // 设置商品描述,必填
		unifiedorderReqParam.setOut_trade_no(record.getOutTradeNo());// 设置商户订单号
		unifiedorderReqParam.setTotal_fee(1);// 设置总金额,单位为分goodsOrder.getTotalPrice().intValue()*
		unifiedorderReqParam
				.setSpbill_create_ip(additionMap.get("realIp").toString());// 设置终端IP，必填
		unifiedorderReqParam.setSign_type("HMAC-SHA256"); // 设置签名类型，选填
		unifiedorderReqParam
				.setNotify_url(WechatPayConfig.TEACHER_PAY_NOTIFY_URL); // 设置异步通知回调地址，选填
		unifiedorderReqParam.setTrade_type("APP"); // 设置交易类型
		try {
			System.out.println("unifiedorderReqParam.getReqMap()"
					+ unifiedorderReqParam.gtReqMap());
			return WxPayRequestUtil
					.unifiedorderReqApp(unifiedorderReqParam.gtReqMap());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 公益基金支付
	 */
	@Override
	public Map<String, String> benefitCosts(BenefitRecord record,
			String totalPrice, Map<String, Object> additionMap) {
		UnifiedorderReqParam unifiedorderReqParam = new UnifiedorderReqParam();
		unifiedorderReqParam.setAppid(WechatPayConfig.APP_ID);// 设置应用ID,必填
		unifiedorderReqParam.setMch_id(WechatPayConfig.PARTNERID);// 设置商户号,必填
		unifiedorderReqParam.setNonce_str(WXPayUtil.generateNonceStr()); // 设置随机字符串,必填
		unifiedorderReqParam.setBody(WechatPayConfig.APPNAME + "-" + "公益基金"); // 设置商品描述,必填
		unifiedorderReqParam.setOut_trade_no(record.getOutTradeNo());// 设置商户订单号
		unifiedorderReqParam.setTotal_fee(1);// 设置总金额,单位为分goodsOrder.getTotalPrice().intValue()*
		unifiedorderReqParam
				.setSpbill_create_ip(additionMap.get("realIp").toString());// 设置终端IP，必填
		unifiedorderReqParam.setSign_type("HMAC-SHA256"); // 设置签名类型，选填
		unifiedorderReqParam
				.setNotify_url(WechatPayConfig.BENEFIT_PAY_NOTIFY_URL); // 设置异步通知回调地址，选填
		unifiedorderReqParam.setTrade_type("APP"); // 设置交易类型
		try {
			System.out.println("unifiedorderReqParam.getReqMap()"
					+ unifiedorderReqParam.gtReqMap());
			return WxPayRequestUtil
					.unifiedorderReqApp(unifiedorderReqParam.gtReqMap());
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 	停车费支付
	 */
	@Override
	public Map<String, String> ParkingCharges(Map<String, Object> additionMap) {
		ParkRecord parkRecord = (ParkRecord) additionMap.get("parkRecord");
		//获取订单号
		String out_trade_no = SerializeUtil.OrderSerialize();
		parkRecord.setOrderNum(out_trade_no);	//订单号
		double money = parkRecord.getSupposeParkCost();//应付金额
		//将订单号存进数据库
		System.err.println("parkRecord:"+parkRecord);
		plateManagementMapper.updateParkRecord(parkRecord);
		
		//参数封装
		UnifiedorderReqParam unifiedorderReqParam = new UnifiedorderReqParam();
		
		unifiedorderReqParam.setAppid(WechatPayConfig.APP_ID);					// 设置应用ID,必填
		unifiedorderReqParam.setMch_id(WechatPayConfig.PARTNERID);				// 设置商户号,必填
		unifiedorderReqParam.setNonce_str(WXPayUtil.generateNonceStr()); 		// 设置随机字符串,必填
		unifiedorderReqParam.setBody(WechatPayConfig.APPNAME + "-" + "停车费");	// 设置商品描述,必填
		unifiedorderReqParam.setOut_trade_no(out_trade_no);						// 设置商户订单号
		unifiedorderReqParam.setTotal_fee(1);									// 设置总金额,单位为分
		unifiedorderReqParam
				.setSpbill_create_ip(additionMap.get("realIp").toString());		// 设置终端IP，必填
		unifiedorderReqParam.setSign_type("HMAC-SHA256"); 						// 设置签名类型，选填
		unifiedorderReqParam
				.setNotify_url(WechatPayConfig.PARKING_PAY_NOTIFY_URL); 		// 设置异步通知回调地址，选填
		unifiedorderReqParam.setTrade_type("APP"); 								// 设置交易类型
		try{
			System.out.println("unifiedorderReqParam.gtReqMap():"+unifiedorderReqParam.gtReqMap());
			return WxPayRequestUtil.unifiedorderReqApp(unifiedorderReqParam.gtReqMap());
		}catch(Exception ex){
			ex.printStackTrace();
			
			return null;
		}
	}
	/*
	 * 车位管理费
	 * 
	 */
	@Override
	public Map<String, String> BerthCharges(Map<String, Object> additionMap) {
		BerthOrder berthOrder = (BerthOrder) additionMap.get("berthOrder");
		//获取订单号
		String out_trade_no = SerializeUtil.OrderSerialize();
		berthOrder.setOutTradeNo(out_trade_no);	//订单号
		double money = berthOrder.getMoney();//应付金额
		//将订单号存进数据库
		System.err.println("berthOrder:"+berthOrder);
		berthOrderMapper.updateBerthOrder(berthOrder);
		
		//参数封装
		UnifiedorderReqParam unifiedorderReqParam = new UnifiedorderReqParam();
		
		unifiedorderReqParam.setAppid(WechatPayConfig.APP_ID);					// 设置应用ID,必填
		unifiedorderReqParam.setMch_id(WechatPayConfig.PARTNERID);				// 设置商户号,必填
		unifiedorderReqParam.setNonce_str(WXPayUtil.generateNonceStr()); 		// 设置随机字符串,必填
		unifiedorderReqParam.setBody(WechatPayConfig.APPNAME + "-" + "车位费");	// 设置商品描述,必填
		unifiedorderReqParam.setOut_trade_no(out_trade_no);						// 设置商户订单号
		unifiedorderReqParam.setTotal_fee(1);									// 设置总金额,单位为分
		unifiedorderReqParam
				.setSpbill_create_ip(additionMap.get("realIp").toString());		// 设置终端IP，必填
		unifiedorderReqParam.setSign_type("HMAC-SHA256"); 						// 设置签名类型，选填
		unifiedorderReqParam
				.setNotify_url(WechatPayConfig.PARKING_PAY_NOTIFY_URL); 		// 设置异步通知回调地址，选填
		unifiedorderReqParam.setTrade_type("APP"); 								// 设置交易类型
		try{
			System.out.println("unifiedorderReqParam.gtReqMap():"+unifiedorderReqParam.gtReqMap());
			return WxPayRequestUtil.unifiedorderReqApp(unifiedorderReqParam.gtReqMap());
		}catch(Exception ex){
			ex.printStackTrace();
			
			return null;
		}
	}
	
	
	

}
