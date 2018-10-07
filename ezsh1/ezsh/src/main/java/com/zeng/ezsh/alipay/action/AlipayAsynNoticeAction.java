package com.zeng.ezsh.alipay.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.springframework.format.Printer;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alipay.api.AlipayApiException;
import com.alipay.api.internal.util.AlipaySignature;
import com.zeng.ezsh.alipay.config.AlipayConfig;
import com.zeng.ezsh.alipay.service.AliPayVerifyAsynService;
import com.zeng.ezsh.wy.entity.BenefitRecord;
import com.zeng.ezsh.wy.entity.ChargeInfo;
import com.zeng.ezsh.wy.entity.ChargeRecord;
import com.zeng.ezsh.wy.entity.GoodsOrder;
import com.zeng.ezsh.wy.entity.UserTeacherFee;
import com.zeng.ezsh.wy.service.BenefitApplyService;
import com.zeng.ezsh.wy.service.BenefitRecordService;
import com.zeng.ezsh.wy.service.ChargeRecordService;
import com.zeng.ezsh.wy.service.GoodsOrderService;
import com.zeng.ezsh.wy.service.UserTeacherFeeService;
import com.zeng.ezsh.wy.utils.DateUtil;
/**
 * @description 异步通知验证支付结果信息接口
 *
 * @author qwc
 */
@Controller
@RequestMapping(value = "aliPayNotice")
public class AlipayAsynNoticeAction {
	private static Logger logger = Logger
			.getLogger(AlipayAsynNoticeAction.class);
	@Resource
	AliPayVerifyAsynService aliPayVerifyAsynService;

	@RequestMapping(value = "gateWayInform")
	public void gateWayInform() {
		logger.info("I have recieve information!");
	}

	/**
	 * @description 通过支付宝异步通知进行商品支付结果校验
	 *
	 * @auhtor qwc 2017年9月9日下午9:22:27
	 * @param request
	 * @param response
	 * @throws IOException
	 * @return void
	 */
	@RequestMapping("verifyOrderAsyn")
	public void AsynNoticeVerification(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		PrintWriter out = response.getWriter();

		// 获取支付宝POST过来的支付通知信息
		Map<String, String> params = new HashMap<String, String>();
		Map requestParams = request.getParameterMap();
		for (Iterator iter = requestParams.keySet().iterator(); iter
				.hasNext();) {
			String name = (String) iter.next();
			String[] values = (String[]) requestParams.get(name);
			String valueStr = "";
			for (int i = 0; i < values.length; i++) {
				valueStr = (i == values.length - 1)
						? valueStr + values[i]
						: valueStr + values[i] + ",";
			}
			// 乱码解决，这段代码在出现乱码时使用。
			// valueStr = new String(valueStr.getBytes("ISO-8859-1"), "utf-8");
			logger.info("valueStr>>" + valueStr);
			params.put(name, valueStr);
		}

		try {
			// 对支付宝异步过来的通知进行验签
			boolean flag = AlipaySignature.rsaCheckV1(params,
					AlipayConfig.AlipayPublicKey,
					AlipayConfig.AliPaySignCharset, "RSA2");

			// 验签通过
			if (flag) {
				// 核实并更新后台支付订单记录的支付状态
				if (aliPayVerifyAsynService
						.verifyGoodsPayAsynAndUpadteOrder(params)) {
					out.write("success");
				} else {
					out.write("failure");
				}
			} else {
				out.write("failure");
			}
		} catch (AlipayApiException e) {
			// 发生异常返回失败
			out.write("failure");
			e.printStackTrace();
		}
	}

