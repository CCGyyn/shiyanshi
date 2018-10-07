package com.zeng.ezsh.wy.entity;

import java.util.List;

public class Management {
    private Integer managerId;

    private String managerName;

    private String managerAddr;

    private Integer heightBuildNum;

    private Integer manyBuildNum;

    private Double buildArea;

    private Double floorArea;

    private Double greenArea;

    private Double publicArea;

    private Double parkingArea;

    private Integer parkingNum;

    private String contact;

    private String head;

    private String contactNum;

    private String remark;
    
    private Integer pProvinceId;

    private Integer pCityId;

    private Integer pDistrictId;
    
    private String provinceName;
    
	private String cityName;
	
	private String districtName;
	
    private Integer status;
    
    private List<Building> buildList;

	public Integer getManagerId() {
        return managerId;
    }

    public void setManagerId(Integer managerId) {
        this.managerId = managerId ;
    }

    public String getManagerName() {
        return managerName;
    }

    public void setManagerName(String managerName) {
        this.managerName = managerName == null ? null : managerName.trim();
    }

    public String getManagerAddr() {
        return managerAddr;
    }

    public void setManagerAddr(String managerAddr) {
        this.managerAddr = managerAddr == null ? null : managerAddr.trim();
    }

    public Integer getHeightBuildNum() {
        return heightBuildNum;
    }

    public void setHeightBuildNum(Integer heightBuildNum) {
        this.heightBuildNum = heightBuildNum;
    }

    public Integer getManyBuildNum() {
        return manyBuildNum;
    }

    public void setManyBuildNum(Integer manyBuildNum) {
        this.manyBuildNum = manyBuildNum;
    }

    public Double getBuildArea() {
        return buildArea;
    }

    public void setBuildArea(Double buildArea) {
        this.buildArea = buildArea;
    }

    public Double getFloorArea() {
        return floorArea;
    }

    public void setFloorArea(Double floorArea) {
        this.floorArea = floorArea;
    }

    public Double getGreenArea() {
        return greenArea;
    }

    public void setGreenArea(Double greenArea) {
        this.greenArea = greenArea;
    }

    public Double getPublicArea() {
        return publicArea;
    }

    public void setPublicArea(Double publicArea) {
        this.publicArea = publicArea;
    }

    public Double getParkingArea() {
        return parkingArea;
    }

    public void setParkingArea(Double parkingArea) {
        this.parkingArea = parkingArea;
    }

    public Integer getParkingNum() {
        return parkingNum;
    }

    public void setParkingNum(Integer parkingNum) {
        this.parkingNum = parkingNum;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact == null ? null : contact.trim();
    }

    public String getHead() {
        return head;
    }

    public void setHead(String head) {
        this.head = head == null ? null : head.trim();
    }

    public String getContactNum() {
        return contactNum;
    }

    public void setContactNum(String contactNum) {
        this.contactNum = contactNum == null ? null : contactNum.trim();
    } 
    
    public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Integer getpProvinceId() {
        return pProvinceId;
    }

    public void setpProvinceId(Integer pProvinceId) {
        this.pProvinceId = pProvinceId;
    }

    public Integer getpCityId() {
        return pCityId;
    }

    public void setpCityId(Integer pCityId) {
        this.pCityId = pCityId;
    }

    public Integer getpDistrictId() {
        return pDistrictId;
    }

    public void setpDistrictId(Integer pDistrictId) {
        this.pDistrictId = pDistrictId;
    }
    
    public String getProvinceName() {
		return provinceName;
	}

	public void setProvinceName(String provinceName) {
		this.provinceName = provinceName;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}
	
    public String getDistrictName() {
		return districtName;
	}

	public void setDistrictName(String districtName) {
		this.districtName = districtName;
	}

	public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
    
    public List<Building> getBuildList() {
		return buildList;
	}

	public void setBuildList(List<Building> buildList) {
		this.buildList = buildList;
	}
}