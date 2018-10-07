package com.zeng.ezsh.wy.admin.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zeng.ezsh.wy.action.BaseAction;
import com.zeng.ezsh.wy.entity.Building;
import com.zeng.ezsh.wy.entity.Management;
import com.zeng.ezsh.wy.entity.Page;
import com.zeng.ezsh.wy.service.BuildingService;
import com.zeng.ezsh.wy.service.ManagementService;
import com.zeng.ezsh.wy.utils.EasyUITree;

/**
 * @description  楼宇操作类后台
 *
 * @author qwc
 */
@Controller
@RequestMapping("/build")
public class BuildingAction extends BaseAction {
	@Autowired
	private BuildingService buildingService;
	@Autowired
	private ManagementService managementService;

	/**
	 * @author qwc 2017年10月31日下午5:32:28
	 * @return 获取所有管理处列表 （楼宇管理界面形成管理处下拉列表）
	 */
	@RequestMapping("/findManager")
	@ResponseBody
	public Object findManager() {
		List<Management> list = managementService.findAll();
		return list;
	}

	/**
	 * 2017年10月31日下午5:31:32
	 * 
	 * @param building
	 * @return 添加楼宇 Object
	 */
	@RequestMapping(value = "/insert")
	@ResponseBody
	public Object insert(Building building) {
		int addStatus = 0;
		int checkName = buildingService.checkNameHasOn(building);
		if (checkName > 0) {
			return 2;
		}
		try {
			addStatus = buildingService.insert(building);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return addStatus;
	}

	/**
	 * 2017年10月31日下午5:30:38
	 * 
	 * @param building
	 * @return 更新楼宇 Object
	 */
	@RequestMapping(value = "/update")
	@ResponseBody
	public Object update(Building building) {
		int updateStatus = 0;
		try {
			updateStatus = buildingService.update(building);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return updateStatus;
	}

	/**
	 * 2017年10月31日下午5:29:52
	 * 
	 * @param pks
	 * @return 删除楼宇 Object
	 */
	@RequestMapping("/deleteList")
	@ResponseBody
	public Object deleteList(String[] pks) {
		int deleteStatus = 0;
		try {
			deleteStatus = buildingService.deleteList(pks);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return deleteStatus;
	}

	/**
	 * @author qwc 2017年10月31日下午4:48:48
	 * @param page
	 * @param building
	 * @return 获取楼宇列表 Object
	 */
	@RequestMapping("/selectPage")
	@ResponseBody
	public Object selectPage(Page<Building> page, Building building) {
		page.setParamEntity(building);
		Page<Building> pageUseDyc = buildingService.selectPageUseDyc(page);
		return pageUseDyc.getPageMap();
	}

	/**
	 * @author qwc 2017年10月31日下午4:49:42
	 * @param conditions
	 * @param keyword
	 * @param managerId
	 * @return 设置查询条件 Object
	 */
	@RequestMapping("/setConditions")
	@ResponseBody
	public Object setConditions(String conditions, String keyword,
			String managerId) {
		Building building = new Building();
		/*
		 * if(conditions.equals("buildId")&&StringUtils.isNotBlank(keyword)){
		 * building.setBuildId(Integer.parseInt(keyword)); }
		 */
		if (conditions.equals("buildName") && StringUtils.isNotBlank(keyword)) {
			building.setBuildName(keyword);
		}
		if (conditions.equals("buildAddr") && StringUtils.isNotBlank(keyword)) {
			building.setBuildAddr(keyword);
		}
		if (conditions.equals("buildType") && StringUtils.isNotBlank(keyword)) {
			building.setBuildType(keyword);
		}
		if (conditions.equals("remark") && StringUtils.isNotBlank(keyword)) {
			building.setRemark(keyword);
		}
		return building;
	}

	/**
	 * @author qwc 2017年9月26日下午5:47:46
	 * @param build
	 * @param request
	 * @param response
	 * @throws IOException
	 *             void 通过管理处ID获取楼宇列表
	 */
	@RequestMapping("gtBuildListById")
	public void getBuildListByManagerId(Building build,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		PrintWriter out = response.getWriter();
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("buildManagerId", build.getBuildManagerId());
		List<Building> list = buildingService.findBuildingByParamToAdmin(param);
		out.write(JSONArray.fromObject(list).toString());
	}

	/**
	 * @author qwc 2017年9月27日下午2:44:18
	 * @param room
	 *            void 获取楼宇树
	 * @throws IOException
	 */
	@RequestMapping("gtBuildTree")
	public void getBuildTree(Building build, HttpServletResponse response)
			throws IOException {
		PrintWriter out = response.getWriter();
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("buildManagerId", build.getBuildManagerId());
		List<Building> list = buildingService.findBuildingByParamToAdmin(param);
		System.out.println(JSONArray.fromObject(list).toString());
		List<EasyUITree> uiTreeList = new ArrayList<EasyUITree>();
		for (Building buildInfo : list) {
			EasyUITree tree = new EasyUITree();
			tree.setId(buildInfo.getBuildId());
			tree.setText(buildInfo.getBuildName());
			tree.setChecked(false);
			tree.setState("closed");
			uiTreeList.add(tree);
		}
		out.write(JSONArray.fromObject(uiTreeList).toString());
	}
}
