package com.zeng.ezsh.wy.service;

import java.util.List;
import java.util.Map;

import com.zeng.ezsh.wy.entity.TeacherResume;

public interface TeacherResumeService {
	/*添加个人简历*/
	public int insertSelective(TeacherResume record);
	
	/*获取家教老师需求列表*/
	public List<TeacherResume> selectByPrimaryKey(Map<String, Object> param);
	
	/*完善个人简历*/
	public int updateByPrimaryKeySelective(TeacherResume record);
}
