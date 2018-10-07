package com.zeng.ezsh.wy.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zeng.ezsh.alipay.service.impl.AlipayServiceImpl;
import com.zeng.ezsh.wy.dao.PlateManagementMapper;
import com.zeng.ezsh.wy.entity.BerthMessage;
import com.zeng.ezsh.wy.entity.BerthMessage2;
import com.zeng.ezsh.wy.entity.BerthOrder;
import com.zeng.ezsh.wy.entity.CarManageResult;
import com.zeng.ezsh.wy.entity.CarMessage;
import com.zeng.ezsh.wy.entity.HouseMessage;
import com.zeng.ezsh.wy.entity.InviteRecord;
import com.zeng.ezsh.wy.entity.ParkRecord;
import com.zeng.ezsh.wy.entity.PlateCost;
import com.zeng.ezsh.wy.entity.PlateResult;
import com.zeng.ezsh.wy.entity.ResidentialUser;
import com.zeng.ezsh.wy.exception.PlateException;
import com.zeng.ezsh.wy.service.ParkPayService;
import com.zeng.ezsh.wy.service.PlateManagementService;
import com.zeng.ezsh.wy.utils.AccessTokenUtil;
import com.zeng.ezsh.wy.utils.DateUtil;
import com.zeng.ezsh.wy.utils.GetIpAdrr;
import com.zeng.ezsh.wy.utils.JsonUtil;
import com.zeng.ezsh.wy.utils.RetJsonUtil;
/**
 * Title:PlateManageAction
 * Description:停车管理
 * @author HAO
 * @date:2017年8月7日 下午8:24:58
 */
@Controller
@RequestMapping("/plate")
public class PlateManageAction {
	
	@Resource
	private PlateManagementService plateservice;
	
	@Resource
	private PlateManagementMapper platedao;
	
	@Resource
	private AlipayServiceImpl alipayService;
	
