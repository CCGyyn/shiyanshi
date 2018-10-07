package com.zeng.ezsh.wechatpay.service;

import java.util.Map;

import com.zeng.ezsh.wechatpay.uitls.CloseOrderReqParam;
import com.zeng.ezsh.wechatpay.uitls.OrderqueryReqParam;
import com.zeng.ezsh.wy.entity.BenefitRecord;
import com.zeng.ezsh.wy.entity.ChargeInfo;
import com.zeng.ezsh.wy.entity.GoodsOrder;
import com.zeng.ezsh.wy.entity.UserTeacherFee;

public interface WechatPayAppService {
	/**
	 * @description 关闭微信支付订单
	 *
	 * @param closeOrderReqParam
	 * @return boolean
	 */
	public boolean closeOrder(CloseOrderReqParam closeOrderReqParam);

	/**
	 * @description 查询微信支付订单
	 *
	 * @auhtor qwc 2018年2月2日 下午1:59:18
	 * @param orderqueryReqParam
	 * @return boolean
	 */
	public boolean orderquery(OrderqueryReqParam orderqueryReqParam);

	/**
	 * @description 创建商品微信支付订单
	 *
	 * @param goodsOrder
	 * @param goodsName
	 * @param realIp
	 * @return Map<String,String>
	 */
	public Map<String, String> goodsPayment(GoodsOrder goodsOrder,
			String goodsName, Map<String, Object> additionMap);

	/**
	 * @description 创建物业缴费支付订单
	 *
	 * @param chargeInfo
	 * @param totalPrice
	 * @param additionMap
	 * @return Map<String,String>
	 */
	public Map<String, String> PropertyCosts(ChargeInfo chargeInfo,
			String totalPrice, Map<String, Object> additionMap);

	/**
	 * @description 创建家教平台使用费支付订单
	 *
	 * @param record
	 * @param totalPrice
	 * @param additionMap
	 * @return Map<String,String>
	 */
	public Map<String, String> teacherCosts(UserTeacherFee record,
			String totalPrice, Map<String, Object> additionMap);

	/**
	 * @description 创建公益基金支付订单
	 *
	 * @param record
	 * @param totalPrice
	 * @param additionMap
	 * @return Map<String,String>
	 */
	public Map<String, String> benefitCosts(BenefitRecord record,
			String totalPrice, Map<String, Object> additionMap);

	/** 
	 * 停车费支付 
	 * 
	 */
	public Map<String, String> ParkingCharges(Map<String, Object> map);
	/**
	 * 车位管理费支付
	 * @param map
	 * @return
	 */
	public Map<String, String> BerthCharges(Map<String, Object> map);
	
}
