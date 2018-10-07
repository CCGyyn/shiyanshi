package com.zeng.ezsh.wy.admin.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zeng.ezsh.wy.entity.News;

import com.zeng.ezsh.wy.service.NewsService;
import com.zeng.ezsh.wy.service.NoticeService;
import com.zeng.ezsh.wy.utils.RetJsonUtil;
/**
 * 个人消息
 * @author quanweicong
 *
 */
@Controller
@RequestMapping(value="news")
public class NewsAdminAction {
	@Resource
	NewsService newsService;
	
	/**
	 * @author qwc
	 * 2017年10月26日下午9:15:06
	 * @param request
	 * @param response
	 * @param record
	 * @throws IOException 
	 * void 添加个人信息
	 */
	@RequestMapping(value = "add")
	public void addNews(HttpServletRequest request,HttpServletResponse response,
			News record) throws IOException{
		PrintWriter out = response.getWriter();
		RetJsonUtil retJsonUtil = new RetJsonUtil();
		
		//设置添加时间
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String dateNowStr = sdf.format(date); 
		record.setNewsTime(dateNowStr);
		
		int status = newsService.insert(record);
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
	 * void 删除个人信息
	 */
	@RequestMapping(value = "delete")
	public void deleteNotice(HttpServletRequest request,HttpServletResponse response,
			News record) throws IOException{
		PrintWriter out = response.getWriter();
		RetJsonUtil retJsonUtil = new RetJsonUtil();
		
		int status = newsService.deleteByPrimaryKey(record);
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
	 * void 获取个人信息
	 */
	@RequestMapping(value = "select")
	public void selectNews(HttpServletRequest request,HttpServletResponse response,News record,
			@RequestParam(value="page",required=true,defaultValue="1") Integer startPage,
			@RequestParam(value="rows",required=false,defaultValue="10") Integer pageSize) throws IOException{
		PrintWriter out = response.getWriter();
		RetJsonUtil retJsonUtil = new RetJsonUtil();
		
		//分页获取
		PageHelper.startPage(startPage, pageSize);
		List<News> list=newsService.selectByPrimaryKey(record);
		PageInfo<News> page = new PageInfo<News>(list);
		
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
	 * void 更新个人信息
	 */
	@RequestMapping(value = "update")
	public void updatNews(HttpServletRequest request,HttpServletResponse response,
			News record) throws IOException{
		PrintWriter out = response.getWriter();
		RetJsonUtil retJsonUtil = new RetJsonUtil();
		
		int status = newsService.updateByPrimaryKeySelective(record); // 执行更新
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
