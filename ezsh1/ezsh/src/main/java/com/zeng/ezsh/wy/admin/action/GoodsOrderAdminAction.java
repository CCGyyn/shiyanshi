package com.zeng.ezsh.wy.admin.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zeng.ezsh.wy.entity.GoodsOrder;
import com.zeng.ezsh.wy.entity.GoodsOrderDetails;
import com.zeng.ezsh.wy.service.GoodsOrderService;
import com.zeng.ezsh.wy.utils.AccessTokenUtil;
import com.zeng.ezsh.wy.utils.RetJsonUtil;
/**
 * 
 * @author quanweicong
 * 商品后台订单控制器
 */
@Controller
@RequestMapping("orderAd")
public class GoodsOrderAdminAction {
	private Logger logger=Logger.getLogger(GoodsOrderAdminAction.class);
	@Resource
	GoodsOrderService goodsOrderService;
	
	/**
	 * @author qwc
	 * 2017年10月17日下午8:34:07
	 * @param session
	 * @param request
	 * @param response
	 * @param startPage
	 * @param pageSize
	 * @throws IOException 
	 * void 后台获取商品订单集合
	 */
	@RequestMapping("gtGoodsOrderAd")
	public void getGoodsOrderByUserId(GoodsOrder goodsOrder, HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value="page",required=true,defaultValue="1") Integer startPage, 
			@RequestParam(value="rows",required=false,defaultValue="10") Integer pageSize,
			@RequestParam(value="startTime",required=false) String startTime,
			@RequestParam(value="endTime",required=false) String endTime) throws IOException{
		PrintWriter out=response.getWriter();
		RetJsonUtil retJsonUtil = new RetJsonUtil();// 创建返回json对象
	
		Map<String, Object> paramMap = new HashMap<String, Object>();// 查询条件
		List<GoodsOrder> goodsOrdersList=new ArrayList<GoodsOrder>();// 存放获取到的订单集合
		String condition=request.getParameter("condition");
		System.out.println("condition>>"+condition);
		// 设置查询条件
		if(!StringUtils.isBlank(condition) && Integer.parseInt(condition) == 1){// 未支付
			System.out.println("condition1>>"+condition);
			paramMap.put("payStatus", 0);
		}
		if(!StringUtils.isBlank(condition) && Integer.parseInt(condition) == 2){// 待收货
			paramMap.put("orderStatus", 2);
		}
		if(!StringUtils.isBlank(condition) && Integer.parseInt(condition) == 3){// 未评价
			paramMap.put("appraiseStatus", 0);
		}
		if(!StringUtils.isBlank(condition) && Integer.parseInt(condition) == 4){// 退货订单
			paramMap.put("afterSaleStatus", 2);
		}
		if(!StringUtils.isBlank(startTime)){// 设置时间范围的起始时间
			paramMap.put("startTime", startTime);
		}
		if(!StringUtils.isBlank(endTime)){// 设置时间范围的结束时间
			paramMap.put("endTime", endTime);
		}
		
		if(goodsOrder.getpUserId() != null){// 设置搜索的用户ID
			
			paramMap.put("pUserId", goodsOrder.getpUserId());
		}
		
		// 执行查询
		PageHelper.startPage(startPage, pageSize);
		goodsOrdersList=goodsOrderService.getGoodsOrderDetailsListByParam(paramMap);
		PageInfo<GoodsOrder> page=new PageInfo<GoodsOrder>(goodsOrdersList);
		
		// 返回结果
		retJsonUtil.setTotal(page.getTotal());
		retJsonUtil.setList(goodsOrdersList);
		out.write(retJsonUtil.getRetJsonEasyGrid());
	}
	
	/**
	 * @author qwc 2017年8月13日下午10:46:29
	 * void 商家接受退货申请 
	 */
	@RequestMapping("handelRef")
	public void handleRefund(HttpServletRequest request, 
			HttpServletResponse response) {
		String method = request.getParameter("method");
		GoodsOrderDetails goodsOrderDetails = new GoodsOrderDetails();
		int updateStatus = 0;
		if (method == null) {

		} else if (method.equals("recieve")) {
			goodsOrderDetails.setAfterSaleStatus(2);
			updateStatus = goodsOrderService
					.updateOrderDetails(goodsOrderDetails);
		} else if (method.equals("confirm")) {
			goodsOrderDetails.setAfterSaleStatus(3);
			updateStatus = goodsOrderService
					.updateOrderDetails(goodsOrderDetails);
		}
		if (updateStatus > 0) {
			logger.info("更新成功！");
		} else {
			logger.info("更新失败！");
		}
	}

}
