package com.zeng.ezsh.wy.service;

import com.zeng.ezsh.wy.entity.BerthOrder;

public interface BerthOrderService {
	
	int insertBerthOrder(BerthOrder berthOrder);
	
	BerthOrder getBerthOrderByOrderNum(String OrderNum);
}
