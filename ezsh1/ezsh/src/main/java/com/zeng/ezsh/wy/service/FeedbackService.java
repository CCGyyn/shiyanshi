package com.zeng.ezsh.wy.service;

import java.util.List;
import java.util.Map;

import com.zeng.ezsh.wy.entity.Feedback;

public interface FeedbackService {
	
	//用户提交反馈
	public int addFeedback(Feedback feedback);
	
	//客服获取反馈
	public List<Feedback> getFeedback(Map<String,Object> map);
	
	//客服审核反馈
	public int veryfyFeedback(Map<String,Object> map);
	
	//用户获取意见记录
	public List<Feedback> userGetFeedback(Map<String,Object> map);
	
	//获取用户反馈总数
	public int getFeedbackCount(Map<String,Object> map);
	
	//获取意见记录图片路径
	public String getImgsUrlById(int id);
	
	//删除意见记录
	public int deleteFeedback(int id);

}
