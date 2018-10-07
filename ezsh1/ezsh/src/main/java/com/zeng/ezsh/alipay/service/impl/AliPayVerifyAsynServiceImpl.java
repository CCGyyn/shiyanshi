package com.zeng.ezsh.alipay.service.impl;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.zeng.ezsh.alipay.config.AlipayConfig;
import com.zeng.ezsh.alipay.service.AliPayVerifyAsynService;
import com.zeng.ezsh.wy.dao.BenefitRecordMapper;
import com.zeng.ezsh.wy.dao.BerthOrderMapper;
import com.zeng.ezsh.wy.dao.ChargeInfoMapper;
import com.zeng.ezsh.wy.dao.ChargeOrderMapper;
import com.zeng.ezsh.wy.dao.GoodsOrderMapper;
import com.zeng.ezsh.wy.dao.PlateManagementMapper;
import com.zeng.ezsh.wy.dao.ResidentialUserMapper;
import com.zeng.ezsh.wy.dao.UserTeacherFeeMapper;
import com.zeng.ezsh.wy.entity.BenefitRecord;
import com.zeng.ezsh.wy.entity.BerthOrder;
import com.zeng.ezsh.wy.entity.ChargeInfo;
import com.zeng.ezsh.wy.entity.ChargeOrder;
import com.zeng.ezsh.wy.entity.GoodsOrder;
import com.zeng.ezsh.wy.entity.ParkRecord;
import com.zeng.ezsh.wy.entity.ResidentialUser;
import com.zeng.ezsh.wy.entity.UserTeacherFee;
import com.zeng.ezsh.wy.utils.DateUtil;
import com.zeng.ezsh.wy.utils.ShoppingMallUtil;

@Service
public class AliPayVerifyAsynServiceImpl implements AliPayVerifyAsynService {
	private static Logger logger = Logger
			.getLogger("AliPayVerifyAsynServiceImpl");
	@Resource
	GoodsOrderMapper goodsOrderMapper;
	@Resource
	ChargeInfoMapper chargeInfoMapper;
	@Resource
	ChargeOrderMapper chargeOrderMapper;
	@Resource
	UserTeacherFeeMapper userTeacherFeeMapper;
	@Resource
	BenefitRecordMapper benefitRecordMapper;
	@Resource
	ResidentialUserMapper residentialUserMapper;
	@Resource
	PlateManagementMapper plateManagementMapper;
	@Resource
	BerthOrderMapper berthOrderMapper;
	/**
	 * @description 异步商品支付时微信返回的数据并更新订单状态
	 */
	@Override
	public boolean verifyGoodsPayAsynAndUpadteOrder(
			Map<String, String> retMap) {
		GoodsOrder goodsOrder = new GoodsOrder();
		// 获取商户订单号
		goodsOrder.setOrderSerialNum(retMap.get("out_trade_no"));

		// 根据商户订单号获取后台数据库商品支付订单的信息
		goodsOrder = goodsOrderMapper.checkOrderSerialNumIsOn(goodsOrder);

		// 商家系统支付宝用户号不相等，返回失败
		if (!retMap.get("seller_id")
				.equals(AlipayConfig.SellerId)) { return false; }
		// 应用ID不相等，返回失败
		if (!retMap.get("app_id").equals(AlipayConfig.APPID)) { return false; }
		// 订单号不存在
		if (goodsOrder == null) { return false; }

		/*
		 * if(!params.get("total_amount").equals(goodsOrder.
		 * getTotalPrice().toString())){//订单金额不相等，返回失败 out.write("failure");
		 * return; }
		 */
		// 取消订单
		if (retMap.get("trade_status").equals("TRADE_CLOSED")) {
			goodsOrder.setTransactionNum(retMap.get("trade_no"));
			goodsOrder.setOrderStatus(-1);
			// 交易成功
		} else if (retMap.get("trade_status").equals("TRADE_SUCCESS")) {
			goodsOrder.setPayTime(retMap.get("gmt_payment"));// 设置订单信息的支付时间
			goodsOrder.setTransactionNum(retMap.get("trade_no"));// 设置订单信息的支付宝交易号
			goodsOrder.setPayStatus(1);// 设置订单信息的支付状态为已付款
			goodsOrder.setOrderStatus(1);// 设置订单信息的物流状态为代发货
			goodsOrderMapper.updateOrderSerialNum(goodsOrder);// 根据商家后台生成的订单号更新订单信息
			// 检测购买商品时是否使用积分
			if (goodsOrder.getUsedIntegral() != null && goodsOrder
					.getUsedIntegral().compareTo(new BigDecimal(0.00)) != 0) {
				ResidentialUser residentialUser = new ResidentialUser();
				residentialUser.setUserId(goodsOrder.getpUserId());
				residentialUser = residentialUserMapper
						.getUserIntegralByUserId(residentialUser);
				residentialUser
						.setUserIntegral(residentialUser.getUserIntegral()
								.add(ShoppingMallUtil.MoneyToUserIntegral(
										goodsOrder.getTotalPrice())));
				residentialUserMapper.updateByUserId(residentialUser);
			}
			return true;
		}
		return false;
	}

