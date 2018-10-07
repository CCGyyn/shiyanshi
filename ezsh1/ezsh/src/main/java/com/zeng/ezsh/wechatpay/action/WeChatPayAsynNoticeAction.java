package com.zeng.ezsh.wechatpay.action;

import java.io.PrintWriter;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.dom4j.DocumentException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.github.wxpay.sdk.WXPayConstants.SignType;
import com.zeng.ezsh.wechatpay.config.WechatPayConfig;
import com.zeng.ezsh.wechatpay.service.WechatPayVerifyAsynService;
import com.zeng.ezsh.wechatpay.uitls.WXPayUtil;
import com.zeng.ezsh.wechatpay.uitls.WechatPayAsynRetJson;
import com.zeng.ezsh.wy.utils.XStreamUtil;

import net.sf.json.JSON;
import net.sf.json.JSONObject;
/**
 * @description 微信支付异步通知回调接口
 *
 * @author qwc
 */
@Controller
@RequestMapping(value = "wechatPayNotice")
public class WeChatPayAsynNoticeAction {
	@Resource
	WechatPayVerifyAsynService wechatPayVerifyAsynService;
	/**
	 * @description 商品支付异步验证【提供给微信支付服务端调用】
	 *
	 * @author qwc 2018年1月26日 下午10:54:17
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("verifyOrderAsyn")
	public void AsynNoticeVerificationForGoodsPay(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		PrintWriter out = response.getWriter();
		WechatPayAsynRetJson wechatPayAsynRetJson = new WechatPayAsynRetJson();
		try {
			Map<String, String> retMap = XStreamUtil.xmlToMap(request);
			System.out.println("retMap>>"
					+ JSONObject.fromObject(retMap).toString());
			SignType signType = SignType.HMACSHA256;
			try {
				boolean valid = WXPayUtil.isSignatureValid(retMap,
						WechatPayConfig.KEY, signType);
				if (valid) {
					if (wechatPayVerifyAsynService
							.verifyGoodsPayAsynAndUpadteOrder(retMap)) {
						wechatPayAsynRetJson.setReturn_code("SUCCESS");
						wechatPayAsynRetJson.setReturn_msg("OK");
					} else {
						wechatPayAsynRetJson.setReturn_code("SUCCESS");
						wechatPayAsynRetJson.setReturn_msg("FAIL");
					}
					out.write(WXPayUtil
							.mapToXml(wechatPayAsynRetJson.gtRetMap()));
				} else {
					wechatPayAsynRetJson.setReturn_code("SUCCESS");
					wechatPayAsynRetJson.setReturn_msg("FAIL");
					out.write(WXPayUtil
							.mapToXml(wechatPayAsynRetJson.gtRetMap()));
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				wechatPayAsynRetJson.setReturn_code("SUCCESS");
				wechatPayAsynRetJson.setReturn_msg("FAIL");
				out.write(WXPayUtil.mapToXml(wechatPayAsynRetJson.gtRetMap()));
				e.printStackTrace();
			}
			// 为SUCCESS时对返回数据进行解析
			if (retMap.get("return_code") != null
					&& retMap.get("return_code").equals("SUCCESS")) {

			}
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			wechatPayAsynRetJson.setReturn_code("SUCCESS");
			wechatPayAsynRetJson.setReturn_msg("FAIL");
			out.write(WXPayUtil.mapToXml(wechatPayAsynRetJson.gtRetMap()));
			e.printStackTrace();
		}
	}
	
	/**
	 * @description 通过支付宝异步通知进行物业费支付结果检验
	 *
	 * @auhtor qwc 2018年1月25日 下午5:41:30
	 * @param request
	 * @param response
	 * @throws Exception void
	 */
	@RequestMapping(value = "verifyPropertyPay")
	public void AsynNoticeVerificationForPropertyPay(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		PrintWriter out = response.getWriter();
		WechatPayAsynRetJson wechatPayAsynRetJson = new WechatPayAsynRetJson();
		try {
			Map<String, String> retMap = XStreamUtil.xmlToMap(request);
			SignType signType = SignType.HMACSHA256;
			try {
				boolean valid = WXPayUtil.isSignatureValid(retMap,
						WechatPayConfig.KEY, signType);
				if (valid) {
					if (wechatPayVerifyAsynService
							.verifyPropertyPayAsynAndUpadteOrder(retMap)) {
						wechatPayAsynRetJson.setReturn_code("SUCCESS");
						wechatPayAsynRetJson.setReturn_msg("OK");
					} else {
						wechatPayAsynRetJson.setReturn_code("SUCCESS");
						wechatPayAsynRetJson.setReturn_msg("FAIL");
					}
					out.write(WXPayUtil
							.mapToXml(wechatPayAsynRetJson.gtRetMap()));
				} else {
					wechatPayAsynRetJson.setReturn_code("SUCCESS");
					wechatPayAsynRetJson.setReturn_msg("FAIL");
					out.write(WXPayUtil
							.mapToXml(wechatPayAsynRetJson.gtRetMap()));
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				wechatPayAsynRetJson.setReturn_code("SUCCESS");
				wechatPayAsynRetJson.setReturn_msg("FAIL");
				out.write(WXPayUtil.mapToXml(wechatPayAsynRetJson.gtRetMap()));
				e.printStackTrace();
			}
			// 为SUCCESS时对返回数据进行解析
			if (retMap.get("return_code") != null
					&& retMap.get("return_code").equals("SUCCESS")) {

			}
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			wechatPayAsynRetJson.setReturn_code("SUCCESS");
			wechatPayAsynRetJson.setReturn_msg("FAIL");
			out.write(WXPayUtil.mapToXml(wechatPayAsynRetJson.gtRetMap()));
			e.printStackTrace();
		}
	}

	/**
	 * @description 通过支付宝异步通知进行家教平台使用费用的支付结果检测
	 *
	 * @auhtor qwc 2018年1月25日 下午5:42:00
	 * @param request
	 * @param response
	 * @throws Exception void
	 */
	@RequestMapping(value = "verifyTeacherPay")
	public void AsynNoticeVerificationForTeacherPay(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		PrintWriter out = response.getWriter();
		WechatPayAsynRetJson wechatPayAsynRetJson = new WechatPayAsynRetJson();
		try {
			Map<String, String> retMap = XStreamUtil.xmlToMap(request);
			SignType signType = SignType.HMACSHA256;
			try {
				boolean valid = WXPayUtil.isSignatureValid(retMap,
						WechatPayConfig.KEY, signType);
				// 验签通过
				if (valid) {
					if (wechatPayVerifyAsynService
							.verifyTeacherPayAsynAndUpadteOrder(retMap)) {
						wechatPayAsynRetJson.setReturn_code("SUCCESS");
						wechatPayAsynRetJson.setReturn_msg("OK");
					} else {
						wechatPayAsynRetJson.setReturn_code("SUCCESS");
						wechatPayAsynRetJson.setReturn_msg("FAIL");
					}
					out.write(WXPayUtil
							.mapToXml(wechatPayAsynRetJson.gtRetMap()));
				} else {
					wechatPayAsynRetJson.setReturn_code("SUCCESS");
					wechatPayAsynRetJson.setReturn_msg("FAIL");
					out.write(WXPayUtil
							.mapToXml(wechatPayAsynRetJson.gtRetMap()));
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				wechatPayAsynRetJson.setReturn_code("SUCCESS");
				wechatPayAsynRetJson.setReturn_msg("FAIL");
				out.write(WXPayUtil.mapToXml(wechatPayAsynRetJson.gtRetMap()));
				e.printStackTrace();
			}
			// 为SUCCESS时对返回数据进行解析
			if (retMap.get("return_code") != null
					&& retMap.get("return_code").equals("SUCCESS")) {

			}
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			wechatPayAsynRetJson.setReturn_code("SUCCESS");
			wechatPayAsynRetJson.setReturn_msg("FAIL");
			out.write(WXPayUtil.mapToXml(wechatPayAsynRetJson.gtRetMap()));
			e.printStackTrace();
		}
	}

	/**
	 * @description 通过支付宝异步通知进行公益基金的支付结果检测
	 *
	 * @auhtor qwc 2018年2月2日 下午5:43:40
	 * @param request
	 * @param response
	 * @throws Exception void
	 */
	@RequestMapping(value = "verifyBenefitPay")
	public void AsynNoticeVerificationForBenefitPay(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		PrintWriter out = response.getWriter();
		WechatPayAsynRetJson wechatPayAsynRetJson = new WechatPayAsynRetJson();
		try {
			Map<String, String> retMap = XStreamUtil.xmlToMap(request);
			SignType signType = SignType.HMACSHA256;
			try {
				boolean valid = WXPayUtil.isSignatureValid(retMap,
						WechatPayConfig.KEY, signType);
				if (valid) {
					if (wechatPayVerifyAsynService
							.verifyBenefitPayAsynAndUpadteOrder(retMap)) {
						wechatPayAsynRetJson.setReturn_code("SUCCESS");
						wechatPayAsynRetJson.setReturn_msg("OK");
					} else {
						wechatPayAsynRetJson.setReturn_code("SUCCESS");
						wechatPayAsynRetJson.setReturn_msg("FAIL");
					}
					out.write(WXPayUtil
							.mapToXml(wechatPayAsynRetJson.gtRetMap()));
				} else {
					wechatPayAsynRetJson.setReturn_code("SUCCESS");
					wechatPayAsynRetJson.setReturn_msg("FAIL");
					out.write(WXPayUtil
							.mapToXml(wechatPayAsynRetJson.gtRetMap()));
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				wechatPayAsynRetJson.setReturn_code("SUCCESS");
				wechatPayAsynRetJson.setReturn_msg("FAIL");
				out.write(WXPayUtil.mapToXml(wechatPayAsynRetJson.gtRetMap()));
				e.printStackTrace();
			}
			// 为SUCCESS时对返回数据进行解析
			if (retMap.get("return_code") != null
					&& retMap.get("return_code").equals("SUCCESS")) {

			}
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			wechatPayAsynRetJson.setReturn_code("SUCCESS");
			wechatPayAsynRetJson.setReturn_msg("FAIL");
			out.write(WXPayUtil.mapToXml(wechatPayAsynRetJson.gtRetMap()));
			e.printStackTrace();
		}
	}
	
	/**
	 * 微信异步通知
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	
	@RequestMapping("verifyParkingPay")
	public void AsynNoticeVerificationForParkingPay(HttpServletRequest request,
			HttpServletResponse response) throws Exception{
		PrintWriter out = response.getWriter();
		WechatPayAsynRetJson wechatPayAsynRetJson = new WechatPayAsynRetJson();
		
		try {
			Map<String, String> retMap = XStreamUtil.xmlToMap(request);
			boolean valid;
			try {
				valid = WXPayUtil.isSignatureValid(retMap,
						WechatPayConfig.KEY, SignType.HMACSHA256);
				if(valid){
					if(wechatPayVerifyAsynService
							.verifyParkingPayAsynAndUpdateOrder(retMap)){
						wechatPayAsynRetJson.setReturn_code("SUCCESS");
						wechatPayAsynRetJson.setReturn_msg("OK");
					} else {
						wechatPayAsynRetJson.setReturn_code("SUCCESS");
						wechatPayAsynRetJson.setReturn_msg("FAIL");
					}
					out.write(WXPayUtil
							.mapToXml(wechatPayAsynRetJson.gtRetMap()));
				} else {
					wechatPayAsynRetJson.setReturn_code("SUCCESS");
					wechatPayAsynRetJson.setReturn_msg("FAIL");
					out.write(WXPayUtil
							.mapToXml(wechatPayAsynRetJson.gtRetMap()));
				}
			} catch (Exception e) {
				wechatPayAsynRetJson.setReturn_code("SUCCESS");
				wechatPayAsynRetJson.setReturn_msg("FAIL");
				out.write(WXPayUtil
						.mapToXml(wechatPayAsynRetJson.gtRetMap()));
				e.printStackTrace();
			}
		} catch (DocumentException e) {
			wechatPayAsynRetJson.setReturn_code("SUCCESS");
			wechatPayAsynRetJson.setReturn_msg("FAIL");
			out.write(WXPayUtil
					.mapToXml(wechatPayAsynRetJson.gtRetMap()));
			e.printStackTrace();
		}
		
	}
	
	@RequestMapping("verifyBerthPay")
	public void AsynNoticeVerificationForBerthPay(HttpServletRequest request,
			HttpServletResponse response) throws Exception{
		PrintWriter out = response.getWriter();
		WechatPayAsynRetJson wechatPayAsynRetJson = new WechatPayAsynRetJson();
		
		try {
			Map<String, String> retMap = XStreamUtil.xmlToMap(request);
			boolean valid;
			try {
				valid = WXPayUtil.isSignatureValid(retMap,
						WechatPayConfig.KEY, SignType.HMACSHA256);
				if(valid){
					if(wechatPayVerifyAsynService
							.verifyBerthPayAsynAndUpdateOrder(retMap)){
						wechatPayAsynRetJson.setReturn_code("SUCCESS");
						wechatPayAsynRetJson.setReturn_msg("OK");
					} else {
						wechatPayAsynRetJson.setReturn_code("SUCCESS");
						wechatPayAsynRetJson.setReturn_msg("FAIL");
					}
					out.write(WXPayUtil
							.mapToXml(wechatPayAsynRetJson.gtRetMap()));
				} else {
					wechatPayAsynRetJson.setReturn_code("SUCCESS");
					wechatPayAsynRetJson.setReturn_msg("FAIL");
					out.write(WXPayUtil
							.mapToXml(wechatPayAsynRetJson.gtRetMap()));
				}
			} catch (Exception e) {
				wechatPayAsynRetJson.setReturn_code("SUCCESS");
				wechatPayAsynRetJson.setReturn_msg("FAIL");
				out.write(WXPayUtil
						.mapToXml(wechatPayAsynRetJson.gtRetMap()));
				e.printStackTrace();
			}
		} catch (DocumentException e) {
			wechatPayAsynRetJson.setReturn_code("SUCCESS");
			wechatPayAsynRetJson.setReturn_msg("FAIL");
			out.write(WXPayUtil
					.mapToXml(wechatPayAsynRetJson.gtRetMap()));
			e.printStackTrace();
		}
		
	}
}
