package com.zeng.ezsh.wy.admin.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zeng.ezsh.wy.entity.Grid;
import com.zeng.ezsh.wy.entity.Rent;
import com.zeng.ezsh.wy.service.RentService;
import com.zeng.ezsh.wy.utils.RetJsonUtil;
@Controller
@RequestMapping("rentA")
public class RentActionAdmin {
	@Resource
	RentService rentService;
	/**
	 * @author qwc
	 * 2017年11月11日下午3:59:14
	 * @param rent
	 * @param request
	 * @param response 
	 * void 添加出租记录
	 * @throws IOException 
	 */
	@RequestMapping(value = "add")
	public void addRent(Rent record, HttpServletRequest request, 
			HttpServletResponse response) throws IOException{
		PrintWriter out = response.getWriter();
		RetJsonUtil retJsonUtil = new RetJsonUtil();
		int status = rentService.insertSelective(record);//添加出租记录
		
		if (status > 0 ){
			retJsonUtil.setMessage("添加成功");
			retJsonUtil.setStatus("1");
		} else {
			retJsonUtil.setMessage("添加失败");
			retJsonUtil.setStatus("0");
		}
		out.write(retJsonUtil.getRetJsonM());
	}
	
	/**
	 * @author qwc
	 * 2017年11月11日下午4:04:03
	 * @param record
	 * @param request
	 * @param response
	 * @throws IOException 
	 * void 获取出租记录集合
	 */
	@RequestMapping(value = "select")
	public void selectRent(Rent record, HttpServletRequest request,HttpServletResponse response,
			@RequestParam(value="page",required=true,defaultValue="1") Integer startPage,
			@RequestParam(value="rows",required=false,defaultValue="10") Integer pageSize) throws IOException{
		PrintWriter out = response.getWriter();
		RetJsonUtil retJsonUtil = new RetJsonUtil();

		PageHelper.startPage(startPage, pageSize);//分页获取
		List<Rent> list = rentService.selectRentList(record);
		PageInfo<Rent> page = new PageInfo<Rent>(list);
		
		//返回结果
		retJsonUtil.setList(list);
		retJsonUtil.setTotal(page.getTotal());
		out.write(retJsonUtil.getRetJsonEasyGrid());
	}
	
	/**
	 * @author qwc
	 * 2017年11月11日下午9:33:05
	 * @param record
	 * @param request
	 * @param response
	 * @throws IOException 
	 * void 更新出租记录
	 */
	@RequestMapping(value = "update")
	public void updateRent(Rent record, HttpServletRequest request,HttpServletResponse response) throws IOException{
		PrintWriter out = response.getWriter();
		RetJsonUtil retJsonUtil = new RetJsonUtil();

		int status = rentService.updateRent(record);
		
		if (status > 0){
			retJsonUtil.setMessage("更新成功");
			retJsonUtil.setStatus("1");
		} else {
			retJsonUtil.setMessage("更新失败");
			retJsonUtil.setStatus("0");
		}
		//返回结果
		out.write(retJsonUtil.getRetJsonM());
	}
	
	/**
	 * @author qwc
	 * 2017年11月11日下午9:35:18
	 * @param record
	 * @param request
	 * @param response
	 * @throws IOException 
	 * void 删除出租记录
	 */
	@RequestMapping(value = "delete")
	public void delRent(Rent record, HttpServletRequest request,HttpServletResponse response) throws IOException{
		PrintWriter out = response.getWriter();
		RetJsonUtil retJsonUtil = new RetJsonUtil();

		int status = rentService.delRent(record);
		
		if (status > 0){
			retJsonUtil.setMessage("删除成功");
			retJsonUtil.setStatus("1");
		} else {
			retJsonUtil.setMessage("删除失败");
			retJsonUtil.setStatus("0");
		}
		//返回结果
		out.write(retJsonUtil.getRetJsonM());
	}
}
