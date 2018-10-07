package com.zeng.ezsh.wy.entity;

import java.util.List;

public class ChargeInfo {
    private Integer chargeInfoId;

    private Integer ptRoomId;

    private String chargeOfMonth;

    private String chargeTransactionNum;

    private String chargePayTime;

    private String chargeWay;

	private Integer chargePayStatus;
	
    private List<ChargeRecord> chargeRecordList;

	public Integer getChargeInfoId() {
        return chargeInfoId;
    }

    public void setChargeInfoId(Integer chargeInfoId) {
        this.chargeInfoId = chargeInfoId;
    }

    public Integer getPtRoomId() {
        return ptRoomId;
    }

    public void setPtRoomId(Integer ptRoomId) {
        this.ptRoomId = ptRoomId;
    }

    public String getChargeOfMonth() {
        return chargeOfMonth;
    }

    public void setChargeOfMonth(String chargeOfMonth) {
        this.chargeOfMonth = chargeOfMonth == null ? null : chargeOfMonth.trim();
    }

    public Integer getChargePayStatus() {
		return chargePayStatus;
	}

	public void setChargePayStatus(Integer chargePayStatus) {
		this.chargePayStatus = chargePayStatus;
	}

    public String getChargeTransactionNum() {
        return chargeTransactionNum;
    }

    public void setChargeTransactionNum(String chargeTransactionNum) {
        this.chargeTransactionNum = chargeTransactionNum == null ? null : chargeTransactionNum.trim();
    }

    public String getChargePayTime() {
        return chargePayTime;
    }

    public void setChargePayTime(String chargePayTime) {
        this.chargePayTime = chargePayTime == null ? null : chargePayTime.trim();
    }

    public String getChargeWay() {
        return chargeWay;
    }

    public void setChargeWay(String chargeWay) {
        this.chargeWay = chargeWay == null ? null : chargeWay.trim();
    }

    public List<ChargeRecord> getChargeRecordList() {
		return chargeRecordList;
	}

	public void setChargeRecordList(List<ChargeRecord> chargeRecordList) {
		this.chargeRecordList = chargeRecordList;
	}
}