package com.zeng.ezsh.wy.entity;

public class GridRecord {
    private Integer gridRecordId;

    private Integer pManagerId;

    private Integer pBuildId;

    private Integer pRoomId;

	private Integer pGridId;

    private String buildName;

    private String roomNum;

    private String gridNum;

    private String customerName;

    private String gridReadType;

    private String gridReadPeople;

    private String gridReadTime;

    private String enteringTime;

    private Double beginDosage;

	private Double dosage;

    public Integer getGridRecordId() {
        return gridRecordId;
    }

    public void setGridRecordId(Integer gridRecordId) {
        this.gridRecordId = gridRecordId;
    }

    public Integer getpManagerId() {
        return pManagerId;
    }

    public void setpManagerId(Integer pManagerId) {
        this.pManagerId = pManagerId;
    }

    public Integer getpBuildId() {
        return pBuildId;
    }

    public void setpBuildId(Integer pBuildId) {
        this.pBuildId = pBuildId;
    }

    public Integer getpRoomId() {
        return pRoomId;
    }
    
    public void setpRoomId(Integer pRoomId) {
        this.pRoomId = pRoomId;
    }
    
    /*解决反射机制找不到构造方法*/
   /* public Integer getPRoomId() {
		return pRoomId;
	}

	public void setPRoomId(Integer pRoomId) {
		this.pRoomId = pRoomId;
	}*/
	
    public Integer getpGridId() {
        return pGridId;
    }

    public void setpGridId(Integer pGridId) {
        this.pGridId = pGridId;
    }

    public String getBuildName() {
        return buildName;
    }

    public void setBuildName(String buildName) {
        this.buildName = buildName == null ? null : buildName.trim();
    }

    public String getRoomNum() {
        return roomNum;
    }

    public void setRoomNum(String roomNum) {
        this.roomNum = roomNum == null ? null : roomNum.trim();
    }

    public String getGridNum() {
        return gridNum;
    }

    public void setGridNum(String gridNum) {
        this.gridNum = gridNum == null ? null : gridNum.trim();
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName == null ? null : customerName.trim();
    }

    public String getGridReadType() {
        return gridReadType;
    }

    public void setGridReadType(String gridReadType) {
        this.gridReadType = gridReadType == null ? null : gridReadType.trim();
    }

    public String getGridReadPeople() {
        return gridReadPeople;
    }

    public void setGridReadPeople(String gridReadPeople) {
        this.gridReadPeople = gridReadPeople == null ? null : gridReadPeople.trim();
    }

    public String getGridReadTime() {
        return gridReadTime;
    }

    public void setGridReadTime(String gridReadTime) {
        this.gridReadTime = gridReadTime == null ? null : gridReadTime.trim();
    }

    public String getEnteringTime() {
        return enteringTime;
    }

    public void setEnteringTime(String enteringTime) {
        this.enteringTime = enteringTime == null ? null : enteringTime.trim();
    }

    public Double getBeginDosage() {
		return beginDosage;
	}

	public void setBeginDosage(Double beginDosage) {
		this.beginDosage = beginDosage;
	}

    public Double getDosage() {
        return dosage;
    }

    public void setDosage(Double dosage) {
        this.dosage = dosage;
    }
}