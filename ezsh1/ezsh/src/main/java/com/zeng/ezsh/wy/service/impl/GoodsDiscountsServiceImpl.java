package com.zeng.ezsh.wy.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.zeng.ezsh.wy.dao.GoodsDiscountsMapper;
import com.zeng.ezsh.wy.entity.GoodsDiscounts;
import com.zeng.ezsh.wy.service.GoodsDiscountsService;
@Service
public class GoodsDiscountsServiceImpl implements GoodsDiscountsService{
	@Resource
	GoodsDiscountsMapper goodsDiscountsMapperDao;
	/**
	 * @author qwc
	 * 2017年8月22日下午6:11:57
	 * @param record
	 * @return
	 * 插入商品的优惠信息
	 */
	@Override
	public int insertDiscountInfoByGoodsInfoId(GoodsDiscounts record) {
		// TODO Auto-generated method stub
		int insertStatus=goodsDiscountsMapperDao.insertDiscountInfoByGoodsInfoId(record);
		return insertStatus;
	}
	
	/**
	 * @author qwc
	 * 2017年11月14日下午11:22:38
	 * @param record
	 * @return 更新商品附加优惠信息
	 */
	@Override
	public int updateDiscountInfoByGoodsInfoId(GoodsDiscounts record) {
		// TODO Auto-generated method stub
		return goodsDiscountsMapperDao.updateDiscountInfoByGoodsInfoId(record);
	}

}
