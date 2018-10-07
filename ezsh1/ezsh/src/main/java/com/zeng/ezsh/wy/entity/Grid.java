package com.zeng.ezsh.wy.entity;

public class Grid {
    private Integer gridId;
    
    private Integer pManagerId;

	private String gridName;

    private Integer pChargeItemId;
    
    private String managerName;
   
    private String chargeItemName;

	private ChargeItem chargeItemInfo;

	public Integer getGridId() {
        return gridId;
    }

    public void setGridId(Integer gridId) {
        this.gridId = gridId;
    }
    
    public Integer getpManagerId() {
		return pManagerId;
	}

	public void setpManagerId(Integer pManagerId) {
		this.pManagerId = pManagerId;
	}
	
    public String getGridName() {
        return gridName;
    }

    public void setGridName(String gridName) {
        this.gridName = gridName == null ? null : gridName.trim();
    }

    public Integer getpChargeItemId() {
        return pChargeItemId;
    }

    public void setpChargeItemId(Integer pChargeItemId) {
        this.pChargeItemId = pChargeItemId;
    }
    
    public String getManagerName() {
		return managerName;
	}

	public void setManagerName(String managerName) {
		this.managerName = managerName;
	}
	 
	public String getChargeItemName() {
		return chargeItemName;
	}

	public void setChargeItemName(String chargeItemName) {
		this.chargeItemName = chargeItemName;
	}
	
    public ChargeItem getChargeItemInfo() {
		return chargeItemInfo;
	}

	public void setChargeItemInfo(ChargeItem chargeItemInfo) {
		this.chargeItemInfo = chargeItemInfo;
	}
}