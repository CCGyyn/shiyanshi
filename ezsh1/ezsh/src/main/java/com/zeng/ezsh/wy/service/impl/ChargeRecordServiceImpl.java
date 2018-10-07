package com.zeng.ezsh.wy.service.impl;

import com.zeng.ezsh.alipay.service.AlipayService;
import com.zeng.ezsh.wechatpay.service.WechatPayAppService;
import com.zeng.ezsh.wy.dao.*;
import com.zeng.ezsh.wy.entity.*;
import com.zeng.ezsh.wy.service.ChargeRecordService;
import com.zeng.ezsh.wy.utils.CalcChargePriceUtil;
import com.zeng.ezsh.wy.utils.DateUtil;
import com.zeng.ezsh.wy.utils.SerializeUtil;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class ChargeRecordServiceImpl implements ChargeRecordService {
	@Resource
    ChargeRecordMapper chargeRecordMapperDao;
	@Resource
    ManagementMapper managementMapperDao;
	@Resource
    BuildingMapper buildMapperDao;
	@Resource
    RoomMapper roomMapperDao;
	@Resource
    ChargeRoomIdsMapper chargeRoomIdsMapperDao;
	@Resource
    ChargeInfoMapper chargeInfoMapperDao;
    @Resource
    UMsIdsMapper uMsIdsMapper;
	@Resource
    ChargeOrderMapper chargeOrderMapper;
	@Resource
    AlipayService alipayService;
	@Resource
    WechatPayAppService wechatPayAppService;

	/**
	 * @author qwc 2017年9月21日下午9:21:54
	 * @return 根据房间收费项目批量生成应收费记录（后台定时实现）
	 */
	@Override
	public int insertChargeRecordBatch() {

		int amount = 0;
		int amountChargeRecordInfo = 0;
		List<Management> manageList = new ArrayList<Management>();
		List<Building> buildingList = new ArrayList<Building>();
		List<Room> roomList = new ArrayList<Room>();

		// 获取出所有的小区
		manageList = managementMapperDao.findAll();
		// 对出所有的小区进行迭代遍历
		Iterator<Management> it = manageList.iterator();
		while (it.hasNext()) {
			Map<String, Object> param = new HashMap<String, Object>();
			// 获取出管理处
			Management management = it.next();
			// 设置查询条件，准备查询出该小区下的所有楼宇
			param.put("buildManagerId", management.getManagerId());
			// 根据小区ID获取出所有楼栋
			buildingList = buildMapperDao.findBuildingByParamToAdmin(param);

			// 迭代遍历楼宇
			Iterator<Building> buildIt = buildingList.iterator();
			while (buildIt.hasNext()) {
				Building build = new Building();
				build = buildIt.next();
				// 设置查询条件，准备查询出该楼宇下的所有房间
				Map<String, Object> paramOne = new HashMap<String, Object>();
				paramOne.put("buildId", build.getBuildId());
				// 根据楼栋ID获取出所有的房间
				roomList = roomMapperDao.findRoomChargeByParam(paramOne);

				// 应收费记录集合
				List<ChargeRecord> chargeRecordList = new ArrayList<ChargeRecord>();
				// 应收费记录基本信息集合
				List<ChargeInfo> chargeRecordInfoList = new ArrayList<ChargeInfo>();

				// 遍历某楼宇下的所有房间
				for (int i = 0; i < roomList.size(); i++) {
					Room room = new Room();
					room = roomList.get(i);
                    Integer roomId=room.getRoomId();
                    UMsIds uMsIds=uMsIdsMapper.selectByRoomId(roomId);

					// 如果房间的客户信息和房间收费项目不为空，自动生成当月的收费记录
					if (uMsIds!=null&&!(room.getChargeItemIdsList()).isEmpty()) {
					    Integer userId=uMsIds.getpUserId();
						ChargeInfo chargeInfo = new ChargeInfo();
						chargeInfo.setPtRoomId(room.getRoomId());
						SimpleDateFormat dateSDF = new SimpleDateFormat(
								"yyyy-MM");
						// 设置应收费用基本信息所属月份
						chargeInfo.setChargeOfMonth(dateSDF.format(new Date()));
						// 添加到list集合中
						chargeRecordInfoList.add(chargeInfo);
						
						
						for (int k = 0; k < (room.getChargeItemIdsList())
								.size(); k++) {
							ChargeRecord charge = new ChargeRecord();// 创建应收费记录对象
							BigDecimal totalPrice = new BigDecimal(0);
							ChargeItem chargeItem=room.getChargeItemIdsList()
									.get(k).getChargeItemInfo();

							charge.setpManagerId(management.getManagerId());// 设置管理处ID
							charge.setpBuildingId(build.getBuildId());// 设置楼宇ID
							charge.setpRoomId(room.getRoomId());// 设置房间号ID
							
//							Integer ptChargeInfoId = 
//							charge.setPtChargeInfoId(ptChargeInfoId);
							
							charge.setpChargeItemId(room.getChargeItemIdsList()
									.get(k).getpChargeItemId());// 设置收费项目ID
							charge.setUnitPrice(chargeItem.getChargeBillingUnitPrice());// 设计单价
							totalPrice = CalcChargePriceUtil.CalcCharge(chargeItem,room);
							charge.setTotalPrice(totalPrice);

							if(chargeItem.getChargeBillingWay()==1) {
								charge.setChargeAmount(totalPrice.divide(charge.getUnitPrice()));
							}
							charge.setpUserId(userId);
							charge.setChargeOfDate(DateUtil.dateToStr(new Date(),1));
							charge.setChargeItemName(chargeItem.getChargeName());
							charge.setChargeEndDate(DateUtil.theSameMonthLastDay());// 获取当月的最后一天
							chargeRecordList.add(charge);

							if (chargeRecordList.size() > 50) {
								// 如果chargeRecordList集合的size大于50且未遍历到最后一个房间，批量插入记录
								amount += chargeRecordMapperDao
										.insertChargeRecordBatch(
												chargeRecordList);
								chargeRecordList.clear();
							}
						}
					}

					// 如果chargeRecordList集合的size小于50且遍历到最后一个房间，批量插入收费详细信息记录
					if (!chargeRecordList.isEmpty()
							&& (chargeRecordList.size() == 50
									|| (chargeRecordList.size() < 50
											))) {//&& i == (roomList.size() - 1)
						// 批量插入
						amount += chargeRecordMapperDao
								.insertChargeRecordBatch(chargeRecordList);
						chargeRecordList.clear();
					}

					// 如果chargeRecordInfoList集合的size小于50且遍历到最后一个房间，批量插入收费详细信息记录
					if (!chargeRecordInfoList.isEmpty()
							&& (chargeRecordInfoList.size() == 50
									|| (chargeRecordInfoList.size() < 50
											))) {//&& i == (roomList.size() - 1)
						// 批量插入
						amountChargeRecordInfo += chargeInfoMapperDao
								.insertChargeInfoBatch(chargeRecordInfoList);// 批量插入应收费基本信息
						ChargeRecord chargeRecord = new ChargeRecord();
						chargeRecord.setChargeOfDate(DateUtil.dateToStr(new Date(),1));
						chargeRecord.setpRoomId(roomId);
						chargeRecord.setPtChargeInfoId(chargeRecordInfoList.get(0).getChargeInfoId());
						chargeRecordList.add(chargeRecord);
						
						chargeRecordMapperDao.updateChargeRecordBatch(chargeRecordList);
						chargeRecordList.clear();
						chargeRecordInfoList.clear();
					} else if (chargeRecordInfoList.size() > 50
							&& i != (roomList.size() - 1)) {
						// 如果chargeRecordInfoList集合的size大于50且未遍历到最后一个房间，批量插入收费基本信息记录
						amountChargeRecordInfo += chargeInfoMapperDao
								.insertChargeInfoBatch(chargeRecordInfoList);// 批量插入应收费基本信息
						chargeRecordMapperDao.updateChargeRecordBatch(chargeRecordList);
						chargeRecordInfoList.clear();
					}
				}
			}
		}
		System.out.println("amountChargeRecordInfo>>" + amountChargeRecordInfo);
		return amount;
	}

	/**
	 * @author qwc 2017年9月22日下午4:25:04
	 * @param record
	 * @return 根据条件获取收费项目集合
	 */
	@Override
	public List<ChargeRecord> selectChargeRecordByParam(ChargeRecord record) {
		// TODO Auto-generated method stub
		return chargeRecordMapperDao.selectChargeRecordByParam(record);
	}

	/**
	 * @author qwc 2017年9月24日下午4:15:42
	 * @param recordList
	 * @return 批量更新应收费记录
	 */
	@Override
	public int updateChargeRecordBatch(List<ChargeRecord> recordList) {
		// TODO Auto-generated method stub
		return chargeRecordMapperDao.updateChargeRecordBatch(recordList);
	}

	/**
	 * @author qwc 2017年10月15日下午4:27:26
	 * @param param
	 * @return 获取缴费记录
	 */
	@Override
	public List<ChargeInfo> selectChargeListByParam(Map<String, Object> param) {
		// TODO Auto-generated method stub
		return chargeInfoMapperDao.selectChargeListByParam(param);
	}

	/**
	 * @author qwc 2017年10月15日下午9:02:35
	 * @param record
	 * @return 检测缴费状态
	 */
	@Override
	public int checkPayStatus(ChargeInfo record) {
		// TODO Auto-generated method stub
		return chargeInfoMapperDao.checkPayStatus(record);
	}

	/**
	 * @author qwc 2017年10月15日下午10:08:05
	 * @param chargeInfo
	 * @return 生成物业费支付订单信息
	 */
	@Override
	public String ceratePayInfo(ChargeInfo chargeInfo, String payMethod,
                                ResidentialUser tokenModel, Map<String, Object> additionMap) {
		BigDecimal totalPrice = new BigDecimal(0);
		// 获取出准备缴费的物业费记录
		chargeInfo = chargeInfoMapperDao.selectChargeInfoReacord(chargeInfo);
		// 计算出总价钱
		if (chargeInfo.getChargeRecordList() != null) {
			for (ChargeRecord record : chargeInfo.getChargeRecordList()) {
				totalPrice.add(record.getTotalPrice());
			}
		} else {
			return null;
		}

		ChargeOrder record = new ChargeOrder();
		String outTradeNo = SerializeUtil.generateUUID();
		additionMap.put("outTradeNo", outTradeNo);
		record.setOutTradeNo(outTradeNo); // 设置商户系统后台生成的唯一订单号
		record.setPayFee(totalPrice); // 设置总支付金额
		record.setPayTime(
				DateUtil.dateToStr(new Date(), DateUtil.DATE_TIME_LINE));// 设置支付时间
		record.setPtChargeInfoId(chargeInfo.getChargeInfoId()); // 设置物业费基本信息ID
		record.setUserName(tokenModel.getUserName());
		record.setPtUserId(tokenModel.getUserId());
		if (tokenModel.getUmsIdsInfo() != null) {
			record.setManagerName(tokenModel.getUmsIdsInfo().getManagerName());// 设置小区名
			record.setPtManagerId(tokenModel.getUmsIdsInfo().getpManagerId());// 设置小区ID
		} else {
			return null;
		}
		// 设置支付方式
		if (payMethod.equals("aliPay")) {
			record.setPayClassify(1);
		} else {
			record.setPayClassify(2);
		}

		// 插入物业费支付订单
		chargeOrderMapper.insertSelective(record);

		// 判断是微信还是阿里支付
		if (payMethod.equals("aliPay")) {
			return alipayService.PropertyCosts(chargeInfo,
					totalPrice.toString(), additionMap);
		} else {
			Map<String, String> retMap = new HashMap<String, String>();
			retMap = wechatPayAppService.PropertyCosts(chargeInfo,
					totalPrice.toString(), additionMap);
			if (retMap != null) {
				return JSONObject.fromObject(retMap).toString();
			} else {
				return null;
			}
		}

	}

	/**
	 * @author qwc 2017年10月15日下午10:31:54
	 * @param record
	 * @return 获取单个缴费信息
	 */
	@Override
	public ChargeInfo selectChargeInfoReacord(ChargeInfo record) {
		// TODO Auto-generated method stub
		return chargeInfoMapperDao.selectChargeInfoReacord(record);
	}

	@Override
	public int updateChargeInfoPayStatus(ChargeInfo record) {
		// TODO Auto-generated method stub
		return chargeInfoMapperDao.updateChargeInfoPayStatus(record);
	}

	/**
	 * @author qwc 2017年10月25日下午1:54:16
	 * @param record
	 * @return 添加单个应收费记录
	 */
	@Override
	public int insertSelective(ChargeRecord record) {
		ChargeInfo chargeInfo = new ChargeInfo();
		chargeInfo.setPtRoomId(record.getpRoomId());
		chargeInfo.setChargeOfMonth(record.getChargeOfDate());
		chargeInfoMapperDao.insertSelective(chargeInfo);
		return chargeRecordMapperDao.insertSelective(record);
	}

	/**
	 * @author qwc 2017年10月25日下午4:08:45
	 * @param record
	 * @return 删除应收费记录
	 */
	@Override
	public int deleteByPrimaryKey(ChargeRecord record) {
		// TODO Auto-generated method stub
		return chargeRecordMapperDao.deleteByPrimaryKey(record);
	}

	/**
	 * @author qwc 2017年10月25日下午4:21:24
	 * @param record
	 * @return 更新应收费记录
	 */
	@Override
	public int updateByPrimaryKeySelective(ChargeRecord record) {
		return chargeRecordMapperDao.updateByPrimaryKeySelective(record);
	}

}
