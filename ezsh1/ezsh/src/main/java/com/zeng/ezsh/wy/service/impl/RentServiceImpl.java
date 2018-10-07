package com.zeng.ezsh.wy.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.zeng.ezsh.wy.dao.RentMapper;
import com.zeng.ezsh.wy.entity.Rent;
import com.zeng.ezsh.wy.service.RentService;
@Service
public class RentServiceImpl implements RentService {
	@Resource
	RentMapper rentMapperDao;
	/**
	 * @author qwc
	 * 2017年11月11日下午3:54:13
	 * @param record
	 * @return 添加出租记录
	 */
	@Override
	public int insertSelective(Rent record) {
		// TODO Auto-generated method stub
		return rentMapperDao.insertSelective(record);
	}
	
	/**
	 * @author qwc
	 * 2017年11月11日下午4:06:59
	 * @param record
	 * @return 获取出租记录集合
	 */
	@Override
	public List<Rent> selectRentList(Rent record) {
		// TODO Auto-generated method stub
		return rentMapperDao.selectRentList(record);
	}
	
	/**
	 * @author qwc
	 * 2017年11月11日下午9:32:13
	 * @param record
	 * @return 更新出租记录
	 */
	@Override
	public int updateRent(Rent record) {
		// TODO Auto-generated method stub
		return rentMapperDao.updateRent(record);
	}
	
	/**
	 * @author qwc
	 * 2017年11月11日下午9:32:39
	 * @param record
	 * @return 删除出租记录
	 */
	@Override
	public int delRent(Rent record) {
		// TODO Auto-generated method stub
		return rentMapperDao.delRent(record);
	}

}
