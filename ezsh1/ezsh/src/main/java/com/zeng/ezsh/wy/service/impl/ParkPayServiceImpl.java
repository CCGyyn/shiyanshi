package com.zeng.ezsh.wy.service.impl;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.zeng.ezsh.alipay.service.AlipayService;
import com.zeng.ezsh.wechatpay.service.WechatPayAppService;
import com.zeng.ezsh.wechatpay.service.WechatPayF2FService;
import com.zeng.ezsh.wechatpay.service.WechatPayH5Service;
import com.zeng.ezsh.wy.entity.BerthOrder;
import com.zeng.ezsh.wy.entity.ParkRecord;
import com.zeng.ezsh.wy.service.ParkPayService;
import com.zeng.ezsh.wy.service.PlateManagementService;
import com.zeng.ezsh.wy.utils.JsonUtil;
@Service
public class ParkPayServiceImpl implements ParkPayService {
	@Resource
	PlateManagementService plateManagementService;
	
	
	@Resource
	AlipayService alipayService;
	@Resource
	WechatPayAppService wechatPayAppService;
	@Resource
	WechatPayF2FService wechatPayF2FService;
	@Resource
	WechatPayH5Service wechatPayH5Service;
	/**
	 * 一般支付
	 */
	@Override
	public String addOrder(Map<String, Object> additionsMap) {
		Map<String, String> retMap = new HashMap<String, String>();
		ParkRecord parkRecord = (ParkRecord) additionsMap.get("parkRecord");
		//支付方式
		String payWay = parkRecord.getPayWay();
		String identify = (String) additionsMap.get("identify");
		if(identify.equals("wap")){
			if(payWay.equals("Alipay")||
					payWay.equals("alipay")||
					payWay.equals("AliPay")||
					payWay.equals("aliPay")){
				return alipayService.ParkingChargesByWeb(parkRecord);
			}else{
				retMap = wechatPayH5Service.parkingPayOfH5(additionsMap);
				if(retMap.isEmpty()){
					return null;
				}else{
					return JsonUtil.map2json(retMap);
				}
			}
		}else{
			if(payWay.equals("Alipay")){
				return alipayService.ParkingCharges(parkRecord);
			}else{
				retMap = wechatPayAppService.ParkingCharges(additionsMap);
				if(retMap.isEmpty()){
					return null;
				}else{
					return retMap.toString();
				}
			}
		}
	}
	/**
	 * 扫码支付
	 */
	public String addOrderWithBarCode(Map<String,Object> addtionMap){
		ParkRecord parkRecord = new ParkRecord();
		parkRecord = (ParkRecord) addtionMap.get("parkRecord");
		
		if("Alipay".equals(parkRecord.getPayWay())){
			return alipayService.ParkingChargesByBarCode(addtionMap);
		}else{
			return wechatPayF2FService.ParkingCostByBarCode(addtionMap);
		}
		
	}
	/**
	 * 移动端支付停车管理费
	 */
	public String addBerthOrder(Map<String, Object> map){
		BerthOrder berthOrder = (BerthOrder) map.get("berthOrder");
		String payWay = berthOrder.getPayway();
		
		if(payWay.equals("Alipay")||
			payWay.equals("alipay")||
			payWay.equals("AliPay")||
			payWay.equals("aliPay")){
			return alipayService.BerthCosts(map);
		}else{
			Map<String, String> retMap = new HashMap<>();
			retMap = wechatPayAppService.BerthCharges(map);
			return retMap.isEmpty() ? null : retMap.toString();
		}
	}
	
}
