package com.zeng.ezsh.wy.service.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import javax.annotation.Resource;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import com.zeng.ezsh.alipay.service.AlipayService;
import com.zeng.ezsh.alipay.utils.AlipayTradeCloseReqParam;
import com.zeng.ezsh.wechatpay.config.WechatPayConfig;
import com.zeng.ezsh.wechatpay.service.WechatPayAppService;
import com.zeng.ezsh.wechatpay.uitls.CloseOrderReqParam;
import com.zeng.ezsh.wechatpay.uitls.WXPayUtil;
import com.zeng.ezsh.wy.dao.GoodsMapper;
import com.zeng.ezsh.wy.dao.GoodsOrderDetailsMapper;
import com.zeng.ezsh.wy.dao.GoodsOrderMapper;
import com.zeng.ezsh.wy.dao.ResidentialUserMapper;
import com.zeng.ezsh.wy.entity.Goods;
import com.zeng.ezsh.wy.entity.GoodsOrder;
import com.zeng.ezsh.wy.entity.GoodsOrderDetails;
import com.zeng.ezsh.wy.entity.ResidentialUser;
import com.zeng.ezsh.wy.service.GoodsOrderService;
import com.zeng.ezsh.wy.utils.DateUtil;
import com.zeng.ezsh.wy.utils.SerializeUtil;
import com.zeng.ezsh.wy.utils.ShoppingMallUtil;

@Service
public class GoodsOrderServiceImpl implements GoodsOrderService {
	private Logger logger = Logger.getLogger("GoodsOrderServiceImpl");
	@Resource
	ResidentialUserMapper residentialUserMapper;
	@Resource
	GoodsOrderMapper goodsOrderMapperDao;
	@Resource
	GoodsOrderDetailsMapper goodsOrderDetailsDao;
	@Resource
	GoodsMapper goodsMapperDao;
	@Resource
	AlipayService alipayService;
	@Resource
	WechatPayAppService wechatPayAppService;

	/**
	 * @author qwc 2017年7月28日下午9:07:11
	 * @param record
	 * @return 创建商品订单
	 */
	@Override
	public int createGoodsOrder(GoodsOrder record) {
		return goodsOrderMapperDao.createGoodsOrder(record);
	}

	/**
	 * @description 通过条件获取用户订单集合（移动端）
	 */
	@Override
	public List<GoodsOrder> getGoodsOrders(Map<String, Object> param) {
		return goodsOrderMapperDao.getGoodsOrders(param);
	}

	/**
	 * @description 通过条件获取用户订单集合（用于后台）
	 */
	@Override
	public GoodsOrder getGoodsOrdersByOrderId(Map<String, Object> param) {
		return goodsOrderMapperDao.getGoodsOrdersByOrderId(param);
	}

	/**
	 * @description 取消商品支付订单
	 */
	@Override
	public int cancelByPrimaryKey(ResidentialUser residentialUser,
			GoodsOrder record, List<Goods> goodsList) {
		// 恢复库存
		goodsMapperDao.updateGoodsByGoodsId(goodsList);
		goodsOrderMapperDao.updateOrderByOrderId(record);

		// 关闭商品支付订单
		boolean status = false;
		if (record.getPayClassify().equals(1)) {
			AlipayTradeCloseReqParam alipayTradeCloseReqParam = new AlipayTradeCloseReqParam();
			alipayTradeCloseReqParam
					.setOut_trade_no(record.getOrderSerialNum());
			alipayTradeCloseReqParam.setTrade_no(record.getTransactionNum());
			status = alipayService.closeAliPayOrder(alipayTradeCloseReqParam);
		} else {
			CloseOrderReqParam closeOrderReqParam = new CloseOrderReqParam();
			closeOrderReqParam.setAppid(WechatPayConfig.APP_ID);
			closeOrderReqParam.setMch_id(WechatPayConfig.PARTNERID);
			closeOrderReqParam.setNonce_str(WXPayUtil.generateNonceStr());
			closeOrderReqParam.setOut_trade_no(record.getOrderSerialNum());
			status = wechatPayAppService.closeOrder(closeOrderReqParam);
		}
		if (status) {
			return 1;
		} else {
			return 0;
		}
	}

