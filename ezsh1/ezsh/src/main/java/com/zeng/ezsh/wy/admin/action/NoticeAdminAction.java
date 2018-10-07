package com.zeng.ezsh.wy.admin.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zeng.ezsh.wy.entity.Grid;
import com.zeng.ezsh.wy.entity.Notice;
import com.zeng.ezsh.wy.service.NoticeService;
import com.zeng.ezsh.wy.utils.RetJsonUtil;
/**
 * 公告
 * @author quanweicong
 *
 */
@Controller
@RequestMapping("notice")
public class NoticeAdminAction {
	@Resource
	NoticeService noticeService;
	
	/**
	 * @author qwc
	 * 2017年10月26日下午9:15:06
	 * @param request
	 * @param response
	 * @param record
	 * @throws IOException 
	 * void 添加公告
	 */
	@RequestMapping(value = "add")
	public void addNotice(HttpServletRequest request,HttpServletResponse response,
			Notice record) throws IOException{
		PrintWriter out = response.getWriter();
		RetJsonUtil retJsonUtil = new RetJsonUtil();
		
		//设置添加时间
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String dateNowStr = sdf.format(date); 
		record.setNoticeTime(dateNowStr);
		
		int status = noticeService.insert(record);
		if(status>0){
			retJsonUtil.setMessage("添加成功");
			retJsonUtil.setStatus("1");
		}else{
			retJsonUtil.setMessage("添加失败");
			retJsonUtil.setStatus("0");
		}
		out.write(retJsonUtil.getRetJsonM());
	}
	
	/**
	 * @author qwc
	 * 2017年10月26日下午9:16:17
	 * @param request
	 * @param response
	 * @param record
	 * @throws IOException 
	 * void 删除公告
	 */
	@RequestMapping(value = "delete")
	public void deleteNotice(HttpServletRequest request,HttpServletResponse response,
			Notice record) throws IOException{
		PrintWriter out = response.getWriter();
		RetJsonUtil retJsonUtil = new RetJsonUtil();
		
		int status = noticeService.deleteByPrimaryKey(record);
		if(status>0){
			retJsonUtil.setMessage("删除成功");
			retJsonUtil.setStatus("1");
		}else{
			retJsonUtil.setMessage("删除失败");
			retJsonUtil.setStatus("0");
		}
		out.write(retJsonUtil.getRetJsonM());
	}
	
	/**
	 * @author qwc
	 * 2017年10月26日下午9:18:45
	 * @param request
	 * @param response
	 * @param record
	 * @param startPage
	 * @param pageSize
	 * @throws IOException 
	 * void 获取公告
	 */
	@RequestMapping(value = "select")
	public void selectNotice(HttpServletRequest request,HttpServletResponse response,Notice record,
			@RequestParam(value="page",required=true,defaultValue="1") Integer startPage,
			@RequestParam(value="rows",required=false,defaultValue="10") Integer pageSize) throws IOException{
		PrintWriter out = response.getWriter();
		RetJsonUtil retJsonUtil = new RetJsonUtil();
		
		//没有传管理处ID参数时返回空值
		if(record.getPtManagerId()==null){
			retJsonUtil.setTotal(0);
			out.write(retJsonUtil.getRetJsonEasyGrid());
			return;
		}
		//分页获取
		PageHelper.startPage(startPage, pageSize);
		List<Notice> list=noticeService.selectByPrimaryKey(record);
		PageInfo<Notice> page = new PageInfo<Notice>(list);
		
		//返回结果
		retJsonUtil.setList(list);
		retJsonUtil.setTotal(page.getTotal());
		out.write(retJsonUtil.getRetJsonEasyGrid());
	}
	
	/**
	 * @author qwc
	 * 2017年10月26日下午9:20:33
	 * @param request
	 * @param response
	 * @param record
	 * @throws IOException 
	 * void 更新公告信息
	 */
	@RequestMapping(value = "update")
	public void updateNotice(HttpServletRequest request,HttpServletResponse response,
			Notice record) throws IOException{
		PrintWriter out = response.getWriter();
		RetJsonUtil retJsonUtil = new RetJsonUtil();
		
		int status = noticeService.updateByPrimaryKeySelective(record); // 执行更新
		//返回结果
		if(status>0){
			retJsonUtil.setMessage("更新成功");
			retJsonUtil.setStatus("1");
		}else{
			retJsonUtil.setMessage("更新失败");
			retJsonUtil.setStatus("0");
		}
		out.write(retJsonUtil.getRetJsonM());
	}
}
