package com.zeng.ezsh.wy.dao;
import java.util.List;
import java.util.Map;

import com.zeng.ezsh.wy.entity.GoodsOrder;
import com.zeng.ezsh.wy.entity.GoodsOrderDetails;

public interface GoodsOrderDetailsMapper {
    /*批量生产详细订单*/
    int insertOrderDetails(List<GoodsOrderDetails> recordList);
    
    /*更新订单详细情况*/
    int updateOrderDetails(GoodsOrderDetails record);
    
    /*批量更新订单详细信息集合*/
   /* int updateGoodsOrderDetailsList(List<GoodsOrderDetails> orderList);*/
    
    /*根据订单ID获取订单详细信息集合*/
    List<GoodsOrderDetails> getGoodsOrderDetailsListByOrderId(int orderId);
    
    /*按条件获取订单详细信息集合（后台）*/
    List<GoodsOrder> getGoodsOrderDetailsListByParam(Map<String, Object> param);
    
    int insertSelective(GoodsOrderDetails record);

    GoodsOrderDetails selectByPrimaryKey(Integer orderDetailsId);
    
    int updateByPrimaryKeySelective(GoodsOrderDetails record);

    int updateByPrimaryKey(GoodsOrderDetails record);
}