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
import com.zeng.ezsh.wy.entity.BenefitApply;
import com.zeng.ezsh.wy.entity.BenefitApplyWithBLOBs;
import com.zeng.ezsh.wy.entity.ResidentialUser;
import com.zeng.ezsh.wy.entity.Teacher;
import com.zeng.ezsh.wy.service.BenefitApplyService;
import com.zeng.ezsh.wy.utils.AccessTokenUtil;
import com.zeng.ezsh.wy.utils.RetJsonUtil;

@Controller
@RequestMapping("benefitApplyA")
public class BenefitApplyAdminAction {
	@Resource
	BenefitApplyService benefitApplyService;

	/**
	 * @author qwc 2017年12月7日下午3:14:44
	 * @param request
	 * @param response
	 * @param record
	 * @param token
	 * @param startPage
	 * @param pageSize
	 * @throws IOException
	 * void 后台获取公益基金申请列表集合
	 */
	@RequestMapping(value = "select")
	public void getList(
			HttpServletRequest request,
			HttpServletResponse response,
			BenefitApplyWithBLOBs record,
			@RequestParam(value = "page", required = false, defaultValue = "1") Integer startPage,
			@RequestParam(value = "rows", required = false, defaultValue = "10") Integer pageSize)
			throws IOException {
		PrintWriter out = response.getWriter();
		RetJsonUtil retJsonUtil = new RetJsonUtil();
		
		// 管理处ID为空时不执行查询
		if(record.getPtManagerId() == null) {
			retJsonUtil.setTotal(0);
			out.write(retJsonUtil.getRetJsonEasyGrid());
			return;
		}

		// 执行查询
		PageHelper.startPage(startPage, pageSize);// 分页获取
		List<BenefitApplyWithBLOBs> list = benefitApplyService
				.selectByPrimaryKey(record);
		PageInfo<BenefitApplyWithBLOBs> page = new PageInfo<BenefitApplyWithBLOBs>(
				list);

		// 返回结果
		retJsonUtil.setList(list);
		retJsonUtil.setTotal(page.getTotal());
		out.write(retJsonUtil.getRetJsonEasyGrid());
	}

	/**
	 * @author qwc
	 * 2017年11月21日下午8:21:08
	 * @param request
	 * @param response
	 * @param record
	 * @param checkStatus
	 * @throws IOException 
	 * void 后台审核公益基金
	 */
	@RequestMapping("check")
	public void check(
			HttpServletRequest request,
			HttpServletResponse response,
			BenefitApplyWithBLOBs record,
			@RequestParam(value = "checkStatus", required = true, defaultValue = "0") Integer checkStatus)
			throws IOException {
		PrintWriter out = response.getWriter();
		Subject subject = SecurityUtils.getSubject();
		RetJsonUtil retJsonUtil = new RetJsonUtil();
		
		if(record.getPtManagerId() == null) {
			retJsonUtil.setMessage("请选取小区");
			retJsonUtil.setStatus("0");
			out.write(retJsonUtil.getRetJsonM());
			return;
		}
		
		// 执行审核操作
		int status = benefitApplyService
				.updateByPrimaryKeySelectiveForCheck(record);
		if (status == 1) {
			retJsonUtil.setMessage("操作成功");
			retJsonUtil.setStatus("1");
		} else if (status == 2){
			retJsonUtil.setMessage("已通过审核，不能重复操作");
			retJsonUtil.setStatus("2");
		}else if (status == -1){
			retJsonUtil.setMessage("操作失败,请联系管理员");
			retJsonUtil.setStatus("-1");
		} else {
			retJsonUtil.setMessage("操作失败");
			retJsonUtil.setStatus("0");
		}
		out.write(retJsonUtil.getRetJsonM());
	}
}
