package com.zeng.ezsh.wy.service;

import java.util.List;
import java.util.Map;

import com.zeng.ezsh.wy.entity.DeviceLock;
import com.zeng.ezsh.wy.entity.ResidentialUser;
import com.zeng.ezsh.wy.entity.VisitorCode;

public interface DeviceLockService {
	/*获取楼宇的设备锁（移动端）*/
	public DeviceLock selectDeviceByParam(DeviceLock record);
	
	/*根据条件获取楼宇的门锁设备信息集合（移动端），用于获取出业主有权限开哪些门*/
	List<DeviceLock> selectListDeviceByParamA(ResidentialUser tokenModel);
	
	/*检测是否在线（移动端）*/
	public int checkDeviceOnLineStatus(String DeviceId);
	
	/*添加访客密码（移动端）*/
	public int generateVisitPassword(Map<String, Object> param);
	
	/*远程开门（移动端）*/
	public Object openDoor(Map<String, Object> param);
	
	/*移动端获取楼宇设备锁列表（后台）*/
	public List<DeviceLock> selectListDeviceByParamA(DeviceLock record);
	
	/*移动端获取楼宇设备锁列表（移动端）*/
	public List<DeviceLock> selectListDeviceByParamM(DeviceLock record);
	
	/*添加楼宇设备锁（后台）*/
	public int insert(DeviceLock record);
	
	/*检测设备锁是否存在（后台添加设备锁时）*/
	public int checkDeviceIsOn(DeviceLock record);
	
	/*更新楼宇设备锁信息（后台）*/
	public int updateByPrimaryKeySelective(DeviceLock record);
	
	/*删除楼宇设备锁信息（后台）*/
	public int deleteByPrimaryKey(DeviceLock record);
	
	/*根据设备锁的ID获取设备信息*/
	public DeviceLock selectDeviceByDeviceId(DeviceLock record);
}
