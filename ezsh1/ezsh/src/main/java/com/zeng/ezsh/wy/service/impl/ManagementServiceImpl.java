package com.zeng.ezsh.wy.service.impl;


import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.zeng.ezsh.wy.entity.Management;
import com.zeng.ezsh.wy.service.ManagementService;
@Service
public class ManagementServiceImpl extends BaseServiceImpl<Management> implements ManagementService  {
	@Override
	public List<Management> findAll() {
		// TODO Auto-generated method stub
		managementMapper.findAll();
		return 	managementMapper.findAll();
	}
	
	/**
    * @author qwc
    * 2017年8月5日下午3:47:21
    * @return List<Management>
    * 省市区级联获取小区集合
    */
	@Override
	public List<Management> getManagerListByPCityDistrId(Map<String, Object> paramMap) {
		// TODO Auto-generated method stub
		return managementMapper.getManagerListByPCityDistrId(paramMap);
	}
	
	/**
	 * @author qwc
	 * 2017年8月12日下午9:43:59
	 * @param parmaMap
	 * @return
	 * 通过省份名称/城市名称/小区名 来搜索小区
	 */
	@Override
	public List<Management> getManagerListByPCName(Map<String, Object> parmaMap) {
		// TODO Auto-generated method stub
		return managementMapper.getManagerListByPCName(parmaMap);
	}
	
	/**
	 * @author qwc
	 * 2017年9月20日下午12:10:01
	 * @return
	 * 查询小区集合用于构建树
	 */
	@Override
	public List<Management> findAllManagement(Map<String, Object> parmaMap) {
		// TODO Auto-generated method stub
		return managementMapper.findAllManagement(parmaMap);
	}
	
	/**
	 * @author qwc
	 * 2017年12月20日下午10:49:53
	 * @param record
	 * @return 检测管理处名称是否存在
	 */
	@Override
	public int checkNameHasOn(Management record) {
		// TODO Auto-generated method stub
		return managementMapper.checkNameHasOn(record);
	}

}
