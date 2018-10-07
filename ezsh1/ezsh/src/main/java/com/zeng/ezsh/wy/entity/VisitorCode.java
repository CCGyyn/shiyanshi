package com.zeng.ezsh.wy.entity;

public class VisitorCode {
    private Integer codeId;

    private Integer ptUserId;

    private Integer ptManagerId;

    private Integer ptBuildId;

    private Integer ptRoomId;
    
    private Integer ptLockId;
    
    private String managerName;

    private String buildName;

    private String roomNum;

    private String visitorName;

    private String visitorTelephone;

    private String visitorPassword;

    private String visitorValidityTimeStart;

    private String visitorValidityTimeEnd;

    private Byte vitsitStatus;
    
    private DeviceLock deviceLock;

    public Integer getCodeId() {
		return codeId;
	}

	public void setCodeId(Integer codeId) {
		this.codeId = codeId;
	}

	public Integer getPtUserId() {
        return ptUserId;
    }

    public void setPtUserId(Integer ptUserId) {
        this.ptUserId = ptUserId;
    }

    public Integer getPtManagerId() {
        return ptManagerId;
    }

    public void setPtManagerId(Integer ptManagerId) {
        this.ptManagerId = ptManagerId;
    }

    public Integer getPtBuildId() {
        return ptBuildId;
    }

    public void setPtBuildId(Integer ptBuildId) {
        this.ptBuildId = ptBuildId;
    }

    public Integer getPtRoomId() {
        return ptRoomId;
    }

    public void setPtRoomId(Integer ptRoomId) {
        this.ptRoomId = ptRoomId;
    }
    
    public Integer getPtLockId() {
		return ptLockId;
	}

	public void setPtLockId(Integer ptLockId) {
		this.ptLockId = ptLockId;
	}

	public String getManagerName() {
        return managerName;
    }

    public void setManagerName(String managerName) {
        this.managerName = managerName == null ? null : managerName.trim();
    }

    public String getBuildName() {
        return buildName;
    }

    public void setBuildName(String buildName) {
        this.buildName = buildName == null ? null : buildName.trim();
    }

    public String getRoomNum() {
		return roomNum;
	}

	public void setRoomNum(String roomNum) {
		this.roomNum = roomNum;
	}

	public String getVisitorName() {
        return visitorName;
    }

    public void setVisitorName(String visitorName) {
        this.visitorName = visitorName == null ? null : visitorName.trim();
    }

    public String getVisitorTelephone() {
        return visitorTelephone;
    }

    public void setVisitorTelephone(String visitorTelephone) {
        this.visitorTelephone = visitorTelephone == null ? null : visitorTelephone.trim();
    }

    public String getVisitorPassword() {
        return visitorPassword;
    }

    public void setVisitorPassword(String visitorPassword) {
        this.visitorPassword = visitorPassword == null ? null : visitorPassword.trim();
    }

    public String getVisitorValidityTimeStart() {
        return visitorValidityTimeStart;
    }

    public void setVisitorValidityTimeStart(String visitorValidityTimeStart) {
        this.visitorValidityTimeStart = visitorValidityTimeStart == null ? null : visitorValidityTimeStart.trim();
    }

    public String getVisitorValidityTimeEnd() {
        return visitorValidityTimeEnd;
    }

    public void setVisitorValidityTimeEnd(String visitorValidityTimeEnd) {
        this.visitorValidityTimeEnd = visitorValidityTimeEnd == null ? null : visitorValidityTimeEnd.trim();
    }

    public Byte getVitsitStatus() {
        return vitsitStatus;
    }

    public void setVitsitStatus(Byte vitsitStatus) {
        this.vitsitStatus = vitsitStatus;
    }

	public DeviceLock getDeviceLock() {
		return deviceLock;
	}

	public void setDeviceLock(DeviceLock deviceLock) {
		this.deviceLock = deviceLock;
	}
    
}