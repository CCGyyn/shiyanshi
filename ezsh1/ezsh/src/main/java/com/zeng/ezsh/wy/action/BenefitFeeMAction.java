package com.zeng.ezsh.wy.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.zeng.ezsh.alipay.service.AlipayService;
import com.zeng.ezsh.wy.entity.BenefitFee;
import com.zeng.ezsh.wy.entity.BenefitRecord;
import com.zeng.ezsh.wy.entity.ResidentialUser;
import com.zeng.ezsh.wy.service.BenefitFeeService;
import com.zeng.ezsh.wy.service.BenefitRecordService;
import com.zeng.ezsh.wy.utils.AccessTokenUtil;
import com.zeng.ezsh.wy.utils.GetIpAdrr;
import com.zeng.ezsh.wy.utils.RetJsonUtil;
/**
 * @description 公益基金操作类
 *
 * @author qwc
 */
@Controller
@RequestMapping(value = "benefitM")
public class BenefitFeeMAction {
	@Resource
	BenefitRecordService benefitRecordService;
	@Resource
	BenefitFeeService benefitFeeService;
	@Resource
	AlipayService alipayService;

	/**
	 * @description 公益基金支付
	 *
	 * @author qwc 2017年12月6日下午3:37:27
	 * @param request
	 * @param response
	 * @param record
	 * @param payMethod 支付方式
	 * @param accessToken
	 * @throws IOException void
	 */
	@RequestMapping(value = "benefitPay")
	public void benefitPay(HttpServletRequest request,
			HttpServletResponse response, BenefitRecord record,
			@RequestParam(value = "payMethod", defaultValue = "aliPay") String payMethod,
			@RequestParam(value = "token", required = true) String accessToken)
			throws IOException {
		PrintWriter out = response.getWriter();
		RetJsonUtil retJsonUtil = new RetJsonUtil();

		// token解析
		ResidentialUser residentialUser = AccessTokenUtil
				.parserAccessTokenToModel(accessToken);

		// 微信支付时需要获取客户端的真实IP地址
		String realIp = GetIpAdrr.getIpAddr(request);
		Map<String, Object> additionMap = new HashMap<String, Object>();
		additionMap.put("realIp", realIp);

		// 获取用于提交支付的支付信息
		String orderInfo = benefitRecordService.insertOrder(record,
				residentialUser, payMethod, additionMap);

		// 封装返回结果
		Map<String, Object> retMap = new HashMap<String, Object>();
		retMap.put("orderInfo", orderInfo);
		retJsonUtil.setRetMap(retMap);
		retJsonUtil.setStatus("1");
		retJsonUtil.setMessage("操作成功");
		out.write(retJsonUtil.getRetJsonM());
	}

	/**
	 * @description 获取公益基金总金额
	 *
	 * @auhtor qwc 2018年1月7日 上午10:48:35
	 * @param request
	 * @param response
	 * @param accessToken
	 * @throws IOException
	 * void
	 */
	@RequestMapping(value = "gtBenefit")
	public void gtBenefit(HttpServletRequest request,
			HttpServletResponse response,
			@RequestParam(value = "token", required = true) String accessToken)
			throws IOException {
		PrintWriter out = response.getWriter();
		RetJsonUtil retJsonUtil = new RetJsonUtil();

		// token解析
		ResidentialUser residentialUser = AccessTokenUtil
				.parserAccessTokenToModel(accessToken);
		// 检测该账号下是否有小区通过审核
		if (residentialUser.getUmsIdsInfo() == null) {
			retJsonUtil.setStatus("-1");
			retJsonUtil.setMessage("此账号未有小区通过审核");
			out.write(retJsonUtil.getRetJsonM());
			return;
		}
		
		// 获取公益基金
		BenefitFee benefitFee = new BenefitFee();
		benefitFee.setPtManagerId(residentialUser.getUmsIdsInfo().getpManagerId());
		benefitFee=benefitFeeService.selectByPrimaryKey(benefitFee);
		
		// 封装返回结果
		Map<String, Object> retMap = new HashMap<String, Object>();
		retMap.put("benefitFeeInfo", benefitFee);
		retJsonUtil.setRetMap(retMap);;
		retJsonUtil.setStatus("1");
		retJsonUtil.setMessage("操作成功");
		out.write(retJsonUtil.getRetJsonM());
	}
}
