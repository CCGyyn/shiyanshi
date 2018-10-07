package com.zeng.ezsh.wy.entity;

public class HouseMessage {
	//房间楼层
	private int roomFloor;
	//房间号
	private String roomNum;
	
	private int buildId;
	//楼宇名称（栋数）
	private String buildName;
	
	private int managerId;
	
	private String managerName;
	
	

	public int getManagerId() {
		return managerId;
	}

	public void setManagerId(int managerId) {
		this.managerId = managerId;
	}

	public String getManagerName() {
		return managerName;
	}

	public void setManagerName(String managerName) {
		this.managerName = managerName;
	}

	public int getRoomFloor() {
		return roomFloor;
	}

	public void setRoomFloor(int roomFloor) {
		this.roomFloor = roomFloor;
	}

	public String getRoomNum() {
		return roomNum;
	}

	public void setRoomNum(String roomNum) {
		this.roomNum = roomNum;
	}

	public int getBuildId() {
		return buildId;
	}

	public void setBuildId(int buildId) {
		this.buildId = buildId;
	}

	public String getBuildName() {
		return buildName;
	}

	public void setBuildName(String buildName) {
		this.buildName = buildName;
	}

	public HouseMessage(int roomFloor, String roomNum, int buildId) {
		super();
		this.roomFloor = roomFloor;
		this.roomNum = roomNum;
		this.buildId = buildId;
	}

	public HouseMessage() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "HouseMessage [roomFloor=" + roomFloor + ", roomNum=" + roomNum
				+ ", buildId=" + buildId + ", buildName=" + buildName
				+ ", managerId=" + managerId + ", managerName=" + managerName
				+ "]";
	}

	
	
	
	
	

}
