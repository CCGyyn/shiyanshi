package com.zeng.ezsh.wy.dao;

import java.util.List;

import com.zeng.ezsh.wy.entity.GoodsAppraise;
public interface GoodsAppraiseMapper {
    int deleteByPrimaryKey(Integer appraiseId);
    /*添加商品评价*/
    int addGoodsAppraise(GoodsAppraise record);
    
    /*获取商品评价列表*/
    List<GoodsAppraise> getGoodsAppraiseList(GoodsAppraise record);
    
    int insertSelective(GoodsAppraise record);

    GoodsAppraise selectByPrimaryKey(Integer appraiseId);
    
    int updateByPrimaryKeySelective(GoodsAppraise record);

    int updateByPrimaryKey(GoodsAppraise record);
}