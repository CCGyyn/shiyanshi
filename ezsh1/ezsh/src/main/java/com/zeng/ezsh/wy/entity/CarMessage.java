package com.zeng.ezsh.wy.entity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize.Inclusion;



/**
 * Title:CarMessage
 * Description:车辆信息
 * @author HAO
 * @date:2017年7月31日 下午4:06:45
 */

public class CarMessage {
	
    private String plateNumber;
	
	private String houseNumber;

    private String identity;

    private String userName;

    private String userPhone;
    
    private String userCommunity;
    
    private String invitePlate;
    
    private boolean isPay;
    @JsonSerialize(include=Inclusion.NON_EMPTY) 
    private BerthMessage berthMessage;
    
    private String berthNumber;
    
    private int userCommunityId;
    
    
  
	public String getBerthNumber() {
		return berthNumber;
	}



	public void setBerthNumber(String berthNumber) {
		this.berthNumber = berthNumber;
	}



	public CarMessage(String plateNumber, String houseNumber, String identity,
			String userName, String userPhone, BerthMessage berthMessage,
			int userCommunityId) {
		super();
		this.plateNumber = plateNumber;
		this.houseNumber = houseNumber;
		this.identity = identity;
		this.userName = userName;
		this.userPhone = userPhone;
		this.berthMessage = berthMessage;
		this.userCommunityId = userCommunityId;
	}

	

	public int getUserCommunityId() {
		return userCommunityId;
	}

	public void setUserCommunityId(int userCommunityId) {
		this.userCommunityId = userCommunityId;
	}

	public String getPlateNumber() {
		return plateNumber;
	}

	public void setPlateNumber(String plateNumber) {
		this.plateNumber = plateNumber;
	}

	public String getHouseNumber() {
		return houseNumber;
	}

	public void setHouseNumber(String houseNumber) {
		this.houseNumber = houseNumber;
	}

	public String getIdentity() {
		return identity;
	}

	public void setIdentity(String identity) {
		this.identity = identity;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserPhone() {
		return userPhone;
	}

	public void setUserPhone(String userPhone) {
		this.userPhone = userPhone;
	}

	public String getUserCommunity() {
		return userCommunity;
	}

	public void setUserCommunity(String userCommunity) {
		this.userCommunity = userCommunity;
	}

	public String getInvitePlate() {
		return invitePlate;
	}

	public void setInvitePlate(String invitePlate) {
		this.invitePlate = invitePlate;
	}

	public boolean isPay() {
		return isPay;
	}

	public void setPay(boolean isPay) {
		this.isPay = isPay;
	}

	public BerthMessage getBerthMessage() {
		return berthMessage;
	}
	

	public void setBerthMessage(BerthMessage berthMessage) {
		this.berthMessage = berthMessage;
	}

	

	@Override
	public String toString() {
		return "CarMessage [plateNumber=" + plateNumber + ", houseNumber="
				+ houseNumber + ", identity=" + identity + ", userName="
				+ userName + ", userPhone=" + userPhone + ", userCommunity="
				+ userCommunity + ", invitePlate=" + invitePlate + ", isPay="
				+ isPay + ", berthMessage=" + berthMessage
				+ ", userCommunityId=" + userCommunityId + "]";
	}



	public CarMessage(String plateNumber, String houseNumber, String identity,
			String userName, String userPhone, String userCommunity) {
		super();
		this.plateNumber = plateNumber;
		this.houseNumber = houseNumber;
		this.identity = identity;
		this.userName = userName;
		this.userPhone = userPhone;
		this.userCommunity = userCommunity;
	}

	public CarMessage() {
		super();
		// TODO Auto-generated constructor stub
	}



	public CarMessage(String plateNumber, String userPhone,
			BerthMessage berthMessage, int userCommunityId) {
		super();
		this.plateNumber = plateNumber;
		this.userPhone = userPhone;
		this.berthMessage = berthMessage;
		this.userCommunityId = userCommunityId;
	}
	
	
	

    
	
    
    
}