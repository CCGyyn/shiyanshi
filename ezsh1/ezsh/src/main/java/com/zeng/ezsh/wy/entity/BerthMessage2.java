package com.zeng.ezsh.wy.entity;

import java.util.Date;
import java.util.List;

import com.zeng.ezsh.wy.entity.BerthMessage;

public class BerthMessage2 {

	BerthMessage berthMessage;
	
	List<String> plateNum;
//	String plateNumJson;

	public BerthMessage getBerthMessage() {
		return berthMessage;
	}

	public void setBerthMessage(BerthMessage berthMessage) {
		this.berthMessage = berthMessage;
	}
//
//	public String getPlateNumJson() {
//		return plateNumJson;
//	}
//
//	public void setPlateNumJson(String plateNumJson) {
//		this.plateNumJson = plateNumJson;
//	}


	
	public List<String> getPlateNum() {
		return plateNum;
	}

	public void setPlateNum(List<String> plateNum) {
		this.plateNum = plateNum;
	}

	@Override
	public String toString() {
		return "BerthMessage2 [berthMessage=" + berthMessage + ", plateNum="
				+ plateNum + "]";
	}
	
	
}
