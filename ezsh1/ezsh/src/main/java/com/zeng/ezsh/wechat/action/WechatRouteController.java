package com.zeng.ezsh.wechat.action;

import java.io.IOException;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import org.apache.http.HttpResponse;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.zeng.ezsh.wechat.official.config.WechatOfficialConfig;
import com.zeng.ezsh.wy.entity.Teacher;
import com.zeng.ezsh.wy.service.TeacherService;
import com.zeng.ezsh.wy.utils.HttpUrlConnectionUtil;

@Controller
@RequestMapping("routeW")
public class WechatRouteController {
	@Resource
	TeacherService teacherService;

	/*
	 * @RequestMapping(value="login") public String index(){ return
	 * "wechat/teacher/login"; }
	 * 
	 * @RequestMapping("register") public String register(){ return
	 * "wechat/teacher/register"; }
	 */

	@RequestMapping("jobList")
	public String findPass(HttpServletRequest request, HttpSession session,
			Model model) {
		String subject = request.getParameter("subject");
		model.addAttribute("subject", subject);
		return "wechat/job/list";
	}

	@RequestMapping("resume")
	public String resume(HttpServletRequest request,
			HttpServletResponse response, Model model, HttpSession session)
			throws ServletException, IOException {
		System.out.println(
				"request.getSession().getId();" + request.getSession().getId());
		Teacher record = (Teacher) session
				.getAttribute(WechatOfficialConfig.WECHAT_SESSION_ID);
		System.out.println(
				"recordPerson>>" + JSONObject.fromObject(record).toString());
		checkSession(request, response, session);
		model.addAttribute("teacher", record);
		return "wechat/teacher/resume";
	}

	@RequestMapping("wcHome")
	public String WcHome(HttpServletRequest request,
			HttpServletResponse response, Model model, HttpSession session)
			throws ServletException, IOException {
		System.out.println(
				"request.getSession().getId();" + request.getSession().getId());
		Teacher record = (Teacher) session
				.getAttribute(WechatOfficialConfig.WECHAT_SESSION_ID);
		System.out.println(
				"record1>>" + JSONObject.fromObject(record).toString());
		// checkSession(request, response, session);
		return "wechat/home";
	}

	@RequestMapping("wcPerson")
	public String person(HttpServletRequest request,
			HttpServletResponse response, Model model, HttpSession session)
			throws ServletException, IOException {
		System.out.println(
				"request.getSession().getId();" + request.getSession().getId());
		Teacher record = (Teacher) session
				.getAttribute(WechatOfficialConfig.WECHAT_SESSION_ID);
		System.out.println(
				"recordPerson>>" + JSONObject.fromObject(record).toString());
		checkSession(request, response, session);
		model.addAttribute("teacher", record);
		return "wechat/teacher/person";
	}

	@RequestMapping("center")
	public String center(HttpServletRequest request,
			HttpServletResponse response, Model model, HttpSession session)
			throws ServletException, IOException {
		System.out.println("request.getSession().getId()11;"
				+ request.getSession().getId());
		Teacher record = (Teacher) session
				.getAttribute(WechatOfficialConfig.WECHAT_SESSION_ID);
		System.out.println(
				"record2>>" + JSONObject.fromObject(record).toString());
		checkSession(request, response, session);
		// 要进行判空
		model.addAttribute("teacher", record);
		return "wechat/teacher/center";
	}

	// 检测session
	public void checkSession(HttpServletRequest request,
			HttpServletResponse response, HttpSession session)
			throws ServletException, IOException {
		Teacher record = (Teacher) session
				.getAttribute(WechatOfficialConfig.WECHAT_SESSION_ID);
		if (record == null) {
			request.getRequestDispatcher("/loginW/lg?identify=wechatT")
					.forward(request, response);
			return;
		}
	}
}
