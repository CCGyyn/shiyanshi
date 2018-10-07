package com.zeng.ezsh.wy.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.zeng.ezsh.wy.entity.AdviceFeedback;
import com.zeng.ezsh.wy.entity.ResidentialUser;
import com.zeng.ezsh.wy.service.AdviceFeedbackService;
import com.zeng.ezsh.wy.utils.AccessTokenUtil;
import com.zeng.ezsh.wy.utils.DateUtil;
import com.zeng.ezsh.wy.utils.RetJsonUtil;
/**
 * @description 意见反馈操作类
 *
 * @author qwc
 */
@Controller
@RequestMapping(value = "aFeedbackM")
public class AdviceFeedbackMAction {
	@Resource
	AdviceFeedbackService adviceFeedbackService;

	/**
	 * @description 添加反馈意见
	 *
	 * @auhtor qwc 2018年2月1日 下午3:15:08
	 * @param request
	 * @param response
	 * @param record
	 * @throws IOException
	 * void
	 */
	@RequestMapping("add")
	public void add(HttpServletRequest request, HttpServletResponse response,
			AdviceFeedback record) throws IOException {
		PrintWriter out = response.getWriter();
		RetJsonUtil retJsonUtil = new RetJsonUtil();

		// token解
		String token = request.getParameter("token");
		ResidentialUser residentialUser = AccessTokenUtil
				.parserAccessTokenToModel(token);

		if (StringUtils.isBlank(record.getFeedbackContent())) {
			retJsonUtil.setMessage("操作失败,内容不能为空！");
			retJsonUtil.setStatus("-1");
			out.write(retJsonUtil.getRetJsonM());
			return;
		}
		// 设置用户ID
		record.setPtUserId(residentialUser.getUserId());
		// 设置管理处ID
		if (residentialUser.getUmsIdsInfo().getpManagerId() == null) {
			retJsonUtil.setMessage("操作失败,账号未通过审核！");
			retJsonUtil.setStatus("-2");
			out.write(retJsonUtil.getRetJsonM());
			return;
		}
		record.setPtManagerId(residentialUser.getUmsIdsInfo().getpManagerId());
		// 设置小区ID
		record.setPtBuildId(residentialUser.getUmsIdsInfo().getpBuildId());

		// 执行添加操作
		record.setAddTime(
				DateUtil.dateToStr(new Date(), DateUtil.DATE_TIME_LINE));// 设置添加时间
		int status = adviceFeedbackService.insert(record);

		// 封装返回结果
		if (status > 0) {
			retJsonUtil.setMessage("操作成功");
			retJsonUtil.setStatus("1");
		} else {
			retJsonUtil.setMessage("操作失败");
			retJsonUtil.setStatus("0");
		}
		out.write(retJsonUtil.getRetJsonM());
	}
}
