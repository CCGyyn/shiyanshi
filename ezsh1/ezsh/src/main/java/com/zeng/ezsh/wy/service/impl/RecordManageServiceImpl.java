package com.zeng.ezsh.wy.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zeng.ezsh.wy.dao.RepairRecordMapper;
import com.zeng.ezsh.wy.entity.RepairRecord;
import com.zeng.ezsh.wy.service.RecordManageService;

@Service("recordManageService")
public class RecordManageServiceImpl implements RecordManageService {

	@Autowired
	private RepairRecordMapper repairMapper;
	
	@Override
	public int submitRepair(RepairRecord repairRecord) {
		return repairMapper.insertSelective(repairRecord);
	}

	@Override
	public List<RepairRecord> csGetRepairs(Map<String,Object> map) {
		return repairMapper.csGetRepair(map);
	}

	@Override
	public int changeRepairStatus(Map<String,Object> map) {
		return repairMapper.changeRecordStatus(map);
	}

	@Override
	public RepairRecord pushResultToProprietor(Map<String,Object> map) {
		if(repairMapper.changeRecordStatus(map) == 1){
			RepairRecord repairRecord = repairMapper.getRepairRecordById((String)map.get("repairNumber"));
			return repairRecord;
		}else{
			return null;
		}
		
	}
	
	public RepairRecord getRepairRecordByNumber(String repairNumber){
		return repairMapper.getRepairRecordById(repairNumber);
	}
	
	public int getCountBycommumityId(int communityId){
		return repairMapper.getCountBycommumityId(communityId);
	}

	@Override
	public int getCountByCommunityIdAndStatus(Map<String, Object> map) {
		return repairMapper.getCountByCommunityIdAndStatus(map);
	}

	@Override
	public int getAllCountByCommunityId(int communityId) {
		return repairMapper.getAllCountByCommunityId(communityId);
	}

	@Override
	public List<Integer> getCommunityIds(Map<String,Object> map) {
		return repairMapper.getCommunityIds(map);
	}

	@Override
	public String getNameByCommunityId(int id) {
		return repairMapper.getNameByCommunityId(id);
		
	}

	@Override
	public int updateByPrimaryKeySelective(RepairRecord repairRecord) {
		return repairMapper.updateByPrimaryKeySelective(repairRecord);
	}

	@Override
	public List<RepairRecord> getRecordByCommunityIdAndPhone(
			Map<String, Object> map) {
		return repairMapper.getRecordByCommunityIdAndPhone(map);
	}
	
	

}
