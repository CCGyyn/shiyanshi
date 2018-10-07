package com.zeng.ezsh.wy.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import javax.annotation.Resource;

import net.sf.json.JSONObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.zeng.ezsh.wy.admin.action.AdminBerthManagementAction;
import com.zeng.ezsh.wy.entity.BerthMessage;
import com.zeng.ezsh.wy.entity.ParkRecord;
import com.zeng.ezsh.wy.entity.PlateRecordResponse;
import com.zeng.ezsh.wy.service.ParkNoticeService;
import com.zeng.ezsh.wy.service.ParkPayService;
import com.zeng.ezsh.wy.service.PlateManagementService;
import com.zeng.ezsh.wy.utils.SerializeUtil;

@Service
public class ParkNoticeServiceImpl implements ParkNoticeService {

	private static Logger logger = LoggerFactory
			.getLogger(ParkNoticeServiceImpl.class);
	ExecutorService executorService = Executors.newSingleThreadExecutor();
	@Resource
	PlateManagementService plateManagementService;
	@Resource
	ParkPayService parkPayService;

	/**
	 * 通知处理中心
	 * 
	 * @author y
	 */
	@Override
	public PlateRecordResponse handleNotice(Map<String, Object> map) {
		JSONObject temp = (JSONObject) map.get("jsonObj");
		String ip = (String) map.get("ip");
		String actType = temp.getString("ActType");
		Future<PlateRecordResponse> future = null;

		if ("000001".equals(actType)) { // 车辆入场通知
			future = executorService.submit(new enterParkRecordNotice(actType,
					temp.getString("InTime"), temp.getString("ParkingId"), temp
							.getString("Car"), temp.getString("CRC")));
		} else if ("000002".equals(actType)) { // 车辆离场通知
			future = executorService.submit(new leaveParkRecordNotice(actType,
					temp.getString("OutTime"), temp.getString("ParkingId"),
					temp.getString("Car"), temp.getString("CRC")));
		} else if ("000003".equals(actType)) { // 剩余车位通知（不做处理）
			return new PlateRecordResponse(actType, "00", "成功",
					temp.getString("CRC"));
		} else if ("000006".equals(actType)) { // 扫码支付通知
			future = executorService.submit(new payForScan(actType, temp
					.getString("Car"), temp.getString("ParkingId"), temp
					.getString("payCode"), temp.getString("Money"), temp
					.getString("CRC"), ip));
		} else if ("000007".equals(actType)) { // 现金支付通知
			future = executorService.submit(new cashNotice());
		} else if ("000009".equals(actType)) { // 车位管理费通知（续费）
			future = executorService.submit(new berthCostNotice(actType, temp
					.getString("Money"), temp.getString("ParkingId"), temp
					.getString("Berth"), temp.getString("EndTime"), temp
					.getString("CRC")));
		} else if ("000010".equals(actType)) { // 车位信息通知（购买车位）
			future = executorService.submit(new berthMessageNotice(actType,
					temp.getString("Money"), temp.getString("EndTime"), temp
							.getString("ParkindId"), temp.getString("Berth"),
					temp.getString("Car"), temp.getString("Phone"), temp
							.getString("Name"), temp.getString("CRC")));
		} else if ("000011".equals(actType)) {
			future = executorService.submit(new berthCostChangeNotice(actType,
					temp.getString("BerthCost"), temp.getString("ParkingId"),
					temp.getString("Berth"), temp.getString("CRC")));
		} else {
			return new PlateRecordResponse(actType, "04", "无法识别交易码",
					temp.getString("CRC"));
		}

		try {
			return future.get();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		}
		return new PlateRecordResponse(actType, "99", "线程出现错误",
				temp.getString("CRC"));

	}

	// //////////////////////////////////////////////////////////////////////////////////////
	/**
	 * 车辆入场通知
	 * 
	 * @author y
	 * 
	 */
	public class enterParkRecordNotice implements Callable<PlateRecordResponse> {
		String actType, inTime, parkingId, car, CRC;

		public enterParkRecordNotice(String actType, String inTime,
				String parkingId, String car, String cRC) {
			super();
			this.actType = actType;
			this.inTime = inTime;
			this.parkingId = parkingId;
			this.car = car;
			CRC = cRC;
		}

		@Override
		public PlateRecordResponse call() throws Exception {
			PlateRecordResponse plateResponse = null;
			ParkRecord parkRecord = new ParkRecord();
			parkRecord.setPlateNumber(car);// 车牌号
			parkRecord.setUserCommunityId(Integer.parseInt(parkingId));// 小区id
			parkRecord.setCRCCode(CRC);
			SimpleDateFormat format = new SimpleDateFormat(
					"yyyy-MM-dd HH:mm:ss");
			if (!actType.equals("000001")) {// 判断交易码是否正确
				return new PlateRecordResponse(actType, "01", "交易码错误", CRC);
			}
			try {
				Date enterTime = format.parse(inTime);
				parkRecord.setEnterTime(enterTime);// 入场时间
				if (plateManagementService.addParkRecord(parkRecord) == 1) {
					plateResponse = new PlateRecordResponse(actType, "00",
							"成功", CRC);
				} else {
					plateResponse = new PlateRecordResponse(actType, "02",
							"响应失败 ", CRC);
				}
			} catch (Exception e) {
				e.printStackTrace();
				plateResponse = new PlateRecordResponse(actType, "03",
						"系统内部错误", CRC);
			}

			return plateResponse;
		}

	}

