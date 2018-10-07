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

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zeng.ezsh.wy.entity.GoodsShoppingCart;
import com.zeng.ezsh.wy.entity.ResidentialUser;
import com.zeng.ezsh.wy.service.GoodsShoppingCartService;
import com.zeng.ezsh.wy.utils.AccessTokenUtil;
import com.zeng.ezsh.wy.utils.RetJsonUtil;
/**
 * @description 购物车操作
 *
 * @author qwc
 */
@Controller
@RequestMapping("gCart")
public class GoodsShoppingCartAction {
	@Resource
	GoodsShoppingCartService goodsShoppingCartService;

	/**
	 * @description 加入购物车
	 *
	 * @auhtor qwc 2017年8月23日下午2:13:27
	 * @param request
	 * @param response
	 * @throws IOException void
	 */
	@RequestMapping("addGCart")
	public void addGoodsCart(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		PrintWriter out = response.getWriter();
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
		// 获取商户ID
		int merchantId = Integer.parseInt(request.getParameter("merchantId"));

		// 设置属性值
		String goodsId = request.getParameter("goodsId"); //
		String goodsAmount = request.getParameter("goodsAmount"); // 数量
		GoodsShoppingCart goodsShoppingCart = new GoodsShoppingCart();
		goodsShoppingCart.setGoodsAmount(Integer.parseInt(goodsAmount));
		goodsShoppingCart.setpGoodsId(Integer.parseInt(goodsId));
		goodsShoppingCart.setpUserId(residentialUser.getUserId());
		goodsShoppingCart
				.setpManagerId(residentialUser.getUmsIdsInfo().getpManagerId());
		goodsShoppingCart.setPtMerchantId(merchantId);

		// 执行添加
		int addStatus = goodsShoppingCartService
				.insertShoppingCart(goodsShoppingCart);

		// 返回结果
		if (addStatus > 0) {
			retJsonUtil.setMessage("加入成功");
			retJsonUtil.setStatus("1");
		} else {
			retJsonUtil.setStatus("0");
			retJsonUtil.setMessage("加入失败");
		}
		out.write(retJsonUtil.getRetJsonM());
	}

	/**
	 * @description 移除购物车
	 *
	 * @auhtor qwc 2017年8月23日下午2:13:27
	 * @param request
	 * @param response
	 * @throws IOException void
	 */
	@RequestMapping("delGCart")
	public void deleteGoodsCart(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		PrintWriter out = response.getWriter();
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

		int managerId = residentialUser.getUmsIdsInfo().getpManagerId(); // 用户所在小区ID
		int userId = residentialUser.getUserId(); // 用户ID

		String shoppingCartId = request.getParameter("shoppingCartId");//
		String[] shoppingCartIdArray = StringUtils.split(shoppingCartId, ",");
		List<GoodsShoppingCart> goodsShoppingCartList = new ArrayList<GoodsShoppingCart>();
		for (int i = 0; i < shoppingCartIdArray.length; i++) {
			GoodsShoppingCart goodsShoppingCart = new GoodsShoppingCart();
			goodsShoppingCart.setShoppingCartId(
					Integer.parseInt(shoppingCartIdArray[i]));
			goodsShoppingCart.setpUserId(userId);
			goodsShoppingCart.setpManagerId(managerId);
			goodsShoppingCartList.add(goodsShoppingCart);
		}

		// 执行移除操作
		int status = goodsShoppingCartService
				.deleteShoppingCart(goodsShoppingCartList);
		// 封装返回结果
		if (status > 0) {
			retJsonUtil.setStatus("1");
			retJsonUtil.setMessage("移除成功");
		} else {
			retJsonUtil.setStatus("0");
			retJsonUtil.setMessage("移除失败");
		}
		out.write(retJsonUtil.getRetJsonM());
	}

	/**
	 * @description 获取购物车商品
	 *
	 * @auhtor qwc 2017年8月23日下午3:36:25
	 * @param request
	 * @param response
	 * @param startPage
	 * @param pageSize
	 * @throws IOException void
	 */
	@RequestMapping("gtSCart")
	public void getShoppingCart(HttpServletRequest request,
			HttpServletResponse response,
			@RequestParam(value = "startPage", required = true, defaultValue = "1") Integer startPage,
			@RequestParam(value = "pageSize", required = false, defaultValue = "10") Integer pageSize)
			throws IOException {
		PrintWriter out = response.getWriter();
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
		int managerId = residentialUser.getUmsIdsInfo().getpManagerId();// 用户所在小区ID
		int userId = residentialUser.getUserId();// 用户ID

		// 执行分页查询
		Map<String, Object> paramMap = new HashMap<String, Object>();// 查询条件
		List<GoodsShoppingCart> goodsShoppingCartList = new ArrayList<GoodsShoppingCart>();
		paramMap.put("userId", userId);
		paramMap.put("managerId", managerId);
		PageHelper.startPage(startPage, pageSize);
		goodsShoppingCartList = goodsShoppingCartService
				.selectShoppingCart(paramMap);
		PageInfo<GoodsShoppingCart> page = new PageInfo<GoodsShoppingCart>(
				goodsShoppingCartList);

		// 返回结果
		Map<String, Object> retMap = new HashMap<String, Object>();
		retMap.put("totalPage", page.getPages());
		retMap.put("goodsShoppingCartList", goodsShoppingCartList);
		retJsonUtil.setMessage("获取成功");
		retJsonUtil.setStatus("1");
		retJsonUtil.setRetMap(retMap);
		out.write(retJsonUtil.getRetJsonM());
	}

}
