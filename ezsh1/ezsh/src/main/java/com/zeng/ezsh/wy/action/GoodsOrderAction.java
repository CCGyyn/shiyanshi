package com.zeng.ezsh.wy.action;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import com.zeng.ezsh.wy.entity.Goods;
import com.zeng.ezsh.wy.entity.GoodsOrder;
import com.zeng.ezsh.wy.entity.GoodsOrderDetails;
import com.zeng.ezsh.wy.entity.ResidentialUser;
import com.zeng.ezsh.wy.service.GoodsOrderService;
import com.zeng.ezsh.wy.service.GoodsService;
import com.zeng.ezsh.wy.service.ResidentialUserService;
import com.zeng.ezsh.wy.utils.AccessTokenUtil;
import com.zeng.ezsh.wy.utils.DateUtil;
import com.zeng.ezsh.wy.utils.GetIpAdrr;
import com.zeng.ezsh.wy.utils.RetJsonUtil;
import com.zeng.ezsh.wy.utils.ShoppingMallUtil;
/**
 * @description 商品订单操作控制器
 *
 * @author qwc
 */
@Controller
@RequestMapping("order")
public class GoodsOrderAction {
	private Logger logger = Logger.getLogger("GoodsOrderAction");
	@Resource
	GoodsOrderService goodsOrderService;
	@Resource
	GoodsService goodsService;
	@Resource
	ResidentialUserService residentialUserService;
	/**
	 * @description 创建订单
	 *
	 * @auhtor qwc 2017年7月28日下午9:11:36
	 * @param goodsOrder 商品订单对象
	 * @param session
	 * @param request
	 * @param response
	 * @param payMethod 支付方法
	 * @param merchantId 商家ID
	 * @throws IOException
	 * @return void
	 */
	@RequestMapping("ctGoodsOrder")
	public void createGoodsOrder(GoodsOrder goodsOrder, HttpSession session,
			HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value = "payMethod", defaultValue = "aliPay") String payMethod,
			@RequestParam(value = "merchantId") int merchantId,
			@RequestParam(value = "userIntegral", required = false) BigDecimal userIntegral)
			throws IOException {
		PrintWriter out = response.getWriter();
		RetJsonUtil retJsonUtil = new RetJsonUtil();

		// 获取登录token
		String accessToken = request.getParameter("token");
		// 对token进行解析转化
		ResidentialUser residentialUser = AccessTokenUtil
				.parserAccessTokenToModel(accessToken);

		// 保存前端传来商品ID集合
		String goodsIds = request.getParameter("goodsIds");
		// 保存前端传来商品数量集合
		String goodsAmounts = request.getParameter("goodsAmounts");
		// 配送方式
		String distribution = request.getParameter("distribution");

		List<Goods> goodsList = new ArrayList<Goods>();
		String[] goodsIdsArray = null;
		String[] goodsAmountsArray = null;
		// 商品ID数组
		goodsIdsArray = StringUtils.split(goodsIds, ",");
		// 商品数量集合数组
		goodsAmountsArray = StringUtils.split(goodsAmounts, ",");
		// 根据商品ID集合批量获取商品数据
		goodsList = goodsService.getGoodsByIds(goodsIdsArray);

		// 判断goodsList是否为空
		if (goodsList != null && goodsList.size() != 0) {
			// 失败商品列表
			List<Goods> falseGoods = new ArrayList<Goods>();
			List<GoodsOrderDetails> goodsOrderDetailsList = new ArrayList<GoodsOrderDetails>();
			BigDecimal totalPrice = new BigDecimal(Double.toString(0.00));

			// 检测商品是否售罄
			falseGoods = goodsOrderService.checkGoodsSellOut(goodsList,
					falseGoods, goodsAmountsArray, totalPrice,
					goodsOrderDetailsList);
			// falseGoods不为空，表示有商品售罄，不能提交订单
			if (falseGoods.size() > 0) {
				retJsonUtil.setStatus("0");
				retJsonUtil.setMessage("提交失败，以上商品库存不足");
				retJsonUtil.setObject(falseGoods);
				out.write(retJsonUtil.getRetJsonO());
				return;
			} else {
				totalPrice = goodsOrderService.calcTotalPrice(goodsList,
						goodsAmountsArray, goodsOrderDetailsList);
				logger.info("totalPrice>>>>" + totalPrice);
			}

			ResidentialUser residentialUserRecord = residentialUserService
					.getUserIntegralByUserId(residentialUser);

			// 支付时使用积分
			if (userIntegral != null) {
				// 声明所使用的积分subTractIntegral
				BigDecimal subTractIntegral = new BigDecimal(
						Double.toString(0.00));
				// 声明使用积分后台的的总价格totalPriceAfterUseIntegral
				BigDecimal totalPriceAfterUseIntegral = new BigDecimal(
						Double.toString(0.00));

				// 计算出使用积分后应付的总价格
				totalPriceAfterUseIntegral = goodsOrderService
						.calcGoodsTotalPriceWithIntegral(totalPrice,
								residentialUserRecord);
				// 所使用的积分
				subTractIntegral = ShoppingMallUtil.MoneyToUserIntegral(
						totalPrice.subtract(totalPriceAfterUseIntegral));
				// 更新用户积分
				residentialUserRecord.setUserIntegral(residentialUserRecord
						.getUserIntegral().subtract(subTractIntegral));

				totalPrice = totalPriceAfterUseIntegral;
				if (totalPriceAfterUseIntegral == null) {
					retJsonUtil.setStatus("0");
					retJsonUtil.setMessage("订单提交失败");
					out.write(retJsonUtil.getRetJsonM());
					return;
				} else {
					// 设置扣除的积分数值到订单中
					goodsOrder.setUsedIntegral(subTractIntegral);
				}
			}

			// 微信支付时需要获取客户端的真实IP地址
			String realIp = GetIpAdrr.getIpAddr(request);
			Map<String, Object> additionMap = new HashMap<String, Object>();
			additionMap.put("realIp", realIp);

			// 生成商品支付的提交订单
			String orderInfo = goodsOrderService.createGoodsOrder(goodsOrder,
					goodsList, goodsOrderDetailsList, goodsIds, goodsAmounts,
					distribution, merchantId, totalPrice, goodsAmountsArray,
					payMethod, residentialUserRecord, additionMap);

			// 创建返回结果
			if (orderInfo != null) {
				Map<String, Object> retMap = new HashMap<String, Object>();
				retJsonUtil.setStatus("1");
				retJsonUtil.setMessage("订单提交成功");
				retMap.put("orderInfo", orderInfo);
				// 用户剩余积分
				String residueIntegral = residentialUserRecord.getUserIntegral()
						.setScale(2, BigDecimal.ROUND_HALF_UP).toString();
				retMap.put("residueIntegral", residueIntegral);
				retJsonUtil.setRetMap(retMap);
			} else {
				retJsonUtil.setStatus("0");
				retJsonUtil.setMessage("订单提交失败");
			}
			out.write(retJsonUtil.getRetJsonM());
		} else {
			retJsonUtil.setStatus("-1");
			retJsonUtil.setMessage("商品已下架");
			out.write(retJsonUtil.getRetJsonM());
		}

	}

	/**
	 * @description 通过条件获取用户订单数据
	 *
	 * @auhtor qwc 2017年7月29日下午3:10:26
	 * @param request
	 * @param response
	 * @param startPage
	 * @param pageSize
	 * @throws IOException void
	 */
	@RequestMapping("getOrders")
	public void getGoodsOrderByUserId(HttpServletRequest request,
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

		// 声明查询对象paramMap
		String condition = request.getParameter("condition");
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("userId", residentialUser.getUserId());

		// 设置查询条件
		if (StringUtils.isNotBlank(condition)) {
			if (Integer.parseInt(condition) == 1) {// 未支付
				paramMap.put("payStatus", 0);
			}
			if (Integer.parseInt(condition) == 2) {// 待收货
				paramMap.put("orderStatus", 2);
			}
			if (Integer.parseInt(condition) == 3) {// 未评价
				paramMap.put("appraiseStatus", 0);
			}
			if (Integer.parseInt(condition) == 4) {// 退货订单
				paramMap.put("afterSaleStatus", 2);
			}
		}

		// 进行分页获取操作
		List<GoodsOrder> goodsOrderList = new ArrayList<GoodsOrder>(); // 存放获取到的订单集合
		PageHelper.startPage(startPage, pageSize);
		goodsOrderList = goodsOrderService.getGoodsOrders(paramMap);
		PageInfo<GoodsOrder> page = new PageInfo<GoodsOrder>(goodsOrderList);

		// 设置查询返回的数据中的SynthesizeStatus字段为condition，标识该数据是通过哪个条件从数据库查询出来
		if (StringUtils.isNotBlank(condition)) {
			for (GoodsOrder goodsOrder : goodsOrderList) {
				List<GoodsOrderDetails> orderDetailsList = goodsOrder
						.getOrderDetailsList();
				for (GoodsOrderDetails goodsOrderDetails : orderDetailsList) {
					goodsOrderDetails.setSynthesizeStatus(condition);
				}
			}
		} else {
			for (GoodsOrder goodsOrder : goodsOrderList) {
				List<GoodsOrderDetails> orderDetailsList = goodsOrder
						.getOrderDetailsList();
				if (goodsOrder.getPayStatus() == 0) {// 未支付
					for (GoodsOrderDetails goodsOrderDetails : orderDetailsList) {
						goodsOrderDetails
								.setSynthesizeStatus(String.valueOf(1));
					}
				} else if (goodsOrder.getOrderStatus() == 2) {// 待收货
					for (GoodsOrderDetails goodsOrderDetails : orderDetailsList) {
						goodsOrderDetails
								.setSynthesizeStatus(String.valueOf(2));
					}
				} else {
					for (GoodsOrderDetails goodsOrderDetails : orderDetailsList) {
						if (goodsOrderDetails.getAfterSaleStatus() > 0) {
							goodsOrderDetails
									.setSynthesizeStatus(String.valueOf(4));
						} else if (goodsOrderDetails
								.getAfterSaleStatus() == 0) {
							goodsOrderDetails
									.setSynthesizeStatus(String.valueOf(3));
						}
					}
				}
			}
		}

		// 封装返回给移动端的数据对象
		Map<String, Object> retMap = new HashMap<String, Object>();
		retMap.put("orderList", goodsOrderList);
		retMap.put("totalPage", page.getPages());
		retJsonUtil.setMessage("获取成功");
		retJsonUtil.setStatus("1");
		retJsonUtil.setRetMap(retMap);
		logger.info(
				"goodsOrdersList>>>:" + JSONArray.fromObject(goodsOrderList));
		out.write(retJsonUtil.getRetJsonM());
	}

	/**
	 * @description 取消商品支付订单
	 *
	 * @author qwc 2017年8月7日下午8:23:38
	 * @param response
	 * @param request
	 * @param goodsOrder
	 * @throws IOException void
	 */
	@RequestMapping("clOrder")
	public void cancelOrder(HttpServletResponse response, GoodsOrder goodsOrder,
			HttpServletRequest request) throws IOException {
		PrintWriter out = response.getWriter();
		// 创建json返回对象
		RetJsonUtil retJsonUtil = new RetJsonUtil();

		// token解析
		String token = request.getParameter("token");
		ResidentialUser residentialUser = AccessTokenUtil
				.parserAccessTokenToModel(token);

		// 商品ID数组
		String[] goodsIdArray = null;
		// 获取出要取消的订单详情
		List<GoodsOrderDetails> goodsOrderDetailsList = goodsOrderService
				.getGoodsOrderDetailsByOrderId(goodsOrder.getOrderId());

		// 保存商品ID到goodsIdArray数组中
		if (goodsOrderDetailsList.size() > 0) {
			goodsIdArray = new String[goodsOrderDetailsList.size()];
			for (int i = 0; i < goodsOrderDetailsList.size(); i++) {
				goodsIdArray[i] = Integer
						.toString(goodsOrderDetailsList.get(i).getpGoodsId());
			}
		}

		List<Goods> goodsList = new ArrayList<Goods>();
		goodsList = goodsService.getGoodsByIds(goodsIdArray);
		for (int j = 0; j < goodsList.size(); j++) {
			int amount = goodsList.get(j).getGoodsAmount()
					+ goodsOrderDetailsList.get(j).getBuyAmount();
			goodsList.get(j).setGoodsAmount(amount);
		}

		// 进行订单取消操作
		int status = goodsOrderService.cancelByPrimaryKey(residentialUser,
				goodsOrder, goodsList);

		// 封装返回给移动端的数据对象
		if (status > 0) {
			retJsonUtil.setMessage("取消成功");
			retJsonUtil.setStatus("1");
		} else {
			retJsonUtil.setMessage("取消失败");
			retJsonUtil.setStatus("0");
		}
		out.write(retJsonUtil.getRetJsonM());
	}

	/**
	 * @description 售后服务，用户申请退货
	 *
	 * @auhtor qwc 2017年8月13日下午3:54:44
	 * @param response
	 * @param request
	 * @param orderDetailsId
	 * @param filesRefundImage
	 * @throws IOException void
	 */
	@RequestMapping("applyRef")
	public void applyRefund(HttpServletResponse response,
			HttpServletRequest request,
			@RequestParam(value = "orderDetailsId", required = true) int orderDetailsId,
			@RequestParam("refundImage") MultipartFile[] filesRefundImage)
			throws IOException {
		PrintWriter out = response.getWriter();
		GoodsOrderDetails goodsOrderDetails = new GoodsOrderDetails();
		String refundImgs = null;
		RetJsonUtil retJsonUtil = new RetJsonUtil();

		String refundMoney = request.getParameter("refundMoney");// 退款金额
		String refundContent = request.getParameter("refundContent");// 退货内容
		refundImgs = fileUpload(filesRefundImage, "refundImages",
				orderDetailsId);// 退货商品图片
		logger.info("refundImgs>>" + refundImgs);

		// 设置属性
		goodsOrderDetails.setRefundMoney(new BigDecimal(refundMoney));// 退款金额
		goodsOrderDetails.setRefundContent(refundContent);
		goodsOrderDetails.setRefundImgs(refundImgs);
		goodsOrderDetails.setOrderDetailsId(orderDetailsId);
		goodsOrderDetails.setAfterSaleStatus(1);// 设置为申请退货状态

		// 执行申请操作
		int updateStatus = goodsOrderService
				.updateOrderDetails(goodsOrderDetails);

		if (updateStatus > 0) {
			retJsonUtil.setStatus("1");
			retJsonUtil.setMessage("申请成功");
		} else {
			retJsonUtil.setStatus("0");
			retJsonUtil.setMessage("申请失败");
		}
		out.write(retJsonUtil.getRetJsonM());
	}

	/**
	 * @description 确定收货
	 *
	 * @auhtor qwc 2017年8月18日下午1:34:00
	 * @param request
	 * @param response
	 * @throws IOException void
	 */
	@RequestMapping("confirmAccept")
	public void confirmAcceptGoods(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		PrintWriter out = response.getWriter();
		String orderId = request.getParameter("orderId");
		GoodsOrder goodsOrder = new GoodsOrder();
		RetJsonUtil retJsonUtil = new RetJsonUtil();

		int updateStatus = 0;
		goodsOrder.setOrderId(Integer.parseInt(orderId));

		// 执行确认操作
		updateStatus = goodsOrderService.confirmAcceptGoods(goodsOrder);

		// 返回结果
		if (updateStatus > 0) {
			retJsonUtil.setStatus("1");
			retJsonUtil.setMessage("确定成功");
		} else {
			retJsonUtil.setStatus("0");
			retJsonUtil.setMessage("确定失败");
		}
		out.write(retJsonUtil.getRetJsonM());
	}

	/**
	 * @author qwc 2017年8月13日下午10:56:16
	 * @param files
	 * @param folderName
	 * @param goodsInfoId
	 * @return String 保存退货申请时上传的图片
	 */
	public String fileUpload(MultipartFile[] files, String folderName,
			int goodsInfoId) {
		String saveTosqlurl = null;// 保存到数据库中的路径
		String bootUrl = "/usr";// 保存的根目录
		String uploadUrl = "/ezsh/" + "upload/images/" + folderName + "/"
				+ goodsInfoId + "/";
		String returnUrl = null;
		File dir = new File(bootUrl + uploadUrl);
		if (!dir.exists()) {
			dir.mkdirs();
		}
		// 判断file数组不能为空并且长度大于0
		if (files != null && files.length > 0) {
			// 循环获取file数组中得文件
			for (int i = 0; i < files.length; i++) {
				MultipartFile file = files[i];
				try {
					saveTosqlurl = uploadUrl + DateUtil.dateToStr(new Date(),
							DateUtil.DATE_TIME_NO_SLASH) + i + ".jpg";
					file.transferTo(new File(bootUrl + saveTosqlurl));// 保存到服务器中

					if (returnUrl == null) {
						returnUrl = saveTosqlurl;
					} else {
						returnUrl = "," + returnUrl + saveTosqlurl;
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return returnUrl;
	}

}
