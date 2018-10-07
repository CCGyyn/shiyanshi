package com.zeng.ezsh.wy.utils;

import java.math.BigDecimal;

import com.zeng.ezsh.wy.entity.ChargeItem;
import com.zeng.ezsh.wy.entity.Room;

/**
 * @description 计算费用总价钱工具类
 *
 * @author qwc
 */
public class CalcChargePriceUtil {
	/**
	 * @description 返回费当月房间客户相应收费项目应缴纳的总价钱
	 *
	 * @auhtor qwc 2017年11月7日下午3:55:30
	 * @param chargeItem 收费项目实体
	 * @param room 房间信息实体
	 * @return BigDecimal
	 */
	public static BigDecimal CalcCharge(ChargeItem chargeItem, Room room) {
		BigDecimal totalPrice = new BigDecimal(0);
		if (chargeItem.getChargeBillingWay() == 1) {// （公摊费，按建筑面积计算）
			BigDecimal area = new BigDecimal(room.getBuildArea());
			// 单价乘总建筑面积
			totalPrice = area.multiply(chargeItem.getChargeBillingUnitPrice());
			return totalPrice;
		} else if (chargeItem.getChargeBillingWay() == 2) {// 抄电表
			return totalPrice;
		} else if (chargeItem.getChargeBillingWay() == 3) {// 定额
			return totalPrice;
		} else if (chargeItem.getChargeBillingWay() == 4) {// 抄水表
			return totalPrice;
		}
		return totalPrice;
	}
}
