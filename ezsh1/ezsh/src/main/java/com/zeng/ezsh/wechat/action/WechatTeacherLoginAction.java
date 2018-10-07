package com.zeng.ezsh.wechat.action;

import java.io.IOException;
import java.net.URLEncoder;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.zeng.ezsh.wechat.official.config.WechatOfficialConfig;
import com.zeng.ezsh.wy.entity.Teacher;
import com.zeng.ezsh.wy.service.TeacherService;
import com.zeng.ezsh.wy.utils.HttpUrlConnectionUtil;
@Controller
@RequestMapping("loginW")
public class WechatTeacherLoginAction {
	@Resource
	TeacherService teacherService;
	
	/**
	 * @author qwc
	 * 2017年11月28日下午10:26:46
	 * @param request
	 * @param response
	 * @throws IOException 
	 * void 微信登录
	 */
	@RequestMapping(value = "lg")
	public void loginW(HttpServletRequest request, HttpServletResponse response) throws IOException{
		String pathUrl = WechatOfficialConfig.getOpenIdUrl;
		pathUrl = pathUrl.replace("APPID", WechatOfficialConfig.APPID);
		pathUrl = pathUrl.replace("REDIRECT_URI", URLEncoder.encode("https://szykcz.com/ezsh/loginW/getCode?identify=wechat", 
				"UTF-8"));// 第一步，获取code
		System.out.println("pathUrl>>"+pathUrl);
		response.sendRedirect(pathUrl);
	}
	
	/**
	 * @author qwc
	 * 2017年11月10日下午10:33:30
	 * @param request
	 * @param code （针对scope为snsapi_base时的情况获取code，然后获取openId）
	 * void
	 * @throws IOException 
	 * @throws ServletException 
	 */
	@RequestMapping(value = "getCode")
	public void getCode(HttpServletRequest request, HttpServletResponse response, HttpSession session,
			@RequestParam(value="code",required=true) String code) throws IOException, ServletException{
		String pathUrl = WechatOfficialConfig.GET_ACCESS_TOKEN_FOR_OPENID;
		pathUrl = pathUrl.replace("APPID", WechatOfficialConfig.APPID);
		pathUrl = pathUrl.replace("SECRET", WechatOfficialConfig.APPSECRET);
		pathUrl = pathUrl.replace("CODE", code);
		String getOpenId = HttpUrlConnectionUtil.urlPost(pathUrl, new String(), WechatOfficialConfig.ENCODE_UTF_8);//获取openId
		JSONObject json = JSONObject.fromObject(getOpenId);
		if(json.has("openid")){
			getOpenId = json.getString("openid");
		} else {
			getOpenId = null;
		}
		System.out.println("getOpenId>>"+getOpenId);
		if (getOpenId != null) {
			System.out.println("已访问过");
			Teacher record =new Teacher();
			record.setTeacherWcOpenId(getOpenId);
			record = teacherService.selectByPrimaryKey(record);
			if (record != null) {// 如何用户信息不为空，已授权过，直接进入界面
				session.setAttribute(WechatOfficialConfig.WECHAT_SESSION_ID, record);
				request.setAttribute(WechatOfficialConfig.WECHAT_SESSION_ID, record);
				request.getRequestDispatcher("/routeW/wcHome?identify=wechatT").forward(request, response);
			} else {// 未授权过snsapi_userinfo
				System.out.println("第一次访问");
				String pathUrl1 = WechatOfficialConfig.getOpenIdUrl.substring(0);
				pathUrl1 =  pathUrl1.replace("APPID", WechatOfficialConfig.APPID);
				pathUrl1 =  pathUrl1.replace("snsapi_base", "snsapi_userinfo");
				pathUrl1 =  pathUrl1.replace("REDIRECT_URI", URLEncoder.encode("https://szykcz.com/ezsh/loginW/getCodeUserInfo?identify=wechat", 
						WechatOfficialConfig.ENCODE_UTF_8));// 第一步，获取code
				System.out.println("pathUrl1>>"+pathUrl1);
				response.sendRedirect(pathUrl1);
			}
		} else {
			request.getRequestDispatcher("/loginW/lg?identify=wechatT").forward(request, response);
		}
	}
	
	/**
	 * @author qwc
	 * 2017年11月10日下午9:47:30
	 * @param request
	 * @param 
	 * void 获取code然后获取access_token,然后进一步获取用户的unionId等全部信息
	 */
	@RequestMapping(value = "getCodeUserInfo")
	public ModelAndView getCodeUserInfo(HttpServletRequest request, HttpSession session,
			@RequestParam(value="code",required=true) String code){
		ModelAndView modelAndView = new ModelAndView();
		String pathUrl = WechatOfficialConfig.GET_ACCESS_TOKEN_FOR_OPENID;
		pathUrl = pathUrl.replace("APPID", WechatOfficialConfig.APPID);// 开发者ID
		pathUrl = pathUrl.replace("SECRET", WechatOfficialConfig.APPSECRET);//开发者秘钥
		pathUrl = pathUrl.replace("CODE", code);
		
		// 请求openId
		String getOpenId = HttpUrlConnectionUtil
				.urlPost(pathUrl, new String(), WechatOfficialConfig.ENCODE_UTF_8);// 获取openId
		JSONObject json = JSONObject.fromObject(getOpenId);
		getOpenId = json.getString("openid");
		String accessToken = json.getString("access_token");
		
		// 请求userInfo
		if (getOpenId!=null && accessToken!=null) {
			System.out.println("获取用户信息！");
			// 请求userInfo 的url
			String pathUrlForGetUserInfo = WechatOfficialConfig.GET_USER_INFO;
			// 获取access_token
			pathUrlForGetUserInfo = pathUrlForGetUserInfo
					.replace("ACCESS_TOKEN", accessToken);
			// 获取用户openId
			pathUrlForGetUserInfo = pathUrlForGetUserInfo
					.replace("OPENID", getOpenId);
			// get请求获取userInfo
			String userInfo = HttpUrlConnectionUtil
					.urlGet(pathUrlForGetUserInfo, WechatOfficialConfig.ENCODE_UTF_8);
			// 解析响应参数为json
			JSONObject json1 = JSONObject.fromObject(userInfo);
			
			if(json1.get("openid") != null){
				System.out.println("用户信息成功》》"+userInfo);
				Teacher teacher = new Teacher();
				// 设置openId
				teacher.setTeacherWcOpenId(json1.get("openid").toString());
				if (json1.containsKey("unionid")) {
					// 设置unionId
					teacher.setTeacherWcUnionId(json1.get("unionid").toString());
				}
				teacher.setTeacherNickName(json1.get("nickname").toString());
				teacher.setTeacherIcon(json1.get("headimgurl").toString());
				// 把用户信息保存到数据库中
				teacherService.insert(teacher);
				
				// 把户信息保存到session中
				session.setAttribute(WechatOfficialConfig.WECHAT_SESSION_ID, teacher);
				modelAndView.setViewName("/wechat/home");
			} else {
				modelAndView.setViewName("/wechat/error");
				System.out.println("获取用户信息失败！");
			}
		}
		return modelAndView;
	}
}
