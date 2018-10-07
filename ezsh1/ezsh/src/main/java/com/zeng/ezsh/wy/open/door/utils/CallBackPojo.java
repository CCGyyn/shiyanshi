package com.zeng.ezsh.wy.open.door.utils;

import java.util.List;

public class CallBackPojo {
	private String DEVICEID;
	
	private String RESULT;
	
	private String OPERATE;
	
	private List<String> PARAMS;

	public String getDEVICEID() {
		return DEVICEID;
	}

	public void setDEVICEID(String dEVICEID) {
		DEVICEID = dEVICEID;
	}

	public String getRESULT() {
		return RESULT;
	}

	public void setRESULT(String rESULT) {
		RESULT = rESULT;
	}

	public String getOPERATE() {
		return OPERATE;
	}

	public void setOPERATE(String oPERATE) {
		OPERATE = oPERATE;
	}

	public List<String> getPARAMS() {
		return PARAMS;
	}

	public void setPARAMS(List<String> pARAMS) {
		PARAMS = pARAMS;
	}
}
