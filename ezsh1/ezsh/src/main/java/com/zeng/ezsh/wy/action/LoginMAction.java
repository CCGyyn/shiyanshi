package com.zeng.ezsh.wy.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.zeng.ezsh.wy.entity.DeviceLock;
import com.zeng.ezsh.wy.entity.Management;
import com.zeng.ezsh.wy.entity.ResidentialUser;
import com.zeng.ezsh.wy.entity.UMsIds;
import com.zeng.ezsh.wy.service.DeviceLockService;
import com.zeng.ezsh.wy.service.ResidentialUserService;
import com.zeng.ezsh.wy.service.UMsIdsService;
import com.zeng.ezsh.wy.utils.AccessTokenUtil;
import com.zeng.ezsh.wy.utils.RetJsonUtil;
import com.zeng.ezsh.wy.utils.StringMd5Util;
/**
 * @description 移动端登录操作类
 *
 * @author qwc
 */
@Controller
@RequestMapping("andrLg")
public class LoginMAction {
	private static Logger logger = Logger.getLogger(LoginMAction.class);
	@Resource
	ResidentialUserService residentialUserServiceToAndr;
	@Resource
	UMsIdsService uMsIdsService;
	@Resource
	DeviceLockService deviceLockService;

	/**
	 * @description
	 *
	 * @auhtor qwc 2017年7月26日下午3:34:45
	 * @param residentialUserModel
	 * @param session
	 * @param request
	 * @param response
	 * @throws IOException void 用户登录,移动端
	 */
	@RequestMapping(value = "lg")
	public void userLogin(ResidentialUser residentialUserModel,
			HttpSession session, HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		PrintWriter out = response.getWriter();
		UMsIds uMsIdsInfo = new UMsIds();
		String telephone = request.getParameter("telephone");// 手机号
		String password = request.getParameter("password");// 密码
		String accessToken = null;
		int userId = 0;// 用户ID
		int managerId = 0;// 用户管理处ID
		int pBuildId = 0;
		int pRoomId = 0;
		int identifyClassify = 0;
		int checkStatus = 0;
		Map<String, Object> mtMap = new HashMap<String, Object>();
		Map<String, Object> userLgInfoMap = new HashMap<String, Object>();
		RetJsonUtil retJson = new RetJsonUtil();
		password = StringMd5Util.MD5Digest(password);
		residentialUserModel.setUserAccount(telephone);
		residentialUserModel.setUserPassword(password);

		int amount = residentialUserServiceToAndr.checkAccountIsIn(telephone);
		if (amount == 0) {
			retJson.setStatus("-1");
			retJson.setMessage("账户不存在");
			retJson.setRetMap(userLgInfoMap);

		} else {
			residentialUserModel = residentialUserServiceToAndr
					.getUserLoginInfo(residentialUserModel);
			if (residentialUserModel == null) {// 或账户密码错误
				retJson.setStatus("0");
				retJson.setMessage("账户密码错误");
				retJson.setRetMap(userLgInfoMap);

			} else {
				
				
				if (!residentialUserModel.getUroomList().isEmpty()) {
					// 用户房间列表
					uMsIdsInfo = residentialUserModel.getUroomList().get(0);
					managerId = uMsIdsInfo.getpManagerId();
					pBuildId = uMsIdsInfo.getpBuildId();
					pRoomId = uMsIdsInfo.getpRoomId();
					identifyClassify = uMsIdsInfo.getIdentifyClassify();
					checkStatus = uMsIdsInfo.getCheckStatus();
				}

				userId = residentialUserModel.getUserId();
				residentialUserModel.setUroomList(null);
				residentialUserModel.setUmsIdsInfo(uMsIdsInfo);
				mtMap.put("userId", userId);
				mtMap.put("managerId", managerId);
				mtMap.put("pBuildId", pBuildId);
				mtMap.put("pRoomId", pRoomId);
				mtMap.put("identifyClassify", identifyClassify);
				mtMap.put("checkStatus", checkStatus);
				mtMap.put("residentialUser", residentialUserModel);
				
				List<DeviceLock> lockList = new ArrayList<DeviceLock>();
				lockList = deviceLockService.selectListDeviceByParamA(residentialUserModel);
				mtMap.put("lockList", lockList);
				
				accessToken = AccessTokenUtil.getAccessToken(mtMap, 3600);// token有效期为一天
				logger.info("accessToken>>>:" + accessToken);

				// 设置成功登录返回的json
				if (accessToken != null) {
					userLgInfoMap.put("userLgInfo", residentialUserModel);
					userLgInfoMap.put("accessToken", accessToken);
					userLgInfoMap.put("lgManager", managerId);
					userLgInfoMap.put("uMsId", uMsIdsInfo.getuMsId());
					userLgInfoMap.put("lockList", lockList);
					retJson.setStatus("1");
					retJson.setMessage("登录成功");
					retJson.setRetMap(userLgInfoMap);

				} else {
					retJson.setStatus("-3");
					retJson.setMessage("登录失败");
					retJson.setRetMap(userLgInfoMap);
				}
			}
		}
		out.write(retJson.getRetJsonM());
	}

