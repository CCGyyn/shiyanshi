package com.zeng.ezsh.wy.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ChargeRecord {
    private Integer chargeRecordId;

    private Integer pUserId;

    private Integer pManagerId;

    private Integer pBuildingId;
    
    private Integer pRoomId;
    
    private Integer ptChargeInfoId;
    
	private Integer pChargeItemId;
	
    private String chargeItemName;

	private BigDecimal chargeAmount;

    private BigDecimal unitPrice;

	private BigDecimal totalPrice;

    private String chargeForPeople;

    private String chargeTime;

    private String chargeOfDate;

	private String chargeEndDate;

	private String checkPeople;

    private String checkTime;

    @JsonIgnore
    private Integer checkStatus;

    private Integer overdueTime;

    private Byte status;
    
    private ChargeItem chargeItem;
    
    public Integer getChargeRecordId() {
        return chargeRecordId;
    }

    public void setChargeRecordId(Integer chargeRecordId) {
        this.chargeRecordId = chargeRecordId;
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

 	public Integer getpBuildingId() {
 		return pBuildingId;
 	}

 	public void setpBuildingId(Integer pBuildingId) {
 		this.pBuildingId = pBuildingId;
 	}

 	public Integer getpRoomId() {
 		return pRoomId;
 	}

 	public void setpRoomId(Integer pRoomId) {
 		this.pRoomId = pRoomId;
 	}
 	
 	public Integer getPtChargeInfoId() {
		return ptChargeInfoId;
	}

	public void setPtChargeInfoId(Integer ptChargeInfoId) {
		this.ptChargeInfoId = ptChargeInfoId;
	}

	public Integer getpChargeItemId() {
 		return pChargeItemId;
 	}

 	public void setpChargeItemId(Integer pChargeItemId) {
 		this.pChargeItemId = pChargeItemId;
 	}
    
	public String getChargeItemName() {
		return chargeItemName;
	}

	public void setChargeItemName(String chargeItemName) {
		this.chargeItemName = chargeItemName;
	}
	
    public BigDecimal getChargeAmount() {
		return chargeAmount;
	}

	public void setChargeAmount(BigDecimal chargeAmount) {
		this.chargeAmount = chargeAmount;
	}

	public BigDecimal getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(BigDecimal unitPrice) {
		this.unitPrice = unitPrice;
	}

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getChargeForPeople() {
        return chargeForPeople;
    }

    public void setChargeForPeople(String chargeForPeople) {
        this.chargeForPeople = chargeForPeople == null ? null : chargeForPeople.trim();
    }

    public String getChargeTime() {
        return chargeTime;
    }

    public void setChargeTime(String chargeTime) {
        this.chargeTime = chargeTime == null ? null : chargeTime.trim();
    }

	public String getChargeOfDate() {
		return chargeOfDate;
	}

	public void setChargeOfDate(String chargeOfDate) {
		this.chargeOfDate = chargeOfDate;
	}

    public String getChargeEndDate() {
		return chargeEndDate;
	}

	public void setChargeEndDate(String chargeEndDate) {
		this.chargeEndDate = chargeEndDate;
	}

    public String getCheckPeople() {
        return checkPeople;
    }

    public void setCheckPeople(String checkPeople) {
        this.checkPeople = checkPeople == null ? null : checkPeople.trim();
    }


	public String getCheckTime() {
		return checkTime;
	}

	public void setCheckTime(String checkTime) {
		this.checkTime = checkTime;
	}

    public Integer getCheckStatus() {
        return checkStatus;
    }

    public void setCheckStatus(Integer checkStatus) {
        this.checkStatus = checkStatus;
    }

	public Integer getOverdueTime() {
		return overdueTime;
	}

	public void setOverdueTime(Integer overdueTime) {
		this.overdueTime = overdueTime;
	}

	public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

	public ChargeItem getChargeItem() {
		return chargeItem;
	}

	public void setChargeItem(ChargeItem chargeItem) {
		this.chargeItem = chargeItem;
	}
    
}