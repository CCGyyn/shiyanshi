package com.zeng.ezsh.wy.service;

import java.util.List;
import java.util.Map;

import com.zeng.ezsh.wy.entity.ChargeRoomIds;

public interface ChargeRoomIdsService {
	/*添加房间收费项目*/
    public int insert(ChargeRoomIds record);
    
    /*获取房间收费项目列表*/
    public List<ChargeRoomIds> selectChargeItemsByParam(Map<String, Object> param);
    
    /*删除房间收费项目*/
	public int deleteByPrimaryKey(Integer chargeRoomId);
	
	/*查询所收费项目是否已被使用*/
    public int checkChargeItemIsOnUse(ChargeRoomIds record); 
}
