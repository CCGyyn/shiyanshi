package com.zeng.ezsh.wechatpay.uitls;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import com.github.wxpay.sdk.WXPayConfig;
import com.github.wxpay.sdk.WXPayConstants.SignType;
import com.sun.media.jfxmedia.logging.Logger;
import com.zeng.ezsh.wechatpay.config.WechatPayConfig;
import com.zeng.ezsh.wy.service.impl.ParkNoticeServiceImpl.payForScan;
import com.zeng.ezsh.wy.utils.HttpClientUtil;
import com.zeng.ezsh.wy.utils.HttpUrlConnectionUtil;
import com.zeng.ezsh.wy.utils.XStreamUtil;

public class WxPayRequestUtil {
	/**
	 * @description 关闭微信支付订单接口
	 *
	 * @auhtor qwc 2018年12月22日 下午1:26:29
	 * @param param
	 * @return Map<String,String>
	 * @throws Exception
	 */
	public static Map<String, String> closeOrderReq(Map<String, String> param)
			throws Exception {
		// 签名算法
		SignType signType = SignType.HMACSHA256;
		// 生成带签名的xml形式的请求参数
		String reqXmString = WXPayUtil.generateSignature(param,
				WechatPayConfig.KEY, signType);

		// 执行微信支付订单关闭操作
		String repXmlString = HttpUrlConnectionUtil.urlPostWeChatPay(
				WechatPayConfig.DOMAIN_API
						+ WechatPayConfig.CLOSEORDER_URL_SUFFIX,
				reqXmString, WechatPayConfig.Charset);

		// 将微信支付服务端返回的xml形式的查询结果转换成Map
		Map<String, String> repMap = WXPayUtil.xmlToMap(repXmlString);
		// 判断查询结果
		if (repMap.get("return_code").equals("SUCCESS")) {
			// 对订单查询的返回结果进行验签【这一步很重要，不可缺】
			if (WXPayUtil.isSignatureValid(repMap, WechatPayConfig.KEY)) {
				// 验签通过返回查询结果
				return repMap;
			} else {
				return null;
			}
		} else {
			return null;
		}
	}

	/**
	 * @description 统一下单请求
	 *
	 * @auhtor qwc 2018年12月21日 下午9:37:37
	 * @param param
	 * @return
	 * @throws Exception Map<String,String>
	 */
	public static Map<String, String> unifiedorderReqApp(
			Map<String, String> param) throws Exception {
		SignType signType = SignType.HMACSHA256;
		// 生成带签名的xml形式的请求参数

		String reqXmString = WXPayUtil.generateSignedXml(param,
				WechatPayConfig.KEY, signType);
		System.out.println("reqXmString" + reqXmString);
		String respXmlString = HttpUrlConnectionUtil.urlPostWeChatPay(
				WechatPayConfig.DOMAIN_API
						+ WechatPayConfig.UNIFIEDORDER_URL_SUFFIX,
				reqXmString, WechatPayConfig.Charset);

		// 将微信支付服务端返回的xml数据转换成Map
		Map<String, String> repMap = WXPayUtil.xmlToMap(respXmlString);
		if (repMap.get("return_code").equals("SUCCESS")) { //

			if (WXPayUtil.isSignatureValid(repMap, WechatPayConfig.KEY,
					signType)) {// 进行验签
				// Map<String, String> appReqMap = new HashMap<String,
				// String>();
				AppSubOrderReqParam appReqParam = new AppSubOrderReqParam();
				appReqParam.setAppid(WechatPayConfig.APP_ID);// 设置应用ID，必填
				appReqParam.setPartnerid(WechatPayConfig.PARTNERID); // 设置商户号，必填
				appReqParam.setPrepayid(repMap.get("prepay_id"));// 设置预支付交易会话ID，必填
				/*
				 * appReqParam.setNoncestr(WXPayUtil.generateNonceStr());//
				 * 设置随机字符串,必填 appReqParam.setTimestamp(String.valueOf(WXPayUtil.
				 * getCurrentTimestamp()));//设置时间戳，必填
				 */
				// 返回app 请求参数
				return WXPayUtil.generateSignedMap(appReqParam.gtAppReqMap(),
						WechatPayConfig.KEY, signType);
			} else {
				return null;
			}

		} else {
			return null;
		}
	}
	/**
	 * 微信H5支付
	 * @param param
	 * @return
	 * @throws Exception
	 */
	public static Map<String, String> unifiedorderReqH5(
			Map<String, String> param) throws Exception {
		SignType signType = SignType.MD5;
		/**
		 * 沙箱获取秘钥
		 */
//		String URI = "https://api.mch.weixin.qq.com/sandboxnew/pay/getsignkey";
//		Map<String, String> paramMap = new HashMap<>();
//		paramMap.put("mch_id",WechatPayConfig.PARTNERID_H5);
//		paramMap.put("nonce_str", param.get("nonce_str"));
//		String sandBoxreq = WXPayUtil.generateSignedXml(paramMap, 
//				WechatPayConfig.SECRET, signType);
//		System.out.println(sandBoxreq);
//		String signKey = WXPayUtil.xmlToMap(HttpUrlConnectionUtil.urlPostWeChatPay(URI, 
//				sandBoxreq, WechatPayConfig.Charset)).get("sandbox_signkey");
		
		// 生成带签名的xml形式的请求参数

		String reqXmString = WXPayUtil.generateSignedXml(param,
				WechatPayConfig.KEY_H5, signType);
		System.out.println("reqXmString" + reqXmString);
		String respXmlString = HttpUrlConnectionUtil.urlPostWeChatPay(
				WechatPayConfig.DOMAIN_API
//						+ WechatPayConfig.SANDBOX_UNIFIEDORDER_URL_SUFFIX,
						+ WechatPayConfig.UNIFIEDORDER_URL_SUFFIX,
				reqXmString, WechatPayConfig.Charset);

		// 将微信支付服务端返回的xml数据转换成Map
		Map<String, String> repMap = WXPayUtil.xmlToMap(respXmlString);
		if (repMap.get("return_code").equals("SUCCESS")) { //

			if (WXPayUtil.isSignatureValid(repMap, WechatPayConfig.KEY_H5,
					signType)) {// 进行验签
				// Map<String, String> appReqMap = new HashMap<String,
				// String>();
				AppSubOrderReqParam appReqParam = new AppSubOrderReqParam();
				appReqParam.setAppid(WechatPayConfig.APP_ID_JSAPI);// 设置应用ID，必填
//				appReqParam.setPartnerid(WechatPayConfig.PARTNERID_H5); // 设置商户号，必填
				System.out.println("签名方式："+signType.toString());
				appReqParam.setSignType("MD5");
				appReqParam.setPrepayid(repMap.get("prepay_id"));// 设置预支付交易会话ID，必填
				/*
				 * appReqParam.setNoncestr(WXPayUtil.generateNonceStr());//
				 * 设置随机字符串,必填 appReqParam.setTimestamp(String.valueOf(WXPayUtil.
				 * getCurrentTimestamp()));//设置时间戳，必填
				 */
				// 返回app 请求参数
				return WXPayUtil.generateSignedMap(appReqParam.gtH5ReqMap(),
						WechatPayConfig.KEY_H5, signType);
			} else {
				return null;
			}

		} else {
			return null;
		}
	}
	
