package com.zeng.ezsh.wy.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import net.sf.json.JSONObject;

import org.springframework.stereotype.Service;

import com.zeng.ezsh.opendoor.config.OpenDoorConfig;
import com.zeng.ezsh.wy.dao.VisitorCodeMapper;
import com.zeng.ezsh.wy.dao.VisitorRecordMapper;
import com.zeng.ezsh.wy.entity.DeviceLock;
import com.zeng.ezsh.wy.entity.VisitorCode;
import com.zeng.ezsh.wy.entity.VisitorRecord;
import com.zeng.ezsh.wy.open.door.utils.OpenDoorPojo;
import com.zeng.ezsh.wy.service.DeviceLockService;
import com.zeng.ezsh.wy.service.VisitorService;
import com.zeng.ezsh.wy.utils.DateUtil;
import com.zeng.ezsh.wy.utils.HttpUrlConnectionUtil;
import com.zeng.ezsh.wy.utils.SerializeUtil;

@Service
public class VisitorServiceImpl implements VisitorService {
	@Resource
	DeviceLockService deviceLockService;
	@Resource
	VisitorCodeMapper visitorCodeMapper;
	@Resource
	VisitorRecordMapper visitorRecordMapper;

	/**
	 * @author qwc 2017年10月20日下午8:06:18
	 * @param record
	 * @return 添加访客通行证（访客密码）
	 */
	@Override
	public  synchronized int insert(VisitorCode record, String deviceId, String devicePass) {
		// TODO Auto-generated method stub
		
		// 封装添加门锁密码的接口的请求参数
		Map<String, Object> param = new HashMap<String, Object>();
		// 门锁设备ID
		param.put("deviceId", deviceId);
		// 门锁设备密码（每个门锁都只有一个）
		param.put("devicePsw", devicePass);
		
		// 访客密码有效期
		param.put("date", record.getVisitorValidityTimeEnd());
		
		// 访客密码
		
		param.put("keyId", record.getVisitorPassword());
		
		// 执行添加
		int status = deviceLockService.generateVisitPassword(param);
		
		System.out.println("record.getPtManagerId()"+record.getPtManagerId());
		// 返回结果
		if(status == 1){
			record.setVisitorValidityTimeEnd(DateUtil.todayLastTime()); //设置保存到数据库字段的访问码有效期
			return visitorCodeMapper.insertSelective(record); // 执行插入
		} else {
			return status;
		}
	}

	/**
	 * @author qwc 2017年10月21日下午8:50:35
	 * @param record
	 * @return 检测是否重新设置
	 */
	@Override
	public VisitorCode checkByParam(VisitorCode record) {
		// TODO Auto-generated method stub
		return visitorCodeMapper.checkByParam(record);
	}

	/**
	 * @author qwc 2017年10月21日下午10:58:06
	 * @param record
	 * @return 获取个人设置的同行证列表
	 */
	@Override
	public List<VisitorCode> selectCodeListByParam(VisitorCode record) {
		// TODO Auto-generated method stub
		return visitorCodeMapper.selectCodeListByParam(record);
	}

	/**
	 * @author qwc 2017年12月14日下午10:55:19
	 * @param record
	 * @return 远程开门
	 */
	@Override
	public int remoteOpenDoor(DeviceLock record) {
		// TODO Auto-generated method stub
		
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("deviceId", record.getDeviceId());
		param.put("devicePsw", record.getDevicePass());
		OpenDoorPojo result = (OpenDoorPojo) deviceLockService.openDoor(param);
		if (result.getResult().equals("0")) {
			/*VisitorRecord visitorRecord = new VisitorRecord();
			visitorRecord.setPtCodeId(record.getCodeId());
			// 设置访问时间
			visitorRecord.setVisitoreTime(DateUtil.dateToStr(new Date(),
					DateUtil.DATE_TIME_LINE));
			visitorRecordMapper.insert(visitorRecord);*/
			return 1;
		} else if (result.getResult().equals("203")) {
			return -1;
		} else {
			return 0;
		}
	}
	
	/**
	 * 根据用户ID和楼宇ID获取访客码信息
	 */
	@Override
	public VisitorCode selectCodeByParam(VisitorCode record) {
		// TODO Auto-generated method stub
		return visitorCodeMapper.selectCodeByParam(record);
	}

}
