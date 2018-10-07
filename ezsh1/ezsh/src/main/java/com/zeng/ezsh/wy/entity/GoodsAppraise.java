package com.zeng.ezsh.wy.entity;

import java.util.Date;

public class GoodsAppraise {
    private Integer appraiseId;

    private Integer pGoodsInfoId;

	private Integer pUserId;
	
	private Integer pManagerId;

	private String appraiseContent;

    private String appraiseImg;

    private String appraiseTime;
    
    private int appraiseReplyAmount;
    
    private ResidentialUser userInfo;

	public Integer getAppraiseId() {
        return appraiseId;
    }

    public void setAppraiseId(Integer appraiseId) {
        this.appraiseId = appraiseId;
    }

    public Integer getpGoodsInfoId() {
		return pGoodsInfoId;
	}

	public void setpGoodsInfoId(Integer pGoodsInfoId) {
		this.pGoodsInfoId = pGoodsInfoId;
	}

    public Integer getpUserId() {
        return pUserId;
    }

    public void setpUserId(Integer pUserId) {
        this.pUserId = pUserId;
    }
    
    public Integer getpManagerId() {
		return pManagerId;
	}

	public void setpManagerId(Integer pManagerId) {
		this.pManagerId = pManagerId;
	}
	
    public String getAppraiseContent() {
        return appraiseContent;
    }

    public void setAppraiseContent(String appraiseContent) {
        this.appraiseContent = appraiseContent == null ? null : appraiseContent.trim();
    }

    public String getAppraiseImg() {
        return appraiseImg;
    }

    public void setAppraiseImg(String appraiseImg) {
        this.appraiseImg = appraiseImg == null ? null : appraiseImg.trim();
    }

    public String getAppraiseTime() {
		return appraiseTime;
	}

	public void setAppraiseTime(String appraiseTime) {
		this.appraiseTime = appraiseTime;
	}
    
	public int getAppraiseReplyAmount() {
		return appraiseReplyAmount;
	}

	public void setAppraiseReplyAmount(int appraiseReplyAmount) {
		this.appraiseReplyAmount = appraiseReplyAmount;
	}
    
	public ResidentialUser getUserInfo() {
		return userInfo;
	}

	public void setUserInfo(ResidentialUser userInfo) {
		this.userInfo = userInfo;
	}
}