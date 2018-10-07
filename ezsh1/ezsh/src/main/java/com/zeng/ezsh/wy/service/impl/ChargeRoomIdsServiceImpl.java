package com.zeng.ezsh.wy.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.zeng.ezsh.wy.dao.ChargeRoomIdsMapper;
import com.zeng.ezsh.wy.entity.ChargeRoomIds;
import com.zeng.ezsh.wy.service.ChargeRoomIdsService;
@Service
public class ChargeRoomIdsServiceImpl implements ChargeRoomIdsService {
	@Resource
	/*添加房间收费项目*/
	ChargeRoomIdsMapper chargeRoomIdsMapperDao;
	/**
	 * @author qwc
	 * 2017年9月20日下午4:14:42
	 * @param record
	 * @return
	 * 添加房间收费项目
	 */
	@Override
	public int insert(ChargeRoomIds record) {
		// TODO Auto-generated method stub
		return chargeRoomIdsMapperDao.insert(record);
	}
	
	/**
	 * @author qwc
	 * 2017年9月20日下午7:59:05
	 * @param chargeRoomId
	 * @return
	 * 获取房间收费项目列表
	 */
	@Override
	public List<ChargeRoomIds> selectChargeItemsByParam(Map<String, Object> param) {
		// TODO Auto-generated method stub
		return chargeRoomIdsMapperDao.selectChargeItemsByParam(param);
	}
	
	/**
	 * @author qwc
	 * 2017年10月24日下午3:00:42
	 * @param chargeRoomId
	 * @return 删除房间收费项目
	 */
	@Override
	public int deleteByPrimaryKey(Integer chargeRoomId) {
		// TODO Auto-generated method stub
		return chargeRoomIdsMapperDao.deleteByPrimaryKey(chargeRoomId);
	}
	
	/**
	 * @author qwc
	 * 2017年10月24日下午3:00:20
	 * @param record
	 * @return 查询收费项目是否已被使用
	 */
	@Override
	public int checkChargeItemIsOnUse(ChargeRoomIds record) {
		// TODO Auto-generated method stub
		return chargeRoomIdsMapperDao.checkChargeItemIsOnUse(record);
	}

}
