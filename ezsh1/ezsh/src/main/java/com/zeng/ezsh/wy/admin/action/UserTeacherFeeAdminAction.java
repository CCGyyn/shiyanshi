package com.zeng.ezsh.wy.admin.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zeng.ezsh.wy.entity.Grid;
import com.zeng.ezsh.wy.entity.GridRoomItem;
import com.zeng.ezsh.wy.entity.UserTeacherFee;
import com.zeng.ezsh.wy.service.UserTeacherFeeService;
import com.zeng.ezsh.wy.utils.RetJsonUtil;
/**
 * 
 * @author quanweicong
 * 家教平台移动端支付记录后台action
 */
@Controller
@RequestMapping("userTeacherFeeM")
public class UserTeacherFeeAdminAction {
	@Resource
	UserTeacherFeeService userTeacherFeeService;
	/**
	 * @author qwc
	 * 2017年12月7日下午4:50:50
	 * @param roomId
	 * @param response
	 * @throws IOException 
	 * void
	 */
	@RequestMapping(value = "select")
	public void selectGrid(HttpServletRequest request,HttpServletResponse response, UserTeacherFee record,
			@RequestParam(value="page",required=true,defaultValue="1") Integer startPage,
			@RequestParam(value="rows",required=false,defaultValue="10") Integer pageSize) throws IOException{
		PrintWriter out=response.getWriter();
		RetJsonUtil retJsonUtil = new RetJsonUtil();//创建json返回对象

		PageHelper.startPage(startPage, pageSize);//分页获取
		List<UserTeacherFee> list = userTeacherFeeService.selectListByParamM(record);
		PageInfo<UserTeacherFee> page = new PageInfo<UserTeacherFee>(list);
		
		//返回结果
		retJsonUtil.setList(list);
		retJsonUtil.setTotal(page.getTotal());
		out.write(retJsonUtil.getRetJsonEasyGrid());
	}
	
}
