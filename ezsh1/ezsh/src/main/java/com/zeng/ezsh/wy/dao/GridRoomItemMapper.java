package com.zeng.ezsh.wy.dao;

import com.zeng.ezsh.wy.entity.GridRoomItem;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

public interface GridRoomItemMapper {
	
	/*添加房间表计类型*/
    int insert(GridRoomItem record);
    
    /*获取房间表计类型列表*/
    List<GridRoomItem> selectGridRoomItemsByParam(Map<String, Object> param);
    
    /*删除房间表计类型*/
	int deleteByPrimaryKey(Integer gridRoomItemId);
	
	/*查询表计类型是否已被使用*/
    int checkGridRoomItemIsOnUse(GridRoomItem record);
    
    /*修改房间表计类别*/
    int updateByPrimaryKeySelective(GridRoomItem record);
    /*int deleteByPrimaryKey(Integer roomGridItemId);

    int insert(GridRoomItem record);

    int insertSelective(GridRoomItem record);

    GridRoomItem selectByPrimaryKey(Integer roomGridItemId);

    int updateByPrimaryKeySelective(GridRoomItem record);

    */
}