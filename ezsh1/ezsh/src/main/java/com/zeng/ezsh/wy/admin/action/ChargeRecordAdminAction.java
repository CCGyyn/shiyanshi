package com.zeng.ezsh.wy.admin.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zeng.ezsh.wy.dao.ChargeRecordMapper;
import com.zeng.ezsh.wy.dao.UMsIdsMapper;
import com.zeng.ezsh.wy.entity.UMsIds;
import com.zeng.ezsh.wy.utils.DateUtil;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.jsqlparser.statement.delete.Delete;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zeng.ezsh.wy.entity.Admin;
import com.zeng.ezsh.wy.entity.ChargeRecord;
import com.zeng.ezsh.wy.entity.Grid;
import com.zeng.ezsh.wy.service.ChargeRecordService;
import com.zeng.ezsh.wy.utils.RetJsonUtil;
import org.springframework.web.bind.annotation.ResponseBody;

@RequestMapping("chargeRecord")
@Controller
public class ChargeRecordAdminAction {
	@Resource
	ChargeRecordService chargeRecordAdminService;

	@Resource
	ChargeRecordMapper chargeRecordMapper;

	@Resource
	UMsIdsMapper uMsIdsMapper;
	/**
	 * @author qwc
	 * 2017年9月24日下午3:47:25 
	 * void 批量插入应收费记录
	 */
	@RequestMapping("insert")
	public void inserCharge(){
		chargeRecordAdminService.insertChargeRecordBatch();
	}
	
	/**
	 * @author qwc
	 * 2017年9月24日下午3:47:45
	 * void 获取应收费记录
	 * @throws IOException 
	 */
	@RequestMapping("select")
	public void selectRecordByparam(HttpServletResponse response,Integer pRoomId,
			@RequestParam(value="page",required=true,defaultValue="1") Integer startPage,
			@RequestParam(value="rows",required=false,defaultValue="10") Integer pageSize,Integer checkStatus) throws IOException{
		PrintWriter out=response.getWriter();
		RetJsonUtil retJsonUtil = new RetJsonUtil();
		Map<String,Object> map=new HashMap<>();
		map.put("pRoomId",pRoomId);
		map.put("checkStatus",checkStatus);

		//分页获取应收费记录
		PageHelper.startPage(startPage, pageSize);//分页获取
		List<ChargeRecord> list=chargeRecordMapper.selectChargeRecordBypRoomId(map);
		PageInfo<ChargeRecord> page = new PageInfo<ChargeRecord>(list);
		
		//返回结果
		retJsonUtil.setList(list);
		retJsonUtil.setTotal(page.getTotal());
		out.write(retJsonUtil.getRetJsonEasyGrid());
	}
	
