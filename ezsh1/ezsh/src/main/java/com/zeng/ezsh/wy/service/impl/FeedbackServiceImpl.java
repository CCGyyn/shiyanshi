package com.zeng.ezsh.wy.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zeng.ezsh.wy.dao.FeedbackMapper;
import com.zeng.ezsh.wy.entity.Feedback;
import com.zeng.ezsh.wy.service.FeedbackService;

import java.util.Map;

@Service("feedbackService")
public class FeedbackServiceImpl implements FeedbackService {

	@Autowired
	private FeedbackMapper feedbackMapper;
	
	@Override
	public int addFeedback(Feedback feedback) {
		
		return feedbackMapper.insertSelective(feedback);
	}

	@Override
	public int veryfyFeedback(Map<String, Object> map) {
		
		return feedbackMapper.veryfyFeedback(map);
	}


	@Override
	public List<Feedback> getFeedback(Map<String, Object> map) {
	
		return feedbackMapper.getFeedbackList(map);
	}

	@Override
	public List<Feedback> userGetFeedback(Map<String, Object> map) {
		
		return feedbackMapper.userGetFeedback(map);
	}

	@Override
	public int getFeedbackCount(Map<String, Object> map) {

		return feedbackMapper.getFeedbackCount(map);
	}

	@Override
	public String getImgsUrlById(int id) {

		return feedbackMapper.getImgsUrlById(id);
	}

	@Override
	public int deleteFeedback(int id) {
		
		return feedbackMapper.deleteByPrimaryKey(id);
	}

}
