package com.zeng.ezsh.wy.dao;

import java.util.List;
import java.util.Map;

import com.zeng.ezsh.wy.entity.Housekeeping;

public interface HousekeepingMapper {
    int deleteByPrimaryKey(Integer hkId);

    int insert(Housekeeping record);

    int insertSelective(Housekeeping record);

    Housekeeping selectByPrimaryKey(Integer hkId);

    int updateByImg(Housekeeping record);

    int updateByPrimaryKey(Housekeeping record);
    
    List<Housekeeping> selectNoAuditedByPage(Map<String,Object> map);
    
    int audite(Map<String,Object> map);
    
    List<Housekeeping> selectAuditedList(Map<String,Object> map);
    
    int selectNoAuditedCount();
    
    int selectAuditedCount();
    
    String getHkNameById(int hkId);
    
    int getId(Map<String,Object> map);
}