package com.zeng.ezsh.wy.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

public class PlateRecordResponse {
	@JsonProperty
	String ActType;
	@JsonProperty
	String Code;
	@JsonProperty
	String Msg;
	@JsonProperty
	String CRC;

	@Override
	public String toString() {
		return "PlateRecordResponse [ActType=" + ActType + ", Code=" + Code
				+ ", Msg=" + Msg + ", CRC=" + CRC + "]";
	}
	@JsonIgnore
	public String getActType() {
		return ActType;
	}
	@JsonIgnore
	public void setActType(String actType) {
		ActType = actType;
	}
	@JsonIgnore
	public String getCode() {
		return Code;
	}
	@JsonIgnore
	public void setCode(String code) {
		Code = code;
	}
	@JsonIgnore
	public String getMsg() {
		return Msg;
	}
	@JsonIgnore
	public void setMsg(String msg) {
		Msg = msg;
	}
	@JsonIgnore
	public String getCRC() {
		return CRC;
	}
	@JsonIgnore
	public void setCRC(String cRC) {
		CRC = cRC;
	}

	public PlateRecordResponse(String actType, String code, String msg,
			String cRC) {
		super();
		ActType = actType;
		Code = code;
		Msg = msg;
		CRC = cRC;
	}

	public PlateRecordResponse(String actType, String code, String msg) {
		super();
		ActType = actType;
		Code = code;
		Msg = msg;
	}
	
	
	
	
}
