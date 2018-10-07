package com.zeng.ezsh.wy.service;

import java.util.List;

import com.zeng.ezsh.wy.entity.Hire;
import com.zeng.ezsh.wy.entity.Page;

public interface HireService {
	public Page<Hire> SelectByPage(Page<Hire> page);//根據條件批量查询

	public int getCount(Page<Hire> page);// 获取数据库相关信息条数

	public int insert(List<Hire>hire);// 批量插入

	public int deleteByPrimaryKey(int i);

	public int updataHire(Hire hir);
}
