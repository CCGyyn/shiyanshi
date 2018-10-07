package com.zeng.ezsh.wy.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.zeng.ezsh.wy.dao.CityMapper;
import com.zeng.ezsh.wy.dao.DistrictMapper;
import com.zeng.ezsh.wy.dao.ProvinceMapper;
import com.zeng.ezsh.wy.entity.City;
import com.zeng.ezsh.wy.entity.District;
import com.zeng.ezsh.wy.entity.Province;
import com.zeng.ezsh.wy.service.ProvCityDistrService;
@Service
public class ProvCityDistrServiceImpl implements ProvCityDistrService {
	@Resource
	ProvinceMapper provinceMapperDao;
	@Resource
	CityMapper cityMapperDao;
	@Resource
	DistrictMapper districtMapperDao;
	
	/**
	 * @author qwc
	 * 2017年8月5日下午1:52:55
	 * @return
	 * 获取省列表
	 */
	@Override
	public List<Province> getProvinceList() {
		// TODO Auto-generated method stub
		return provinceMapperDao.getProvinceList();
	}
	
	/**
	 * @author qwc
	 * 2017年8月5日下午1:52:42
	 * @param provinceId
	 * @return
	 * 获取城市列表
	 */
	@Override
	public List<City> getCityList(int provinceId) {
		// TODO Auto-generated method stub
		return cityMapperDao.getCityList(provinceId);
	}
	
	/**
	 * @author qwc
	 * 2017年8月5日下午1:52:26
	 * @param cityId
	 * @return
	 * 获取区列表
	 */
	@Override
	public List<District> getDistrictList(int cityId) {
		// TODO Auto-generated method stub
		return districtMapperDao.getDistrictList(cityId);
	}
	
	/**
	 * @author qwc
	 * 2017年8月12日下午8:45:45
	 * @param cityName
	 * @return
	 * 通过城市名获取城市ID
	 */
	@Override
	public City getCityIdByCityName(String cityName) {
		// TODO Auto-generated method stub
		return cityMapperDao.getCityIdByCityName(cityName);
	}
	
	/**
	 * @author qwc
	 * 2017年8月12日下午8:45:51
	 * @param provinceName
	 * @return
	 * 通过省份名获取省份ID
	 */
	@Override
	public Province getProvinceIdByProvinceName(String provinceName) {
		// TODO Auto-generated method stub
		return provinceMapperDao.getProvinceIdByProvinceName(provinceName);
	}

}
