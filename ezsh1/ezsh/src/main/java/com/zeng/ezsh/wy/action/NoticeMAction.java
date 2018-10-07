package com.zeng.ezsh.wy.action;

import java.io.IOException;
import java.io.PrintWriter;
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
import com.zeng.ezsh.wy.entity.Notice;
import com.zeng.ezsh.wy.service.NoticeService;
import com.zeng.ezsh.wy.utils.AccessTokenUtil;
import com.zeng.ezsh.wy.utils.RetJsonUtil;

@Controller
@RequestMapping(value = "noticeM")
public class NoticeMAction extends BaseAction {
	@Resource
	NoticeService noticeService;

	/**
	 * @author qwc 2017年10月26日下午10:50:22
	 * @param request
	 * @param response
	 * @param record
	 * @param startPage
	 * @param pageSize
	 * @throws IOException
	 *             void 获取公告
	 */
	@RequestMapping(value = "select")
	public void selectNotice(
			HttpServletRequest request,
			HttpServletResponse response,
			Notice record,
			@RequestParam(value = "startPage", required = true, defaultValue = "1") Integer startPage,
			@RequestParam(value = "pageSize", required = false, defaultValue = "10") Integer pageSize)
			throws IOException {
		PrintWriter out = response.getWriter();
		RetJsonUtil retJsonUtil = new RetJsonUtil();
		Map<String, Object> retMap = new HashMap<String, Object>();

		Map<String, Object> tokenMap = new HashMap<String, Object>();
		String token = request.getParameter("token");
		tokenMap = AccessTokenUtil.parserAccessTokenToMap(token);
		int ptManagerId = (int) tokenMap.get("managerId");
		record.setPtManagerId(ptManagerId);

		// 分页获取
		PageHelper.startPage(startPage, pageSize);
		List<Notice> list = noticeService.selectByPrimaryKey(record);
		PageInfo<Notice> page = new PageInfo<Notice>(list);

		// 返回结果
		retMap.put("totalPage", page.getLastPage());
		retMap.put("noticeList", list);
		retJsonUtil.setRetMap(retMap);
		retJsonUtil.setMessage("获取成功");
		retJsonUtil.setStatus("1");
		out.write(retJsonUtil.getRetJsonM());
	}
}
