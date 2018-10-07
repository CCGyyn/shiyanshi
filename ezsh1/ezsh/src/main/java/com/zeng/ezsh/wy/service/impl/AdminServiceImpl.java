package com.zeng.ezsh.wy.service.impl;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import com.zeng.ezsh.wy.dao.AdminMapper;
import com.zeng.ezsh.wy.dao.RoleMapper;
import com.zeng.ezsh.wy.entity.Admin;
import com.zeng.ezsh.wy.entity.Role;
import com.zeng.ezsh.wy.service.AdminService;
import com.zeng.ezsh.wy.service.RoleService;
@Service
public class AdminServiceImpl implements AdminService {
	@Resource
	AdminMapper adminMapperDao;
	@Resource
	RoleMapper roleMapperDao;
	/**
	 * @author qwc
	 * 2017年10月10日下午9:12:58
	 * @param record
	 * @return 添加管理员
	 */
	@Override
	public int insert(Admin record) {
		// TODO Auto-generated method stub
		return adminMapperDao.insert(record);
	}
	
	/**
	 * @author qwc
	 * 2017年10月10日下午9:13:13
	 * @param record
	 * @return 检测账号是否存在
	 */
	@Override
	public int checkAccountIsIn(Admin record) {
		// TODO Auto-generated method stub
		return adminMapperDao.checkAccountIsIn(record);
	}
	
	/**
	 * @author qwc
	 * 2017年10月10日下午9:27:24
	 * @param record
	 * @return 更新管理员信息
	 */
	@Override
	public int updateByPrimaryKey(Admin record) {
		// TODO Auto-generated method stub
		return adminMapperDao.updateByPrimaryKey(record);
	}
	
	/**
	 * @author qwc
	 * 2017年10月10日下午9:48:11
	 * @param record
	 * @return 获取管理员列表
	 */
	@Override
	public List<Admin> selectByParam(Admin record) {
		// TODO Auto-generated method stub
		return adminMapperDao.selectByParam(record);
	}
	
	/**
	 * @author qwc
	 * 2017年10月11日下午3:24:03
	 * @param account
	 * @return 通过账号获取登录信息
	 */
	@Override
	public Admin selectByAccount(String account) {
		// TODO Auto-generated method stub
		return adminMapperDao.selectByAccount(account);
	}
	
	/**
	 * @author qwc
	 * 2017年10月12日下午3:18:08
	 * @return 根据用户ID获取用户的角色和权限信息
	 */
	@Override
	public Map<String, Set<String>> selectResourceMapByRoleId(int roleId) {
		// TODO Auto-generated method stub
		Role role=roleMapperDao.selectByPrimaryKey(roleId);
		Map<String, Set<String>> resourceMap = new HashMap<String, Set<String>>();
		Set<String> privilegeSet = new HashSet<String>();
		if(StringUtils.isNotBlank(role.getRoPrevNames())){
	    	String[] privilegeArray=StringUtils.split(role.getRoPrevNames(), ",");
	    	for(int i=0;i<privilegeArray.length;i++){
	    		privilegeSet.add(privilegeArray[i]);
	    	}
		}
		resourceMap.put("privileges", privilegeSet);	
		return resourceMap;
	}
	
	/**
	 * @author qwc
	 * 2017年10月13日下午7:15:37
	 * @param record
	 * @return 修改管理员密码
	 */
	@Override
	public int updatePassByPrimaryKey(Admin record) {
		// TODO Auto-generated method stub
		return adminMapperDao.updatePassByPrimaryKey(record);
	}

}
