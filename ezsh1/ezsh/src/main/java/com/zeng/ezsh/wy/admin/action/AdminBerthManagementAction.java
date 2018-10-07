package com.zeng.ezsh.wy.admin.action;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zeng.ezsh.wy.action.ParkChargeAction;
import com.zeng.ezsh.wy.entity.BerthMessage;
import com.zeng.ezsh.wy.service.PlateManagementService;
import com.zeng.ezsh.wy.utils.RetJsonUtil;

@Controller
@RequestMapping("plate")
public class AdminBerthManagementAction {
	private static Logger logger = LoggerFactory
			.getLogger(AdminBerthManagementAction.class);
	@Resource
	private PlateManagementService plateManagementService;
	@Resource
	ParkChargeAction parkChargeAction;
	/**
	 * 后台更改车位管理费
	 * @param berthMessage 至少需要berthCost，userCommunityId，berthNumber
	 * @param distinguish 识别停车系统或者后台
	 */
	@RequestMapping("/updateBerthCost")
	@ResponseBody
	public String updateBerthCost(BerthMessage berthMessage,String distinguish){
		RetJsonUtil jsonUtil = new RetJsonUtil();
		int num = plateManagementService.updateBerthCost(berthMessage);
		if(num==0){
			jsonUtil.setStatus("0");
			jsonUtil.setMessage("fail");
		}else{
			jsonUtil.setStatus("1");
			jsonUtil.setMessage("success");
			//下面通知停车系统      如果是接收来自停车系统的更新不需要再行通知
			if(!distinguish.equals("park")){
				String retStr = parkChargeAction.updateBerthCostNotice(berthMessage);
				logger.info("retStr="+retStr);
			}
		}
		return jsonUtil.getRetJsonM();
	}
}
