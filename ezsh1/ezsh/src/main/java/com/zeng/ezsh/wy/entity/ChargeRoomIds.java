package com.zeng.ezsh.wy.entity;

public class ChargeRoomIds {
    private Integer chargeRoomId;
    
    private Integer pManagerId;
    
    private String managerName;//管理处名称

	private Integer pBuildId;
	
	private String buildName;//楼宇名称
    
    private Integer pRoomId;
    
    private String roomNum;//房间编号
    
    private Integer pChargeItemId;
    
    private String chargeItemName;//收费项目名称
    
    private Management managerInfo;//管理处信息

	private Building buildInfo;//楼栋信息
    
    private Room roomInfo;//房间信息
    
    private ChargeItem chargeItemInfo;//收费项目信息

	public Integer getChargeRoomId() {
        return chargeRoomId;
    }

    public void setChargeRoomId(Integer chargeRoomId) {
        this.chargeRoomId = chargeRoomId;
    }

	public Integer getpManagerId() {
		return pManagerId;
	}

	public void setpManagerId(Integer pManagerId) {
		this.pManagerId = pManagerId;
	}
    
	public String getManagerName() {
		return managerName;
	}

	public void setManagerName(String managerName) {
		this.managerName = managerName;
	}
	
	public Integer getpBuildId() {
		return pBuildId;
	}

	public void setpBuildId(Integer pBuildId) {
		this.pBuildId = pBuildId;
	}

	public String getBuildName() {
		return buildName;
	}

	public void setBuildName(String buildName) {
		this.buildName = buildName;
	}

    public Integer getpRoomId() {
        return pRoomId;
    }

    public void setpRoomId(Integer pRoomId) {
        this.pRoomId = pRoomId;
    }
    
	public String getRoomNum() {
		return roomNum;
	}

	public void setRoomNum(String roomNum) {
		this.roomNum = roomNum;
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
	
    public Management getManagerInfo() {
		return managerInfo;
	}

	public void setManagerInfo(Management managerInfo) {
		this.managerInfo = managerInfo;
	}

	public Building getBuildInfo() {
		return buildInfo;
	}

	public void setBuildInfo(Building buildInfo) {
		this.buildInfo = buildInfo;
	}
	
    public Room getRoomInfo() {
 		return roomInfo;
 	}

 	public void setRoomInfo(Room roomInfo) {
 		this.roomInfo = roomInfo;
 	}
 	
	public ChargeItem getChargeItemInfo() {
		return chargeItemInfo;
	}

	public void setChargeItemInfo(ChargeItem chargeItemInfo) {
		this.chargeItemInfo = chargeItemInfo;
	}
}