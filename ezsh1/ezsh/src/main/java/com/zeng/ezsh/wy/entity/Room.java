package com.zeng.ezsh.wy.entity;

import java.util.List;

public class Room {
    private Integer roomId;

    private Integer managerId;//管理处

    private Integer buildId;//楼宇

    private Double buildArea;//建筑面积

    private Integer roomStatus;//房间状态

    private Integer roomType;//房间类型

    private Integer roomFloor;//楼层

    private String roomNum;//单元号

    private String chargeMan;

    private String roomUse;

    private String decorate;

    private String position;

    private String toward;

    private String remark;

    private Double rent;

    private Double managementFee;

    private Double singlePrice;

    private Double sumPrice;

    private Integer isAgreement;

    private String contract;

    private Integer rentStatus;

    private Integer effectiveStatus;
    
    private Integer ptUserId;
    
    private String userIdentityCard;
    
    private Building building;
    
    private Management management;
    
    private Customer customerInfo;
    
    private List<ChargeRoomIds> chargeItemIdsList;
    
    private List<GridRoomItem> gridRoomItemList;
    
	public Integer getRoomId() {
        return roomId;
    }
    		
    public void setRoomId(Integer roomId) {
        this.roomId = roomId;
    }

    public Integer getManagerId() {
        return managerId;
    }

    public void setManagerId(Integer managerId) {
        this.managerId = managerId;
    }

    public Integer getBuildId() {
        return buildId;
    }

    public void setBuildId(Integer buildId) {
        this.buildId = buildId;
    }

    public Double getBuildArea() {
        return buildArea;
    }

    public void setBuildArea(Double buildArea) {
        this.buildArea = buildArea;
    }

    public Integer getRoomStatus() {
        return roomStatus;
    }

    public void setRoomStatus(Integer roomStatus) {
        this.roomStatus = roomStatus;
    }

   

    public Integer getRoomType() {
		return roomType;
	}

	public void setRoomType(Integer roomType) {
		this.roomType = roomType;
	}

	public Integer getRoomFloor() {
        return roomFloor;
    }

    public void setRoomFloor(Integer roomFloor) {
        this.roomFloor = roomFloor;
    }

    public String getRoomNum() {
        return roomNum;
    }

    public void setRoomNum(String roomNum) {
        this.roomNum = roomNum == null ? null : roomNum.trim();
    }

    public String getChargeMan() {
        return chargeMan;
    }

    public void setChargeMan(String chargeMan) {
        this.chargeMan = chargeMan == null ? null : chargeMan.trim();
    }

    public String getRoomUse() {
        return roomUse;
    }

    public void setRoomUse(String roomUse) {
        this.roomUse = roomUse == null ? null : roomUse.trim();
    }

    public String getDecorate() {
        return decorate;
    }

    public void setDecorate(String decorate) {
        this.decorate = decorate == null ? null : decorate.trim();
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position == null ? null : position.trim();
    }

    public String getToward() {
        return toward;
    }

    public void setToward(String toward) {
        this.toward = toward == null ? null : toward.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public Double getRent() {
        return rent;
    }

    public void setRent(Double rent) {
        this.rent = rent;
    }

    public Double getManagementFee() {
        return managementFee;
    }

    public void setManagementFee(Double managementFee) {
        this.managementFee = managementFee;
    }

    public Double getSinglePrice() {
        return singlePrice;
    }

    public void setSinglePrice(Double singlePrice) {
        this.singlePrice = singlePrice;
    }

    public Double getSumPrice() {
        return sumPrice;
    }

    public void setSumPrice(Double sumPrice) {
        this.sumPrice = sumPrice;
    }

    public Integer getIsAgreement() {
        return isAgreement;
    }

    public void setIsAgreement(Integer isAgreement) {
        this.isAgreement = isAgreement;
    }

    public String getContract() {
        return contract;
    }

    public void setContract(String contract) {
        this.contract = contract == null ? null : contract.trim();
    }

    public Integer getRentStatus() {
        return rentStatus;
    }

    public void setRentStatus(Integer rentStatus) {
        this.rentStatus = rentStatus;
    }

    public Integer getEffectiveStatus() {
        return effectiveStatus;
    }

    public void setEffectiveStatus(Integer effectiveStatus) {
        this.effectiveStatus = effectiveStatus;
    }
    
	public Integer getPtUserId() {
		return ptUserId;
	}

	public void setPtUserId(Integer ptUserId) {
		this.ptUserId = ptUserId;
	}

	public String getUserIdentityCard() {
		return userIdentityCard;
	}

	public void setUserIdentityCard(String userIdentityCard) {
		this.userIdentityCard = userIdentityCard;
	}

	public Building getBuilding() {
		return building;
	}

	public void setBuilding(Building building) {
		this.building = building;
	}

	public Management getManagement() {
		return management;
	}

	public void setManagement(Management management) {
		this.management = management;
	}
    
	public Customer getCustomerInfo() {
		return customerInfo;
	}

	public void setCustomerInfo(Customer customerInfo) {
		this.customerInfo = customerInfo;
	}
	  
	public List<ChargeRoomIds> getChargeItemIdsList() {
		return chargeItemIdsList;
	}

	public void setChargeItemIdsList(List<ChargeRoomIds> chargeItemIdsList) {
		this.chargeItemIdsList = chargeItemIdsList;
	}

	public List<GridRoomItem> getGridRoomItemList() {
		return gridRoomItemList;
	}

	public void setGridRoomItemList(List<GridRoomItem> gridRoomItemList) {
		this.gridRoomItemList = gridRoomItemList;
	}
	
}