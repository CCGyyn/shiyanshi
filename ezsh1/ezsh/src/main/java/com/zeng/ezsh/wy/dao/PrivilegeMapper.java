package com.zeng.ezsh.wy.dao;

import java.util.List;
import java.util.Map;

import com.zeng.ezsh.wy.entity.Privilege;
import com.zeng.ezsh.wy.entity.PrivilegeExample;
public interface PrivilegeMapper {
    int deleteByPrimaryKey(Integer privId);

    int insert(Privilege record);
    
    int insertSelective(Privilege record);

    Privilege selectByPrimaryKey(Integer privId);

    int updateByPrimaryKeySelective(Privilege record);

    int updateByPrimaryKey(Privilege record);
    
    /*获取权限表中的所有权限*/
    List<Privilege> selectAllPrivList(Map<Object,Object> paramPrivMap);
    
    //List<Privilege> selectByExample(PrivilegeExample example);
}