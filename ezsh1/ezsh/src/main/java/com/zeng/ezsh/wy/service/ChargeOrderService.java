package com.zeng.ezsh.wy.service;

import com.zeng.ezsh.wy.entity.ChargeOrder;
/**
 * @description 物业费支付订单操作接口
 *
 * @author qwc
 */
public interface ChargeOrderService {
	/**
	 * @description 生成物业费支付订单记录
	 *
	 * @auhtor qwc
	 * @param record
	 * @return
	 * int
	 */
	public int insertSelective(ChargeOrder record);
	
	/**
	 * @description 获取物业费支付订单记录（移动端）
	 *
	 * @auhtor qwc
	 * @param chargeOrderId
	 * @return
	 * ChargeOrder
	 */
	public ChargeOrder selectByParam(ChargeOrder record);
	
	/**
	 * @description 更新物业费支付订单记录（移动端）
	 *
	 * @auhtor qwc
	 * @param record
	 * @return
	 * int
	 */
	public int updateByParamSelective(ChargeOrder record);
}
