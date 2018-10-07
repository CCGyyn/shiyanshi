package com.zeng.ezsh.wy.dao;

import java.util.List;

import com.zeng.ezsh.wy.entity.GoodsReceiptAddress;
public interface GoodsReceiptAddressMapper {
    int deleteByPrimaryKey(Integer receiptAddressId);
    
    /*添加收货地址*/
    int addGoodsReceiptAddress(GoodsReceiptAddress record);
    
    /*更新地址内容*/
    int updateGoodsReceiptAddress(GoodsReceiptAddress record);
    
    /*获取收货地址的数量*/
    int getGoodsReceiptAddressAmount(GoodsReceiptAddress record);
    
    /*获取收货地址列表*/
    List<GoodsReceiptAddress> selectGoodsReceiptAddressList(GoodsReceiptAddress record);
    
    int insertSelective(GoodsReceiptAddress record);

    GoodsReceiptAddress selectByPrimaryKey(Integer receiptAddressId);

    int updateByPrimaryKeySelective(GoodsReceiptAddress record);

}