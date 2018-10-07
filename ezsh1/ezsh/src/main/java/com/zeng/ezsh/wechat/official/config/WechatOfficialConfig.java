package com.zeng.ezsh.wechat.official.config;

import javax.servlet.ServletContext;

import org.aspectj.weaver.ast.Var;

public class WechatOfficialConfig {
	// 接口调用凭据 access_token 必须的
	public static String access_token;

	public static final String WECHAT_SESSION_ID = "wechatSessionId";

	public static final String ENCODE_UTF_8 = "UTF-8";

	// 开发者AppID
	public static final String APPID = "wx2c484d45b96bf5fd";

	// 开发者开发者密码
	public static final String APPSECRET = "d67eaf10a26bdc912f3a44ea1b66f1e3";

	// 获取Access_token url &appid=APPID&secret=APPSECRET
	public static final String getAccessTokenUrl = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential";

	// 创建菜单栏
	public static final String createViewUrl = "https://api.weixin.qq.com/cgi-bin/menu/create?access_token=ACCESS_TOKEN";

	// 获取openId
	public static final String getOpenIdUrl = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=APPID&redirect_uri=REDIRECT_URI&response_type=code&scope=snsapi_base&state=ezsh#wechat_redirect";

	// public static String REDIRECT_URI = "";

	public static final String GET_ACCESS_TOKEN_FOR_OPENID = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=APPID&secret=SECRET&code=CODE&grant_type=authorization_code";

	public static final String GET_USER_INFO = "https://api.weixin.qq.com/sns/userinfo?access_token=ACCESS_TOKEN&openid=OPENID&lang=zh_CN";

}
