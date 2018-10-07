package com.zeng.ezsh.wy.service;

import java.util.List;

import com.zeng.ezsh.wy.entity.GoodsReceiptAddress;

public interface GoodsReceiptAddressService {
	/*添加收货地址*/
	public int addGoodsReceiptAddress(GoodsReceiptAddress record);
	
	/*更新地址内容*/
    public int updateGoodsReceiptAddress(GoodsReceiptAddress record);
    
    /*获取收货地址的数量*/
    public int getGoodsReceiptAddressAmount(GoodsReceiptAddress record);
    
    /*获取收货地址列表*/
    public List<GoodsReceiptAddress> selectGoodsReceiptAddressList(GoodsReceiptAddress record);
}
