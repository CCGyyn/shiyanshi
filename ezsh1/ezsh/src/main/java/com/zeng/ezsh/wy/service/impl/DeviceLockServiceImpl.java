package com.zeng.ezsh.wy.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import net.sf.json.JSONObject;

import org.springframework.stereotype.Service;

import com.zeng.ezsh.opendoor.config.OpenDoorConfig;
import com.zeng.ezsh.wy.dao.DeviceLockMapper;
import com.zeng.ezsh.wy.dao.UMsIdsMapper;
import com.zeng.ezsh.wy.entity.DeviceLock;
import com.zeng.ezsh.wy.entity.ResidentialUser;
import com.zeng.ezsh.wy.entity.UMsIds;
import com.zeng.ezsh.wy.entity.VisitorCode;
import com.zeng.ezsh.wy.open.door.utils.OpenDoorPojo;
import com.zeng.ezsh.wy.service.DeviceLockService;
import com.zeng.ezsh.wy.utils.DateUtil;
import com.zeng.ezsh.wy.utils.HttpUrlConnectionUtil;

@Service
public class DeviceLockServiceImpl implements DeviceLockService {
	@Resource
	DeviceLockMapper deviceLockMapper;
	@Resource
	UMsIdsMapper uMsIdsMapper;

	/**
	 * @author qwc 2017年10月22日上午12:37:37
	 * @param record
	 * @return 获取单个楼宇设备锁信息
	 */
	@Override
	public DeviceLock selectDeviceByParam(DeviceLock record) {
		// TODO Auto-generated method stub
		return deviceLockMapper.selectDeviceByParam(record);
	}

	/**
	 * @author qwc 2017年10月22日上午10:47:49
	 * @return 检测锁是否在线
	 */
	@Override
	public int checkDeviceOnLineStatus(String DeviceId) {
		// TODO Auto-generated method stub
		// 设置请求参数
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("DEVICE_ID", DeviceId);
		JSONObject jsonObject = JSONObject.fromObject(HttpUrlConnectionUtil
				.urlPost(OpenDoorConfig.CheckDeviceOnLineStatusUrl, param));
		
		// 将获取到的json转化成实体类
		OpenDoorPojo checkDeviceOnLineStatusPojo = (OpenDoorPojo) JSONObject
				.toBean(jsonObject, OpenDoorPojo.class);
		int lastIndex = checkDeviceOnLineStatusPojo.getMsg().lastIndexOf(":");
		
		// 获取设备在线状态:0-在线1-不在线
		int status = Integer.parseInt(checkDeviceOnLineStatusPojo.getMsg()																		
				.substring(lastIndex + 1).trim());
		if (status > 0) {// 在线
			return 1;
		} else {
			return 0;
		}
	}
	
	/**
	 * @author qwc
	 * 2017年12月14日下午10:43:21
	 * @param param
	 * @return 添加访客密码
	 */
	@Override
	public int generateVisitPassword(Map<String, Object> param) {
		// TODO Auto-generated method stub
		Map<String, Object> params = new HashMap<String, Object>();
		// 门锁设备ID
		params.put("DEVICE_ID", param.get("deviceId"));
		// 门锁设备密码（每个门锁都只有一个）
		params.put("DEVICEPSW", param.get("devicePsw"));
		// 设置访客密码类型
		params.put("KEYTYPE", "2");
		// 设置访客密码数量
		params.put("KEYCOUNT", "1");
		// 设置访客密码
		params.put("KEYID", param.get("keyId"));
		// 设置访客密码有效期
		params.put("DATE", param.get("date"));
		
		// 执行请求
		System.out.println("添加访客密码的请求参数：" + JSONObject.fromObject(params).toString());
		JSONObject jsonObject = JSONObject.fromObject(HttpUrlConnectionUtil
				.urlPost(OpenDoorConfig.ServiceAddKey, params));
		System.out.println("添加访客密码的响应数据：" + jsonObject.toString());
		//参考成功设置示例：{"msg":"start to send msg","result":0,"data":"{\"DEVICEID\":\"58A39F3C\",\"OPERATE\":\"27\"}"}

		//对返回来的json数据进行分析
		JSONObject jsonObject2 = JSONObject.fromObject(jsonObject.get("data"));
		if(jsonObject2.has("CMD") && jsonObject2.get("CMD").equals("01")){
			return 2; // 不在线状态
		} else if(jsonObject.get("result").toString().equals("0")){
			return 1; //设置成功
		} else {
			return 0; // 设置失败
		}
	}
	
