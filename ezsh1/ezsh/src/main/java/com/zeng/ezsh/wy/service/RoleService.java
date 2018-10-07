package com.zeng.ezsh.wy.service;

import java.util.List;
import java.util.Map;

import com.zeng.ezsh.wy.entity.Role;

public interface RoleService {
    /*添加角色*/
    public int insert(Role record);
    
    /*根据ID修改角色信息*/
    public int updateByPrimaryKey(Role record);
    
    /*条件获取角色集合*/
    public List<Role> selectByCondition(Map<Object, Object> param);
    
    /*根据角色ID获取角色信息*/
    public Role selectByPrimaryKey(Integer roId);
    
    /*根据角色名称查询是否重复*/
    public int selectCountByRoleName(Role record);
}
