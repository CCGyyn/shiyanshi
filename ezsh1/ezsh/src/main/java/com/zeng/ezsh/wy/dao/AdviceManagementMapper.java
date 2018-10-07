package com.zeng.ezsh.wy.dao;

import java.util.List;
import java.util.Map;

import com.zeng.ezsh.wy.entity.Advice;

public interface AdviceManagementMapper {
	
	List<Advice> selectByCommunityIdAndPage(Map<String,Object> map);
	
	int deleteByPrimaryKey(int id);
	
	int addAdvice(Advice advice);
	
	int auditeAdvice(Map<String,Object> map);
	
	Advice getAdviceById(int id);
	
	List<Advice> getAdviceByPage(Map<String,Object> map);
	
	int getAdviceCount();
	
	List<Advice> getAdviceByIdAndPhone(Map<String,Object> map);
	
	String getImgsUrlById(int id);
}