package com.zeng.ezsh.quart.action;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.zeng.ezsh.wy.service.ChargeRecordService;
//@Controller
@RequestMapping(value="inserRecordBatchM")
public class CreateChargeRecordAction {
	@Resource
	ChargeRecordService chargeRecordAdminService;
	/**
	 * @author qwc
	 * 2017年9月24日下午4:36:35 
	 * void 生成应收费记录
	 */
	@RequestMapping(value="inserRecordBatch")
	public void ceateCRJob(){
		chargeRecordAdminService.insertChargeRecordBatch();
	}
	
}
