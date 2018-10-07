package com.zeng.ezsh.wy.admin.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.zeng.ezsh.wy.entity.GridRoomItem;
import com.zeng.ezsh.wy.service.GridRoomService;
import com.zeng.ezsh.wy.utils.RetJsonUtil;
@Controller
@RequestMapping(value = "gridRoom")
public class GridRoomAdminAction {
	private static Logger logger =Logger.getLogger(ChargeRoomIdsAdminAction.class);
	@Resource
	private GridRoomService gridRoomService;
	
	/**
	 * @author qwc
	 * 2017年9月20日下午4:08:52 void
	 * 添加房间表计类别
	 * @throws IOException 
	 */
	@RequestMapping(value="add",method=RequestMethod.POST)
	public void addRoomCharge(HttpServletResponse response, GridRoomItem gridRoomItem) throws IOException{
		PrintWriter out=response.getWriter();
		Map<String, Object> retMap = new HashMap<String, Object>();
		JSONObject retJson=new JSONObject();
		int status=gridRoomService.insert(gridRoomItem);
		if(status>0){
			retJson.put("status", 1);
			retJson.put("data", retMap);
			retJson.put("message", "添加成功");
		}else{
			retJson.put("status", 0);
			retJson.put("data", retMap);
			retJson.put("message", "添加失败");
		}
		out.write(retJson.toString());
	}
	
	
	/**
	 * @author qwc
	 * 2017年10月24日下午7:35:07
	 * @param response
	 * @param chargeRoomIds
	 * @throws IOException 
	 * void 删除房间表计类别
	 */
	@RequestMapping(value="delete")
	public void deleteRoomCharge(HttpServletResponse response, GridRoomItem gridRoomItem) throws IOException{
		PrintWriter out=response.getWriter();
		RetJsonUtil retJsonUtil = new RetJsonUtil();//构建json返回对象

		int status=gridRoomService.deleteByPrimaryKey(gridRoomItem.getRoomGridItemId());//执行删除
		//返回结果
		if(status>0){
			retJsonUtil.setStatus("1");
			retJsonUtil.setMessage("删除成功");
		}else{
			retJsonUtil.setStatus("0");
			retJsonUtil.setMessage("删除失败");
		}
		out.write(retJsonUtil.getRetJsonM());
	}
	
	/**
	 * @author qwc
	 * 2017年9月20日下午7:52:36 void
	 * 获取房间表计类别列表
	 * @throws IOException 
	 */
	@RequestMapping(value = "select",method = RequestMethod.POST)
	public void getRoomChargeItems(@RequestParam(value = "roomId", required = false, defaultValue = "0") Integer roomId,
			HttpServletResponse response) throws IOException{
		PrintWriter out = response.getWriter();
		RetJsonUtil retJsonUtil = new RetJsonUtil();//构建json返回对象
		
		//查询
		Map<String, Object> param = new HashMap<String,Object>();
		param.put("ptRoomId", roomId);//设置查询条件
		List<GridRoomItem> list = gridRoomService.selectGridRoomItemsByParam(param);//执行查询获取
		
		//返回结果
		retJsonUtil.setList(list);
		retJsonUtil.setTotal(list.size());
		out.write(retJsonUtil.getRetJsonEasyGrid());
	}
	
	/**
	 * @author qwc
	 * 2017年11月24日下午8:10:09
	 * @param response
	 * @param gridRoomItem
	 * @throws IOException 
	 * void 修改房间表计类别信息
	 */
	@RequestMapping(value = "update",method = RequestMethod.POST)
	public void updateRoomCharge(HttpServletResponse response, GridRoomItem gridRoomItem) throws IOException{
		PrintWriter out = response.getWriter();
		Map<String, Object> retMap = new HashMap<String, Object>();
		JSONObject retJson = new JSONObject();
		int status = gridRoomService.updateByPrimaryKeySelective(gridRoomItem);
		if(status > 0){
			retJson.put("status", 1);
			retJson.put("data", retMap);
			retJson.put("message", "添加成功");
		}else{
			retJson.put("status", 0);
			retJson.put("data", retMap);
			retJson.put("message", "添加失败");
		}
		out.write(retJson.toString());
	}
}
