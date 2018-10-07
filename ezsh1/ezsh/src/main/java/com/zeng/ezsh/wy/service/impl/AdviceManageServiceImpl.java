package com.zeng.ezsh.wy.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zeng.ezsh.wy.dao.AdviceManagementMapper;
import com.zeng.ezsh.wy.entity.Advice;
import com.zeng.ezsh.wy.service.AdviceManageService;
@Service("adviceService")
public class AdviceManageServiceImpl implements AdviceManageService {

	@Autowired
	AdviceManagementMapper advicedao;
	
	@Override
	public int addAdvice(Advice advice) {
		return advicedao.addAdvice(advice);
		
	}

	@Override
	public List<Advice> getAdvice(Map<String,Object> map) {
		return advicedao.selectByCommunityIdAndPage(map);
	}

	@Override
	public int auditeAdvice(Map<String, Object> map) {
		return advicedao.auditeAdvice(map);
	}

	@Override
	public int deleteByPrimaryKey(int id) {
		return advicedao.deleteByPrimaryKey(id);
	}

	@Override
	public Advice getAdviceById(int id) {
		return advicedao.getAdviceById(id);
	}

	public List<Advice> getAdviceByPage(Map<String,Object> map){
		return advicedao.getAdviceByPage(map);
	}
	
	public int getAdviceCount(){
		return advicedao.getAdviceCount();
	}

	@Override
	public List<Advice> getAdviceByIdAndPhone(Map<String, Object> map) {
		return advicedao.getAdviceByIdAndPhone(map);
	}

	@Override
	public String getImgsUrlById(int id) {
		
		return advicedao.getImgsUrlById(id);
	}
}
