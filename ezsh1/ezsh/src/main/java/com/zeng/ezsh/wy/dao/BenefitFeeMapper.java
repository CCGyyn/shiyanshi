package com.zeng.ezsh.wy.dao;

import java.util.List;

import com.zeng.ezsh.wy.entity.BenefitFee;
public interface BenefitFeeMapper {
	/*更新公益基金（前后台）*/
	int updateByPrimaryKey(BenefitFee record);
	
	/*添加公益基金(移动端)*/
	int insert(BenefitFee record);
	
	/*获取公益基金（前后台）*/
	BenefitFee selectByPrimaryKey(BenefitFee record);
	
	/*获取公益基金列表集合(后台)*/
	List<BenefitFee> selectListByPrimaryKey(BenefitFee record);
}