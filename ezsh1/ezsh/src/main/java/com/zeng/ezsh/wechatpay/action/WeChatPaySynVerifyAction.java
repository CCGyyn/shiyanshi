package com.zeng.ezsh.wechatpay.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
 * @description 微信支付同步验证获取订单支付状态接口
 *
 * @author qwc
 */
@Controller
@RequestMapping(value = "wechatPay")
public class WeChatPaySynVerifyAction {
	
	
	private static Logger logger = LoggerFactory
			.getLogger(WeChatPaySynVerifyAction.class);
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
	 * @description 同步验证商品微信支付结果
	 *
	 * @auhtor qwc 2017年9月9日下午10:52:13
	 * @param request
	 * @param orderSerialNum
	 * @param response
	 * @throws IOException
	 * @return void
	 */
	@RequestMapping("appVerify")
	public void synchronizeVerify(HttpServletRequest request,
			@RequestParam(value = "orderSerialNum", required = true) String orderSerialNum,
			HttpServletResponse response) throws IOException {
		// 创建json返回对象
		PrintWriter out = response.getWriter();
		RetJsonUtil retJsonUtil = new RetJsonUtil();

		// 查询微信支付订单状态
		GoodsOrder goodsOrder = new GoodsOrder();
		// 设置商家后台自动生成的订单号作为查询条件
		goodsOrder.setOrderSerialNum(orderSerialNum);
		// 执行订单查询
		goodsOrder = goodsService.checkOrderSerialNumIsOn(goodsOrder);

		ResidentialUser residentialUser = new ResidentialUser();
		residentialUser.setUserId(goodsOrder.getpUserId());
		residentialUser = residentialUserService
				.getUserIntegralByUserId(residentialUser);

		// 封装返回结果
		if (goodsOrder.getPayStatus().equals(1)) {
			Map<String, Object> retMap = new HashMap<String, Object>();
			retMap.put("residueIntegral",
					residentialUser.getUserIntegral()
							.setScale(2, BigDecimal.ROUND_HALF_UP)
							.toString());
			retJsonUtil.setStatus("1");
			retJsonUtil.setMessage("支付成功");
			retJsonUtil.setRetMap(retMap);
		} else {
			retJsonUtil.setStatus("0");
			retJsonUtil.setMessage("支付失败");
		}
		out.write(retJsonUtil.getRetJsonM());
	}

	/**
	 * @description 同步验证物业缴费微信支付结果
	 *
	 * @auhtor qwc 2017年10月16日下午12:02:38
	 * @param request
	 * @param orderSerialNum
	 * @param response
	 * @throws IOException
	 * @return void
	 */
	@RequestMapping(value = "verifyPropertyPay")
	public void synchronizeVerifyPropertyPay(HttpServletRequest request,
			@RequestParam(value = "orderSerialNum", required = true) String orderSerialNum,
			HttpServletResponse response) throws IOException {
		PrintWriter out = response.getWriter();
		RetJsonUtil retJson = new RetJsonUtil();

		ChargeInfo chargeInfo = new ChargeInfo();
		chargeInfo.setChargeTransactionNum(orderSerialNum);
		chargeInfo = chargeRecordService.selectChargeInfoReacord(chargeInfo);

		// 封装返回结果
		if (chargeInfo.getChargePayStatus().equals(1)) {
			retJson.setStatus("1");
			retJson.setMessage("支付成功");
		} else {
			retJson.setStatus("0");
			retJson.setMessage("支付失败");
		}
		out.write(retJson.toString());
	}

	/**
	 * @description 同步验证结果家教平台移动端支付结果
	 *
	 * @auhtor qwc 2017年10月16日下午12:02:38
	 * @param request
	 * @param tradeNum
	 * @param response
	 * @throws IOException
	 * @return void
	 */
	@RequestMapping(value = "verifyTeacherPay")
	public void synchronizeVerifyTeacherPay(HttpServletRequest request,
			@RequestParam(value = "outTradeNo", required = true) String outTradeNo,
			HttpServletResponse response) throws IOException {
		PrintWriter out = response.getWriter();
		RetJsonUtil retJson = new RetJsonUtil();

		// 查询微信支付订单支付状态
		UserTeacherFee record = new UserTeacherFee();
		record.setOutTradeNo(outTradeNo);
		record = userTeacherFeeService.selectByParam(record);

		// 封装返回结果
		if (record.getTradeNum() != null) {
			retJson.setStatus("1");
			retJson.setMessage("支付成功");
		} else {
			retJson.setStatus("0");
			retJson.setMessage("支付失败");
		}
		out.write(retJson.toString());
	}

