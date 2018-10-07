package com.zeng.ezsh.wy.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.zeng.ezsh.wy.dao.GoodsSlideShowMapper;
import com.zeng.ezsh.wy.entity.GoodsSlideShow;
import com.zeng.ezsh.wy.service.GoodsSlideShowService;
@Service
public class GoodsSlideShowServiceImpl implements GoodsSlideShowService {
	@Resource
	GoodsSlideShowMapper goodsSlideShowMapperDao;

	/**
	 * @description 通过条件获取轮播图集合
	 */
	@Override
	public List<GoodsSlideShow> getGoodsSlideShowsByParam(
			Map<String, Object> param) {
		// TODO Auto-generated method stub
		List<GoodsSlideShow> goodsSlideShowsList = goodsSlideShowMapperDao
				.getGoodsSlideShowsByParam(param);
		return goodsSlideShowsList;
	}

	/**
	 * @author qwc 2017年11月18日下午6:45:38
	 * @param record
	 * @return 添加轮播图
	 */
	@Override
	public int insertSelective(GoodsSlideShow record) {
		// TODO Auto-generated method stub
		return goodsSlideShowMapperDao.insertSelective(record);
	}

	/**
	 * @description 获取商城首页轮播图（后台）
	 */
	@Override
	public List<GoodsSlideShow> getGoodsSlideShowsA(GoodsSlideShow record) {
		// TODO Auto-generated method stub
		return goodsSlideShowMapperDao.getGoodsSlideShowsA(record);
	}

	/**
	 * @description 删除商品首页轮播图
	 */
	@Override
	public int deleteShowImageUrl(GoodsSlideShow record) {
		// TODO Auto-generated method stub
		return goodsSlideShowMapperDao.deleteShowImageUrl(record);
	}

	/**
	 * @description 更新商城首页轮播图
	 */
	@Override
	public int updateShowImageUrl(GoodsSlideShow record) {
		// TODO Auto-generated method stub
		return goodsSlideShowMapperDao.updateShowImageUrl(record);
	}

}
