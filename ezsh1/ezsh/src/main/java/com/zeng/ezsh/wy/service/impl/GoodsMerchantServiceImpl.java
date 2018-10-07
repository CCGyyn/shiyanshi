package com.zeng.ezsh.wy.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.zeng.ezsh.wy.dao.GoodsMapper;
import com.zeng.ezsh.wy.dao.GoodsMerchantMapper;
import com.zeng.ezsh.wy.entity.GoodsMerchant;
import com.zeng.ezsh.wy.service.GoodsMerchantService;
@Service
public class GoodsMerchantServiceImpl implements GoodsMerchantService {
	@Resource
	GoodsMerchantMapper goodsMerchantMapper;
	
	/**
	 * @author qwc
	 * 2017年11月14日下午5:43:20
	 * @param record
	 * @return 获取商家集合
	 */
	@Override
	public List<GoodsMerchant> selectByPrimaryKey(GoodsMerchant record) {
		// TODO Auto-generated method stub
		return goodsMerchantMapper.selectByPrimaryKey(record);
	}

	/**
	 * @author qwc
	 * 2017年11月14日下午9:01:36
	 * @param record
	 * @return 添加商家
	 */
	@Override
	public int insertSelective(GoodsMerchant record) {
		// TODO Auto-generated method stub
		return goodsMerchantMapper.insertSelective(record);
	}
	
	/**
	 * @author qwc
	 * 2017年11月14日下午9:01:24
	 * @param record
	 * @return 更新商家信息
	 */
	@Override
	public int updateByPrimaryKeySelective(GoodsMerchant record) {
		// TODO Auto-generated method stub
		return goodsMerchantMapper.updateByPrimaryKeySelective(record);
	}
	
	/**
	 * @author qwc
	 * 2017年11月14日下午9:44:30
	 * @param record
	 * @return 获取商家数量
	 */
	@Override
	public int getMerchantAmount(GoodsMerchant record) {
		// TODO Auto-generated method stub
		return goodsMerchantMapper.getMerchantAmount(record);
	}

}
