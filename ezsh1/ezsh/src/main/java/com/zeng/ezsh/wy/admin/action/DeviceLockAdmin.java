package com.zeng.ezsh.wy.admin.action;

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
import com.zeng.ezsh.wy.entity.DeviceLock;
import com.zeng.ezsh.wy.service.DeviceLockService;
import com.zeng.ezsh.wy.utils.RetJsonUtil;

/**
 * 
 * @author quanweicong 后台门锁设备 action
 */
@Controller
@RequestMapping(value = "deviceLockA")
public class DeviceLockAdmin {
	@Resource
	DeviceLockService deviceLockService;

	/**
	 * @author qwc 2017年12月17日下午4:59:46
	 * @param request
	 * @param response
	 * @param record
	 * @throws IOException
	 * void 添加门锁设备
	 */
	@RequestMapping(value = "add")
	public void addDeviceLock(HttpServletRequest request,
			HttpServletResponse response, DeviceLock record) throws IOException {
		PrintWriter out = response.getWriter();
		RetJsonUtil retJsonUtil = new RetJsonUtil();

		int isExistRecord = deviceLockService.checkDeviceIsOn(record);
		if (isExistRecord > 0) {
			retJsonUtil.setStatus("2");
			retJsonUtil.setMessage("不能重复添加");
			out.write(retJsonUtil.getRetJsonM());
			return;
		}
		int status = deviceLockService.insert(record);
		if (status > 0) {
			retJsonUtil.setStatus("1");
			retJsonUtil.setMessage("添加成功");
		} else {
			retJsonUtil.setStatus("0");
			retJsonUtil.setMessage("添加失败");
		}
		out.write(retJsonUtil.getRetJsonM());
	}

	/**
	 * @author qwc 2017年12月17日下午4:59:22
	 * @param request
	 * @param response
	 * @param record
	 * @param startPage
	 * @param pageSize
	 * @throws IOException
	 *  void 获取门锁设备列表集合
	 */
	@RequestMapping(value = "select")
	public void selectDeviceLockList(
			HttpServletRequest request,
			HttpServletResponse response,
			DeviceLock record,
			@RequestParam(value = "page", required = true, defaultValue = "1") Integer startPage,
			@RequestParam(value = "rows", required = false, defaultValue = "10") Integer pageSize)
			throws IOException {
		PrintWriter out = response.getWriter();
		RetJsonUtil retJsonUtil = new RetJsonUtil();// 创建json返回对象
		
		if(record.getPtManagerId() ==null){
			retJsonUtil.setTotal(0);
			out.write(retJsonUtil.getRetJsonEasyGrid());
			return;
		}
		
		// 分页获取
		PageHelper.startPage(startPage, pageSize);
		List<DeviceLock> list = deviceLockService
				.selectListDeviceByParamA(record);
		PageInfo<DeviceLock> page = new PageInfo<DeviceLock>(list);

		// 返回结果
		retJsonUtil.setList(list);
		retJsonUtil.setTotal(page.getTotal());
		out.write(retJsonUtil.getRetJsonEasyGrid());
	}
	
	/**
	 * @author qwc
	 * 2017年12月24日上午10:51:45
	 * @param request
	 * @param response
	 * @param record
	 * @throws IOE xception 
	 * void 删除设备楼宇锁
	 */
	@RequestMapping(value = "delete")
	public void deleteDeviceLock(HttpServletRequest request,
			HttpServletResponse response, DeviceLock record) throws IOException {
		PrintWriter out = response.getWriter();
		RetJsonUtil retJsonUtil = new RetJsonUtil();

		int status = deviceLockService.deleteByPrimaryKey(record);
		if (status > 0) {
			retJsonUtil.setStatus("1");
			retJsonUtil.setMessage("删除成功");
		} else {
			retJsonUtil.setStatus("0");
			retJsonUtil.setMessage("删除失败");
		}
		out.write(retJsonUtil.getRetJsonM());
	}
	
	/**
	 * @author qwc
	 * 2017年12月24日上午10:50:09
	 * @param request
	 * @param response
	 * @param record
	 * @throws IOException 
	 * void 更新设备楼宇锁
	 */
	@RequestMapping(value = "update")
	public void updateDeviceLock(HttpServletRequest request,
			HttpServletResponse response, DeviceLock record) throws IOException {
		PrintWriter out = response.getWriter();
		RetJsonUtil retJsonUtil = new RetJsonUtil();
		
		record.setDevicePass(null);
		int status = deviceLockService.updateByPrimaryKeySelective(record);
		
		if (status > 0) {
			retJsonUtil.setStatus("1");
			retJsonUtil.setMessage("更新成功");
		} else {
			retJsonUtil.setStatus("0");
			retJsonUtil.setMessage("更新失败");
		}
		out.write(retJsonUtil.getRetJsonM());
	}
	
	/**
	 * @author qwc
	 * 2017年12月24日上午10:50:09
	 * @param request
	 * @param response
	 * @param record
	 * @throws IOException 
	 * void 更新设备楼宇锁设备密码
	 */
	@RequestMapping(value = "updatePass")
	public void updateDeviceLockPass(HttpServletRequest request,
			HttpServletResponse response, DeviceLock record) throws IOException {
		PrintWriter out = response.getWriter();
		RetJsonUtil retJsonUtil = new RetJsonUtil();
		
		int status = deviceLockService.updateByPrimaryKeySelective(record);
		
		if (status > 0) {
			retJsonUtil.setStatus("1");
			retJsonUtil.setMessage("更新成功");
		} else {
			retJsonUtil.setStatus("0");
			retJsonUtil.setMessage("更新失败");
		}
		out.write(retJsonUtil.getRetJsonM());
	}
}
