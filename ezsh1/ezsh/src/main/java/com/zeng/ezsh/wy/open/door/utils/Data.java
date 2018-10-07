package com.zeng.ezsh.wy.open.door.utils;

public class Data {
	private String DEVICEID;//设备ID
	
	private String CMD;//操作码
	
	private String status;
	
	public String getDEVICEID() {
		return DEVICEID;
	}
	public void setDEVICEID(String dEVICEID) {
		DEVICEID = dEVICEID;
	}
	public String getCMD() {
		return CMD;
	}
	public void setCMD(String cMD) {
		CMD = cMD;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
}
