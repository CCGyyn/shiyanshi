package com.zeng.ezsh.quart.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import javax.annotation.Resource;

import org.quartz.CronExpression;
import org.springframework.stereotype.Service;

import com.zeng.ezsh.quart.util.CronUtil;
import com.zeng.ezsh.quart.util.UnlockCallable;
import com.zeng.ezsh.wy.dao.LockMapper;
import com.zeng.ezsh.wy.entity.Lock;
@Service
public class TimeTaskDispatcherCenter {
	private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss SSS");
    private static final int APPROACH_SECONDS = 1*60;// 临近时间 单位秒
    // 线程安全的map
    private static final Map RUNNING_MAP = Collections.synchronizedMap(new HashMap());
    
    @Resource
    LockMapper lockDao;
  
    public void dispatch() {
        System.out.println("执行中的任务：" + RUNNING_MAP.keySet().toString());
        // 从数据库取解锁任务
        List<Lock> tasks = lockDao.getUnlockTask();
        for (Lock task : tasks) {
            if (judgeAppropching(task)) {// 如果即将执行的时间临近当前时间1分钟内
                if (RUNNING_MAP.containsKey(task.getCar())) {// 如果运行中的任务已包含当前任务,不执行该任务
                    continue;
                } else {
                    RUNNING_MAP.put(task.getCar(), task);
                    execute(task);// 从池中取线程,运行该task
                }
            }
        }
    }

    /**
     * 如果字符串代表的cron表达式时间临近,返回true
     * 当expression字符串为空或cron表达式为空,返回false
     * 
     * @param task
     * @return
     */
    private boolean judgeAppropching(Lock task) {
        CronExpression cron = null;
        try {
            cron = new CronExpression(CronUtil.getCron(task.getValidity()));// 把字符串转换成cron表达式,用以计算下次执行时间
        } catch (ParseException e) {
            e.printStackTrace();
        }
        if (cron != null) {// 如果expression正确
            // 获取下次执行时间点 (long)
            Date nextValidDate = cron.getNextValidTimeAfter(new Date());
            long nextValidTimeMills = nextValidDate.getTime();
            // 计算 下次执行时间点和系统当前时间点 时间差 (delaymillis毫秒)
            long delayMillis = nextValidTimeMills - System.currentTimeMillis();
            System.out.println("任务" + task.getId() + "\t\t\t【下次执行时间预计为:】" + sdf.format(nextValidDate) + "距离当前时间还差" + delayMillis / 1000 + "秒左右");
            // 如果 0秒<时间差<10秒 ,返回true
            if (delayMillis > 0 && delayMillis < APPROACH_SECONDS * 1000) {
                task.setDelayMillis(delayMillis);// 设置延迟执行时间,用于execute()方法
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    private void execute(Lock task) {
        UnlockCallable call = new UnlockCallable();
        call.setTask(task);
        call.setRunningMap(RUNNING_MAP);
        // 调度该任务,延迟单位毫秒 ,judgeAppropching()设置延迟时间
        UnlockCallable.executor.schedule(call, task.getDelayMillis(), TimeUnit.MILLISECONDS);
    }

}
