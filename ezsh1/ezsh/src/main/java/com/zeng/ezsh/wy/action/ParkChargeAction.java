package com.zeng.ezsh.wy.action;

import java.io.IOException;
import java.net.URISyntaxException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

import org.apache.http.client.ClientProtocolException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zeng.ezsh.alipay.service.AlipayService;
import com.zeng.ezsh.wechatpay.config.WechatPayConfig;
import com.zeng.ezsh.wechatpay.service.WechatPayAppService;
import com.zeng.ezsh.wy.entity.BerthMessage;
import com.zeng.ezsh.wy.entity.BerthOrder;
import com.zeng.ezsh.wy.entity.CarMessage;
import com.zeng.ezsh.wy.entity.Lock;
import com.zeng.ezsh.wy.entity.ParkRecord;
import com.zeng.ezsh.wy.service.LockService;
import com.zeng.ezsh.wy.service.ParkPayService;
import com.zeng.ezsh.wy.service.PlateManagementService;
import com.zeng.ezsh.wy.utils.DateUtil;
import com.zeng.ezsh.wy.utils.GetIpAdrr;
import com.zeng.ezsh.wy.utils.HttpClientUtil;
import com.zeng.ezsh.wy.utils.RetJsonUtil;

@Controller
@RequestMapping("park")
public class ParkChargeAction {

	private static Logger logger = LoggerFactory.getLogger(ParkChargeAction.class);
	
	public static final String PARKING_URI = "http://ezshpark.tunnel.echomod.cn/park";
	public static final String PARKING_URI1 = "https://ezshpark.tunnel.echomod.cn/park";
	@Resource
	PlateManagementService plateservice;
	@Resource
	ParkPayService parkPayService;
	@Resource
	AlipayService alipayService;
	@Resource
	WechatPayAppService wechatPayAppService;
	@Resource
	LockService lockService;
	
	/**
	 * 查询应交停车费
	 * @param parkRecord
	 * @return
	 * @throws ClientProtocolException
	 * @throws IOException
	 * @throws URISyntaxException
	 */
		@RequestMapping(value="getSupposeParkCost",produces="application/json")
		@ResponseBody
		public Map<String, Object> querySupposedCost(String plateNumber) throws ClientProtocolException, IOException, URISyntaxException{
			Map<String, Object> retMap = new HashMap<String, Object>();
			ParkRecord parkRecord = plateservice.getParkRecordRecentlyByCarId(plateNumber);
			if(parkRecord == null){
				retMap.put("message", "无法获取该车牌号信息");
				retMap.put("data", null);
				retMap.put("status", 0);
				return retMap;
			}
	        //封装参数
	        JSONObject jsonParam = new JSONObject();  
	        jsonParam.put("ActType", "000004");
	        jsonParam.put("Car", parkRecord.getPlateNumber());
	        jsonParam.put("ParkingId", parkRecord.getUserCommunityId());
	        jsonParam.put("CRC", parkRecord.getCRCCode());
	        
	        HttpClientUtil.createConnection(PARKING_URI, jsonParam.toString());
	        
	        try {
	        	//获取返回值
	        	String content = HttpClientUtil.getResponse();
	            // 判断返回状态是否为200
	            System.out.println("StatusCode:"+HttpClientUtil.getStatusCode());
	            if (HttpClientUtil.getStatusCode() == 200) {
	                JSONObject jsonObject =  JSONObject.fromObject(content);//返回字符串转化为json对象
	                System.out.println(jsonObject.toString());
	                System.err.println(jsonObject.getString("ActType")+" "+jsonObject.getString("Code"));
	                if(jsonObject.getString("ActType").equals("000004") && jsonObject.getString("Code").equals("00")){
	                	parkRecord.setSupposeParkCost(Double.valueOf(jsonObject.getString("Money")));//缴费金额
	                	String sn = jsonObject.getString("SN");//预缴费流水号
	                	if(sn != "" && sn != null){
	                		parkRecord.setSN(jsonObject.getString("SN"));
	                	}
	                	plateservice.updateParkRecord(parkRecord);//更新停车记录
	                	Map<String, Object> data = new HashMap<String,Object>();
	                	data.put("plateNum", parkRecord.getPlateNumber());
	                	data.put("money", parkRecord.getSupposeParkCost());
	                	data.put("inTime", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(parkRecord.getEnterTime()));
	                	retMap.put("message","操作成功");
	                	retMap.put("data", data);
	                	retMap.put("status", 1);
	                }
	            }
	        } finally {//释放资源
	        	HttpClientUtil.closeConnection();
	        }
	        return retMap;
		}
	
