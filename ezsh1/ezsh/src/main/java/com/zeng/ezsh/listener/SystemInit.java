package com.zeng.ezsh.listener;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.zeng.ezsh.wechat.official.config.WechatOfficialConfig;
import com.zeng.ezsh.wy.entity.WechatToken;
import com.zeng.ezsh.wy.service.WechatTokenService;
import com.zeng.ezsh.wy.utils.HttpUrlConnectionUtil;

public class SystemInit implements ServletContextListener {
	private Logger logger = Logger.getLogger(SystemInit.class);
	private static WebApplicationContext context;
	private WechatTokenService tokenService;

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		// TODO Auto-generated method stub

		context = WebApplicationContextUtils
				.getWebApplicationContext(sce.getServletContext());
		tokenService = (WechatTokenService) context
				.getBean("wechatTokenServiceImpl");

		TimerTask task = new TimerTask() {
			@Override
			public void run() {
				// task to run goes here
				Map<String, Object> param = new HashMap<String, Object>();
				param.put("appid", WechatOfficialConfig.APPID);
				param.put("secret", WechatOfficialConfig.APPSECRET);

				WechatToken wechatToken = new WechatToken();
				wechatToken = tokenService.select(wechatToken);

				logger.info("当前的access_token>>" + wechatToken.getAccessToken());

				SimpleDateFormat sdf = new SimpleDateFormat(
						"yyyy-MM-dd HH:mm:ss");
				Date nowDate = new Date();
				// access_token失效
				if (nowDate.getTime() > wechatToken.getExpiresToTime()
						.getTime()) {
					logger.info("access_token过时！");

					String reponseString = HttpUrlConnectionUtil.urlPost(
							WechatOfficialConfig.getAccessTokenUrl, param);
					JSONObject json = JSONObject.fromObject(reponseString);
					wechatToken.setAccessToken(
							json.get("access_token").toString());
					Date date = new Date(nowDate.getTime() + 6480 * 1000);
					wechatToken.setExpiresToTime(date);

					tokenService.update(wechatToken);
					WechatOfficialConfig.access_token = (String) json
							.get("access_token");
				} else {
					logger.info("access_token有效！");
					WechatOfficialConfig.access_token = wechatToken
							.getAccessToken();
				}
			}
		};
		Timer timer = new Timer();
		long delay = 5 * 1000; // 延迟两秒钟执行
		long intevalPeriod = 6480 * 1000; // 1.8个小时执行一次
		timer.scheduleAtFixedRate(task, delay, intevalPeriod);
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		// TODO Auto-generated method stub
		System.out.println("系统停止");
	}

}
