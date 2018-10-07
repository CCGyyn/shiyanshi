package com.zeng.ezsh.wy.service;

import java.util.List;
import java.util.Map;

import com.zeng.ezsh.wy.entity.Building;

public interface BuildingService extends BaseService<Building> {

	public List<Building> findAll();
	
	public List<Building> selectAll(Building building);

	/*条件查询楼栋给移动端*/
	public List<Building> findBuildingByParam(Map<String, Object> param);
	
	/*条件查询楼栋给后台*/
	public List<Building> findBuildingByParamToAdmin(Map<String, Object> param);
	
	/*检测楼宇名称是否存在*/
	public int checkNameHasOn(Building building);
}
