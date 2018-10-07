package com.zeng.ezsh.alipay.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alipay.api.AlipayApiException;
import com.alipay.api.internal.util.AlipaySignature;
import com.zeng.ezsh.alipay.config.AlipayConfig;
import com.zeng.ezsh.alipay.utils.SynchronizeVerifyUtil;
import com.zeng.ezsh.wy.action.ParkChargeAction;
import com.zeng.ezsh.wy.entity.BenefitRecord;
import com.zeng.ezsh.wy.entity.BerthMessage;
import com.zeng.ezsh.wy.entity.BerthOrder;
import com.zeng.ezsh.wy.entity.ChargeInfo;
import com.zeng.ezsh.wy.entity.GoodsOrder;
import com.zeng.ezsh.wy.entity.ParkRecord;
import com.zeng.ezsh.wy.entity.ResidentialUser;
import com.zeng.ezsh.wy.entity.UserTeacherFee;
import com.zeng.ezsh.wy.service.BenefitRecordService;
import com.zeng.ezsh.wy.service.BerthOrderService;
import com.zeng.ezsh.wy.service.ChargeRecordService;
import com.zeng.ezsh.wy.service.GoodsOrderService;
import com.zeng.ezsh.wy.service.PlateManagementService;
import com.zeng.ezsh.wy.service.ResidentialUserService;
import com.zeng.ezsh.wy.service.UserTeacherFeeService;
import com.zeng.ezsh.wy.utils.AccessTokenUtil;
import com.zeng.ezsh.wy.utils.RetJsonUtil;
/**
 * @description 同步验证支付宝支付结果,获取订单支付状态接口
 *
 * @author qwc
 */
@Controller
@RequestMapping("aliPay")
public class AlipaySynVerifyAction {
	@Resource
	GoodsOrderService goodsService;
	@Resource
	ChargeRecordService chargeRecordService;
	@Resource
	UserTeacherFeeService userTeacherFeeService;
	@Resource
	BenefitRecordService benefitRecordService;
	@Resource
	ResidentialUserService residentialUserService;
	@Resource
	PlateManagementService plateManagementService;
	@Resource
	BerthOrderService berthOrderService;
	
