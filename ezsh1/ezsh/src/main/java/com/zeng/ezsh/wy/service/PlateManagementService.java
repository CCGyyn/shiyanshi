package com.zeng.ezsh.wy.service;

import java.util.List;
import java.util.Map;

import com.zeng.ezsh.wy.entity.BerthMessage;
import com.zeng.ezsh.wy.entity.BerthMessage2;
import com.zeng.ezsh.wy.entity.CarMessage;
import com.zeng.ezsh.wy.entity.HouseMessage;
import com.zeng.ezsh.wy.entity.InviteRecord;
import com.zeng.ezsh.wy.entity.ParkRecord;
import com.zeng.ezsh.wy.entity.PlateCost;

public interface PlateManagementService {
	
	//根据身份证号获取用户房屋信息
	public List<HouseMessage> getUserHouseMessage(String identityCard);
	//业主查看管理费和停车超时等费用
	public double getPlateCost(int userCommunityId);
	//业主提交车位管理费
	public int handManageCost(Map<String,Object> map);
	//业主提交车位管理费（有更新合同时间）
	public int handManageCost2(BerthMessage berthMessage);
	//业主添加车辆
	public int addCar(CarMessage car);
	//业主查看车辆信息
	public List<CarMessage> getUserCarMessage(Map<String,Object> map);
	//业主修改车辆信息
	public int modifyCarMessage(Map<String,Object> map);
	public int modifyCar(CarMessage car);
	//业主删除车辆信息
	public int deleteCar(String plateNumber);
	public int deleteUserCar(Map<String,Object> map);
	//业主邀请访客
	public int inviteGuest(InviteRecord inviteRecord);
	//业主取消邀请
	public int cancelInvite(String invitePlate);
	//业主查看邀请
	public List<String> seeUserInvite(Map<String,Object> map);
	//判断某车是否被邀请
	public String judgeInvitedCar(String invitePlate);
	//出租车位
	public int hireBerth(Map<String,Object> map,CarMessage carMessage);
	//取消出租车位
	public int cancelHireBerth(Map<String,Object> map);
	//设置初始车位
	public int setBerth(BerthMessage berthMessage);
	//删除车位
	public int deleteBerth(Map<String,Object> map);
	//根据小区id和车牌号搜索相关记录
	public BerthMessage searchBerthRecord(Map<String,Object> map);
	//用户购买车位
	public int buyBerth(Map<String,Object> map);
	//用户购买车位2 更新所有信息
	public int buyBerth2(BerthMessage berthMessage);
	//用户取消购买车位
	public int cancelBuyBerth(Map<String,Object> map);
	//用户查看所有车位
	public List<BerthMessage> getUserBerthMessage(Map<String,Object> map);
	//用户查看所有车位（多车牌）
	public List<BerthMessage2> getUserBerthMessage2(Map<String, Object> map);
	//后台查看所有车位信息
	public List<BerthMessage> getAllBerthMessage(Map<String,Object> map);
	//获取所有车位的数量
	public int getBerthCount();
	//后台设置各小区车位停车相关费用
	public int setPlateCost(PlateCost plateCost);
	//后台查看各小区费用
	public List<PlateCost> getAllPlateCost(Map<String,Object> map);
	//后台修改各小区费用
	public int modifyPlateCost(Map<String,Object> map);
	//后台查看所有车辆信息
	public List<CarMessage> getAllCarMessage();
	//后台查看用户邀请表
	public List<InviteRecord> getAllInviteRecord();
	//根据车牌号查询被邀请情况
	public List<InviteRecord> getInviteRecordByPlateNumber(String invitePlate);
	//后台查看车辆进出记录
	public List<ParkRecord> getAllParkRecord(Map<String,Object> map);
	//查询各小区费用情况记录数 
	public int getCostCount();
	//获取停车记录数
	public int getParkRecordCount();
	//删除停车记录
	public int deleteRecord(Map<String,Object> map);
	//新增停车记录
	public int addParkRecord(ParkRecord parkRecord);
	//车辆离场时更新离场时间
	public int updateLeaveTime(ParkRecord parkRecord);
	//判断某车是否有车位
	public String getBerthBycar(Map<String,Object> map);
	//更新停车记录信息
	public int updateParkRecord(ParkRecord parkRecord);
	
	public ParkRecord getParkRecordRecentlyByCarId(String carId);
	//根据车牌号和小区id获取正在进行的停车记录
	public ParkRecord getParkRecordRecently(Map<String,Object> map);
	//根据车牌号和小区id获取停车记录信息
	public ParkRecord getParkRecord(Map<String,Object> map);
	//根据id单号获取停车信息
	public ParkRecord getParkRecordByOrderNum(String orderNum);
	//更新预交费支付方式
	public int updatePayWay(Map<String, Object> map);
	//新增停车支付订单
	public int addParkPayRecord(ParkRecord parkRecord);
//	//用户提交车位费
//	public int updateBerthMsg(BerthMessage berthMessage);
	//判断是否有车位信息
	public int getYZBerth(String phone);
	//更新车位管理费
	public int updateBerthCost(BerthMessage berthMessage);
}
