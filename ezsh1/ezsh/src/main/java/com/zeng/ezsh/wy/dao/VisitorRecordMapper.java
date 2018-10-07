package com.zeng.ezsh.wy.dao;

import com.zeng.ezsh.wy.entity.VisitorCode;
import com.zeng.ezsh.wy.entity.VisitorRecord;

import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface VisitorRecordMapper {
    /*int deleteByPrimaryKey(Integer visitorRecordId);*/
	
	/*添加访客记录*/
    int insert(VisitorRecord record);
    
    /*获取访客记录集合（后台）*/
	List<VisitorRecord> selectListByPrimaryKey(VisitorCode record);
	
    /*int insertSelective(VisitorRecord record);

    int updateByPrimaryKeySelective(VisitorRecord record);

    int updateByPrimaryKey(VisitorRecord record);*/
}