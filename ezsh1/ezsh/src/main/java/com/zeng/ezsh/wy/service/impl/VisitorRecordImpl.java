package com.zeng.ezsh.wy.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.zeng.ezsh.wy.dao.VisitorCodeMapper;
import com.zeng.ezsh.wy.dao.VisitorRecordMapper;
import com.zeng.ezsh.wy.entity.VisitorCode;
import com.zeng.ezsh.wy.entity.VisitorRecord;
import com.zeng.ezsh.wy.service.VisitorRecordService;
import com.zeng.ezsh.wy.utils.DateUtil;
@Service
public class VisitorRecordImpl implements VisitorRecordService {
	@Resource
	VisitorRecordMapper visitorRecordMapper;
	@Resource
	VisitorCodeMapper visitorCodeMapper;
	
	/**
	 * @author qwc
	 * 2017年12月15日下午7:46:34
	 * @param record
	 * @return 自动添加访问记录
	 */
	@Override
	public int insert(VisitorRecord record) {
		// TODO Auto-generated method stub
		return visitorRecordMapper.insert(record);
	}
	
	/**
	 * @author qwc
	 * 2017年12月17日下午3:40:31
	 * @param record
	 * @return 获取访客记录集合
	 */
	@Override
	public List<VisitorRecord> selectListByPrimaryKey(VisitorCode record) {
		// TODO Auto-generated method stub
		return visitorRecordMapper.selectListByPrimaryKey(record);
	}
	
	/**
	 * @author qwc
	 * 2017年12月28日下午11:10:58
	 * @param record
	 * @return 添加访客记录(访客按键开门时自动添加)
	 */
	@Override
	public int insertAuto(VisitorRecord record) {
		// TODO Auto-generated method stub
		VisitorCode rVisitorCode = record.getVisitorCode();
		
		// 设置访客码记录查询条件
		Map<String, Object> param = new HashMap<String,Object>();
		param.put("visitorPassword", rVisitorCode .getVisitorPassword());
		String timeString = DateUtil.dateToStr(new Date(), DateUtil.DATE_TIME_LINE);
		param.put("visitorValidityTimeEnd", timeString);
		
		// 根据访客码查询访客码记录
		rVisitorCode = visitorCodeMapper.selectCodeByDeviceIdAndCode(param);
		
		if(rVisitorCode!=null){
			// 执行访客记录自动添加
			record.setPtCodeId(rVisitorCode.getCodeId());
			record.setVisitoreTime(timeString);
			return visitorRecordMapper.insert(record);
		}else {
			return 0;
		}
	}

}
