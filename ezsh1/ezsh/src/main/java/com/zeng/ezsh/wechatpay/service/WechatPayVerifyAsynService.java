package com.zeng.ezsh.wechatpay.service;

import java.util.Map;

/**
 * @description 异步验证微信支付结果并更新订单状态接口
 *
 * @author qwc
 */
public interface WechatPayVerifyAsynService {
	/**
	 * @description 异步商品支付时微信返回的数据并更新订单状态
	 *
	 * @auhtor qwc 2018年1月12日 下午2:02:48
	 * @param retMap
	 * @return boolean
	 */
	boolean verifyGoodsPayAsynAndUpadteOrder(Map<String, String> retMap);

	/**
	 * @description 异步核实物业费支付时微信返回的数据并更新订单状态
	 *
	 * @auhtor qwc 2018年1月2日 下午3:03:09
	 * @param retMap
	 * @return boolean
	 */
	boolean verifyPropertyPayAsynAndUpadteOrder(Map<String, String> retMap);

	/**
	 * @description 异步核实家教支付时微信返回的数据并更新订单状态
	 *
	 * @auhtor qwc 2018年1月2日 下午3:03:39
	 * @param retMap
	 * @return boolean
	 */
	boolean verifyTeacherPayAsynAndUpadteOrder(Map<String, String> retMap);

	/**
	 * @description 异步核实公益基金支付时微信返回的数据并更新订单状态
	 *
	 * @auhtor qwc 2018年1月2日 下午2:04:08
	 * @param retMap
	 * @return boolean
	 */
	boolean verifyBenefitPayAsynAndUpadteOrder(Map<String, String> retMap);

	/**
	 * @description 异步核实停车费支付时微信返回的数据并更新订单状态
	 * 
	 */
	boolean verifyParkingPayAsynAndUpdateOrder(Map<String, String> retMap);
	/**
	 * @description 异步核实车位费支付时微信返回的数据并更新订单状态
	 * @param retMap
	 * @return
	 */
	boolean verifyBerthPayAsynAndUpdateOrder(Map<String, String> retMap);
	
}