	/**
	 * @author qwc
	 * 2017年12月15日下午4:12:46
	 * @param record
	 * @return 远程开门
	 */
	@Override
	public Object openDoor(Map<String, Object> param) {
		// TODO Auto-generated method stub
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("DEVICE_ID", param.get("deviceId"));
		params.put("DEVICEPSW", param.get("devicePsw"));
		// 接受响应数据
		System.out.println("----开门响应-----");
		JSONObject jsonObject = JSONObject.fromObject(HttpUrlConnectionUtil
				.urlPost(OpenDoorConfig.OpenDoorRemoteUrl, params));
		System.out.println("开门响应》》" + jsonObject.toString());
		// 将获取到的json转化成实体类
		OpenDoorPojo statusPojo = (OpenDoorPojo) JSONObject
				.toBean(jsonObject, OpenDoorPojo.class);
		return statusPojo;
	}
	
	/**
	 * @author qwc
	 * 2017年12月17日下午4:53:12
	 * @param record
	 * @return 获取楼宇设备锁列表(后台)
	 */
	@Override
	public List<DeviceLock> selectListDeviceByParamA(DeviceLock record) {
		// TODO Auto-generated method stub
		return deviceLockMapper.selectListDeviceByParamA(record);
	}
	
	/**
	 * @author qwc
	 * 2017年12月17日下午4:53:32
	 * @param record
	 * @return 获取楼宇设备锁列表(移动端)
	 */
	@Override
	public List<DeviceLock> selectListDeviceByParamM(DeviceLock record) {
		// TODO Auto-generated method stub
		return deviceLockMapper.selectListDeviceByParamM(record);
	}
	
	/**
	 * @author qwc
	 * 2017年12月17日下午4:53:37
	 * @param record
	 * @return 添加楼宇楼宇设备锁列表(后台)
	 */
	@Override
	public int insert(DeviceLock record) {
		// TODO Auto-generated method stub
		return deviceLockMapper.insert(record);
	}
	
	/**
	 * @author qwc
	 * 2017年12月17日下午5:42:23
	 * @param record
	 * @return 检测设备锁是否存在（后台添加设备锁时）
	 */
	@Override
	public int checkDeviceIsOn(DeviceLock record) {
		// TODO Auto-generated method stub
		return deviceLockMapper.checkDeviceIsOn(record);
	}
	
	/**
	 * @author qwc
	 * 2017年12月24日上午10:44:32
	 * @param record
	 * @return 更新楼宇设备锁
	 */
	@Override
	public int updateByPrimaryKeySelective(DeviceLock record) {
		// TODO Auto-generated method stub
		return deviceLockMapper.updateByPrimaryKeySelective(record);
	}
	
	/**
	 * @author qwc
	 * 2017年12月24日上午10:44:48
	 * @param record
	 * @return 删除楼宇设备锁
	 */
	@Override
	public int deleteByPrimaryKey(DeviceLock record) {
		// TODO Auto-generated method stub
		return deviceLockMapper.deleteByPrimaryKey(record);
	}
	
	/**
	 * 根据设备锁的ID获取设备信息
	 */
	@Override
	public DeviceLock selectDeviceByDeviceId(DeviceLock record) {
		// TODO Auto-generated method stub
		return deviceLockMapper.selectDeviceByDeviceId(record);
	}
	
	/**
	 * @description 根据条件获取楼宇的门锁设备信息集合（移动端），用于获取出业主有权限开哪些门
	 */
	@Override
	public List<DeviceLock> selectListDeviceByParamA(ResidentialUser tokenModel) {
		// TODO Auto-generated method stub
		if (tokenModel.getUmsIdsInfo() == null) {
			return null;
		}
		int userId = tokenModel.getUserId();
		UMsIds uIds = new UMsIds();
		uIds.setpUserId(userId);
		List<UMsIds> list = uMsIdsMapper.selectAccountList(uIds);
		List<Integer> deviceList = new ArrayList<>();
		Iterator<UMsIds> it = list.iterator();
		while (it.hasNext()) {
			UMsIds uMsIds = new UMsIds();
			uMsIds = it.next();
			if(uMsIds.getPrivilege()) {
				deviceList.add(uMsIds.getpBuildId());
			}
		}
		Map<String, Object> param = new HashMap<String, Object>();
		if(deviceList.isEmpty() || deviceList.size()==0) {
			return null;
		}
		param.put("ptManagerId", tokenModel.getUmsIdsInfo().getpManagerId());
		param.put("deviceList", deviceList);
		return deviceLockMapper.selectListDeviceByParamA(param);
	}

}
