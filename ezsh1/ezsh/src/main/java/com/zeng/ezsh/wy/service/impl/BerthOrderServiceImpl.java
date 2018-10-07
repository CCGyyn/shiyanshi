package com.zeng.ezsh.wy.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.zeng.ezsh.wy.dao.BerthOrderMapper;
import com.zeng.ezsh.wy.entity.BerthOrder;
import com.zeng.ezsh.wy.service.BerthOrderService;
@Service
public class BerthOrderServiceImpl implements BerthOrderService {
	@Resource
	BerthOrderMapper berthOrderMapper;

	@Override
	public int insertBerthOrder(BerthOrder berthOrder) {
		return berthOrderMapper.insertBerthOrder(berthOrder);
	}

	@Override
	public BerthOrder getBerthOrderByOrderNum(String OrderNum) {
		return berthOrderMapper.getBerthOrderByOrderNum(OrderNum);
	}
	
	
	
}
