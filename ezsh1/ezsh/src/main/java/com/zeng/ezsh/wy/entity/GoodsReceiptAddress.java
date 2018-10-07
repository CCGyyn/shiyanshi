package com.zeng.ezsh.wy.entity;

public class GoodsReceiptAddress {
    private Integer receiptAddressId;

    private Integer pUserId;
    
    private Integer pManagerId;
    
    private String linkMan;

	private String linkPhone;
    
    public Integer getpManagerId() {
		return pManagerId;
	}

	public void setpManagerId(Integer pManagerId) {
		this.pManagerId = pManagerId;
	}

	private String receiptAddress;

    public Integer getReceiptAddressId() {
        return receiptAddressId;
    }

    public void setReceiptAddressId(Integer receiptAddressId) {
        this.receiptAddressId = receiptAddressId;
    }

    public Integer getpUserId() {
        return pUserId;
    }

    public void setpUserId(Integer pUserId) {
        this.pUserId = pUserId;
    }

    public String getReceiptAddress() {
        return receiptAddress;
    }

    public void setReceiptAddress(String receiptAddress) {
        this.receiptAddress = receiptAddress == null ? null : receiptAddress.trim();
    }
    
    public String getLinkMan() {
		return linkMan;
	}

	public void setLinkMan(String linkMan) {
		this.linkMan = linkMan;
	}

	public String getLinkPhone() {
		return linkPhone;
	}

	public void setLinkPhone(String linkPhone) {
		this.linkPhone = linkPhone;
	}
}