	/**
	 * 预收费（可重复支付）
	 */
	@RequestMapping(value="parkPrepayment",produces = "application/json;charset=utf-8")
	@ResponseBody
	public String payOfPrepayment(HttpServletRequest request,
			@RequestParam(value="Code",required=false) String Code,
			@RequestParam("plateNum")String plateNumber,	//车牌号
			@RequestParam("money")String money,				//要缴纳的金额
			@RequestParam("payWay")String payWay){			//支付方式 	
		Map<String, Object> additionMap = new HashMap<String, Object>();
		Map<String, Object> map = new HashMap<String, Object>();
		ParkRecord parkRecord = new ParkRecord();
		RetJsonUtil retJsonUtil = new RetJsonUtil();
		String identify = request.getParameter("identify");
//		logger.debug("COde:"+Code);
		if(Code != null){
			String openId = this.getOpenId(Code);
//			logger.debug("openId");
			additionMap.put("openId", openId);
		}
		if(identify == null || !identify.equals("mTerminal")){
			identify = "wap";
		}
		if(money==null){
			money="0.0";
		}else if(money.indexOf(".")==-1){
			money=money+".0";
		}
		if(plateNumber != null){
			logger.debug("plateNumber:"+plateNumber+"--------money:"+money+"-------payWay:"+payWay);
			parkRecord = plateservice.getParkRecordRecentlyByCarId(plateNumber);//取出该车牌号的一次记录
//			parkRecord.setPrepaymentTime(null);							//清除时间
//			parkRecord.setPayStatus(0);									//清除支付状态
//			parkRecord.setOrderNum(null); 								//清除商户订单号
//			parkRecord.setTransactionNum(null);							//清除支付宝订单号
			parkRecord.setPayWay(payWay);								//设置支付方式
			parkRecord.setAdvancePay(Double.parseDouble(money.trim())); //设置支付金额						
			parkRecord.setOrderStatus(parkRecord.getOrderStatus()+1);	//该车主订单数加1
			plateservice.addParkPayRecord(parkRecord);					//新增支付记录
			// 微信支付时需要获取客户端的真实IP地址
			String realIp = GetIpAdrr.getIpAddr(request);
			
			additionMap.put("identify", identify);
			additionMap.put("realIp", realIp);
			additionMap.put("parkRecord", parkRecord);
			logger.debug("IP:"+realIp+"调用该接口！");
			
			if(parkRecord != null){
				String orderInfo = parkPayService.addOrder(additionMap);
				
				logger.debug("orderInfo:"+orderInfo);
				if(orderInfo == null){
					retJsonUtil.setStatus("0");
					retJsonUtil.setMessage("操作失败");
				}else{
					Map<String, Object> retMap = new HashMap<String, Object>();
					retMap.put("orderInfo", orderInfo);
					retJsonUtil.setRetMap(retMap);
					retJsonUtil.setStatus("1");
					retJsonUtil.setMessage("操作成功");
				}
				return retJsonUtil.getRetJsonM();
			}
		}
		return "无法获取参数";
	}
	
	/**
	 * 停车厂预收费通知(支付完成后通知)
	 * @throws URISyntaxException 
	 * @throws IOException 
	 * @throws ClientProtocolException 
	 */
//	@RequestMapping("/prepayment")
//	@ResponseBody
	public String parkPrepaymentNotice(String carId)
			throws URISyntaxException, ClientProtocolException, IOException {
		String retStr="";
		ParkRecord parkRecord = plateservice.getParkRecordRecentlyByCarId(carId);
		
		JSONObject jsonParam = new JSONObject();
		jsonParam.put("ActType", "000005");
		jsonParam.put("Car", parkRecord.getPlateNumber());
		jsonParam.put("ParkingId", parkRecord.getUserCommunityId());
		jsonParam.put("SN", parkRecord.getSN());
		jsonParam.put("Money", parkRecord.getAdvancePay());
		jsonParam.put("ActTime", parkRecord.getPrepaymentTime());
		jsonParam.put("CRC", parkRecord.getCRCCode());
		HttpClientUtil.createConnection(PARKING_URI, jsonParam);
		String content = HttpClientUtil.getResponse();
		// 判断返回状态是否为200
		System.out.println(HttpClientUtil.getStatusCode());
		if (HttpClientUtil.getStatusCode() == 200) {
			JSONObject jsonObject = JSONObject.fromObject(content);// 返回字符串转化为json对象
			if (jsonObject.getString("ActType").equals("000005")
					&& jsonObject.getString("Code").equals("00")) {
				retStr = "success";
				
//				plateservice.updateParkRecord(parkRecord);// 更新停车管理记录
			}
			retStr = "fail";

		} else {
			retStr = "error";
		}
		// 释放资源
		HttpClientUtil.closeConnection();
		return retStr;
	}
	
