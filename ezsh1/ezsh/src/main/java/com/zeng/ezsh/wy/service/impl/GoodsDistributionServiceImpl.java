package com.zeng.ezsh.wy.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.zeng.ezsh.wy.dao.GoodsDistributionMapper;
import com.zeng.ezsh.wy.entity.GoodsDistribution;
import com.zeng.ezsh.wy.service.GoodsDistributionService;
@Service
public class GoodsDistributionServiceImpl implements GoodsDistributionService {
	@Resource
	GoodsDistributionMapper goodsDistributionMapperDao;
	@Override
	public List<GoodsDistribution> getDistributionListById(
			String[] gDistributionIdArrayStrings) {
		// TODO Auto-generated method stub
		return goodsDistributionMapperDao.getDistributionListById(gDistributionIdArrayStrings);
	}

}
