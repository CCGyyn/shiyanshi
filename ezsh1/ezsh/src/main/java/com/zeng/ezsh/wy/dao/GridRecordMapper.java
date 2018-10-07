package com.zeng.ezsh.wy.dao;

import com.zeng.ezsh.wy.entity.GridRecord;

import java.util.List;

import net.sf.jsqlparser.statement.update.Update;

import org.apache.ibatis.annotations.Param;

public interface GridRecordMapper {
	/*添加抄表记录*/
    int insert(GridRecord record);
    
    /*更新单条抄表记录*/
    int updateByPrimaryKeySelective(GridRecord record);
    
    /*删除单条抄表记录*/
    int deletePrimaryKeySelective(GridRecord record);
    
    /*批量插入抄表记录集合*/
    int insertGridRecordBatch(List<GridRecord> gridRecordList);
    
    /*获取抄表记录集合*/
    List<GridRecord> selectGridRecord(GridRecord record);

    int insertSelective(GridRecord record);
    
    /*批量更新抄表记录*/
    int updateGridRecordBatch(List<GridRecord> gridRcord);
}