package com.zeng.ezsh.wy.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.zeng.ezsh.wy.utils.AccessTokenUtil;
import com.zeng.ezsh.wy.utils.MsgUtil;

@Controller
@RequestMapping("gt")
public class SendMsgAction {
	private static Logger logger = Logger.getLogger(SendMsgAction.class);

	/**
	 * @description
	 *
	 * @auhtor qwc 2017年7月30日下午10:59:53
	 * @param session
	 * @param response
	 * @param request
	 * @param telephone
	 * @throws IOException 
	 * void 发送请求获取短信验证码
	 */
	@RequestMapping("msg")
	public void sendSMS(HttpSession session, HttpServletResponse response,
			HttpServletRequest request,
			@RequestParam(value = "telephone", required = true) String telephone)
			throws IOException {
		MsgUtil aMsgUtil = new MsgUtil();
		Map<String, Object> codeMap = new HashMap<String, Object>();
		JSONObject retJson = new JSONObject();
		Map<String, Object> returnCodeMap = new HashMap<String, Object>();
		String accessToken = null;
		String result = null;
		PrintWriter out = response.getWriter();
		String codePara = random();
		logger.info("验证码为：" + codePara);
		try {
			codeMap.put("telephoneCode", codePara);
			codeMap.put("telephone", telephone);
			accessToken = AccessTokenUtil.getAccessToken(codeMap, 300);// 设置有效期
			if (accessToken != null) {
				result = aMsgUtil.reqSMS(telephone, codePara);
			}
			// String result=aMsgUtil.reqSMS(telephone, codePara);
			String statusCode = result.substring(21, 27);
			logger.info("sub=" + statusCode);
			logger.info("length=" + result.length());
			logger.info("Response content is: " + result);
			if (statusCode.equals("000000")) {
				returnCodeMap.put("accessToken", accessToken);
				retJson.put("status", 1);// 验证码已发到手机上！
				retJson.put("data", returnCodeMap);
				retJson.put("message", "验证码已发到手机上");
				out.write(JSONObject.fromObject(retJson).toString());
			}
			if (statusCode.equals("100006")) {
				retJson.put("status", 2);// 手机号不合法！
				retJson.put("data", returnCodeMap);
				retJson.put("message", "手机号不合法");
				out.write(JSONObject.fromObject(retJson).toString());
			}
			/*
			 * if(statusCode.equals("100015")){ returnCodeMap.put("status",
			 * "号码不合法！");
			 * out.write(JSONObject.fromObject(returnCodeMap).toString()); }
			 */
			if (statusCode.equals("100008")) {
				retJson.put("status", 0);// 手机号不合法！
				retJson.put("data", returnCodeMap);
				retJson.put("message", "手机号码为空");
				out.write(JSONObject.fromObject(retJson).toString());
			}
			/* 用于在本地请求测试，非通过官网IP请求验证码 */
			if (statusCode.equals("100005")) {
				retJson.put("status", 3);// 请求IP不合法！
				retJson.put("data", returnCodeMap);
				retJson.put("message", "请求IP不合法");
				out.write(JSONObject.fromObject(retJson).toString());
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	/**
	 * @author qwc 2017年7月30日下午10:59:37
	 * @return String 生成随机码
	 */
	public static String random() {
		String strRandom = "";
		for (int i = 0; i < 6; i++) {
			int rand = (int) (0 + Math.random() * 10);
			strRandom += "" + rand;
		}
		System.out.println(strRandom);
		return strRandom;
	}
}
