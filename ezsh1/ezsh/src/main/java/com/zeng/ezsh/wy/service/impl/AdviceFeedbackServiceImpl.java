package com.zeng.ezsh.wy.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.zeng.ezsh.wy.dao.AdviceFeedbackMapper;
import com.zeng.ezsh.wy.entity.AdviceFeedback;
import com.zeng.ezsh.wy.service.AdviceFeedbackService;
@Service
public class AdviceFeedbackServiceImpl implements AdviceFeedbackService {
	@Resource
	AdviceFeedbackMapper adviceFeedbackMapper;
	
	/**
	 * 添加反馈意见
	 */
	@Override
	public int insert(AdviceFeedback record) {
		// TODO Auto-generated method stub
		return adviceFeedbackMapper.insert(record);
	}

}
