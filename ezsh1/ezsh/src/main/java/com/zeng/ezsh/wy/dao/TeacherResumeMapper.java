package com.zeng.ezsh.wy.dao;

import com.zeng.ezsh.wy.entity.TeacherResume;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

public interface TeacherResumeMapper {
	/*添加个人简历*/
	int insertSelective(TeacherResume record);
	
	/*获取家教老师需求列表*/
	List<TeacherResume> selectByPrimaryKey(Map<String, Object> param);
	
	/*完善个人简历*/
	int updateByPrimaryKeySelective(TeacherResume record);
	
   /* int deleteByPrimaryKey(Integer teacherResumeId);

    int insertSelective(TeacherResume record);

    int updateByPrimaryKeySelective(TeacherResume record);

    int updateByPrimaryKeyWithBLOBs(TeacherResume record);

    int updateByPrimaryKey(TeacherResume record);*/
}