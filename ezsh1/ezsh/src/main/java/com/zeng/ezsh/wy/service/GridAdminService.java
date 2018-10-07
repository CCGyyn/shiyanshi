package com.zeng.ezsh.wy.service;

import java.util.List;

import com.zeng.ezsh.wy.entity.Grid;
import com.zeng.ezsh.wy.entity.GridRecord;

public interface GridAdminService {
	/*添加抄表类别*/
    public int insert(Grid record);
    
    /*检测是否已经存在*/
    public int checkIsExist(Grid record);
    
    /*修改抄表类别*/
    public int updateSelective(Grid record);
    
    /*获取抄表类别集合*/
    public List<Grid> selectSelective(Grid record);
    
    /*获取抄表类别集合（简要）*/
    public List<Grid> selectSelectiveSimple(Grid record);
    
    /*获取抄表类型详细信息*/
    public Grid selectGrid(Grid record);
    
    /*删除表计类别*/
    public int deleteGrid(Grid record);
}
