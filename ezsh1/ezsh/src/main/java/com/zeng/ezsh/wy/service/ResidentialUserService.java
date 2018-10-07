package com.zeng.ezsh.wy.service;

import java.util.List;
import java.util.Map;

import com.zeng.ezsh.wy.entity.Management;
import com.zeng.ezsh.wy.entity.ResidentialUser;
import com.zeng.ezsh.wy.entity.UMsIds;

public interface ResidentialUserService {
	/* 添加用户*/
    public int addUser(ResidentialUser record);
    
    /*更新用户信息*/
    public int updateByUserId(ResidentialUser record);
    
    /*跟更新密码*/
    public int updateByUserAccount(ResidentialUser record);
    
    /*获取用户登录信息*/
    public ResidentialUser getUserLoginInfo(ResidentialUser residentialUser);
    
    /*账号切换*/
    public ResidentialUser getUserLoginInfoByUserId(ResidentialUser residentialUser);
    
    /**
     * @description 获取用户积分信息
     *
     * @auhtor qwc 2018年1月29日 下午10:39:41
     * @param record 
     * @return
     * ResidentialUser
     */
    public ResidentialUser getUserIntegralByUserId(ResidentialUser record);
    
    /*根据条件获取用户集合*/
    public List<ResidentialUser> selectUserInfoByCondition(Map<String, Object> Param);
    
    /*根据条件获取单个用户信息*/
    public ResidentialUser getOneUserInfo(ResidentialUser residentialUser);
    
    /*检测账户是否存在*/
    public int checkAccountIsIn(String telephone);
    
    /*通过小区ID集合获取小区信息集合*/
    public List<Management> getManagementListByIds(List<Integer> manageIdsList);
    
    /*通过手机号码获取管理处ID集合*/
    public List<Integer> getManageIds(String telephone);
    
    /*通过条件获取APP端注册用户列表(后台)*/
    public List<ResidentialUser> getResidentialUserList(Map<String, Object> param);
  
    /*进行身份证的转移*/
    public void identifyClassifyTransfer(String telephone);
}
