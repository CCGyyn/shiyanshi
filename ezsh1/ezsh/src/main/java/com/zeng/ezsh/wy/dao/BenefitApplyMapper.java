package com.zeng.ezsh.wy.dao;

import com.zeng.ezsh.wy.entity.BenefitApply;
import com.zeng.ezsh.wy.entity.BenefitApplyWithBLOBs;

import java.math.BigDecimal;
import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface BenefitApplyMapper {
	
	/*公益金申请（移动端）*/
	int insertSelective(BenefitApplyWithBLOBs record);
	
	/*获取公益基金申请列表（后台）*/
	List<BenefitApplyWithBLOBs> selectByPrimaryKey(BenefitApplyWithBLOBs record);
	
	/*获取个人公益基金申请列表(移动端)*/
	List<BenefitApplyWithBLOBs> selectListByPrimaryKey(BenefitApplyWithBLOBs record);
	
	/*再次提交公益基金申请【移动端】（审核失败后修改资料再次提交）*/
	int updateByPrimaryKeySelectiveForAgain(BenefitApplyWithBLOBs record);
	
	/*审核公益基金（后台）*/
	int updateByPrimaryKeySelectiveForCheck(BenefitApplyWithBLOBs record);
	
	/*根据ID获取单个公益基金记录（后台）*/
	BenefitApplyWithBLOBs selectById(BenefitApplyWithBLOBs record);
	
   /* int deleteByPrimaryKey(Integer benefitApplyId);

    int insert(BenefitApplyWithBLOBs record);

    int updateByPrimaryKeyWithBLOBs(BenefitApplyWithBLOBs record);

    int updateByPrimaryKey(BenefitApply record);*/
}