package com.zeng.ezsh.wy.dao;

import java.util.List;
import java.util.Map;

import com.zeng.ezsh.wy.entity.Goods;
import com.zeng.ezsh.wy.entity.GoodsInfo;
public interface GoodsInfoMapper {
    int deleteByPrimaryKey(Integer goodsInfoId);

    /*添加商品基本信息*/
    public int addGoodsInfo(GoodsInfo record);
    
    /*通过条件获取商品基本信息列表*/
    List<GoodsInfo> getGoodsInfoListByParam(Map<String,Object> param);
    
    /*通过条件获取商品基本信息列表（用于后台）*/
    List<GoodsInfo> getGoodsInfoListByParamToAdmin(Map<String,Object> param);
    
    int insertSelective(GoodsInfo record);
    
    /*根据商品的基本信息ID获取商品的详细信息*/
    GoodsInfo getGoodsDetailsByGoodsInfoId(Integer goodsInfoId);
    
    /*更新商品基本信息*/
    int updateByPrimaryKeySelective(GoodsInfo record);

    
    /*获取每日推荐商品基本信息列表
    List<GoodsInfo> getPerDayRecommentGoodsInfoList();*/
    
   /* int updateByPrimaryKey(GoodsInfo record);*/
}