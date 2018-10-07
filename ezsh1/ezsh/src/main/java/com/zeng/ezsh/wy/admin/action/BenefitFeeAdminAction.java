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
import com.zeng.ezsh.wy.entity.BenefitFee;
import com.zeng.ezsh.wy.entity.Grid;
import com.zeng.ezsh.wy.service.BenefitFeeService;
import com.zeng.ezsh.wy.utils.RetJsonUtil;

@Controller
@RequestMapping(value = "benefitFeeA")
public class BenefitFeeAdminAction {
	@Resource
	BenefitFeeService benefitFeeService;

	@RequestMapping(value = "select")
	public void selectGrid(
			HttpServletRequest request,
			HttpServletResponse response,
			BenefitFee benefitFee,
			@RequestParam(value = "page", required = true, defaultValue = "1") Integer startPage,
			@RequestParam(value = "rows", required = false, defaultValue = "10") Integer pageSize)
			throws IOException {
		PrintWriter out = response.getWriter();
		RetJsonUtil retJsonUtil = new RetJsonUtil();// 创建json返回对象

		PageHelper.startPage(startPage, pageSize);// 分页获取
		List<BenefitFee> list = benefitFeeService.selectListByPrimaryKey(benefitFee);
		PageInfo<BenefitFee> page = new PageInfo<BenefitFee>(list);

		// 返回结果
		retJsonUtil.setList(list);
		retJsonUtil.setTotal(page.getTotal());
		out.write(retJsonUtil.getRetJsonEasyGrid());
	}
}
