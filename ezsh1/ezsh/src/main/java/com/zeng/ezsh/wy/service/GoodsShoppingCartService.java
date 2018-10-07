package com.zeng.ezsh.wy.service;

import java.util.List;
import java.util.Map;

import com.zeng.ezsh.wy.entity.GoodsShoppingCart;

public interface GoodsShoppingCartService {
	/*加入购物车*/
	public int insertShoppingCart(GoodsShoppingCart record);
	
	/*更新购物车*/
	public int updateShoppingCartSelective(GoodsShoppingCart record);
	
	/*移出购物车*/
    public int deleteShoppingCart(List<GoodsShoppingCart>  record);
    
    /*获取个人购物车*/
    public List<GoodsShoppingCart> selectShoppingCart(Map<String,Object> param);
}
