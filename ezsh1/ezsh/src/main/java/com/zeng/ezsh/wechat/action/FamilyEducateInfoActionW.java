package com.zeng.ezsh.wechat.action;

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
import com.zeng.ezsh.wy.entity.FamilyEducateInfo;
import com.zeng.ezsh.wy.service.FamilyEducateInfoService;
import com.zeng.ezsh.wy.utils.AccessTokenUtil;
import com.zeng.ezsh.wy.utils.RetJsonUtil;

@Controller
@RequestMapping("educateInfoW")
public class FamilyEducateInfoActionW {
	@Resource
	FamilyEducateInfoService familyEducateInfoService;

	/**
	 * @author qwc 2017年11月29日下午4:14:27
	 * @param request
	 * @param response
	 * @param record
	 * @param startPage
	 * @param pageSize
	 * @throws IOException void 微信端获取家长家教需求
	 */
	@RequestMapping(value = "select")
	public void getList(HttpServletRequest request,
			HttpServletResponse response, FamilyEducateInfo record,
			@RequestParam(value = "startPage", required = false, defaultValue = "1") Integer startPage,
			@RequestParam(value = "pageSize", required = false, defaultValue = "10") Integer pageSize)
			throws IOException {
		PrintWriter out = response.getWriter();
		RetJsonUtil retJsonUtil = new RetJsonUtil();
		Map<String, Object> retMap = new HashMap<String, Object>();

		// 执行查询
		List<FamilyEducateInfo> list = new ArrayList<FamilyEducateInfo>();
		PageHelper.startPage(startPage, pageSize);
		list = familyEducateInfoService.selectByPrimaryKey(record);
		PageInfo<FamilyEducateInfo> page = new PageInfo<FamilyEducateInfo>(
				list);

		// 返回参数
		retMap.put("totalPage", page.getPages());
		retMap.put("familyEducateInfoList", list);
		retJsonUtil.setStatus("1");
		retJsonUtil.setMessage("获取成功");
		retJsonUtil.setRetMap(retMap);
		out.write(retJsonUtil.getRetJsonM());
	}
}
