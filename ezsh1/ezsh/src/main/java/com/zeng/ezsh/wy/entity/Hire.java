package com.zeng.ezsh.wy.entity;

public class Hire {
	private Integer hireId;// 出租事件id

	private Integer hireType;// 出租类型，0：其他，1：小车，2：房屋

	private String ownerName;// 物主名字

	private String ownerTele;// 物主电话

	private String ownerTypeNumber;// 出租物编号，如房号，车号

	private String renterName;// 租者名字

	private String renterTele;// 租者电话

	private String hrieTime;// 出租时间段

	private String hriePlot;// 出租所在小区
	private String makeTime;// 登记时间

	public String getMakeTime() {
		return makeTime;
	}

	public void setMakeTime(String makeTime) {
		this.makeTime = makeTime;
	}

	public Integer getHireId() {
		return hireId;
	}

	public void setHireId(Integer hireId) {
		this.hireId = hireId;
	}

	public Integer getHireType() {
		return hireType;
	}

	public void setHireType(Integer hireType) {
		this.hireType = hireType;
	}

	public String getOwnerName() {
		return ownerName;
	}

	public void setOwnerName(String ownerName) {
		this.ownerName = ownerName == null ? null : ownerName.trim();
	}

	public String getOwnerTele() {
		return ownerTele;
	}

	public void setOwnerTele(String ownerTele) {
		this.ownerTele = ownerTele == null ? null : ownerTele.trim();
	}

	public String getOwnerTypeNumber() {
		return ownerTypeNumber;
	}

	public void setOwnerTypeNumber(String ownerTypeNumber) {
		this.ownerTypeNumber = ownerTypeNumber == null ? null : ownerTypeNumber
				.trim();
	}

	public String getRenterName() {
		return renterName;
	}

	public void setRenterName(String renterName) {
		this.renterName = renterName == null ? null : renterName.trim();
	}

	public String getRenterTele() {
		return renterTele;
	}

	public void setRenterTele(String renterTele) {
		this.renterTele = renterTele == null ? null : renterTele.trim();
	}

	public String getHrieTime() {
		return hrieTime;
	}

	public void setHrieTime(String hrieTime) {
		this.hrieTime = hrieTime == null ? null : hrieTime.trim();
	}

	public String getHriePlot() {
		return hriePlot;
	}

	public void setHriePlot(String hriePlot) {
		this.hriePlot = hriePlot == null ? null : hriePlot.trim();
	}
}