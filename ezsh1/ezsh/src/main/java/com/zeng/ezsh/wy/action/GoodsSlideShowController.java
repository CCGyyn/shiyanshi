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

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zeng.ezsh.wy.entity.GoodsSlideShow;
import com.zeng.ezsh.wy.entity.ResidentialUser;
import com.zeng.ezsh.wy.service.GoodsSlideShowService;
import com.zeng.ezsh.wy.utils.AccessTokenUtil;
import com.zeng.ezsh.wy.utils.RetJsonUtil;
/**
 * @description 获取轮播图操作
 *
 * @author qwc
 */
@Controller
@RequestMapping("slideShow")
public class GoodsSlideShowController {
	@Resource
	GoodsSlideShowService goodsSlideShowService;

	/**
	 * @description 按小区获取轮播图列表
	 *
	 * @auhtor qwc 2017年8月22日上午11:12:32
	 * @param request
	 * @param response
	 * @param startPage
	 * @param pageSize
	 * @throws IOException void
	 */
	@RequestMapping("gtSlideShow")
	public void getSlideShowImg(HttpServletRequest request,
			HttpServletResponse response,
			@RequestParam(value = "startPage", required = true, defaultValue = "1") Integer startPage,
			@RequestParam(value = "pageSize", required = false, defaultValue = "10") Integer pageSize)
			throws IOException {
		PrintWriter out = response.getWriter();
		Map<String, Object> retMap = new HashMap<String, Object>();
		RetJsonUtil retJsonUtil = new RetJsonUtil();

		// token解析
		String accessToken = request.getParameter("token");// 获取登录token
		ResidentialUser residentialUser = AccessTokenUtil
				.parserAccessTokenToModel(accessToken);
		// 检测该账号下是否有小区通过审核
		if (residentialUser.getUmsIdsInfo() == null) {
			retJsonUtil.setStatus("-1");
			retJsonUtil.setMessage("此账号未有小区通过审核");
			out.write(retJsonUtil.getRetJsonM());
			return;
		}

		int managerId = residentialUser.getUmsIdsInfo().getpManagerId();
		// 分页查询
		Map<String, Object> paramMap = new HashMap<String, Object>(); // 查询条件
		List<GoodsSlideShow> goodsSlideShowsList = new ArrayList<GoodsSlideShow>();

		paramMap.put("managerId", managerId);
		PageHelper.startPage(startPage, pageSize);
		goodsSlideShowsList = goodsSlideShowService
				.getGoodsSlideShowsByParam(paramMap);
		PageInfo<GoodsSlideShow> page = new PageInfo<GoodsSlideShow>(
				goodsSlideShowsList);

		// 返回结果
		retMap.put("goodsSlideShowsList", goodsSlideShowsList);
		retMap.put("totalPage", page.getPages());
		retJsonUtil.setMessage("获取成功");
		retJsonUtil.setStatus("1");
		retJsonUtil.setRetMap(retMap);
		out.write(retJsonUtil.getRetJsonM());
	}

}
