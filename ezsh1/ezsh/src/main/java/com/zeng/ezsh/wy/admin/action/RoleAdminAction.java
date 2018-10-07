package com.zeng.ezsh.wy.admin.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.subject.Subject;
import org.springframework.jmx.export.annotation.ManagedAttribute;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zeng.ezsh.wy.entity.Admin;
import com.zeng.ezsh.wy.entity.Role;
import com.zeng.ezsh.wy.service.RoleService;
import com.zeng.ezsh.wy.utils.RetJsonUtil;
@Controller
@RequestMapping("ro")
public class RoleAdminAction {
	private static Logger logger=Logger.getLogger("RoleAction");
	@Resource
	RoleService roleService;
	/**
	 * @author qwc
	 * 2017年7月17日上午11:23:02
	 * @return String
	 * 获取角色列表
	 * @throws IOException 
	 */
	@RequestMapping("roList")
	public void roleList(HttpServletRequest request,HttpServletResponse response,
			@RequestParam(value="page",required=true,defaultValue="1") Integer startPage,
			@RequestParam(value="rows",required=false,defaultValue="10") Integer pageSize) throws IOException{
		PrintWriter out=response.getWriter();
		RetJsonUtil retJsonUtil=new RetJsonUtil();
		Subject subject = SecurityUtils.getSubject();
		
		//设置查询条件
		Map<Object,Object> param=new HashMap<Object, Object>();
		String keyWord=request.getParameter("keyWord");//设置关键字
		if(StringUtils.isNotBlank(keyWord)){
			param.put("keyWord", keyWord);
		}
		
		//权限检测
		Admin adminInfo =(Admin) subject.getPrincipal();
		if (subject.isPermitted("p101")) {//查看所有小区
			if(StringUtils.isNotBlank(request.getParameter("ptManagerId"))){
				param.put("ptManagerId", request.getParameter("ptManagerId"));//设置managerId
			}
		} else {//没有查看所有小区权限
			param.put("ptManagerId", adminInfo.getAdManagerId());//设置managerId
		}
		
		//分页查询
		PageHelper.startPage(startPage, pageSize);
		List<Role> list = roleService.selectByCondition(param);
		PageInfo<Role> page = new PageInfo<Role>(list);
		
		//返回结果
		retJsonUtil.setList(list);
		retJsonUtil.setTotal(page.getTotal());
		out.write(retJsonUtil.getRetJsonEasyGrid());
	}
	
	/**
	 * @author qwc
	 * 2017年11月14日下午3:28:46
	 * @param request
	 * @param response
	 * @throws IOException 
	 * void 获取角色列表，用于下拉框选择
	 */
	@RequestMapping(value = "getRoleList")
	public void getRoleList(HttpServletRequest request,HttpServletResponse response) throws IOException{
		PrintWriter out=response.getWriter();
		Subject subject = SecurityUtils.getSubject();
		
		//设置查询条件
		Map<Object,Object> param=new HashMap<Object, Object>();
		//权限检测
		Admin adminInfo =(Admin) subject.getPrincipal();
		if (subject.isPermitted("p101")) {//查看所有小区
			if(StringUtils.isNotBlank(request.getParameter("ptManagerId"))){
				param.put("ptManagerId", request.getParameter("ptManagerId"));//设置managerId
			}
		} else {//没有查看所有小区权限
			param.put("ptManagerId", adminInfo.getAdManagerId());//设置managerId
		}
		
		//查询
		List<Role> list = roleService.selectByCondition(param);
	
		//返回结果
		out.write(JSONArray.fromObject(list).toString());
	}
	
	/**
	 * @author qwc
	 * 2017年11月14日下午3:25:42
	 * @return 跳转到添加角色界面
	 * String
	 */
	@RequestMapping("jumpToAddRole")
	public String jumpToAddRoleView(){
		return "admin-set/role/add";
	}
	
	/**
	 * @author qwc
	 * 2017年7月17日上午11:23:34
	 * @return String
	 * 添加角色
	 * @throws IOException 
	 */
	@RequestMapping("addRo")
	@RequiresPermissions("p111")
	public void addRole(Role roleModel,HttpServletRequest request,HttpServletResponse response) throws IOException{
		PrintWriter out=response.getWriter();
		RetJsonUtil retJsonUtil =new RetJsonUtil();
		
		//添加前检查是否代managerId
		if (roleModel.getPtManagerId() == 0 || roleModel.getPtManagerId() == null) {
			retJsonUtil.setStatus("-2");
			retJsonUtil.setMessage("请选择小区");
			out.write(retJsonUtil.getRetJsonM());
			return;
		}
		
		//添加前检查是否有重复的名字
		int check = roleService.selectCountByRoleName(roleModel);
		if (check > 0) {
			retJsonUtil.setStatus("-1");
			retJsonUtil.setMessage("角色名字不能重复");
			out.write(retJsonUtil.getRetJsonM());
			return;
		} 
		
		//执行添加
		int insertStatus = roleService.insert(roleModel);//添加角色
		if (insertStatus == 1) {
			retJsonUtil.setStatus("1");
			retJsonUtil.setMessage("添加成功");
			out.write(retJsonUtil.getRetJsonM());
			return;
		} else {
			retJsonUtil.setStatus("0");
			retJsonUtil.setMessage("添加失败");
			out.write(retJsonUtil.getRetJsonM());
			return;
		}
	}
	
	/**
	 * @author qwc
	 * 2017年7月23日下午12:02:00 void
	 * 跳转到修改角色界面
	 */
	@RequestMapping("jumpToUpdateRole")
	public ModelAndView getPrivByRoleId(Role roleModel, HttpServletRequest request){
		ModelAndView jumpToUpdateRole=new ModelAndView();
		roleModel = roleService.selectByPrimaryKey(roleModel.getRoId());
		jumpToUpdateRole.addObject("roleInfo", roleModel);
		jumpToUpdateRole.setViewName("admin-set/role/update");
		return jumpToUpdateRole;
	}
	
	/**
	 * @author qwc
	 * 2017年7月23日下午12:02:17 void
	 * 修改角色
	 * @throws IOException 
	 */
	@RequestMapping("updateRole")
	public void updateRoleInfo(Role roleModel, HttpServletRequest request, 
			HttpServletResponse response) throws IOException{
		PrintWriter out=response.getWriter();
		RetJsonUtil retJsonUtil = new RetJsonUtil();
		
		//检查是否有重复名字
		int check = roleService.selectCountByRoleName(roleModel);
		if (check > 0) {
			retJsonUtil.setStatus("-1");
			retJsonUtil.setMessage("角色名字已存在");
			out.write(retJsonUtil.getRetJsonM());
			return;
		} 
		
		// 执行更新操作
		int updateStatus = roleService.updateByPrimaryKey(roleModel);//更新角色
		if (updateStatus == 1) {
			retJsonUtil.setStatus("1");
			retJsonUtil.setMessage("更新成功");
			out.write(retJsonUtil.getRetJsonM());
			return;
		} else {
			retJsonUtil.setStatus("0");
			retJsonUtil.setMessage("更新失败");
			out.write(retJsonUtil.getRetJsonM());
			return;
		}
	}
}
