package com.zeng.ezsh.wy.service;

import java.util.List;

import com.zeng.ezsh.wy.entity.Teacher;

public interface TeacherService {
	/*获取微信端用户信息*/
	public Teacher selectByPrimaryKey(Teacher record);
	
	/*添加教师*/
	public int insert(Teacher record);
	
	/*获取教师列表*/
	public List<Teacher> selectTeacherList(Teacher record);
	
	/*资料补充（微信端）*/
	public int updateByPrimaryKeySelective(Teacher record);
	
	/*审核（后台）*/
	public int updateCheck(Teacher record);
	
}