	public String getOpenId(String Code){
		String openId;
		logger.info("Code:"+Code);
		Map<String, String> paramMap = new HashMap<String, String>();
		paramMap.put("appid", WechatPayConfig.APP_ID_JSAPI);
		paramMap.put("secret", WechatPayConfig.SECRET);
		paramMap.put("code", Code);
		paramMap.put("grant_type", "authorization_code");
		try {
			HttpClientUtil.createConnection("https://api.weixin.qq.com/sns/oauth2/access_token", paramMap);
			String content = HttpClientUtil.getResponse();
			logger.info("------------>content:"+content);
			System.out.println(HttpClientUtil.getStatusCode());
			if (HttpClientUtil.getStatusCode() == 200) {
				JSONObject jsonObject = JSONObject.fromObject(content);
				openId = jsonObject.getString("openid");
			}else{
				openId = "error";
			}
		} catch (URISyntaxException e) {
			openId = "error";
			e.printStackTrace();
		} catch (ClientProtocolException e) {
			openId = "error";
			e.printStackTrace();
		} catch (IOException e) {
			openId = "error";
			e.printStackTrace();
		}
		return openId;
	}
	/**
	 * 锁车
	 * @param Car
	 * @param lock
	 * @param validity
	 * @author y
	 * @return
	 */
	@RequestMapping("lockCar")
	@ResponseBody
	public String lockCar(String car,Integer lock,
			@RequestParam(value="validity",required=false)String validity){
		RetJsonUtil retJsonUtil = new RetJsonUtil();
		Map<String, String> paramMap = new HashMap<>();
		int flag;
		Lock lockBean = new Lock();
//		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH");
		/**
		 * validity 自动开锁时间。手动解锁需撤销时间任务。
		 */
		if(validity != null && validity != ""){
			lockBean.setValidity(validity);
		}
		lockBean.setCar(car);
		
		if(car == null || lock == null){
			retJsonUtil.setStatus("0");
			retJsonUtil.setMessage("参数错误！");
			return retJsonUtil.toString();
		}
		JSONObject jsonParam = new JSONObject();
		jsonParam.put("ActType","000008");
		jsonParam.put("Car", car);
		jsonParam.put("Lock", String.valueOf(lock));
		jsonParam.put("CRC", "123");
//		paramMap.put("Car", car);
//		paramMap.put("Lock", String.valueOf(lock));
		
		try {
			HttpClientUtil.createConnection(PARKING_URI, jsonParam.toString());
			String content = HttpClientUtil.getResponse();
			System.out.println("StatusCode:"+HttpClientUtil.getStatusCode());
			if(HttpClientUtil.getStatusCode()==200){
				JSONObject jsonObject = JSONObject.fromObject(content);
				logger.debug(jsonObject.getString("ActType"));
				if(jsonObject.getString("ActType").equals("000008") && 
						jsonObject.getString("Code").equals("00")){
					if(lock==1){
						flag = lockService.locked(lockBean);	//锁车
					}else{
						flag = lockService.unlock(car);			//解锁
					}
					String msg = jsonObject.getString("Msg");
					//执行插入操作成功
					if(flag>0){
						retJsonUtil.setStatus("1");
						retJsonUtil.setMessage(msg);
					}else{
						retJsonUtil.setStatus("0");
						retJsonUtil.setMessage(msg);
					}
				}else{
					retJsonUtil.setStatus("-1");
					retJsonUtil.setMessage("fail");
				}
			}
			HttpClientUtil.closeConnection();	
			
		} catch (URISyntaxException | IOException e) {
			e.printStackTrace();
		} 
		
		return retJsonUtil.getRetJsonM();
	}
	/**
	 * 车位管理费续费通知
	 */
	public String berthPayNotice(BerthOrder berthOrder){

		String retString = null;
		
		JSONObject paramJson = new JSONObject();
		paramJson.put("ActType", "000009");
		paramJson.put("Money", berthOrder.getMoney());
		paramJson.put("ParkingId", berthOrder.getUserCommunityId());
		paramJson.put("Berth", berthOrder.getBerthNumber());
		paramJson.put("CRC", "123");
		try {
			HttpClientUtil.createConnection(PARKING_URI, paramJson);
			String content = HttpClientUtil.getResponse();
			JSONObject jsonObject = JSONObject.fromObject(content);
			if(jsonObject.getString("ActType").equals("000009") && 
					jsonObject.getString("Code").equals("00")){
				//成功发送通知了 要更新一下有效期
				BerthMessage berthMessage = new BerthMessage();
				String endTime = jsonObject.getString("endTime");
				berthMessage.setBerthNumber(berthOrder.getBerthNumber());
				berthMessage.setUserCommunityId(berthOrder.getUserCommunityId());
				berthMessage.setContractEndtime(endTime);
				berthMessage.setContractStarttime(DateUtil.dateToStr(new Date(),DateUtil.DATE_TIME));
				berthMessage.setHandInCost(berthOrder.getMoney());
				
				//更新了实交车位管理费 重置合同有效期
				int flag = plateservice.buyBerth2(berthMessage);
				if(flag > 0){
					retString = "success";
				}else{
					retString = "fail";
				}
			}
			HttpClientUtil.closeConnection();	
		} catch (URISyntaxException e) {
			retString = "fail";
			e.printStackTrace();
		} catch (ClientProtocolException e) {
			retString = "fail";
			e.printStackTrace();
		} catch (IOException e) {
			retString = "fail";
			e.printStackTrace();
		}
		
		return retString;
	}
	
