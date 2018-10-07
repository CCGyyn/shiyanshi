package com.zeng.ezsh.wy.service;

import java.util.List;

import com.zeng.ezsh.wy.entity.VisitorCode;
import com.zeng.ezsh.wy.entity.VisitorRecord;

public interface VisitorRecordService {
	/*添加访客记录*/
	public int insert(VisitorRecord record);
	
	/*添加访客记录(访客按键开门时自动添加)*/
	public int insertAuto(VisitorRecord record);
	
	/*获取访客记录集合（后台）*/
	public List<VisitorRecord> selectListByPrimaryKey(VisitorCode record);
}
