package com.zeng.ezsh.wy.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zeng.ezsh.wy.entity.BenefitApplyWithBLOBs;
import com.zeng.ezsh.wy.entity.ChargeInfo;
import com.zeng.ezsh.wy.entity.ResidentialUser;
import com.zeng.ezsh.wy.service.BenefitApplyService;
import com.zeng.ezsh.wy.utils.AccessTokenUtil;
import com.zeng.ezsh.wy.utils.RetJsonUtil;
import com.zeng.ezsh.wy.utils.UploadFileUtil;
/**
 * @description 公益基金申请操作类
 *
 * @author qwc
 */
@Controller
@RequestMapping("benefitApplyM")
public class BenefitApplyMAction {
	@Resource
	BenefitApplyService benefitApplyService;

	/**
	 * @description 提交公益基金申请（移动端）
	 *
	 * @author qwc 2017年12月6日下午10:05:29
	 * @param request
	 * @param response
	 * @param applyImgs 证明材料图片文件
	 * @param record
	 * @throws IOException void
	 */
	@RequestMapping("add")
	public void add(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value = "applyImgs", required = false) MultipartFile[] applyImgs,
			BenefitApplyWithBLOBs record) throws IOException {
		PrintWriter out = response.getWriter();
		// 构建json返回对象
		RetJsonUtil retJsonUtil = new RetJsonUtil();

		// token解析
		String token = request.getParameter("token");
		ResidentialUser residentialUser = AccessTokenUtil
				.parserAccessTokenToModel(token);

		if (applyImgs != null && applyImgs.length != 0) {
			String imgUrls = UploadFileUtil.fileUploadImgs(applyImgs,
					"benefitApplyImgs", residentialUser.getUserId());
			record.setIdentificationImgs(imgUrls);
		}
		record.setPtManagerId(residentialUser.getUmsIdsInfo().getpManagerId());
		record.setPtUserId(residentialUser.getUserId());

		// 提交申请
		int status = benefitApplyService.insertSelective(record);

		// 封装返回结果
		if (status > 0) {
			retJsonUtil.setMessage("提交成功");
			retJsonUtil.setStatus("1");
		} else {
			retJsonUtil.setMessage("提交失败");
			retJsonUtil.setStatus("0");
		}
		out.write(retJsonUtil.getRetJsonM());
	}

	/**
	 * @author qwc 2017年12月6日下午10:06:47
	 * @param request
	 * @param response
	 * @param record
	 * @throws IOException void 再次提交申请（审核失败后修改资料再次提交）【移动端】
	 */
	@RequestMapping("update")
	public void updateApply(HttpServletRequest request,
			HttpServletResponse response, BenefitApplyWithBLOBs record)
			throws IOException {
		PrintWriter out = response.getWriter();
		// 构建json返回对象
		RetJsonUtil retJsonUtil = new RetJsonUtil();

		// token解析
		String token = request.getParameter("token");
		ResidentialUser residentialUser = AccessTokenUtil
				.parserAccessTokenToModel(token);
		record.setPtUserId(residentialUser.getUserId());
		record.setCheckStatus(0);

		// 执行审核操作
		int status = benefitApplyService
				.updateByPrimaryKeySelectiveForAgain(record);
		if (status > 0) {
			retJsonUtil.setMessage("操作成功");
			retJsonUtil.setStatus("1");
		} else {
			retJsonUtil.setMessage("操作失败");
			retJsonUtil.setStatus("0");
		}
		out.write(retJsonUtil.getRetJsonM());
	}

	/**
	 * @description 获取个人公益基金申请记录集合
	 *
	 * @author qwc 2017年12月6日下午10:08:37
	 * @param request
	 * @param response
	 * @param startPage 起始页
	 * @param pageSize 每天记录大小
	 * @param record
	 * @throws IOException void
	 */
	@RequestMapping("select")
	public void select(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value = "startPage", required = true, defaultValue = "1") Integer startPage,
			@RequestParam(value = "pageSize", required = false, defaultValue = "10") Integer pageSize,
			BenefitApplyWithBLOBs record) throws IOException {
		PrintWriter out = response.getWriter();
		// 构建json返回对象
		RetJsonUtil retJsonUtil = new RetJsonUtil();

		// token解析
		String token = request.getParameter("token");
		ResidentialUser residentialUser = AccessTokenUtil
				.parserAccessTokenToModel(token);
		record.setPtUserId(residentialUser.getUserId());

		// 执行审核操作
		List<BenefitApplyWithBLOBs> list = new ArrayList<BenefitApplyWithBLOBs>();// 创建查询结果保存对象
		PageHelper.startPage(startPage, pageSize);// 分页获取
		list = benefitApplyService.selectListByPrimaryKey(record);// 获取
		PageInfo<BenefitApplyWithBLOBs> page = new PageInfo<BenefitApplyWithBLOBs>(
				list);

		// 封装返回结果
		Map<String, Object> retMap = new HashMap<String, Object>();
		retMap.put("benefitApplyList", list);
		retMap.put("totalPage", page.getPages());
		retJsonUtil.setStatus("1");
		retJsonUtil.setMessage("获取成功");
		retJsonUtil.setRetMap(retMap);
		out.write(retJsonUtil.getRetJsonM());
	}
}
