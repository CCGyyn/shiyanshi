package com.zeng.ezsh.wy.service;

import java.util.List;
import java.util.Map;

import net.sf.jsqlparser.statement.delete.Delete;

import com.zeng.ezsh.wy.entity.ChargeItem;

public interface ChargeItemService {
	/*添加收费项目*/
	public int insert(ChargeItem record);
	
	/*查询收费项目集合*/
	public List<ChargeItem> selectByParam(Map<String , Object> param);
	
	/*更新收费项目*/
	public int updateByPrimaryKeySelective(ChargeItem record);
	
	/*删除收费项目*/
	public int delete(ChargeItem record);
	
	/*检测收费项目名是否在使用*/
	public int checkChargeItemNameIsOnUse(ChargeItem record);
	
	/*检测收费项目打印次序是否存在*/
	public int checkChargeItemNamePrintOrderIsOnUse(ChargeItem record);
	
	/*检测收费项目打印次序是否存在(更新收费项目时)*/
	public int checkChargeItemNamePrintOrderIsOnUseOnUpdate(ChargeItem record);
}
