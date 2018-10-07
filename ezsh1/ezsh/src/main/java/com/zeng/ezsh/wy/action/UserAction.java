package com.zeng.ezsh.wy.action;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.annotations.Param;
import org.apache.log4j.Logger;
import org.springframework.context.support.StaticApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zeng.ezsh.wy.entity.Customer;
import com.zeng.ezsh.wy.entity.ResidentialUser;
import com.zeng.ezsh.wy.entity.Room;
import com.zeng.ezsh.wy.entity.UMsIds;
import com.zeng.ezsh.wy.service.CustomerService;
import com.zeng.ezsh.wy.service.ResidentialUserService;
import com.zeng.ezsh.wy.service.RoomService;
import com.zeng.ezsh.wy.service.UMsIdsService;
import com.zeng.ezsh.wy.utils.AccessTokenUtil;
import com.zeng.ezsh.wy.utils.DateUtil;
import com.zeng.ezsh.wy.utils.RetJsonUtil;
import com.zeng.ezsh.wy.utils.StringMd5Util;
import com.zeng.ezsh.wy.utils.UserUtills;

@Controller
@RequestMapping("ur")
public class UserAction {
	public static Logger logger = Logger.getLogger(UserAction.class);
	@Resource
	ResidentialUserService residentialUserService;
	@Resource
	UMsIdsService uMsIdsMapperService;
	@Resource
	CustomerService customerService;
	/**
	 * @description
	 *
	 * @auhtor qwc 2017年7月25日下午6:43:39
	 * @param residentialUserModel
	 * @param request
	 * @param response
	 * @param telephoneCode [短信验证码]，一个手机号一天最多能获取十条短信，超过则收不到短信
	 * @param accessToken
	 * @throws IOException void 用户注册
	 */
	@RequestMapping("rgUser")
	public void register(@ModelAttribute ResidentialUser residentialUserModel,
			HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value = "telephoneCode", required = true, defaultValue = "000000") String telephoneCode,
			@RequestParam(value = "token", required = true, defaultValue = "000000") String accessToken)
			throws IOException {
		PrintWriter out = response.getWriter();
		RetJsonUtil retJsonUtil = new RetJsonUtil();
		Map<String, String> map = new HashMap<>();
		// 用户注册时初始信息设置
		String telephone = request.getParameter("telephone");// 注册时的账号
		String password = request.getParameter("password");// 注册时的密码
		ResidentialUser checkAccountIsOn = new ResidentialUser();
		residentialUserModel.setUserAccount(telephone);
		// MD5加密
		residentialUserModel.setUserPassword(StringMd5Util.MD5Digest(password));
		// 16位随机字符串作为用户昵称
		residentialUserModel.setUserNickname(UserUtills.getRandomString(16));
		// 设置用户身份证,用于查询房屋所用
		// 流程：通过注册的手机号查询 customer 表 以此关联查询 room 表取出身份证
		map.put("telephone", telephone);
		List<Customer> list = customerService.getCustomerRoomid(map);
		//list 为空说明后台并未录入该手机号对应的业主 此时无法获取到身份证 无法查询房屋信息
		if(!list.isEmpty()){
			String userIdentityCard = list.get(0).getCustomerIdCardNumber();
			String userName = list.get(0).getCustomerName();
			residentialUserModel.setUserName(userName);
			residentialUserModel.setUserIdentityCard(userIdentityCard);
		}
		// 解析短信验证码的token
		Map<String, Object> tokenMap = new HashMap<String, Object>();
		tokenMap = AccessTokenUtil.parserAccessTokenToMap(accessToken);

		// 判断tokenMap是否有效
		if (tokenMap != null) {
			// 检测是该账户是否已注册
			checkAccountIsOn = residentialUserService
					.getUserLoginInfo(residentialUserModel);
			// 表示该账户已注册过
			if (checkAccountIsOn != null) {
				retJsonUtil.setStatus("2");
				retJsonUtil.setMessage("账户已注册过");
				out.write(retJsonUtil.getRetJsonM());
				return;
			}

			// 手机号与短信不匹配
			if (!telephone.equals(tokenMap.get("telephone"))) {
				retJsonUtil.setStatus("0");
				retJsonUtil.setMessage("注册失败,手机号与短信不匹配");
				out.write(retJsonUtil.getRetJsonM());
				return;
			}

			// 短信验证码错误
			if (!checkTelephoneCode(telephoneCode, tokenMap)) {
				retJsonUtil.setStatus("3");
				retJsonUtil.setMessage("注册失败,验证码错误");
				out.write(retJsonUtil.getRetJsonM());
				return;
			}

			// 进行用户注册操作
			int addStatus = residentialUserService
					.addUser(residentialUserModel);
			if (addStatus > 0) {// 注册成功
				retJsonUtil.setStatus("1");
				retJsonUtil.setMessage("注册成功");
			} else {// 注册失败
				retJsonUtil.setStatus("4");
				retJsonUtil.setMessage("注册失败");
			}
			out.write(retJsonUtil.getRetJsonM());
			return;

		} else {
			retJsonUtil.setStatus("-1");
			retJsonUtil.setMessage("token无效");
			out.write(retJsonUtil.getRetJsonM());
			return;
		}
	}

	/**
	 * @author qwc 2017年7月31日下午4:00:47
	 * @param appPayRequest
	 * @param telephoneCodeSession
	 * @param telePhoneCode
	 * @return boolean 验证码正确性检验
	 */
	static boolean checkTelephoneCode(String telePhoneCode,
			Map<String, Object> codeMap) {
		String msgCode = (String) codeMap.get("telephoneCode");
		if (telePhoneCode.equals(msgCode)) {
			return true;
		} else {// 验证码不正确
			return false;
		}
	}

	/**
	 * @author qwc 2017年7月25日下午9:56:09
	 * @throws IOException void 修改或完善用户信息
	 */
	@RequestMapping("updateUser")
	public void updateUserInfo(HttpServletResponse response,
			ResidentialUser record, HttpServletRequest request)
			throws IOException {
		PrintWriter out = response.getWriter();
		MultipartHttpServletRequest Mrequest = (MultipartHttpServletRequest) request;
		Map<String, Object> retMap = new HashMap<String, Object>();

		// 获取用户头像
		MultipartFile iconfile = Mrequest.getFile("userIcon");
		RetJsonUtil retJsonUtil = new RetJsonUtil();

		// 获取登录token并解析
		String accessToken = request.getParameter("token");
		ResidentialUser residentialUserToken = AccessTokenUtil
				.parserAccessTokenToModel(accessToken);
		// 检测该账号下是否有小区通过审核
		if (residentialUserToken.getUmsIdsInfo() == null) {
			retJsonUtil.setStatus("-1");
			retJsonUtil.setMessage("此账号未有小区通过审核");
			out.write(retJsonUtil.getRetJsonM());
			return;
		}

		// 获取用户昵称（接口文档当时错给成userNickName,移动端脾气不怎么好，不想重改接口文档，改这里手动接收）
		String userNickName = Mrequest.getParameter("userNickName");
		// 获取身份类型
		UMsIds uMsIds = residentialUserToken.getUmsIdsInfo();
		int identifyClassify = 0;
		if (uMsIds.getIdentifyClassify() != null) {
			identifyClassify = uMsIds.getIdentifyClassify();
		}

		// 设置用户ID
		record.setUserId(residentialUserToken.getUserId());
		// 设置用户昵称
		record.setUserNickname(userNickName);

		// 检测用户账号是否通过审核
		int checkHasCommunityPass = 0;
		checkHasCommunityPass = uMsIdsMapperService
				.checkHasOneCommunityPass(residentialUserToken.getUserId());

		// 账户通过审核后不允许修改身份类型和身份证号、姓名和性别
		if (checkHasCommunityPass > 0 && identifyClassify == 1
				&& (StringUtils.isNotBlank(record.getUserName())
						|| StringUtils.isNotBlank(record.getUserIdentityCard())
						|| StringUtils.isNotBlank(record.getUserSex()))) {
			retJsonUtil.setStatus("2");
			retJsonUtil.setMessage("非法更改信息");
			retJsonUtil.setRetMap(retMap);
		} else {
			String iconImgUrl = null;
			// 头像不为空
			if (iconfile != null && !iconfile.isEmpty()) {
				iconImgUrl = saveUserIconImg(iconfile,
						residentialUserToken.getUserId(), "userIconImg");
			}
			// 设置头像路径
			record.setUserIcon(iconImgUrl);

			// 执行用户信息更新操作
			int updateStatus = residentialUserService.updateByUserId(record);

			// 封装返回结果
			if (updateStatus > 0) {// 更新成功
				retMap.put("userIcon", iconImgUrl);
				retJsonUtil.setStatus("1");
				retJsonUtil.setMessage("更新成功");
				retJsonUtil.setRetMap(retMap);
			} else {// 更新失败
				retJsonUtil.setStatus("0");
				retJsonUtil.setMessage("更新失败");
				retJsonUtil.setRetMap(retMap);
			}
		}
		out.write(retJsonUtil.getRetJsonM());
	}

	/**
	 * @description 修改密码
	 *
	 * @auhtor qwc 2017年10月20日下午2:21:36
	 * @param session
	 * @param request
	 * @param accessToken
	 * @param telephoneCode
	 * @param response
	 * @param residentialUserModel
	 * @throws IOException void
	 */
	@RequestMapping("changePass")
	public void changePassword(HttpSession session, HttpServletRequest request,
			@RequestParam(value = "token", required = true, defaultValue = "000000") String accessToken,
			@RequestParam(value = "telephoneCode", required = true, defaultValue = "000000") String telephoneCode,
			HttpServletResponse response, ResidentialUser residentialUserModel)
			throws IOException {
		PrintWriter out = response.getWriter();
		RetJsonUtil retJsonUtil = new RetJsonUtil();

		// 验证码token解析
		Map<String, Object> codeMap = AccessTokenUtil
				.parserAccessTokenToMap(accessToken);

		// 判断验证码是否失效
		if (codeMap == null) {
			retJsonUtil.setStatus("-3");
			retJsonUtil.setMessage("验证失效");
			out.write(retJsonUtil.getRetJsonM());
			return;
		}

		// 判断账号是否匹配
		if (!codeMap.get("telephone")
				.equals(residentialUserModel.getUserAccount())) {
			retJsonUtil.setStatus("-1");
			retJsonUtil.setMessage("账号不匹配");
			out.write(retJsonUtil.getRetJsonM());
			return;
		}

		// 判断验证码短信是否匹配
		if (!codeMap.get("telephoneCode").equals(telephoneCode)) {
			retJsonUtil.setStatus("-2");
			retJsonUtil.setMessage("短信不匹配");
			out.write(retJsonUtil.getRetJsonM());
			return;
		}

		// 设置新密码
		residentialUserModel.setUserPassword(StringMd5Util
				.MD5Digest(residentialUserModel.getUserPassword()));
		int status = residentialUserService
				.updateByUserAccount(residentialUserModel);

		// 封装返回结果
		if (status > 0) {
			retJsonUtil.setStatus("1");
			retJsonUtil.setMessage("修改成功");
		} else {
			retJsonUtil.setStatus("0");
			retJsonUtil.setMessage("修改失败");
		}
		out.write(retJsonUtil.getRetJsonM());
	}

	/**
	 * @author qwc 2017年7月25日下午10:19:05
	 * @param appPayRequest
	 * @param file
	 * @param url
	 * @param userId
	 * @param foldername
	 * @return
	 * @throws IOException String 保存用户的图片
	 */
	public static String saveUserIconImg(MultipartFile file, int userId,
			String foldername) throws IOException {
		String url = null;
		String uploadUrl = "/usr/ezsh/upload/images/" + foldername + "/"
				+ userId + "/";// 头像保存路径
		System.out.println("路径为:" + uploadUrl);
		File dir = new File(uploadUrl);
		if (!dir.exists()) {
			dir.mkdirs();
		}
		url = DateUtil.dateToStr(new Date(), DateUtil.DATE_TIME_NO_SLASH)
				+ ".jpg";
		File targetFile = new File(uploadUrl + url);
		uploadUrl = "/ezsh/upload/images/" + foldername + "/" + userId + "/"
				+ url;// 数据库保存路径
		try {
			file.transferTo(targetFile);
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return uploadUrl;
	}

}
