package com.zeng.ezsh.wy.dao;

import java.util.List;
import java.util.Map;

import com.zeng.ezsh.wy.entity.ChargeRoomIds;

public interface ChargeRoomIdsMapper {
    /*添加房间收费项目*/
    int insert(ChargeRoomIds record);
    
    /*获取房间收费项目列表*/
    List<ChargeRoomIds> selectChargeItemsByParam(Map<String, Object> param);
    
    /*删除房间收费项目*/
    int deleteByPrimaryKey(Integer chargeRoomId);
    
    /*查询所收费项目是否已被使用*/
    int checkChargeItemIsOnUse(ChargeRoomIds record); 
    
    int insertSelective(ChargeRoomIds record);

    ChargeRoomIds selectByPrimaryKey(Integer chargeRoomId);

    int updateByPrimaryKeySelective(ChargeRoomIds record);

    int updateByPrimaryKey(ChargeRoomIds record);
}