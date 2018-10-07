package com.zeng.ezsh.wy.service.impl;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import net.sf.json.JSONObject;

import org.springframework.stereotype.Service;

import com.zeng.ezsh.alipay.service.AlipayService;
import com.zeng.ezsh.wechatpay.service.WechatPayAppService;
import com.zeng.ezsh.wy.dao.BenefitRecordMapper;
import com.zeng.ezsh.wy.entity.BenefitFee;
import com.zeng.ezsh.wy.entity.BenefitRecord;
import com.zeng.ezsh.wy.entity.ResidentialUser;
import com.zeng.ezsh.wy.service.BenefitFeeService;
import com.zeng.ezsh.wy.service.BenefitRecordService;

@Service
public class BenefitRecordServiceImpl implements BenefitRecordService {
	@Resource
	BenefitRecordMapper benefitRecordMapper;
	@Resource
	AlipayService alipayService;
	@Resource
	BenefitFeeService benefitFeeService;
	@Resource
	WechatPayAppService wechatPayAppService;
	/**
	 * @author qwc 2017年12月5日下午9:21:02
	 * @param record
	 * @return 添加公益基金支付记录(移动端)
	 */
	@Override
	public int insert(BenefitRecord record) {
		// TODO Auto-generated method stub
		return benefitRecordMapper.insert(record);
	}

	/**
	 * @author qwc 2017年12月5日下午9:21:20
	 * @param record
	 * @return 获取公益基金支付记录(移动端)
	 */
	@Override
	public BenefitRecord selectByParam(BenefitRecord record) {
		// TODO Auto-generated method stub
		return benefitRecordMapper.selectByParam(record);
	}

	/**
	 * @author qwc 2017年12月5日下午9:21:41
	 * @param record
	 * @return 更新公益基金支付记录(移动端)
	 */
	@Override
	public int updateByPrimaryKeySelective(BenefitRecord record) {
		// TODO Auto-generated method stub
		BenefitFee benefitFee = new BenefitFee();
		benefitFee.setFeeSum(record.getBenefitFee());
		benefitFee.setPtManagerId(record.getPtManagerId());
		// 更新公益基金
		benefitFeeService.updateByPrimaryKeyM(benefitFee);
		// 更新支付记录
		return benefitRecordMapper.updateByPrimaryKeySelective(record);
	}

	/**
	 * @author qwc 2017年12月5日下午11:33:32
	 * @param record
	 * @return 批量添加公益金基金记录
	 */
	@Override
	public int insertBenefitRecordBatch(List<BenefitRecord> record) {
		// TODO Auto-generated method stub
		return benefitRecordMapper.insertBenefitRecordBatch(record);
	}

	/**
	 * @author qwc 2017年12月6日下午8:30:14
	 * @param record
	 * @param residentialUser
	 * @return 创建公益基金支付订单(用于移动端提交订单支付)
	 */
	@Override
	public String insertOrder(BenefitRecord record,
			ResidentialUser residentialUser, String payMethod,
			Map<String, Object> additionMap) {
		// TODO Auto-generated method stub
		BigDecimal bd = record.getBenefitFee(); // 提取付款金额
		bd = bd.setScale(2, BigDecimal.ROUND_HALF_UP); // 设置转化参数
		record.setPtUserId(residentialUser.getUserId()); // 设置公益基金订单记录用户ID
		record.setPtRoomId(residentialUser.getUmsIdsInfo().getpRoomId()); // 设置房间ID
		// record.setRoomNum(residentialUser.getUmsIdsInfo().g);
		record.setBenefitFee(bd); // 设置所交公益基金数额
		record.setBuildName(residentialUser.getUmsIdsInfo().getManagerName()); // 设置楼宇名称
		record.setPtBuildId(residentialUser.getUmsIdsInfo().getpBuildId()); // 设置楼宇ID
		record.setPtManagerId(residentialUser.getUmsIdsInfo().getpManagerId()); // 设置管理处ID
		// 自定义交易流水号
		record.setOutTradeNo(Thread.currentThread().getId()
				+ record.getPtUserId() + "-" + System.currentTimeMillis());

		int status = benefitRecordMapper.insert(record);

		// 判断是微信还是阿里支付
		if (payMethod.equals("aliPay")) {
			return alipayService.benefitCosts(record, bd.toString(),additionMap);
		} else {
			Map<String, String> retMap = new HashMap<String, String>();
			retMap = wechatPayAppService.benefitCosts(record, bd.toString(),
					additionMap);
			if (retMap != null) {
				return retMap.toString();
			} else {
				return null;
			}
		}
	}

	/**
	 * @author qwc 2017年12月6日下午9:22:35
	 * @param record
	 * @return 获取公益基金支付记录集合(后台)
	 */
	@Override
	public List<BenefitRecord> selectListByParamA(BenefitRecord record) {
		// TODO Auto-generated method stub
		return benefitRecordMapper.selectListByParamA(record);
	}

}
