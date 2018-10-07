package com.zeng.ezsh.wy.service;

import java.util.List;

import com.zeng.ezsh.wy.entity.News;

public interface NewsService {
	/*添加个人消息*/
	public int insert(News record);
	
	/*删除个人消息*/
	public int deleteByPrimaryKey(News record);
	
	/*查询个人消息*/
	public List<News> selectByPrimaryKey(News record);
	
	/*更新个人消息*/
	public int updateByPrimaryKeySelective(News record);
	
	/*获取个人信息移动端*/
	public List<News> selectByPrimaryKeyM(News record);
}
