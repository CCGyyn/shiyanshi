package com.zeng.ezsh.wy.dao;

import com.zeng.ezsh.wy.entity.Teacher;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TeacherMapper {
	/*获取微信端用户信息*/
	Teacher selectByPrimaryKey(Teacher record);
	
	/*添加教师*/
	int insert(Teacher record);
	
	/*获取教师列表*/
	List<Teacher> selectTeacherList(Teacher record);
	
	/*资料补充（微信端）*/
	int updateByPrimaryKeySelective(Teacher record);
	
	/*审核（后台）*/
	int updateCheck(Teacher record);

    /*int deleteByPrimaryKey(Integer teacherId);

    int insert(Teacher record);

    int insertSelective(Teacher record);
    
    Teacher selectByPrimaryKey(Integer teacherId);

    int updateByPrimaryKeySelective(Teacher record);

    int updateByPrimaryKey(Teacher record);*/
}