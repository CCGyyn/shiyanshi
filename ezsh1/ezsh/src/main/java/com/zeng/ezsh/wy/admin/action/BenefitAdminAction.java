package com.zeng.ezsh.wy.admin.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zeng.ezsh.wy.entity.BenefitRecord;
import com.zeng.ezsh.wy.entity.GridRecord;
import com.zeng.ezsh.wy.service.BenefitRecordService;
import com.zeng.ezsh.wy.utils.RetJsonUtil;
/**
 * 
 * @author quanweicong
 * 公益基金后台action
 */
@Controller
@RequestMapping(value = "benefitA")
public class BenefitAdminAction {
	@Resource
	BenefitRecordService benefitRecordService;
	/**
	 * @author qwc
	 * 2017年12月6日下午9:13:42
	 * @param response
	 * @param record
	 * @param startPage
	 * @param pageSize
	 * @throws IOException 
	 * void 分页按条件获取公益基金记录
	 */
	@RequestMapping(value = "select")
	public  void selectGridRecord(HttpServletResponse response, BenefitRecord record,
			@RequestParam(value = "page", required = true, defaultValue = "1") Integer startPage,
			@RequestParam(value = "rows", required = false, defaultValue = "10") Integer pageSize) throws IOException{
		PrintWriter out = response.getWriter();
		RetJsonUtil retJsonUtil = new RetJsonUtil(); 
		if(record.getPtManagerId() == null) {
			retJsonUtil.setTotal(0);
			out.write(retJsonUtil.getRetJsonEasyGrid());
			return;
		}
		
		//分页获取
		PageHelper.startPage(startPage, pageSize);
		List<BenefitRecord> list = benefitRecordService.selectListByParamA(record);
		PageInfo<BenefitRecord> page = new PageInfo<BenefitRecord>(list);
		
		//返回结果
		retJsonUtil.setTotal(page.getTotal());
		retJsonUtil.setList(list);
		out.write(retJsonUtil.getRetJsonEasyGrid());
	}
}
