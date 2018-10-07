package com.zeng.ezsh.wy.dao;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.zeng.ezsh.wy.entity.Admin;

public interface AdminMapper {

    /*int deleteByPrimaryKey(Integer adId);*/
    
    /*添加管理员*/
    int insert(Admin record);
    
    /*检测账户是否存在*/
    int checkAccountIsIn(Admin record);
    
    /*更新管理信息*/
    int updateByPrimaryKey(Admin record);
    
    /*修改管理员密码*/
    int updatePassByPrimaryKey(Admin record);
    
    /*获取管理员列表*/
    List<Admin> selectByParam(Admin record);
    
    /*通过账号获取用户登录信息*/
    Admin selectByAccount(String account);
    
    /*根据用户ID获取用户的角色和权限*/
    /*Map<String, Set<String>> selectResourceMapByUserId(Long userId);*/
    
   /* int insertSelective(Admin record);
   
    int updateByPrimaryKeySelective(Admin record);
    */
}