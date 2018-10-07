package com.zeng.ezsh.wy.action;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexAction {

	/**
	 * @author qwc 2017年10月11日下午3:21:22
	 * @return String 跳转到官网首页（用于申请认证）
	 */
	@RequestMapping("/")
	public String Index() {
		return "for_check/index";
	}
}
