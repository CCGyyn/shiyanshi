package com.zeng.ezsh.wechat.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import com.zeng.ezsh.wechat.entity.WechatViewPojo;
/**
 * @description
 *
 * @author qwc
 */
public class WechatUtil {
	/**
	 * @description 生成公众号菜单栏
	 *
	 * @auhtor qwc 2018年10月2日 下午1:35:26
	 * @return String
	 */
	public static String createView() {
		Map<String, Object> map = new HashMap<String, Object>();
		List<WechatViewPojo> list = new ArrayList<WechatViewPojo>();

		WechatViewPojo wechatViewPojo = new WechatViewPojo();
		wechatViewPojo.setType("view");
		wechatViewPojo.setName("家教信息");
		wechatViewPojo
				.setUrl("https://szykcz.com/ezsh/loginW/lg?identify=wechatT");
		list.add(wechatViewPojo);

		WechatViewPojo wechatViewPojo1 = new WechatViewPojo();
		wechatViewPojo1.setType("view");
		wechatViewPojo1.setName("公司介绍");
		wechatViewPojo1.setUrl("https://szykcz.com");
		list.add(wechatViewPojo1);
		map.put("button", list);

		return JSONObject.fromObject(map).toString();
	}
}
