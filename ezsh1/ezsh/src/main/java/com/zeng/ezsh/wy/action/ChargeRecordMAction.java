package com.zeng.ezsh.wy.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zeng.ezsh.wy.dao.ChargeRecordMapper;
import com.zeng.ezsh.wy.entity.ChargeRecord;
import com.zeng.ezsh.wy.entity.Vo.ChargeRecordVoLin;
import net.sf.json.JSONObject;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zeng.ezsh.alipay.service.AlipayService;
import com.zeng.ezsh.wy.entity.ChargeInfo;
import com.zeng.ezsh.wy.entity.ResidentialUser;
import com.zeng.ezsh.wy.service.ChargeRecordService;
import com.zeng.ezsh.wy.utils.AccessTokenUtil;
import com.zeng.ezsh.wy.utils.GetIpAdrr;
import com.zeng.ezsh.wy.utils.RetJsonUtil;
/**
 * @description 物业费操作类
 *
 * @author qwc
 */
@Controller
@RequestMapping("chargeMobileT")
public class ChargeRecordMAction {
	@Resource
	ChargeRecordService chargeRecordService;

	@Resource
	ChargeRecordMapper chargeRecordMapper;

	/**
	 * @description 获取个人应缴费记录集合
	 *
	 * @auhtor qwc 2017年11月19日下午5:00:51
	 * @param chargeInfo
	 * @param request
	 * @param response
	 * @param startPage 起始页
	 * @param pageSize 每页记录大小
	 * @throws IOException void
	 */
	@RequestMapping("chargeList")
	public void getChargeList(ChargeInfo chargeInfo, HttpServletRequest request,
			HttpServletResponse response,
			@RequestParam(value = "startPage", required = true, defaultValue = "1") Integer startPage,
			@RequestParam(value = "pageSize", required = false, defaultValue = "10") Integer pageSize)
			throws IOException {
		PrintWriter out = response.getWriter();
		response.setCharacterEncoding("UTF-8");
		// 创建返回json对象
		RetJsonUtil retJsonUtil = new RetJsonUtil();

		// token 解析
		Map<String, Object> tokenMap = new HashMap<String, Object>();// 创建token解析后结果保存对象
		String accessToken = request.getParameter("token");// 获取登录token

		tokenMap = AccessTokenUtil.parserAccessTokenToMap(accessToken);// 解析token
		int pRoomId = (int) tokenMap.get("pRoomId");
		Integer userId=(Integer) tokenMap.get("userId");

		// 返回结果
		if (pRoomId == 0) {
			retJsonUtil.setStatus("0");
			retJsonUtil.setMessage("获取失败");
			out.write(retJsonUtil.getRetJsonM());
			return;
		}

		Map<String,Object> map=new HashMap<>();
		map.put("status",0);
		map.put("pUserId",userId);
		map.put("checkStatus",1);

		// 查询

		List<ChargeRecordVoLin> list = null;// 创建查询结果保存对象
		PageHelper.startPage(startPage, pageSize);// 分页获取
		list = chargeRecordMapper.selectChargeRecordVoByPUserId(map);	//查询待缴费与欠缴费集合
		PageInfo<ChargeRecordVoLin> page = new PageInfo<ChargeRecordVoLin>(list);

		// 封装返回结果
		Map<String, Object> retMap = new HashMap<String, Object>();
		retMap.put("chargeInfo", list);
		retMap.put("totalPage", page.getPages());
		retJsonUtil.setStatus("1");
		retJsonUtil.setMessage("获取成功");
		retJsonUtil.setRetMap(retMap);
		out.write(retJsonUtil.getRetJsonM());
	}

