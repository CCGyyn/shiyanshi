package com.zeng.ezsh.wy.entity;

import java.util.Date;

public class WechatToken {
	private Integer tokenId;
	
    private Date expiresToTime;

    private String accessToken;
    
	public Integer getTokenId() {
		return tokenId;
	}

	public void setTokenId(Integer tokenId) {
		this.tokenId = tokenId;
	}

	public Date getExpiresToTime() {
		return expiresToTime;
	}

	public void setExpiresToTime(Date expiresToTime) {
		this.expiresToTime = expiresToTime;
	}

    public String getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}
}