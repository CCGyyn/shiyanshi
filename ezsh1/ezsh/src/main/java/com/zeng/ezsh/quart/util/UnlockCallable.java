package com.zeng.ezsh.quart.util;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

import javax.annotation.Resource;

import net.sf.json.JSONObject;

import com.zeng.ezsh.wy.action.ParkChargeAction;
import com.zeng.ezsh.wy.dao.LockMapper;
import com.zeng.ezsh.wy.entity.Lock;
import com.zeng.ezsh.wy.utils.HttpClientUtil;

public class UnlockCallable implements Callable<String> {
	@Resource
	private LockMapper lockMapper;
	// 固定30个线程的线程池
	public static final ScheduledExecutorService executor = Executors
			.newScheduledThreadPool(30);
	private Lock task;
	private String PARKING_URI=ParkChargeAction.PARKING_URI;
//	private static String encoding = "UTF-8";
	private Map runningMap;
	private static final SimpleDateFormat sdf = new SimpleDateFormat(
			"yyyy-MM-dd HH:mm:ss SSS");
	
	@Override
	public String call() throws Exception {
		String returnData = null;
		System.out.println("------------------------------");
		System.out.println("任务开始时间:【" + sdf.format(new Date()) + "】");
		try {
			Thread.currentThread().sleep(1000);
			if(lockMapper.getLockNumByCar(task.getCar())>0){
				String retString = new ParkChargeAction().lockCar(task.getCar(), 0,null);
				System.out.println("---------当前线程："+Thread.currentThread().getName()+"----运行结果为："+retString);
				returnData = JSONObject.fromObject(retString).getString("message");
			}else{
				returnData = "已被手动开锁，任务不执行。。。";
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			runningMap.remove(task.getCar());
			System.out.println("任务结束时间:【" + sdf.format(new Date()) + "】");
			System.out.println();
		}
		return returnData;
	}

	public Lock getTask() {
		return task;
	}

	public void setTask(Lock task) {
		this.task = task;
	}

	public Map getRunningMap() {
		return runningMap;
	}

	public void setRunningMap(Map runningMap) {
		this.runningMap = runningMap;
	}

}
