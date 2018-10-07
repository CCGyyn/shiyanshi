package com.zeng.ezsh.wy.entity;

import java.util.Date;



/**
 * Title:ParkRecord
 * Description:外来车辆停车记录
 * @author HAO
 * @date:2017年8月3日 下午4:55:40
 */
public class ParkRecord {
	
	private int id;
	
	private String plateNumber;
	
	private Date enterTime;
	
	private String enterTimeStr;
	
	private Date leaveTime;
	
	private String leaveTimeStr;
	
	private int userCommunityId;
	
	private double supposeParkCost;
	
	private double handParkCost;
	
	private String payWay;
	
	private String CRCCode;
	
	private double advancePay;
	
	private Date prepaymentTime;
	
	private String prepaymentTimeStr;
	
	private String SN;
	
	private int payStatus;
	
	private String orderNum;
	
	private String transactionNum;
	
	private int orderStatus;
	
	

	public String getEnterTimeStr() {
		return enterTimeStr;
	}

	public void setEnterTimeStr(String enterTimeStr) {
		this.enterTimeStr = enterTimeStr;
	}

	public String getLeaveTimeStr() {
		return leaveTimeStr;
	}

	public void setLeaveTimeStr(String leaveTimeStr) {
		this.leaveTimeStr = leaveTimeStr;
	}

	public String getPrepaymentTimeStr() {
		return prepaymentTimeStr;
	}

	public void setPrepaymentTimeStr(String prepaymentTimeStr) {
		this.prepaymentTimeStr = prepaymentTimeStr;
	}

	public Date getPrepaymentTime() {
		return prepaymentTime;
	}

	public void setPrepaymentTime(Date prepaymentTime) {
		this.prepaymentTime = prepaymentTime;
	}

	public int getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(int orderStatus) {
		this.orderStatus = orderStatus;
	}

	public String getTransactionNum() {
		return transactionNum;
	}

	public void setTransactionNum(String transactionNum) {
		this.transactionNum = transactionNum;
	}

	public String getOrderNum() {
		return orderNum;
	}

	public void setOrderNum(String orderNum) {
		this.orderNum = orderNum;
	}

	public int getPayStatus() {
		return payStatus;
	}

	public void setPayStatus(int payStatus) {
		this.payStatus = payStatus;
	}

	

	

	public String getSN() {
		return SN;
	}

	public void setSN(String sN) {
		SN = sN;
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

	public Date getEnterTime() {
		return enterTime;
	}

	public void setEnterTime(Date enterTime) {
		this.enterTime = enterTime;
	}

	public Date getLeaveTime() {
		return leaveTime;
	}

	public void setLeaveTime(Date leaveTime) {
		this.leaveTime = leaveTime;
	}

	public int getUserCommunityId() {
		return userCommunityId;
	}

	public void setUserCommunityId(int userCommunityId) {
		this.userCommunityId = userCommunityId;
	}

	public double getSupposeParkCost() {
		return supposeParkCost;
	}

	public void setSupposeParkCost(double supposeParkCost) {
		this.supposeParkCost = supposeParkCost;
	}

	public double getHandParkCost() {
		return handParkCost;
	}

	public void setHandParkCost(double handParkCost) {
		this.handParkCost = handParkCost;
	}

	public String getPayWay() {
		return payWay;
	}

	public void setPayWay(String payWay) {
		this.payWay = payWay;
	}

	public String getCRCCode() {
		return CRCCode;
	}

	public void setCRCCode(String cRCCode) {
		CRCCode = cRCCode;
	}

	public double getAdvancePay() {
		return advancePay;
	}

	public void setAdvancePay(double advancePay) {
		this.advancePay = advancePay;
	}

	public ParkRecord() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ParkRecord( String plateNumber, Date enterTime,
			int userCommunityId, String cRCCode) {
		super();
		this.plateNumber = plateNumber;
		this.enterTime = enterTime;
		this.userCommunityId = userCommunityId;
		CRCCode = cRCCode;
	}
	@Override
	public String toString() {
		return "ParkRecord [id=" + id + ", plateNumber=" + plateNumber
				+ ", enterTime=" + enterTime + ", leaveTime=" + leaveTime
				+ ", userCommunityId=" + userCommunityId + ", supposeParkCost="
				+ supposeParkCost + ", handParkCost=" + handParkCost
				+ ", payWay=" + payWay + ", CRCCode=" + CRCCode
				+ ", advancePay=" + advancePay + ", prepaymentTime="
				+ prepaymentTime + ", SN=" + SN + ", payStatus=" + payStatus
				+ ", orderNum=" + orderNum + ", transactionNum="
				+ transactionNum + ", orderStatus=" + orderStatus + "]";
	}


	
	
	

	
	
	

}
