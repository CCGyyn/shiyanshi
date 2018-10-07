package com.zeng.ezsh.wy.entity;

public class AdviceFeedback {
    private Integer feedbackId;

    private Integer ptUserId;

    private Integer ptManagerId;

    private Integer ptBuildId;

    private String feedbackContent;
    
    private String addTime;
    
    public Integer getFeedbackId() {
        return feedbackId;
    }

    public void setFeedbackId(Integer feedbackId) {
        this.feedbackId = feedbackId;
    }

    public Integer getPtUserId() {
        return ptUserId;
    }

    public void setPtUserId(Integer ptUserId) {
        this.ptUserId = ptUserId;
    }

    public Integer getPtManagerId() {
        return ptManagerId;
    }

    public void setPtManagerId(Integer ptManagerId) {
        this.ptManagerId = ptManagerId;
    }

    public Integer getPtBuildId() {
        return ptBuildId;
    }

    public void setPtBuildId(Integer ptBuildId) {
        this.ptBuildId = ptBuildId;
    }

    public String getFeedbackContent() {
        return feedbackContent;
    }

    public void setFeedbackContent(String feedbackContent) {
        this.feedbackContent = feedbackContent == null ? null : feedbackContent.trim();
    }

	public String getAddTime() {
		return addTime;
	}

	public void setAddTime(String addTime) {
		this.addTime = addTime;
	}
    
}