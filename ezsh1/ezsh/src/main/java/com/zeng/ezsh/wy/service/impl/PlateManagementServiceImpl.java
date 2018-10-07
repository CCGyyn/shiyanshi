package com.zeng.ezsh.wy.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.zeng.ezsh.wy.dao.PlateManagementMapper;
import com.zeng.ezsh.wy.entity.BerthMessage;
import com.zeng.ezsh.wy.entity.BerthMessage2;
import com.zeng.ezsh.wy.entity.HouseMessage;
import com.zeng.ezsh.wy.entity.CarMessage;
import com.zeng.ezsh.wy.entity.InviteRecord;
import com.zeng.ezsh.wy.entity.ParkRecord;
import com.zeng.ezsh.wy.entity.PlateCost;
import com.zeng.ezsh.wy.exception.PlateException;
import com.zeng.ezsh.wy.service.PlateManagementService;
import com.zeng.ezsh.wy.utils.JsonUtil;

@Service("plateManagementService")
public class PlateManagementServiceImpl implements PlateManagementService {
	
	private static Logger logger = LoggerFactory
			.getLogger(PlateManagementServiceImpl.class);
	@Resource
	PlateManagementMapper plateManagedao;
	
	public List<HouseMessage> getUserHouseMessage(String identityCard){
		List<HouseMessage> list = plateManagedao.selectUserRoomNumber(identityCard);
		if(list.size()!=0){
		for(HouseMessage houseMessage : list){
			String manageName = plateManagedao.getCommunitynameById(houseMessage.getManagerId());
			houseMessage.setManagerName(manageName);
			String buildName = plateManagedao.getBuildNameById(houseMessage.getBuildId());
			houseMessage.setBuildName(buildName);
		}
		}
		return list;
		
	}
	
	public double getPlateCost(int userCommunityId){
		return plateManagedao.getPlateCostByCommunityId(userCommunityId);
	}
	
	public int handManageCost(Map<String,Object> map){
		return plateManagedao.handPlateCost(map);
	}
	/**
	 * 更新了合同的时间
	 * @param berthMessage
	 * @return
	 */
	public int handManageCost2(BerthMessage berthMessage){
		return plateManagedao.handPlateCost2(berthMessage);
	}
	
	public int addCar(CarMessage car){
		int result = 0;
		try{
		 result = plateManagedao.addCar(car);
		 if(result == 0){
			 throw new PlateException("重复添加车辆");
		 }
		 return result;
		}catch(PlateException e){
			throw e;
		}
	}
	
	public List<CarMessage> getUserCarMessage(Map<String,Object> map){
		List<CarMessage> list = plateManagedao.getUserCarmessage(map);
		if(list.size()!=0){
			for(CarMessage carMessage : list){
				String manageName = plateManagedao.getCommunitynameById(carMessage.getUserCommunityId());
				carMessage.setHouseNumber(manageName+carMessage.getHouseNumber());
			}
		}
		return list;
		
		
	}
	
	public int modifyCarMessage(Map<String,Object> map){
		int result = 0;
		if(plateManagedao.judgeBerthByUser(map) != null){
			if(plateManagedao.judgeBerthIsHire(map) == 0){
				result = plateManagedao.modifyCarMessage(map);
				if(result == 1){
					return result;
				}else{
					return 0;
				}
			}else{
				throw new PlateException("该车位已出租，不能修改");
			}
		}else{
			throw new PlateException("该车位不存在");
		}
		
	}
	
	public int deleteCar(String plateNumber){
		return plateManagedao.deleteCarByplateNumebr(plateNumber);
	}
	
	public int inviteGuest(InviteRecord inviteRecord){
		try{
		int result = plateManagedao.addInvite(inviteRecord);
		if(result == 0){
			throw new PlateException("重复邀请,您已邀请该访客");
		}
		return result;
		}catch(PlateException e){
			throw e;
		}
	}
	
	public int cancelInvite(String invitePlate){
		return plateManagedao.cancelInvite(invitePlate);
	}
	
	public List<String> seeUserInvite(Map<String,Object> map){
		return plateManagedao.getUserInvite(map);
	}
	
	public String judgeInvitedCar(String invitePlate){
		return plateManagedao.judgeInvitedCar(invitePlate);
	}
	
	public int hireBerth(Map<String,Object> map,CarMessage carMessage){
		try{
			if(plateManagedao.judgeBerthIsHire(map) == 1){
				throw new PlateException("该车位已出租!");
			}
			plateManagedao.hireBerth(map);
		    plateManagedao.deleteCarByBerth(map);
//		    Map<String,Object> carmap = new HashMap<>();
//		    carmap.put("car", carMessage);
//		    carmap.put("berthNumber", carMessage.getBerthMessage().getBerthNumber());
		    return plateManagedao.addCar(carMessage);
		    
		}catch(PlateException e){
			throw e;
		}
		
	}
	
	public int cancelHireBerth(Map<String,Object> map){
		plateManagedao.deleteCarByBerth(map);
		return plateManagedao.cancelHireBerth(map);
	}
	
	public int setBerth(BerthMessage berthMessage){
		int result = 0;
		try{
		 result = plateManagedao.addBerth(berthMessage);
		if(result == 0){
			throw new PlateException("该车位重复添加!");
		}
		return result;
		}catch(PlateException e){
			throw e;
			
		}
	}
	
	public int buyBerth(Map<String,Object> map){
		return plateManagedao.buyBerth(map);
	}
	
	public int buyBerth2(BerthMessage berthMessage){
		return plateManagedao.buyBerth2(berthMessage);
	}
	public int deleteBerth(Map<String,Object> map){
		return plateManagedao.deleteBerth(map);
	}
	