	/**
	 * 异步核实物业费支付时微信返回的数据并更新订单状态
	 */
	@Override
	public boolean verifyPropertyPayAsynAndUpadteOrder(
			Map<String, String> retMap) {
		// TODO Auto-generated method stub
		ChargeInfo chargeInfo = new ChargeInfo();
		ChargeOrder chargeOrder = new ChargeOrder();
		// 获取商家系统后台生成的订单号
		String out_trade_no = retMap.get("out_trade_no");
		chargeOrder.setOutTradeNo(out_trade_no);
		chargeOrder = chargeOrderMapper.selectByParam(chargeOrder);
		/*
		 * chargeInfo.setChargeInfoId(Integer.parseInt(
		 * out_trade_no.substring(0, out_trade_no.indexOf("-") + 1)));
		 */
		logger.info("缴费信息ID>>" + chargeInfo.getChargeInfoId());

		// 获取物业费支付订单信息
		chargeInfo = chargeInfoMapper.selectChargeInfoReacord(chargeInfo);

		// 商家系统支付宝用户号不相等，返回失败
		if (!retMap.get("seller_id")
				.equals(AlipayConfig.SellerId)) { return false; }
		// 应用ID不相等，返回失败
		if (!retMap.get("app_id").equals(AlipayConfig.APPID)) { return false; }
		// 订单号不存在
		if (chargeInfo == null) { return false; }
		/*
		 * BigDecimal totalPrice=new BigDecimal(0); for(ChargeRecord
		 * record:chargeInfo.getChargeRecordList()){
		 * totalPrice.add(record.getTotalPrice()); }
		 * if(!params.get("total_amount").equals(totalPrice.toString()))
		 * {//订单金额不相等，返回失败 out.write("failure"); return; }
		 */
		// 取消订单
		if (retMap.get("trade_status").equals("TRADE_CLOSED")) {
			/*
			 * chargeInfo.setChargePayStatus(chargePayStatus);
			 */
			return true;
			// 交易成功
		} else if (retMap.get("trade_status").equals("TRADE_SUCCESS")) {
			chargeInfo.setChargePayStatus(1);// 支付状态
			chargeInfo.setChargePayTime(retMap.get("timestamp"));// 成功支付时间
			chargeInfo.setChargeTransactionNum(retMap.get("trade_no"));// 支付系统的交易流水号
			int status = chargeInfoMapper.updateChargeInfoPayStatus(chargeInfo);
			chargeOrder.setPayStatus(1);// 设置支付状态
			chargeOrder.setTradeNum(retMap.get("trade_no"));// 支付系统的交易流水号
			chargeOrder.setOutTradeNo(retMap.get("out_trade_no"));// 设置商家后台系统生成的订单号
			int updateChargeOrderStatus = chargeOrderMapper
					.updateByParamSelective(chargeOrder);
			if (status > 0 && updateChargeOrderStatus > 0) {
				return true;
			} else {
				return false;
			}
		}
		return false;
	}

