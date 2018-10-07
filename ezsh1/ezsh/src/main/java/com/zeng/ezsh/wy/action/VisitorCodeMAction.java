package com.zeng.ezsh.wy.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.record.Record;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zeng.ezsh.opendoor.config.OpenDoorConfig;
import com.zeng.ezsh.wy.dao.VisitorCodeMapper;
import com.zeng.ezsh.wy.entity.DeviceLock;
import com.zeng.ezsh.wy.entity.ResidentialUser;
import com.zeng.ezsh.wy.entity.Room;
import com.zeng.ezsh.wy.entity.UMsIds;
import com.zeng.ezsh.wy.entity.VisitorCode;
import com.zeng.ezsh.wy.open.door.utils.OpenDoorPojo;
import com.zeng.ezsh.wy.service.DeviceLockService;
import com.zeng.ezsh.wy.service.RoomService;
import com.zeng.ezsh.wy.service.UMsIdsService;
import com.zeng.ezsh.wy.service.VisitorService;
import com.zeng.ezsh.wy.utils.AccessTokenUtil;
import com.zeng.ezsh.wy.utils.DateUtil;
import com.zeng.ezsh.wy.utils.HttpUrlConnectionUtil;
import com.zeng.ezsh.wy.utils.RetJsonUtil;
import com.zeng.ezsh.wy.utils.SerializeUtil;

/**
 * @description 访客功能操作
 *
 * @author qwc
 */
@Controller
@RequestMapping("visitor")
public class VisitorCodeMAction {
	@Resource
	VisitorService visitorCodeService;
	@Resource
	UMsIdsService uMsIdsService;
	@Resource
	DeviceLockService deviceLockService;
	@Resource
	RoomService roomService;
	@Resource
	VisitorCodeMapper visitorCodeMapper;

	/**
	 * @description 设置访客通行码
	 *
	 * @auhtor qwc 2017年10月20日下午7:59:18
	 * @param visitorCode
	 * @param request
	 * @param response
	 * @throws IOException void
	 */
	@RequestMapping("setVisitPermitCode")
	public void setVisitorTrafficPermit(VisitorCode visitorCode,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		PrintWriter out = response.getWriter();
		RetJsonUtil retJsonUtil = new RetJsonUtil();// 创建返回json对象

		// token解析
		String accessToken = request.getParameter("token");
		ResidentialUser tokenModel = AccessTokenUtil
				.parserAccessTokenToModel(accessToken);
		// 检测该账号下是否有小区通过审核
		if (tokenModel.getUmsIdsInfo() == null) {
			retJsonUtil.setStatus("-1");
			retJsonUtil.setMessage("此账号未有小区通过审核");
			out.write(retJsonUtil.getRetJsonM());
			return;
		}
		UMsIds uMsIdsInfo = tokenModel.getUmsIdsInfo();

		// 设置准备保存到数据库visitor_code表的记录的属性值
		int uMsId = uMsIdsInfo.getuMsId();
		// 设置业主的 uMsId
		uMsIdsInfo.setuMsId(uMsId);
		// 重新获取uMsIdsInfo
		// uMsIdsInfo = uMsIdsService.gtUserUmsInfoById(uMsIdsInfo);
		// 设置业主ID
		visitorCode.setPtUserId(uMsIdsInfo.getpUserId());
		// 设置管理处ID
		visitorCode.setPtManagerId(uMsIdsInfo.getpManagerId());
		// 设置管理处名称
		if (uMsIdsInfo.getManagerInfo() != null) {
			visitorCode.setManagerName(
					uMsIdsInfo.getManagerInfo().getManagerName());
		}
		// 设置楼宇ID
		visitorCode.setPtBuildId(uMsIdsInfo.getpBuildId());
		if (uMsIdsInfo.getBuildInfo() != null) { // 楼宇名称
			visitorCode.setBuildName(uMsIdsInfo.getBuildInfo().getBuildName());
		}
		// 设置房间ID
		visitorCode.setPtRoomId(uMsIdsInfo.getpRoomId());
		// 设置房间号
		if (uMsIdsInfo.getRoomInfo() != null) {
			visitorCode.setRoomNum(uMsIdsInfo.getRoomInfo().getRoomNum());
		}
		// visitorRecord.setVisitorPassword(); // 访客同行证（六位随机码组成）

		SimpleDateFormat dateFormat = new SimpleDateFormat(
				"yyyy-MM-dd HH:mm:ss"); // 设置日期格式
		SimpleDateFormat dateFormat1 = new SimpleDateFormat("yyyyMMddHH");
		long startTime = System.currentTimeMillis(); // 系统当前时间

		visitorCode.setVisitorValidityTimeStart(dateFormat.format(startTime)); // （有效期）访问码起始时间
		visitorCode.setVisitorValidityTimeEnd(dateFormat1.format(startTime)); // （有效期）访问码有效期

		// 检测是否有安装门锁
		DeviceLock lock = new DeviceLock();
		lock.setPtManagerId(uMsIdsInfo.getpManagerId());
		lock.setPtBuildId(uMsIdsInfo.getpBuildId());
		lock = deviceLockService.selectDeviceByParam(lock);

		if (lock == null) {
			retJsonUtil.setStatus("-2");
			retJsonUtil.setObject(lock);
			retJsonUtil.setMessage("该楼宇还未装门锁");
			out.write(retJsonUtil.getRetJsonO());
			return;
		}

		// 检测门锁是否在线
		int online = deviceLockService
				.checkDeviceOnLineStatus(lock.getDeviceId());
		if (online == 0) { // 不在线
			System.out.println("不在线");
			retJsonUtil.setStatus("-3");
			retJsonUtil.setMessage("门锁处于不在线状态");
			out.write(retJsonUtil.getRetJsonO());
			return;
		}

		// 查询是否在有效期内重复设置访问码
		VisitorCode record = visitorCodeService.checkByParam(visitorCode);
		if (record != null) { // 还在有效期，不能重设
			retJsonUtil.setStatus("0");
			retJsonUtil.setObject(record);
			retJsonUtil.setMessage("有效期内不能重设");
		} else {
			int status = visitorCodeService.insert(visitorCode,
					lock.getDeviceId(), "12345678");
			if (status == 1) {
				retJsonUtil.setStatus("1");
				retJsonUtil.setObject(record);
				retJsonUtil.setMessage("设置成功");
			} else if (status == 2) {
				retJsonUtil.setStatus("2");
				retJsonUtil.setObject(record);
				retJsonUtil.setMessage("门锁处于不在线状态");
			} else {
				retJsonUtil.setStatus("-1");
				retJsonUtil.setObject(record);
				retJsonUtil.setMessage("设置失败");
			}
		}
		out.write(retJsonUtil.getRetJsonO());
	}

