package com.zeng.ezsh.wy.service.impl;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.zeng.ezsh.alipay.service.AlipayService;
import com.zeng.ezsh.wechatpay.service.WechatPayAppService;
import com.zeng.ezsh.wy.dao.ResidentialUserMapper;
import com.zeng.ezsh.wy.dao.UserTeacherFeeMapper;
import com.zeng.ezsh.wy.entity.ResidentialUser;
import com.zeng.ezsh.wy.entity.UserTeacherFee;
import com.zeng.ezsh.wy.service.UserTeacherFeeService;
import com.zeng.ezsh.wy.utils.DateUtil;
import com.zeng.ezsh.wy.utils.SerializeUtil;
@Service
public class UserTeacherFeeServiceImpl implements UserTeacherFeeService {
	@Resource
	UserTeacherFeeMapper userTeacherFeeMapper;
	@Resource
	ResidentialUserMapper residentialUserMapper;
	@Resource
	AlipayService alipayService;
	@Resource
	WechatPayAppService wechatPayAppService;
	/**
	 * @author qwc 2017年12月5日下午5:44:23
	 * @param record
	 * @return 获取家教平台移动端支付记录
	 */
	@Override
	public UserTeacherFee selectByParam(UserTeacherFee record) {
		// TODO Auto-generated method stub
		return userTeacherFeeMapper.selectByParam(record);
	}

	/**
	 * @author qwc 2017年12月5日下午5:44:55
	 * @param record
	 * @return 更新家教平台移动端支付记录
	 */
	@Override
	public int updateByPrimaryKeySelective(UserTeacherFee record) {
		// TODO Auto-generated method stub
		ResidentialUser residentialUser = new ResidentialUser();
		residentialUser.setUserId(record.getPtUserId()); // 设置用户ID
		residentialUser.setUserTeacherFeeStatus(1); // 设置家教平台缴费状态
		Calendar calendar = Calendar.getInstance(); // 得到日历
		calendar.setTime(new Date()); // 把当前时间赋给日历
		calendar.add(Calendar.MONTH, 3); // 设置为前3月
		Date date = calendar.getTime();
		residentialUser.setUserTeacherFeeValid(
				DateUtil.dateToStr(date, DateUtil.YM_NO_SLASH));// 设置有效期为三个月
		int status = residentialUserMapper
				.upSelectiveForTeacherPay(residentialUser); // 执行更新操作
		status += userTeacherFeeMapper.updateByPrimaryKeySelective(record); // 执行更新操作
		if (status > 2) {
			return 1;
		} else {
			return 0;
		}
	}

	/**
	 * @author qwc 2017年12月7日下午4:54:00
	 * @param record
	 * @return 获取家教平台移动端支付记录(后台)
	 */
	@Override
	public List<UserTeacherFee> selectListByParamA(UserTeacherFee record) {
		// TODO Auto-generated method stub
		return userTeacherFeeMapper.selectListByParamA(record);
	}

	/**
	 * @author qwc 2017年12月7日下午4:54:13
	 * @param record
	 * @return 获取家教平台移动端支付记录(移动端)
	 */
	@Override
	public List<UserTeacherFee> selectListByParamM(UserTeacherFee record) {
		// TODO Auto-generated method stub
		return userTeacherFeeMapper.selectListByParamM(record);
	}

	/**
	 * @author qwc 2017年12月17日上午10:47:51
	 * @param userTeacherFee
	 * @param residentialUser
	 * @return 生成支付订单
	 */
	@Override
	public String insertOrder(UserTeacherFee record,
			ResidentialUser residentialUser, String payMethod,
			Map<String, Object> additionMap) {
		// TODO Auto-generated method stub
		BigDecimal totalPrice = record.getTeacherFee();// 提取付款金额
		totalPrice = totalPrice.setScale(2, BigDecimal.ROUND_HALF_UP); // 设置转化参数
		record.setPtUserId(residentialUser.getUserId()); // 设置公益基金订单记录用户ID

		record.setTeacherFee(totalPrice); // 设置所需支付的家教平台费用

		record.setPtManagerId(residentialUser.getUmsIdsInfo().getpManagerId()); // 设置管理处ID
		// 自定义交易流水号
		record.setOutTradeNo(SerializeUtil.generateUUID());// 商家后台系统生成的唯一标识订单号
		if (residentialUser.getUmsIdsInfo() != null) {
			record.setManagerName(
					residentialUser.getUmsIdsInfo().getManagerName());
		} else {
			return null;
		}
		// 返回用于提交订单支付的支付信息
		int status = userTeacherFeeMapper.insert(record);

		if (payMethod.equals("aliPay")) {
			String orderInfo = null;
			orderInfo = alipayService.teacherCosts(record,
					totalPrice.toString(), additionMap);
			return orderInfo;
		} else {
			Map<String, String> retMap = new HashMap<String, String>();
			retMap = wechatPayAppService.teacherCosts(record,
					totalPrice.toString(), additionMap);
			if (retMap != null) {
				return retMap.toString();
			} else {
				return null;
			}
		}

	}

}