	/**
	 * @description 通过支付宝异步通知进行物业费的支付结果检测
	 *
	 * @auhtor qwc 2017年10月15日下午10:10:48
	 * @param request
	 * @param response
	 * @throws IOException
	 * @return void
	 */
	@RequestMapping("verifyPropertyPayAsyn")
	public void AsynNoticeVerificationForPropertyPay(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		PrintWriter out = response.getWriter();

		// 获取支付宝POST过来的支付通知信息
		Map<String, String> params = new HashMap<String, String>();
		Map requestParams = request.getParameterMap();
		for (Iterator iter = requestParams.keySet().iterator(); iter
				.hasNext();) {
			String name = (String) iter.next();
			String[] values = (String[]) requestParams.get(name);
			String valueStr = "";
			for (int i = 0; i < values.length; i++) {
				valueStr = (i == values.length - 1)
						? valueStr + values[i]
						: valueStr + values[i] + ",";
			}
			// 乱码解决，这段代码在出现乱码时使用。
			// valueStr = new String(valueStr.getBytes("ISO-8859-1"), "utf-8");
			logger.info("valueStr>>" + valueStr);
			params.put(name, valueStr);
		}

		try {
			// 对支付宝异步过来的通知进行验签
			boolean flag = AlipaySignature.rsaCheckV1(params,
					AlipayConfig.AlipayPublicKey,
					AlipayConfig.AliPaySignCharset, "RSA2");

			// 验签通过
			if (flag) {
				// 核实并更新后台支付订单记录的支付状态
				if (aliPayVerifyAsynService
						.verifyPropertyPayAsynAndUpadteOrder(params)) {
					out.write("success");
				} else {
					out.write("failure");
				}
			} else {
				out.write("failure");
			}
		} catch (AlipayApiException e) {
			// 发生异常返回失败
			out.write("failure");
			e.printStackTrace();
		}
	}

	/**
	 * @description 通过支付宝异步通知进行家教平台使用费用的支付结果检测（支付宝异步调用此接口）
	 *
	 * @auhtor qwc 2017年12月5日下午5:09:19
	 * @param request
	 * @param response
	 * @throws IOException
	 * @return void
	 */
	@RequestMapping("verifyTeacherPayAsyn")
	public void AsynNoticeVerificationForTeacherPay(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		PrintWriter out = response.getWriter();

		// 获取支付宝POST过来的支付通知信息
		Map<String, String> params = new HashMap<String, String>();
		Map requestParams = request.getParameterMap();
		for (Iterator iter = requestParams.keySet().iterator(); iter
				.hasNext();) {
			String name = (String) iter.next();
			String[] values = (String[]) requestParams.get(name);
			String valueStr = "";
			for (int i = 0; i < values.length; i++) {
				valueStr = (i == values.length - 1)
						? valueStr + values[i]
						: valueStr + values[i] + ",";
			}
			// 乱码解决，这段代码在出现乱码时使用。
			// valueStr = new String(valueStr.getBytes("ISO-8859-1"), "utf-8");
			logger.info("valueStr>>" + valueStr);
			params.put(name, valueStr);
		}

		try {
			// 对支付宝异步过来的通知进行验签
			boolean flag = AlipaySignature.rsaCheckV1(params,
					AlipayConfig.AlipayPublicKey,
					AlipayConfig.AliPaySignCharset, "RSA2");
			// 验签通过
			if (flag) {
				// 进一步核实后台家教费支付订单记录和更新后台家教费支付订单记录的支付状态
				if (aliPayVerifyAsynService
						.verifyTeacherPayAsynAndUpadteOrder(params)) {
					out.write("success");
				} else {
					out.write("failure");
				}
			} else {
				out.write("failure");
			}
		} catch (AlipayApiException e) {
			// 发生异常返回失败
			out.write("failure");
			e.printStackTrace();
		}
	}

	/**
	 * @description 通过支付宝异步通知进行公益基金的支付结果检测（支付宝异步调用此接口）
	 *
	 * @auhtor qwc 2017年12月5日下午9:16:33
	 * @param request
	 * @param response
	 * @throws IOException
	 * @return void
	 */
	@RequestMapping("verifyBenefitPayAsyn")
	public void AsynNoticeVerificationForBenefitPay(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		PrintWriter out = response.getWriter();

		// 获取支付宝POST过来的支付通知信息
		Map<String, String> params = new HashMap<String, String>();
		Map requestParams = request.getParameterMap();
		for (Iterator iter = requestParams.keySet().iterator(); iter
				.hasNext();) {
			String name = (String) iter.next();
			String[] values = (String[]) requestParams.get(name);
			String valueStr = "";
			for (int i = 0; i < values.length; i++) {
				valueStr = (i == values.length - 1)
						? valueStr + values[i]
						: valueStr + values[i] + ",";
			}
			// 乱码解决，这段代码在出现乱码时使用。
			// valueStr = new String(valueStr.getBytes("ISO-8859-1"), "utf-8");
			logger.info("valueStr>>" + valueStr);
			params.put(name, valueStr);
		}

		try {
			// 检验签名
			boolean flag = AlipaySignature.rsaCheckV1(params,
					AlipayConfig.AlipayPublicKey,
					AlipayConfig.AliPaySignCharset, "RSA2");
			// 进一步核实后台公益基金支付订单记录和更新后台公益基金支付订单记录的支付状态
			if (flag) {
				if (aliPayVerifyAsynService
						.verifyBenefitPayAsynAndUpadteOrder(params)) {
					out.write("success");
				} else {
					out.write("failure");
				}
			} else {
				out.write("failure");
			}
		} catch (AlipayApiException e) {
			// 发生异常返回失败
			out.write("failure");
			e.printStackTrace();
		}
	}
	
