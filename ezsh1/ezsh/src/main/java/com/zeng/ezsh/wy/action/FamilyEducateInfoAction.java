package com.zeng.ezsh.wy.action;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zeng.ezsh.alipay.service.AlipayService;
import com.zeng.ezsh.wy.entity.BenefitApplyWithBLOBs;
import com.zeng.ezsh.wy.entity.FamilyEducateInfo;
import com.zeng.ezsh.wy.entity.GoodsAppraise;
import com.zeng.ezsh.wy.entity.ResidentialUser;
import com.zeng.ezsh.wy.service.BenefitApplyService;
import com.zeng.ezsh.wy.service.FamilyEducateInfoService;
import com.zeng.ezsh.wy.utils.AccessTokenUtil;
import com.zeng.ezsh.wy.utils.DateUtil;
import com.zeng.ezsh.wy.utils.RetJsonUtil;
import com.zeng.ezsh.wy.utils.TeacherUtil;
/**
 * @description 移动端家教模块action
 *
 * @author qwc
 */
@Controller
@RequestMapping("educateInfoM")
public class FamilyEducateInfoAction {
	@Resource
	FamilyEducateInfoService familyEducateInfoService;

	/**
	 * @author qwc 2017年11月29日下午3:42:55
	 * @param request
	 * @param response
	 * @param record
	 * @throws IOException 家长端添加家教需求 void
	 */
	@RequestMapping("add")
	public void add(HttpServletRequest request, HttpServletResponse response,
			FamilyEducateInfo record) throws IOException {
		PrintWriter out = response.getWriter();
		RetJsonUtil retJsonUtil = new RetJsonUtil();

		// token解析
		Map<String, Object> tokenMap = new HashMap<String, Object>();
		String token = request.getParameter("token");
		tokenMap = AccessTokenUtil.parserAccessTokenToMap(token);
		int userId = (int) tokenMap.get("userId");
		int managerId = (int) tokenMap.get("managerId");

		// 执行审核操作
		record.setPtManagerId(managerId);
		record.setPtUserId(userId); // 设置userId
		record.setAddTime(
				DateUtil.dateToStr(new Date(), DateUtil.DATE_TIME_LINE));// 设置添加时间
		int status = familyEducateInfoService.insert(record);

		ResidentialUser residentialUser = AccessTokenUtil
				.parserAccessTokenToModel(token);
		if (residentialUser.getUserTeacherFeeStatus() == 0) {
			retJsonUtil.setStatus("-2");
			retJsonUtil.setMessage("请先支付家教平台使用费");
			out.write(retJsonUtil.getRetJsonM());
			return;
		}
		if (TeacherUtil.checkIsInValid(residentialUser)) {
			retJsonUtil.setStatus("-1");
			retJsonUtil.setMessage("家教平台使用费已过期，请重新缴费");
			out.write(retJsonUtil.getRetJsonM());
			return;
		}

		// 返回结果
		if (status > 0) {
			retJsonUtil.setMessage("操作成功");
			retJsonUtil.setStatus("1");
		} else {
			retJsonUtil.setMessage("操作失败");
			retJsonUtil.setStatus("0");
		}
		out.write(retJsonUtil.getRetJsonM());
	}

	@RequestMapping(value = "select")
	public void getList(HttpServletRequest request,
			HttpServletResponse response, FamilyEducateInfo record,
			@RequestParam(value = "startPage", required = true, defaultValue = "1") Integer startPage,
			@RequestParam(value = "pageSize", required = false, defaultValue = "10") Integer pageSize)
			throws IOException {
		PrintWriter out = response.getWriter();
		RetJsonUtil retJsonUtil = new RetJsonUtil();
		Map<String, Object> retMap = new HashMap<String, Object>();

		// token解析
		Map<String, Object> tokenMap = new HashMap<String, Object>();
		String token = request.getParameter("token");
		tokenMap = AccessTokenUtil.parserAccessTokenToMap(token);
		int userId = (int) tokenMap.get("userId");

		ResidentialUser residentialUser = AccessTokenUtil
				.parserAccessTokenToModel(token);
		if (residentialUser.getUserTeacherFeeStatus() == 0) {
			retJsonUtil.setStatus("-2");
			retJsonUtil.setMessage("请先支付家教平台使用费");
			out.write(retJsonUtil.getRetJsonM());
			return;
		}
		if (TeacherUtil.checkIsInValid(residentialUser)) {
			retJsonUtil.setStatus("-1");
			retJsonUtil.setMessage("家教平台使用费已过期，请重新缴费");
			out.write(retJsonUtil.getRetJsonM());
			return;
		}

		// 执行查询
		record.setPtUserId(userId);
		List<FamilyEducateInfo> list = new ArrayList<FamilyEducateInfo>();
		PageHelper.startPage(startPage, pageSize);
		list = familyEducateInfoService.selectByPrimaryKey(record);
		PageInfo<FamilyEducateInfo> page = new PageInfo<FamilyEducateInfo>(
				list);

		// 返回参数
		retMap.put("tatalPage", page.getPages());
		retMap.put("familyEducateInfoList", list);
		retJsonUtil.setStatus("1");
		retJsonUtil.setMessage("获取成功");
		retJsonUtil.setRetMap(retMap);
		out.write(retJsonUtil.getRetJsonM());
	}

	/**
	 * @author qwc 2017年12月4日下午11:06:23
	 * @param request
	 * @param response
	 * @param record
	 * @throws IOException void 删除家长家教需求
	 */
	@RequestMapping(value = "delete")
	public void delete(HttpServletRequest request, HttpServletResponse response,
			FamilyEducateInfo record) throws IOException {
		PrintWriter out = response.getWriter();
		RetJsonUtil retJsonUtil = new RetJsonUtil();

		// token解析
		Map<String, Object> tokenMap = new HashMap<String, Object>();
		String token = request.getParameter("token");
		tokenMap = AccessTokenUtil.parserAccessTokenToMap(token);
		ResidentialUser residentialUser = AccessTokenUtil
				.parserAccessTokenToModel(token);
		int userId = (int) tokenMap.get("userId");

		if (residentialUser.getUserTeacherFeeStatus() == 0) {
			retJsonUtil.setStatus("-2");
			retJsonUtil.setMessage("请先支付家教平台使用费");
			out.write(retJsonUtil.getRetJsonM());
			return;
		}
		if (TeacherUtil.checkIsInValid(residentialUser)) {
			retJsonUtil.setStatus("-1");
			retJsonUtil.setMessage("家教平台使用费已过期，请重新缴费");
			out.write(retJsonUtil.getRetJsonM());
			return;
		}

		// 执行删除
		record.setPtUserId(userId);
		record.setDelStatus(1);
		int status = familyEducateInfoService.deleteById(record);

		// 返回参数
		if (status > 0) {
			retJsonUtil.setStatus("1");
			retJsonUtil.setMessage("操作成功");
		} else {
			retJsonUtil.setStatus("0");
			retJsonUtil.setMessage("操作失败");
		}
		out.write(retJsonUtil.getRetJsonM());
	}

}
