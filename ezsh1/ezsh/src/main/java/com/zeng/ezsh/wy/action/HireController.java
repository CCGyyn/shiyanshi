package com.zeng.ezsh.wy.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zeng.ezsh.wy.entity.Hire;
import com.zeng.ezsh.wy.entity.Page;
import com.zeng.ezsh.wy.service.HireService;

@Controller
@RequestMapping("hire")
public class HireController {
	@Autowired
	private HireService hireservice;

	// 根据条件分页查询
	@ResponseBody
	@RequestMapping("HireList")
	public Object SelectBypage(Page<Hire> pages, HttpServletRequest request,
			Hire hire) {
		String startpage = request.getParameter("page");
		String rows = request.getParameter("rows");
		String hiretype = request.getParameter("hireType");
		String hireplot = request.getParameter("hirePlot");
		// 注入查询条件：物品类型
		if (hiretype != null && hiretype != "") {

			hire.setHireType(Integer.parseInt(hiretype));
		} else {
			hire.setHireType(0);
		}
		// 注入查询条件:小区
		if (hireplot != null && hireplot != "") {

			hire.setHriePlot(hireplot);
		} else {
			hire.setHriePlot(null);
		}
		pages.setParamEntity(hire);
		if (startpage != null) {// 起始页
			pages.setPage(Integer.parseInt(startpage));
		}
		if (rows != null) {// 每页大小
			pages.setRows(Integer.parseInt(rows));
		} else {
			pages.setRows(10);
		}
		pages = hireservice.SelectByPage(pages);
		return pages.getPageMap();
	}

	// 批量添加小区租聘
	@ResponseBody
	@RequestMapping("insert")
	public Map insert(Hire hire, HttpServletRequest request,
			HttpServletResponse response) {
		String command = request.getParameter("command");// 决定是返回还是插入数据库
		HttpSession session = request.getSession();
		List<Hire> list = (List) session.getAttribute("hires");
		if ("add".equals(command)) {
			if (list == null) {
				list = new ArrayList<Hire>();

			}
			list.add(hire);
			session.setAttribute("hires", list);
			Map<String, Object> mapa = new HashMap<String, Object>();
			mapa.put("hire", list);
			return mapa;
		} else if ("save".equals(command)) {
			String info = "插入失败";

			if (list != null && list.size() > 0) {

				hireservice.insert(list);
			}
			info = "成功";

			session.removeAttribute("hires");
			Map<String, Object> mapa = new HashMap<String, Object>();
			mapa.put("state", info);
			return mapa;
		}
		return null;

	}

	// 删除
	@ResponseBody
	@RequestMapping("HiredelectByPrimary")
	public Map delectByPrimary(HttpServletRequest request) {
		Integer i = Integer.valueOf(request.getParameter("i"));
		Map<String, Object> map = new HashMap<String, Object>();
		int d = hireservice.deleteByPrimaryKey(i);
		if (d > 0) {
			map.put("state", "success");
			return map;
		} else {
			map.put("state", "false");
			return map;
		}
	}

	// 修改
	@ResponseBody
	@RequestMapping("UpdateHireByPrimarykey")
	public Map UpdateHireByPrimarykey(Hire hire) {
		int i = hireservice.updataHire(hire);
		Map<String, Object> mapa = new HashMap<String, Object>();
		if (i > 0) {
			mapa.put("state", "success");
			return mapa;
		} else {
			mapa.put("state", "false");
			return mapa;
		}
	}
}
