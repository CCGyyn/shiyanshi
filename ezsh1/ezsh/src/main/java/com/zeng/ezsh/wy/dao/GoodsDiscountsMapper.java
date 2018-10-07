package com.zeng.ezsh.wy.dao;
import com.zeng.ezsh.wy.entity.GoodsDiscounts;

public interface GoodsDiscountsMapper {
	/*通过商品基本信息ID插入商品的优惠信息*/
	int insertDiscountInfoByGoodsInfoId(GoodsDiscounts record);
	
	/*更新商品附加优惠信息*/
	int updateDiscountInfoByGoodsInfoId(GoodsDiscounts record);
    /*int deleteByPrimaryKey(Integer pGoodsInfoId);

    int insertSelective(GoodsDiscounts record);
    
    GoodsDiscounts selectByPrimaryKey(Integer pGoodsInfoId);

    int updateByPrimaryKeySelective(GoodsDiscounts record);

    int updateByPrimaryKey(GoodsDiscounts record);*/
}