package com.zeng.ezsh.wy.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.zeng.ezsh.wy.dao.ChargeItemMapper;
import com.zeng.ezsh.wy.entity.ChargeItem;
import com.zeng.ezsh.wy.service.ChargeItemService;
@Service
public class ChargeItemServiceImpl implements ChargeItemService {
	@Resource
	ChargeItemMapper chargeItemMapperDao;
	
	/**
	 * @author qwc
	 * 2017年9月16日下午9:45:41
	 * @param record
	 * @return
	 * 添加收费项目
	 */
	@Override
	public int insert(ChargeItem record) {
		// TODO Auto-generated method stub
		return chargeItemMapperDao.insert(record);
	} 
	
	/**
	 * @author qwc
	 * 2017年9月16日下午9:45:28
	 * @param param
	 * @return
	 * 查询收费项目
	 */
	@Override
	public List<ChargeItem> selectByParam(Map<String, Object> param) {
		// TODO Auto-generated method stub
		return chargeItemMapperDao.selectByParam(param);
	}
	
	/**
	 * @author qwc
	 * 2017年9月16日下午9:45:17
	 * @param record
	 * @return
	 * 更新收费项目
	 */
	@Override
	public int updateByPrimaryKeySelective(ChargeItem record) {
		// TODO Auto-generated method stub
		return chargeItemMapperDao.updateByPrimaryKeySelective(record);
	}
	
	/**
	 * @author qwc
	 * 2017年10月24日下午1:51:18
	 * @param record
	 * @return 删除收费项目
	 */
	@Override
	public int delete(ChargeItem record) {
		// TODO Auto-generated method stub
		return chargeItemMapperDao.delete(record);
	}
	
	/**
	 * @author qwc
	 * 2017年10月24日下午3:38:43
	 * @param record
	 * @return 检测收费项目名是否在使用
	 */
	@Override
	public int checkChargeItemNameIsOnUse(ChargeItem record) {
		// TODO Auto-generated method stub
		return chargeItemMapperDao.checkChargeItemNameIsOnUse(record);
	}
	
	/**
	 * @author qwc
	 * 2017年10月24日下午3:53:46
	 * @param record 
	 * @return 检测收费项目打印次数是否存在
	 */
	@Override
	public int checkChargeItemNamePrintOrderIsOnUse(ChargeItem record) {
		// TODO Auto-generated method stub
		return chargeItemMapperDao.checkChargeItemNamePrintOrderIsOnUse(record);
	}
	
	/**
	 * @author qwc
	 * 2017年11月1日下午4:38:40
	 * @param record
	 * @return 检测收费项目打印次数是否存在（用于收费项目更新时）
	 */
	@Override
	public int checkChargeItemNamePrintOrderIsOnUseOnUpdate(ChargeItem record) {
		// TODO Auto-generated method stub
		return chargeItemMapperDao.checkChargeItemNamePrintOrderIsOnUseOnUpdate(record);
	}
	
}
