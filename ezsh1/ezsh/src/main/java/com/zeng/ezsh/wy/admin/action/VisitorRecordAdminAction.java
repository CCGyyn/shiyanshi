package com.zeng.ezsh.wy.admin.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zeng.ezsh.wy.entity.VisitorCode;
import com.zeng.ezsh.wy.entity.VisitorRecord;
import com.zeng.ezsh.wy.service.VisitorRecordService;
import com.zeng.ezsh.wy.utils.RetJsonUtil;

@Controller
@RequestMapping(value = "visitRecordA")
public class VisitorRecordAdminAction {
	@Resource
	VisitorRecordService visitorRecordService;

	/**
	 * @author qwc 2017年9月24日下午8:15:57
	 * @param request
	 * @param response
	 * @param grid
	 * @throws IOException
	 * void 获取访客记录集合
	 */
	@RequestMapping(value = "select")
	public void selectGrid(
			HttpServletRequest request,
			HttpServletResponse response,
			VisitorCode record,
			@RequestParam(value = "page", required = true, defaultValue = "1") Integer startPage,
			@RequestParam(value = "rows", required = false, defaultValue = "10") Integer pageSize)
			throws IOException {
		PrintWriter out = response.getWriter();
		RetJsonUtil retJsonUtil = new RetJsonUtil(); // 创建json返回对象

		PageHelper.startPage(startPage, pageSize); // 分页获取
		List<VisitorRecord> list = visitorRecordService
				.selectListByPrimaryKey(record);
		PageInfo<VisitorRecord> page = new PageInfo<VisitorRecord>(list);

		// 返回结果
		retJsonUtil.setList(list);
		retJsonUtil.setTotal(page.getTotal());
		out.write(retJsonUtil.getRetJsonEasyGrid());
	}
}
