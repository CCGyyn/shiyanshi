package com.zeng.ezsh.wy.service.impl;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.zeng.ezsh.alipay.service.AlipayService;
import com.zeng.ezsh.wy.dao.FamilyEducateInfoMapper;
import com.zeng.ezsh.wy.dao.UserTeacherFeeMapper;
import com.zeng.ezsh.wy.entity.FamilyEducateInfo;
import com.zeng.ezsh.wy.entity.ResidentialUser;
import com.zeng.ezsh.wy.entity.UserTeacherFee;
import com.zeng.ezsh.wy.service.FamilyEducateInfoService;
@Service
public class FamilyEducateInfoServiceImpl implements FamilyEducateInfoService {
	@Resource
	FamilyEducateInfoMapper familyEducateInfoMapper;

	/**
	 * @author qwc
	 * 2017年11月28日下午8:16:53
	 * @param record
	 * @return 添加家教需求信息
	 */
	@Override
	public int insert(FamilyEducateInfo record) {
		// TODO Auto-generated method stub
		return familyEducateInfoMapper.insert(record);
	}
	
	/**
	 * @author qwc
	 * 2017年11月28日下午8:18:02
	 * @param param
	 * @return 获取家教需求信息列表
	 */
	@Override
	public List<FamilyEducateInfo> selectByPrimaryKey(FamilyEducateInfo record) {
		// TODO Auto-generated method stub
		return familyEducateInfoMapper.selectByPrimaryKey(record);
	}
	
	/**
	 * @author qwc
	 * 2017年12月4日下午11:04:36
	 * @param record
	 * @return 删除家长家教需求
	 */
	@Override
	public int deleteById(FamilyEducateInfo record) {
		// TODO Auto-generated method stub
		return familyEducateInfoMapper.deleteById(record);
	}
	
}
