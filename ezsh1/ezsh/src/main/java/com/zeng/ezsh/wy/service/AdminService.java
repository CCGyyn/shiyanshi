package com.zeng.ezsh.wy.service;

import java.util.List;
import java.util.Map;
import java.util.Set;

import com.zeng.ezsh.wy.entity.Admin;

public interface AdminService {

    /*public int deleteByPrimaryKey(Integer adId);*/
    
    /*添加管理员*/
    public int insert(Admin record);
    
    /*检测账户是否存在*/
    public int checkAccountIsIn(Admin record);
    
    /*更新管理信息*/
    public int updateByPrimaryKey(Admin record);
    
    /*修改管理员密码*/
    public int updatePassByPrimaryKey(Admin record);
    
    /*获取管理员列表*/
    public List<Admin> selectByParam(Admin record);
    
    /*通过账号获取用户登录信息*/
    public Admin selectByAccount(String account);
    
    /*获取用户的权限*/
    public Map<String, Set<String>> selectResourceMapByRoleId(int roleId);
 
}
