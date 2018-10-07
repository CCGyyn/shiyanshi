package com.zeng.ezsh.filter;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.zeng.ezsh.wechat.official.config.WechatOfficialConfig;
import com.zeng.ezsh.wy.entity.Teacher;
import com.zeng.ezsh.wy.utils.AccessTokenUtil;

public class LoginInterceptor implements HandlerInterceptor {

	@Override
	public void afterCompletion(HttpServletRequest arg0,
			HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1,
			Object arg2, ModelAndView arg3) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object arg2) throws Exception {
		// TODO Auto-generated method stub
		String url = request.getRequestURI();
		String contentType = request.getContentType();
		String token = request.getParameter("token");
		JSONObject retJson = new JSONObject();
		Map<String, Object> userLgInfoMap = new HashMap<String, Object>();
		String identify = ((HttpServletRequest) request)
				.getParameter("identify");
		System.out.println("contentType" + contentType);
		Subject subject = SecurityUtils.getSubject();
		if (url != null && url.endsWith(".css")
				|| (url != null && url.endsWith(".js"))
				|| (url != null && url.endsWith(".woff"))
				|| (url != null && url.endsWith(".ttf"))
				|| (url != null && url.endsWith(".jpg"))
				|| (url != null && url.endsWith(".png"))
				|| (url != null && url.endsWith(".gif"))
				|| (url != null && url.endsWith(".json"))
				|| (url != null && url.endsWith(".txt"))
				|| (url != null && url.endsWith(".swf"))
				|| (url != null && url.endsWith(".woff2"))) { return true; }

		if (url.indexOf("park") >= 0) { return true; // 停车系统接口放行
		}
		
		if (url.endsWith("paymanage")||url.endsWith("payto")
				||url.endsWith("paysuccess")||url.endsWith("paysuccessful")){		//放行网页支付
			System.out.println("允许访问！");
			return true;
		}
		
		if (url.contains("/aliPayNotice/")
				|| url.contains("/wechatPayNotice/")
				|| url.contains("/alipay/")
				|| url.contains("/wechatPay/")) {
			System.out.print("允许访问！");
			return true;
		}

		if (identify != null && identify.equals("mTerminal")) {
			if (url.equals("/ezsh/andrLg/lg") || url.equals("/andrLg/lg")) {// 放行安卓登录接口，不需要检验token
				System.out.print("允许访问！");
				return true;
			}

			if (url.equals("/ezsh/ur/rgUser") || url.equals("/ur/rgUser")) {// 放行安卓注册接口，不需要检验token
				System.out.print("允许访问！");
				return true;
			}

			if (url.equals("/ezsh/gt/msg") || url.equals("/gt/msg")) {// 放行安卓获取验证码接口，不需要检验token
				System.out.print("允许访问！");
				return true;
			}
			if (url.equals("/ezsh/ur/changePass")
					|| url.equals("/ur/changePass")) {// 修改密码接口，不需要检验token
				System.out.print("允许访问！");
				return true;
			}

			if (AccessTokenUtil.parserAccessTokenToMap(token) != null) {
				System.out.println(
						"contentType>>" + request.getParameter("token"));
				return true;
			} else {
//				return true;
				PrintWriter out = response.getWriter();
				retJson.put("status", -2);
				retJson.put("data", userLgInfoMap);
				retJson.put("message", "未登录");
				out.write(JSONObject.fromObject(retJson).toString());// 未登录，即accessToken过期或被篡改
			}

		} else if (url.contains("/loginW/") || url.contains("/routeW/")
				|| url.contains("/teacherW/")) {

			Teacher record = (Teacher) request.getSession()
					.getAttribute(WechatOfficialConfig.WECHAT_SESSION_ID);
			System.out
					.println("会话：" + JSONObject.fromObject(record).toString());
			return true;

		} else if (url.contains("/wechat/") || url.contains("/educateInfoW/")
				|| url.contains("/teacherResumeT/")) {
			Teacher record = (Teacher) request.getSession()
					.getAttribute(WechatOfficialConfig.WECHAT_SESSION_ID);
			System.out
					.println("会话：" + JSONObject.fromObject(record).toString());
			return true;
		} else if (url.contains("/openDoor/callBack")) { // 如果是开门的回调地址，放行
			return true;
		} else {
			if (url.equals("/")) { return true; }
			if (url.equals("/ezsh/wechat/callback")
					|| url.equals("/wechat/callback")) { return true; }
			if (url.equals("/ezsh/wechat/ctView")
					|| url.equals("/wechat/ctView")) {
				System.out.print("允许访问！");
				return true;
			}
			if (url.equals("/ezsh/adminLg/toLogin")) {
				return true;
			} else if (url.equals("/ezsh/kaptcha/getKaptchaImage")) {
				return true;
			} else if (url.equals("/ezsh/adminLg/login")) {// 登录
				return true;
			} else if (url.equals("/ezsh/adminLg/logout")) { return true; }
			
//			return true;
			if (subject.getPrincipal() == null) {// 表示没有登录，重定向到登录页面
				System.out.println("跳转至登录！");
				request.getRequestDispatcher("/WEB-INF/jsp/login/login.jsp")
						.forward(request, response);
			} else { // 登录状态
				return true;
			}
			return false;
		}
		return false;
	}

}
