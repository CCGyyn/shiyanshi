package com.zeng.ezsh.wy.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.zeng.ezsh.wy.dao.ResidentialUserMapper;
import com.zeng.ezsh.wy.dao.UMsIdsMapper;
import com.zeng.ezsh.wy.entity.ResidentialUser;
import com.zeng.ezsh.wy.entity.UMsIds;
import com.zeng.ezsh.wy.service.UMsIdsService;
@Service
public class UMsIdsServiceImpl implements UMsIdsService {
	@Resource
	UMsIdsMapper uMsIdsMapperDao;
	@Resource
	ResidentialUserMapper residentialUserMapperDao;

	/**
	 * @author qwc
	 * 2017年8月4日下午9:46:37
	 * @param record
	 * @return
	 * 添加小区
	 */
	@Override
	public int insert(UMsIds record) {
		// TODO Auto-generated method stub
		return uMsIdsMapperDao.insert(record);
	}
	
	/**
	 * @author qwc
	 * 2017年8月4日下午9:57:01
	 * @param record
	 * @return
	 * 检测是否重复添加小区
	 */
	@Override
	public int checkIsOn(UMsIds record) {
		// TODO Auto-generated method stub
		return uMsIdsMapperDao.checkIsOn(record);
	}

	@Override
	public int checkHasOneCommunityPass(int userId) {
		// TODO Auto-generated method stub
		return uMsIdsMapperDao.checkHasOneCommunityPass(userId);
	}
	
	/**
	 * @author qwc
	 * 2017年8月5日下午9:09:43
	 * @param userId
	 * @param communityId
	 * @return
	 * 小区审核不通过，再次提交审核
	 */
	@Override
	public int agSubCommunityCheck(int userId, int communityId) {
		// TODO Auto-generated method stub
		return uMsIdsMapperDao.agSubCommunityCheck(userId, communityId);
	}
	
	/**
	 * @author qwc
	 * 2017年8月8日下午11:07:12
	 * @param record
	 * @return
	 * 通过用户ID和小区ID查询用户所添加的小区是否已经通过审核
	 */
	@Override
	public int checkHasPassByUserIdAndCommunity(UMsIds record) {
		// TODO Auto-generated method stub
		return uMsIdsMapperDao.checkHasPassByUserIdAndCommunity(record);
	}
	
	/**
	 * @author qwc
	 * 2017年9月2日下午4:09:08
	 * @param record
	 * @return
	 * 检测房下用户是否存在
	 */
	@Override
	public int checkRoomUserIsOn(UMsIds record) {
		// TODO Auto-generated method stub
		return uMsIdsMapperDao.checkRoomUserIsOn(record);
	}

	@Override
	public int addRoomUser(UMsIds record) {
		// TODO Auto-generated method stub
		ResidentialUser residentialUser=new ResidentialUser();
		if(record.getRegisterTelephone()!=null&&residentialUserMapperDao.checkAccountIsIn
				(record.getRegisterTelephone())==0){//用户未注册
			residentialUser.setUserAccount(record.getRegisterTelephone());
			residentialUser.setUserPassword(record.getRegisterTelephone());
			residentialUserMapperDao.addUser(residentialUser);
			int userId=residentialUser.getUserId();
			record.setpUserId(userId);//用户ID
			record.setCheckStatus(1);
			uMsIdsMapperDao.insert(record);//添加房下用户
			return 1;
		}else if(record.getRegisterTelephone()!=null&&residentialUserMapperDao.checkAccountIsIn
				(record.getRegisterTelephone())>0){//用户已注册
			Map<String, Object> param=new HashMap<String, Object>();
			param.put("userAccount", record.getRegisterTelephone());
			residentialUser=residentialUserMapperDao.getResidentialUserByParam(param);
			record.setpUserId(residentialUser.getUserId());
			if(uMsIdsMapperDao.checkRoomUserIsOn(record)>0){
				return 2;//不能重复添加房下用户
			}
			uMsIdsMapperDao.insert(record);//添加房下用户
			return 1;
		}
		return 0;
	}
	
	/**
	 * @author qwc
	 * 2017年9月2日下午8:51:21
	 * @param pOwnerId
	 * @return
	 * 获取业主门下用户
	 */
	@Override
	public List<UMsIds> selectUMsList(Map<String, Object> param) {
		// TODO Auto-generated method stub
		return uMsIdsMapperDao.selectUMsList(param);
	}
	
	/**
	 * @author qwc
	 * 2017年9月2日下午8:51:17
	 * @param record
	 * @return
	 * 选择性更新用户小区信息
	 */
	@Override
	public int updateUMsSelective(UMsIds record) {
		// TODO Auto-generated method stub
		return uMsIdsMapperDao.updateUMsSelective(record);
	}
	
	/**
	 * @author qwc
	 * 2017年9月2日下午9:26:58
	 * @param param
	 * @return
	 * 删除房下用户
	 */
	@Override
	public int deleteByParam(Map<String, Object> param) {
		// TODO Auto-generated method stub
		return uMsIdsMapperDao.deleteByParam(param);
	}
	
	/**
	 * @author qwc
	 * 2017年10月20日下午12:44:40
	 * @param record
	 * @return 同过ID获取UMsIds信息
	 */
	@Override
	public UMsIds gtUserUmsInfoById(UMsIds record) {
		// TODO Auto-generated method stub
		return uMsIdsMapperDao.gtUserUmsInfoById(record);
	}
	
	/**
	 * @author qwc
	 * 2017年10月20日下午3:48:25
	 * @param param
	 * @return 业主获取自己的房间集合
	 */
	@Override
	public List<UMsIds> selectUMsRoomList(Map<String, Object> param) {
		// TODO Auto-generated method stub
		return uMsIdsMapperDao.selectUMsRoomList(param);
	}
	
	/**
	 * @author qwc
	 * 2017年10月23日下午1:48:48
	 * @param record
	 * @return 获取账号列表
	 */
	@Override
	public List<UMsIds> selectAccountList(UMsIds record) {
		// TODO Auto-generated method stub
		return uMsIdsMapperDao.selectAccountList(record);
	}
	
	/**
	 * @author qwc
	 * 2017年11月26日下午8:14:14
	 * @param record
	 * @return 更新APP端注册用户信息（用于审核）
	 */
	@Override
	public int updateByPrimaryKeySelectiveForCheck(UMsIds record) {
		// TODO Auto-generated method stub
		return uMsIdsMapperDao.updateByPrimaryKeySelectiveForCheck(record);
	}
	
	/**
	 * @author qwc
	 * 2017年11月26日下午10:04:22
	 * @param record
	 * @return 获取账号列表用于审核（后台）
	 */
	@Override
	public List<UMsIds> selectAccountListA(ResidentialUser record) {
		// TODO Auto-generated method stub
		return uMsIdsMapperDao.selectAccountListA(record);
	}
	
	/**
	 * @author qwc
	 * 2017年12月5日下午10:19:22
	 * @param record
	 * @return 获取个人剩余公益基金列表
	 */
	@Override
	public List<UMsIds> selectBenefitList(UMsIds record) {
		// TODO Auto-generated method stub
		return uMsIdsMapperDao.selectBenefitList(record);
	}
	
	/**
	 * 蓝牙开门前检测是否有权限
	 */
	@Override
	public int checkHasOpenDoorPermission(UMsIds record) {
		// TODO Auto-generated method stub
		return uMsIdsMapperDao.checkHasOpenDoorPermission(record);
	}
}
