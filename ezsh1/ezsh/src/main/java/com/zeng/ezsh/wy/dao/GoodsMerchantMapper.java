package com.zeng.ezsh.wy.dao;

import java.util.List;

import com.zeng.ezsh.wy.entity.GoodsMerchant;

public interface GoodsMerchantMapper {
	
	/*通过商家ID获取商家信息*/
	GoodsMerchant getMerchantInfoById(Integer merchantId);

	/*获取商家集合*/
	List<GoodsMerchant> selectByPrimaryKey(GoodsMerchant record);
	
	/*添加商家*/
	int insertSelective(GoodsMerchant record);
	
	/*更新商家信息*/
	int updateByPrimaryKeySelective(GoodsMerchant record);
	
	/*获取商家数量*/
	int getMerchantAmount(GoodsMerchant record);
    /*int insert(GoodsMerchant record);

    GoodsMerchant selectByPrimaryKey(Integer merchantId);
    
    int updateByPrimaryKeySelective(GoodsMerchant record);

    int updateByPrimaryKey(GoodsMerchant record);*/
}