	/**
	 * @description 同步验证商品支付结果
	 *
	 * @auhtor qwc 2017年9月9日下午10:52:13
	 * @param request
	 * @param response
	 * @throws IOException
	 * @return void
	 */
	@RequestMapping("appVerify")
	public void synchronizeVerify(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		PrintWriter out = response.getWriter();
		RetJsonUtil retJsonUtil = new RetJsonUtil();

		// 从移动端获取用户支付后支付宝端同步返回的信息
		JSONObject json = JSONObject
				.fromObject(request.getParameter("synchronize"));
		String alipayTradeAppPayResponse = (String) json
				.get("alipay_trade_app_pay_response");
		String sign = (String) json.get("sign");
		try {
			// 验签，貌似同步同步返回报文时会自动进行验签
			boolean flag = AlipaySignature.rsaCheck(alipayTradeAppPayResponse,
					sign, AlipayConfig.AlipayPublicKey,
					AlipayConfig.AliPaySignCharset, "RSA2");

			// 验签通过
			if (flag) {
				JSONObject jsonObject = JSONObject
						.fromObject(alipayTradeAppPayResponse);
				GoodsOrder goodsOrder = new GoodsOrder();
				// 设置商家后台生成的订单号
				goodsOrder.setOrderSerialNum(
						(String) jsonObject.get("out_trade_no"));
				// 设置支付宝服务器生成的交易号
				goodsOrder
						.setTransactionNum((String) jsonObject.get("trade_no"));
				// 根据支付宝服务器生成的交易号获取订单信息
				goodsOrder = goodsService.checkOrderSerialNumIsOn(goodsOrder);
				
				ResidentialUser residentialUser = new ResidentialUser();
				residentialUser.setUserId(goodsOrder.getpUserId());
				residentialUser = residentialUserService
						.getUserIntegralByUserId(residentialUser);

				if (goodsOrder.getPayStatus().equals(1)) {
					Map<String, Object> retMap = new HashMap<String, Object>();
					retMap.put("residueIntegral",
							residentialUser.getUserIntegral()
									.setScale(2, BigDecimal.ROUND_HALF_UP)
									.toString());
					retJsonUtil.setStatus("1");
					retJsonUtil.setRetMap(retMap);
					retJsonUtil.setMessage("支付成功");
				} else {
					retJsonUtil.setStatus("0");
					retJsonUtil.setMessage("支付失败");
				}
				out.write(retJsonUtil.getRetJsonM());
			} else {
				retJsonUtil.setStatus("0");
				retJsonUtil.setMessage("支付失败");
				out.write(retJsonUtil.getRetJsonM());
			}
		} catch (AlipayApiException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * @description 物业缴费支付同步验证结果
	 *
	 * @auhtor qwc 2017年10月16日下午12:02:38
	 * @param request
	 * @param response
	 * @throws IOException void
	 */
	@RequestMapping(value = "verifyPropertyPay")
	public void synchronizeVerifyPropertyPay(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		PrintWriter out = response.getWriter();
		RetJsonUtil retJsonUtil = new RetJsonUtil();
		
		Map<String, Object> retMap = new HashMap<String, Object>();
		JSONObject json = JSONObject
				.fromObject(request.getParameter("synchronize"));
		String alipayTradeAppPayResponse = (String) json
				.get("alipay_trade_app_pay_response");
		String sign = (String) json.get("sign");
		try {
			// 貌似同步同步返回报文时会自动进行验签
			boolean flag = SynchronizeVerifyUtil.rsaCheck(
					alipayTradeAppPayResponse, sign,
					AlipayConfig.AlipayPublicKey,
					AlipayConfig.AliPaySignCharset, "RSA2");
			JSONObject jsonObject = JSONObject
					.fromObject(alipayTradeAppPayResponse);
			ChargeInfo chargeInfo = new ChargeInfo();
			chargeInfo.setChargeTransactionNum(
					(String) jsonObject.get("trade_no"));
			chargeInfo = chargeRecordService
					.selectChargeInfoReacord(chargeInfo);
			if (flag && chargeInfo.getChargePayStatus().equals(1)) {
	
				retJsonUtil.setStatus("1");
				retJsonUtil.setRetMap(retMap);
				retJsonUtil.setMessage("支付成功");
				out.write(retJsonUtil.getRetJsonM());
			} else {
				retJsonUtil.setStatus("0");
				retJsonUtil.setRetMap(retMap);
				retJsonUtil.setMessage("支付失败");
				out.write(retJsonUtil.getRetJsonM());
			}
		} catch (AlipayApiException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * @description 家教平台移动端支付同步验证结果
	 *
	 * @auhtor qwc 2017年10月16日下午12:02:38
	 * @param request
	 * @param response
	 * @throws IOException void
	 */
	@RequestMapping(value = "verifyTeacherPay")
	public void synchronizeVerifyTeacherPay(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		PrintWriter out = response.getWriter();
		RetJsonUtil retJsonUtil = new RetJsonUtil();
		
		Map<String, Object> retMap = new HashMap<String, Object>();
		JSONObject json = JSONObject
				.fromObject(request.getParameter("synchronize")); // 获取同步验证返回结果
		String alipayTradeAppPayResponse = (String) json
				.get("alipay_trade_app_pay_response");
		String sign = (String) json.get("sign");
		try {
			// 貌似同步同步返回报文时会自动进行验签
			boolean flag = SynchronizeVerifyUtil.rsaCheck(
					alipayTradeAppPayResponse, sign,
					AlipayConfig.AlipayPublicKey,
					AlipayConfig.AliPaySignCharset, "RSA2");
			JSONObject jsonObject = JSONObject
					.fromObject(alipayTradeAppPayResponse); // 解析返回报文

			UserTeacherFee record = new UserTeacherFee();
			record.setTradeNum((String) jsonObject.get("trade_no")); // 支付宝返回的交易流水号
			record = userTeacherFeeService.selectByParam(record);
			if (flag && record.getTradeNum() != null) {
				retJsonUtil.setStatus("1");
				retJsonUtil.setRetMap(retMap);
				retJsonUtil.setMessage("支付成功");
				out.write(retJsonUtil.getRetJsonM());
			} else {
				retJsonUtil.setStatus("0");
				retJsonUtil.setRetMap(retMap);
				retJsonUtil.setMessage("支付失败");
				out.write(retJsonUtil.getRetJsonM());
			}
		} catch (AlipayApiException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * @description 公益基金支付同步验证
	 *
	 * @auhtor qwc 2017年12月5日下午9:37:59
	 * @param request
	 * @param response
	 * @throws IOException void
	 */
	@RequestMapping(value = "verifyBenefitPay")
	public void synchronizeVerifyBenefitPay(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		PrintWriter out = response.getWriter();
		RetJsonUtil retJsonUtil = new RetJsonUtil();
		
		Map<String, Object> retMap = new HashMap<String, Object>();
		JSONObject json = JSONObject
				.fromObject(request.getParameter("synchronize")); // 获取同步验证返回结果
		String alipayTradeAppPayResponse = (String) json
				.get("alipay_trade_app_pay_response");
		String sign = (String) json.get("sign");
		try {
			// 貌似同步同步返回报文时会自动进行验签
			boolean flag = SynchronizeVerifyUtil.rsaCheck(
					alipayTradeAppPayResponse, sign,
					AlipayConfig.AlipayPublicKey,
					AlipayConfig.AliPaySignCharset, "RSA2");
			JSONObject jsonObject = JSONObject
					.fromObject(alipayTradeAppPayResponse); // 解析返回报文

			BenefitRecord record = new BenefitRecord();
			record.setTradeNum((String) jsonObject.get("trade_no")); // 支付宝返回的交易流水号
			record = benefitRecordService.selectByParam(record);
			if (flag && record.getTradeNum() != null) {
				retJsonUtil.setStatus("1");
				retJsonUtil.setRetMap(retMap);
				retJsonUtil.setMessage("支付成功");
				out.write(retJsonUtil.getRetJsonM());
			} else {
				retJsonUtil.setStatus("0");
				retJsonUtil.setRetMap(retMap);
				retJsonUtil.setMessage("支付失败");
				out.write(retJsonUtil.getRetJsonM());
			}
		} catch (AlipayApiException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
	 * 停车支付同步通知
	 * @param carId
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping("verifyParkingPay")
	public void synchronizeVerifyParkingPay(
			HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		
		JSONObject retJson = new JSONObject();
		Map<String, Object> retMap = new HashMap<>();
		ParkRecord parkRecord = new ParkRecord();
		
		JSONObject json = JSONObject.fromObject(request.getParameter("synchronize"));
		String alipayTradeAppPayResponse = (String) json.get("alipay_trade_app_pay_response");
		String alipayTradeWebPayResponse = (String) json.get("alipay_trade_web_pay_response");
		String sign = (String) json.get("sign");
		
		String tradeResponse;
		if(alipayTradeAppPayResponse!=null && alipayTradeAppPayResponse!=""){
			tradeResponse = alipayTradeAppPayResponse;
		}else{
			tradeResponse = alipayTradeWebPayResponse;
		}
		
		try {
			//验签
			boolean flag = SynchronizeVerifyUtil.rsaCheck(
					tradeResponse, sign,
					AlipayConfig.AlipayPublicKey, AlipayConfig.AliPaySignCharset, "RSA2");
			//解析 获取交易流水号
			String transactionNum = (String) JSONObject.fromObject(tradeResponse)
														.get("trade_no");
			String out_trade_no = (String) JSONObject.fromObject(tradeResponse)
					.get("out_trade_no");
			System.err.println("transactionNum:"+transactionNum);
			
			if(out_trade_no != null || out_trade_no != ""){//根据商户订单号获取记录
				parkRecord = plateManagementService.getParkRecordByOrderNum(out_trade_no);
//				parkRecord.setPrepaymentTime(new Date());
//				parkRecord.setTransactionNum(transactionNum);
//				parkRecord.setPayStatus(1);
				System.out.println(parkRecord);
//				//更新数据
//				plateManagementService.updateParkRecord(parkRecord);
				retMap.put("payWay", parkRecord.getPayWay());		//支付方式
				retMap.put("orderNum", parkRecord.getOrderNum());	//订单号
				retMap.put("money", parkRecord.getHandParkCost());	//已付金额
				ParkChargeAction action = new ParkChargeAction();
				action.parkPrepaymentNotice(parkRecord.getPlateNumber());//发送通知
			}
			if(flag){
				retJson.put("status", 1);
				retJson.put("data", retMap);
				retJson.put("message", "支付成功");
			}else{
				retJson.put("status", 0);
				retJson.put("data", retMap);
				retJson.put("message", "支付失败");
			}
			
		} catch (AlipayApiException e) {
			e.printStackTrace();
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
	}
	/**
	 * 车位续费支付同步通知
	 * @param carId
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping("verifyBerthPay")
	public void synchronizeVerifyBerthPay(
			HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		
		JSONObject retJson = new JSONObject();
		Map<String, Object> retMap = new HashMap<>();
		BerthOrder berthOrder = new BerthOrder();
		
		JSONObject json = JSONObject.fromObject(request.getParameter("synchronize"));
		String alipayTradeAppPayResponse = (String) json.get("alipay_trade_app_pay_response");
		String alipayTradeWebPayResponse = (String) json.get("alipay_trade_web_pay_response");
		String sign = (String) json.get("sign");
		
		String tradeResponse;
		if(alipayTradeAppPayResponse!=null && alipayTradeAppPayResponse!=""){
			tradeResponse = alipayTradeAppPayResponse;
		}else{
			tradeResponse = alipayTradeWebPayResponse;
		}
		
		try {
			//验签
			boolean flag = SynchronizeVerifyUtil.rsaCheck(
					tradeResponse, sign,
					AlipayConfig.AlipayPublicKey, AlipayConfig.AliPaySignCharset, "RSA2");
			//解析 获取交易流水号
			String transactionNum = (String) JSONObject.fromObject(tradeResponse)
														.get("trade_no");
			String out_trade_no = (String) JSONObject.fromObject(tradeResponse)
					.get("out_trade_no");
			System.err.println("transactionNum:"+transactionNum);
			
			if(out_trade_no != null || out_trade_no != ""){//根据商户订单号获取记录
				berthOrder = berthOrderService.getBerthOrderByOrderNum(out_trade_no);
				
				System.out.println("BerthOrder-->"+berthOrder);
				retMap.put("payWay", berthOrder.getPayway());		//支付方式
				retMap.put("orderNum", berthOrder.getOutTradeNo());	//订单号
				retMap.put("money", berthOrder.getMoney());	//已付金额
				ParkChargeAction action = new ParkChargeAction();
				action.berthPayNotice(berthOrder);//发送通知
			}
			if(flag){
				retJson.put("status", 1);
				retJson.put("data", retMap);
				retJson.put("message", "支付成功");
			}else{
				retJson.put("status", 0);
				retJson.put("data", retMap);
				retJson.put("message", "支付失败");
			}
			
		} catch (AlipayApiException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 购买车位支付同步通知
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping("verifyBuyBerthPay")
	public void synchronizeVerifyBuyBerthPay(
			HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		
		JSONObject retJson = new JSONObject();
		Map<String, Object> retMap = new HashMap<>();
		BerthOrder berthOrder = new BerthOrder();
		
		JSONObject json = JSONObject.fromObject(request.getParameter("synchronize"));
		String alipayTradeAppPayResponse = (String) json.get("alipay_trade_app_pay_response");
		String alipayTradeWebPayResponse = (String) json.get("alipay_trade_web_pay_response");
		String sign = (String) json.get("sign");
		
		String tradeResponse;
		if(alipayTradeAppPayResponse!=null && alipayTradeAppPayResponse!=""){
			tradeResponse = alipayTradeAppPayResponse;
		}else{
			tradeResponse = alipayTradeWebPayResponse;
		}
		
		try {
			//验签
			boolean flag = SynchronizeVerifyUtil.rsaCheck(
					tradeResponse, sign,
					AlipayConfig.AlipayPublicKey, AlipayConfig.AliPaySignCharset, "RSA2");
			//解析 获取交易流水号
			String transactionNum = (String) JSONObject.fromObject(tradeResponse)
														.get("trade_no");
			String out_trade_no = (String) JSONObject.fromObject(tradeResponse)
					.get("out_trade_no");
			System.err.println("transactionNum:"+transactionNum);
			
			if(out_trade_no != null || out_trade_no != ""){//根据商户订单号获取记录
				berthOrder = berthOrderService.getBerthOrderByOrderNum(out_trade_no);
				
				System.out.println("BerthOrder-->"+berthOrder);
				retMap.put("payWay", berthOrder.getPayway());		//支付方式
				retMap.put("orderNum", berthOrder.getOutTradeNo());	//订单号
				retMap.put("money", berthOrder.getMoney());	//已付金额
				
				String accessToken = request.getParameter("token");
				ResidentialUser user = AccessTokenUtil.parserAccessTokenToModel(accessToken);
				BerthMessage berthMessage = new BerthMessage();
				berthMessage.setBerthNumber(berthOrder.getBerthNumber());
				berthMessage.setUserCommunityId(berthOrder.getUserCommunityId());
				berthMessage.setHandInCost(berthOrder.getMoney());
				berthMessage.setUserName(user.getUserName());
				berthMessage.setUserPhone(user.getUserLinkphone());
				
				ParkChargeAction action = new ParkChargeAction();
				action.buyBerthNotice(berthMessage);//发送通知
			}
			if(flag){
				retJson.put("status", 1);
				retJson.put("data", retMap);
				retJson.put("message", "支付成功");
			}else{
				retJson.put("status", 0);
				retJson.put("data", retMap);
				retJson.put("message", "支付失败");
			}
			
		} catch (AlipayApiException e) {
			e.printStackTrace();
		}
	}
}
