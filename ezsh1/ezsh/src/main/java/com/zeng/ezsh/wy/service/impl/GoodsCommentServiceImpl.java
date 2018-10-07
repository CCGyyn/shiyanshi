package com.zeng.ezsh.wy.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.zeng.ezsh.wy.dao.GoodsAppraiseMapper;
import com.zeng.ezsh.wy.dao.GoodsCommentMapper;
import com.zeng.ezsh.wy.entity.GoodsComment;
import com.zeng.ezsh.wy.service.GoodsCommentService;
@Service
public class GoodsCommentServiceImpl implements GoodsCommentService {
	@Resource
	GoodsCommentMapper goodsCommentMapperDao;
	/**
	 * @author qwc
	 * 2017年8月8日下午9:45:10
	 * @param record
	 * @return
	 * 添加商品评价回复
	 */
	@Override
	public int addComment(GoodsComment record) {
		// TODO Auto-generated method stub
		return goodsCommentMapperDao.addComment(record);
	}
	
	/**
	 * @author qwc
	 * 2017年8月8日下午10:04:05
	 * @param gCommentId
	 * @return
	 * 获取商品评价的回复集合
	 */
	@Override
	public List<GoodsComment> selectGoodsCommentList(GoodsComment record) {
		// TODO Auto-generated method stub
		return goodsCommentMapperDao.selectGoodsCommentList(record);
	}

}
