package com.zeng.ezsh.wy.entity;

public class Customer {
    private Integer customerId;

    private Integer pManagerId;
    
    private Integer ptBuildId;
    
    private Integer pRoomId;

    private String customerName;

    private Integer customerClassify;

	private String customerTelephone;

    private String customerCheckInTime;

    private String customerIdCardNumber;
    
    private String chargeStartDate;

	private String chargeEndDate;
    
    private String contractStartDate;

    private String contractEndDate;
    
    private Integer resideStatus;
    
    private String remark;
    
    private Room roomInfo;

	public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public Integer getpManagerId() {
        return pManagerId;
    }

    public void setpManagerId(Integer pManagerId) {
        this.pManagerId = pManagerId;
    }

    public Integer getPtBuildId() {
		return ptBuildId;
	}

	public void setPtBuildId(Integer ptBuildId) {
		this.ptBuildId = ptBuildId;
	}

	public Integer getpRoomId() {
        return pRoomId;
    }

    public void setpRoomId(Integer pRoomId) {
        this.pRoomId = pRoomId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName == null ? null : customerName.trim();
    }

    public Integer getCustomerClassify() {
		return customerClassify;
	}

	public void setCustomerClassify(Integer customerClassify) {
		this.customerClassify = customerClassify;
	}

    public String getCustomerTelephone() {
        return customerTelephone;
    }

    public void setCustomerTelephone(String customerTelephone) {
        this.customerTelephone = customerTelephone == null ? null : customerTelephone.trim();
    }

    public String getCustomerCheckInTime() {
        return customerCheckInTime;
    }

    public void setCustomerCheckInTime(String customerCheckInTime) {
        this.customerCheckInTime = customerCheckInTime == null ? null : customerCheckInTime.trim();
    }

    public String getCustomerIdCardNumber() {
        return customerIdCardNumber;
    }

    public void setCustomerIdCardNumber(String customerIdCardNumber) {
        this.customerIdCardNumber = customerIdCardNumber == null ? null : customerIdCardNumber.trim();
    }

    public String getChargeStartDate() {
		return chargeStartDate;
	}

	public void setChargeStartDate(String chargeStartDate) {
		this.chargeStartDate = chargeStartDate;
	}

	public String getChargeEndDate() {
		return chargeEndDate;
	}

	public void setChargeEndDate(String chargeEndDate) {
		this.chargeEndDate = chargeEndDate;
	}
	
    public String getContractStartDate() {
        return contractStartDate;
    }

    public void setContractStartDate(String contractStartDate) {
        this.contractStartDate = contractStartDate == null ? null : contractStartDate.trim();
    }

    public String getContractEndDate() {
        return contractEndDate;
    }

    public void setContractEndDate(String contractEndDate) {
        this.contractEndDate = contractEndDate == null ? null : contractEndDate.trim();
    }
    
    public Integer getResideStatus() {
		return resideStatus;
	}

	public void setResideStatus(Integer resideStatus) {
		this.resideStatus = resideStatus;
	}

	public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }
      
    public Room getRoomInfo() {
		return roomInfo;
	}

	public void setRoomInfo(Room roomInfo) {
		this.roomInfo = roomInfo;
	}
}