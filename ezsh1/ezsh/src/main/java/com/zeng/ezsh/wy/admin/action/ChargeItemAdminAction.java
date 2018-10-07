package com.zeng.ezsh.wy.admin.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zeng.ezsh.wy.action.BaseAction;
import com.zeng.ezsh.wy.entity.ChargeItem;
import com.zeng.ezsh.wy.entity.ChargeRoomIds;
import com.zeng.ezsh.wy.service.ChargeItemService;
import com.zeng.ezsh.wy.service.ChargeRoomIdsService;
import com.zeng.ezsh.wy.utils.RetJsonUtil;
@RequestMapping(value="charge")
@Controller
public class ChargeItemAdminAction extends BaseAction{
	@Resource
	ChargeItemService chargeItemService;
	@Resource
	ChargeRoomIdsService chargeRoomIdsService;
	
	/**
	 * @author qwc
	 * 2017年9月16日下午9:37:33 void
	 * 添加收费项目
	 * @throws IOException 
	 */
	@RequestMapping(value="add")
	public void addChargeItemAction(ChargeItem chargeItem,HttpServletResponse response,
			HttpServletRequest request) throws IOException{
		PrintWriter out=response.getWriter();
		RetJsonUtil retJsonUtil=new RetJsonUtil(); 
		
		//检测收费项目名是否存在
		int check=chargeItemService.checkChargeItemNameIsOnUse(chargeItem);
		if(check>0){
			retJsonUtil.setMessage("收费项目名已存在");
			retJsonUtil.setStatus("-1");
			out.write(retJsonUtil.getRetJsonM());
			return;
		}
		
		/*检测打印次序是否存在*/
		int checkOrder=chargeItemService.checkChargeItemNamePrintOrderIsOnUse(chargeItem);
		if(checkOrder>0){
			retJsonUtil.setMessage("打印次序已存在");
			retJsonUtil.setStatus("-2");
			out.write(retJsonUtil.getRetJsonM());
			return;
		}
		
		//添加
		int  status=chargeItemService.insert(chargeItem);
		if(status>0){
			retJsonUtil.setMessage("添加成功");
			retJsonUtil.setStatus("1");
		}else{
			retJsonUtil.setMessage("添加失败");
			retJsonUtil.setStatus("0");
		}
		out.write(retJsonUtil.getRetJsonM());
	}
	
	/**
	 * @author qwc
	 * 2017年9月16日下午9:37:02 void
	 * 获取收费项目集合
	 * @throws IOException 
	 */
	@RequestMapping(value="select")
	public void selectChargeList(HttpServletResponse response,HttpServletRequest request,ChargeItem chargeItem,
			@RequestParam(value="page",required=true,defaultValue="1") Integer startPage,
			@RequestParam(value="rows",required=false,defaultValue="10") Integer pageSize) throws IOException{
		PrintWriter out=response.getWriter();
		RetJsonUtil retJsonUtil=new RetJsonUtil();//json返回对象 
		
		//返回结果
		if(chargeItem.getpManagerId()==null|| chargeItem.getpManagerId()==0){
			retJsonUtil.setTotal(0);
			out.write(retJsonUtil.getRetJsonEasyGrid());
			return;
		}
		
		Map<String, Object> paramMap = new HashMap<String, Object>();//查询条件
		//设置查询条件
		paramMap.put("pManagerId", chargeItem.getpManagerId());//管理处ID
		paramMap.put("chargeName", chargeItem.getChargeName());//项目名
		
		//分页获取
		PageHelper.startPage(startPage, pageSize);
		List<ChargeItem> chargeItemList=chargeItemService.selectByParam(paramMap);
		PageInfo<ChargeItem> page=new PageInfo<ChargeItem>(chargeItemList);
		
		//返回结果
		retJsonUtil.setTotal(page.getTotal());
		retJsonUtil.setList(chargeItemList);
		out.write(retJsonUtil.getRetJsonEasyGrid());
	}
	
	/**
	 * @author qwc
	 * 2017年9月25日下午9:10:01
	 * @param response
	 * @param request
	 * @throws IOException 
	 * void 根据小区ID获取收费项目集合
	 */
	@RequestMapping("selectByManagerId")
	public void selectChargeListByManagerId(HttpServletResponse response,
			HttpServletRequest request) throws IOException{
		PrintWriter out=response.getWriter();
		
		//设置查询条件
		Map<String, Object> paramMap = new HashMap<String, Object>();//创建查询条件Map
		String pManagerId=request.getParameter("pManagerId");//设置管理处ID
		paramMap.put("pManagerId", pManagerId);
		
		//获取收费项目集合
		List<ChargeItem> chargeItemList=chargeItemService.selectByParam(paramMap);
		out.write(JSONArray.fromObject(chargeItemList).toString());
	}
	
	/**
	 * @author qwc
	 * 2017年9月16日下午9:38:10 void
	 * 修改收费项目信息
	 * @throws IOException 
	 */
	@RequestMapping(value="update")
	public void updateCharge(ChargeItem chargeItem,
			HttpServletResponse response) throws IOException{
		PrintWriter out=response.getWriter();
		RetJsonUtil retJsonUtil=new RetJsonUtil();
		
		/*检测打印次序是否存在*/
		int checkOrder=chargeItemService.checkChargeItemNamePrintOrderIsOnUseOnUpdate(chargeItem);
		if(checkOrder>0){
			retJsonUtil.setMessage("打印次序已存在");
			retJsonUtil.setStatus("-2");
			out.write(retJsonUtil.getRetJsonM());
			return;
		}
		
		//执行修改
		int status=chargeItemService.updateByPrimaryKeySelective(chargeItem);
		if(status>0){
			retJsonUtil.setMessage("修改成功");
			retJsonUtil.setStatus("1");
		}else{
			retJsonUtil.setMessage("修改失败");
			retJsonUtil.setStatus("0");
		}
		out.write(retJsonUtil.getRetJsonM());
	}
	
	/**
	 * @author qwc
	 * 2017年10月24日下午1:54:07
	 * @param response
	 * @param chargeItem
	 * @throws IOException 
	 * void 删除收费项目
	 */
	@RequestMapping(value="delete")
	public void deleteRoomCharge(HttpServletResponse response,ChargeItem chargeItem) throws IOException{
		PrintWriter out=response.getWriter();
		RetJsonUtil retJsonUtil=new RetJsonUtil();//构建json返回对象
	
		//检测收费项目是否已投入使用
		ChargeRoomIds chargeRoomIds=new ChargeRoomIds();
		chargeRoomIds.setpChargeItemId(chargeItem.getChargeId());//设置收费项目ID，用于查询
		int check=chargeRoomIdsService.checkChargeItemIsOnUse(chargeRoomIds);//查询结果
		if(check>0){
			retJsonUtil.setStatus("-1");
			retJsonUtil.setMessage("收费项目已投入使用，不能删除");
			out.write(retJsonUtil.getRetJsonM());
			return;
		}
		
		//删除操作
		int status=chargeItemService.delete(chargeItem);//删除
		if(status>0){
			retJsonUtil.setStatus("1");
			retJsonUtil.setMessage("删除成功");
		}else{
			retJsonUtil.setStatus("0");
			retJsonUtil.setMessage("删除成功");
		}
		out.write(retJsonUtil.getRetJsonM());
	}
}
