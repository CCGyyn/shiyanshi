package com.zeng.ezsh.wy.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.zeng.ezsh.wy.entity.Goods;
public interface GoodsMapper {
	/*添加商品*/
    int addGood(List<Goods> goodsList);
    
    /*更新商品类别信息*/
    int updateByPrimaryKeySelective(Goods record);
    
    /*通过商品ID数组批量获取商品，用于订单提交*/
    List<Goods> getGoodsByIds(String[] goodsIdArray);
    
    /*用于查看订单*/
    List<Goods> getGoodsListByIdsForOrder(String[] goodsIdArray);
    
    /*批量更新商品信息*/
    int updateGoodsByGoodsId(List<Goods> goodsList);
    
    /*通过商品的基本信息ID获取商品的详细信息*/
    List<Goods> selectGoodsListPrimaryKey(int goodsInfoId); 
    
    /*更新商品详细信息*/
    int updateGoodsDetails(Map<String,Object> param);
    
    int insertSelective(Goods record);
    
    Goods selectByPrimaryKey(Integer goosdId);

    int deleteByPrimaryKey(Integer goosdId);
    
    int updateByPrimaryKey(Goods record);
}