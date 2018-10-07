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
import com.zeng.ezsh.wy.entity.Room;
import com.zeng.ezsh.wy.service.RoomService;
import com.zeng.ezsh.wy.utils.RetJsonUtil;
@Controller
@RequestMapping("roomT")
public class RoomMAction {
	@Resource
	RoomService roomService;

	/**
	 * @description 获取房间号给移动端
	 *
	 * @auhtor qwc 2017年8月23日下午9:36:33
	 * @param request
	 * @param response
	 * @param startPage
	 * @param pageSize
	 * @throws IOException void
	 */
	@RequestMapping("gtRoom")
	public void getBuildingList(HttpServletRequest request,
			HttpServletResponse response,
			@RequestParam(value = "startPage", required = true, defaultValue = "1") Integer startPage,
			@RequestParam(value = "pageSize", required = false, defaultValue = "10") Integer pageSize)
			throws IOException {
		PrintWriter out = response.getWriter();
		RetJsonUtil retJsonUtil = new RetJsonUtil();

		String buildId = request.getParameter("buildId");
		String roomNum = request.getParameter("roomNum");
		String managerId = request.getParameter("buildManagerId");

		Map<String, Object> paramMap = new HashMap<String, Object>();// 查询条件
		paramMap.put("buildId", buildId);
		paramMap.put("buildName", roomNum);
		paramMap.put("managerId", managerId);
		PageHelper.startPage(startPage, pageSize);
		roomService.findRoomByParam(paramMap);
		List<Room> roomList = roomService.findRoomByParam(paramMap);
		PageInfo<Room> page = new PageInfo<Room>(roomList);

		// 封装返回数据
		Map<String, Object> retMap = new HashMap<String, Object>();
		retMap.put("totalPage", page.getPages());
		retMap.put("roomList", roomList);
		retJsonUtil.setStatus("1");
		retJsonUtil.setMessage("获取成功");
		retJsonUtil.setRetMap(retMap);
		out.write(retJsonUtil.getRetJsonM());
	}
}
