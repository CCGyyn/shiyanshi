package com.zeng.ezsh.wy.entity;

import java.math.BigDecimal;

import com.mchange.v2.resourcepool.ResourcePool.Manager;

public class UMsIds {
	private Integer uMsId;

    private Integer pUserId;

    private Integer pManagerId;

    private Integer pBuildId;

    private Integer pRoomId;
    
    private Integer pOwnerId;

	private String registerName;

    private String registerTelephone;

    private Integer identifyClassify;

	private String applyTime;

    private Integer checkStatus;

	private String failureInfo;
	
    private Boolean privilege;

	private String managerName;
	
	private BigDecimal welfareFund;
	
	private Management ManagerInfo;

	private Room RoomInfo;

	private Building BuildInfo;
	
    public Integer getuMsId() {
        return uMsId;
    }

    public void setuMsId(Integer uMsId) {
        this.uMsId = uMsId;
    }

    public Integer getpUserId() {
        return pUserId;
    }

    public void setpUserId(Integer pUserId) {
        this.pUserId = pUserId;
    }

    public Integer getpManagerId() {
        return pManagerId;
    }

    public void setpManagerId(Integer pManagerId) {
        this.pManagerId = pManagerId;
    }

    public Integer getpBuildId() {
        return pBuildId;
    }

    public void setpBuildId(Integer pBuildId) {
        this.pBuildId = pBuildId;
    }

    public Integer getpRoomId() {
        return pRoomId;
    }

    public void setpRoomId(Integer pRoomId) {
        this.pRoomId = pRoomId;
    }
    
    public Integer getpOwnerId() {
		return pOwnerId;
	}

	public void setpOwnerId(Integer pOwnerId) {
		this.pOwnerId = pOwnerId;
	}
	
    public String getRegisterName() {
        return registerName;
    }

    public void setRegisterName(String registerName) {
        this.registerName = registerName == null ? null : registerName.trim();
    }

    public String getRegisterTelephone() {
        return registerTelephone;
    }

    public void setRegisterTelephone(String registerTelephone) {
        this.registerTelephone = registerTelephone == null ? null : registerTelephone.trim();
    }

	public Integer getIdentifyClassify() {
		return identifyClassify;
	}

	public void setIdentifyClassify(Integer identifyClassify) {
		this.identifyClassify = identifyClassify;
	}

    public String getApplyTime() {
        return applyTime;
    }

    public void setApplyTime(String applyTime) {
        this.applyTime = applyTime == null ? null : applyTime.trim();
    }

    public Integer getCheckStatus() {
		return checkStatus;
	}

	public void setCheckStatus(Integer checkStatus) {
		this.checkStatus = checkStatus;
	}

	public String getFailureInfo() {
		return failureInfo;
	}

	public void setFailureInfo(String failureInfo) {
		this.failureInfo = failureInfo;
	}

	public Boolean getPrivilege() {
		return privilege;
	}

	public void setPrivilege(Boolean privilege) {
		this.privilege = privilege;
	}
	
    public String getManagerName() {
		return managerName;
	}

	public void setManagerName(String managerName) {
		this.managerName = managerName;
	}

	public BigDecimal getWelfareFund() {
		return welfareFund;
	}

	public void setWelfareFund(BigDecimal welfareFund) {
		this.welfareFund = welfareFund;
	}

	public Management getManagerInfo() {
		return ManagerInfo;
	}

	public void setManagerInfo(Management managerInfo) {
		ManagerInfo = managerInfo;
	}
	
	public Room getRoomInfo() {
		return RoomInfo;
	}

	public void setRoomInfo(Room roomInfo) {
		RoomInfo = roomInfo;
	}

	public Building getBuildInfo() {
		return BuildInfo;
	}

	public void setBuildInfo(Building buildInfo) {
		BuildInfo = buildInfo;
	}
}