	/**
	 * @description 异步核实家教支付时微信返回的数据并更新订单状态
	 */
	@Override
	public boolean verifyTeacherPayAsynAndUpadteOrder(
			Map<String, String> retMap) {
		UserTeacherFee record = new UserTeacherFee();

		// 商家后台生成的订单号
		String out_trade_no = retMap.get("out_trade_no");
		record.setOutTradeNo(out_trade_no);
		// 根据订单号获取支付订单信息
		record = userTeacherFeeMapper.selectByParam(record);

		// 商家系统支付宝用户号不相等，返回失败
		if (!retMap.get("seller_id")
				.equals(AlipayConfig.SellerId)) { return false; }
		// 应用ID不相等，返回失败
		if (!retMap.get("app_id").equals(AlipayConfig.APPID)) { return false; }
		// 订单号不存在
		if (record == null) { return false; }
		/*
		 * BigDecimal totalPrice=new BigDecimal(0); for(ChargeRecord
		 * record:chargeInfo.getChargeRecordList()){
		 * totalPrice.add(record.getTotalPrice()); }
		 * if(!params.get("total_amount").equals(totalPrice.toString()))
		 * {//订单金额不相等，返回失败 out.write("failure"); return; }
		 */
		// 交易关闭
		if (retMap.get("trade_status").equals("TRADE_CLOSED")) {
			return true;
			// 交易成功
		} else if (retMap.get("trade_status").equals("TRADE_SUCCESS")
				|| retMap.get("trade_status").equals("TRADE_FINISHED")) {
			// 设置支付订单的支付时间
			record.setTradeTime(
					DateUtil.dateToStr(new Date(), DateUtil.DATE_TIME_LINE));
			record.setTradeNum(retMap.get("trade_no")); // 设置支付订单的交易订单号

			// 更新家教平台费用有效期
			ResidentialUser residentialUser = new ResidentialUser();
			residentialUser.setUserId(record.getPtUserId());
			residentialUser.setUserTeacherFeeValid(DateUtil.addXMonth(3));
			residentialUserMapper.updateByUserId(residentialUser);

			int status = userTeacherFeeMapper
					.updateByPrimaryKeySelective(record);// 更新移动端家教平台家教费支付记录
			if (status > 0) {
				return true;
			} else {
				return false;
			}
		}
		return false;
	}

	/**
	 * @description 异步核实公益基金支付时微信返回的数据并更新订单状态
	 */
	@Override
	public boolean verifyBenefitPayAsynAndUpadteOrder(
			Map<String, String> retMap) {
		// TODO Auto-generated method stub
		BenefitRecord record = new BenefitRecord();

		// 商家后台生成的订单号
		String out_trade_no = retMap.get("out_trade_no");
		record.setOutTradeNo(out_trade_no);
		record = benefitRecordMapper.selectByParam(record);

		// 商家系统支付宝用户号不相等，返回失败
		if (!retMap.get("seller_id")
				.equals(AlipayConfig.SellerId)) { return false; }
		// 应用ID不相等，返回失败
		if (!retMap.get("app_id").equals(AlipayConfig.APPID)) { return false; }
		// 订单号不存在
		if (record == null) { return false; }
		/*
		 * BigDecimal totalPrice=new BigDecimal(0); for(ChargeRecord
		 * record:chargeInfo.getChargeRecordList()){
		 * totalPrice.add(record.getTotalPrice()); }
		 * if(!params.get("total_amount").equals(totalPrice.toString()))
		 * {//订单金额不相等，返回失败 out.write("failure"); return; }
		 */
		// 交易关闭
		if (retMap.get("trade_status").equals("TRADE_CLOSED")) {
			return true;
			// 交易成功
		} else if (retMap.get("trade_status").equals("TRADE_SUCCESS")
				|| retMap.get("trade_status").equals("TRADE_FINISHED")) { // 表示支付成功
			record.setPayTime(
					DateUtil.dateToStr(new Date(), DateUtil.DATE_TIME_LINE));
			record.setTradeNum(retMap.get("trade_no")); // 设置支付宝返回的交易订单号
			int status = benefitRecordMapper
					.updateByPrimaryKeySelective(record); // 更新支付记录
			if (status > 0) {
				return true;
			} else {
				return false;
			}
		}
		return false;
	}
	