	/**
	 * 	通过支付宝异步通知进行停车的支付结果检测（支付宝异步调用此接口）
	 */
	@RequestMapping("verifyParkingPayAsyn")
	public void AsynNoticeVerificationForParkingPay(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		System.err.println("异步已经执行");
		PrintWriter out = response.getWriter();
		
		//获取支付宝传来的参数
		Map<String, String> params = new HashMap<String, String>();
		Map<String, String[]> requestParam = request.getParameterMap();
		
		for (Iterator iterator = requestParam.keySet().iterator(); iterator.hasNext();) {
			String name = (String) iterator.next();
			String[] value = (String[]) requestParam.get(name);
			String valueStr = "";
			for(int i = 0;i < value.length;i++){
				valueStr = (i == value.length - 1)
						? valueStr + value[i]
						: valueStr + value[i] + ",";
			}
			// 乱码解决，这段代码在出现乱码时使用。
			// valueStr = new String(valueStr.getBytes("ISO-8859-1"), "utf-8");
			logger.info("------------->name="+name+"----------->valueStr="+valueStr);
			params.put(name, valueStr);
		}
		
		try{
			boolean flag = AlipaySignature.rsaCheckV1(params,
					AlipayConfig.AlipayPublicKey, 
					AlipayConfig.AliPaySignCharset,"RSA2");
			if(flag){
				if(aliPayVerifyAsynService.verufyParkingPayAsynAndUpdateOrder(params)){
					out.print("success");
				}else{
					out.print("fail");
				}
			}else{
				logger.info("-------------------->验签不通过!");
				out.print("fail");
			}
			
		}catch(AlipayApiException e){
			e.printStackTrace();
			out.print("fail");
		}
	
	}
	
	@RequestMapping("verifyBerthPayAsyn")
	public void verifyBerthPayAsyn(HttpServletRequest request,
			HttpServletResponse response) throws IOException{
		System.out.println("异步已经执行！");
		
		PrintWriter out = response.getWriter();
		
		Map<String, String> params = new HashMap<>();
		Map<String, String[]> requestParam = request.getParameterMap();
		
		for (Iterator iterator = requestParam.keySet().iterator();iterator.hasNext();) {
			String name = (String) iterator.next();
			String[] value = (String[]) requestParam.get(name);
			String valueStr = "";
			for(int i = 0;i < value.length;i++){
				valueStr = (i == value.length - 1)
						? valueStr + value[i]
						: valueStr + value[i] + ",";
			}
			// 乱码解决，这段代码在出现乱码时使用。
			// valueStr = new String(valueStr.getBytes("ISO-8859-1"), "utf-8");
			logger.info("------------->name="+name+"----------->valueStr="+valueStr);
			params.put(name, valueStr);
		}
		try{
			boolean flag = AlipaySignature.rsaCheckV1(params,
					AlipayConfig.AlipayPublicKey, 
					AlipayConfig.AliPaySignCharset,"RSA2");
			if(flag){
				if(aliPayVerifyAsynService.verufyBerthPayAsynAndUpdateOrder(params)){
					out.print("success");
				}else{
					out.print("fail");
				}
			}else{
				logger.info("-------------------->验签不通过!");
				out.print("fail");
			}
			
		}catch(AlipayApiException e){
			e.printStackTrace();
			out.print("fail");
		}
	
		
	}
	
}
