package com.zeng.ezsh.wy.dao;

import com.zeng.ezsh.wy.entity.ChargeItem;

import java.util.List;
import java.util.Map;

public interface ChargeItemMapper {
	/*添加收费项目*/
	int insert(ChargeItem record);
	
	/*查询收费项目集合*/
	List<ChargeItem> selectByParam(Map<String, Object> param);

	List<ChargeItem> selectByManagerId(Integer managerId);

	ChargeItem selectChargeItemsById(Integer chargeId);
	
	/*更新收费项目*/
	int updateByPrimaryKeySelective(ChargeItem record);
	
	/*删除收费项目*/
	int delete(ChargeItem record);
	
	/*检测收费项目名是否在使用*/
	int checkChargeItemNameIsOnUse(ChargeItem record);
	
	/*检测收费项目打印次序是否存在(添加收费项目时)*/
	int checkChargeItemNamePrintOrderIsOnUse(ChargeItem record);
	
	/*检测收费项目打印次序是否存在(更新收费项目时)*/
	int checkChargeItemNamePrintOrderIsOnUseOnUpdate(ChargeItem record);
}