	/**
	 * @description 账号切换
	 *
	 * @param uIds
	 * @param response
	 * @param request
	 * @throws IOException void
	 */
	@RequestMapping("switchAccount")
	public void switchAccount(UMsIds uIds, HttpServletResponse response,
			HttpServletRequest request) throws IOException {
		PrintWriter out = response.getWriter();
		String accessToken = request.getParameter("token");
		Map<String, Object> tokenMap = new HashMap<String, Object>();
		Map<String, Object> mtMap = new HashMap<String, Object>();
		Map<String, Object> userLgInfoMap = new HashMap<String, Object>();
		RetJsonUtil retJson = new RetJsonUtil();
		ResidentialUser record = new ResidentialUser();
		tokenMap = AccessTokenUtil.parserAccessTokenToMap(accessToken);
		int userId = (int) tokenMap.get("userId");
		record.setUserId(userId);
		record = residentialUserServiceToAndr.getUserLoginInfoByUserId(record);
		List<DeviceLock> lockList = new ArrayList<DeviceLock>();
		
		if (!record.getUroomList().isEmpty()) {
			List<UMsIds> list = record.getUroomList();
			for (UMsIds uMsIds : list) {
				if (uIds.getuMsId().equals(uMsIds.getuMsId())) {
					uIds = uMsIds;
					break;
				}

			}
			record.setUmsIdsInfo(uIds);
			record.setUroomList(null);
			mtMap.put("userId", record.getUserId());
			mtMap.put("managerId", uIds.getpManagerId());
			mtMap.put("pBuildId", uIds.getpBuildId());
			mtMap.put("pRoomId", uIds.getpRoomId());
			mtMap.put("identifyClassify", uIds.getIdentifyClassify());
			mtMap.put("checkStatus", uIds.getCheckStatus());
			
			lockList = deviceLockService.selectListDeviceByParamA(record);
			mtMap.put("lockList", lockList);
			userLgInfoMap.put("lockList", lockList);
			accessToken = AccessTokenUtil.getAccessToken(mtMap, 3600);// token有效期为一天
		}
		
		if (accessToken != null) {// 设置成功登录返回的json
			userLgInfoMap.put("userLgInfo", record);
			userLgInfoMap.put("accessToken", accessToken);
			userLgInfoMap.put("lgManager", uIds.getpManagerId());
			userLgInfoMap.put("uMsId", uIds.getuMsId());
			
			retJson.setStatus("1");
			retJson.setMessage("切换成功");
			retJson.setRetMap(userLgInfoMap);

		} else {
			retJson.setStatus("0");
			retJson.setMessage("切换失败");
			retJson.setRetMap(userLgInfoMap);
		}
		out.write(retJson.getRetJsonM());
	}

	/**
	 * @author qwc 2017年10月23日下午1:45:33
	 * @param request
	 * @param response void 获取账号列表
	 * @throws IOException
	 */
	@RequestMapping("gtAccountList")
	public void getAccountList(UMsIds uIds, HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		PrintWriter out = response.getWriter();
		RetJsonUtil retJsonUtil = new RetJsonUtil();

		String accessToken = request.getParameter("token");
		Map<String, Object> tokenMap = new HashMap<String, Object>();
		tokenMap = AccessTokenUtil.parserAccessTokenToMap(accessToken);

		int userId = (int) tokenMap.get("userId");
		uIds.setpUserId(userId);
		List<UMsIds> list = uMsIdsService.selectAccountList(uIds);

		// 返回结果
		retJsonUtil.setMessage("获取成功");
		retJsonUtil.setList(list);
		retJsonUtil.setStatus("1");
		out.write(retJsonUtil.getRetJsonL());
	}
 
	/*@RequestMapping("test")
	public void Test(UMsIds uIds, HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		PrintWriter out = response.getWriter();
		RetJsonUtil retJsonUtil = new RetJsonUtil();

		String accessToken = request.getParameter("token");
		ResidentialUser tokenModel = new ResidentialUser();
		tokenModel = AccessTokenUtil.parserAccessTokenToModel(accessToken);

		// 检测该账号下是否有小区通过审核
		if (tokenModel.getUmsIdsInfo() == null) {
			retJsonUtil.setStatus("-1");
			retJsonUtil.setMessage("此账号未有小区通过审核");
			out.write(retJsonUtil.getRetJsonM());
			return;
		}

		int userId = tokenModel.getUserId();
		uIds.setpUserId(userId);
		List<UMsIds> list = uMsIdsService.selectAccountList(uIds);
		List<Integer> list1 = new ArrayList<>();
		Iterator<UMsIds> it = list.iterator();
		while (it.hasNext()) {
			UMsIds uMsIds = new UMsIds();
			uMsIds = it.next();
			list1.add(uMsIds.getpBuildId());
		}

		Map<String, Object> param = new HashMap<String, Object>();
		param.put("ptManagerId", tokenModel.getUmsIdsInfo().getpManagerId());
		param.put("deviceList", list1);
		List<DeviceLock> deviceLockList = deviceLockService
				.selectListDeviceByParamA(param);
		out.write(JSONArray.fromObject(deviceLockList).toString());
	}*/

	/**
	 * @author qwc 2017年8月2日下午10:02:16 void 通过手机号去获取小区集合
	 * @throws IOException
	 */
	@RequestMapping("gtManage")
	public void getCommunityByTelephone(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		PrintWriter out = response.getWriter();
		String telephone = request.getParameter("telephone");
		List<Integer> manageIdsList = new ArrayList<Integer>();// 管理处ID集合
		manageIdsList = residentialUserServiceToAndr.getManageIds(telephone);
		Integer[] manageIds = null;
		for (int i = 0; i < manageIdsList.size(); i++) {
			System.out.print("i>>>>" + manageIdsList.get(i));
			manageIds[i] = manageIdsList.get(i);
		}

		System.out.println(JSONArray.fromObject(manageIdsList));
		List<Management> listManage = residentialUserServiceToAndr
				.getManagementListByIds(manageIdsList);
		out.write(JSONArray.fromObject(listManage).toString());
	}

}
