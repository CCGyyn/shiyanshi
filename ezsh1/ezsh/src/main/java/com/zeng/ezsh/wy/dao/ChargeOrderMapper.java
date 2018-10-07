package com.zeng.ezsh.wy.dao;

import com.zeng.ezsh.wy.entity.ChargeOrder;

public interface ChargeOrderMapper {
	/**
	 * @description 生成物业费支付订单记录
	 *
	 * @auhtor qwc
	 * @param record
	 * @return
	 * int
	 */
	int insertSelective(ChargeOrder record);
	
	/**
	 * @description 获取物业费支付订单记录（移动端）
	 *
	 * @auhtor qwc
	 * @param chargeOrderId
	 * @return
	 * ChargeOrder
	 */
	ChargeOrder selectByParam(ChargeOrder record);
	
	/**
	 * @description 更新物业费支付订单记录（移动端）
	 *
	 * @auhtor qwc
	 * @param record
	 * @return
	 * int
	 */
	int updateByParamSelective(ChargeOrder record);
	
	
    /*int deleteByPrimaryKey(Integer chargeOrderId);

    int insert(ChargeOrder record);

    int updateByPrimaryKey(ChargeOrder record);*/
}