	/**
	 * @description 获取业主个人设置的访客同行证列表
	 *
	 * @auhtor qwc 2017年10月21日下午10:54:24
	 * @param record
	 * @param request
	 * @param response
	 * @param startPage
	 * @param pageSize
	 * @throws IOException void
	 */
	@RequestMapping("gtPermitCodeList")
	public void getPermitCodeList(VisitorCode record,
			HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value = "startPage", required = true, defaultValue = "1") Integer startPage,
			@RequestParam(value = "pageSize", required = false, defaultValue = "10") Integer pageSize)
			throws IOException {
		PrintWriter out = response.getWriter();
		RetJsonUtil retJsonUtil = new RetJsonUtil();

		String accessToken = request.getParameter("token");// 获取登录token
		Map<String, Object> tokenMap = new HashMap<String, Object>();
		tokenMap = AccessTokenUtil.parserAccessTokenToMap(accessToken);// token解析

		// 设置查询条件
		int userId = (int) tokenMap.get("userId");
		record.setPtUserId(userId);

		// 执行分页获取
		PageHelper.startPage(startPage, pageSize);
		List<VisitorCode> list = visitorCodeService
				.selectCodeListByParam(record);
		PageInfo<VisitorCode> pageInfo = new PageInfo<VisitorCode>(list);

		// 设置返回结果
		retJsonUtil.setMessage("获取成功");
		Map<String, Object> dataMap = new HashMap<String, Object>(); // 保存返回字段data的数据
		dataMap.put("totalPage", pageInfo.getLastPage());
		dataMap.put("codeList", pageInfo.getList());
		retJsonUtil.setRetMap(dataMap);
		retJsonUtil.setStatus("1");
		out.write(retJsonUtil.getRetJsonM());
	}

	/**
	 * @description 获取某栋楼宇的访客同行码
	 *
	 * @auhtor qwc 2018年3月5日 上午11:25:00
	 * @param request
	 * @param response
	 * @param record
	 * @param lock
	 * @param ptRoomId
	 * @throws IOException void
	 */
	@RequestMapping("getPermitCode")
	public void getPermitCodeByParam(HttpServletRequest request,
			HttpServletResponse response, VisitorCode record, DeviceLock lock,
			@RequestParam(value = "", required = true, defaultValue = "0") Integer ptRoomId)
			throws IOException {
		PrintWriter out = response.getWriter();
		RetJsonUtil retJsonUtil = new RetJsonUtil();

		// 获取登录token
		String accessToken = request.getParameter("token");
		// token解析
		ResidentialUser residentialUser = AccessTokenUtil
				.parserAccessTokenToModel(accessToken);

		record.setPtUserId(residentialUser.getUserId()); // 设置用户ID
		SimpleDateFormat dateFormat = new SimpleDateFormat(
				"yyyy-MM-dd HH:mm:ss"); // 设置日期格式
		SimpleDateFormat dateFormat1 = new SimpleDateFormat("yyyyMMddHH");// 设置访问码的有效期(用于请求到第三方设置访客码的接口,不是存到数据库字段的形式)
		long startTime = System.currentTimeMillis(); // 系统当前时间
		record.setVisitorValidityTimeStart(dateFormat.format(startTime)); // （有效期）访问码起始时间
		record.setVisitorValidityTimeEnd(dateFormat1.format(startTime)); // （有效期）访问码有效期
		VisitorCode visitorCode = visitorCodeService.selectCodeByParam(record);

		if (visitorCode == null) { // 如果还没有设置访客码
			Long reLong = visitorCodeMapper.selectMaxId(); // 获取访客记录自增ID的最大值
			record.setVisitorPassword(SerializeUtil.getSixNum(reLong));
			int status = visitorCodeService.insert(record, lock.getDeviceId(),
					"12345678");
			if (status == 1) {
				retJsonUtil.setStatus("1");
				retJsonUtil.setObject(record);
				retJsonUtil.setMessage("设置成功");
			} else if (status == 2) {
				retJsonUtil.setStatus("2");
				retJsonUtil.setObject(visitorCode);
				retJsonUtil.setMessage("门锁处于不在线状态");
			} else {
				retJsonUtil.setStatus("-1");
				retJsonUtil.setObject(visitorCode);
				retJsonUtil.setMessage("设置失败");
			}
		} else {
			retJsonUtil.setStatus("1");
			retJsonUtil.setObject(visitorCode);
			retJsonUtil.setMessage("获取成功");
		}
		out.write(retJsonUtil.getRetJsonO());
	}

	/**
	 * @author qwc 2017年10月21日下午4:09:07 void 检测设备是否在线（用于测试）
	 */
	/* @Test */
	@RequestMapping("checkIsOnline")
	public void checkDeviceOnLineStatus(HttpServletRequest request) {
		/*
		 * RetJsonUtil retJsonUtil = new RetJsonUtil(); Map<String, Object>
		 * param = new HashMap<String, Object>(); String deviceId =
		 * request.getParameter("deviceId"); param.put("DEVICE_ID", deviceId);//
		 * 设置请求参数 System.out.println("deviceId"+deviceId ); JSONObject
		 * jsonObject = JSONObject.fromObject(HttpUrlConnectionUtil
		 * .urlPost(OpenDoorConfig.CheckDeviceOnLineStatusUrl, param));
		 * OpenDoorPojo checkDeviceOnLineStatusPojo = (OpenDoorPojo) JSONObject
		 * .toBean(jsonObject, OpenDoorPojo.class);// 将获取到的json转化成实体类 int
		 * lastIndex = checkDeviceOnLineStatusPojo.getMsg().lastIndexOf(":"); //
		 * 获取设备在线状态 0-在线 1-不在线 int status =
		 * Integer.parseInt(checkDeviceOnLineStatusPojo.getMsg()
		 * .substring(lastIndex + 1).trim()); if (status > 0) {
		 * retJsonUtil.setStatus("1"); retJsonUtil.setMessage("在线");
		 * System.out.println("锁的在线状态：>>在线"); } else {
		 * retJsonUtil.setStatus("0"); retJsonUtil.setMessage("不在线"); }
		 */
		/*
		 * Map<String, Object> param = new HashMap<String, Object>();
		 * param.put("visitorPassword", "766930");
		 * param.put("visitorValidityTimeEnd", DateUtil.dateToStr(new Date(),
		 * DateUtil.DATE_TIME_LINE)); VisitorCode recordCode =
		 * visitorCodeMapper.selectCodeByDeviceIdAndCode(param);
		 * System.out.println("recordCode>" +
		 * JSONObject.fromObject(recordCode).toString());
		 */
	}

}
