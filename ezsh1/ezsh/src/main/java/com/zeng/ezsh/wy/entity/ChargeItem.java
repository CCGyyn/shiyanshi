package com.zeng.ezsh.wy.entity;

import java.math.BigDecimal;

public class ChargeItem {
    private Integer chargeId;
    
    private Integer pManagerId;

	private String chargeName;

    private Integer chargeWay;

	private Integer chargeBillingWay;

    private BigDecimal chargeBillingUnitPrice;

    private Integer chargeClassify;

    private Integer chargeBillingCycleCount;

    private Integer chargeBillingCycleUnit;

	private Double chargeOverdueFine;

    private Integer printOrder;

    public Integer getChargeId() {
        return chargeId;
    }

    public void setChargeId(Integer chargeId) {
        this.chargeId = chargeId;
    }
    
    public Integer getpManagerId() {
		return pManagerId;
	}

	public void setpManagerId(Integer pManagerId) {
		this.pManagerId = pManagerId;
	}
	
    public String getChargeName() {
        return chargeName;
    }

    public void setChargeName(String chargeName) {
        this.chargeName = chargeName == null ? null : chargeName.trim();
    }

    public Integer getChargeWay() {
		return chargeWay;
	}

	public void setChargeWay(Integer chargeWay) {
		this.chargeWay = chargeWay;
	}

	public Integer getChargeBillingWay() {
		return chargeBillingWay;
	}

	public void setChargeBillingWay(Integer chargeBillingWay) {
		this.chargeBillingWay = chargeBillingWay;
	}

    public BigDecimal getChargeBillingUnitPrice() {
        return chargeBillingUnitPrice;
    }

    public void setChargeBillingUnitPrice(BigDecimal chargeBillingUnitPrice) {
        this.chargeBillingUnitPrice = chargeBillingUnitPrice;
    }

    public Integer getChargeClassify() {
		return chargeClassify;
	}

	public void setChargeClassify(Integer chargeClassify) {
		this.chargeClassify = chargeClassify;
	}

    public Integer getChargeBillingCycleCount() {
        return chargeBillingCycleCount;
    }

    public void setChargeBillingCycleCount(Integer chargeBillingCycleCount) {
        this.chargeBillingCycleCount = chargeBillingCycleCount;
    }

    public Integer getChargeBillingCycleUnit() {
		return chargeBillingCycleUnit;
	}

	public void setChargeBillingCycleUnit(Integer chargeBillingCycleUnit) {
		this.chargeBillingCycleUnit = chargeBillingCycleUnit;
	}

    public Double getChargeOverdueFine() {
        return chargeOverdueFine;
    }

    public void setChargeOverdueFine(Double chargeOverdueFine) {
        this.chargeOverdueFine = chargeOverdueFine;
    }

    public Integer getPrintOrder() {
        return printOrder;
    }

    public void setPrintOrder(Integer printOrder) {
        this.printOrder = printOrder;
    }
}