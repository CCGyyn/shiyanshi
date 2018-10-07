package com.zeng.ezsh.wy.dao;

import com.zeng.ezsh.wy.entity.Province;

import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface ProvinceMapper {
	/*获取省列表*/
	public List<Province> getProvinceList();
	
	/*通过省份名称获取省份ID*/
	public Province getProvinceIdByProvinceName(String provinceName);
	
	/*通过省份ID获取省份名称*/
	public String getProvinceNameById(int provinceId);

}