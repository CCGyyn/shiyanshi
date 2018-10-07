package com.zeng.ezsh.wy.dao;

import com.zeng.ezsh.wy.entity.Grid;
import com.zeng.ezsh.wy.entity.GridRecord;

import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface GridMapper {
	/*添加抄表类别*/
    int insert(Grid record);
    
    /*检测是否已经存在*/
    int checkIsExist(Grid record);
    
    int insertSelective(Grid record);
    
    /*修改抄表类别*/
    int updateSelective(Grid record);
    
    /*获取抄表类别集合（详细）*/
    List<Grid> selectSelective(Grid record);
    
    /*获取抄表类别集合（简要）*/
    List<Grid> selectSelectiveSimple(Grid record);
    
    /*获取抄表类型详细信息*/
    Grid selectGrid(Grid record);
    
    /*删除表计类别*/
    int deleteGrid(Grid record);
}