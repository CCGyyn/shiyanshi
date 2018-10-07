package com.zeng.ezsh.wy.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.zeng.ezsh.wy.entity.DeviceLock;
import com.zeng.ezsh.wy.entity.ResidentialUser;
import com.zeng.ezsh.wy.entity.UMsIds;
import com.zeng.ezsh.wy.service.DeviceLockService;
import com.zeng.ezsh.wy.service.UMsIdsService;
import com.zeng.ezsh.wy.service.VisitorService;
import com.zeng.ezsh.wy.utils.AccessTokenUtil;
import com.zeng.ezsh.wy.utils.RetJsonUtil;

/**
 * @description 开门操作类
 *
 * @author qwc
 */
@Controller
@RequestMapping(value = "doorM")
public class OpenDoorMAction {
	@Resource
	VisitorService visitorCodeService;
	@Resource
	DeviceLockService deviceLockService;
	@Resource
	UMsIdsService uMsIdsService;

	/**
	 * @author quanweicong 2017年12月14日下午10:39:53
	 * @param response
	 * @param request
	 * @param lock
	 * @param token
	 * @throws IOException
	 * @return void
	 * @description 业主通过手机远程开门 业主通过手机远程开门
	 */
	@RequestMapping("openDoor")
	public void openRemoteDoor(HttpServletResponse response,
			HttpServletRequest request, DeviceLock lock,
			@RequestParam(value = "token", required = true) String token)
			throws IOException {
		PrintWriter out = response.getWriter();
		RetJsonUtil retJsonUtil = new RetJsonUtil();

		// token解析
		ResidentialUser tokenModel = AccessTokenUtil
				.parserAccessTokenToModel(token);
		// 检测该账号下是否有小区通过审核
		if (tokenModel.getUmsIdsInfo() == null) {
			retJsonUtil.setStatus("-1");
			retJsonUtil.setMessage("此账号未有小区通过审核");
			out.write(retJsonUtil.getRetJsonM());
			return;
		}

		// 身份类型验证
		int identifyClassify = tokenModel.getUmsIdsInfo().getIdentifyClassify();
		if (identifyClassify != 1) {
			retJsonUtil.setStatus("0");
			retJsonUtil.setMessage("获取失败，非业主身份");
			out.write(retJsonUtil.getRetJsonM());
			return;
		}

		lock = deviceLockService.selectDeviceByDeviceId(lock);
		int status = visitorCodeService.remoteOpenDoor(lock);

		// 封装返回结果
		if (status == 1) {
			retJsonUtil.setStatus("1");
			retJsonUtil.setMessage("开门成功");
		} else if (status == 0) {
			retJsonUtil.setStatus("0");
			retJsonUtil.setMessage("开门失败");
		} else {
			retJsonUtil.setStatus("-1");
			retJsonUtil.setMessage("不在线");
		}
		out.write(retJsonUtil.getRetJsonM());
	}

	/**
	 * @author qwc 2017年12月14日下午10:39:53
	 * @param response
	 * @param request
	 * @param devicePsw
	 * @param token
	 * @throws IOException void 蓝牙开门前检测是否有开门权限
	 */
	@RequestMapping("checkPermission")
	public void checkHasPermission(HttpServletResponse response,
			HttpServletRequest request, DeviceLock lock,
			@RequestParam(value = "token", required = true) String token)
			throws IOException {
		PrintWriter out = response.getWriter();
		RetJsonUtil retJsonUtil = new RetJsonUtil();

		// token解析
		ResidentialUser tokenModel = AccessTokenUtil
				.parserAccessTokenToModel(token);

		lock = deviceLockService.selectDeviceByDeviceId(lock);
		UMsIds record = new UMsIds();
		record.setpBuildId(lock.getPtBuildId());
		record.setpUserId(tokenModel.getUserId());
		int check = uMsIdsService.checkHasOpenDoorPermission(record);

		// 封装返回结果
		if (check > 0) {
			retJsonUtil.setStatus("1");
			retJsonUtil.setMessage("有权开门");
		} else {
			retJsonUtil.setStatus("0");
			retJsonUtil.setMessage("无权开门");
		}
		out.write(retJsonUtil.getRetJsonM());
	}
}
