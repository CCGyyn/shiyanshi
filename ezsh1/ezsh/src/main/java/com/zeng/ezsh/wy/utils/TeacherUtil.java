package com.zeng.ezsh.wy.utils;

import java.util.Date;

import com.zeng.ezsh.wy.entity.ResidentialUser;

public class TeacherUtil {
	/**
	 * @description 检测家教费是否超过有效期
	 *
	 * @return boolean
	 */
	public static boolean checkIsInValid(ResidentialUser residentialUser) {
		String tMonth = DateUtil.dateToStr(new Date(), DateUtil.YM_NO_SLASH);
		if (residentialUser.getUserTeacherFeeValid().compareTo(tMonth) < 0) {
			return false;
		} else {
			return true;
		}
	}
}
