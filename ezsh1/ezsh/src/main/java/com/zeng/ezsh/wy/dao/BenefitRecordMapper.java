package com.zeng.ezsh.wy.dao;

import com.zeng.ezsh.wy.entity.BenefitApplyWithBLOBs;
import com.zeng.ezsh.wy.entity.BenefitRecord;

import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface BenefitRecordMapper {
	/*添加公益基金支付记录(移动端)*/
	int insert(BenefitRecord record);
	
	/*获取公益基金支付记录(移动端)*/
	BenefitRecord selectByParam(BenefitRecord record);
	
	/*更新公益基金支付记录(移动端)*/
	int updateByPrimaryKeySelective(BenefitRecord record);

	/*批量添加公益金基金记录*/
	int insertBenefitRecordBatch(List<BenefitRecord> record);
	
	/*获取公益基金支付记录集合(后台)*/
	List<BenefitRecord> selectListByParamA(BenefitRecord record);
    /*int deleteByPrimaryKey(Integer benefitRecordId);

    int insert(BenefitRecord record);

    int insertSelective(BenefitRecord record);

    BenefitRecord selectByPrimaryKey(Integer benefitRecordId);

    int updateByPrimaryKeySelective(BenefitRecord record);

    int updateByPrimaryKey(BenefitRecord record);*/
}