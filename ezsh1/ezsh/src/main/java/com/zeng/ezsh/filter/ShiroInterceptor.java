package com.zeng.ezsh.filter;

import java.security.Principal;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.AccessControlFilter;
import org.apache.shiro.web.util.WebUtils;

public class ShiroInterceptor extends AccessControlFilter {
	private static final String LOGIN_URL = "/adminLg/toLogin";// 登录链接

	@Override
	protected boolean isAccessAllowed(ServletRequest arg0, ServletResponse arg1,
			Object arg2) throws Exception {
		// TODO Auto-generated method stub
		HttpServletRequest request = (HttpServletRequest) arg0;
		HttpServletResponse response = (HttpServletResponse) arg1;
		String identify = arg0.getParameter("identify");
		System.out.println("identifyShiro>>" + identify);
		String url = request.getRequestURI();
		System.out.println("url>>" + url);
		if (url != null && url.endsWith(".css")
				|| (url != null && url.endsWith(".js"))
				|| (url != null && url.endsWith(".woff"))
				|| (url != null && url.endsWith(".ttf"))
				|| (url != null && url.endsWith(".jpg"))
				|| (url != null && url.endsWith(".png"))
				|| (url != null && url.endsWith(".gif"))
				|| (url != null && url.endsWith(".json"))
				|| (url != null && url.endsWith(".swf"))
				|| (url != null && url.endsWith(".woff2"))) { return true; }
		return true;
		/*
		 * if(identify!=null&&identify.equals("mTerminal")){//移动端 return true;
		 * }else if(url.equals("/ezsh/adminLg/toLogin")){ return true; }else
		 * if(url.equals("/ezsh/kaptcha/getKaptchaImage")){ return true; }else
		 * if(url.equals("/ezsh/adminLg/login")){//登录 return true; }else
		 * if(url.equals("/ezsh/adminLg/logout")){ return true; } return false;
		 */
	}

	@Override
	protected boolean onAccessDenied(ServletRequest arg0, ServletResponse arg1)
			throws Exception {
		// TODO Auto-generated method stub
		HttpServletRequest request = (HttpServletRequest) arg0;
		HttpServletResponse response = (HttpServletResponse) arg1;
		Subject subject = getSubject(request, response);
//		return true;
		if (subject.getPrincipal() == null) {// 表示没有登录，重定向到登录页面
			/* saveRequest(request); */
			System.out.println("跳转至登录！");
			request.getRequestDispatcher("/WEB-INF/jsp/login/login.jsp")
					.forward(request, response);
			/* WebUtils.issueRedirect(request, response, LOGIN_URL); */
			/* return true; */
		} else {
			return true;
		}
		return false;
	}

}
