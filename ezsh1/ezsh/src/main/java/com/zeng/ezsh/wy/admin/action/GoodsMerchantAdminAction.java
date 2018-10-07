package com.zeng.ezsh.wy.admin.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.apache.commons.lang.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zeng.ezsh.wy.entity.Admin;
import com.zeng.ezsh.wy.entity.GoodsMerchant;
import com.zeng.ezsh.wy.entity.Grid;
import com.zeng.ezsh.wy.service.GoodsMerchantService;
import com.zeng.ezsh.wy.utils.RetJsonUtil;

@Controller
@RequestMapping(value = "merchantA")
public class GoodsMerchantAdminAction {
	@Resource
	GoodsMerchantService goodsMerchantService;
	/**
	 * @author qwc
	 * 2017年9月24日下午10:16:41
	 * @param request
	 * @param response
	 * @param grid
	 * @throws IOException 
	 * void 获取小区商家集合
	 */
	@RequestMapping(value="selectSimple")
	public void selectMerchantList(HttpServletRequest request, HttpServletResponse response, 
			GoodsMerchant goodsMerchant) throws IOException{
		PrintWriter out=response.getWriter();
		Subject subject = SecurityUtils.getSubject();
		
		Admin adminInfo =(Admin) subject.getPrincipal();
		if (subject.isPermitted("p101")) {//查看所有小区
			
		} else {//没有查看所有小区权限
			goodsMerchant.setpManagerId(adminInfo.getAdManagerId());
		}
		
		List<GoodsMerchant> list=goodsMerchantService.selectByPrimaryKey(goodsMerchant);
		out.write(JSONArray.fromObject(list).toString());
	}
	
	/**
	 * @author qwc
	 * 2017年11月14日下午9:02:42
	 * @param request
	 * @param response
	 * @param goodsMerchant
	 * @throws IOException 
	 * void 添加商家
	 */
	@RequestMapping(value="add")
	public void addMerchant(HttpServletRequest request, HttpServletResponse response, 
			GoodsMerchant goodsMerchant) throws IOException{
		PrintWriter out=response.getWriter();
		RetJsonUtil retJsonUtil = new RetJsonUtil();
		int amount = goodsMerchantService.getMerchantAmount(goodsMerchant);
		if(amount > 0){
			retJsonUtil.setMessage("暂时只允许一家商家");
			retJsonUtil.setStatus("-1");
			out.write(retJsonUtil.getRetJsonM());
			return;
		}
		
		int status = goodsMerchantService.insertSelective(goodsMerchant);
		if (status > 0) {
			retJsonUtil.setMessage("添加成功");
			retJsonUtil.setStatus("1");
			out.write(retJsonUtil.getRetJsonM());
		} else {
			retJsonUtil.setMessage("添加失败");
			retJsonUtil.setStatus("0");
			out.write(retJsonUtil.getRetJsonM());
		}
	}
	
	/**
	 * @author qwc
	 * 2017年11月14日下午9:02:42
	 * @param request
	 * @param response
	 * @param goodsMerchant
	 * @throws IOException 
	 * void 更新商家信息
	 */
	@RequestMapping(value="update")
	public void updateMerchant(HttpServletRequest request, HttpServletResponse response, 
			GoodsMerchant goodsMerchant) throws IOException{
		PrintWriter out=response.getWriter();
		RetJsonUtil retJsonUtil = new RetJsonUtil();

		int status = goodsMerchantService.updateByPrimaryKeySelective(goodsMerchant);
		if (status > 0) {
			retJsonUtil.setMessage("更新成功");
			retJsonUtil.setStatus("1");
			out.write(retJsonUtil.getRetJsonM());
		} else {
			retJsonUtil.setMessage("更新失败");
			retJsonUtil.setStatus("0");
			out.write(retJsonUtil.getRetJsonM());
		}
	}
	
	/**
	 * @author qwc
	 * 2017年11月14日下午9:06:30
	 * @param request
	 * @param response
	 * @param goodsMerchant
	 * @throws IOException 
	 * void 获取商家集合
	 */
	@RequestMapping(value="select")
	public void getMerchantList(HttpServletRequest request, HttpServletResponse response, GoodsMerchant goodsMerchant,
			@RequestParam(value="page",required=true,defaultValue="1") Integer startPage,
			@RequestParam(value="rows",required=false,defaultValue="10") Integer pageSize) throws IOException{
		PrintWriter out=response.getWriter();
		Subject subject = SecurityUtils.getSubject();
		RetJsonUtil retJsonUtil = new RetJsonUtil();
		
		Admin adminInfo = (Admin) subject.getPrincipal();
		if (subject.isPermitted("p101")) {//查看所有小区
			
		} else {//没有查看所有小区权限
			goodsMerchant.setpManagerId(adminInfo.getAdManagerId());
		}
		
		PageHelper.startPage(startPage, pageSize);//分页获取
		List<GoodsMerchant> list=goodsMerchantService.selectByPrimaryKey(goodsMerchant);
		PageInfo<GoodsMerchant> page = new PageInfo<GoodsMerchant>(list);
	
		//返回结果
		retJsonUtil.setList(list);
		retJsonUtil.setTotal(page.getTotal());
		out.write(retJsonUtil.getRetJsonEasyGrid());
	}
}
