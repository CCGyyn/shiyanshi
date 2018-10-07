package com.zeng.ezsh.wechat.action;
import javax.annotation.Resource;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.zeng.ezsh.wechat.official.config.WechatOfficialConfig;
import com.zeng.ezsh.wechat.utils.WechatUtil;
import com.zeng.ezsh.wy.service.WechatTokenService;
import com.zeng.ezsh.wy.utils.HttpUrlConnectionUtil;

@Controller
@RequestMapping("wechat")
public class WechatCreateViewAction {
	private Logger logger = Logger.getLogger(WechatCreateViewAction.class);
	@Resource
	WechatTokenService wechatTokenService;
	/**
	 * @description 创建菜单栏
	 *
	 * @auhtor qwc 2018年10月2日 下午1:41:16
	 * @param request void
	 */
	@RequestMapping("ctView")
	public void CreateView() {
		// Map<String, Object> param = new HashMap<String, Object>();
		String createViewUrl = WechatOfficialConfig.createViewUrl
				.replace("ACCESS_TOKEN", WechatOfficialConfig.access_token);
		logger.info("请求参数：" + WechatUtil.createView());
		String responseString = HttpUrlConnectionUtil.urlPost(createViewUrl,
				WechatUtil.createView(), WechatOfficialConfig.ENCODE_UTF_8);
		logger.info("创建菜单结果：" + responseString);
	}

}
