package com.zeng.ezsh.wy.dao;


import java.util.List;
import java.util.Map;

import com.zeng.ezsh.wy.entity.Management;

public interface ManagementMapper extends BaseMapper<Management> {
	
   public List<Management> findAll();
   
   public int findIdByName(String managerName);
   
   /**
    * @author qwc
    * 2017年8月5日下午3:47:21
    * @return List<Management>
    * 省市区级联获取小区集合（后台）
    */
   List<Management> getManagerListByPCityDistrId(Map<String, Object> parmaMap);
   
   /**
    * @author qwc
    * 2017年8月12日下午9:37:39
    * @param parmaMap
    * @return List<Management>
    * 通过省份名称/城市名称/小区名 来搜索小区（移动端）
    */
   List<Management> getManagerListByPCName(Map<String, Object> parmaMap);
   
   /*通过管理处ID获取单个管理处信息（后台）*/
   Management getManagerByParam(int managerId);
   
   /*查询小区集合用于构建树（后台）*/
   List<Management> findAllManagement(Map<String, Object> parmaMap);
   
   /*检查管理处名称是否存在*/
   int checkNameHasOn(Management record);
}