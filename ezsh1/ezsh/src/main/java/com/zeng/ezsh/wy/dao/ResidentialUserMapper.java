package com.zeng.ezsh.wy.dao;

import java.util.List;
import java.util.Map;

import com.zeng.ezsh.wy.entity.Management;
import com.zeng.ezsh.wy.entity.ResidentialUser;
import com.zeng.ezsh.wy.entity.UMsIds;
public interface ResidentialUserMapper {
    int deleteByPrimaryKey(Integer userId);
    
    /*用户注册*/
    int addUser(ResidentialUser record);
    
    /*根据用户ID更新用户信息*/
    int updateByUserId(ResidentialUser record);
    
    /*更新用户积分*/
    int updateIntegralByUserId(ResidentialUser record);
    
    /*用户登录*/
    ResidentialUser getUserLoginInfo(ResidentialUser residentialUser);
    
    /*跟更新密码*/
    int updateByUserAccount(ResidentialUser record);
    
    /*账号切换*/
    ResidentialUser getUserLoginInfoByUserId(ResidentialUser residentialUser);
    
    /*账号切换（弃用）*/
    ResidentialUser getUserLoginInfoByUserIduMsId(UMsIds uMsIds);
    
    /*根据条件获取用户信息*/
    ResidentialUser getResidentialUserByParam(Map<String,Object> param);
    
    /*根据条件获取集体用户信息*/
    List<ResidentialUser> selectUserInfoByCondition(Map<String, Object> Param);
    
    /*根据条件获取单个用户信息*/
    ResidentialUser getOneUserInfo(ResidentialUser residentialUser);
    
    /*检测用户账号是存在*/
    int checkAccountIsIn(String telephone);
    
    /*获取小区集合*/
    List<Management> getManagementByIds(List<Integer> manageIds);
    
    /*通过手机号码获取管理处ID集合*/
    List<Integer> getManageIds(String telephone);
    
    /*通过条件获取APP端注册用户列表(后台)*/
    List<ResidentialUser> getResidentialUserList(Map<String, Object> param);
    
    /*更新家教平台移动端支付记录的同时更新用户信息的支付状态和费用有效期*/
    int upSelectiveForTeacherPay(ResidentialUser record);
    
    /*获取用户积分信息（移动端）*/
    ResidentialUser getUserIntegralByUserId(ResidentialUser record);
  
}