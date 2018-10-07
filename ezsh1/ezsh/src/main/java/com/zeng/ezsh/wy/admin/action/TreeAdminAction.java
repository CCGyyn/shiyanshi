package com.zeng.ezsh.wy.admin.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import net.sf.json.JSONArray;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zeng.ezsh.wy.entity.Building;
import com.zeng.ezsh.wy.entity.Management;
import com.zeng.ezsh.wy.entity.Room;
import com.zeng.ezsh.wy.service.BuildingService;
import com.zeng.ezsh.wy.service.ManagementService;
import com.zeng.ezsh.wy.service.RoomService;
import com.zeng.ezsh.wy.utils.EasyTreeUtil;

@Controller
@RequestMapping(value = "tree")
public class TreeAdminAction {
	@Resource
	private ManagementService managementService;
	@Resource
	private BuildingService buildingService;
	@Resource
	private RoomService roomService;

	/**
	 * @author qwc 2017年10月1日下午8:29:47
	 * @param id
	 * @return String 获取后台房间树（【2-管理处ID-楼宇ID-房间ID】）
	 */
	@RequestMapping(value = "gtTree", produces = "application/json;charset=utf-8")
	@ResponseBody
	public String getTree(
			@RequestParam(value = "id", required = false) String id) {
		List<EasyTreeUtil> treeList = new ArrayList<EasyTreeUtil>();
		if (id != null && id.indexOf("-") > 0) {
			int select = Integer.parseInt(id.substring(0, id.indexOf("-")));
			String managerId = null;
			String buildId = null;
			System.out.println("id>" + id.substring(id.indexOf("-") + 1));
			if (select == 0) {// 获取的是楼宇
				Map<String, Object> param = new HashMap<String, Object>();
				Building build = new Building();
				managerId = id.substring(id.indexOf("-") + 1);
				param.put("buildManagerId", managerId);
				List<Building> list = buildingService
						.findBuildingByParamToAdmin(param);
				System.out.println(JSONArray.fromObject(list).toString());
				for (Building buildInfo : list) {
					EasyTreeUtil tree = new EasyTreeUtil();
					tree.setId("1-" + managerId + "-" + buildInfo.getBuildId());// （树的深度为1）设置ID的形式为【1-管理处ID-楼宇ID】
					tree.setText(buildInfo.getBuildName());
					tree.setChecked(false);
					tree.setState("closed");
					treeList.add(tree);
				}
			}

			if (select == 1) {// 获取的是房间
				String swap = null;
				Map<String, Object> param = new HashMap<String, Object>();
				Room room = new Room();
				swap = id.substring(id.indexOf("-") + 1);
				managerId = swap.substring(0, swap.indexOf("-"));// 从ID中获取出管理处ID
				swap = swap.substring(swap.indexOf("-") + 1);
				buildId = swap;
				room.setBuildId(Integer.parseInt(buildId));// 设置楼宇ID为查询条件
				List<Room> list = roomService.findRoomCustomer(room);
				System.out.println(JSONArray.fromObject(list).toString());
				for (Room roomInfo : list) {
					EasyTreeUtil tree = new EasyTreeUtil();
					tree.setId("2-" + managerId + "-" + buildId + "-"
							+ roomInfo.getRoomId());// 设置ID的形式为【2-管理处ID-楼宇ID-房间ID】
					tree.setText(roomInfo.getRoomNum() + "|"
							+ roomInfo.getCustomerInfo().getCustomerName());// 节点text的形式【房间号+客户名称】
					tree.setChecked(false);
					tree.setState("closed");
					treeList.add(tree);
				}
			}
			return JSONArray.fromObject(treeList).toString();
		}

		List<Management> list = managementService.findAll();
		System.out.println(JSONArray.fromObject(list).toString());
		for (Management managerInfo : list) {
			EasyTreeUtil tree = new EasyTreeUtil();
			tree.setId("0-" + managerInfo.getManagerId());// 设置ID的形式为【0-管理处ID】
			tree.setText(managerInfo.getManagerName());
			tree.setChecked(false);
			tree.setState("closed");
			treeList.add(tree);
		}
		return JSONArray.fromObject(treeList).toString();
	}