	// //////////////////////////////////////////////////////////////////////////////////////
	/**
	 * 车辆离场通知
	 * 
	 * @author y
	 * 
	 */
	public class leaveParkRecordNotice implements Callable<PlateRecordResponse> {
		String actType, outTime, parkingId, car, CRC;

		public leaveParkRecordNotice(String actType, String outTime,
				String parkingId, String car, String cRC) {
			super();
			this.actType = actType;
			this.outTime = outTime;
			this.parkingId = parkingId;
			this.car = car;
			CRC = cRC;
		}

		@Override
		public PlateRecordResponse call() throws Exception {
			PlateRecordResponse plateResponse = null;
			ParkRecord parkRecord = new ParkRecord();
			parkRecord.setUserCommunityId(Integer.parseInt(parkingId));
			parkRecord.setPlateNumber(car);
			Map<String, Object> map = new HashMap<>();
			map.put("plateNumber", car);
			map.put("userCommunityId", Integer.parseInt(parkingId));
			SimpleDateFormat format = new SimpleDateFormat(
					"yyyy/MM/dd HH:mm:ss");
			if (!actType.equals("000002")) {// 判断交易码是否正确
				return new PlateRecordResponse(actType, "01", "交易码错误", CRC);
			}
			try {
				Date leaveTime = format.parse(outTime);
				parkRecord.setLeaveTime(leaveTime);// 设置离场时间
				if (plateManagementService.updateLeaveTime(parkRecord) == 1) {// 更新离场时间
					plateResponse = new PlateRecordResponse("000002", "00",
							"成功", CRC);
				} else {
					plateResponse = new PlateRecordResponse("000002", "02",
							"响应失败 ", CRC);
				}
			} catch (Exception e) {
				e.printStackTrace();
				plateResponse = new PlateRecordResponse("000002", "03", "系统错误",
						CRC);
			}
			return plateResponse;
		}
	}

	// //////////////////////////////////////////////////////////////////////////////////////
	/**
	 * 扫码支付通知
	 * 
	 * @author y
	 * 
	 */
	public class payForScan implements Callable<PlateRecordResponse> {
		String actType, car, parkingId, payCode, money, CRC, ip;

		public payForScan(String actType, String car, String parkingId,
				String payCode, String money, String cRC, String ip) {
			super();
			this.actType = actType;
			this.car = car;
			this.parkingId = parkingId;
			this.payCode = payCode;
			this.money = money;
			CRC = cRC;
			this.ip = ip;
		}

		@Override
		public PlateRecordResponse call() throws Exception {
			Map<String, Object> addtionsMap = new HashMap<String, Object>();
			ParkRecord parkRecord = new ParkRecord();
			PlateRecordResponse response = null;

			if (payCode.startsWith("10") // 微信支付授权码范围
					|| payCode.startsWith("11")
					|| payCode.startsWith("12")
					|| payCode.startsWith("13")
					|| payCode.startsWith("14")
					|| payCode.startsWith("15")) {
				parkRecord.setPayWay("WechatPay");
			} else if (payCode.startsWith("25") // 支付宝支付授权码范围
					|| payCode.startsWith("26")
					|| payCode.startsWith("27")
					|| payCode.startsWith("28")
					|| payCode.startsWith("29")
					|| payCode.startsWith("30")) {
				parkRecord.setPayWay("AliPay");
			}

			parkRecord.setPlateNumber(car);
			parkRecord.setUserCommunityId(Integer.parseInt(parkingId));
			parkRecord.setCRCCode(CRC);
			parkRecord.setOrderNum(SerializeUtil.generateUUID());
			int num = plateManagementService.updateParkRecord(parkRecord);// 主要存进订单号
			logger.info(String.valueOf(num));
			// 入参
			addtionsMap.put("parkRecord", parkRecord);
			addtionsMap.put("money", money);
			addtionsMap.put("payCode", payCode);
			addtionsMap.put("ip", ip);
			// 进行支付
			if (!"000006".equals(actType))
				return new PlateRecordResponse(actType, "01", "交易码错误", CRC);
			if ("Success".equals(parkPayService
					.addOrderWithBarCode(addtionsMap))) {
				try {
					if (plateManagementService.updateParkRecord(parkRecord) == 1) {
						response = new PlateRecordResponse(actType, "00", "成功",
								CRC);
					} else {
						response = new PlateRecordResponse(actType, "02",
								"数据库更新错误", CRC);
					}
				} catch (Exception e) {
					response = new PlateRecordResponse(actType, "03", "响应失败",
							CRC);
				}
			} else {
				response = new PlateRecordResponse(actType, "04", "交易失败", CRC);
			}
			return response;
		}
	}

