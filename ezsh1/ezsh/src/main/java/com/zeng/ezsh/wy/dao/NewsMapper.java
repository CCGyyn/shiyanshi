package com.zeng.ezsh.wy.dao;

import java.util.List;

import com.zeng.ezsh.wy.entity.News;

public interface NewsMapper {
	/*添加个人消息*/
	int insert(News record);
	
	/*删除个人消息*/
	int deleteByPrimaryKey(News record);
	
	/*查询个人消息*/
	List<News> selectByPrimaryKey(News record);
	
	/*更新个人消息*/
	int updateByPrimaryKeySelective(News record);
	
	/*获取个人信息移动端*/
	List<News> selectByPrimaryKeyM(News record);
    /*
    int insertSelective(News record);

    int updateByPrimaryKey(News record);*/
}