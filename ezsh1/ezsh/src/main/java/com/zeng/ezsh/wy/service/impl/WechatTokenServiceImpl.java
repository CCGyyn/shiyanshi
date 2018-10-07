package com.zeng.ezsh.wy.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.zeng.ezsh.wy.dao.WechatTokenMapper;
import com.zeng.ezsh.wy.entity.WechatToken;
import com.zeng.ezsh.wy.service.WechatTokenService;
@Service
public class WechatTokenServiceImpl implements WechatTokenService {
	@Resource
	WechatTokenMapper wechatTokenMapper;
	
	/**
	 * @author qwc
	 * 2017年10月29日下午3:51:38
	 * @param record
	 * @return 获取token
	 */
	@Override
	public WechatToken select(WechatToken record) {
		// TODO Auto-generated method stub
		return wechatTokenMapper.select(record);
	}
	
	/**
	 * @author qwc
	 * 2017年10月29日下午3:51:59
	 * @param record
	 * @return 更新token
	 */
	@Override
	public int update(WechatToken record) {
		// TODO Auto-generated method stub
		return wechatTokenMapper.update(record);
	}

}
