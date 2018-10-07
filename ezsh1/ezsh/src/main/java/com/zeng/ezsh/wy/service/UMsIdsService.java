package com.zeng.ezsh.wy.service;

import java.util.List;
import java.util.Map;

import com.zeng.ezsh.wy.entity.ResidentialUser;
import com.zeng.ezsh.wy.entity.UMsIds;

public interface UMsIdsService {
	/*添加房屋认证*/
    public int insert(UMsIds record);
    
    /*检测是否重复添加小区*/
    public int checkIsOn(UMsIds record);
    
    /*检测用户是否有某个小区通过审核*/
    public int checkHasOneCommunityPass(int userId);
    
    /*小区审核失败，再次提交审核*/
    public int agSubCommunityCheck(int userId,int communityId);
    
    /*通过用户ID和小区ID查询用户所添加的小区是否已经通过审核*/
    public int checkHasPassByUserIdAndCommunity(UMsIds record);
    
    /*检测房下用户是否存在*/
    public int checkRoomUserIsOn(UMsIds record);
    
    /*添加房下用户*/
    public int addRoomUser(UMsIds record);
    
    /*获取业主房下用户列表*/
    public List<UMsIds> selectUMsList(Map<String, Object> param);
    
    /*业主获取自己的房间集合*/
    public List<UMsIds> selectUMsRoomList(Map<String, Object> param);
    
    /*同过ID获取UMsIds信息*/
    public UMsIds gtUserUmsInfoById(UMsIds record);
    
    /*有选择性的更新用户社区信息*/
    public int updateUMsSelective(UMsIds record);
    
    /*业主删除房下用户*/
    public int deleteByParam(Map<String, Object> param);
    
    /*获取账号列表*/
    public List<UMsIds> selectAccountList(UMsIds record);
    
    /*更新App端用户账号信息（后台审核）*/
    public int updateByPrimaryKeySelectiveForCheck(UMsIds record);
    
    /*获取账号列表（后台）*/
    public List<UMsIds> selectAccountListA(ResidentialUser record);
    
    /*获取个人剩余公益基金列表*/
    public List<UMsIds> selectBenefitList(UMsIds record);
   
    /*蓝牙开门前检测是否有权限*/
    public int checkHasOpenDoorPermission(UMsIds record);
}
