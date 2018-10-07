package com.zeng.ezsh.wy.service;

import java.util.Map;

import com.zeng.ezsh.wy.entity.PlateRecordResponse;

public interface ParkNoticeService {

	public PlateRecordResponse handleNotice(Map<String,Object> map);
	
}
