package com.zeng.ezsh.wy.admin.action;

import javax.annotation.Resource;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zeng.ezsh.wy.action.BaseAction;
import com.zeng.ezsh.wy.entity.Management;
import com.zeng.ezsh.wy.entity.Page;
import com.zeng.ezsh.wy.service.BuildingService;
import com.zeng.ezsh.wy.service.ManagementService;
import com.zeng.ezsh.wy.service.RoomService;
/**
 * 
 * 管理处模块
 *
 */
@Controller
@RequestMapping("/management")
public class ManagementAction extends BaseAction {
	private Logger logger = Logger.getLogger(ManagementAction.class);
	@Autowired
	private ManagementService managementService;
	@Resource
	private BuildingService buildingService;
	@Resource
	private RoomService roomService;

	/**
	 * 2017年9月16日下午9:06:06
	 * 
	 * @param management
	 * @return Object 添加楼宇
	 */
	@RequestMapping(value = "/insert")
	@ResponseBody
	public Object insert(Management management) {
		int addStatus = 0;
		int checkName = managementService.checkNameHasOn(management);
		// 名称有重复
		if (checkName > 0) {
			return 2;
		}
		try {
			// 执行添加操作
			addStatus = managementService.insert(management);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return addStatus;
	}

	/**
	 * 2017年9月16日下午9:06:25
	 * 
	 * @param management
	 * @return Object 更新楼宇
	 */
	@RequestMapping(value = "/update")
	@ResponseBody
	public Object update(Management management) {
		int updateStatus = 0;
		try {
			// 执行更新操作
			updateStatus = managementService.update(management);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return updateStatus;
	}

	/**
	 * 2017年9月16日下午9:07:40
	 * 
	 * @param pks
	 * @return Object 删除楼宇
	 */
	@RequestMapping("/deleteList")
	@ResponseBody
	public Object deleteList(String[] pks) {
		int deleteStatus = 0;
		try {
			deleteStatus = managementService.deleteList(pks);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return deleteStatus;
	}

	/**
	 * 2017年10月31日下午3:47:00
	 * 
	 * @param page 分页对象
	 * @param management
	 * @return Object
	 */
	@RequestMapping("/selectPage")
	@ResponseBody
	public Object selectPage(Page<Management> page, Management management) {
		page.setParamEntity(management);
		Page<Management> p = managementService.selectPageUseDyc(page);
		return p.getPageMap();
	}

	/**
	 * 2017年9月16日下午9:08:02
	 * 
	 * @param conditions
	 * @param keyword
	 * @return Object 条件查询楼宇
	 */
	@RequestMapping("/setConditions")
	@ResponseBody
	public Object setConditions(String conditions, String keyword) {
		Management management = new Management();
		/*
		 * if(conditions.equals("managerId") &&
		 * StringUtils.isNotBlank(keyword)){
		 * management.setManagerId(Integer.parseInt(keyword)); }
		 */
		if (conditions.equals("managerName") && StringUtils.isNotBlank(keyword)) {
			management.setManagerName(keyword);
		}
		if (conditions.equals("managerAddr") && StringUtils.isNotBlank(keyword)) {
			management.setManagerAddr(keyword);
		}
		if (conditions.equals("head") && StringUtils.isNotBlank(keyword)) {
			management.setHead(keyword);
		}
		if (conditions.equals("contact") && StringUtils.isNotBlank(keyword)) {
			management.setContact(keyword);
		}
		if (conditions.equals("contactNum") && StringUtils.isNotBlank(keyword)) {
			management.setContactNum(keyword);
		}
		return management;
	}

	/**
	 * @author qwc 2017年9月27日下午4:23:29
	 * @return String 获取管理处树
	 */
	/*
	 * @RequestMapping(value="gtManagerTree", produces =
	 * "application/json;charset=utf-8")
	 * 
	 * @ResponseBody public String
	 * getRoomCustomer(@RequestParam(value="id",required=false) String id){
	 * List<EasyTreeUtil> treeList = new ArrayList<EasyTreeUtil>(); if (id !=
	 * null&&id.indexOf("-") > 0) { int select =
	 * Integer.parseInt(id.substring(0,id.indexOf("-")));
	 * System.out.println("id>"+id.substring(id.indexOf("-")+1)); if (select ==
	 * 0) { Map<String, Object> param = new HashMap<String, Object>(); Building
	 * build = new Building(); param.put("buildManagerId",
	 * id.substring(id.indexOf("-") + 1)); List<Building> list =
	 * buildingService.findBuildingByParamToAdmin(param);
	 * 
	 * for (Building buildInfo : list) { EasyTreeUtil tree = new EasyTreeUtil();
	 * tree.setId("1-"+buildInfo.getBuildId());
	 * tree.setText(buildInfo.getBuildName()); tree.setChecked(false);
	 * tree.setState("closed"); treeList.add(tree); } } if (select == 1) {
	 * Map<String, Object> param = new HashMap<String, Object>(); Room room =
	 * new Room(); room.setBuildId(Integer.parseInt(id.substring(id.indexOf("-")
	 * + 1))); List<Room> list = roomService.findRoomCustomer(room);
	 * 
	 * for (Room roomInfo : list) { EasyTreeUtil tree = new EasyTreeUtil();
	 * tree.setId("2-" + roomInfo.getRoomId());
	 * tree.setText(roomInfo.getRoomNum() + "|" +
	 * roomInfo.getCustomerInfo().getCustomerName()); tree.setChecked(false);
	 * tree.setState("closed"); treeList.add(tree); } } return
	 * JSONArray.fromObject(treeList).toString(); }
	 * 
	 * List<Management> list = managementService.findAll();
	 * 
	 * for (Management managerInfo : list) { EasyTreeUtil tree = new
	 * EasyTreeUtil(); tree.setId("0-" + managerInfo.getManagerId());
	 * tree.setText(managerInfo.getManagerName()); tree.setChecked(false);
	 * tree.setState("closed"); treeList.add(tree); } return
	 * JSONArray.fromObject(treeList).toString(); }
	 */
}
