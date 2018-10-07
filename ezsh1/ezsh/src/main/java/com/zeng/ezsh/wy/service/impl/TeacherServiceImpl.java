package com.zeng.ezsh.wy.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.zeng.ezsh.wy.dao.TeacherMapper;
import com.zeng.ezsh.wy.dao.TeacherResumeMapper;
import com.zeng.ezsh.wy.entity.Teacher;
import com.zeng.ezsh.wy.entity.TeacherResume;
import com.zeng.ezsh.wy.service.TeacherService;
@Service
public class TeacherServiceImpl implements TeacherService {
	@Resource
	TeacherMapper teacherMapper;
	@Resource
	TeacherResumeMapper teacherResumeMapper;
	
	/**
	 * @author qwc
	 * 2017年11月10日下午9:36:35
	 * @param record
	 * @return 获取微信端用户信息
	 */
	@Override
	public Teacher selectByPrimaryKey(Teacher record) {
		// TODO Auto-generated method stub
		return teacherMapper.selectByPrimaryKey(record);
	}
	
	/**
	 * @author qwc
	 * 2017年11月12日下午9:37:48
	 * @param record
	 * @return 添加教师
	 */
	@Override
	public int insert(Teacher record) {
		// TODO Auto-generated method stub
		teacherMapper.insert(record);
		TeacherResume teacherResume = new TeacherResume(); 
		teacherResume.setPtTeacherId(record.getTeacherId());// 设置简历所指向的teacherId
		//teacherResumeMapper.insertSelective(teacherResume);
		return teacherResumeMapper.insertSelective(teacherResume);
	}
	
	/**
	 * @author qwc
	 * 2017年11月20日下午9:27:37
	 * @param record
	 * @return 获取教师列表
	 */
	@Override
	public List<Teacher> selectTeacherList(Teacher record) {
		// TODO Auto-generated method stub
		return teacherMapper.selectTeacherList(record);
	}
	
	/**
	 * @author qwc
	 * 2017年11月20日下午10:21:39
	 * @param record
	 * @return 微信端教师补充资料
	 */
	@Override
	public int updateByPrimaryKeySelective(Teacher record) {
		// TODO Auto-generated method stub
		return teacherMapper.updateByPrimaryKeySelective(record);
	}
	
	/**
	 * @author qwc
	 * 2017年11月20日下午10:22:04
	 * @param record
	 * @return 后台审核
	 */
	@Override
	public int updateCheck(Teacher record) {
		// TODO Auto-generated method stub
		return teacherMapper.updateCheck(record);
	}

}
