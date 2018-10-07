package com.zeng.ezsh.alipay.service;

import java.util.Map;

/**
 * @description 异步验证微信支付结果并更新订单状态接口
 *
 * @author qwc
 */
public interface AliPayVerifyAsynService {
	/**
	 * @description 异步商品支付时支付宝返回的数据并更新订单状态
	 *
	 * @param retMap
	 * @return boolean
	 */
	boolean verifyGoodsPayAsynAndUpadteOrder(Map<String, String> retMap);

	/**
	 * @description 异步核实物业费支付时支付宝返回的数据并更新订单状态
	 *
	 * @param retMap
	 * @return boolean
	 */
	boolean verifyPropertyPayAsynAndUpadteOrder(Map<String, String> retMap);

	/**
	 * @description 异步核实家教支付时支付宝返回的数据并更新订单状态
	 *
	 * @param retMap
	 * @return boolean
	 */
	boolean verifyTeacherPayAsynAndUpadteOrder(Map<String, String> retMap);

	/**
	 * @description 异步核实公益基金支付时支付宝返回的数据并更新订单状态
	 *
	 * @param retMap
	 * @return boolean
	 */
	boolean verifyBenefitPayAsynAndUpadteOrder(Map<String, String> retMap);
	
	/**
	 * @description 异步核实停车支付时支付宝返回的数据并更新订单状态
	 * @param retMap
	 * @return boolean
	 */
	boolean verufyParkingPayAsynAndUpdateOrder(Map<String, String> retMap);
	
	/**
	 * @description 异步核实停车支付时支付宝返回的数据并更新订单状态
	 * @param retMap
	 * @return boolean
	 */
	boolean verufyBerthPayAsynAndUpdateOrder(Map<String, String> retMap);
	
}
