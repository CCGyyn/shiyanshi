package com.zeng.ezsh.wy.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.zeng.ezsh.wy.entity.Building;
import com.zeng.ezsh.wy.service.BuildingService;
@Service("BuildingService")
public class BuildingServiceImpl extends BaseServiceImpl<Building> implements BuildingService  {

	@Override
	public List<Building> findAll() {
		// TODO Auto-generated method stub
		return buildingMapper.findAll();
	}

	@Override
	public List<Building> selectAll(Building building) {
		// TODO Auto-generated method stub
		return buildingMapper.selectAll(building);
	}
	
	/**
	 * @author qwc
	 * 2017年8月23日下午9:28:12
	 * @param param
	 * @return
	 * 条件查询楼栋给移动端
	 */
	@Override
	public List<Building> findBuildingByParam(Map<String, Object> param) {
		// TODO Auto-generated method stub
		return buildingMapper.findBuildingByParam(param);
	}
	
	/**
	 * @author qwc
	 * 2017年9月17日下午5:18:07
	 * @param param
	 * @return
	 * 条件查询楼栋给后台
	 */
	@Override
	public List<Building> findBuildingByParamToAdmin(Map<String, Object> param) {
		// TODO Auto-generated method stub
		return buildingMapper.findBuildingByParamToAdmin(param);
	}
	
	/**
	 * @author qwc
	 * 2017年12月20日下午10:19:41
	 * @param building
	 * @return 检测楼宇名称是否存在
	 */
	@Override
	public int checkNameHasOn(Building building) {
		// TODO Auto-generated method stub
		return buildingMapper.checkNameHasOn(building);
	}


}
