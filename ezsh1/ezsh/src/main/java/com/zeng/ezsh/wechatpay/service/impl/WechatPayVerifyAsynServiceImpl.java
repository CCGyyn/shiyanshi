package com.zeng.ezsh.wechatpay.service.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.zeng.ezsh.alipay.config.AlipayConfig;
import com.zeng.ezsh.wechatpay.config.WechatPayConfig;
import com.zeng.ezsh.wechatpay.service.WechatPayVerifyAsynService;
import com.zeng.ezsh.wy.dao.BenefitRecordMapper;
import com.zeng.ezsh.wy.dao.BerthOrderMapper;
import com.zeng.ezsh.wy.dao.ChargeInfoMapper;
import com.zeng.ezsh.wy.dao.GoodsOrderMapper;
import com.zeng.ezsh.wy.dao.PlateManagementMapper;
import com.zeng.ezsh.wy.dao.ResidentialUserMapper;
import com.zeng.ezsh.wy.dao.UserTeacherFeeMapper;
import com.zeng.ezsh.wy.entity.BenefitRecord;
import com.zeng.ezsh.wy.entity.BerthOrder;
import com.zeng.ezsh.wy.entity.ChargeInfo;
import com.zeng.ezsh.wy.entity.GoodsOrder;
import com.zeng.ezsh.wy.entity.ParkRecord;
import com.zeng.ezsh.wy.entity.ResidentialUser;
import com.zeng.ezsh.wy.entity.UserTeacherFee;
import com.zeng.ezsh.wy.utils.DateUtil;
import com.zeng.ezsh.wy.utils.ShoppingMallUtil;

