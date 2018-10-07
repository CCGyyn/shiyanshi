package com.zeng.ezsh.wy.dao;

import com.zeng.ezsh.wy.entity.GoodsOrder;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

public interface GoodsOrderMapper {
	/*创建商品订单*/
	int createGoodsOrder(GoodsOrder record);
    
    /*通过条件获取用户订单集合*/
    List<GoodsOrder> getGoodsOrders(Map<String, Object> param);
    
    /*通过条件获取用户订单基本信息集合（用于后台）*/
    List<GoodsOrder> getGoodsOrdersToAdmin(Map<String, Object> param);
    
    /*根据订单ID获取订单信息*/
    GoodsOrder getGoodsOrdersByOrderId(Map<String, Object> param);
   
    /*通过订单ID更新订单*/
    int updateOrderByOrderId(GoodsOrder record);
    
    /*通过商家后台生成的订单号更新订单信息(移动端)*/
    int updateOrderSerialNum(GoodsOrder record);
    
    /*用户确定收货*/
    int confirmAcceptGoods(GoodsOrder record);
    
    /*检测订单号是否存在（移动端）*/
    GoodsOrder checkOrderSerialNumIsOn(GoodsOrder record);
    
    /*int deleteByPrimaryKey(Integer orderId);
    
    int insertSelective(GoodsOrder record);

    GoodsOrder selectByPrimaryKey(Integer orderId);
    
    int updateByPrimaryKeySelective(GoodsOrder record);

    int updateByPrimaryKeyWithBLOBs(GoodsOrder record);*/

}