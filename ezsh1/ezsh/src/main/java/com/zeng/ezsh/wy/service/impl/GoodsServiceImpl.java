package com.zeng.ezsh.wy.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.zeng.ezsh.wy.dao.GoodsMapper;
import com.zeng.ezsh.wy.entity.Goods;
import com.zeng.ezsh.wy.service.GoodsService;
@Service
public class GoodsServiceImpl implements GoodsService {
	@Resource
	GoodsMapper goodsMapperDao;
	/**
	 * @author qwc
	 * 2017年7月27日下午8:49:27
	 * @param record
	 * @return
	 * 添加商品
	 */
	@Override
	public int addGood(List<Goods> goodsList) {
		// TODO Auto-generated method stub
		return goodsMapperDao.addGood(goodsList);
	}
	
	/**
	 * @author qwc
	 * 2017年8月7日上午2:05:23
	 * @param goodsIdArray
	 * @return
	 * 根据商品ID数组批量获取商品，用于提交订单
	 */
	@Override
	public List<Goods> getGoodsByIds(String[] goodsIdsArray) {
		// TODO Auto-generated method stub
		return goodsMapperDao.getGoodsByIds(goodsIdsArray);
	}

	/**
	 * @author qwc
	 * 2017年8月7日下午3:58:44
	 * @param goodsList
	 * @return
	 * 批量更新库存
	 */
	@Override
	public int updateGoodsBatch(List<Goods> goodsList) {
		// TODO Auto-generated method stub
		return goodsMapperDao.updateGoodsByGoodsId(goodsList);
	}

	@Override
	public List<Goods> getGoodsListByIdsForOrder(String[] goodsIdArray) {
		// TODO Auto-generated method stub
		return goodsMapperDao.getGoodsListByIdsForOrder(goodsIdArray);
	}

	@Override
	public List<Goods> selectGoodsListPrimaryKey(int goodsInfoId) {
		// TODO Auto-generated method stub
		return goodsMapperDao.selectGoodsListPrimaryKey(goodsInfoId);
	}
	
	/**
	 * @author qwc
	 * 2017年11月20日下午8:06:54
	 * @param param
	 * @return 更新商品详细信息
	 */
	@Override
	public int updateGoodsDetails(Map<String, Object> param) {
		// TODO Auto-generated method stub
		return goodsMapperDao.updateGoodsDetails(param);
	}
}
