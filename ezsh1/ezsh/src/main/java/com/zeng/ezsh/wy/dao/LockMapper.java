package com.zeng.ezsh.wy.dao;

import java.util.List;

import com.zeng.ezsh.wy.entity.Lock;

public interface LockMapper {

	public int locked(Lock lock);
	
	public int unLock(String car);
	
	public List<Lock> getUnlockTask();
	
	public int getLockNumByCar(String car);
	
	public Lock getLock(String car);
}
