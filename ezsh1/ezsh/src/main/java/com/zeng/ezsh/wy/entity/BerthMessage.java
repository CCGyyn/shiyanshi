package com.zeng.ezsh.wy.entity;

import java.util.Date;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize.Inclusion;

/**
 * Title:BerthMessage Description:车位信息
 * 
 * @author HAO
 * @date:2017年7月31日 下午4:11:19
 */

public class BerthMessage {

	private int id;

	private int userCommunityId;

	private String berthNumber;

	private String plateNumber;

	private String userName;

	private String userPhone;

	private double handInCost;

	private String hirePlate;

	private int isHire;

	private Date contractStartTime;

	private String contractStarttime;

	private Date contractEndTime;

	private String contractEndtime;

	private String userCommunity;

	private int status;

	private double berthCost;

	public String getContractStarttime() {
		return contractStarttime;
	}

	public void setContractStarttime(String contractStarttime) {
		this.contractStarttime = contractStarttime;
	}

	public String getContractEndtime() {
		return contractEndtime;
	}

	public void setContractEndtime(String contractEndtime) {
		this.contractEndtime = contractEndtime;
	}

	public double getBerthCost() {
		return berthCost;
	}

	public void setBerthCost(double berthCost) {
		this.berthCost = berthCost;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public Date getContractStartTime() {
		return contractStartTime;
	}

	public void setContractStartTime(Date contractStartTime) {
		this.contractStartTime = contractStartTime;
	}

	public Date getContractEndTime() {
		return contractEndTime;
	}

	public void setContractEndTime(Date contractEndTime) {
		this.contractEndTime = contractEndTime;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getPlateNumber() {
		return plateNumber;
	}

	public void setPlateNumber(String plateNumber) {
		this.plateNumber = plateNumber;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public int getIsHire() {
		return isHire;
	}

	public void setIsHire(int isHire) {
		this.isHire = isHire;
	}

	public String getUserCommunity() {
		return userCommunity;
	}

	public void setUserCommunity(String userCommunity) {
		this.userCommunity = userCommunity;
	}

	public int getUserCommunityId() {
		return userCommunityId;
	}

	public void setUserCommunityId(int userCommunity) {
		this.userCommunityId = userCommunity;
	}

	public String getBerthNumber() {
		return berthNumber;
	}

	public void setBerthNumber(String berthNumber) {
		this.berthNumber = berthNumber;
	}

	public String getUserPhone() {
		return userPhone;
	}

	public void setUserPhone(String userPhone) {
		this.userPhone = userPhone;
	}

	public double getHandInCost() {
		return handInCost;
	}

	public void setHandInCost(double handInCost) {
		this.handInCost = handInCost;
	}

	public String getHirePlate() {
		return hirePlate;
	}

	public void setHirePlate(String hirePlate) {
		this.hirePlate = hirePlate;
	}

	public BerthMessage(String berthNumber, double handInCost, String hirePlate) {
		super();
		this.berthNumber = berthNumber;
		this.handInCost = handInCost;
		this.hirePlate = hirePlate;
	}

	public BerthMessage(int userCommunity, String berthNumber) {
		super();
		this.userCommunityId = userCommunity;
		this.berthNumber = berthNumber;
	}

	public BerthMessage(String berthNumber) {
		super();
		this.berthNumber = berthNumber;
	}

	public BerthMessage(int userCommunity, String berthNumber,
			String userPhone, double handInCost, String hirePlate,
			String contractStarttime, String contractEndtime) {
		super();
		this.userCommunityId = userCommunity;
		this.berthNumber = berthNumber;
		this.userPhone = userPhone;
		this.handInCost = handInCost;
		this.hirePlate = hirePlate;
		this.contractStarttime = contractStarttime;
		this.contractEndtime = contractEndtime;

	}

	public BerthMessage() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "BerthMessage [id=" + id + ", userCommunityId="
				+ userCommunityId + ", berthNumber=" + berthNumber
				+ ", plateNumber=" + plateNumber + ", userName=" + userName
				+ ", userPhone=" + userPhone + ", handInCost=" + handInCost
				+ ", hirePlate=" + hirePlate + ", isHire=" + isHire
				+ ", contractStartTime=" + contractStartTime
				+ ", contractStarttime=" + contractStarttime
				+ ", contractEndTime=" + contractEndTime + ", contractEndtime="
				+ contractEndtime + ", userCommunity=" + userCommunity
				+ ", status=" + status + ", berthCost=" + berthCost + "]";
	}

}
