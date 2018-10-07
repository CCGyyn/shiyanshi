package com.zeng.ezsh.wy.entity;

public class DeviceLock {
    private Integer lockId;

    private String deviceId;
    
    private String devicePass;
    
    private Integer ptBuildId;

    private String buildName;

    private Integer ptManagerId;

    private String managerName;
    
    private Integer ptUserId;

    public Integer getLockId() {
        return lockId;
    }

    public void setLockId(Integer lockId) {
        this.lockId = lockId;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId == null ? null : deviceId.trim();
    }
    
    public String getDevicePass() {
		return devicePass;
	}

	public void setDevicePass(String devicePass) {
		this.devicePass = devicePass;
	}

	public Integer getPtBuildId() {
        return ptBuildId;
    }

    public void setPtBuildId(Integer ptBuildId) {
        this.ptBuildId = ptBuildId;
    }

    public String getBuildName() {
        return buildName;
    }

    public void setBuildName(String buildName) {
        this.buildName = buildName == null ? null : buildName.trim();
    }

    public Integer getPtManagerId() {
        return ptManagerId;
    }

    public void setPtManagerId(Integer ptManagerId) {
        this.ptManagerId = ptManagerId;
    }

    public String getManagerName() {
        return managerName;
    }

    public void setManagerName(String managerName) {
        this.managerName = managerName == null ? null : managerName.trim();
    }

	public Integer getPtUserId() {
		return ptUserId;
	}

	public void setPtUserId(Integer ptUserId) {
		this.ptUserId = ptUserId;
	}
}