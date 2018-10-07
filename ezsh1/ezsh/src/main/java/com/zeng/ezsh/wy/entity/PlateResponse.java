package com.zeng.ezsh.wy.entity;

import java.util.Map;

public class PlateResponse {

	private String tradeCode;
	
	private String responseCode;
	
	private String responseMessage;
	
	private String CRCCode;
	
	private Map<String,Object> data;

	public String getTradeCode() {
		return tradeCode;
	}

	public void setTradeCode(String tradeCode) {
		this.tradeCode = tradeCode;
	}

	public String getResponseCode() {
		return responseCode;
	}

	public void setResponseCode(String responseCode) {
		this.responseCode = responseCode;
	}

	public String getResponseMessage() {
		return responseMessage;
	}

	public void setResponseMessage(String responseMessage) {
		this.responseMessage = responseMessage;
	}

	public String getCRCCode() {
		return CRCCode;
	}

	public void setCRCCode(String cRCCode) {
		CRCCode = cRCCode;
	}

	public Map<String, Object> getData() {
		return data;
	}

	public void setData(Map<String, Object> data) {
		this.data = data;
	}

	public PlateResponse() {
		super();
		// TODO Auto-generated constructor stub
	}

	public PlateResponse(String tradeCode, String responseCode,
			String responseMessage, String cRCCode) {
		super();
		this.tradeCode = tradeCode;
		this.responseCode = responseCode;
		this.responseMessage = responseMessage;
		CRCCode = cRCCode;
	}

	public PlateResponse(String tradeCode, String responseCode,
			String responseMessage, String cRCCode, Map<String, Object> data) {
		super();
		this.tradeCode = tradeCode;
		this.responseCode = responseCode;
		this.responseMessage = responseMessage;
		CRCCode = cRCCode;
		this.data = data;
	}

	@Override
	public String toString() {
		return "PlateResponse [tradeCode=" + tradeCode + ", responseCode="
				+ responseCode + ", responseMessage=" + responseMessage
				+ ", CRCCode=" + CRCCode + ", data=" + data + "]";
	}
	
	
	
}
