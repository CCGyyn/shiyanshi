package com.zeng.ezsh.wy.dao;

import com.zeng.ezsh.wy.entity.ChargeInfo;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

public interface ChargeInfoMapper {
	
	/*获取缴费记录*/
	List<ChargeInfo> selectChargeListByParam(Map<String, Object> param);
	
	/*检测缴费记录的缴费状态*/
	int checkPayStatus(ChargeInfo record);
	
	/*获取单个缴费信息*/
	ChargeInfo selectChargeInfoReacord(ChargeInfo record);
	
	/*更新支付状态*/
	int updateChargeInfoPayStatus(ChargeInfo record);
	
	/*批量插入收费记录info*/
	int insertChargeInfoBatch(List<ChargeInfo> recordList);
	
	/*插入收费记录基本信息*/
	int insertSelective(ChargeInfo record);
    /*int deleteByPrimaryKey(Integer chargeInfoId);

    ChargeInfo selectByPrimaryKey(Integer chargeInfoId);
    
    int updateByPrimaryKeySelective(ChargeInfo record);

   */
}