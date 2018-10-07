package com.zeng.ezsh.wy.service;

import java.util.Map;

import com.zeng.ezsh.wy.entity.ParkRecord;

public interface ParkPayService {

	public String addOrder(Map<String, Object> map);
	
	public String addOrderWithBarCode(Map<String, Object> map);
	
	public String addBerthOrder(Map<String, Object> map);

}
