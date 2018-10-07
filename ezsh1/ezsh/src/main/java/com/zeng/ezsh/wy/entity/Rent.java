package com.zeng.ezsh.wy.entity;

public class Rent {
    private Integer rentId;

    private Integer ptManagerId;

    private String managerName;

    private Integer ptBuildId;

    private String buildName;

    private Integer ptRoomId;

    private String roomNum;

    private String rentTelephone;

    private String rentName;

    private String rentClassify;

    private String rentDescr;

    private Byte rentStatus;

    private String rentTime;
    
    private Integer delStatus;

    public Integer getRentId() {
        return rentId;
    }

    public void setRentId(Integer rentId) {
        this.rentId = rentId;
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

    public Integer getPtBuildId() {
        return ptBuildId;
    }

    public void setPtBuildId(Integer ptBuildId) {
        this.ptBuildId = ptBuildId;
    }

    public String getBuildName() {
        return buildName;
    }

    public void setBuildName(String buildName) {
        this.buildName = buildName == null ? null : buildName.trim();
    }

    public Integer getPtRoomId() {
        return ptRoomId;
    }

    public void setPtRoomId(Integer ptRoomId) {
        this.ptRoomId = ptRoomId;
    }

    public String getRoomNum() {
        return roomNum;
    }

    public void setRoomNum(String roomNum) {
        this.roomNum = roomNum == null ? null : roomNum.trim();
    }

    public String getRentTelephone() {
        return rentTelephone;
    }

    public void setRentTelephone(String rentTelephone) {
        this.rentTelephone = rentTelephone == null ? null : rentTelephone.trim();
    }

    public String getRentName() {
        return rentName;
    }

    public void setRentName(String rentName) {
        this.rentName = rentName == null ? null : rentName.trim();
    }

    public String getRentClassify() {
        return rentClassify;
    }

    public void setRentClassify(String rentClassify) {
        this.rentClassify = rentClassify == null ? null : rentClassify.trim();
    }

    public String getRentDescr() {
        return rentDescr;
    }

    public void setRentDescr(String rentDescr) {
        this.rentDescr = rentDescr == null ? null : rentDescr.trim();
    }

    public Byte getRentStatus() {
        return rentStatus;
    }

    public void setRentStatus(Byte rentStatus) {
        this.rentStatus = rentStatus;
    }

    public String getRentTime() {
        return rentTime;
    }

    public void setRentTime(String rentTime) {
        this.rentTime = rentTime == null ? null : rentTime.trim();
    }

	public Integer getDelStatus() {
		return delStatus;
	}

	public void setDelStatus(Integer delStatus) {
		this.delStatus = delStatus;
	} 
}