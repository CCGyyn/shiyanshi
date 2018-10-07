package com.zeng.ezsh.wy.dao;

import java.util.List;

import com.zeng.ezsh.wy.entity.HkType;
import com.zeng.ezsh.wy.entity.HkTypeVo;

public interface HkTypeMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(HkType record);

    int insertSelective(HkType record);

    HkType selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(HkType record);

    int updateByPrimaryKey(HkType record);
    
    int getTypeId(String hkType);
    
    String getTypeById(int typeId);
    
    List<HkType> getHkItems(int hkId);
    
    List<HkTypeVo> getHkTypes(int hkId);
}