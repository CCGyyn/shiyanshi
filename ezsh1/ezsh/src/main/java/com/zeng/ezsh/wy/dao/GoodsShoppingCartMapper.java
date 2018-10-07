package com.zeng.ezsh.wy.dao;

import java.util.List;
import java.util.Map;

import com.zeng.ezsh.wy.entity.GoodsShoppingCart;

public interface GoodsShoppingCartMapper {
	/*加入购物车*/
	int insertShoppingCart(GoodsShoppingCart record);
	
	/*更新购物车*/
	int updateShoppingCartSelective(GoodsShoppingCart record);
	
	/*移出购物车*/
    int deleteShoppingCart(List<GoodsShoppingCart>  record);
    
    /*获取个人购物车*/
    List<GoodsShoppingCart> selectShoppingCart(Map<String,Object> param);
  
}