	// //////////////////////////////////////////////////////////////////////////////////////
	/**
	 * 现金支付通知
	 * 
	 * @author y
	 * 
	 */
	public class cashNotice implements Callable<PlateRecordResponse> {

		@Override
		public PlateRecordResponse call() throws Exception {

			return new PlateRecordResponse("000007", "00", "交易成功", "");
		}

	}

	/**
	 * 车位管理费通知(续费)
	 */
	public class berthCostNotice implements Callable<PlateRecordResponse> {
		String actType, money, parkingId, berth, endTime, CRC;

		public berthCostNotice(String actType, String money, String parkingId,
				String berth, String endTime, String cRC) {
			super();
			this.actType = actType;
			this.money = money;
			this.parkingId = parkingId;
			this.berth = berth;
			this.endTime = endTime;
			CRC = cRC;
		}

		@Override
		public PlateRecordResponse call() throws Exception {
			PlateRecordResponse response = null;

			BerthMessage berthMessage = new BerthMessage();
			berthMessage.setBerthNumber(this.berth);
			berthMessage.setContractStarttime(new SimpleDateFormat(
					"yyyy-MM-dd HH:mm:ss").format(new Date()));
			berthMessage.setContractEndtime(this.endTime);
			berthMessage.setHandInCost(Double.parseDouble(this.money));

			int num = plateManagementService.handManageCost2(berthMessage);
			if (num == 1) {
				response = new PlateRecordResponse(actType, "00", "响应成功", CRC);
			} else {
				response = new PlateRecordResponse(actType, "01", "无效信息", CRC);
			}
			return response;
		}
	}

	// //////////////////////////////////////////////////////////////////////////////////////
	/**
	 * 车位信息通知
	 */
	public class berthMessageNotice implements Callable<PlateRecordResponse> {
		String actType, money, endTime, parkingId, berth, Car, phone, name,
				CRC;

		public berthMessageNotice(String actType, String money, String endTime,
				String parkingId, String berth, String car, String phone,
				String name, String cRC) {
			super();
			this.actType = actType;
			this.money = money;
			this.endTime = endTime;
			this.parkingId = parkingId;
			this.berth = berth;
			Car = car;
			this.phone = phone;
			this.name = name;
			CRC = cRC;
		}

		@Override
		public PlateRecordResponse call() throws Exception {
			PlateRecordResponse response = null;

			BerthMessage berthMessage = new BerthMessage();
			berthMessage.setBerthNumber(berth);
			berthMessage.setBerthCost(Double.valueOf(money));
			berthMessage.setHandInCost(Double.valueOf(money));
			berthMessage.setContractStarttime(new SimpleDateFormat(
					"yyyy-MM-dd HH:mm:ss").format(new Date()));
			berthMessage.setContractEndtime(endTime);
			berthMessage.setHirePlate(Car);
			berthMessage.setIsHire(1);
			berthMessage.setUserCommunityId(Integer.valueOf(parkingId));
			// berthMessage.setUserName(name);
			berthMessage.setUserPhone(phone);

			int num = plateManagementService.buyBerth2(berthMessage);
			if (num == 1) {
				response = new PlateRecordResponse(actType, "00", "响应成功", CRC);
			} else {
				response = new PlateRecordResponse(actType, "01", "无效信息", CRC);
			}
			return response;
		}
	}

	// //////////////////////////////////////////////////////////////////////////////////////
	/**
	 * 更新车位管理费
	 * 
	 * @author y
	 * 
	 */
	public class berthCostChangeNotice implements Callable<PlateRecordResponse> {
		String actType, berthCost, parkingId, berth, CRC;

		public berthCostChangeNotice(String actType, String berthCost,
				String parkingId, String berth, String cRC) {
			super();
			this.actType = actType;
			this.berthCost = berthCost;
			this.parkingId = parkingId;
			this.berth = berth;
			CRC = cRC;
		}

		@Override
		public PlateRecordResponse call() throws Exception {
			PlateRecordResponse response = null;
			AdminBerthManagementAction action = new AdminBerthManagementAction();
			BerthMessage berthMessage = new BerthMessage();
			berthMessage.setBerthCost(Double.parseDouble(berthCost));
			berthMessage.setUserCommunityId(Integer.parseInt(parkingId));
			berthMessage.setBerthNumber(berth);

			String retStr = action.updateBerthCost(berthMessage, "park");
			JSONObject jsonObject = JSONObject.fromObject(retStr);
			if (jsonObject.getString("status").equals("1")) {
				response = new PlateRecordResponse(actType, "00", "success",
						CRC);
			} else {
				response = new PlateRecordResponse(actType, "01", "更新失败", CRC);
			}
			return response;
		}
	}
	// //////////////////////////////////////////////////////////////////////////////////////
}
