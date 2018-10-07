package com.zeng.ezsh.wy.admin.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zeng.ezsh.wy.entity.Admin;
import com.zeng.ezsh.wy.entity.GoodsMerchant;
import com.zeng.ezsh.wy.entity.Teacher;
import com.zeng.ezsh.wy.service.TeacherService;
import com.zeng.ezsh.wy.utils.RetJsonUtil;
/**
 * 
 * @author quanweicong
 * 后台家教老师 action
 */
@Controller
@RequestMapping("teacherA")
public class TeacherAdminAction {
	@Resource
	TeacherService teacherService;
	
	@RequestMapping(value="select")
	public void getTeacherList(HttpServletRequest request, HttpServletResponse response, Teacher teacher,
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
		List<Teacher> list = teacherService.selectTeacherList(teacher);
		PageInfo<Teacher> page = new PageInfo<Teacher>(list);
	
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
	public void checkTeacher(HttpServletRequest request, HttpServletResponse response, Teacher teacher,
			@RequestParam(value="checkStatus",required=true,defaultValue="0") Integer checkStatus) throws IOException {
		PrintWriter out=response.getWriter();
		Subject subject = SecurityUtils.getSubject();
		RetJsonUtil retJsonUtil = new RetJsonUtil();
		
		//执行审核操作
		int status = teacherService.updateCheck(teacher);
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