	/**
	 * 车位管理费购买通知
	 */
	public String buyBerthNotice(BerthMessage berthMessage){

		String retString = null;
		Map<String, Object> map = new HashMap<>();
		List<CarMessage> list = new ArrayList<>();
		JSONObject paramJson = new JSONObject();
		
		//获取车辆
		map.put("userCommunityId", berthMessage.getUserCommunityId());
		map.put("userPhone", berthMessage.getUserPhone());
		list = plateservice.getUserCarMessage(map);
		//参数封装
		paramJson.put("ActType", "000010");
		paramJson.put("Money", berthMessage.getHandInCost());
		paramJson.put("ParkingId", berthMessage.getUserCommunityId());
		paramJson.put("Berth", berthMessage.getBerthNumber());
		paramJson.put("Phone", berthMessage.getUserPhone());
		paramJson.put("Name", berthMessage.getUserName());
		paramJson.put("CRC", "123");
		for (CarMessage carMessage : list) {
			paramJson.put("Car", carMessage.getPlateNumber());
			try {
				HttpClientUtil.createConnection(PARKING_URI, paramJson);
				String content = HttpClientUtil.getResponse();
				JSONObject jsonObject = JSONObject.fromObject(content);
				if(jsonObject.getString("ActType")=="000010" && 
						jsonObject.getString("Code")=="00"){
					//成功发送通知了 要更新一下有效期
					String endTime = jsonObject.getString("endTime");
					berthMessage.setContractEndtime(endTime);
					berthMessage.setContractStarttime(DateUtil.dateToStr(new Date(),DateUtil.DATE_TIME));
					
					//更新了实交车位管理费 重置合同有效期
					int flag = plateservice.buyBerth2(berthMessage);
					if(flag > 0){
						retString = "success";
					}else{
						retString = "fail";
					}
				}
				HttpClientUtil.closeConnection();	
			} catch (URISyntaxException e) {
				retString = "fail";
				e.printStackTrace();
			} catch (ClientProtocolException e) {
				retString = "fail";
				e.printStackTrace();
			} catch (IOException e) {
				retString = "fail";
				e.printStackTrace();
			}
		}
		return retString;
	}
	//更新车位管理费
	public String updateBerthCostNotice(BerthMessage berthMessage) {
		String retStr = "fail";
		JSONObject paramJson = new JSONObject();
		paramJson.put("ActType", "000010");
		paramJson.put("ParkingId", berthMessage.getUserCommunityId());
		paramJson.put("Berth", berthMessage.getBerthNumber());
		paramJson.put("BerthCost", berthMessage.getBerthCost());
		paramJson.put("CRC", "123");

		try {
			HttpClientUtil.createConnection(PARKING_URI, paramJson.toString());
			String content = HttpClientUtil.getResponse();
			JSONObject jsonObject = JSONObject.fromObject(content);
			if (jsonObject.getString("ActType").equals("000011")
					&& jsonObject.getString("Code").equals("00")) {
				retStr="success";
			}
			HttpClientUtil.closeConnection();
		} catch (URISyntaxException e) {
			e.printStackTrace();
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return retStr;
	}
}
