package com.zeng.ezsh.wy.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.zeng.ezsh.wy.dao.GoodsAppraiseMapper;
import com.zeng.ezsh.wy.dao.GoodsOrderDetailsMapper;
import com.zeng.ezsh.wy.entity.GoodsAppraise;
import com.zeng.ezsh.wy.entity.GoodsOrderDetails;
import com.zeng.ezsh.wy.service.GoodsAppraiseService;
@Service
public class GoodsAppraiseServiceImpl implements GoodsAppraiseService {
	@Resource
	GoodsAppraiseMapper goodsAppraiseMapperDao;
	@Resource
	GoodsOrderDetailsMapper goodsOrderDetailsMapper;
	
	/**
	 * @author qwc
	 * 2017年8月8日下午8:45:33
	 * @param record
	 * @return
	 * 添加商品评价
	 */
	@Override
	public int addGoodsAppraise(GoodsAppraise record) {
		// TODO Auto-generated method stub
		return goodsAppraiseMapperDao.addGoodsAppraise(record);
	}
	
	/**
	 * @author qwc
	 * 2017年8月8日下午9:01:37
	 * @param record
	 * @return
	 * 获取商品评价列表
	 */
	@Override
	public List<GoodsAppraise> getGoodsAppraiseList(GoodsAppraise record) {
		// TODO Auto-generated method stub
		return goodsAppraiseMapperDao.getGoodsAppraiseList(record);
	}
	
	/**
	 * @author qwc
	 * 2017年12月4日下午5:04:09
	 * @param goodsAppraiseModel
	 * @param goodsOrderDetails
	 * @return 执行评价操作
	 */
	@Override
	public int insertUpdateAppraise(GoodsAppraise goodsAppraiseModel, GoodsOrderDetails goodsOrderDetails) {
		// TODO Auto-generated method stub
		// 添加评价
		int addGoodsComment = goodsAppraiseMapperDao.addGoodsAppraise(goodsAppraiseModel);//评价
		// 设置评价状态
		goodsOrderDetails.setOrderDetailsId(goodsOrderDetails.getOrderDetailsId());
		goodsOrderDetails.setAppraiseStatus(1);
		// 执行更新订单评价状态
		int upStatus = goodsOrderDetailsMapper.updateOrderDetails(goodsOrderDetails);
		
		if(upStatus>0 && addGoodsComment > 0) {
			return 1;
		} else {
			return 0;
		}
	}

}
