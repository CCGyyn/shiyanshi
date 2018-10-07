package com.zeng.ezsh.wy.dao;

import com.zeng.ezsh.wy.entity.City;

import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface CityMapper {
    /*获取城市列表*/
	List<City> getCityList(int provinceId);
	
	/*通过城市名称获取城市ID*/
	City getCityIdByCityName(String cityName);
	
	/*通过城市ID获取城市名称*/
	public String getCityNameById(int cityId);

}