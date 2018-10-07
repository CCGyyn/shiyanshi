package com.zeng.ezsh.wy.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.zeng.ezsh.wy.dao.PrivilegeMapper;
import com.zeng.ezsh.wy.entity.Privilege;
import com.zeng.ezsh.wy.service.PrivilegeService;
@Service
public class PrivilegeServiceImpl implements PrivilegeService {
	private static Logger logger = Logger.getLogger("PrivilegeServiceImpl");  
	@Resource
	PrivilegeMapper privilegeDao;
	
	/**
	 * @author qwc
	 * 2017年7月19日下午4:23:49
	 * @param paramPrivMap
	 * @return
	 */
	@Override
	public JSONArray selectAllPrivList(Map<Object, Object> paramPrivMap) {
		// TODO Auto-generated method stub
		Map<String, Object> prevJsonMap=new HashMap<String, Object>();//所有权限的集合
		Map<Object, Object> paramPrivMapTwo=new HashMap<Object, Object>();
		List<String> secondaryPermissionsIDList=new ArrayList<String>();//保存二级权限的ID
		List<Privilege> privList=new ArrayList<Privilege>();//保存根据条件获取出来的权限集合
		List<Privilege> privListTwo=new ArrayList<Privilege>();//保存根据条件获取出来的权限集合
		List<Map<String, Object>> listMapTwo=new ArrayList<Map<String, Object>>();//保存重新构造后的权限树结构
		
		prevJsonMap.put("id", "p0");
		prevJsonMap.put("text", "权限");//设置权限下拉列表顶点，顶级
		
		paramPrivMap.put("privId", "p0");//设置查询权限的条件
		privList=privilegeDao.selectAllPrivList(paramPrivMap);//查询p_priv_id为p0的权限集合
		for(Privilege privilegeModel:privList){
			secondaryPermissionsIDList.add(privilegeModel.getPrivId());//设置二级权限的ID
			//listMapTwo.add(childJsonMap);//二级
		}
		logger.info("size:"+privList.size());//二级大小
		paramPrivMapTwo.put("secondaryPermissionsIDList", secondaryPermissionsIDList);
		privListTwo=privilegeDao.selectAllPrivList(paramPrivMapTwo);//[第三级权限集合]根据p_priv_id为p0权限集合，查询其下一级权限
		logger.info("privListTwo>>:"+JSONArray.fromObject(privList));
		for(Privilege privilegeTwoModel:privList){
			Map<String, Object> childJsonMap=new HashMap<String, Object>();
			List<Map<String, Object>> listMapThree=new ArrayList<Map<String, Object>>();//[三级]保存重新构造后的权限树结构
			for(Privilege privilegeThreeModelModel:privListTwo){
				Map<String, Object> childTwoJsonMap=new HashMap<String, Object>();
				if(privilegeTwoModel.getPrivId().equals(privilegeThreeModelModel.getpPrivId())){
					childTwoJsonMap.put("id", privilegeThreeModelModel.getPrivId());
					childTwoJsonMap.put("text", privilegeThreeModelModel.getOperation());
					listMapThree.add(childTwoJsonMap);//三级权限集合
				}
			}
			childJsonMap.put("id", privilegeTwoModel.getPrivId());
			childJsonMap.put("text", privilegeTwoModel.getOperation());
			childJsonMap.put("children", listMapThree);
			listMapTwo.add(childJsonMap);//二级
		}
		
		
		prevJsonMap.put("children", listMapTwo);
		JSONArray json = JSONArray.fromObject(prevJsonMap);
		logger.info("json>>:"+json.toString());
		return json;
	}

}
