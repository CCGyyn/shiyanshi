package com.zeng.ezsh.wy.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.zeng.ezsh.wy.dao.ResidentialUserMapper;
import com.zeng.ezsh.wy.dao.RoomMapper;
import com.zeng.ezsh.wy.entity.Management;
import com.zeng.ezsh.wy.entity.ResidentialUser;
import com.zeng.ezsh.wy.entity.UMsIds;
import com.zeng.ezsh.wy.service.ResidentialUserService;
@Service
public class ResidentialUserServiceImpl implements ResidentialUserService {
	@Resource
	ResidentialUserMapper residentialUserMapperDao;
	
	@Resource
	RoomMapper roomMapper;
	/**
	 * @author qwc
	 * 2017年7月25日下午6:42:07
	 * @param record
	 * @return
	 * 添加用户
	 */
	@Override
	public int addUser(ResidentialUser record) {
		// TODO Auto-generated method stub
		return residentialUserMapperDao.addUser(record);
	}
	
	/**
	 * @author qwc
	 * 2017年7月26日上午10:52:08
	 * @param record
	 * @return
	 * 更新用户信息
	 */
	@Override
	public int updateByUserId(ResidentialUser record) {
		// TODO Auto-generated method stub
		return residentialUserMapperDao.updateByUserId(record);
	}
	
	/**
	 * @author qwc
	 * 2017年7月26日下午4:59:38
	 * @param residentialUser
	 * @return
	 * 获取用户登录信息
	 */
	@Override
	public ResidentialUser getUserLoginInfo(ResidentialUser residentialUser) {
		// TODO Auto-generated method stub
		return residentialUserMapperDao.getUserLoginInfo(residentialUser);
	}
	
	/**
	 * @author qwc
	 * 2017年7月26日下午9:26:07
	 * @param Param
	 * @return
	 * 根据条件获取集体用户信息
	 */
	@Override
	public List<ResidentialUser> selectUserInfoByCondition(
			Map<String, Object> Param) {
		// TODO Auto-generated method stub
		return residentialUserMapperDao.selectUserInfoByCondition(Param);
	}
	
	/**
	 * @author qwc
	 * 2017年7月26日下午9:25:58
	 * @param residentialUser
	 * @return
	 * 根据条件获取单个用户信息
	 */
	@Override
	public ResidentialUser getOneUserInfo(ResidentialUser residentialUser) {
		// TODO Auto-generated method stub
		return residentialUserMapperDao.getOneUserInfo(residentialUser);
	}
	
	/**
	 * @author qwc
	 * 2017年8月2日下午1:20:15
	 * @param telephone
	 * @return
	 * 检测账户是否存在
	 */
	@Override
	public int checkAccountIsIn(String telephone) {
		// TODO Auto-generated method stub
		return residentialUserMapperDao.checkAccountIsIn(telephone);
	}
	
	/**
	 * @author qwc
	 * 2017年8月2日下午10:07:09
	 * @param manageIdsList
	 * @return
	 * 通过管理处ID集合获取小区集合
	 */
	@Override
	public List<Management> getManagementListByIds(List<Integer> manageIdsList) {
		// TODO Auto-generated method stub
		return residentialUserMapperDao.getManagementByIds(manageIdsList);
	}
	
	/**
	 * @author qwc
	 * 2017年8月2日下午10:14:16
	 * @param telephone
	 * @return
	 * 通过手机号获取管理处ID集合
	 */
	@Override
	public List<Integer> getManageIds(String telephone) {
		// TODO Auto-generated method stub
		return residentialUserMapperDao.getManageIds(telephone);
	}
	
	/**
	 * @author qwc
	 * 2017年10月20日下午1:05:16
	 * @param uMsIds
	 * @return 用于账号切换
	 */
	/*@Override
	public ResidentialUser getUserLoginInfoByUserIduMsId(UMsIds uMsIds) {
		// TODO Auto-generated method stub
		return residentialUserMapperDao.getUserLoginInfoByUserIduMsId(uMsIds);
	}*/
	
	/**
	 * @author qwc
	 * 2017年10月20日下午1:12:22
	 * @param residentialUser
	 * @return 账号切换
	 */
	@Override
	public ResidentialUser getUserLoginInfoByUserId(
			ResidentialUser residentialUser) {
		// TODO Auto-generated method stub
		return residentialUserMapperDao.getUserLoginInfoByUserId(residentialUser);
	}
	
	/**
	 * @author qwc
	 * 2017年10月20日下午3:09:59
	 * @param record
	 * @return 密码更新
	 */
	@Override
	public int updateByUserAccount(ResidentialUser record) {
		// TODO Auto-generated method stub
		return residentialUserMapperDao.updateByUserAccount(record);
	}
	
	/**
	 * @author qwc
	 * 2017年11月26日下午8:14:58
	 * @param residentialUser
	 * @return 获取APP端注册用户列表
	 */
	@Override
	public List<ResidentialUser> getResidentialUserList(
			Map<String, Object> param) {
		// TODO Auto-generated method stub
		return residentialUserMapperDao.getResidentialUserList(param);
	}
	
	/**
	 * @description 获取用户积分信息
	 */
	@Override
	public ResidentialUser getUserIntegralByUserId(ResidentialUser record) {
		// TODO Auto-generated method stub
		return residentialUserMapperDao.getUserIntegralByUserId(record);
	}
	/**
	 * @description 身份证的转移
	 */
	@Override
	public void identifyClassifyTransfer(String telephone) {
//		roomMapper.
	}
	
}
