package com.zeng.ezsh.wy.dao;

import com.zeng.ezsh.wy.entity.UserTeacherFee;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UserTeacherFeeMapper {
	/*获取家教平台移动端支付记录(移动端)*/
	UserTeacherFee selectByParam(UserTeacherFee record);
	
	/*更新家教平台移动端支付记录(移动端)*/
	int updateByPrimaryKeySelective(UserTeacherFee record);
	
	/*获取家教平台移动端支付记录(后台)*/
	List<UserTeacherFee> selectListByParamA(UserTeacherFee record);
	
	/*获取家教平台移动端支付记录(移动端)*/
	List<UserTeacherFee> selectListByParamM(UserTeacherFee record);
	
	int insert(UserTeacherFee record);
    /*int deleteByPrimaryKey(Integer userTeacherFeeId);

    int insertSelective(UserTeacherFee record);

    UserTeacherFee selectByPrimaryKey(Integer userTeacherFeeId);

    int updateByPrimaryKey(UserTeacherFee record);*/
}