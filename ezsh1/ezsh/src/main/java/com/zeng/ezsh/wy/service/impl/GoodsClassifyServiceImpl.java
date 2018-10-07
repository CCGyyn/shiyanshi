package com.zeng.ezsh.wy.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.zeng.ezsh.wy.dao.GoodsClassfyMapper;
import com.zeng.ezsh.wy.entity.GoodsClassfy;
import com.zeng.ezsh.wy.service.GoodsClassifyService;
@Service
public class GoodsClassifyServiceImpl implements GoodsClassifyService {
	@Resource
	GoodsClassfyMapper goodsClassfyMapper;
	/**
	 * @author qwc
	 * 2017年10月2日下午10:02:37
	 * @param classfyId
	 * @return 获取商品分类集合
	 */
	@Override
	public List<GoodsClassfy> selectByPrimaryKey(int classfyId) {
		// TODO Auto-generated method stub
		return goodsClassfyMapper.selectByPrimaryKey(classfyId);
	}

}
