package com.zeng.ezsh.wy.dao;

import org.apache.ibatis.annotations.Param;

import com.zeng.ezsh.wy.entity.WechatToken;

public interface WechatTokenMapper {
	/*获取接口调用凭据*/
	WechatToken select(WechatToken record);
	
    int insert(WechatToken record);
    
    /*更新接口调用凭据*/
    int update(WechatToken record);
    
}