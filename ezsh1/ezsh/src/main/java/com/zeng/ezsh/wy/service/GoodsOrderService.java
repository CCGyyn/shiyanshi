package com.zeng.ezsh.wy.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import com.zeng.ezsh.wy.entity.Goods;
import com.zeng.ezsh.wy.entity.GoodsOrder;
import com.zeng.ezsh.wy.entity.GoodsOrderDetails;
import com.zeng.ezsh.wy.entity.ResidentialUser;
/**
 * @description 订单操作接口
 *
 * @author qwc
 */
public interface GoodsOrderService {

	/**
	 * @description 创建商品订单基本信息
	 *
	 * @auhtor qwc 2018年9月24日 下午4:31:39
	 * @param record
	 * @return int
	 */
	public int createGoodsOrder(GoodsOrder record);

	/**
	 * @description 创建订单（移动端）
	 *
	 * @auhtor qwc 2018年9月29日 下午10:34:05
	 * @param goodsOrder
	 * @param goodsList
	 * @param goodsOrderDetailsList
	 * @param goodsIds
	 * @param realIp
	 * @param goodsAmounts 购买商品数量
	 * @param distribution 配送方式
	 * @param merchantId 商家ID
	 * @param totalPrice 总共价格
	 * @param goodsAmountsArray
	 * @param payMethod 支付方式
	 * @param residentialUser
	 * @return String
	 */
	public String createGoodsOrder(GoodsOrder goodsOrder, List<Goods> goodsList,
			List<GoodsOrderDetails> goodsOrderDetailsList, String goodsIds,
			String goodsAmounts, String distribution, int merchantId,
			BigDecimal totalPrice, String[] goodsAmountsArray, String payMethod,
			ResidentialUser residentialUser, Map<String, Object> additionMap);

	/**
	 * @description 批量生产订单详细信息
	 *
	 * @auhtor qwc 2018年9月24日 下午4:31:58
	 * @param recordList
	 * @return int
	 */
	int insertOrderDetails(List<GoodsOrderDetails> recordList);

	/**
	 * @description 通过条件获取用户订单集合（移动端）
	 *
	 * @auhtor qwc 2018年9月24日 下午4:32:10
	 * @param param
	 * @return List<GoodsOrder>
	 */
	public List<GoodsOrder> getGoodsOrders(Map<String, Object> param);

	/**
	 * @description 检测商品是否售罄（移动端）
	 *
	 * @param goodsList
	 * @param falseGoodsList 库存不足或者售罄商品集合
	 * @param goodsAmountsArray 批量购买的商品数量数组
	 * @param totalPrice
	 * @param goodsOrderDetailsList 存储商品详细订单集合
	 * @return
	 * @return List<Goods>
	 */
	public List<Goods> checkGoodsSellOut(List<Goods> goodsList,
			List<Goods> falseGoodsList, String[] goodsAmountsArray,
			BigDecimal totalPrice,
			List<GoodsOrderDetails> goodsOrderDetailsList);

	/**
	 * @description
	 *
	 * @auhtor qwc 2018年9月26日 下午4:32:33
	 * @param goodsList
	 * @param goodsAmountsArray
	 * @param goodsOrderDetailsList
	 * @return BigDecimal
	 */
	public BigDecimal calcTotalPrice(List<Goods> goodsList,
			String[] goodsAmountsArray,
			List<GoodsOrderDetails> goodsOrderDetailsList);

	/**
	 * @description 按条件获取订单详细信息集合（后台）
	 *
	 * @auhtor qwc 2018年9月26日 下午4:33:00
	 * @param param
	 * @return List<GoodsOrder>
	 */
	public List<GoodsOrder> getGoodsOrderDetailsListByParam(
			Map<String, Object> param);

	/**
	 * @description 根据订单ID获取订单信息
	 *
	 * @auhtor qwc 2018年10月14日 下午4:33:16
	 * @param param
	 * @return GoodsOrder
	 */
	public GoodsOrder getGoodsOrdersByOrderId(Map<String, Object> param);

	/**
	 * @description 取消商品支付订单
	 *
	 * @param record
	 * @param goodsList
	 * @return int
	 */
	public int cancelByPrimaryKey(ResidentialUser residentialUser,
			GoodsOrder record, List<Goods> goodsList);

	/**
	 * @description 通过订单交易号更新订单
	 *
	 * @auhtor qwc 2018年10月12日 下午4:33:35
	 * @param record
	 * @return int
	 */
	public int updateOrderSerialNum(GoodsOrder record);

	/**
	 * @description 根据订单ID获取订单详细信息集合
	 *
	 * @auhtor qwc 2018年10月14日 下午4:33:51
	 * @param orderId
	 * @return List<GoodsOrderDetails>
	 */
	public List<GoodsOrderDetails> getGoodsOrderDetailsByOrderId(int orderId);

	/**
	 * @description 支付时使用积分，重新计算所需的总支付金额
	 *
	 * @auhtor qwc 2018年1月30日 下午2:50:35
	 * @param totalPrice 商品总价格
	 * @param integral 传入所使用的积分数
	 * @return BigDecimal
	 */
	public BigDecimal calcGoodsTotalPriceWithIntegral(BigDecimal totalPrice,
			ResidentialUser record);

	/**
	 * @description 更新订单详细情况
	 *
	 * @auhtor qwc 2018年10月13日 下午4:34:18
	 * @param record
	 * @return int
	 */
	public int updateOrderDetails(GoodsOrderDetails record);

	/**
	 * @description 用户确定收货
	 *
	 * @auhtor qwc 2018年10月15日 下午4:34:30
	 * @param record
	 * @return int
	 */
	public int confirmAcceptGoods(GoodsOrder record);

	/**
	 * @description 检测订单号是否存在
	 *
	 * @auhtor qwc 2018年10月14日 下午4:34:43
	 * @param record
	 * @return GoodsOrder
	 */
	public GoodsOrder checkOrderSerialNumIsOn(GoodsOrder record);
}
