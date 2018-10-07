package com.zeng.ezsh.wy.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.zeng.ezsh.wy.dao.GoodsReceiptAddressMapper;
import com.zeng.ezsh.wy.entity.GoodsReceiptAddress;
import com.zeng.ezsh.wy.service.GoodsReceiptAddressService;
@Service
public class GoodsReceiptAddressServiceImpl implements
		GoodsReceiptAddressService {
	@Resource
	GoodsReceiptAddressMapper goodsReceiptAddressDao;
	/**
	 * @author qwc
	 * 2017年8月8日下午5:04:17
	 * @param record
	 * @return
	 * 添加收货地址
	 */
	@Override
	public int addGoodsReceiptAddress(GoodsReceiptAddress record) {
		// TODO Auto-generated method stub
		return goodsReceiptAddressDao.addGoodsReceiptAddress(record);
	}
	
	/**
	 * @author qwc
	 * 2017年8月8日下午5:45:59
	 * @param record
	 * @return
	 * 更新收货地址
	 */
	@Override
	public int updateGoodsReceiptAddress(GoodsReceiptAddress record) {
		// TODO Auto-generated method stub
		return goodsReceiptAddressDao.updateGoodsReceiptAddress(record);
	}
	
	/**
	 * @author qwc
	 * 2017年8月8日下午5:48:32
	 * @return
	 * 获取收货地址
	 */
	@Override
	public int getGoodsReceiptAddressAmount(GoodsReceiptAddress record) {
		// TODO Auto-generated method stub
		return goodsReceiptAddressDao.getGoodsReceiptAddressAmount(record);
	}
	
	/**
	 * @author qwc
	 * 2017年8月8日下午5:50:10
	 * @param receiptAddressId
	 * @return
	 * 获取收货地址列表
	 */
	@Override
	public List<GoodsReceiptAddress> selectGoodsReceiptAddressList(
			GoodsReceiptAddress record) {
		// TODO Auto-generated method stub
		return goodsReceiptAddressDao.selectGoodsReceiptAddressList(record);
	}


}
