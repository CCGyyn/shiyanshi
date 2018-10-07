package com.zeng.ezsh.wy.entity;

import java.math.BigDecimal;

public class BenefitRecord {
    private Integer benefitRecordId;

    private Integer ptManagerId;

    private String managerName;

    private Integer ptBuildId;

    private String buildName;

    private Integer ptRoomId;

    private Integer roomNum;

    private Integer ptUserId;
    
    private String userName;
    
    private String outTradeNo;

    private String tradeNum;
    
    private BigDecimal benefitFee;
    
    private String payTime;
    
    private Integer payStatus;
    
    public Integer getBenefitRecordId() {
        return benefitRecordId;
    }

    public void setBenefitRecordId(Integer benefitRecordId) {
        this.benefitRecordId = benefitRecordId;
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

    public Integer getPtBuildId() {
        return ptBuildId;
    }

    public void setPtBuildId(Integer ptBuildId) {
        this.ptBuildId = ptBuildId;
    }

    public String getBuildName() {
        return buildName;
    }

    public void setBuildName(String buildName) {
        this.buildName = buildName == null ? null : buildName.trim();
    }

    public Integer getPtRoomId() {
        return ptRoomId;
    }

    public void setPtRoomId(Integer ptRoomId) {
        this.ptRoomId = ptRoomId;
    }

    public Integer getRoomNum() {
        return roomNum;
    }

    public void setRoomNum(Integer roomNum) {
        this.roomNum = roomNum;
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
		this.userName = userName;
	}

	public String getOutTradeNo() {
        return outTradeNo;
    }

    public void setOutTradeNo(String outTradeNo) {
        this.outTradeNo = outTradeNo == null ? null : outTradeNo.trim();
    }

    public String getTradeNum() {
        return tradeNum;
    }

    public void setTradeNum(String tradeNum) {
        this.tradeNum = tradeNum == null ? null : tradeNum.trim();
    }
    
    public BigDecimal getBenefitFee() {
		return benefitFee;
	}

	public void setBenefitFee(BigDecimal benefitFee) {
		this.benefitFee = benefitFee;
	}

	public String getPayTime() {
        return payTime;
    }

    public void setPayTime(String payTime) {
        this.payTime = payTime == null ? null : payTime.trim();
    }

	public Integer getPayStatus() {
		return payStatus;
	}

	public void setPayStatus(Integer payStatus) {
		this.payStatus = payStatus;
	}
    
}