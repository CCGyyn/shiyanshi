package com.zeng.ezsh.alipay.service;

import java.util.Map;

import com.zeng.ezsh.alipay.utils.AlipayTradeCloseReqParam;
import com.zeng.ezsh.wy.entity.BenefitRecord;
import com.zeng.ezsh.wy.entity.ChargeInfo;
import com.zeng.ezsh.wy.entity.GoodsOrder;
import com.zeng.ezsh.wy.entity.ParkRecord;
import com.zeng.ezsh.wy.entity.UserTeacherFee;
/**
 * @description 支付宝支付接口
 *
 * @author qwc
 */
public interface AlipayService {
	/**
	 * @description 关闭支付宝交易订单
	 *
	 * @param additionMap
	 * @return boolean
	 */
	public boolean closeAliPayOrder(
			AlipayTradeCloseReqParam alipayTradeReqParam);

	/**
	 * @description 创建支付宝商品支付订单
	 *
	 * @param goodsOrder
	 * @param goodsName
	 * @return String
	 */
	public String goodsPayment(GoodsOrder goodsOrder, String goodsName);

	/**
	 * @description 查询支付宝商品支付交易订单
	 *
	 * @param goodsOrder
	 * @return boolean
	 */
	public boolean goodsPaymentTradeQuery(GoodsOrder goodsOrder);

	/**
	 * @description 创建支付宝物业缴费支付订单
	 *
	 * @param chargeInfo 物业费基本信息记录
	 * @param totalPrice 总需支付金额
	 * @param additionMap 附加参数
	 * @return String
	 */
	public String PropertyCosts(ChargeInfo chargeInfo, String totalPrice,
			Map<String, Object> additionMap);

	/**
	 * @description 创建支付宝家教缴费支付订单
	 *
	 * @param record
	 * @param totalPrice 总需支付金额
	 * @param additionMap 附加参数
	 * @return String
	 */
	public String teacherCosts(UserTeacherFee record, String totalPrice,
			Map<String, Object> additionMap);

	/**
	 * @description 创建支付宝公益基金支付订单
	 *
	 * @param record
	 * @param totalPrice 总需支付金额
	 * @return String
	 */
	public String benefitCosts(BenefitRecord record, String totalPrice,
			Map<String, Object> additionMap);

	/**
	 *  @description 支付宝停车费支付
	 */
	public String ParkingCharges(ParkRecord parkRecord);
	public String ParkingChargesByWeb(ParkRecord parkRecord);
	
	/**
	 * @description 支付宝停车管理费支付
	 */
	public String BerthCosts(Map<String,Object> map);
	
	/**
	 * @description 支付宝停车费扫码支付
	 */
	public String ParkingChargesByBarCode(Map<String, Object> addtionmap);

}
