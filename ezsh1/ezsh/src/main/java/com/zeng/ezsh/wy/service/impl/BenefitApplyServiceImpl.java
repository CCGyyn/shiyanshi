package com.zeng.ezsh.wy.service.impl;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.zeng.ezsh.wy.dao.BenefitApplyMapper;
import com.zeng.ezsh.wy.dao.BenefitFeeMapper;
import com.zeng.ezsh.wy.dao.BenefitRecordMapper;
import com.zeng.ezsh.wy.dao.BuildingMapper;
import com.zeng.ezsh.wy.dao.ManagementMapper;
import com.zeng.ezsh.wy.dao.RoomMapper;
import com.zeng.ezsh.wy.dao.UMsIdsMapper;
import com.zeng.ezsh.wy.entity.BenefitApplyWithBLOBs;
import com.zeng.ezsh.wy.entity.BenefitFee;
import com.zeng.ezsh.wy.entity.BenefitRecord;
import com.zeng.ezsh.wy.entity.Building;
import com.zeng.ezsh.wy.entity.ChargeInfo;
import com.zeng.ezsh.wy.entity.ChargeRecord;
import com.zeng.ezsh.wy.entity.Management;
import com.zeng.ezsh.wy.entity.ResidentialUser;
import com.zeng.ezsh.wy.entity.Room;
import com.zeng.ezsh.wy.entity.UMsIds;
import com.zeng.ezsh.wy.service.BenefitApplyService;
import com.zeng.ezsh.wy.service.BenefitFeeService;
import com.zeng.ezsh.wy.utils.CalcChargePriceUtil;
import com.zeng.ezsh.wy.utils.DateUtil;

@Service
public class BenefitApplyServiceImpl implements BenefitApplyService {
	@Resource
	BenefitApplyMapper benefitApplyMapper;
	@Resource
	ManagementMapper managementMapperDao;
	@Resource
	BuildingMapper buildMapperDao;
	@Resource
	UMsIdsMapper uMsIdsMapper;
	@Resource
	BenefitRecordMapper benefitRecordMapper;
	@Resource
	BenefitFeeService benefitFeeService;

	/**
	 * @author qwc 2017年11月28日下午8:03:00
	 * @param record
	 * @return 公益金申请
	 */
	@Override
	public int insertSelective(BenefitApplyWithBLOBs record) {
		// TODO Auto-generated method stub
		return benefitApplyMapper.insertSelective(record);
	}

	/**
	 * @author qwc 2017年11月28日下午8:02:44
	 * @param record
	 * @return 获取公益基金申请列表
	 */
	@Override
	public List<BenefitApplyWithBLOBs> selectByPrimaryKey(
			BenefitApplyWithBLOBs record) {
		// TODO Auto-generated method stub
		return benefitApplyMapper.selectByPrimaryKey(record);
	}

	/**
	 * @author qwc 2017年11月28日下午8:04:07
	 * @param record
	 * @return 审核公益基金
	 */
	@Override
	public int updateByPrimaryKeySelectiveForCheck(BenefitApplyWithBLOBs record) {
		// TODO Auto-generated method stub
		// 如果通过申请，则更新该小区公益基金总数额
		BenefitApplyWithBLOBs checkBenefitApplyWithBLOBs = benefitApplyMapper
				.selectById(record);
		if(checkBenefitApplyWithBLOBs.getCheckStatus() == 1){
			return 2; // 已通过审核的记录不能再次通过审核，非法操作
		}
		if (record.getCheckStatus() == 1) {
			BenefitFee benefitFee = new BenefitFee();
			benefitFee.setPtManagerId(record.getPtManagerId());
			benefitFee.setFeeSum(record.getApplyMoney());
			int status = benefitFeeService.updateByPrimaryKeyA(benefitFee);
			//设置成功申请的时间
			record.setApplyTime(DateUtil.dateToStr(new Date(), DateUtil.DATE_TIME_LINE)); 
			if (status < 0) {
				return status;
			}
		}
		return benefitApplyMapper.updateByPrimaryKeySelectiveForCheck(record);
	}

	/**
	 * @author qwc 2017年12月6日下午10:30:31
	 * @param record
	 * @return 再次提交公益基金申请（审核失败后修改资料再次提交）
	 */
	@Override
	public int updateByPrimaryKeySelectiveForAgain(BenefitApplyWithBLOBs record) {
		// TODO Auto-generated method stub
		return benefitApplyMapper.updateByPrimaryKeySelectiveForAgain(record);
	}

	/**
	 * @author qwc 2017年12月6日下午10:51:47
	 * @param record
	 * @return 获取个人公益基金申请记录集合
	 */
	@Override
	public List<BenefitApplyWithBLOBs> selectListByPrimaryKey(
			BenefitApplyWithBLOBs record) {
		// TODO Auto-generated method stub
		return benefitApplyMapper.selectListByPrimaryKey(record);
	}

}
