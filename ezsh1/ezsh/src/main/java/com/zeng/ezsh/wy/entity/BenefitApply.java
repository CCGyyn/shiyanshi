package com.zeng.ezsh.wy.entity;

import java.math.BigDecimal;

public class BenefitApply {
    private Integer benefitApplyId;

    private Integer ptManagerId;

    private String ptManagerName;

    private Integer ptUserId;

    private String identifyCard;

    private String linkPhone;

    private String illness;

    private String identificationImgs;
    
    private BigDecimal applyMoney;
    
    private String applyTime;
    
    private Integer checkStatus;

    public Integer getBenefitApplyId() {
        return benefitApplyId;
    }

    public void setBenefitApplyId(Integer benefitApplyId) {
        this.benefitApplyId = benefitApplyId;
    }

    public Integer getPtManagerId() {
        return ptManagerId;
    }

    public void setPtManagerId(Integer ptManagerId) {
        this.ptManagerId = ptManagerId;
    }

    public String getPtManagerName() {
        return ptManagerName;
    }

    public void setPtManagerName(String ptManagerName) {
        this.ptManagerName = ptManagerName == null ? null : ptManagerName.trim();
    }

    public Integer getPtUserId() {
        return ptUserId;
    }

    public void setPtUserId(Integer ptUserId) {
        this.ptUserId = ptUserId;
    }

    public String getIdentifyCard() {
        return identifyCard;
    }

    public void setIdentifyCard(String identifyCard) {
        this.identifyCard = identifyCard == null ? null : identifyCard.trim();
    }

    public String getLinkPhone() {
        return linkPhone;
    }

    public void setLinkPhone(String linkPhone) {
        this.linkPhone = linkPhone == null ? null : linkPhone.trim();
    }

    public String getIllness() {
        return illness;
    }

    public void setIllness(String illness) {
        this.illness = illness == null ? null : illness.trim();
    }

    public String getIdentificationImgs() {
        return identificationImgs;
    }

    public void setIdentificationImgs(String identificationImgs) {
        this.identificationImgs = identificationImgs == null ? null : identificationImgs.trim();
    }
    
	public BigDecimal getApplyMoney() {
		return applyMoney;
	}

	public void setApplyMoney(BigDecimal applyMoney) {
		this.applyMoney = applyMoney;
	}
	
	public String getApplyTime() {
		return applyTime;
	}

	public void setApplyTime(String applyTime) {
		this.applyTime = applyTime;
	}

	public Integer getCheckStatus() {
		return checkStatus;
	}

	public void setCheckStatus(Integer checkStatus) {
		this.checkStatus = checkStatus;
	}

}