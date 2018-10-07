package com.zeng.ezsh.wy.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.zeng.ezsh.wy.dao.GoodsShoppingCartMapper;
import com.zeng.ezsh.wy.entity.GoodsShoppingCart;
import com.zeng.ezsh.wy.service.GoodsShoppingCartService;
@Service
public class GoodsShoppingCartServiceImpl implements GoodsShoppingCartService {
	@Resource
	GoodsShoppingCartMapper goodsShoppingCartMapperDao;
	
	@Override
	public int insertShoppingCart(GoodsShoppingCart record) {
		// TODO Auto-generated method stub
		return goodsShoppingCartMapperDao.insertShoppingCart(record);
	}

	@Override
	public int updateShoppingCartSelective(GoodsShoppingCart record) {
		// TODO Auto-generated method stub
		return goodsShoppingCartMapperDao.updateShoppingCartSelective(record);
	}

	@Override
	public List<GoodsShoppingCart> selectShoppingCart(Map<String, Object> param) {
		// TODO Auto-generated method stub
		return goodsShoppingCartMapperDao.selectShoppingCart(param);
	}

	@Override
	public int deleteShoppingCart(List<GoodsShoppingCart> record) {
		// TODO Auto-generated method stub
		return goodsShoppingCartMapperDao.deleteShoppingCart(record);
	}

}