	/**
	 * @author qwc 2017年11月14日下午8:38:56
	 * @param id
	 * @return 获取后台（房间用户）树（ID的形式为【2-管理处ID-楼宇ID-房间ID-用户ID】） String
	 */
	@RequestMapping(value = "gtTreeUserId", produces = "application/json;charset=utf-8")
	@ResponseBody
	public String getTreeUserId(
			@RequestParam(value = "id", required = false) String id) {
		List<EasyTreeUtil> treeList = new ArrayList<EasyTreeUtil>();
		if (id != null && id.indexOf("-") > 0) {
			int select = Integer.parseInt(id.substring(0, id.indexOf("-")));
			String managerId = null;
			String buildId = null;
			System.out.println("id>" + id.substring(id.indexOf("-") + 1));
			if (select == 0) {// 获取的是楼宇
				Map<String, Object> param = new HashMap<String, Object>();
				Building build = new Building();
				managerId = id.substring(id.indexOf("-") + 1);
				param.put("buildManagerId", managerId);
				List<Building> list = buildingService
						.findBuildingByParamToAdmin(param);
				System.out.println(JSONArray.fromObject(list).toString());
				for (Building buildInfo : list) {
					EasyTreeUtil tree = new EasyTreeUtil();
					tree.setId("1-" + managerId + "-" + buildInfo.getBuildId());// （树的深度为1）设置ID的形式为【1-管理处ID-楼宇ID】
					tree.setText(buildInfo.getBuildName());
					tree.setChecked(false);
					tree.setState("closed");
					treeList.add(tree);
				}
			}

			if (select == 1) {// 获取的是房间
				String swap = null;
				Map<String, Object> param = new HashMap<String, Object>();
				Room room = new Room();
				swap = id.substring(id.indexOf("-") + 1);
				managerId = swap.substring(0, swap.indexOf("-"));// 从ID中获取出管理处ID
				swap = swap.substring(swap.indexOf("-") + 1);
				buildId = swap;
				room.setBuildId(Integer.parseInt(buildId));// 设置楼宇ID为查询条件
				List<Room> list = roomService.findRoomCustomer(room);
				System.out.println(JSONArray.fromObject(list).toString());
				for (Room roomInfo : list) {
					EasyTreeUtil tree = new EasyTreeUtil();
					tree.setId("2-" + managerId + "-" + buildId + "-"
							+ roomInfo.getRoomId() + "-"
							+ roomInfo.getPtUserId());// 设置ID的形式为【2-管理处ID-楼宇ID-房间ID-用户ID】
					tree.setText(roomInfo.getRoomNum() + "|"
							+ roomInfo.getCustomerInfo().getCustomerName());// 节点text的形式【房间号+客户名称】
					tree.setChecked(false);
					tree.setState("closed");
					treeList.add(tree);
				}
			}
			return JSONArray.fromObject(treeList).toString();
		}

		List<Management> list = managementService.findAll();
		System.out.println(JSONArray.fromObject(list).toString());
		for (Management managerInfo : list) {
			EasyTreeUtil tree = new EasyTreeUtil();
			tree.setId("0-" + managerInfo.getManagerId());// 设置ID的形式为【0-管理处ID】
			tree.setText(managerInfo.getManagerName());
			tree.setChecked(false);
			tree.setState("closed");
			treeList.add(tree);
		}
		return JSONArray.fromObject(treeList).toString();
	}
	
	/**
	 * @author qwc 2017年11月14日下午8:38:56
	 * @param id
	 * @return 获取后台楼宇树（ID的形式为【2-管理处ID-楼宇ID】） String
	 */
	@RequestMapping(value = "gtTreeBuild", produces = "application/json;charset=utf-8")
	@ResponseBody
	public String getTreeBuild(
			@RequestParam(value = "id", required = false) String id) {
		List<EasyTreeUtil> treeList = new ArrayList<EasyTreeUtil>();
		if (id != null && id.indexOf("-") > 0) {
			int select = Integer.parseInt(id.substring(0, id.indexOf("-")));
			String managerId = null;
			String buildId = null;
			System.out.println("id>" + id.substring(id.indexOf("-") + 1));
			if (select == 0) {// 获取的是楼宇
				Map<String, Object> param = new HashMap<String, Object>();
				Building build = new Building();
				managerId = id.substring(id.indexOf("-") + 1);
				param.put("buildManagerId", managerId);
				List<Building> list = buildingService
						.findBuildingByParamToAdmin(param);
				System.out.println(JSONArray.fromObject(list).toString());
				for (Building buildInfo : list) {
					EasyTreeUtil tree = new EasyTreeUtil();
					tree.setId("1-" + managerId + "-" + buildInfo.getBuildId());// （树的深度为1）设置ID的形式为【1-管理处ID-楼宇ID】
					tree.setText(buildInfo.getBuildName());
					tree.setChecked(false);
					tree.setState("closed");
					treeList.add(tree);
				}
			}
			return JSONArray.fromObject(treeList).toString();
		}

		List<Management> list = managementService.findAll();
		System.out.println(JSONArray.fromObject(list).toString());
		for (Management managerInfo : list) {
			EasyTreeUtil tree = new EasyTreeUtil();
			tree.setId("0-" + managerInfo.getManagerId());// 设置ID的形式为【0-管理处ID】
			tree.setText(managerInfo.getManagerName());
			tree.setChecked(false);
			tree.setState("closed");
			treeList.add(tree);
		}
		return JSONArray.fromObject(treeList).toString();
	}

	
	/**
	 * @author qwc 2017年11月14日下午8:44:49
	 * @param id
	 * @return 获取管理处树
	 */
	@RequestMapping(value = "gtTreeManager", produces = "application/json;charset=utf-8")
	@ResponseBody
	public String getTreeOnlyManager(
			@RequestParam(value = "id", required = false) String id) {
		List<EasyTreeUtil> treeList = new ArrayList<EasyTreeUtil>();
		if (StringUtils.isNotBlank(id)) {
			return JSONArray.fromObject(treeList).toString();
		}

		List<Management> list = managementService.findAll();
		System.out.println(JSONArray.fromObject(list).toString());
		for (Management managerInfo : list) {
			EasyTreeUtil tree = new EasyTreeUtil();
			tree.setId("0-" + managerInfo.getManagerId());// 设置ID的形式为【0-管理处ID】
			tree.setText(managerInfo.getManagerName());
			tree.setChecked(false);
			tree.setState("closed");
			treeList.add(tree);
		}
		return JSONArray.fromObject(treeList).toString();
	}
}
