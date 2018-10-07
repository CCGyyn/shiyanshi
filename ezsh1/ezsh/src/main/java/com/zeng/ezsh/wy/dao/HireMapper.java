package com.zeng.ezsh.wy.dao;

import java.util.List;

import com.zeng.ezsh.wy.entity.Hire;
import com.zeng.ezsh.wy.entity.Page;

public interface HireMapper {

	int deleteByPrimaryKey(Integer hireId);// 删除租聘信息

	int insert(List<Hire> record);// 批量增加

	int getCount(Page<Hire> page);// 获取该条件下的租聘数量

	Hire selectByPrimaryKey(Integer hireId);// 根据主键查询

	int updateByPrimaryKey(Hire record);// 根据主键修改

	List selectHireByPage(Page<Hire> page);// 分页查询
}