package com.zeng.ezsh.wy.dao;

import java.util.List;
import java.util.Map;

import com.zeng.ezsh.wy.entity.Role;
public interface RoleMapper {
    /*添加角色*/
    int insert(Role record);

    /*根据角色ID获取角色信息*/
    Role selectByPrimaryKey(Integer roId);
    
    /*条件获取角色集合*/
    List<Role> selectByCondition(Map<Object, Object> param);
    
    /*根据ID修改角色信息*/
    int updateByPrimaryKey(Role record);
    
    /*根据角色名称查询是否重复*/
    int selectCountByRoleName(Role record);
}