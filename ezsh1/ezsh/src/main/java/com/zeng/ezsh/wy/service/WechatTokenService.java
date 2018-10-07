package com.zeng.ezsh.wy.service;

import com.zeng.ezsh.wy.entity.WechatToken;
public interface WechatTokenService {
	/*获取接口调用凭据*/
	public WechatToken select(WechatToken record);
	
	/*更新接口调用凭据*/
    public int update(WechatToken record);
}
