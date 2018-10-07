package com.zeng.ezsh.wy.action;

import java.io.BufferedReader;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zeng.ezsh.wy.entity.Lock;
import com.zeng.ezsh.wy.entity.ParkRecord;
import com.zeng.ezsh.wy.entity.PlateRecordResponse;
import com.zeng.ezsh.wy.service.LockService;
import com.zeng.ezsh.wy.service.ParkNoticeService;
import com.zeng.ezsh.wy.service.ParkPayService;
import com.zeng.ezsh.wy.service.PlateManagementService;
import com.zeng.ezsh.wy.utils.DateUtil;
import com.zeng.ezsh.wy.utils.GetIpAdrr;
import com.zeng.ezsh.wy.utils.RetJsonUtil;

/**
 * 停车系统控制层
 * @author y
 *
 */
@Controller
@RequestMapping("park")
public class ParkAction {
	
	private static Logger logger = LoggerFactory.getLogger(ParkAction.class);
	@Resource
	private PlateManagementService plateservice;
	@Resource
	private ParkPayService parkPayService;
	@Resource
	private ParkNoticeService parkNoticeService;
	@Resource
	private LockService lockService;
	/**
	 * 通知总接口 park/notice
	 * @param request
	 * @return
	 * @author y
	 */
	@RequestMapping(value="notice",produces="application/json")//"application/x-www-form-urlencoded"
	@ResponseBody
	public PlateRecordResponse allNotice(HttpServletRequest request){

		Map<String,Object> map = new HashMap<>();
		StringBuffer json = new StringBuffer();  
		String line = null;  
		try {  
		    BufferedReader reader = request.getReader();  
		    while((line = reader.readLine()) != null) {  
		        json.append(line);  
		    }
		}  
		catch(Exception e) {
			
			e.printStackTrace();  
		}  
		logger.info("Parking json data="+json);  
		  
		JSONObject jsonObj = JSONObject.fromObject(json.toString());  

		//获取停车系统IP
		String ip =GetIpAdrr.getIpAddr(request);
		
		map.put("jsonObj", jsonObj);
		map.put("ip", ip);
		
		logger.info("访问来源:"+ip);
		
		return parkNoticeService.handleNotice(map);

	}	
	/**
	 * 获取当前锁车与否
	 * @author y
	 */
	@RequestMapping("getCarLockMsg")
	@ResponseBody
	public String getCarLockMsg(String car){
		RetJsonUtil retJsonUtil = new RetJsonUtil();
		Lock lock ;
		
		if(car != null){
			lock = lockService.getLockMsg(car);
			if(lock==null){
				retJsonUtil.setStatus("0");
				retJsonUtil.setMessage("fail");
			} else {
				retJsonUtil.setStatus("1");
				retJsonUtil.setMessage("success");
				retJsonUtil.setObject(lock);
			}
		}
		return retJsonUtil.getRetJsonO();
	}
	
	@RequestMapping("gtParkrecord")
	@ResponseBody
	public String gtParkrecord(ParkRecord parkRecord,
			@RequestParam("car")String car){
		RetJsonUtil jsonUtil = new RetJsonUtil();
		parkRecord = plateservice.getParkRecordRecentlyByCarId(car);
		if(parkRecord == null){
			jsonUtil.setStatus("0");
			jsonUtil.setMessage("停车场中无该车辆");
		}else{
			jsonUtil.setStatus("1");
			jsonUtil.setMessage("获取成功");
			if(parkRecord.getEnterTime() != null)
				parkRecord.setEnterTimeStr(DateUtil.dateToStr(parkRecord.getEnterTime(),4));
			if(parkRecord.getLeaveTime() != null)
				parkRecord.setLeaveTimeStr(DateUtil.dateToStr(parkRecord.getLeaveTime(), 4));
			if(parkRecord.getPrepaymentTime() != null)
				parkRecord.setPrepaymentTimeStr(DateUtil.dateToStr(parkRecord.getPrepaymentTime(), 4));
			jsonUtil.setObject(parkRecord);
		}
		return jsonUtil.getRetJsonO();
	}
}
