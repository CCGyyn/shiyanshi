package com.zeng.ezsh.wy.service;

import java.util.List;

import com.zeng.ezsh.wy.entity.BenefitFee;

public interface BenefitFeeService {
	/*更新公益基金(移动端)*/
	public int updateByPrimaryKeyM(BenefitFee record);
	
	/*更新公益基金(后台)*/
	public int updateByPrimaryKeyA(BenefitFee record);
	
	/*添加公益基金(移动端)*/
	public int insert(BenefitFee record);
	
	/*获取公益基金(前后台)*/
	public BenefitFee selectByPrimaryKey(BenefitFee record);
	
	/*获取公益基金列表集合(后台)*/
	public List<BenefitFee> selectListByPrimaryKey(BenefitFee record);
}
