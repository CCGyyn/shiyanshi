package com.zeng.ezsh.wy.service;

import com.zeng.ezsh.wy.entity.GoodsDiscounts;

public interface GoodsDiscountsService {
	/*通过商品基本信息ID插入商品的优惠信息*/
	public int insertDiscountInfoByGoodsInfoId(GoodsDiscounts record);
	
	/*更新商品附加优惠信息*/
	public int updateDiscountInfoByGoodsInfoId(GoodsDiscounts record);
}
