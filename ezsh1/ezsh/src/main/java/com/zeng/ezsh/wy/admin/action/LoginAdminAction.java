package com.zeng.ezsh.wy.admin.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.DisabledAccountException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.google.code.kaptcha.Constants;
import com.zeng.ezsh.wy.entity.Admin;
import com.zeng.ezsh.wy.service.AdminService;
import com.zeng.ezsh.wy.utils.StringMd5Util;
@Controller
@RequestMapping("adminLg")
public class LoginAdminAction{
    @Resource
    AdminService adminService;
	
	/**
	 * @author qwc
	 * 2017年10月11日下午3:37:06
	 * @return 
	 * String 跳转到登录界面
	 */
	@RequestMapping(value ="/toLogin")
	public String login() {
        if (SecurityUtils.getSubject().isAuthenticated()) {//用户已通过认证则通过
            return "redirect:main/main";
        }
        return "login/login";
    }
	
	/**
	 * @author qwc
	 * 2017年10月11日下午10:47:03
	 * @param request
	 * @return 
	 * boolean 图片验证码验证
	 */
	public boolean VerifyCodeUtil(HttpServletRequest request){
		String code=request.getParameter("code");
		String original =(String) request.getSession().getAttribute(Constants.KAPTCHA_SESSION_KEY);
		if (!StringUtils.isEmpty(code)) {
            if (code.equalsIgnoreCase(original)) {
            	return true;
            }
        }
		return false;
	}
	
	
	/**
	 * @author qwc
	 * 2017年10月12日下午2:48:58
	 * @param admin
	 * @param request
	 * @param response
	 * @throws IOException 
	 * void 后台登录
	 */
	@RequestMapping(value = "/login",method = RequestMethod.POST)
    public void Login(Admin admin,HttpServletRequest request,
    		HttpServletResponse response) throws IOException {
		PrintWriter out=response.getWriter();
        boolean checkCode=VerifyCodeUtil(request);//验证码校验
        /*if(!checkCode){
        	out.write("{\"status\":2}");
        	return;
        }*/
        if (StringUtils.isBlank(admin.getAdAccount())) {
            throw new RuntimeException("用户名不能为空");
        }
        if (StringUtils.isBlank(admin.getAdPassword())) {
            throw new RuntimeException("密码不能为空");
        }
        Admin user = adminService.selectByAccount(admin.getAdAccount());
        if (user == null) {
        	out.write("{\"status\":0}");//用户不存在
        	return;
        }
        if (!StringMd5Util.MD5Digest(admin.getAdPassword()).equals(user.getAdPassword())) {
        	out.write("{\"status\":-1}");
        	return;
        }
        SecurityUtils.getSecurityManager().logout(SecurityUtils.getSubject());//重新登录前注销
        UsernamePasswordToken token = new UsernamePasswordToken(user.getAdAccount(), user.getAdPassword());//封装门令
        Subject subject = SecurityUtils.getSubject();
        try {
			subject.login(token);
			out.write("{\"status\":1}");//登录成功返回
		} catch (AuthenticationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
	
	/**
	 * @author qwc
	 * 2017年10月12日下午10:04:09 
	 * void 退出登录
	 * @throws IOException 
	 */
	@RequestMapping("logout")
	public void logout(HttpServletRequest request,HttpServletResponse response) throws IOException {
		PrintWriter out=response.getWriter();
        Subject subject = SecurityUtils.getSubject();
        try {
			subject.logout();
			out.write("{\"status\":1}");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
    }
}
