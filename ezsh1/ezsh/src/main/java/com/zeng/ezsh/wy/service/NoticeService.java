package com.zeng.ezsh.wy.service;

import java.util.List;

import com.zeng.ezsh.wy.entity.Notice;

public interface NoticeService {
	/*添加公告*/
	public int insert(Notice record);
	
	/*删除公告*/
	public int deleteByPrimaryKey(Notice record);
	
	/*获取公告列表*/
	public List<Notice> selectByPrimaryKey(Notice record);
	
	/*修改公告信息*/
	public int updateByPrimaryKeySelective(Notice record);
}
