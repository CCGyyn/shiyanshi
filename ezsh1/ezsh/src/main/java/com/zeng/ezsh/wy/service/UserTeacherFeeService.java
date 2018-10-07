package com.zeng.ezsh.wy.service;

import java.util.List;
import java.util.Map;

import com.zeng.ezsh.wy.entity.ResidentialUser;
import com.zeng.ezsh.wy.entity.UserTeacherFee;

public interface UserTeacherFeeService {
	/* 获取家教平台移动端支付记录 */
	public UserTeacherFee selectByParam(UserTeacherFee record);

	/* 更新家教平台移动端支付记录 */
	public int updateByPrimaryKeySelective(UserTeacherFee record);

	/* 获取家教平台移动端支付记录(后台) */
	public List<UserTeacherFee> selectListByParamA(UserTeacherFee record);

	/* 获取家教平台移动端支付记录(移动端) */
	public List<UserTeacherFee> selectListByParamM(UserTeacherFee record);

	public String insertOrder(UserTeacherFee userTeacherFee,
			ResidentialUser residentialUser, String payMethod,
			Map<String, Object> additionMap);
}
