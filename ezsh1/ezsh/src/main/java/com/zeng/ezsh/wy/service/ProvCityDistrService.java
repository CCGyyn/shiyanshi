package com.zeng.ezsh.wy.service;

import java.util.List;

import com.zeng.ezsh.wy.entity.City;
import com.zeng.ezsh.wy.entity.District;
import com.zeng.ezsh.wy.entity.Province;

public interface ProvCityDistrService {
	/*获取省列表*/
	List<Province> getProvinceList();
	
	/*获取城市列表*/
	List<City> getCityList(int provinceId);
	
	/*获取区列表*/
	List<District> getDistrictList(int cityId);
	
	/*通过城市名称获取城市ID*/
	public City getCityIdByCityName(String cityName);
	
	/*通过省份名称获取省份ID*/
	public Province getProvinceIdByProvinceName(String provinceName);
}
