package com.zeng.ezsh.wy.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zeng.ezsh.wy.entity.GoodsDistribution;
import com.zeng.ezsh.wy.entity.GoodsInfo;
import com.zeng.ezsh.wy.entity.ResidentialUser;
import com.zeng.ezsh.wy.service.GoodsDistributionService;
import com.zeng.ezsh.wy.service.GoodsInfoService;
import com.zeng.ezsh.wy.service.GoodsService;
import com.zeng.ezsh.wy.utils.AccessTokenUtil;
import com.zeng.ezsh.wy.utils.RetJsonUtil;

@Controller
@RequestMapping("merchandise")
public class GoodsMAction {
	private Logger logger = Logger.getLogger("GoodsAction");
	@Resource
	GoodsService goodsService;
	@Resource
	GoodsInfoService goodsInfoService;
	@Resource
	GoodsDistributionService goodsDistributionService;

	/**
	 * @author qwc 2017年7月28日下午6:50:42 void 根据条件获取商品数据
	 * @throws IOException
	 */
	@RequestMapping("gtGoodsInfo")
	public void getGoods(HttpServletRequest request,
			HttpServletResponse response,
			@RequestParam(value = "startPage", required = true, defaultValue = "1") Integer startPage,
			@RequestParam(value = "pageSize", required = false, defaultValue = "10") Integer pageSize)
			throws IOException {
		PrintWriter out = response.getWriter();
		Map<String, Object> paramMap = new HashMap<String, Object>();// 查询条件
		Map<String, Object> retMap = new HashMap<String, Object>();
		RetJsonUtil retJsonUtil = new RetJsonUtil();
		List<GoodsInfo> goodsInfoList = new ArrayList<GoodsInfo>();

		String showClassfy = request.getParameter("showClassfy");// 展示分类
		String keyWord = request.getParameter("keyWord");// 搜索关键字
		String hotKeyWord = request.getParameter("hotKeyWord");// 热门搜索关键字
		String orderBy = request.getParameter("orderBy");

		paramMap.put("showClassfy", showClassfy);// 1-普通 2-每日推荐 3-优惠活动
		paramMap.put("keyWord", keyWord);
		if (orderBy != null && orderBy.equals("synthesize")) {
			paramMap.put("orderBy", "synthesize");
		}
		if (orderBy != null && orderBy.equals("highPrice")) {
			paramMap.put("orderBy", "highPrice");
		}
		if (orderBy != null && orderBy.equals("lowPrice")) {
			paramMap.put("orderBy", "lowPrice");
		}
		if (hotKeyWord != null) {
			if (hotKeyWord.equals("棉衣女短款")) {
				hotKeyWord = "14";
			}
			if (hotKeyWord.equals("美容护肤")) {
				hotKeyWord = "14";
			}
			if (hotKeyWord.equals("增肌粉")) {
				hotKeyWord = "14";
			}
			if (hotKeyWord.equals("台式机")) {
				hotKeyWord = "14";
			}
			if (hotKeyWord.equals("纸巾")) {
				hotKeyWord = "21";
			}
			if (hotKeyWord.equals("保温杯")) {
				hotKeyWord = "22";
			}
			if (hotKeyWord.equals("暖手宝")) {
				hotKeyWord = "23";
			}
			if (hotKeyWord.equals("胸针")) {
				hotKeyWord = "24";
			}
			if (hotKeyWord.equals("沙发")) {
				hotKeyWord = "26";
			}
		}

		paramMap.put("hotKeyWord", hotKeyWord);

		// token解析
		String token = request.getParameter("token");
		ResidentialUser residentialUser = AccessTokenUtil
				.parserAccessTokenToModel(token);
		
		if (residentialUser.getUmsIdsInfo() == null) {
			retJsonUtil.setStatus("-1");
			retJsonUtil.setMessage("此账号未有小区通过审核");
			out.write(retJsonUtil.getRetJsonM());
			return;
		}
		
		int managerId = residentialUser.getUmsIdsInfo().getpManagerId();
		if (managerId == 0) {
			retJsonUtil.setStatus("0");
			retJsonUtil.setMessage("获取失败，用户不属于任何小区");
			retJsonUtil.setRetMap(retMap);
			out.write(retJsonUtil.getRetJsonM());
			return;
		} else {
			paramMap.put("managerId", managerId);
		}

		// 执行分页获取
		PageHelper.startPage(startPage, pageSize);// 分页获取
		goodsInfoList = goodsInfoService.getGoodsInfoListByParam(paramMap);// 获取
		PageInfo<GoodsInfo> page = new PageInfo<GoodsInfo>(goodsInfoList);

		// 返回结果
		retMap.put("goodsInfo", goodsInfoList);
		retMap.put("totalPage", page.getPages());
		retJsonUtil.setStatus("1");
		retJsonUtil.setMessage("获取成功");
		retJsonUtil.setRetMap(retMap);
		out.write(retJsonUtil.getRetJsonM());
	}

	/**
	 * @author qwc 2017年8月17日下午7:48:23 void 根据goodsInfoId获取商品详细信息
	 * @throws IOException
	 */
	@RequestMapping("gtGoodsDetails")
	public void getGoodsDetailsInfoByGoodsInfoId(HttpServletResponse response,
			HttpServletRequest request,
			@RequestParam(value = "goodsInfoId") int goodsInfoId)
			throws IOException {
		PrintWriter out = response.getWriter();
		RetJsonUtil retJsonUtil = new RetJsonUtil();

		GoodsInfo goodsInfo = new GoodsInfo();
		goodsInfo = goodsInfoService.getGoodsDetailsByGoodsInfoId(goodsInfoId);
		// 获取配送方式
		String goodsDistribution = goodsInfo.getGoodsDistribution();
		String[] goodsDistributionArray = StringUtils.split(goodsDistribution,
				",");
		List<GoodsDistribution> goodsDistributionsList = goodsDistributionService
				.getDistributionListById(goodsDistributionArray);
		goodsInfo.setGoodsDistributionsList(goodsDistributionsList);

		// 封装返回结果
		Map<String, Object> retMap = new HashMap<String, Object>();
		retMap.put("goodsInfo", goodsInfo);
		retJsonUtil.setStatus("1");
		retJsonUtil.setMessage("获取成功");
		retJsonUtil.setRetMap(retMap);
		out.write(retJsonUtil.getRetJsonM());
	}

}
