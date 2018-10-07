package com.zeng.ezsh.wy.dao;

import com.zeng.ezsh.wy.entity.BerthOrder;

public interface BerthOrderMapper {

	int insertBerthOrder(BerthOrder berthOrder);
	
	int updateBerthOrder(BerthOrder berthOrder);
	
	BerthOrder getBerthOrderByOrderNum(String outTradeNo);
}
