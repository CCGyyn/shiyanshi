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

import com.zeng.ezsh.wy.entity.ResidentialUser;
import com.zeng.ezsh.wy.entity.UserTeacherFee;
import com.zeng.ezsh.wy.service.UserTeacherFeeService;
import com.zeng.ezsh.wy.utils.AccessTokenUtil;
import com.zeng.ezsh.wy.utils.GetIpAdrr;
import com.zeng.ezsh.wy.utils.RetJsonUtil;

/**
 * @description 家长家教平台使用费操作类
 *
 * @author qwc
 */
@Controller
@RequestMapping(value = "userTeacherFeeA")
public class UserTeacherFeeMAction {
	@Resource
	UserTeacherFeeService userTeacherFeeService;
	/**
	 * @description 预创建家教平台使用费支付订单
	 *
	 * @auhtor qwc 2018年1月12日 下午2:12:27
	 * @param request
	 * @param payMethod 支付方法： aliPay 和 wechatPay 两种
	 * @param response
	 * @param userTeacherFee
	 * @throws IOException void
	 */
	@RequestMapping(value = "teacherFeePay")
	public void teacherFeePay(HttpServletRequest request,
			@RequestParam(value = "payMethod", defaultValue = "aliPay") String payMethod,
			HttpServletResponse response, UserTeacherFee userTeacherFee)
			throws IOException {
		PrintWriter out = response.getWriter();
		// 创建json返回对象
		RetJsonUtil retJsonUtil = new RetJsonUtil();

		// token解析
		String token = request.getParameter("token");
		ResidentialUser residentialUser = AccessTokenUtil
				.parserAccessTokenToModel(token);

		// 获取客户端的真实IP地址
		String realIp = GetIpAdrr.getIpAddr(request);
		Map<String, Object> additionMap = new HashMap<String, Object>();
		additionMap.put("realIp", realIp);

		// 生成订单信息
		String orderInfo = userTeacherFeeService.insertOrder(userTeacherFee,
				residentialUser, payMethod, additionMap);

		// 封装返回结果
		if (orderInfo != null) {
			Map<String, Object> retMap = new HashMap<String, Object>();
			retMap.put("orderInfo", orderInfo);
			retJsonUtil.setStatus("1");
			retJsonUtil.setMessage("提交订单成功");
			retJsonUtil.setRetMap(retMap);
		} else {
			retJsonUtil.setStatus("0");
			retJsonUtil.setMessage("提交订单失败");
		}
		out.write(retJsonUtil.getRetJsonM());
	}
	
	
}
