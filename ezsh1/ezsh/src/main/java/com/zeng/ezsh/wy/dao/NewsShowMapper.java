package com.zeng.ezsh.wy.dao;

import java.util.List;

import com.zeng.ezsh.wy.entity.NewsShow;
import com.zeng.ezsh.wy.entity.Page;

public interface NewsShowMapper {

	int deleteByPrimaryKey(Integer id);// 删除新闻

	int insert(NewsShow record);// 添加新闻

	int updateByPrimaryKey(NewsShow record);// 修改新闻

	int getCount(Page<NewsShow> page);// 按条件计算新闻总条数

	public List<NewsShow> queryMessageListByPage(Page<NewsShow> page);// 根据查询条件分页查询消息列表

	public int deleteList(String[] pks);// 通过主键（数组）批量删除数据

}