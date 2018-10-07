package com.zeng.ezsh.wy.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zeng.ezsh.wy.entity.Building;
import com.zeng.ezsh.wy.service.BuildingService;
/**
 * @description 楼宇操作类
 *
 * @author qwc
 */
@Controller
@RequestMapping("buildingT")
public class BuildingMAction {
	@Resource
	BuildingService buildingService;
	
	/**
	 * @author qwc
	 * 2017年8月23日下午9:36:33 void
	 * 获取楼栋给移动端
	 * @throws IOException 
	 */
	@RequestMapping("gtBuilding")
	public void getBuildingList(HttpServletRequest request,HttpServletResponse response,
			@RequestParam(value="startPage",required=true,defaultValue="1") Integer startPage,@RequestParam(value="pageSize",required=false,defaultValue="10") Integer pageSize) throws IOException{
		PrintWriter out=response.getWriter();
		Map<String, Object> tokenMap = new HashMap<String, Object>();
		Map<String, Object> paramMap = new HashMap<String, Object>();//查询条件
		Map<String, Object> retMap = new HashMap<String, Object>();
		JSONObject retObject=new JSONObject();
		
		String buildManagerId=request.getParameter("buildManagerId");
		String buildName=request.getParameter("buildName");
		paramMap.put("buildManagerId", buildManagerId);
		paramMap.put("buildName", buildName);
		PageHelper.startPage(startPage, pageSize);
		List<Building> buiList=buildingService.findBuildingByParam(paramMap);
		PageInfo<Building> page=new PageInfo<Building>(buiList);
		
		retMap.put("totalPage", page.getPages());
		retMap.put("buildList", buiList);
		retObject.put("status", 1);
		retObject.put("message", "获取成功");
		retObject.put("data", retMap);
		out.write((JSONObject.fromObject(retObject)).toString());
	}
}
