package com.zeng.ezsh.wy.service;

import java.util.List;
import java.util.Map;

import com.zeng.ezsh.wy.entity.FamilyEducateInfo;
import com.zeng.ezsh.wy.entity.ResidentialUser;
import com.zeng.ezsh.wy.entity.UserTeacherFee;

public interface FamilyEducateInfoService {
	/*添加家教信息*/
	public int insert(FamilyEducateInfo record);
	
	/*获取家教信息列表*/
	public List<FamilyEducateInfo> selectByPrimaryKey(FamilyEducateInfo record);
	
	/*删除家长家教需求*/
	public int deleteById(FamilyEducateInfo record);
	
}
