package com.zeng.ezsh.wy.utils;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.beanutils.converters.BigDecimalConverter;

import com.zeng.ezsh.wy.entity.ResidentialUser;

/**
 * @description 商城模块的工具类
 *
 * @author qwc
 */
public class ShoppingMallUtil {

	/**
	 * @description 用户积分转换成相应的金额
	 *
	 * @auhtor qwc 2018年1月29日 下午7:40:02
	 * @return
	 * @return BigDecimal
	 */
	public static BigDecimal userIntegralToMoney(BigDecimal userIntegral) {
		// 根据自定义积分规则表示为10积分一毛钱，100积分一块钱
		return userIntegral.divide(new BigDecimal(10));
	}

	/**
	 * @description 用户购买商品时支付的money所对应能获取的积分
	 *
	 * @auhtor qwc 2018年1月29日 下午7:50:12
	 * @param money
	 * @return
	 * @return BigDecimal
	 */
	public static BigDecimal MoneyToUserIntegral(BigDecimal money) {
		// 根据自定义积分规则100块的商品可获得20积分来算
		return money.divide(new BigDecimal(5));
	}

	/**
	 * @description 检测用户积分是否有效
	 *
	 * @auhtor qwc 2018年1月30日 下午2:17:57
	 * @param integeral
	 * @param residentialUser
	 * @return boolean
	 */
	public static boolean checkIntegralIsValid(BigDecimal integral,
			ResidentialUser residentialUser) {
		if (residentialUser.getUserIntegral().compareTo(integral)==0) {
			return true;
		} else {
			return false;
		}
	}

}
