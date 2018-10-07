package com.zeng.ezsh.wy.admin.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zeng.ezsh.wy.entity.ResidentialUser;
import com.zeng.ezsh.wy.entity.Teacher;
import com.zeng.ezsh.wy.entity.UMsIds;
import com.zeng.ezsh.wy.service.ResidentialUserService;
import com.zeng.ezsh.wy.service.TeacherService;
import com.zeng.ezsh.wy.service.UMsIdsService;
import com.zeng.ezsh.wy.utils.RetJsonUtil;
@Controller
@RequestMapping(value = "userA")
public class UserAdminAction {
	@Resource
	ResidentialUserService residentialUserService;
	@Resource
	UMsIdsService uMsIdsService;
	/**
	 * @author qwc
	 * 2017年11月26日下午8:08:01
	 * @param request
	 * @param response
	 * @param teacher
	 * @param startPage
	 * @param pageSize
	 * @throws IOException 
	 * void 获取App端注册用户
	 */
	@RequestMapping(value="select")
	public void getUserList(HttpServletRequest request, HttpServletResponse response, ResidentialUser residentialUser,
			@RequestParam(value="page",required=false,defaultValue="1") Integer startPage,
			@RequestParam(value="rows",required=false,defaultValue="10") Integer pageSize) throws IOException{
		PrintWriter out=response.getWriter();
		Subject subject = SecurityUtils.getSubject();
		RetJsonUtil retJsonUtil = new RetJsonUtil();
		
		Map<String, Object> param = new HashMap<String, Object>();
		/*Admin adminInfo =(Admin) subject.getPrincipal();
		if (subject.isPermitted("p101")) {//查看所有小区
			
		} else {//没有查看所有小区权限
			teacher.setpManagerId(adminInfo.getAdManagerId());
		}*/
		
		// 执行查询
		PageHelper.startPage(startPage, pageSize);//分页获取
		List<ResidentialUser> list = residentialUserService.getResidentialUserList(param);
		PageInfo<ResidentialUser> page = new PageInfo<ResidentialUser>(list);
	
		// 返回结果
		retJsonUtil.setList(list);
		retJsonUtil.setTotal(page.getTotal());
		out.write(retJsonUtil.getRetJsonEasyGrid());
	}
	
	@RequestMapping("jumpToDetailsView")
	public String jumpToGoodsDetailsList(HttpServletRequest request, Model model){
		String userId=request.getParameter("userId");
		model.addAttribute("userId", userId);
		return "customer/mobile/detailsList";
	}
	
	@RequestMapping(value="selectD")
	public void getUserAccountListD(HttpServletRequest request, HttpServletResponse response, ResidentialUser record,
			@RequestParam(value="page",required=false,defaultValue="1") Integer startPage,
			@RequestParam(value="rows",required=false,defaultValue="10") Integer pageSize) throws IOException{
		PrintWriter out=response.getWriter();
		Subject subject = SecurityUtils.getSubject();
		RetJsonUtil retJsonUtil = new RetJsonUtil();
		
		/*Admin adminInfo =(Admin) subject.getPrincipal();
		if (subject.isPermitted("p101")) {//查看所有小区
			
		} else {//没有查看所有小区权限
			teacher.setpManagerId(adminInfo.getAdManagerId());
		}*/
		
		// 执行查询
		PageHelper.startPage(startPage, pageSize);//分页获取
		List<UMsIds> list = uMsIdsService.selectAccountListA(record);
		PageInfo<UMsIds> page = new PageInfo<UMsIds>(list);
	
		// 返回结果
		retJsonUtil.setList(list);
		retJsonUtil.setTotal(page.getTotal());
		out.write(retJsonUtil.getRetJsonEasyGrid());
	}
	
	/**
	 * @author qwc
	 * 2017年11月21日下午8:21:08 
	 * void
	 * @throws IOException 
	 */
	@RequestMapping("check")
	public void checkTeacher(HttpServletRequest request, HttpServletResponse response, UMsIds record,
			@RequestParam(value="checkStatus",required=true,defaultValue="0") Integer checkStatus) throws IOException {
		PrintWriter out=response.getWriter();
		Subject subject = SecurityUtils.getSubject();
		RetJsonUtil retJsonUtil = new RetJsonUtil();
		
		//执行审核操作
		int status = uMsIdsService.updateByPrimaryKeySelectiveForCheck(record);
		if(status > 0) {
			retJsonUtil.setMessage("操作成功");
			retJsonUtil.setStatus("1");
		} else {
			retJsonUtil.setMessage("操作失败");
			retJsonUtil.setStatus("0");
		}
		out.write(retJsonUtil.getRetJsonM());
	}
}
