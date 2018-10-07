package com.zeng.ezsh.wy.admin.action;

import java.io.PrintWriter;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javassist.compiler.ast.NewExpr;
import javassist.expr.NewArray;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zeng.ezsh.wy.entity.Customer;
import com.zeng.ezsh.wy.entity.ResidentialUser;
import com.zeng.ezsh.wy.entity.Room;
import com.zeng.ezsh.wy.entity.UMsIds;
import com.zeng.ezsh.wy.service.CustomerService;
import com.zeng.ezsh.wy.service.ResidentialUserService;
import com.zeng.ezsh.wy.service.RoomService;
import com.zeng.ezsh.wy.service.UMsIdsService;
import com.zeng.ezsh.wy.utils.DateUtil;
import com.zeng.ezsh.wy.utils.RetJsonUtil;

@Controller
@RequestMapping("customer")
public class CustomerAction {
	@Resource
	CustomerService customerService;
	@Resource
	RoomService roomService;
	@Resource
	ResidentialUserService residentialUserService;
	@Resource
	UMsIdsService uMsIdsService;
	/**
	 * @author qwc
	 * 2017年12月1日下午5:45:21
	 * explain:登记房间客户资料
	 * @param request
	 * @param customer
	 * @return 
	 * String
	 */
	@ResponseBody
	@RequestMapping(value="checkIn",method=RequestMethod.POST)
	public String checkInCustomer(HttpServletRequest request,Customer customer){
		RetJsonUtil retJsonUtil = new RetJsonUtil();
		
		int status=customerService.insertWithUpdateRoom(customer);
		if(status>0){
			//如果用户于买房前就已注册移动端，需要在此处将身份证录入
			ResidentialUser user = new ResidentialUser();
			user.setUserAccount(customer.getCustomerTelephone());
			user.setUserName(customer.getCustomerName());
			user.setUserIdentityCard(customer.getCustomerIdCardNumber());
			residentialUserService.updateByUserAccount(user);
			
			retJsonUtil.setMessage("迁入成功");
			retJsonUtil.setStatus("1");
			return retJsonUtil.getRetJsonM();
		}else{
			retJsonUtil.setMessage("迁入失败");
			retJsonUtil.setStatus("0");
			return retJsonUtil.getRetJsonM();
		}
	}
	
	@ResponseBody
	@RequestMapping(value="checkOut",method=RequestMethod.POST)
	public String checkOutCustomer(HttpServletRequest request,Customer customer){
		RetJsonUtil retJsonUtil = new RetJsonUtil();
		
		int status=customerService.checkOut(customer);
		if(status>0){
			retJsonUtil.setMessage("迁出成功");
			retJsonUtil.setStatus("1");
			return retJsonUtil.getRetJsonM();
		}else{
			retJsonUtil.setMessage("迁出失败");
			retJsonUtil.setStatus("0");
			return retJsonUtil.getRetJsonM();
		}
	}
	
	/**
	 * @author qwc
	 * 2017年9月7日下午4:22:01
	 * @return String
	 * 获取客户登记列表（迁入迁出管理）
	 */
	@ResponseBody
	@RequestMapping(value="gtCheckInList",produces = "application/json; charset=utf-8")
	public String getCustomerCheckInList(Room room,
			@RequestParam(value="page",required=true,defaultValue="1") Integer startPage,
			@RequestParam(value="rows",required=false,defaultValue="10") Integer pageSize){
		RetJsonUtil retJsonUtil = new RetJsonUtil();//构建json返回对象
		
		//管理处ID为空时返回结果为空
		if(room.getManagerId() == null || room.getManagerId() == 0){
			retJsonUtil.setTotal(0);
			return retJsonUtil.getRetJsonEasyGrid();
		}
		
		//分页获取
		PageHelper.startPage(startPage, pageSize);
		List<Room> list=customerService.selectCustomerCheckInList(room);
		PageInfo<Room> page=new PageInfo<Room>(list);
		
		//返回结果
		retJsonUtil.setTotal(page.getTotal());
		retJsonUtil.setList(list);
		return retJsonUtil.getRetJsonEasyGrid();
	}
	
	
	/**
	 * @author qwc
	 * 2017年9月7日下午8:40:55
	 * @return String
	 * 跳转到客户档案列表界面
	 */
	@RequestMapping(value="/jumpArchives")
	public String jumpToCustomerArchivesList(){
		return "customer/archivesList";
	}
	
	/**
	 * @author qwc
	 * 2017年9月11日下午4:03:26 void
	 * 更新客户档案资料
	 */
	@ResponseBody
	@RequestMapping(value="/updateArchives")
	public String updateCustomer(@ModelAttribute Customer customerModel){
		RetJsonUtil retJsonUtil = new RetJsonUtil();
		if(customerService.updateByPrimaryKeySelective(customerModel)>0){
			//更新完后需要同步更新房间对应身份证
			Room room = new Room();
			Customer customer = customerService.getCustomerByCusId(customerModel.getCustomerId());
			room.setRoomId(customer.getpRoomId());
			room.setUserIdentityCard(customerModel.getCustomerIdCardNumber());
			roomService.updateRoomSelective(room);
			
			retJsonUtil.setMessage("更新成功！");
			retJsonUtil.setStatus("1");
			return retJsonUtil.getRetJsonM();
		}else{
			retJsonUtil.setMessage("更新失败！");
			retJsonUtil.setStatus("0");
			return retJsonUtil.getRetJsonM();
		}
	}
	
	/**
	 * @author qwc
	 * 2017年9月7日下午5:58:59
	 * @param room
	 * @param startPage
	 * @param pageSize
	 * @return String
	 * 获取房间客户档案列表
	 */
	@ResponseBody
	@RequestMapping(value="gtArchivesList",produces = "application/json; charset=utf-8",method=RequestMethod.GET)
	public String getCustomerArchivesList(Customer customer,HttpServletRequest request,
			@RequestParam(value="page",required=true,defaultValue="1") Integer startPage,
			@RequestParam(value="rows",required=false,defaultValue="10") Integer pageSize){
		PageHelper.startPage(startPage, pageSize);
		RetJsonUtil retJson = new RetJsonUtil();
		
		if(customer.getpManagerId()==null || customer.getpManagerId()==0){
			retJson.setTotal(0);
			return retJson.getRetJsonEasyGrid();
		}
		
		//分页获取
		List<Customer> list=customerService.selectCustomerArchivesList(customer);
		PageInfo<Customer> page=new PageInfo<Customer>(list);
		
		retJson.setList(list);
		retJson.setTotal(page.getTotal());
		return retJson.getRetJsonEasyGrid();
	}
}