	/**
	 * @description 生成物业缴费预支付订单
	 *
	 * @author qwc 2017年10月15日下午8:44:10
	 * @param chargeInfo
	 * @param request
	 * @param payMethod 支付方式:aliPay支付宝支付，wechatPay微信支付
	 * @param response
	 * @throws IOException void
	 */
	@RequestMapping(value = "propertyPay")
	public void propertyPay(ChargeInfo chargeInfo, HttpServletRequest request,
			@RequestParam(value = "payMethod", defaultValue = "aliPay") String payMethod,
			HttpServletResponse response) throws IOException {
		PrintWriter out = response.getWriter();

		// 创建json返回对象
		RetJsonUtil retJsonUtil = new RetJsonUtil();
		String accessToken = request.getParameter("token");// 获取登录token
		ResidentialUser tokenModel = AccessTokenUtil
				.parserAccessTokenToModel(accessToken);// 解析token

		// 检测当月的物业费缴费状态
		int status = chargeRecordService.checkPayStatus(chargeInfo);
		// 返回结果
		if (status > 0) {
			retJsonUtil.setStatus("-1");
			retJsonUtil.setMessage("已缴费");
			out.write(retJsonUtil.getRetJsonM());
			return;
		}

		// 获取客户端的真实IP地址
		String realIp = GetIpAdrr.getIpAddr(request);
		Map<String, Object> additionMap = new HashMap<String, Object>();
		additionMap.put("realIp", realIp);

		String orderInfo = chargeRecordService.ceratePayInfo(chargeInfo,
				payMethod, tokenModel, additionMap);// 生成提交至支付宝的物业缴费订单信息

		// 返回结果
		if (orderInfo != null) {
			Map<String, Object> retMap = new HashMap<String, Object>();
			retMap.put("orderInfo", orderInfo);
			retJsonUtil.setStatus("1");
			retJsonUtil.setMessage("订单已提交成功");
			retJsonUtil.setRetMap(retMap);
		} else {
			retJsonUtil.setStatus("0");
			retJsonUtil.setMessage("提交订单失败");
		}
		out.write(retJsonUtil.getRetJsonM());
	}

	/**
	 * @author qwc 2017年11月19日下午5:06:48
	 * @param request
	 * @param response
	 * @param startPage
	 * @param pageSize
	 * @throws IOException void
	 */
	@RequestMapping("chargeHistory")
	public void getChargeHistoryList(HttpServletRequest request,
			HttpServletResponse response,
			@RequestParam(value = "startPage", required = false, defaultValue = "1") Integer startPage,
			@RequestParam(value = "pageSize", required = false, defaultValue = "10") Integer pageSize,
			@RequestParam(value = "queryDate", required = false, defaultValue = "10") String queryDate)
			throws IOException {
		PrintWriter out = response.getWriter();
		response.setCharacterEncoding("UTF-8");
		// 创建返回json对象
		RetJsonUtil retJsonUtil = new RetJsonUtil();

		// token 解析
		Map<String, Object> tokenMap = new HashMap<String, Object>();// 创建token解析后结果保存对象
		String accessToken = request.getParameter("token");// 获取登录token

		tokenMap = AccessTokenUtil.parserAccessTokenToMap(accessToken);// 解析token
		int pRoomId = (int) tokenMap.get("pRoomId");
		Integer userId=(Integer) tokenMap.get("userId");

		// 返回结果
		if (pRoomId == 0) {
			retJsonUtil.setStatus("0");
			retJsonUtil.setMessage("获取失败");
			out.write(retJsonUtil.getRetJsonM());
			return;
		}

		Map<String,Object> map=new HashMap<>();
		map.put("status",1);
		map.put("pUserId",userId);
		map.put("checkStatus",1);

		// 查询

		List<ChargeRecordVoLin> list = null;// 创建查询结果保存对象
		PageHelper.startPage(startPage, pageSize);// 分页获取
		list = chargeRecordMapper.selectChargeRecordVoByPUserId(map);	//查询待缴费与欠缴费集合
		PageInfo<ChargeRecordVoLin> page = new PageInfo<ChargeRecordVoLin>(list);

		// 封装返回结果
		Map<String, Object> retMap = new HashMap<String, Object>();
		retMap.put("chargeInfo", list);
		retMap.put("totalPage", page.getPages());
		retJsonUtil.setStatus("1");
		retJsonUtil.setMessage("获取成功");
		retJsonUtil.setRetMap(retMap);
		out.write(retJsonUtil.getRetJsonM());
	}
}
