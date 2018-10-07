package com.zeng.ezsh.wy.service;

import java.util.List;
import java.util.Map;

import com.zeng.ezsh.wy.entity.Management;

public interface ManagementService extends BaseService<Management> {
	public List<Management> findAll();
	
	/**
    * @author qwc
    * 2017年8月5日下午3:47:21
    * @return List<Management>
    * 省市区级联获取小区集合
    */
	public List<Management> getManagerListByPCityDistrId(Map<String, Object> paramMap);
	
	/**
	 * @author qwc
	 * 2017年8月12日下午9:41:44
	 * @param parmaMap
	 * @return List<Management>
	 * 通过省份名称/城市名称/小区名 来搜索小区
	 */
	public List<Management> getManagerListByPCName(Map<String, Object> parmaMap);
	
	/**
	 * @author qwc
	 * 2017年9月20日下午12:09:41
	 * @return List<Management>
	 * 查询小区集合用于构建树
	 */
	public List<Management> findAllManagement(Map<String, Object> parmaMap);
	
	/*检测管理处名称是否存在*/
	public int checkNameHasOn(Management record);
}
