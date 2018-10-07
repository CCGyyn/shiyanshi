package com.zeng.ezsh.wy.entity;

public class GoodsComment {
    private Integer gCommentId;

    private Integer pAppraiseId;

	private Integer pUserId;

    private String commentContent;

    private String commentTime;
    
    private ResidentialUser userInfo;

	public Integer getgCommentId() {
        return gCommentId;
    }

    public void setgCommentId(Integer gCommentId) {
        this.gCommentId = gCommentId;
    }

    public Integer getpAppraiseId() {
		return pAppraiseId;
	}

	public void setpAppraiseId(Integer pAppraiseId) {
		this.pAppraiseId = pAppraiseId;
	}

    public Integer getpUserId() {
        return pUserId;
    }

    public void setpUserId(Integer pUserId) {
        this.pUserId = pUserId;
    }

    public String getCommentContent() {
        return commentContent;
    }

    public void setCommentContent(String commentContent) {
        this.commentContent = commentContent == null ? null : commentContent.trim();
    }

    public String getCommentTime() {
        return commentTime;
    }

    public void setCommentTime(String commentTime) {
        this.commentTime = commentTime == null ? null : commentTime.trim();
    }
    
    public ResidentialUser getUserInfo() {
		return userInfo;
	}

	public void setUserInfo(ResidentialUser userInfo) {
		this.userInfo = userInfo;
	}
}