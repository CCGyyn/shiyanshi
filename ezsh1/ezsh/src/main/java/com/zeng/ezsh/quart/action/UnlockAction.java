package com.zeng.ezsh.quart.action;

import java.util.Date;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;

import com.zeng.ezsh.quart.service.TimeTaskDispatcherCenter;
//@Controller
public class UnlockAction {
	
	private static Logger logger = LoggerFactory.getLogger(UnlockAction.class);
	@Resource
	TimeTaskDispatcherCenter center;
	/**
	 * 时间调度解锁
	 * @author y 
	 */
	public void unlock(){
		logger.info("-----unlock开始调度:"+new Date()+"-----");
    	center.dispatch();
	}
}