	public BerthMessage searchBerthRecord(Map<String,Object> map){
		return plateManagedao.searchBerthRecord(map);
	}
	
	public int cancelBuyBerth(Map<String,Object> map){
		plateManagedao.deleteCarByBerth(map);
		return plateManagedao.initializeBerth(map);
	}
	
	public List<BerthMessage> getUserBerthMessage(Map<String,Object> map){
		List<BerthMessage> list = plateManagedao.getUserBerthMessage(map);
		for(BerthMessage berthMessage : list){
			Map<String,Object> map1 = new HashMap<>();
			map1.put("userCommunityId", (Integer)map.get("userCommunityId"));
			map1.put("berthNumber", berthMessage.getBerthNumber());
			String plateNumber = plateManagedao.getPlateNumberByBerth(map1);
			if(plateNumber != null){
				berthMessage.setPlateNumber(plateNumber);
			}
		}
		return list;
	}
	//用于获取车牌号不止一个的情况 author:y
	public List<BerthMessage2> getUserBerthMessage2(Map<String, Object> map){
		if(map.isEmpty()){
			return null;
		}
		List<BerthMessage> list = plateManagedao.getUserBerthMessage(map);//map：小区号-号码
		BerthMessage2 berthMessage2 = new BerthMessage2();
		List<BerthMessage2> retList = new ArrayList<BerthMessage2>();
		
		for(BerthMessage berthMessage : list){
			berthMessage2.setBerthMessage(berthMessage);
			Map<String,Object> map1 = new HashMap<>();
			map1.put("userCommunityId", (Integer)map.get("userCommunityId"));
			map1.put("berthNumber", berthMessage.getBerthNumber());
			List<String> plateNum = plateManagedao.getPlateNumberListByBerth(map1);
			if(plateNum != null){
				berthMessage2.setPlateNum(plateNum);
			}
			retList.add(berthMessage2);
		}
		
		return retList;
	}
	
	public List<BerthMessage> getAllBerthMessage(Map<String,Object> map){
		return plateManagedao.getAllBerthMessge(map);
	}
	
	public int getBerthCount(){
		return plateManagedao.getBerthCount();
	}

	public List<BerthMessage> getBerthMessageByUser(Map<String,Object> map){
		return plateManagedao.getBerthMessageByUser(map);
	}
	
	public int setPlateCost(PlateCost plateCost){
		int result = 0;
		try{
		result = plateManagedao.setCost(plateCost);
		if(result == 0){
			throw new PlateException("重复添加!");
		}
		return result;
		}catch(PlateException e){
			throw e;
		}
	}
	
	public int modifyPlateCost(Map<String,Object> map){
		return plateManagedao.alterBerthCost(map);
	}
	
	public List<PlateCost> getAllPlateCost(Map<String,Object> map){
		return plateManagedao.getAllPlateCost(map);
	}
	
	public int getCostCount(){
		return plateManagedao.getCostCount();
	}
	
	public List<CarMessage> getAllCarMessage(){
		return plateManagedao.getAllCarMessage();
	}
	
	public List<InviteRecord> getAllInviteRecord(){
		return plateManagedao.getAllInviteRecord();
	}
	
	public List<InviteRecord> getInviteRecordByPlateNumber(String invitePlate){
		return plateManagedao.getInviteRecordByPlateNumber(invitePlate);
	}
	
	public List<ParkRecord> getAllParkRecord(Map<String,Object> map){
		return plateManagedao.getAllParkRecord(map);
	}
	
	public int getParkRecordCount(){
		return plateManagedao.getParkRecordCount();
	}
	
	public int deleteRecord(Map<String,Object> map){
		return plateManagedao.deleteRecord(map);
	}
	
	public int addParkRecord(ParkRecord parkRecord){
		return plateManagedao.addParkRecord(parkRecord);
	}
	
	public int updateLeaveTime(ParkRecord parkRecord){
		return plateManagedao.updateLeaveTime(parkRecord);
	}
	
	public String getBerthBycar(Map<String,Object> map){
		return plateManagedao.getBerthBycar(map);
	}
	
	public int updateParkRecord(ParkRecord parkRecord){
		return plateManagedao.updateParkRecord(parkRecord);
	}
	
	public ParkRecord getParkRecordRecentlyByCarId(String carId){
		ParkRecord parkRecord = plateManagedao.getParkRecordRecentlyByCarId(carId);
		return parkRecord;
	}
	
	public ParkRecord getParkRecordRecently(Map<String,Object> map){
		return plateManagedao.getParkRecordRecently(map);
	}
	
	public ParkRecord getParkRecord(Map<String,Object> map){
		return plateManagedao.getParkRecord(map);
	}
	
	public ParkRecord getParkRecordByOrderNum(String orderNum){
		return plateManagedao.getParkRecordByOrderNum(orderNum);
	}

	@Override
	public int modifyCar(CarMessage car) {
		return plateManagedao.updateCarMessageSelective(car);
	}

	@Override
	public int deleteUserCar(Map<String, Object> map) {
		
		return plateManagedao.deleteUserCar(map);
	}

	@Override
	public int updatePayWay(Map<String,Object> map) {
		
		return plateManagedao.updatePayWay(map);
	}

	@Override
	public int addParkPayRecord(ParkRecord parkRecord) {
		return plateManagedao.addParkPayRecord(parkRecord);
	}

	@Override
	public int getYZBerth(String phone) {
		return plateManagedao.getBerthBytelephone(phone);
	}

	@Override
	public int updateBerthCost(BerthMessage berthMessage) {

		return plateManagedao.updateBerthCost(berthMessage);
	}
	
	
}
