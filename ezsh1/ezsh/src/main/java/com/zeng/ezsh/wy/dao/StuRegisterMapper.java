package com.zeng.ezsh.wy.dao;

import java.util.List;

import com.zeng.ezsh.wy.entity.NewsShow;
import com.zeng.ezsh.wy.entity.Page;
import com.zeng.ezsh.wy.entity.StuRegister;

public interface StuRegisterMapper {

	int deleteByPrimaryKey(Integer stuId);

	int insert(StuRegister record);

	StuRegister selectByPrimaryKey(Integer stuId);

	int updateByPrimaryKey(StuRegister record);

	int deleteList(String[] pks);// 通过主键（数组）批量删除数据

	int getCount(Page<NewsShow> page);// 按条件计算学生注册数量

	public List<NewsShow> queryMessageListByPage(Page<NewsShow> page);// 根据查询条件分页查询消息列表
}