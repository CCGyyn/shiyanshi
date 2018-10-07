package com.zeng.ezsh.wy.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.zeng.ezsh.wy.dao.RoleMapper;
import com.zeng.ezsh.wy.entity.Role;
import com.zeng.ezsh.wy.service.RoleService;
@Service
public class RoleServiceImpl implements RoleService {
	@Resource
	RoleMapper roleMapperDao;
	/**
	 * @author qwc
	 * 2017年7月23日上午11:45:05
	 * @param record
	 * @return
	 * 添加角色
	 */
	@Override
	public int insert(Role record) {
		// TODO Auto-generated method stub
		return roleMapperDao.insert(record);
	}
	
	/**
	 * @author qwc
	 * 2017年7月23日下午12:00:46
	 * @param record
	 * @return
	 * 根据角色ID修改角色信息
	 */
	@Override
	public int updateByPrimaryKey(Role record) {
		// TODO Auto-generated method stub
		return roleMapperDao.updateByPrimaryKey(record);
	}
	
	/**
	 * @author qwc
	 * 2017年7月23日下午4:46:25
	 * @param param
	 * @return
	 * 根据条件获取角色信息
	 */
	@Override
	public List<Role> selectByCondition(Map<Object, Object> param) {
		// TODO Auto-generated method stub
		return roleMapperDao.selectByCondition(param);
	}
	
	/**
	 * @author qwc
	 * 2017年7月23日下午12:11:42
	 * @param roId
	 * @return
	 * 根据角色ID获取角色信息
	 */
	@Override
	public Role selectByPrimaryKey(Integer roId) {
		// TODO Auto-generated method stub
		return roleMapperDao.selectByPrimaryKey(roId);
	}
	
	/**
	 * @author qwc
	 * 2017年11月12日下午10:23:20
	 * @param record
	 * @return 检测角色名字是否重复
	 */
	@Override
	public int selectCountByRoleName(Role record) {
		// TODO Auto-generated method stub
		return roleMapperDao.selectCountByRoleName(record);
	}


}