	/**
	 * @description 批量生产订单详细信息
	 */
	@Override
	public int insertOrderDetails(List<GoodsOrderDetails> recordList) {
		return goodsOrderDetailsDao.insertOrderDetails(recordList);
	}

	/**
	 * @description 更新订单详细信息
	 */
	@Override
	public int updateOrderDetails(GoodsOrderDetails record) {
		return goodsOrderDetailsDao.updateOrderDetails(record);
	}

	@Override
	public List<GoodsOrderDetails> getGoodsOrderDetailsByOrderId(int orderId) {
		return goodsOrderDetailsDao.getGoodsOrderDetailsListByOrderId(orderId);
	}

	/**
	 * @description 确定收货
	 */
	@Override
	public int confirmAcceptGoods(GoodsOrder record) {
		return goodsOrderMapperDao.confirmAcceptGoods(record);
	}

	/**
	 * @description 检测订单是否存在
	 */
	@Override
	public GoodsOrder checkOrderSerialNumIsOn(GoodsOrder record) {
		return goodsOrderMapperDao.checkOrderSerialNumIsOn(record);
	}

	/**
	 * @description 根据订单号更新订单信息
	 */
	@Override
	public int updateOrderSerialNum(GoodsOrder record) {
		return goodsOrderMapperDao.updateOrderSerialNum(record);
	}

	/**
	 * @description 获取商品详细订单集合
	 */
	@Override
	public List<GoodsOrder> getGoodsOrderDetailsListByParam(
			Map<String, Object> param) {
		return goodsOrderDetailsDao.getGoodsOrderDetailsListByParam(param);
	}

	/**
	 * @description 创建商品支付订单（移动端）
	 */
	@Override
	public String createGoodsOrder(GoodsOrder goodsOrder, List<Goods> goodsList,
			List<GoodsOrderDetails> goodsOrderDetailsList, String goodsIds,
			String goodsAmounts, String distribution, int merchantId,
			BigDecimal totalPrice, String[] goodsAmountsArray, String payMethod,
			ResidentialUser residentialUser, Map<String, Object> additionMap) {

		// 开始创建提交订单所需要的参数
		goodsOrder.setpUserId(residentialUser.getUserId()); // 设置用户ID
		goodsOrder.setLinkMan(goodsOrder.getLinkMan()); // 联系人姓名
		goodsOrder.setLinkPhone(goodsOrder.getLinkPhone()); // 设置联系电话
		goodsOrder.setOrderDistribution(distribution);// 设置配送方式
		goodsOrder.setpMerchantId(merchantId);// 设置商家ID
		goodsOrder.setOrderSerialNum(SerializeUtil.generateUUID()); // 订单编号
		goodsOrder.setTotalPrice(totalPrice);// 设置总价格
		goodsOrder.setAddressContent(goodsOrder.getAddressContent());// 设置收货地址

		// 获取所购买商品的名称集合字符串
		String goodsName = null;
		for (Goods goods : goodsList) {
			if (goodsName != null && goodsName
					.indexOf(goods.getGoodsInfo().getGoodsName()) < 0) {
				goodsName = goodsName + ","
						+ goods.getGoodsInfo().getGoodsName();
			} else if (goodsName == null) {
				goodsName = goods.getGoodsInfo().getGoodsName();
			}
		}

		// 更新商品库存
		int updateTheInventory = goodsMapperDao.updateGoodsByGoodsId(goodsList);

		// 设置订单记录的支付类型
		if (payMethod.equals("aliPay")) {
			goodsOrder.setPayClassify(1);
		} else {
			// 微信支付
			goodsOrder.setPayClassify(2);
		}

		// 数据库插入订单基本信息记录状态
		int createOrderStatus = 0;
		// 数据库插入详细订单记录状态
		int createOrderDetailStatus = 0;

		// 判断库存更新是否成功
		if (updateTheInventory > 0) {
			goodsOrder.setAddTime(DateUtil.dateToStr(new Date(),
					DateUtil.DATE_TIME_NO_SLASH));
			goodsOrder.setAddTime(
					DateUtil.dateToStr(new Date(), DateUtil.DATE_HM));// 订单生成时间
			goodsOrder.setTotalPrice(new BigDecimal(0.01));// 测试阶段金额设置为0.01
			// 数据库插入订单基本信息记录
			createOrderStatus = goodsOrderMapperDao
					.createGoodsOrder(goodsOrder);
		} else {
			return null;
		}

		// 数据库插入订单基本信息记录成功
		if (createOrderStatus > 0) {
			for (int m = 0; m < goodsOrderDetailsList.size(); m++) {
				goodsOrderDetailsList.get(m)
						.setpOrderId(goodsOrder.getOrderId());
			}
			// 数据库批量插入详细订单记录
			logger.info("goodsOrderDetailsList>>"
					+ JSONArray.fromObject(goodsOrderDetailsList).toString());
			createOrderDetailStatus = goodsOrderDetailsDao
					.insertOrderDetails(goodsOrderDetailsList);
		} else {
			return null;
		}

		// 更新用户积分到数据库中
		residentialUserMapper.updateIntegralByUserId(residentialUser);

		// 数据库插入订单基本信息且批量插入详细订单记录成功
		if (createOrderStatus > 0 && createOrderDetailStatus > 0) {
			String orderInfo = null;
			// 支付宝支付
			if (payMethod.equals("aliPay")) {
				orderInfo = alipayService.goodsPayment(goodsOrder, goodsName);
				// 微信支付
			} else if (payMethod.equals("wechatPay")) {
				Map<String, String> retMap = new HashMap<String, String>();
				retMap = wechatPayAppService.goodsPayment(goodsOrder, goodsName,
						additionMap);
				orderInfo = JSONObject.fromObject(retMap).toString();
			} else {
				return null;
			}
			return orderInfo;
		}
		return null;
	}

