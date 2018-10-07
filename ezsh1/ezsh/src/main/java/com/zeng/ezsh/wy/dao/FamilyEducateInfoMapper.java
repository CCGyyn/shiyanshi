package com.zeng.ezsh.wy.dao;

import com.zeng.ezsh.wy.entity.FamilyEducateInfo;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

public interface FamilyEducateInfoMapper {
	/*添加家教需求信息*/
	int insert(FamilyEducateInfo record);
	
	/*获取家教需求信息列表*/
	List<FamilyEducateInfo> selectByPrimaryKey(FamilyEducateInfo record);
	
	/*删除家长家教需求*/
	int deleteById(FamilyEducateInfo record);
    /*int deleteByPrimaryKey(Integer familyEducateInfoId);

    int insertSelective(FamilyEducateInfo record);

    int updateByPrimaryKeySelective(FamilyEducateInfo record);

    int updateByPrimaryKeyWithBLOBs(FamilyEducateInfo record);

    int updateByPrimaryKey(FamilyEducateInfo record);*/
}