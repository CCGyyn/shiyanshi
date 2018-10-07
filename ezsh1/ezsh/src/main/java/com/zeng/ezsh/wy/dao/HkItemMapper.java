package com.zeng.ezsh.wy.dao;

import java.util.List;
import java.util.Map;

import com.zeng.ezsh.wy.entity.HkItem;

public interface HkItemMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(HkItem record);

    int insertSelective(HkItem record);

    HkItem selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(HkItem record);

    int updateByPrimaryKey(HkItem record);
    
    HkItem getHkItemById(Map<String,Object> map);
    
    List<HkItem> getItemList(Map<String,Object> map);
    
    List<HkItem> getItemByHkType(int typeId);
}