	@Resource
	private ParkPayService parkPayService;
	/**
	 * 
	 * @param userCommunityId
	 * @param userPhone
	 * @return PlateResult
	 * Description:用户获取车辆信息接口
	 * @author:HAO
	 */
	@RequestMapping(value="/usergetcars",method=RequestMethod.GET,produces = "application/json")
	@ResponseBody
	public CarManageResult<List<CarMessage>> getCar(@RequestParam("userCommunityId") int userCommunityId,@RequestParam("userPhone") String userPhone){
		CarManageResult<List<CarMessage>> plateResult = null;
		Map<String,Object> map = new HashMap<>();
		map.put("userCommunityId", userCommunityId);
		map.put("userPhone", userPhone);
		try{
			List<CarMessage> list = plateservice.getUserCarMessage(map);
			if(list.size() != 0){
				plateResult = new CarManageResult<List<CarMessage>>(1,list,"获取成功");
			}else{
				plateResult = new CarManageResult<List<CarMessage>>(0,"无车辆信息");
			}
		}catch(Exception e){
			e.printStackTrace();
			plateResult = new CarManageResult(0,"系统内部错误");
		}
		return plateResult;	
	}
	
	
	/**
	 * 
	 * @param car
	 * @return
	 * Description:用户修改信息
	 * @author:HAO
	 */
	@RequestMapping(value="modifyCar",method=RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public CarManageResult modifyCar(CarMessage car){
		CarManageResult result = null;
		try{
			if(plateservice.modifyCar(car) == 1){
				result = new CarManageResult(1,"操作成功");
			}else{
				result = new CarManageResult(0,"操作失败");
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * 
	 * @param plateNumber
	 * @return PlateResult
	 * Description:用户删除车辆接口
	 * @author:HAO
	 */
	@RequestMapping(value="/deletecars",method=RequestMethod.POST, produces = "application/json;charset=utf-8")
	@ResponseBody
	public CarManageResult deleteCarMessageByUser(@RequestParam("plateNumber") String plateNumber){
		CarManageResult plateResult = null;
		try{
			if(plateservice.deleteCar(plateNumber) == 1){
				plateResult = new CarManageResult(1,"操作成功");
			}else{
				plateResult = new CarManageResult(0,"操作失败");
			}
		}catch(Exception e){
			e.printStackTrace();
			plateResult = new CarManageResult(0,"系统内部错误");
		}
		return plateResult;
		
	}
	
	/**
	 * 
	 * @param inviteRecord
	 * @return PlateResult
	 * Description:用户邀请访客接口
	 * @author:HAO
	 */
	@RequestMapping(value="/inviteRecord",method=RequestMethod.POST, produces = "application/json;charset=utf-8")
	@ResponseBody
	public PlateResult inviteGuest(InviteRecord inviteRecord){
		PlateResult plateResult = null;
		try{
			if(plateservice.judgeInvitedCar(inviteRecord.getInvitePlate()) == null){
			if(plateservice.inviteGuest(inviteRecord) == 1){
				plateResult = new PlateResult(true,"邀请成功");
			}
			}else{
				plateResult = new PlateResult(false,"该车辆已被邀请");
			}
		}catch(PlateException e){
			e.printStackTrace();
			plateResult = new PlateResult(false,"系统内部错误");
		}
		return plateResult;
	}
	
	/**
	 * 
	 * @param userPhone
	 * @param userCommunityId
	 * @return PlateResult
	 * Description:用户查看邀请情况接口
	 * @author:HAO
	 */
	@RequestMapping(value="/inviteRecord/{userPhone}/{userCommunityId}",method=RequestMethod.GET)
	@ResponseBody
	public PlateResult getInviteRecordByUser(@PathVariable("userPhone") String userPhone,@PathVariable("userCommunityId") int userCommunityId){
		PlateResult plateResult = null;
		Map<String,Object> map = new HashMap<>();
		map.put("userPhone", userPhone);
		map.put("userCommunityId", userCommunityId);
		try{
			List<String> invitePlate = plateservice.seeUserInvite(map);
			if(invitePlate.size() != 0){
				plateResult = new PlateResult(true,invitePlate);
			}else{
				plateResult = new PlateResult(false,"无邀请记录");
			}
		}catch(Exception e){
			e.printStackTrace();
			plateResult = new PlateResult(false,"系统内部错误");
		}
		return plateResult;
		
	}
		
		/**
		 * 
		 * @param invitePlate
		 * @return PlateResult
		 * Description:用户取消邀请接口
		 * @author:HAO
		 */
		@RequestMapping(value="/inviteRecord",method=RequestMethod.DELETE)
		@ResponseBody
		public PlateResult cancelInviteByUser(@RequestParam("invitePlate") String invitePlate){
			PlateResult plateResult = null;
			try{
				if(plateservice.cancelInvite(invitePlate) == 1){
					plateResult = new PlateResult(true,"操作成功");
				}
			}catch(Exception e){
				e.printStackTrace();
				plateResult = new PlateResult(false,"系统内部错误");
			}
			return plateResult;	
		}
	
	/**
	 * 
	 * @param berthMessage
	 * @return PlateResult
	 * Description:后台添加车位接口
	 * @author:HAO
	 */
	
	@RequestMapping(value="/berthmessage",method=RequestMethod.POST, produces = "application/json")	
	@ResponseBody	
	public PlateResult addBerthMessage(BerthMessage berthMessage){
		PlateResult plateresult = null;
		try{
			if(plateservice.setBerth(berthMessage) == 1){
				plateresult = new PlateResult(true,"操作成功");
			}
		}catch(PlateException e1){
			e1.printStackTrace();
			plateresult = new PlateResult(false,"重复添加");
		}catch(Exception e2){
			e2.printStackTrace();
			plateresult = new PlateResult(false,"系统内部错误");
		}
		return plateresult;
	}
	
	/**
	 * 
	 * @param berthNumber
	 * @param userCommunityId
	 * @return PlateResult
	 * Description:后台删除车位
	 * @author:HAO
	 */
	@RequestMapping(value="/berthmessage",method=RequestMethod.DELETE, produces = "application/json")
	@ResponseBody
	public PlateResult deleteBerth(@RequestParam("berthNumber") String berthNumber,@RequestParam("id") int id){
		Map<String,Object> map = new HashMap<>();
		map.put("berthNumber", berthNumber);
		map.put("id", id);
		PlateResult plateResult = null;
		try{
			if(plateservice.deleteBerth(map) == 1){
				plateResult = new PlateResult(true,"操作成功");
			}else{
				plateResult = new PlateResult(false,"操作失败");
			}
		}catch(Exception e){
			e.printStackTrace();
			plateResult = new PlateResult(false,"系统内部错误");
		}
		return plateResult;
	}
	/**
	 * 
	 * @param berthNumber
	 * @param userCommunityId
	 * @return PlateResult
	 * Description:搜索车位记录
	 * @author:HAO
	 */
	@RequestMapping(value="/berthmessage/{berthNumber}/{userCommunityId}",method=RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public PlateResult searchBerthMessage(@PathVariable("berthNumber") String berthNumber,@PathVariable("userCommunityId") int userCommunityId){
		PlateResult plateResult = null;
		Map<String,Object> map = new HashMap<>();
		map.put("berthNumber", berthNumber);
		map.put("userCommunityId", userCommunityId);
		BerthMessage berthMessage = plateservice.searchBerthRecord(map);
		if(berthMessage!=null){
			plateResult = new PlateResult(true,berthMessage);
		}else{
			plateResult = new PlateResult(false,"无数据");
		}
		return plateResult;
	}
	
	/**
	 * 
	 * @param userPhone
	 * @param berthNumber
	 * @param userCommunityId
	 * @return PlateResult
	 * Description:用户购买车位接口     ps:(按照选择的合同时间生成对应的支付金额 需要停车端提供接口)
	 * @author:HAO
	 * @throws ParseException 
	 */
	@RequestMapping(value="/berthmessage",method=RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public String userBuyBerth(
			HttpServletRequest request,
			@RequestParam("berthNumber") String berthNumber,
			@RequestParam("userCommunityId") int userCommunityId,
			@RequestParam("handinCost") Double handinCost,
			@RequestParam("payWay") String payWay) throws ParseException{
		Map<String,Object> map = new HashMap<>();
		Map<String, Object> retMap = new HashMap<String, Object>();
		RetJsonUtil retJsonUtil = new RetJsonUtil();
		
		String realIp = GetIpAdrr.getIpAddr(request);
		
		BerthOrder berthOrder = new BerthOrder();
		berthOrder.setBerthNumber(berthNumber);
		berthOrder.setMoney(handinCost);
		berthOrder.setPayway(payWay);
		berthOrder.setUserCommunityId(userCommunityId);

		map.put("berthOrder", berthOrder);
		map.put("realIp", realIp);
		retMap.put("orderInfo", parkPayService.addBerthOrder(map));
		retJsonUtil.setStatus("1");
		retJsonUtil.setRetMap(retMap);
		retJsonUtil.setMessage("操作成功");
		return retJsonUtil.getRetJsonM();
	}
	
	/**
	 * 
	 * @param berthNumber
	 * @param userCommunityId
	 * @return PlateResult
	 * Description:用户取消购买车位接口
	 * @author:HAO
	 */
	@RequestMapping(value="/berthMessage",method=RequestMethod.PUT, produces = "application/json")
	@ResponseBody
	public PlateResult cancelBuyBerth(@RequestParam("berthNumber") String berthNumber,@RequestParam("userCommunityId") int userCommunityId){
		PlateResult plateResult = null;
		Map<String,Object> map = new HashMap();
		map.put("berthNumber", berthNumber);
		map.put("userCommunityId", userCommunityId);
		try{
			if(plateservice.cancelBuyBerth(map) == 1){
				plateResult = new PlateResult(true,"操作成功");
			}
		}catch(Exception e){
			e.printStackTrace();
			plateResult = new PlateResult(false,"系统内部错误");
		}
		return plateResult;
		
	}
	
	/**
	 * 
	 * @return
	 * Description:后台查看所有车位信息接口
	 * @author:HAO
	 */
	@RequestMapping(value="/berthMessage",method=RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public Map<Object,Object> getAllBerthMessage(@RequestParam("page") int page,@RequestParam("rows") int rows,@RequestParam("status")String status){
		
		Map<String,Object> pageMap = new HashMap<>();
		pageMap.put("start", (page-1)*10);
		pageMap.put("rows", rows);
		pageMap.put("status", status);
		List<BerthMessage> list = plateservice.getAllBerthMessage(pageMap);
		for(int i=0;i<list.size();i++){
			BerthMessage berthMessage = list.get(i);
			berthMessage.setContractStarttime(DateUtil.dateToStr(berthMessage.getContractStartTime(), 12));
			berthMessage.setContractEndtime(DateUtil.dateToStr(berthMessage.getContractEndTime(), 12));
		}
		Map<Object,Object> jo = new HashMap<>();
		//JSONArray json = JSONArray.fromObject(list);
		jo.put("total", plateservice.getBerthCount());
		jo.put("rows", list);
		return jo;

	}
	
	/**
	 * 
	 * @param identityCard
	 * @return CarManageResult
	 * Description:获取用户房屋信息接口
	 * @author:HAO
	 */
	@RequestMapping(value="/house",method=RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public CarManageResult<List<HouseMessage>> getUserHouses(@RequestParam("identityCard") String identityCard){
		CarManageResult<List<HouseMessage>> carResult = null;
		if(identityCard == null || identityCard == ""){
			return new CarManageResult<List<HouseMessage>>(-1,"无有效身份证信息");
		}
		List<HouseMessage> list = plateservice.getUserHouseMessage(identityCard);
		if(list.size()!=0){
			carResult = new CarManageResult<List<HouseMessage>>(1,list,"获取成功");
		}else{
			carResult = new CarManageResult<List<HouseMessage>>(0,"无数据");
		}
		return carResult;
	}
	
	/**
	 * 
	 * @param car
	 * @param berthNumber
	 * @return CarManageResult
	 * Description:用户添加车辆信息接口
	 * @author:HAO
	 */
	@RequestMapping(value="/cars",method=RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public CarManageResult addCar(CarMessage car){
		//TODO验证该车主是否有车位
		CarManageResult plateresult = null;
		Map<String,Object> map = new HashMap<>();
		try{
			if(plateservice.addCar(car) == 1){
				plateresult = new CarManageResult(1,"操作成功");
			}
		}catch(PlateException e){
			e.printStackTrace();
			plateresult = new CarManageResult(0,"重复添加");
		}catch(Exception e){
			e.printStackTrace();
			plateresult = new CarManageResult(-1,"系统内部错误");
		}
		return plateresult;

	}
	
	/**
	 * 
	 * @return CarManageResult
	 * Description:用户删除车辆信息接口
	 * @author:HAO
	 */
	@RequestMapping(value="deleteCar",method=RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public CarManageResult deleteUserCar(int userCommunityId,String userPhone,String plateNumber){
		CarManageResult plateresult = null;
		Map<String,Object> map = new HashMap<>();
		map.put("userCommunityId", userCommunityId);
		map.put("userPhone", userPhone);
		map.put("plateNumber", plateNumber);
		int result = plateservice.deleteUserCar(map);
		if(result == 1){
			plateresult = new CarManageResult(1,"操作成功");
		}else{
			plateresult = new CarManageResult(0,"操作失败");
		}
		return plateresult;
	}	
	
	/**
	 * 
	 * @param handinCost
	 * @param berthNumber
	 * @param userCommunityId
	 * @return PlateResult
	 * Description:用户提交管理费接口
	 * @author:HAO
	 */
	@RequestMapping(value="/managecost",method=RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public String handPlateCost(HttpServletRequest request,
			@RequestParam("handinCost") double handinCost,
			@RequestParam("berthNumber") String berthNumber,
			@RequestParam("userCommunityId") int userCommunityId,
			@RequestParam("payWay") String payWay){
		RetJsonUtil retJsonUtil = new RetJsonUtil();
		Map<String,Object> retMap = new HashMap<>();
		Map<String,Object> map = new HashMap<>();
		BerthOrder berthOrder = new BerthOrder();
		
		berthOrder.setBerthNumber(berthNumber);
		berthOrder.setUserCommunityId(userCommunityId);
		berthOrder.setMoney(handinCost);
		berthOrder.setPayway(payWay);
		
		map.put("berthOrder", berthOrder);
		map.put("realIp", GetIpAdrr.getIpAddr(request));
		
		//缴费
		String orderInfo = parkPayService.addBerthOrder(map);
		if(orderInfo == null){
			retJsonUtil.setStatus("0");
			retJsonUtil.setMessage("操作失败");
		}else{
			retMap.put("orderInfo", orderInfo);
			retJsonUtil.setStatus("1");
			retJsonUtil.setMessage("操作成功");
			retJsonUtil.setRetMap(retMap);
		}
		return retJsonUtil.getRetJsonM();
	}
	/**
	 * 
	 * @param userPhone
	 * @param userCommunityId
	 * @return PlateResult
	 * @Description:用户查看车位信息接口
	 * @author y
	 */
	@RequestMapping(value="/uberthmessage",method=RequestMethod.GET, produces = "application/json")
//	@ResponseBody
	public void getBerthMessageByUser(
			HttpServletResponse response,
			@RequestParam("userPhone") String userPhone,
			@RequestParam("userCommunityId") int userCommunityId){
		PrintWriter out = null ;
		Map<String,Object> map = new HashMap<>();
		map.put("userPhone", userPhone);
		map.put("userCommunityId", userCommunityId);
		CarManageResult plateResult = null;
		try{
			out = response.getWriter();
			List<BerthMessage2> list = plateservice.getUserBerthMessage2(map);
			for (BerthMessage2 berthMessage2 : list) {
				System.out.println(berthMessage2);
			}
			if(list.size()==0){
				plateResult = new CarManageResult(0,"未购买车位，无车位信息");
			}else{
				//map1.put("managecost", plateservice.getPlateCost(userCommunityId));
				//map1.put("berthMessageList", list);
//				String listJson = JsonUtil.list2json(list);
				plateResult = new CarManageResult(1,list,"获取成功");
				
			}
		}catch(Exception e){
				e.printStackTrace();
				plateResult = new CarManageResult(-1,"系统内部错误");
		}finally{
			String retJson = JsonUtil.bean2json(plateResult);
//			response.setCharacterEncoding("utf-8");
			response.setContentType("text/html;charset=utf-8");
			out.write(retJson);
		}
//		return plateResult;
//		return retJson;
	}
	/**
	 * 
	 * @param hirePlate
	 * @param berthNumber
	 * @param userCommunityId
	 * @param userPhone
	 * @return PlateResult
	 * Description:用户出租车位接口
	 * @author:HAO
	 */
	@RequestMapping(value="/hireberth",method=RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public CarManageResult hireBerth(@RequestParam("hirePlate") String hirePlate,@RequestParam("berthNumber") String berthNumber,@RequestParam("userCommunityId") int userCommunityId,@RequestParam("userPhone") String userPhone){
		Map<String,Object> map = new HashMap<>();
		CarManageResult plateResult = null;
		map.put("hirePlate", hirePlate);
		map.put("berthNumber", berthNumber);
		map.put("userCommunityId", userCommunityId);
		BerthMessage berMessage = new BerthMessage(berthNumber);
		CarMessage carMessage = new CarMessage(hirePlate,userPhone,berMessage,userCommunityId);
		try{
			if(plateservice.hireBerth(map, carMessage) == 1){
				plateResult = new CarManageResult(1,"操作成功");
			}
		}catch(PlateException e1){
			e1.printStackTrace();
			plateResult = new CarManageResult(0,"操作失败，该车位已出租");
		}catch(Exception e2){
			e2.printStackTrace();
			plateResult = new CarManageResult(-1,"系统内部错误");
		}
		return plateResult;
	}
	
	/**
	 * 
	 * @param berthNumber
	 * @param userCommunityId
	 * @return
	 * Description:用户取消出租车位接口
	 * @author:HAO
	 */
	@RequestMapping(value="/cancelhireberth",method=RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public CarManageResult cancelHireBerth(@RequestParam("berthNumber") String berthNumber,@RequestParam("userCommunityId") int userCommunityId){
		Map<String,Object> map = new HashMap<>();
		CarManageResult plateResult = null;
		map.put("berthNumber", berthNumber);
		map.put("userCommunityId", userCommunityId);
		try{
			if(plateservice.cancelHireBerth(map) == 1){
				plateResult = new CarManageResult(1,"操作成功");
			}else{
				plateResult = new CarManageResult(0,"该车位并未出租");
			}
		}catch(Exception e){
			e.printStackTrace();
			plateResult = new CarManageResult(-1,"系统内部错误");
		}
		return plateResult;
	}
	
	/**
	 * 
	 * @param plateCost
	 * @return PlateResult
	 * Description:后台设置各小区管理费接口
	 * @author:HAO
	 */
	@RequestMapping(value="/platecost",method=RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public PlateResult setPlateCost(PlateCost plateCost){
		PlateResult plateResult = null;
		try{
			if(plateservice.setPlateCost(plateCost) == 1){
				plateResult = new PlateResult(true,"操作成功");
			}
		}catch(PlateException e1){
			e1.printStackTrace();
			plateResult = new PlateResult(false,"重复添加");
		}catch(Exception e2){
			e2.printStackTrace();
			plateResult = new PlateResult(false,"系统内部错误");
		}
		return plateResult;
	}
	
	/**
	 * 
	 * @return PlateResult
	 * Description:后台查看各小区费用情况接口
	 * @author:HAO
	 */
	@RequestMapping(value="/plateCost",method=RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public Map<Object,Object> getAllPlateCost(@RequestParam("page") int page,@RequestParam("rows") int rows){
		Map<String,Object> pageMap = new HashMap<>();
		pageMap.put("start", (page-1)*10);
		pageMap.put("rows", rows);
		List<PlateCost> list = plateservice.getAllPlateCost(pageMap);
		Map<Object,Object> jo = new HashMap();
		JSONArray json = JSONArray.fromObject(list);
		
		jo.put("total", plateservice.getCostCount());
		jo.put("rows", json);
		return jo;
	}

	/**
	 * 
	 * @param managementCost
	 * @param userCommunityId
	 * @return PlateResult
	 * Description:后台修改小区管理费接口
	 * @author:HAO
	 */
	@RequestMapping(value="/platecost",method=RequestMethod.PUT, produces = "application/json")
	@ResponseBody
	public PlateResult modifyPlateCost(@RequestParam("managementCost") double managementCost,@RequestParam("userCommunityId") int userCommunityId){
		PlateResult plateResult = null;
		Map<String,Object> map = new HashMap<>();
		map.put("managementCost", managementCost);
		map.put("userCommunityId", userCommunityId);
		try{
			if(plateservice.modifyPlateCost(map) == 1){
				plateResult = new PlateResult(true,"操作成功");
			}
		}catch(Exception e){
			e.printStackTrace();
			plateResult = new PlateResult(false,"系统内部错误");
		}
		return plateResult;
	}
	
	/**
	 * 后台查看车辆出入记录
	 * @param page
	 * @param rows
	 * @return
	 */
	@RequestMapping(value="/parkrecord",method=RequestMethod.POST)
	@ResponseBody
	public Map<Object,Object> getAllParkRecord(@RequestParam("page") int page,@RequestParam("rows") int rows){
		Map<String,Object> pageMap = new HashMap<>();
		pageMap.put("start", (page-1)*10);
		pageMap.put("rows", rows);
		List<ParkRecord> list = plateservice.getAllParkRecord(pageMap);
		Map<Object,Object> jo = new HashMap();
		JSONArray json = JSONArray.fromObject(list);
		jo.put("total", plateservice.getParkRecordCount());
		jo.put("rows", json);
		return jo;
	}
	/**
	 * 后台删除车辆出入记录
	 * @param id
	 * @param plateNumber
	 * @return
	 */
	@RequestMapping(value="/parkrecord",method=RequestMethod.DELETE, produces = "application/json")
	@ResponseBody
	public PlateResult deleteRecord(@RequestParam("id") int id,@RequestParam("plateNumber") String plateNumber){
		PlateResult plateResult = null;
		Map<String,Object> map = new HashMap<>();
		map.put("id", id);
		map.put("plateNumber", plateNumber);
		try{
			if(plateservice.deleteRecord(map) == 1){
				plateResult = new PlateResult(true,"操作成功");
			}else{
				plateResult = new PlateResult(false,"操作失败");
			}
		}catch(Exception e){
			plateResult = new PlateResult(false,"系统内部错误");
		}
		return plateResult;
	}
	
	
//	@RequestMapping("/payCost")
//	public void payForCost(String car,String parkingId){
//		Map<String,Object> map = new HashMap();
//		map.put("plateNumber", car);
//		map.put("userCommunityId", Integer.parseInt(parkingId));
//		ParkRecord parkRecord = plateservice.getParkRecord(map);
//		
//	}
	
	@RequestMapping("/payCost")
	@ResponseBody
	public String payForCost(){
		
		//return alipayService.ParkingCost();
		return null;
	}
	
	/**
	 * 获取业主车位信息有无
	 * @throws IOException 
	 */
	@RequestMapping("/getBerth")
	public void getYZBerth(HttpServletRequest request,HttpServletResponse response) throws IOException{
		Map<String, Object> map = new HashMap<>();
		RetJsonUtil jsonUtil = new RetJsonUtil();
		PrintWriter out = response.getWriter();
		
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		//获取用户电话号码
		String accessToken = request.getParameter("token");
		ResidentialUser residentialUser = AccessTokenUtil.parserAccessTokenToModel(accessToken);
		String telephone = residentialUser.getUserAccount();
		//计算该号码车位数
		map.put("telephone", telephone);
		Integer num = plateservice.getYZBerth(telephone);
		
		if(num==0){
			jsonUtil.setStatus("0");
			jsonUtil.setMessage("该手机号无对应车位数据");
		}else{
			String str = "有"+num+"个车位。";
			jsonUtil.setStatus("1");
			jsonUtil.setMessage(str);
			jsonUtil.setObject(num);
		}
		
		out.print(jsonUtil.getRetJsonO());
	}

}
