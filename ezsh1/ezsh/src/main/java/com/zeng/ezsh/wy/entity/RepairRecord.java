package com.zeng.ezsh.wy.entity;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

public class RepairRecord {
    private String repairNumber;

    private Integer communityId;

    private String communityName;

    private Date repairTime;
    
    private String expectTime;

    private String proprietorName;

    private String proprietorPhone;

    private String roomNumber;

    private int repairStatus;

    private String repairWay;
    
    private String repairTitle;

    private String repairDesc;
    //图片名
    private String repairImages;
    
    private String[] imgurls;

    private String repairUnit;

    private String repairStaff;

    private String repairSphone;

    private String completeStatus;
    @JsonInclude(Include.NON_NULL)
    private Date completeTime;

    private BigDecimal repairCost;

    private String feedback;

    
    
    public String getRepairTitle() {
		return repairTitle;
	}


	public void setRepairTitle(String repairTitle) {
		this.repairTitle = repairTitle;
	}


	public String[] getImgurls() {
		return imgurls;
	}


	public void setImgurls(String[] imgurls) {
		this.imgurls = imgurls;
	}


	public String getRepairNumber() {
        return repairNumber;
    }

    
    public String getExpectTime() {
		return expectTime;
	}


	public void setExpectTime(String expectTime) {
		this.expectTime = expectTime;
	}


	public void setRepairNumber(String repairNumber) {
        this.repairNumber = repairNumber == null ? null : repairNumber.trim();
    }

    public Integer getCommunityId() {
        return communityId;
    }

    public void setCommunityId(Integer communityId) {
        this.communityId = communityId;
    }

    public String getCommunityName() {
        return communityName;
    }

    public void setCommunityName(String communityName) {
        this.communityName = communityName == null ? null : communityName.trim();
    }

    public Date getRepairTime() {
        return repairTime;
    }

    public void setRepairTime(Date repairTime) {
        this.repairTime = repairTime;
    }

    public String getProprietorName() {
        return proprietorName;
    }

    public void setProprietorName(String proprietorName) {
        this.proprietorName = proprietorName == null ? null : proprietorName.trim();
    }

    public String getProprietorPhone() {
        return proprietorPhone;
    }

    public void setProprietorPhone(String proprietorPhone) {
        this.proprietorPhone = proprietorPhone == null ? null : proprietorPhone.trim();
    }

    public String getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(String roomNumber) {
        this.roomNumber = roomNumber == null ? null : roomNumber.trim();
    }

    public int getRepairStatus() {
        return repairStatus;
    }

    public void setRepairStatus(int repairStatus) {
        this.repairStatus = repairStatus;
    }

    public String getRepairWay() {
        return repairWay;
    }

    public void setRepairWay(String repairWay) {
        this.repairWay = repairWay == null ? null : repairWay.trim();
    }

    public String getRepairDesc() {
        return repairDesc;
    }

    public void setRepairDesc(String repairDesc) {
        this.repairDesc = repairDesc == null ? null : repairDesc.trim();
    }

    public String getRepairImages() {
        return repairImages;
    }

    public void setRepairImages(String repairImages) {
        this.repairImages = repairImages == null ? null : repairImages.trim();
    }

    public String getRepairUnit() {
        return repairUnit;
    }

    public void setRepairUnit(String repairUnit) {
        this.repairUnit = repairUnit == null ? null : repairUnit.trim();
    }

    public String getRepairStaff() {
        return repairStaff;
    }

    public void setRepairStaff(String repairStaff) {
        this.repairStaff = repairStaff == null ? null : repairStaff.trim();
    }

    public String getRepairSphone() {
        return repairSphone;
    }

    public void setRepairSphone(String repairSphone) {
        this.repairSphone = repairSphone == null ? null : repairSphone.trim();
    }

    public String getCompleteStatus() {
        return completeStatus;
    }

    public void setCompleteStatus(String completeStatus) {
        this.completeStatus = completeStatus == null ? null : completeStatus.trim();
    }

    public Date getCompleteTime() {
        return completeTime;
    }

    public void setCompleteTime(Date completeTime) {
        this.completeTime = completeTime;
    }

    public BigDecimal getRepairCost() {
        return repairCost;
    }

    public void setRepairCost(BigDecimal repairCost) {
        this.repairCost = repairCost;
    }

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback == null ? null : feedback.trim();
    }


	public RepairRecord(Integer communityId, String communityName,String proprietorName,
			String proprietorPhone, String roomNumber, String repairDesc,String repairTitle) {
		super();
		this.communityId = communityId;
		this.communityName = communityName;
		this.proprietorName = proprietorName;
		this.proprietorPhone = proprietorPhone;
		this.roomNumber = roomNumber;
		this.repairDesc = repairDesc;
		this.repairTitle = repairTitle;
	}
	
	public RepairRecord() {
		super();
		// TODO Auto-generated constructor stub
	}


	@Override
	public String toString() {
		return "RepairRecord [repairNumber=" + repairNumber + ", communityId="
				+ communityId + ", communityName=" + communityName
				+ ", repairTime=" + repairTime + ", expectTime=" + expectTime
				+ ", proprietorName=" + proprietorName + ", proprietorPhone="
				+ proprietorPhone + ", roomNumber=" + roomNumber
				+ ", repairStatus=" + repairStatus + ", repairWay=" + repairWay
				+ ", repairDesc=" + repairDesc + ", repairImages="
				+ repairImages + ", imgurls=" + Arrays.toString(imgurls)
				+ ", repairUnit=" + repairUnit + ", repairStaff=" + repairStaff
				+ ", repairSphone=" + repairSphone + ", completeStatus="
				+ completeStatus + ", completeTime=" + completeTime
				+ ", repairCost=" + repairCost + ", feedback=" + feedback + "]";
	}

	
    
}