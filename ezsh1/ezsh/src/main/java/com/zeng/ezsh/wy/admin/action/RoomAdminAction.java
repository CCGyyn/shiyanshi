package com.zeng.ezsh.wy.admin.action;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import com.zeng.ezsh.wy.action.BaseAction;
import com.zeng.ezsh.wy.entity.Building;
import com.zeng.ezsh.wy.entity.Management;
import com.zeng.ezsh.wy.entity.Page;
import com.zeng.ezsh.wy.entity.Room;
import com.zeng.ezsh.wy.service.BuildingService;
import com.zeng.ezsh.wy.service.ManagementService;
import com.zeng.ezsh.wy.service.RoomService;
import com.zeng.ezsh.wy.utils.EasyUITree;

/**
 * 后台房间管理
 */
@Controller
@RequestMapping("/room")
public class RoomAdminAction extends BaseAction {
	@Autowired
	private RoomService roomService;
	@Autowired
	private ManagementService managementService;
	@Autowired
	private BuildingService buildingService;

	/**
	 * 获取房间列表集合(后台)
	 * 
	 * @param page
	 * @param room
	 * @return Object
	 */
	@RequestMapping("/selectPage")
	@ResponseBody
	public Object selectPage(Page<Room> page, Room room) {
		page.setParamEntity(room);
		Page<Room> pageUseDyc = roomService.selectPageUseDyc(page);
		return pageUseDyc.getPageMap();
	}

	@RequestMapping("/findBuild")
	@ResponseBody
	public Object findbuild(String managerId) {
		Building building = new Building();
		building.setBuildManagerId(Integer.parseInt(managerId));
		List<Building> bulids = buildingService.selectAll(building);
		return bulids;
	}

