package com.zeng.ezsh.wy.service;

import java.util.List;
import java.util.Map;

import com.zeng.ezsh.wy.entity.RepairRecord;

public interface RecordManageService {
	
	//业主提交报单
	public int submitRepair(RepairRecord repairRecord);
	
	//客服获取报单
	public List<RepairRecord> csGetRepairs(Map<String,Object> map);
	
	//修改报单状态
	public int changeRepairStatus(Map<String,Object> map);
	
	//审核通过后自动把结果推送给业主
	public RepairRecord pushResultToProprietor(Map<String,Object> map);
	
	//通过报单号查询报单
	public RepairRecord getRepairRecordByNumber(String repairNumber);
	
	//通过小区id获取报单数
	public int getCountBycommumityId(int communityId);
	
	//根据小区id和状态号查询各状态报单数量 
	public int getCountByCommunityIdAndStatus(Map<String,Object> map);
	
	//根据小区id获取 报单总数
	public int getAllCountByCommunityId(int communityId);
	
	//获取小区id集
	public List<Integer> getCommunityIds(Map<String,Object> map);
	
	//根据小区id获取小区名
	public String getNameByCommunityId(int id);
	
	//更新报单
	public int updateByPrimaryKeySelective(RepairRecord repairRecord);
	
	//根据小区Id和业主手机查询报单
	public List<RepairRecord> getRecordByCommunityIdAndPhone(Map<String,Object> map);

}