	/**
	 * 微信刷卡支付
	 * @throws Exception 
	 */
	public static Map<String, String> micropayReqF2F(Map<String,String> param) throws Exception{
		//将参数转为xml格式并签名
		String reqXmString = WXPayUtil.generateSignedXml(param, WechatPayConfig.KEY, SignType.HMACSHA256);
		//发送请求到微信服务器
		String respXmlString = HttpUrlConnectionUtil.urlPostWeChatPay(
				WechatPayConfig.DOMAIN_API
						+ WechatPayConfig.MICROPAY_URL_SUFFIX,
				reqXmString, WechatPayConfig.Charset);
		// 将微信支付服务端返回的xml数据转换成Map
		Map<String, String> repMap = WXPayUtil.xmlToMap(respXmlString);
		return repMap;
//		if (repMap.get("return_code").equals("SUCCESS")) { //返回状态码
//
//			if (WXPayUtil.isSignatureValid(repMap, WechatPayConfig.KEY,
//					 SignType.HMACSHA256)) {// 进行验签
//				if(repMap.get("result_code").equals("SUCCESS")){
//					return "SUCCESS";
//				}else{
//					return "TRADEERROR";
//				}
//			} else {
//				return "SIGNERROR";
//			}
//
//		} else {
//			return repMap.get("err_code");
//		}
	}
	
	
	/**
	 * @description 微信支付订单查询
	 *
	 * @auhtor qwc 2018年1月27日 下午4:59:08
	 * @param param
	 * @return
	 * @throws Exception
	 * @return Map<String,String>
	 */
	public static Map<String, String> OrderqueryReq(Map<String, String> param)
			throws Exception {
		// 签名算法
		SignType signType = SignType.HMACSHA256;
		// 生成带签名的xml形式的请求参数
		String reqXmString = WXPayUtil.generateSignature(param,
				WechatPayConfig.KEY, signType);

		// 执行微信支付订单查询操作
		String repXmlString = HttpUrlConnectionUtil.urlPostWeChatPay(
				WechatPayConfig.DOMAIN_API
						+ WechatPayConfig.ORDERQUERY_URL_SUFFIX,
				reqXmString, WechatPayConfig.Charset);

		// 将微信支付服务端返回的xml形式的查询结果转换成Map
		Map<String, String> repMap = WXPayUtil.xmlToMap(repXmlString);
		// 判断查询结果
		if (repMap.get("return_code").equals("SUCCESS")) {
			// 对订单查询的返回结果进行验签【这一步很重要，不可缺】
			if (WXPayUtil.isSignatureValid(repMap, WechatPayConfig.KEY)) {
				// 验签通过返回查询结果
				return repMap;
			} else {
				return null;
			}
		} else {
			return null;
		}
	}
}