	/**
	 * @description 异步核实停车支付时微信返回的数据并更新订单状态
	 */
	@Override
	public boolean verufyParkingPayAsynAndUpdateOrder(Map<String, String> retMap) {

		ParkRecord record = new ParkRecord();
		String orderNum = retMap.get("out_trade_no");	//订单号
		record = plateManagementMapper.getParkRecordByOrderNum(orderNum);
		SimpleDateFormat format =  new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		// 商家系统支付宝用户号不相等，返回失败
		if(!retMap.get("seller_id").equals(AlipayConfig.SellerId)){
			return false;
		}
		// 应用ID不相等，返回失败
		if(!retMap.get("app_id").equals(AlipayConfig.APPID)){
			return false;
		}
		// 订单号不存在， 返回失败
		if(record == null){
			return false;
		}
		// 交易关闭
		if(retMap.get("trade_status").equals("TRADE_CLOSED")){
			return true;
		}else if (retMap.get("trade_status").equals("TRADE_SUCCESS")
				|| retMap.get("trade_status").equals("TRADE_FINISHED")){
			try {
				record.setPrepaymentTime(format.parse(retMap.get("gmt_payment")));
			} catch (ParseException e) {
				logger.info("时间格式转换错误");
				e.printStackTrace();
			}
			record.setHandParkCost(Double.parseDouble(retMap.get("total_amount")));
			record.setTransactionNum(retMap.get("trade_no"));	//设置支付宝返回的交易订单号
			record.setPayStatus(1);
			
			System.out.println("---------------------->record:"+record);
			
			int status = plateManagementMapper.updateParkRecord(record);
			if(status > 0)
				return true;
			else return false;
		}
		return false;
	}

	/**
	 * @description 异步核实停车支付时微信返回的数据并更新订单状态
	 */
	@Override
	public boolean verufyBerthPayAsynAndUpdateOrder(Map<String, String> retMap) {

		BerthOrder berthOrder = new BerthOrder();
		String orderNum = retMap.get("out_trade_no");	//订单号
		berthOrder = berthOrderMapper.getBerthOrderByOrderNum(orderNum);
		SimpleDateFormat format =  new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		// 商家系统支付宝用户号不相等，返回失败
		if(!retMap.get("seller_id").equals(AlipayConfig.SellerId)){
			return false;
		}
		// 应用ID不相等，返回失败
		if(!retMap.get("app_id").equals(AlipayConfig.APPID)){
			return false;
		}
		// 订单号不存在， 返回失败
		if(berthOrder == null){
			return false;
		}
		// 交易关闭
		if(retMap.get("trade_status").equals("TRADE_CLOSED")){
			return true;
		}else if (retMap.get("trade_status").equals("TRADE_SUCCESS")
				|| retMap.get("trade_status").equals("TRADE_FINISHED")){
			try {
				berthOrder.setPayTime(format.parse(retMap.get("gmt_payment")));
			} catch (ParseException e) {
				logger.info("时间格式转换错误");
				e.printStackTrace();
			}
			berthOrder.setMoney(Double.parseDouble(retMap.get("total_amount")));
			berthOrder.setTransactionNum(retMap.get("trade_no"));	//设置支付宝返回的交易订单号
			berthOrder.setPayStatu(1);
			
			System.out.println("---------------------->berthOrder:"+berthOrder);
			
			int status = berthOrderMapper.updateBerthOrder(berthOrder);
			if(status > 0)
				return true;
			else return false;
		}
		return false;
	}

}