	/**
	 * @param room
	 * @return 添加房间 Object
	 */
	@RequestMapping("/insert")
	@ResponseBody
	public Object insert(Room room) {
		int checkName = roomService.checkNameHasOn(room);
		if (checkName > 0) {
			return 2;
		}
		int i = 0;
		try {
			i = roomService.insert(room);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return i;
	}

	/**
	 * 删除房间
	 * 
	 * @param pks
	 * @return Object
	 */
	@RequestMapping("/deleteList")
	@ResponseBody
	public Object deleteList(String[] pks) {
		int i = 0;
		try {
			i = roomService.deleteList(pks);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return i;
	}

	/**
	 * @author qwc 2017年12月21日下午8:21:13
	 * @param room
	 * @return 更新房间信息 Object
	 */
	@RequestMapping("/update")
	@ResponseBody
	public Object update(Room room) {
		int i = 0;
		try {
			i = roomService.update(room);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return i;
	}

	/**
	 * 按条件查询房间信息
	 * 
	 * @param keyword
	 * @param conditions
	 * @param room
	 * @return Object
	 */
	@RequestMapping("/setConditions")
	@ResponseBody
	public Object setConditions(String keyword, String conditions, Room room) {
		/*
		 * if (conditions.equals("roomId") && StringUtils.isNoneBlank(keyword))
		 * { room.setRoomId(Integer.parseInt(keyword)); } else
		 */if (conditions.equals("buildArea")
				&& StringUtils.isNoneBlank(keyword)) {
			room.setBuildArea(Double.parseDouble(keyword));
		} else if (conditions.equals("roomFloor")
				&& StringUtils.isNoneBlank(keyword)) {
			room.setRoomFloor(Integer.parseInt(keyword));
		} else if (conditions.equals("roomNum")
				&& StringUtils.isNoneBlank(keyword)) {
			room.setRoomNum(keyword);
		} else if (conditions.equals("chargeMan")
				&& StringUtils.isNoneBlank(keyword)) {
			room.setChargeMan(keyword);
		} else if (conditions.equals("roomUse")
				&& StringUtils.isNoneBlank(keyword)) {
			room.setRoomUse(keyword);
		} else if (conditions.equals("position")
				&& StringUtils.isNoneBlank(keyword)) {
			room.setPosition(keyword);
		} else if (conditions.equals("toward")
				&& StringUtils.isNoneBlank(keyword)) {
			room.setToward(keyword);
		} else if (conditions.equals("rent")
				&& StringUtils.isNoneBlank(keyword)) {
			room.setRent(Double.parseDouble(keyword));
		} else if (conditions.equals("managementFee")
				&& StringUtils.isNoneBlank(keyword)) {
			room.setManagementFee(Double.parseDouble(keyword));
		} else if (conditions.equals("sumPrice")
				&& StringUtils.isNoneBlank(keyword)) {
			room.setSumPrice(Double.parseDouble(keyword));
		} else if (conditions.equals("singlePrice")
				&& StringUtils.isNoneBlank(keyword)) {
			room.setSinglePrice(Double.parseDouble(keyword));
		}
		return room;
	}

	/**
	 * 楼宇树
	 */
	@RequestMapping("/getBuildTree")
	@ResponseBody
	public Object getBuildTree() {
		List<EasyUITree> result = new ArrayList<EasyUITree>();
		List<Building> bulids = buildingService.findAll();
		List<Management> managers = managementService.findAll();
		for (Management management : managers) {
			EasyUITree easyUITree = new EasyUITree();
			easyUITree.setText(management.getManagerName());
			easyUITree.setChecked(true);
			easyUITree.setState("closed");
			List<EasyUITree> childs = new ArrayList<EasyUITree>();
			for (Building build : bulids) {
				if (management.getManagerId() == build.getBuildManagerId()) {
					EasyUITree child = new EasyUITree();
					child.setId(build.getBuildId());
					child.setText(build.getBuildName());
					child.setChecked(false);
					childs.add(child);
					easyUITree.setChildren(childs);
				}
			}
			result.add(easyUITree);
		}
		return result;
	}

	/**
	 * @author qwc 2017年9月27日下午1:43:55 void 获取房间以及客户集合
	 */
	@RequestMapping(value = "gtRoomCust", produces = "application/json;charset=utf-8")
	@ResponseBody
	public String getRoomCustomer(Room room) {
		List<Room> list = roomService.findRoomCustomer(room);
		System.out.println(JSONArray.fromObject(list).toString());
		List<EasyUITree> uiTreeList = new ArrayList<EasyUITree>();
		for (Room roomInfo : list) {
			EasyUITree tree = new EasyUITree();
			tree.setId(roomInfo.getRoomId());
			tree.setText(roomInfo.getRoomNum() + "|"
					+ roomInfo.getCustomerInfo().getCustomerName());
			tree.setChecked(false);
			uiTreeList.add(tree);
		}
		return JSONArray.fromObject(uiTreeList).toString();
	}

	/**
	 * 导出房间信息列表
	 * 
	 * @param response
	 *            void
	 */
	@RequestMapping("/export")
	@ResponseBody
	public void export(HttpServletResponse response) {
		try {
			response.setContentType("application/vnd.ms-excel");
			response.setHeader("content-disposition", "attachment;filename="
					+ new String("房间档案.xls".getBytes(), "ISO8859-1"));
			ServletOutputStream outputStream = response.getOutputStream();
			List<Room> list = roomService.findAll();
			System.out.println("JSONArray.fromObject(list).toString()>>"
					+ JSONArray.fromObject(list).toString());
			roomService.exportExcel(outputStream, list);
			if (outputStream != null) {
				outputStream.close();
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@RequestMapping("/importExcel")
	@ResponseBody
	public Object importExcel(HttpServletResponse response,
			HttpServletRequest request) {
		// 创建一个通用的多部分解析器
		CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(
				request.getSession().getServletContext());
		InputStream inExcelFile = null;
		String excelName = null;
		// 判断 request 是否有文件上传,即多部分请求importDailyResult
		if (multipartResolver.isMultipart(request)) {
			// 转换成多部分request
			MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;

			// 取得request中的所有文件名
			Iterator<String> iter = multiRequest.getFileNames();
			while (iter.hasNext()) {
				// 记录上传过程起始时的时间，用来计算上传时间
				int pre = (int) System.currentTimeMillis();
				// 取得上传文件
				MultipartFile file = multiRequest.getFile(iter.next());
				excelName = file.getOriginalFilename();
				try {
					inExcelFile = file.getInputStream();
				} catch (IOException e) {
					e.printStackTrace();
				}

			}

		}
		int i = 0;
		if (excelName != null) {
			if (excelName.matches("^.+\\.(?i)((xls)|(xlsx))$")) {
				i = roomService.importExcel(excelName, inExcelFile);
			}
		}
		return i;
	}
}