	/**
	 * @author qwc
	 * 2017年10月25日下午1:49:21
	 * @param response
	 * void 添加应收费记录
	 * @throws IOException 
	 */
	@RequestMapping(value="add")
	public void add(HttpServletResponse response,ChargeRecord record) throws IOException{
		PrintWriter out=response.getWriter();
		RetJsonUtil retJsonUtil = new RetJsonUtil();

		UMsIds uMsIds=uMsIdsMapper.selectByRoomId(record.getpRoomId());

		//计算总价钱
		BigDecimal amount = record.getChargeAmount();
		BigDecimal price= record.getUnitPrice();
		record.setTotalPrice(price.multiply(amount));
		record.setpUserId(uMsIds.getpUserId());
		record.setpManagerId(uMsIds.getuMsId());
		record.setpBuildingId(uMsIds.getpBuildId());
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy/MM");
		record.setChargeOfDate(sdf.format(new Date()));
		record.setChargeEndDate(DateUtil.getNowMonthLastDay());

		int status=chargeRecordMapper.insertSelective(record);
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
	 * 2017年10月25日下午4:07:15
	 * @param response
	 * @param record 
	 * void 删除应收费记录
	 * @throws IOException 
	 */
	@RequestMapping("delete")
	public void  delete(HttpServletResponse response,ChargeRecord record) throws IOException{
		PrintWriter out=response.getWriter();
		RetJsonUtil retJsonUtil = new RetJsonUtil();//构建json返回对象
		
		int status=chargeRecordAdminService.deleteByPrimaryKey(record);//指向删除操作
		//返回结果
		if(status>0){
			retJsonUtil.setMessage("删除成功");
			retJsonUtil.setStatus("1");
		}else{
			retJsonUtil.setMessage("删除失败");
			retJsonUtil.setStatus("0");
		}
		out.write(retJsonUtil.getRetJsonM());
	}
	
	/**
	 * @author qwc
	 * 2017年10月25日下午4:22:21
	 * @param response
	 * @param record
	 * @throws IOException 
	 * void 更新应收费记录
	 */
	@RequestMapping("update")
	public void  update(HttpServletResponse response,ChargeRecord record) throws IOException{
		PrintWriter out=response.getWriter();
		RetJsonUtil retJsonUtil = new RetJsonUtil();//构建json返回对象
		

		BigDecimal amount = record.getChargeAmount();
		record.setTotalPrice(amount.multiply(record.getUnitPrice()));

		
		int status=chargeRecordAdminService.updateByPrimaryKeySelective(record);
		//返回结果
		if(status>0){
			retJsonUtil.setMessage("更新成功");
			retJsonUtil.setStatus("1");
		}else{
			retJsonUtil.setMessage("更新失败");
			retJsonUtil.setStatus("0");
		}
		out.write(retJsonUtil.getRetJsonM());
	}

	/**
	 * lyd
	 */
	@RequestMapping("checkRecord")
	public void checkRecord(ChargeRecord chargeRecord,HttpServletResponse response) throws IOException {
		PrintWriter out=response.getWriter();
		JSONObject json=new JSONObject();
		int i=0;
		chargeRecord.setCheckStatus(1);
		RetJsonUtil retJsonUtil = new RetJsonUtil();//构建json返回对象
		i=chargeRecordMapper.updateByPrimaryKeySelective(chargeRecord);
		if (i>=1){
			retJsonUtil.setStatus("1");
			retJsonUtil.setMessage("审核成功！");
		}else {
			retJsonUtil.setStatus("0");
			retJsonUtil.setMessage("审核失败！");
		}
		out.write(retJsonUtil.getRetJsonM());
	}
	
	
	/**
	 * 缴费管理controller
	 * url : /chargePay
	 */
	@RequestMapping("/chargePay")
	public void chargePage(ChargeRecord chargeRecord,HttpServletResponse response) throws IOException {
		PrintWriter out=response.getWriter();
		JSONObject json=new JSONObject();
		int i=0;
		Byte b = 1;
		chargeRecord.setStatus(b);
		RetJsonUtil retJsonUtil = new RetJsonUtil();//构建json返回对象

		i=chargeRecordMapper.updateByPrimaryKeySelective(chargeRecord);
		if (i>=1){
			retJsonUtil.setStatus("1");
			retJsonUtil.setMessage("缴费成功！");
		}else {
			retJsonUtil.setStatus("0");
			retJsonUtil.setMessage("缴费失败！");
		}
		out.write(retJsonUtil.getRetJsonM());
	}
	
	/**
	 * @author qwc
	 * 2017年9月24日下午3:47:45
	 * void 获取具体每一年的缴费与未缴费记录
	 * @throws IOException 
	 */
	@RequestMapping("selectAll")
	public void selectRecordByAll(HttpServletResponse response,HttpServletRequest request,Integer pRoomId,
			@RequestParam(value="page",required=true,defaultValue="1") Integer startPage,
			@RequestParam(value="rows",required=false,defaultValue="10") Integer pageSize,Integer checkStatus,String year,Integer pay,String month) throws IOException{
		PrintWriter out=response.getWriter();
		RetJsonUtil retJsonUtil = new RetJsonUtil();
		Map<String,Object> map=new HashMap<>();
		map.put("pRoomId",pRoomId);
		map.put("checkStatus",checkStatus);
		map.put("status",pay );
		StringBuffer yd=new StringBuffer();
		yd.append(year);
		yd.append("/");
		yd.append(month);
		
		map.put("chargeOfDate", yd.toString());
		map.put("month",month);
	
		//分页获取应收费记录
		PageHelper.startPage(startPage, pageSize);//分页获取
		List<ChargeRecord> list=chargeRecordMapper.selectChargeRecordBypRoomId1(map);
		PageInfo<ChargeRecord> page = new PageInfo<ChargeRecord>(list);
		//获取总价
		  Integer sum = chargeRecordMapper.selectChargeRecordBypRoomId2(map);
	      request.getSession().setAttribute("sum", sum);
		//返回结果
		retJsonUtil.setList(list);
		retJsonUtil.setTotal(page.getTotal());
		out.write(retJsonUtil.getRetJsonEasyGrid());
	}
	
	
	/**
	 * 公摊费后台录入专用
	 * @author y
	 */
//	@RequestMapping("updateRecordOfPublic")
//	@ResponseBody
//	public void updateRecordOfPublic(ChargeRecord chargeRecord) {//要有chargeAmount和chargeRecordId
//		RetJsonUtil retJsonUtil = new RetJsonUtil();
//		
//		int i=chargeRecordMapper.updateByPrimaryKeySelective(chargeRecord);
//		if (i>=1){
//			retJsonUtil.setStatus("1");
//			retJsonUtil.setMessage("输入成功！");
//		}else {
//			retJsonUtil.setStatus("0");
//			retJsonUtil.setMessage("输入失败！");
//		}
//		retJsonUtil.getRetJsonM();
//	}
}
