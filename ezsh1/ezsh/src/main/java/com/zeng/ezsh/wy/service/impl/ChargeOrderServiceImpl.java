package com.zeng.ezsh.wy.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.zeng.ezsh.wy.dao.ChargeOrderMapper;
import com.zeng.ezsh.wy.entity.ChargeOrder;
import com.zeng.ezsh.wy.service.ChargeOrderService;
@Service
public class ChargeOrderServiceImpl implements ChargeOrderService {
	@Resource
	ChargeOrderMapper chargeOrderMapper;
	/**
	 * @description 生成物业费支付订单记录
	 */
	@Override
	public int insertSelective(ChargeOrder record) {
		// TODO Auto-generated method stub
		return chargeOrderMapper.insertSelective(record);
	}

	/**
	 * @description 获取物业费支付订单记录（移动端）
	 */
	@Override
	public ChargeOrder selectByParam(ChargeOrder record) {
		// TODO Auto-generated method stub
		return chargeOrderMapper.selectByParam(record);
	}

	/**
	 * @description 更新物业费支付订单记录（移动端）
	 */
	@Override
	public int updateByParamSelective(ChargeOrder record) {
		// TODO Auto-generated method stub
		return chargeOrderMapper.updateByParamSelective(record);
	}

}
