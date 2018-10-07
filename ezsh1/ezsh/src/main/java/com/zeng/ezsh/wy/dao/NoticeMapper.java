package com.zeng.ezsh.wy.dao;

import com.zeng.ezsh.wy.entity.Notice;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface NoticeMapper {
	/*添加公告*/
	int insert(Notice record);
	
	/*删除公告*/
	int deleteByPrimaryKey(Notice record);
	
	/*获取公告列表*/
	List<Notice> selectByPrimaryKey(Notice record);
	
	/*修改公告信息*/
	int updateByPrimaryKeySelective(Notice record);
	
}