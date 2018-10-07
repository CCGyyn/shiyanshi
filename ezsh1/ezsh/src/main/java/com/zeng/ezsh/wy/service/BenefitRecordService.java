package com.zeng.ezsh.wy.service;

import java.util.List;
import java.util.Map;

import com.zeng.ezsh.wy.entity.BenefitApplyWithBLOBs;
import com.zeng.ezsh.wy.entity.BenefitRecord;
import com.zeng.ezsh.wy.entity.ResidentialUser;

public interface BenefitRecordService {
	/* 添加公益基金支付记录(移动端) */
	public int insert(BenefitRecord record);

	/* 创建公益基金支付订单(用于移动端提交订单支付) */
	public String insertOrder(BenefitRecord record,
			ResidentialUser residentialUser, String payMethod,
			Map<String, Object> additionMap);

	/* 获取公益基金支付记录(移动端) */
	public BenefitRecord selectByParam(BenefitRecord record);

	/* 更新公益基金支付记录(移动端) */
	public int updateByPrimaryKeySelective(BenefitRecord record);

	/* 批量添加公益金基金记录 */
	public int insertBenefitRecordBatch(List<BenefitRecord> record);

	/* 获取公益基金支付记录集合(后台) */
	public List<BenefitRecord> selectListByParamA(BenefitRecord record);
}
