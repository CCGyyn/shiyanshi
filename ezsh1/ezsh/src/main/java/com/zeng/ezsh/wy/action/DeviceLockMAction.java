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

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zeng.ezsh.wy.entity.DeviceLock;
import com.zeng.ezsh.wy.entity.ResidentialUser;
import com.zeng.ezsh.wy.service.DeviceLockService;
import com.zeng.ezsh.wy.utils.AccessTokenUtil;
import com.zeng.ezsh.wy.utils.RetJsonUtil;

/**
 * @description 门锁操作类移动端
 *
 * @author qwc
 */
@Controller
@RequestMapping(value = "deviceLockM")
public class DeviceLockMAction {
	@Resource
	DeviceLockService deviceLockService;

	/**
	 * @description 获取门锁列表
	 *
	 * @auhtor qwc 2018年2月1日 下午3:29:10
	 * @param record
	 * @param request
	 * @param response
	 * @param startPage
	 * @param pageSize
	 * @throws IOException void
	 */
	@RequestMapping("selectList")
	public void selectList(DeviceLock record, HttpServletRequest request,
			HttpServletResponse response,
			@RequestParam(value = "startPage", required = true, defaultValue = "1") Integer startPage,
			@RequestParam(value = "pageSize", required = false, defaultValue = "10") Integer pageSize)
			throws IOException {
		PrintWriter out = response.getWriter();
		Map<String, Object> paramMap = new HashMap<String, Object>();// 查询条件
		RetJsonUtil retJsonUtil = new RetJsonUtil();// 创建返回json对象

		// token 解析
		String accessToken = request.getParameter("token");// 获取登录token
		ResidentialUser residentialUser = AccessTokenUtil
				.parserAccessTokenToModel(accessToken);

		// 查询
		record.setPtUserId(residentialUser.getUserId()); // 暂存userId，用于数据层搜索作为查询条件
		List<DeviceLock> list = new ArrayList<DeviceLock>();// 创建查询结果保存对象
		PageHelper.startPage(startPage, pageSize);// 分页获取
		list = deviceLockService.selectListDeviceByParamM(record);// 获取
		PageInfo<DeviceLock> page = new PageInfo<DeviceLock>(list);

		// 返回结果
		Map<String, Object> retMap = new HashMap<String, Object>();
		retMap.put("deviceLockList", list);
		retMap.put("totalPage", page.getPages());
		retJsonUtil.setStatus("1");
		retJsonUtil.setMessage("获取成功");
		retJsonUtil.setRetMap(retMap);
		out.write(retJsonUtil.getRetJsonM());
	}
}
