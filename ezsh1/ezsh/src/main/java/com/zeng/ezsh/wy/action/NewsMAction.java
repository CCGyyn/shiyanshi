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
import com.zeng.ezsh.wy.entity.News;
import com.zeng.ezsh.wy.entity.Notice;
import com.zeng.ezsh.wy.entity.ResidentialUser;
import com.zeng.ezsh.wy.service.NewsService;
import com.zeng.ezsh.wy.service.NoticeService;
import com.zeng.ezsh.wy.utils.AccessTokenUtil;
import com.zeng.ezsh.wy.utils.RetJsonUtil;

/**
 * 消息模块
 * 
 * @author quanweicong
 *
 */
@Controller
@RequestMapping("newsM")
public class NewsMAction {
	@Resource
	NewsService newsService;
	/**
	 * @description 获取个人消息列表
	 *
	 * @auhtor qwc 2017年10月27日下午4:56:50
	 * @param request
	 * @param response
	 * @param record
	 * @param startPage
	 * @param pageSize
	 * @throws IOException void
	 */
	@RequestMapping(value = "select")
	public void selectNotice(HttpServletRequest request,
			HttpServletResponse response, News record,
			@RequestParam(value = "startPage", required = true, defaultValue = "1") Integer startPage,
			@RequestParam(value = "pageSize", required = false, defaultValue = "10") Integer pageSize)
			throws IOException {
		PrintWriter out = response.getWriter();
		RetJsonUtil retJsonUtil = new RetJsonUtil();
		Map<String, Object> retMap = new HashMap<String, Object>();

		// token解析
		String token = request.getParameter("token");
		ResidentialUser residentialUser = AccessTokenUtil
				.parserAccessTokenToModel(token);
		// 检测该账号下是否有小区通过审核
		if (residentialUser.getUmsIdsInfo() == null) {
			retJsonUtil.setStatus("-1");
			retJsonUtil.setMessage("此账号未有小区通过审核");
			out.write(retJsonUtil.getRetJsonM());
			return;
		}

		// 设置查询条件
		record.setPtManagerId(residentialUser.getUmsIdsInfo().getpManagerId());
		record.setPtUserId(residentialUser.getUserId());

		// 如果用户ID为空，则查询结果为空
		if (record.getPtUserId() == null) {
			retMap.put("totalPage", 0);
			retMap.put("newsList", new ArrayList<News>());
			out.write(retJsonUtil.getRetJsonM());
			return;
		}

		// 执行分页获取个人信息操作
		PageHelper.startPage(startPage, pageSize);
		List<News> list = newsService.selectByPrimaryKeyM(record);
		PageInfo<News> page = new PageInfo<News>(list);

		// 返回查询结果
		retMap.put("totalPage", page.getLastPage());
		retMap.put("newsList", list);
		retJsonUtil.setRetMap(retMap);
		retJsonUtil.setMessage("获取成功");
		retJsonUtil.setStatus("1");
		out.write(retJsonUtil.getRetJsonM());
	}
}
