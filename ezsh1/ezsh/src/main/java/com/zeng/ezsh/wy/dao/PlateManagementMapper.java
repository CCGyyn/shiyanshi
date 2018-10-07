package com.zeng.ezsh.wy.dao;

import java.util.List;
import java.util.Map;

import com.zeng.ezsh.wy.entity.BerthMessage;
import com.zeng.ezsh.wy.entity.HouseMessage;
import com.zeng.ezsh.wy.entity.CarMessage;
import com.zeng.ezsh.wy.entity.InviteRecord;
import com.zeng.ezsh.wy.entity.ParkRecord;
import com.zeng.ezsh.wy.entity.PlateCost;

public interface PlateManagementMapper {
	int updateBerthCost(BerthMessage berthMessage);
	
	String getBuildNameById(int buildId);
	
	String getPhoneByIdCard(String IDCard);
	
	int addCar(CarMessage car);
	
	int updateManageCost(Map<String,Object> map);
	
	Map<String,Object> selectUserBaseMessage(Map<String,Object> map);
	
	List<CarMessage> selectPlateMessageByPhone(Map<String,Object> map);
	
	List<HouseMessage> selectUserRoomNumber(String identityCard);
	
	String selectUserBuildingNumber(int buildId);
	
	int deleteByPlateNumber(String plateNumber);
	
	List<CarMessage> selectAllPlateMessage();
	
	int getManageId(String userCommunity);
    
	int addBerth(BerthMessage berthMessage);
	
	int deleteBerth(Map<String,Object> map);
	
	List<BerthMessage> getAllBerthMessge(Map<String,Object> map);
	
	List<BerthMessage> getUserBerthMessage(Map<String,Object> map);
	
	String getPlateNumberByBerth(Map<String, Object> map1);
	
	List<String> getPlateNumberListByBerth(Map<String, Object> map1);
	
	int buyBerth(Map<String,Object> map);
	
	int buyBerth2(BerthMessage berthMessage);
	
	int handPlateCost(Map<String,Object> map);
	
	int handPlateCost2(BerthMessage berthMessage);
	
	String getPlateNumberByBerthAndCommunity(Map<String,String> map);
	
	int deleteCarByplateNumebr(String plateNumber);
	
	int deletePlateAndCommunity(String plateNumber);
	
	int initializeBerth(Map<String,Object> map);
	
	int hireBerth(Map<String,Object> map);
	
	String getBerthByPlateNumber(Map<String, Object> map);
	
	int deleteBerthNumber(Map<String,String> map);
	
	BerthMessage searchBerthRecord(Map<String,Object> map);
	
	String getCommunitynameById(int userCommunityId);
	
	List<CarMessage> getUserCarmessage(Map<String,Object> map);
	
	int deleteUserCar(Map<String,Object> map);
	
	String judgeBerthByUser(Map<String,Object> map);
	
	List<CarMessage> getAllCarMessage();
	
	String getCarBerth(Map<String,Object> map);
	
	int deleteCar(String plateNumber);
	
	int addEnterRecord(ParkRecord parkRecord);
	
	int payParkCost(Map<String,Object> map);
	
	int addInvite(InviteRecord inviteRecord);
	
	List<String> getUserInvite(Map<String,Object> map);
	
	String judgeInvitedCar(String invitePlate);
	
	int cancelInvite(String invitePlate);
	
	int setCost(PlateCost plateCost);
	
	int alterBerthCost(Map<String,Object> map);
	
	double getPlateCostByCommunityId(int userCommunityId);
	
	List<PlateCost> getAllPlateCost(Map<String,Object> map);
	
	int deleteCarByBerth(Map<String,Object> map);
	
	int cancelHireBerth(Map<String,Object> map);
	
	int modifyCarMessage(Map<String,Object> map);
	
	List<BerthMessage> getBerthMessageByUser(Map<String,Object> map);
	
	List<InviteRecord> getAllInviteRecord();
	
	List<InviteRecord> getInviteRecordByPlateNumber(String invitePlate);
	
	List<ParkRecord> getAllParkRecord(Map<String,Object> map);
	
	int judgeBerthIsHire(Map<String,Object> map);
	
	int getCostCount();
	
	int getBerthCount();
	
	int getParkRecordCount();
	
	int deleteRecord(Map<String,Object> map);
	
	int addParkRecord(ParkRecord parkRecord);
	
	int updateLeaveTime(ParkRecord parkRecord);
	
	String getBerthBycar(Map<String,Object> map);
	
	int updateParkRecord(ParkRecord parkRecord);
	
	ParkRecord getParkRecordRecentlyByCarId(String carId);
	
	ParkRecord getParkRecordRecently(Map<String,Object> map);
	
	ParkRecord getParkRecord(Map<String,Object> map);
	
	ParkRecord getParkRecordByOrderNum(String orderNum);

	int updateCarMessageSelective(CarMessage car);
	
	int updatePayWay(Map<String,Object> map);
	
	int addParkPayRecord(ParkRecord parkRecord);
	
	int getBerthBytelephone(String telephone);
}