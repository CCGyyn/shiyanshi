package com.zeng.ezsh.wy.entity;

import java.math.BigDecimal;

public class ChargeOrder {
    private Integer chargeOrderId;

    private Integer ptManagerId;

    private String managerName;

    private Integer ptUserId;

    private String userName;

    private Integer ptChargeInfoId;

    private String tradeNum;

    private String outTradeNo;

    private BigDecimal payFee;

    private String payTime;

    private Integer payClassify;
    
    private Integer payStatus;
    
    public Integer getChargeOrderId() {
        return chargeOrderId;
    }

    public void setChargeOrderId(Integer chargeOrderId) {
        this.chargeOrderId = chargeOrderId;
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

    public Integer getPtChargeInfoId() {
        return ptChargeInfoId;
    }

    public void setPtChargeInfoId(Integer ptChargeInfoId) {
        this.ptChargeInfoId = ptChargeInfoId;
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

    public BigDecimal getPayFee() {
		return payFee;
	}

	public void setPayFee(BigDecimal payFee) {
		this.payFee = payFee;
	}

	public String getPayTime() {
        return payTime;
    }

    public void setPayTime(String payTime) {
        this.payTime = payTime == null ? null : payTime.trim();
    }

	public Integer getPayClassify() {
		return payClassify;
	}

	public void setPayClassify(Integer payClassify) {
		this.payClassify = payClassify;
	}

	public Integer getPayStatus() {
		return payStatus;
	}

	public void setPayStatus(Integer payStatus) {
		this.payStatus = payStatus;
	}
}