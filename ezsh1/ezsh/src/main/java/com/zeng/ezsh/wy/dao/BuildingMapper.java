package com.zeng.ezsh.wy.dao;

import java.util.List;
import java.util.Map;

import com.zeng.ezsh.wy.entity.Building;

public interface BuildingMapper extends BaseMapper<Building>{

	public   List<Building> findAll();
	
	public   int findIdByName(String buildName);
   
	/*条件查询楼栋（移动端）*/
	List<Building> findBuildingByParam(Map<String, Object> param);
	
	/*条件查询楼栋给(后台)*/
	List<Building> findBuildingByParamToAdmin(Map<String, Object> param);
	
	/*通过楼栋ID获取楼栋信息*/
	Building getBuildingInfoByParam(int buildId);
	
	/*检测楼宇名称是否存在*/
	int checkNameHasOn(Building building);
}