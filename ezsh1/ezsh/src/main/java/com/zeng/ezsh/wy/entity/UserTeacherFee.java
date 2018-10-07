package com.zeng.ezsh.wy.entity;

import java.math.BigDecimal;

public class UserTeacherFee {
    private Integer userTeacherFeeId;

    private Integer ptManagerId;

    private String managerName;

    private Integer ptUserId;

    private String userName;

    private String tradeNum;

    private String outTradeNo;
    
    private BigDecimal teacherFee;
    
    private String tradeTime;

    public Integer getUserTeacherFeeId() {
        return userTeacherFeeId;
    }

    public void setUserTeacherFeeId(Integer userTeacherFeeId) {
        this.userTeacherFeeId = userTeacherFeeId;
    }

    public Integer getPtManagerId() {
		return ptManagerId;
	}

	public void setPtManagerId(Integer ptManagerId) {
		this.ptManagerId = ptManagerId;
	}

    public String getManagerName() {
        return managerName;
    }

    public void setManagerName(String managerName) {
        this.managerName = managerName == null ? null : managerName.trim();
    }

    public Integer getPtUserId() {
		return ptUserId;
	}

	public void setPtUserId(Integer ptUserId) {
		this.ptUserId = ptUserId;
	}

	public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    public String getTradeNum() {
        return tradeNum;
    }

    public void setTradeNum(String tradeNum) {
        this.tradeNum = tradeNum == null ? null : tradeNum.trim();
    }

    public String getOutTradeNo() {
        return outTradeNo;
    }

    public void setOutTradeNo(String outTradeNo) {
        this.outTradeNo = outTradeNo == null ? null : outTradeNo.trim();
    }
    
    public BigDecimal getTeacherFee() {
		return teacherFee;
	}

	public void setTeacherFee(BigDecimal teacherFee) {
		this.teacherFee = teacherFee;
	}

	public String getTradeTime() {
        return tradeTime;
    }

    public void setTradeTime(String tradeTime) {
        this.tradeTime = tradeTime == null ? null : tradeTime.trim();
    }
}