	/**
	 * @description 同步验证公益基金支付结果
	 *
	 * @auhtor qwc 2017年12月5日下午9:37:59
	 * @param request
	 * @param tradeNum
	 * @param response
	 * @throws IOException
	 * @return void
	 */
	@RequestMapping(value = "verifyBenefitPay")
	public void synchronizeVerifyBenefitPay(HttpServletRequest request,
			@RequestParam(value = "outTradeNo", required = true) String outTradeNo,
			HttpServletResponse response) throws IOException {
		PrintWriter out = response.getWriter();
		RetJsonUtil retJson = new RetJsonUtil();

		// 查询订单支付状态
		BenefitRecord record = new BenefitRecord();
		record.setOutTradeNo(outTradeNo);
		record = benefitRecordService.selectByParam(record);

		// 封装返回结果
		if (record.getTradeNum() != null) {
			retJson.setStatus("1");
			retJson.setMessage("支付成功");
		} else {
			retJson.setStatus("0");
			retJson.setMessage("支付失败");
		}
		out.write(retJson.getRetJsonM());
	}
	/**
	 * @description 同步验证停车支付支付结果
	 * @param request
	 * @param response
	 * @param outTradeNo
	 * @throws IOException
	 */
	@RequestMapping("verityParkingPay")
	public void synchronizeVerifyParkingPay(
			HttpServletRequest request,
			HttpServletResponse response,
			@RequestParam(value = "outTradeNo") String outTradeNo
			) throws IOException {
		PrintWriter out =response.getWriter();
		ParkRecord parkRecord = new ParkRecord();
		RetJsonUtil retJson = new RetJsonUtil();
		parkRecord = plateManagementService.getParkRecordByOrderNum(outTradeNo);
		
		if(parkRecord.getTransactionNum() != null){
			retJson.setStatus("1");
			retJson.setMessage("支付成功");
			ParkChargeAction action = new ParkChargeAction();
			try {
				action.parkPrepaymentNotice(parkRecord.getPlateNumber());
			} catch (URISyntaxException e) {
				e.printStackTrace();
			}//发送通知
		}else{
			retJson.setStatus("0");
			retJson.setMessage("支付失败");
		}
		logger.info(retJson.getRetJsonM());
		out.write(retJson.getRetJsonM());
	}
	
	/**
	 * @description 同步验证车位管理费支付支付结果
	 * @param request
	 * @param response
	 * @param outTradeNo
	 * @throws IOException
	 */
	@RequestMapping("verityBerthPay")
	public void synchronizeVerifyBerthPay(
			HttpServletRequest request,
			HttpServletResponse response,
			@RequestParam(value = "outTradeNo") String outTradeNo
			) throws IOException {
		PrintWriter out =response.getWriter();
		BerthOrder berthOrder = new BerthOrder();
		RetJsonUtil retJson = new RetJsonUtil();
		berthOrder = berthOrderService.getBerthOrderByOrderNum(outTradeNo);
		
		if(berthOrder.getTransactionNum() != null){
			retJson.setStatus("1");
			retJson.setMessage("支付成功");
			ParkChargeAction action = new ParkChargeAction();
			action.berthPayNotice(berthOrder);//发送通知
		}else{
			retJson.setStatus("0");
			retJson.setMessage("支付失败");
		}
		logger.info(retJson.getRetJsonM());
		out.write(retJson.getRetJsonM());
	}
	/**
	 * @description 同步验证购买车位支付结果
	 * @param request
	 * @param response
	 * @param outTradeNo
	 * @throws IOException
	 */
	@RequestMapping("verityBuyBerthPay")
	public void synchronizeVerifyBuyBerthPay(
			HttpServletRequest request,
			HttpServletResponse response,
			@RequestParam(value = "outTradeNo") String outTradeNo
			) throws IOException {
		PrintWriter out =response.getWriter();
		BerthOrder berthOrder = new BerthOrder();
		RetJsonUtil retJson = new RetJsonUtil();
		berthOrder = berthOrderService.getBerthOrderByOrderNum(outTradeNo);
		
		if(berthOrder.getTransactionNum() != null){
			retJson.setStatus("1");
			retJson.setMessage("支付成功");
			
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
		}else{
			retJson.setStatus("0");
			retJson.setMessage("支付失败");
		}
		logger.info(retJson.getRetJsonM());
		out.write(retJson.getRetJsonM());
	}
	
	
}
