package com.zeng.ezsh.wy.dao;

import java.util.List;
import java.util.Map;

import com.zeng.ezsh.wy.entity.Feedback;

public interface FeedbackMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Feedback record);

    int insertSelective(Feedback record);

    Feedback selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Feedback record);

    int updateByPrimaryKey(Feedback record);
    
    List<Feedback> getFeedbackList(Map<String,Object> map);
    
    int veryfyFeedback(Map<String,Object> map);
    
    List<Feedback> userGetFeedback(Map<String,Object> map);
    
    int getFeedbackCount(Map<String,Object> map);
    
    String getImgsUrlById(int id);
}