package com.zeng.ezsh.wy.action;

import javax.annotation.Resource;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
/**
 * @description 页面跳转
 *
 * @author qwc
 */
@Controller
@RequestMapping("/base")
public class BaseAction {
	@Resource
	ServletContext application;
	/**
	 * @description 二级页面跳转,方法参数folder通过@PathVariable指定其值可以从@RequestMapping的{folder}获取，同理file也一样
	 *
	 * @param folder
	 * @param file
	 * @param session
	 * @return String
	 */
	//
	@RequestMapping("/goURL/{folder}/{file}")
	public String goURL(@PathVariable String folder, @PathVariable String file,
			HttpSession session) {
		/*
		 * application.setAttribute("account", session.getAttribute("account"));
		 */
		return "forward:/WEB-INF/jsp/" + folder + "/" + file + ".jsp";
	}

	/**
	 * @description 三级页面跳转 String
	 *
	 * @param folder
	 * @param file
	 * @param file1
	 * @param session
	 * @return String
	 */
	@RequestMapping("/goURLT/{folder}/{file}/{file1}")
	public String goURL(@PathVariable String folder, @PathVariable String file,
			@PathVariable String file1, HttpSession session) {
		return "forward:/WEB-INF/jsp/" + folder + "/" + file + "/" + file1
				+ ".jsp";
	}
}