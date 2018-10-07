package com.zeng.ezsh.wy.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.zeng.ezsh.wy.dao.LockMapper;
import com.zeng.ezsh.wy.entity.Lock;
import com.zeng.ezsh.wy.service.LockService;

@Service
public class LockServiceImpl implements LockService {

	@Resource
	LockMapper lockMapper;
	
	@Override
	public int locked(Lock lock) {
		return lockMapper.locked(lock);
	}

	@Override
	public int unlock(String car) {
		return lockMapper.unLock(car);
	}

	@Override
	public Lock getLockMsg(String car) {
		return lockMapper.getLock(car);
	}

}
