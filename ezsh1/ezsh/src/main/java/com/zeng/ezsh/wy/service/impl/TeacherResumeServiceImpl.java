package com.zeng.ezsh.wy.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.zeng.ezsh.wy.dao.TeacherResumeMapper;
import com.zeng.ezsh.wy.entity.TeacherResume;
import com.zeng.ezsh.wy.service.TeacherResumeService;
@Service
public class TeacherResumeServiceImpl implements TeacherResumeService {
	@Resource
	TeacherResumeMapper teacherResumeMapper;

	/**
	 * @author qwc
	 * 2017年11月28日下午9:48:42
	 * @param record
	 * @return 添加个人简历
	 */
	@Override
	public int insertSelective(TeacherResume record) {
		// TODO Auto-generated method stub
		return teacherResumeMapper.insertSelective(record);
	}

	/**
	 * @author qwc
	 * 2017年11月28日下午9:50:21
	 * @param teacherResumeId
	 * @return 获取家教老师需求列表
	 */
	@Override
	public List<TeacherResume> selectByPrimaryKey(
			Map<String, Object> param) {
		// TODO Auto-generated method stub
		return teacherResumeMapper.selectByPrimaryKey(param);
	}

	/**
	 * @author qwc
	 * 2017年11月28日下午9:51:59
	 * @param record
	 * @return 更新个人简历
	 */
	@Override
	public int updateByPrimaryKeySelective(
			com.zeng.ezsh.wy.entity.TeacherResume record) {
		// TODO Auto-generated method stub
		return teacherResumeMapper.updateByPrimaryKeySelective(record);
	}

}
