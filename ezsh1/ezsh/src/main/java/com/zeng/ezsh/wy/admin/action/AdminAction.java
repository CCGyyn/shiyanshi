package com.zeng.ezsh.wy.admin.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.bouncycastle.util.encoders.UrlBase64Encoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zeng.ezsh.wy.entity.Admin;
import com.zeng.ezsh.wy.entity.GoodsInfo;
import com.zeng.ezsh.wy.service.AdminService;
import com.zeng.ezsh.wy.utils.HttpUrlConnectionUtil;
import com.zeng.ezsh.wy.utils.RetJsonUtil;
import com.zeng.ezsh.wy.utils.StringMd5Util;

/**
 * 
 * @author quanweicong 后台用户action
 */
@Controller
@RequestMapping("ad")
public class AdminAction {
	@Resource
	AdminService adminService;

	/**
	 * @author qwc 2017年7月16日下午4:17:26 void 添加管理员
	 * @throws IOException
	 */
	@RequestMapping(value = "add")
	public void addAdmin(Admin adminModel, HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		PrintWriter out = response.getWriter();
		RetJsonUtil retJsonUtil = new RetJsonUtil();

		// 判断密码是否为空
		if (adminModel.getAdPassword() != null) {
			adminModel.setAdPassword(StringMd5Util.MD5Digest(adminModel
					.getAdPassword()));// 加密
		} else {
			retJsonUtil.setMessage("密码为空");
			retJsonUtil.setStatus("-1");
			out.write(retJsonUtil.getRetJsonM());// 密码为空
			return;
		}

		// 检测账号是否存在
		int isIn = adminService.checkAccountIsIn(adminModel);
		if (isIn > 0) {
			retJsonUtil.setMessage("账号已存在");
			retJsonUtil.setStatus("2");
			out.write(retJsonUtil.getRetJsonM());// 账号已存在
			return;
		}

		// 执行添加
		int status = adminService.insert(adminModel);
		if (status > 0) {
			retJsonUtil.setMessage("添加成功");
			retJsonUtil.setStatus("1");
		} else {
			retJsonUtil.setMessage("添加失败");
			retJsonUtil.setStatus("0");
		}
		out.write(retJsonUtil.getRetJsonM());
	}

	/**
	 * @author qwc 2017年10月10日下午9:26:16
	 * @param adminModel
	 * @param request
	 * @param response
	 * @throws IOException
	 * void 修改管理员信息
	 */
	@RequestMapping("update")
	public void updateAdmin(Admin adminModel, HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		PrintWriter out = response.getWriter();
		RetJsonUtil retJsonUtil = new RetJsonUtil();

		// 执行更新
		int status = adminService.updateByPrimaryKey(adminModel);// 执行更新
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
	 * @author qwc 2017年10月13日下午7:13:18
	 * @param adminModel
	 * @param request
	 * @param response
	 * @throws IOException
	 * void 修改管理员密码
	 */
	@RequestMapping("changePass")
	public void updateAdminPass(Admin adminModel, HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		PrintWriter out = response.getWriter();
		RetJsonUtil retJsonUtil = new RetJsonUtil();

		adminModel.setAdPassword(StringMd5Util.MD5Digest(adminModel
				.getAdPassword()));
		int status = adminService.updatePassByPrimaryKey(adminModel);// 执行更新
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
	 * @author qwc 2017年10月10日下午9:45:41
	 * @param adminModel
	 * @param request
	 * @param response
	 * @throws IOException
	 * void 获取管理员列表
	 */
	@RequestMapping("select")
	public void selectAdmin(
			Admin adminModel,
			HttpServletRequest request,
			HttpServletResponse response,
			@RequestParam(value = "startPage", required = true, defaultValue = "1") Integer startPage,
			@RequestParam(value = "pageSize", required = false, defaultValue = "10") Integer pageSize)
			throws IOException {
		PrintWriter out = response.getWriter();
		Subject subject = SecurityUtils.getSubject();
		RetJsonUtil retJsonUtil = new RetJsonUtil();

		// 检测权限
		Admin adminInfo = (Admin) subject.getPrincipal();
		if (subject.hasRole("p101")) {// 有查看所有小区权限

		} else {// 没有查看所有小区权限
			adminModel.setAdManagerId(adminInfo.getAdManagerId());
		}

		// 分页获取
		PageHelper.startPage(startPage, pageSize);
		List<Admin> list = adminService.selectByParam(adminModel);
		PageInfo<Admin> page = new PageInfo<Admin>(list);

		// 返回结果
		retJsonUtil.setList(list);
		retJsonUtil.setTotal(page.getTotal());
		out.write(retJsonUtil.getRetJsonEasyGrid());
	}

	/**
	 * @author qwc 2017年10月13日下午7:11:06
	 * @return 跳转到管理员列表界面 String
	 */
	@RequestMapping("list")
	public String adList() {
		return "admin-set/list";
	}

	/**
	 * @author qwc 2017年10月13日下午4:11:03
	 * @return 跳转到后台主页 String
	 */
	@RequestMapping("main")
	public String adminIndex() {
		return "main/main";
	}

}
