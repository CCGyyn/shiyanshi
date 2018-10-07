package com.zeng.ezsh.wy.dao;

import java.util.List;
import java.util.Map;

import com.zeng.ezsh.wy.entity.DeviceLock;

public interface DeviceLockMapper {
	/*获取楼宇的设备锁*/
	DeviceLock selectDeviceByParam(DeviceLock record);
	
	/*根据条件获取楼宇的门锁设备信息集合（移动端），用于获取出业主有权限开哪些门*/
	List<DeviceLock> selectListDeviceByParamA(Map<String, Object> param);
	
	/*移动端获取楼宇设备锁列表（后台）*/
	List<DeviceLock> selectListDeviceByParamA(DeviceLock record);
	
	/*移动端获取楼宇设备锁列表（移动端）*/
	List<DeviceLock> selectListDeviceByParamM(DeviceLock record);
	
	/*添加楼宇设备锁（后台）*/
	int insert(DeviceLock record);
	
	/*检测设备锁是否存在（后台添加设备锁时）*/
	int checkDeviceIsOn(DeviceLock record);
	
	/*更新楼宇设备锁信息（后台）*/
	int updateByPrimaryKeySelective(DeviceLock record);
	
	/*删除楼宇设备锁信息（后台）*/
	int deleteByPrimaryKey(DeviceLock record);
	
	/*根据设备锁的ID获取设备信息*/
	DeviceLock selectDeviceByDeviceId(DeviceLock record);
	
	/*DeviceLock selectDeviceByLockId(DeviceLock record);*/
	
    /*int deleteByPrimaryKey(String deviceId);

    int insertSelective(DeviceLock record);

    DeviceLock selectByPrimaryKey(String deviceId);

    int updateByPrimaryKeySelective(DeviceLock record);

    int updateByPrimaryKey(DeviceLock record);*/
}