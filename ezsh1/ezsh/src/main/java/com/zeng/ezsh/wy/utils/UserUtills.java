package com.zeng.ezsh.wy.utils;

import java.util.Map;
import java.util.Random;
/**
 * @description 用户工具类
 *
 * @author qwc
 */
public class UserUtills {
	/**
	 * @description 检测用户账号是否有绑定了小区（即用户账号下是否有小区通过了审核）
	 *
	 * @auhtor qwc 2017年9月5日 上午10:45:41
	 * @param tokenMap
	 * @return boolean
	 */
	public static boolean checkUserHasBoundManager(
			Map<String, Object> tokenMap) {
		if (tokenMap.containsKey("managerId")
				&& tokenMap.get("managerId").toString().equals("0")) {
			return false;
		} else {
			return true;
		}
	}
	
	/**
	 * @description 随机昵称
	 *
	 * @param length
	 * @return
	 * String
	 */
	public static String getRandomString(int length) {
		String base = "abcdefghijklmnopqrstuvwxyz0123456789";
		Random random = new Random();
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < length; i++) {
			int number = random.nextInt(base.length());
			sb.append(base.charAt(number));
		}
		return sb.toString();
	}
}