	/**
	 * @description 检测商品是否售罄
	 */
	@Override
	public List<Goods> checkGoodsSellOut(List<Goods> goodsList,
			List<Goods> falseGoodsList, String[] goodsAmountsArray,
			BigDecimal totalPrice,
			List<GoodsOrderDetails> goodsOrderDetailsList) {
		// 检测是否有商品刚好售罄的
		for (int i = 0; i < goodsList.size(); i++) { // 循环商品列表集合
			// 商品库存大于购买量，库存大于零
			if ((goodsList.get(i).getGoodsAmount() > Integer
					.parseInt(goodsAmountsArray[i]))
					&& Integer.parseInt(goodsAmountsArray[i]) > 0
					&& goodsList.get(i).getGoodsAmount() > 0) {
			} else {
				// 设置售罄获取不够库存的商品到falseGoods中
				falseGoodsList.add(goodsList.get(i));
			}
		}
		return falseGoodsList;
	}

	/**
	 * @description 计算出购买商品时总共需要支付的金额
	 */
	@Override
	public BigDecimal calcTotalPrice(List<Goods> goodsList,
			String[] goodsAmountsArray,
			List<GoodsOrderDetails> goodsOrderDetailsList) {
		BigDecimal subPrice = new BigDecimal(0.00);

		// 循环商品列表集合
		for (int i = 0; i < goodsList.size(); i++) {
			GoodsOrderDetails goodsOrderDetails = new GoodsOrderDetails();
			// 商品库存大于购买量，库存大于零
			if ((goodsList.get(i).getGoodsAmount() > Integer
					.parseInt(goodsAmountsArray[i]))
					&& Integer.parseInt(goodsAmountsArray[i]) > 0
					&& goodsList.get(i).getGoodsAmount() > 0) {

				subPrice = subPrice.add((goodsList.get(i).getGoodsPrice())
						.multiply(new BigDecimal(goodsAmountsArray[i])));
				goodsOrderDetails.setpGoodsId(goodsList.get(i).getGoodsId());
				goodsOrderDetails
						.setBuyAmount(Integer.parseInt(goodsAmountsArray[i])); // 设置数量
				goodsOrderDetails.setPrice(goodsList.get(i).getGoodsPrice()); // 设置单价
				goodsOrderDetails.setGoodsName(
						goodsList.get(i).getGoodsInfo().getGoodsName());

				// 设置类别属性一的值和名称到详细订单表的字段中
				if (StringUtils
						.isNotBlank(goodsList.get(i).getSectionOneValue())) {
					goodsOrderDetails.setSectionNamesValues(
							goodsList.get(i).getGoodsInfo().getSectionOneName()
									+ ":"
									+ goodsList.get(i).getSectionOneValue());
				}

				// 设置类别属性二的值和名称到详细订单表的字段中
				if (StringUtils
						.isNotBlank(goodsList.get(i).getSectionTwoValue())) {
					if (goodsOrderDetails.getSectionNamesValues() != null) {
						goodsOrderDetails.setSectionNamesValues(
								goodsOrderDetails.getSectionNamesValues() + ";"
										+ goodsList.get(i).getGoodsInfo()
												.getSectionTwoName()
										+ ":" + goodsList.get(i)
												.getSectionTwoValue());
					} else {
						goodsOrderDetails.setSectionNamesValues(goodsList.get(i)
								.getGoodsInfo().getSectionTwoName() + ":"
								+ goodsList.get(i).getSectionTwoValue());
					}
				}

				// 设置类别属性三的值和名称到详细订单表的字段中
				if (StringUtils
						.isNotBlank(goodsList.get(i).getSectionThreeValue())) {
					if (goodsOrderDetails.getSectionNamesValues() != null) {
						goodsOrderDetails.setSectionNamesValues(
								goodsOrderDetails.getSectionNamesValues() + ";"
										+ goodsList.get(i).getGoodsInfo()
												.getSectionThreeName()
										+ ":" + goodsList.get(i)
												.getSectionThreeValue());
					} else {
						goodsOrderDetails.setSectionNamesValues(goodsList.get(i)
								.getGoodsInfo().getSectionThreeName() + ":"
								+ goodsList.get(i).getSectionTwoValue());
					}
				}

				// 设置类别属性四的值和名称到详细订单表的字段中
				if (StringUtils
						.isNotBlank(goodsList.get(i).getSectionFourValue())) {
					if (goodsOrderDetails.getSectionNamesValues() != null) {
						goodsOrderDetails.setSectionNamesValues(
								goodsOrderDetails.getSectionNamesValues() + ";"
										+ goodsList.get(i).getGoodsInfo()
												.getSectionFourName()
										+ ":" + goodsList.get(i)
												.getSectionFourValue());
					} else {
						goodsOrderDetails.setSectionNamesValues(goodsList.get(i)
								.getGoodsInfo().getSectionFourName() + ":"
								+ goodsList.get(i).getSectionTwoValue());
					}
				}

				// 设置类别属性五的值和名称到详细订单表的字段中
				if (StringUtils
						.isNotBlank(goodsList.get(i).getSectionFiveValue())) {
					if (goodsOrderDetails.getSectionNamesValues() != null) {
						goodsOrderDetails.setSectionNamesValues(
								goodsOrderDetails.getSectionNamesValues() + ";"
										+ goodsList.get(i).getGoodsInfo()
												.getSectionFiveName()
										+ ":" + goodsList.get(i)
												.getSectionFiveValue());
					} else {
						goodsOrderDetails.setSectionNamesValues(goodsList.get(i)
								.getGoodsInfo().getSectionFiveName() + ":"
								+ goodsList.get(i).getSectionTwoValue());
					}
				}

				// 设置商品剩余库存数量goodsList集合中
				goodsList.get(i)
						.setGoodsAmount((goodsList.get(i).getGoodsAmount()
								- Integer.parseInt(goodsAmountsArray[i])));

				goodsOrderDetailsList.add(goodsOrderDetails);
			}
		}
		return subPrice;
	}

	/**
	 * @description 支付时使用积分，重新计算所需的总支付金额
	 */
	@Override
	public BigDecimal calcGoodsTotalPriceWithIntegral(BigDecimal totalPrice,
			ResidentialUser residentialUser) {
		// 把用户的积分转换成金额
		BigDecimal integralToMoney = ShoppingMallUtil
				.userIntegralToMoney(residentialUser.getUserIntegral());

		// 判断积分兑换成的金额是否大于总支付金额
		if (integralToMoney.compareTo(totalPrice) > 0) {
			// 算出新的总支付金额
			totalPrice = new BigDecimal(0.01);
			// 积分兑换成的金额小于总支付金额
		} else {
			totalPrice = totalPrice.subtract(integralToMoney);
		}
		return totalPrice;
	}

}
