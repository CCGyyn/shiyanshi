package com.zeng.ezsh.wy.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.zeng.ezsh.wy.dao.BenefitFeeMapper;
import com.zeng.ezsh.wy.dao.BenefitRecordMapper;
import com.zeng.ezsh.wy.entity.BenefitFee;
import com.zeng.ezsh.wy.service.BenefitFeeService;
@Service
public class BenefitFeeServiceImpl implements BenefitFeeService {
	@Resource
	BenefitFeeMapper benefitFeeMapper;

	/**
	 * @author qwc 2017年12月16日下午2:53:59
	 * @param record
	 * @return 更新公益基金金额(后台)
	 */
	@Override
	public int updateByPrimaryKeyA(BenefitFee record) {
		// TODO Auto-generated method stub
		// 获取出小区现有的公益基金数额
		BenefitFee benefitFeeApply = benefitFeeMapper
				.selectByPrimaryKey(record);
		if (benefitFeeApply == null) { // 如果该小区还没有人捐赠公益基金
			return -1;
		} else if (record.getFeeSum().compareTo(benefitFeeApply.getFeeSum()) == 1) {
			return -2; // 申请数额超过现有公益基金金额
		} else {
			benefitFeeApply.setFeeSum(benefitFeeApply.getFeeSum().subtract(
					record.getFeeSum()));
			return benefitFeeMapper.updateByPrimaryKey(benefitFeeApply);
		}
	}

	/**
	 * @author qwc 2017年12月16日下午2:53:59
	 * @param record
	 * @return 更新公益基金金额(移动端)
	 */
	@Override
	public int updateByPrimaryKeyM(BenefitFee record) {
		// TODO Auto-generated method stub
		// 获取出小区现有的公益基金数额
		BenefitFee benefitFee = benefitFeeMapper.selectByPrimaryKey(record);
		if (benefitFee == null) { // 如果小区第一次收到捐赠的公益基金，插入新的记录
			return benefitFeeMapper.insert(record);
		} else {
			// 设置新金额
			benefitFee
					.setFeeSum(benefitFee.getFeeSum().add(record.getFeeSum()));
			// 执行更新操作
			return benefitFeeMapper.updateByPrimaryKey(benefitFee);
		}
	}

	/**
	 * @author qwc 2017年12月16日下午2:55:20
	 * @param record
	 * @return 添加公益基金
	 */
	@Override
	public int insert(BenefitFee record) {
		// TODO Auto-generated method stub
		return benefitFeeMapper.insert(record);
	}

	/**
	 * @author qwc 2017年12月16日下午2:56:37
	 * @param record
	 * @return 获取公益基金
	 */
	@Override
	public BenefitFee selectByPrimaryKey(BenefitFee record) {
		// TODO Auto-generated method stub
		return benefitFeeMapper.selectByPrimaryKey(record);
	}
	
	/**
	 * @author qwc
	 * 2017年12月16日下午4:53:15
	 * @param record
	 * @return 获取公益基金列表集合(后台)
	 */
	@Override
	public List<BenefitFee> selectListByPrimaryKey(BenefitFee record) {
		// TODO Auto-generated method stub
		return benefitFeeMapper.selectListByPrimaryKey(record);
	}

}
