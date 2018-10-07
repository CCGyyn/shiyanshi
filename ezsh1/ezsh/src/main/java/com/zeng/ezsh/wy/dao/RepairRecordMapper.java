package com.zeng.ezsh.wy.dao;

import java.util.List;
import java.util.Map;

import com.zeng.ezsh.wy.entity.RepairRecord;

public interface RepairRecordMapper {

	int insertSelective(RepairRecord repairRecord);
	
	List<RepairRecord> csGetRepair(Map<String,Object> map);
	
	int changeRecordStatus(Map<String,Object> map);
	
	RepairRecord getRepairRecordById(String repairNumber);
	
	int getCountBycommumityId(int communityId);
	
	int getCountByCommunityIdAndStatus(Map<String,Object> map);
	
	int getAllCountByCommunityId(int communityId);
	
	List<Integer> getCommunityIds(Map<String,Object> map);
	
	String getNameByCommunityId(int id);
	
	int updateByPrimaryKeySelective(RepairRecord repairRecord);
	
	List<RepairRecord> getRecordByCommunityIdAndPhone(Map<String,Object> map);
}
