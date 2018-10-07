package com.zeng.ezsh.wy.entity;

public class PlateMessage {
	
    private String plateNumber;
	
	private String houseNumber;

    private String identity;

    private String userName;

    private String userPhone;
    
    private float managementCost;

    
    public float getManagementCost() {
		return managementCost;
	}

	public void setManagementCost(float managementCost) {
		this.managementCost = managementCost;
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
        this.identity = identity ;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName ;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }
    
    
    public String getPlateNumber() {
        return plateNumber;
    }

    public void setPlateNumber(String plateNumber) {
        this.plateNumber = plateNumber ;
    }

	
	public PlateMessage(String plateNumber, String houseNumber,
			String userPhone,float managementCost) {
		super();
		this.plateNumber = plateNumber;
		this.houseNumber = houseNumber;
		this.userPhone = userPhone;
		this.managementCost = managementCost;
	}

	@Override
	public String toString() {
		return "PlateMessage [plateNumber=" + plateNumber
				+ ", houseNumber=" + houseNumber + ", identity=" + identity
				+ ", hostName=" + userName + ", userPhone=" + userPhone
				+ ", managementCost=" + managementCost + "]";
	}

	public PlateMessage() {
		super();
	}
	
	
    
    
}