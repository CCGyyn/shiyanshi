package com.zeng.ezsh.wy.service;

import java.math.BigDecimal;
import java.util.List;

import com.zeng.ezsh.wy.entity.BenefitApplyWithBLOBs;

public interface BenefitApplyService {
	/*公益金申请*/
	public int insertSelective(BenefitApplyWithBLOBs record);
	
	/*获取公益基金申请列表*/
	public List<BenefitApplyWithBLOBs> selectByPrimaryKey(BenefitApplyWithBLOBs record);
	
	/*获取个人公益基金申请列表(移动端)*/
	public List<BenefitApplyWithBLOBs> selectListByPrimaryKey(BenefitApplyWithBLOBs record);
	
	/*审核公益基金(后台)*/
	public int updateByPrimaryKeySelectiveForCheck(BenefitApplyWithBLOBs record);
	
	/*再次提交公益基金申请（审核失败后修改资料再次提交）*/
	public int updateByPrimaryKeySelectiveForAgain(BenefitApplyWithBLOBs record);
	
	/*批量更新公益金基金记录(每次使用完公益基金后)*/
	/*public int updateBatchBenefitRecord(BenefitApplyWithBLOBs record);*/

}
