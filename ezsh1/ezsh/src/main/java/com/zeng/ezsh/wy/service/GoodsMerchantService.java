package com.zeng.ezsh.wy.service;

import java.util.List;

import com.zeng.ezsh.wy.entity.GoodsMerchant;

public interface GoodsMerchantService {
	/*获取商家集合*/
	public List<GoodsMerchant> selectByPrimaryKey(GoodsMerchant record);
	
	/*添加商家*/
	public int insertSelective(GoodsMerchant record);
	
	/*更新商家信息*/
	public int updateByPrimaryKeySelective(GoodsMerchant record);
	
	/*获取商家数量*/
	public int getMerchantAmount(GoodsMerchant record);
}
