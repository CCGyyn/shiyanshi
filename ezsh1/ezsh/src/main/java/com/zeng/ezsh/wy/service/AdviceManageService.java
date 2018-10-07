package com.zeng.ezsh.wy.service;

import java.util.List;
import java.util.Map;

import com.zeng.ezsh.wy.entity.Advice;

public interface AdviceManageService {
	
	//新增用户意见或建议 
	public int addAdvice(Advice advice);
	
	//根据小区id查询用户意见或建议
	public List<Advice> getAdvice(Map<String,Object> map);
	
	//审核
	public int auditeAdvice(Map<String,Object> map);
	
	//后台删除记录
	public int deleteByPrimaryKey(int id);
	
	//根据id查询意见
	public Advice getAdviceById(int id);
	
	//后台分页查询记录
	public List<Advice> getAdviceByPage(Map<String,Object> map);
	
	//获取意见总记录数
	public int getAdviceCount();
	
	//根据小区id和手机查询投诉记录
	public List<Advice> getAdviceByIdAndPhone(Map<String,Object> map);
	
	//根据id获取图片路径
	public String getImgsUrlById(int id);
	
}
