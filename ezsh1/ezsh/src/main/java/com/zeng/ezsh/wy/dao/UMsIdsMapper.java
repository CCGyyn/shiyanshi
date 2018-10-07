package com.zeng.ezsh.wy.dao;
import com.zeng.ezsh.wy.entity.BenefitApplyWithBLOBs;
import com.zeng.ezsh.wy.entity.ResidentialUser;
import com.zeng.ezsh.wy.entity.UMsIds;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

public interface UMsIdsMapper {  
    /*添加房屋认证*/
    int insert(UMsIds record);
    
    /*检测是否重复添加房屋认证*/
    int checkIsOn(UMsIds record);
    
    /*检测用户是否有某个小区通过审核*/
    int checkHasOneCommunityPass(int userId);
    
    /*小区审核失败，再次提交审核*/
    int agSubCommunityCheck(int userId,int communityId);
    
    /*通过用户ID和小区ID查询用户所添加的小区是否已经通过审核*/
    int checkHasPassByUserIdAndCommunity(UMsIds record);
    
    /*检测房下用户是否存在*/
    int checkRoomUserIsOn(UMsIds record);
    
    /*有选择性的更新用户社区信息*/
    int updateUMsSelective(UMsIds record);
    
    /*获取业主房下用户*/
    List<UMsIds> selectUMsList(Map<String, Object> param);
    
    /*业主获取自己的房间集合*/
    List<UMsIds> selectUMsRoomList(Map<String, Object> param);
    
    /*获取账号列表*/
    List<UMsIds> selectAccountList(UMsIds record);
    
    /*同过ID获取UMsIds信息*/
    UMsIds gtUserUmsInfoById(UMsIds record);

    UMsIds selectByRoomId(Integer pRoomId);
    
    /*业主删除房下用户*/
    int deleteByParam(Map<String, Object> param);
    
    /*更新用户账号信息（后台审核）*/
    int updateByPrimaryKeySelectiveForCheck(UMsIds record);
    
    /*获取账号列表（后台）*/
    List<UMsIds> selectAccountListA(ResidentialUser record);
    
    /*获取每个小区用户账号*/
    List<UMsIds> selectAccountAmount(UMsIds record);
	
	/*计算公益基金总数量*/
	public BigDecimal sumBenefitFee(UMsIds record);
	
    /*获取个人剩余公益基金列表*/
    List<UMsIds> selectBenefitList(UMsIds record);
    
    /*批量更新用户账号公益基金数额*/
    int updateUMsBenefitBatch(List<UMsIds> record);
    
    /*蓝牙开门前检测是否有权限*/
    int checkHasOpenDoorPermission(UMsIds record);
}