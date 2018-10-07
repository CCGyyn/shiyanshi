package com.zeng.ezsh.wy.service;

import com.zeng.ezsh.wy.entity.Lock;

public interface LockService {

	public int locked(Lock lock);
	
	public int unlock(String car);
	
	public Lock getLockMsg(String car);
}