@Service
public class WechatPayVerifyAsynServiceImpl
		implements
			WechatPayVerifyAsynService {
	@Resource
	GoodsOrderMapper goodsOrderMapper;
	@Resource
	ChargeInfoMapper chargeInfoMapper;
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
	 * 异步商品支付时微信返回的数据并更新订单状态
	 */
	@Override
	public boolean verifyGoodsPayAsynAndUpadteOrder(
			Map<String, String> retMap) {
		// TODO Auto-generated method stub
		GoodsOrder goodsOrder = new GoodsOrder();
		goodsOrder.setOrderSerialNum(retMap.get("out_trade_no"));
		goodsOrder = goodsOrderMapper.checkOrderSerialNumIsOn(goodsOrder);

		// 卖家支付宝用户号不相等，返回失败
		if (!retMap.get("mch_id")
				.equals(WechatPayConfig.PARTNERID)) { return false; }

		// 应用ID不相等，返回失败
		if (!retMap.get("appid")
				.equals(WechatPayConfig.APP_ID)) { return false; }
		if (goodsOrder == null) {// 订单号不存在
			return false;
		}
		/*
		 * if(!params.get("total_amount").equals(goodsOrder.
		 * getTotalPrice().toString())){//订单金额不相等，返回失败 out.write("failure");
		 * return; }
		 */
		if (retMap.get("result_code").equals("SUCCESS")
				&& retMap.get("return_code").equals("SUCCESS")) {
			goodsOrder.setPayTime(retMap.get("gmt_payment"));
			goodsOrder.setTransactionNum(retMap.get("trade_no"));
			goodsOrder.setPayStatus(1);// 已付款
			goodsOrder.setOrderStatus(1);// 代发货
			goodsOrderMapper.updateOrderSerialNum(goodsOrder);

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
		String out_trade_no = retMap.get("out_trade_no");
		chargeInfo.setChargeInfoId(Integer.parseInt(
				out_trade_no.substring(0, out_trade_no.indexOf("-") + 1)));
		System.out.print("缴费信息ID》》" + chargeInfo.getChargeInfoId());
		chargeInfo = chargeInfoMapper.selectChargeInfoReacord(chargeInfo);

		if (!retMap.get("seller_id").equals(AlipayConfig.SellerId)) {// 卖家支付宝用户号不相等，返回失败
			return false;
		}
		if (!retMap.get("app_id").equals(AlipayConfig.APPID)) {// 应用ID不相等，返回失败
			return false;
		}
		if (chargeInfo == null) {// 订单号不存在
			return false;
		}
		/*
		 * BigDecimal totalPrice=new BigDecimal(0); for(ChargeRecord
		 * record:chargeInfo.getChargeRecordList()){
		 * totalPrice.add(record.getTotalPrice()); }
		 * if(!params.get("total_amount").equals(totalPrice.toString()))
		 * {//订单金额不相等，返回失败 out.write("failure"); return; }
		 */
		if (retMap.get("result_code").equals("SUCCESS")
				&& retMap.get("return_code").equals("SUCCESS")) {
			chargeInfo.setChargePayStatus(1);
			int status = chargeInfoMapper.updateChargeInfoPayStatus(chargeInfo);
			if (status > 0) {
				return true;
			} else {
				return false;
			}

		}
		return false;
	}

	/**
	 * 异步核实家教支付时微信返回的数据并更新订单状态
	 */
	@Override
	public boolean verifyTeacherPayAsynAndUpadteOrder(
			Map<String, String> retMap) {
		// TODO Auto-generated method stub
		UserTeacherFee record = new UserTeacherFee();

		// ChargeInfo chargeInfo=new ChargeInfo();
		String out_trade_no = retMap.get("out_trade_no"); // 自我生成的交易流水号
		record.setOutTradeNo(out_trade_no);
		record = userTeacherFeeMapper.selectByParam(record);

		if (!retMap.get("seller_id").equals(AlipayConfig.SellerId)) {// 卖家支付宝用户号不相等，返回失败
			return false;
		}
		if (!retMap.get("app_id").equals(AlipayConfig.APPID)) {// 应用ID不相等，返回失败
			return false;
		}
		if (record == null) {// 订单号不存在
			return false;
		}
		/*
		 * BigDecimal totalPrice=new BigDecimal(0); for(ChargeRecord
		 * record:chargeInfo.getChargeRecordList()){
		 * totalPrice.add(record.getTotalPrice()); }
		 * if(!params.get("total_amount").equals(totalPrice.toString()))
		 * {//订单金额不相等，返回失败 out.write("failure"); return; }
		 */
		if (retMap.get("result_code").equals("SUCCESS")
				&& retMap.get("return_code").equals("SUCCESS")) { // 表示支付成功
			record.setTradeTime(
					DateUtil.dateToStr(new Date(), DateUtil.DATE_TIME_LINE));
			record.setTradeNum(retMap.get("trade_no")); // 设置支付宝返回的交易订单号
			int status = userTeacherFeeMapper
					.updateByPrimaryKeySelective(record);// 更新家教平台移动端支付记录
			if (status > 0) {
				return true;
			} else {
				return false;
			}

		}
		return false;
	}

	/**
	 * 异步核实公益基金支付时微信返回的数据并更新订单状态
	 */
	@Override
	public boolean verifyBenefitPayAsynAndUpadteOrder(
			Map<String, String> retMap) {
		// TODO Auto-generated method stub
		BenefitRecord record = new BenefitRecord();

		// ChargeInfo chargeInfo=new ChargeInfo();
		String out_trade_no = retMap.get("out_trade_no"); // 自我生成的交易流水号
		record.setOutTradeNo(out_trade_no);
		record = benefitRecordMapper.selectByParam(record);

		if (!retMap.get("seller_id").equals(AlipayConfig.SellerId)) {// 卖家支付宝用户号不相等，返回失败
			return false;
		}
		if (!retMap.get("app_id").equals(AlipayConfig.APPID)) {// 应用ID不相等，返回失败
			return false;
		}
		if (record == null) {// 订单号不存在
			return false;
		}
		/*
		 * BigDecimal totalPrice=new BigDecimal(0); for(ChargeRecord
		 * record:chargeInfo.getChargeRecordList()){
		 * totalPrice.add(record.getTotalPrice()); }
		 * if(!params.get("total_amount").equals(totalPrice.toString()))
		 * {//订单金额不相等，返回失败 out.write("failure"); return; }
		 */
		if (retMap.get("result_code").equals("SUCCESS")
				&& retMap.get("return_code").equals("SUCCESS")) { // 表示支付成功
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
	
	@Override
	public boolean verifyParkingPayAsynAndUpdateOrder(Map<String, String> retMap) {
		ParkRecord parkRecord = new ParkRecord();
		String orderNum = retMap.get("out_trade_no");
		parkRecord = plateManagementMapper.getParkRecordByOrderNum(orderNum);
		if (!retMap.get("seller_id").equals(AlipayConfig.SellerId)) {// 卖家支付宝用户号不相等，返回失败
			return false;
		}
		if (!retMap.get("app_id").equals(AlipayConfig.APPID)) {// 应用ID不相等，返回失败
			return false;
		}
		if (parkRecord == null) {// 订单号不存在
			return false;
		}
		if (retMap.get("trade_status").equals("TRADE_CLOSED")) {// 交易关闭
			return false;
		} else if (retMap.get("trade_status").equals("TRADE_SUCCESS")
				|| retMap.get("trade_status").equals("TRADE_FINISHED")) { // 表示支付成功
			parkRecord.setPrepaymentTime(new Date());			//支付时间
			parkRecord.setTransactionNum(retMap.get("trade_no")); // 设置微信？返回的交易订单号
			parkRecord.setPayStatus(1);
			int status = plateManagementMapper
					.updateParkRecord(parkRecord); // 更新支付记录
			if (status > 0) {
				return true;
			} else {
				return false;
			}
			
		}
		return false;
	}

	@Override
	public boolean verifyBerthPayAsynAndUpdateOrder(Map<String, String> retMap) {
		BerthOrder berthOrder = new BerthOrder();
		String orderNum = retMap.get("out_trade_no");
		berthOrder = berthOrderMapper.getBerthOrderByOrderNum(orderNum);
		if (!retMap.get("seller_id").equals(AlipayConfig.SellerId)) {// 卖家支付宝用户号不相等，返回失败
			return false;
		}
		if (!retMap.get("app_id").equals(AlipayConfig.APPID)) {// 应用ID不相等，返回失败
			return false;
		}
		if (berthOrder == null) {// 订单号不存在
			return false;
		}
		if (retMap.get("trade_status").equals("TRADE_CLOSED")) {// 交易关闭
			return false;
		} else if (retMap.get("trade_status").equals("TRADE_SUCCESS")
				|| retMap.get("trade_status").equals("TRADE_FINISHED")) { // 表示支付成功
			berthOrder.setPayTime(new Date());			//支付时间
			berthOrder.setMoney(Double.parseDouble(retMap.get("total_fee"))/100.0);//单位分 换算成单位元
			berthOrder.setTransactionNum(retMap.get("trade_no"));	//设置支付宝返回的交易订单号
			berthOrder.setPayStatu(1);
			int status = berthOrderMapper
					.updateBerthOrder(berthOrder); // 更新支付记录
			if (status > 0) {
				return true;
			} else {
				return false;
			}
			
		}
		return false;
	}
}
