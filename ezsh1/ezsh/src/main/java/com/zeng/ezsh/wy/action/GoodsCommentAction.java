package com.zeng.ezsh.wy.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zeng.ezsh.wy.entity.GoodsAppraise;
import com.zeng.ezsh.wy.entity.GoodsComment;
import com.zeng.ezsh.wy.entity.ResidentialUser;
import com.zeng.ezsh.wy.service.GoodsCommentService;
import com.zeng.ezsh.wy.utils.AccessTokenUtil;
import com.zeng.ezsh.wy.utils.DateUtil;
import com.zeng.ezsh.wy.utils.RetJsonUtil;

@Controller
@RequestMapping("gComment")
public class GoodsCommentAction {
	private Logger logger = Logger.getLogger(GoodsCommentAction.class);
	@Resource
	GoodsCommentService goodsCommentService;
	
	/**
	 * @author qwc
	 * 2017年12月17日下午8:41:32
	 * @param request
	 * @param response
	 * @throws IOException 
	 * void 添加评价回复
	 */
	@RequestMapping("addComment")
	public void appendComment(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		PrintWriter out = response.getWriter();
		RetJsonUtil retJsonUtil = new RetJsonUtil();

		// token解析
		String token = request.getParameter("token");
		ResidentialUser residentialUser = AccessTokenUtil
				.parserAccessTokenToModel(token);

		String gCommentContent = request.getParameter("commentContent");
		int appraiseId = Integer.parseInt(request.getParameter("appraiseId"));

		GoodsComment goodsappraiseComment = new GoodsComment();
		goodsappraiseComment.setpAppraiseId(appraiseId);
		goodsappraiseComment.setpUserId(residentialUser.getUserId());
		goodsappraiseComment.setCommentContent(gCommentContent);
		goodsappraiseComment.setCommentTime(DateUtil.dateToStr(new Date(),
				DateUtil.DATE_HM));

		// 添加评价回复
		int addGoodsComment = goodsCommentService
				.addComment(goodsappraiseComment);

		// 返回结果
		if (addGoodsComment > 0) {
			retJsonUtil.setStatus("1");
			retJsonUtil.setMessage("回复成功");
		} else {
			retJsonUtil.setStatus("0");
			retJsonUtil.setMessage("回复成功");
		}
		out.write(retJsonUtil.getRetJsonM());
	}

	/**
	 * @author qwc 2017年8月8日下午10:06:17
	 * @param request
	 * @param response
	 * @param startPage
	 * @param pageSize
	 * @throws IOException
	 * void 获取商品评价的回复集合
	 */
	@RequestMapping("gtCommentList")
	public void getCommentList(
			HttpServletRequest request,
			HttpServletResponse response,
			@RequestParam(value = "startPage", required = true, defaultValue = "1") Integer startPage,
			@RequestParam(value = "pageSize", required = false, defaultValue = "10") Integer pageSize)
			throws IOException {
		PrintWriter out = response.getWriter();
		RetJsonUtil retJsonUtil = new RetJsonUtil();

		// token解析
		String token = request.getParameter("token");
		ResidentialUser residentialUser = AccessTokenUtil
				.parserAccessTokenToModel(token);
		
		// 设置查询条件
		GoodsComment goodsCommentModel = new GoodsComment();
		int appraiseId = Integer.parseInt(request.getParameter("appraiseId"));
		goodsCommentModel.setpAppraiseId(appraiseId);
		goodsCommentModel.setpUserId(residentialUser.getUserId());
		
		// 分页获取
		List<GoodsComment> goodsCommentList = new ArrayList<GoodsComment>();
		PageHelper.startPage(startPage, pageSize);
		goodsCommentList = goodsCommentService
				.selectGoodsCommentList(goodsCommentModel);
		PageInfo<GoodsComment> page = new PageInfo<GoodsComment>(
				goodsCommentList);
		
		// 返回结果
		Map<String, Object> retMap = new HashMap<String, Object>();
		retMap.put("tatalPage", page.getPages());
		retMap.put("goodsCommentForAppraiseList", goodsCommentList);
		retJsonUtil.setRetMap(retMap);
		retJsonUtil.setMessage("获取成功");
		retJsonUtil.setStatus("1");
		out.write(retJsonUtil.getRetJsonM());
	}
}
