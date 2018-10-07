package com.zeng.ezsh.wy.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.zeng.ezsh.wy.entity.Goods;
@Service
public interface GoodsService {
	/*添加商品*/
    public int addGood(List<Goods> goodsList);
    
    /*通过商品ID数组批量获取商品，用于订单提交*/
    public List<Goods> getGoodsByIds(String[] goodsIdsArray);
    
    /*批量更新库存*/
    public int updateGoodsBatch(List<Goods> goodsList);
    
    /*更新商品详细信息*/
    public int updateGoodsDetails(Map<String,Object> param);
    
    /*用于查看订单*/
    public List<Goods> getGoodsListByIdsForOrder(String[] goodsIdArray);
    
    /*通过商品的基本信息ID获取商品的详细信息*/
    public List<Goods> selectGoodsListPrimaryKey(int goodsInfoId);
}
