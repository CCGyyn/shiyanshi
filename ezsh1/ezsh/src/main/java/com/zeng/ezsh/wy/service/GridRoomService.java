package com.zeng.ezsh.wy.service;

import java.util.List;
import java.util.Map;

import com.zeng.ezsh.wy.entity.GridRoomItem;

public interface GridRoomService {
	/*添加房间表计类型*/
    public int insert(GridRoomItem record);
    
    /*获取房间表计类型列表*/
    public List<GridRoomItem> selectGridRoomItemsByParam(Map<String, Object> param);
    
    /*删除房间表计类型*/
	public int deleteByPrimaryKey(Integer gridRoomItemId);
	
	/*查询表计类型是否已被使用*/
    public int checkGridRoomItemIsOnUse(GridRoomItem record); 
    
    /*修改房间表计类别*/
    int updateByPrimaryKeySelective(GridRoomItem record);
}
