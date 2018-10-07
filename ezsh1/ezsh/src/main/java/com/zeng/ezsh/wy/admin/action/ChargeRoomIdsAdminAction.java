package com.zeng.ezsh.wy.admin.action;

import com.zeng.ezsh.wy.dao.ChargeItemMapper;
import com.zeng.ezsh.wy.dao.GridRecordMapper;
import com.zeng.ezsh.wy.dao.RoomMapper;
import com.zeng.ezsh.wy.entity.ChargeRoomIds;
import com.zeng.ezsh.wy.entity.GridRecord;
import com.zeng.ezsh.wy.entity.Vo.RoomUserVoLin;
import com.zeng.ezsh.wy.service.ChargeRoomIdsService;
import com.zeng.ezsh.wy.utils.RetJsonUtil;
import net.sf.json.JSONArray;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("chargeRoom")
public class ChargeRoomIdsAdminAction {
	private static Logger logger = Logger.getLogger(ChargeRoomIdsAdminAction.class);
	@Resource
	private ChargeRoomIdsService chargeRoomIdsService;

	@Resource
	private ChargeItemMapper chargeItemMapper;

	@Resource
	private GridRecordMapper gridRecordMapper;

	@Resource
	private RoomMapper roomMapper;

	/**
	 * @author qwc 2017年9月20日下午4:08:52 void 添加房间收费项目
	 * @throws IOException
	 */
	@RequestMapping(value = "add", method = RequestMethod.POST)
	public void addRoomCharge(HttpServletResponse response, 
			ChargeRoomIds chargeRoomIds)
			throws IOException {
		PrintWriter out = response.getWriter();
		RetJsonUtil retJsonUtil = new RetJsonUtil();
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy/MM");
		
		
		int status = chargeRoomIdsService.insert(chargeRoomIds);
		if (status > 0) {
			retJsonUtil.setMessage("添加成功");
			retJsonUtil.setStatus("1");
		} else {
			retJsonUtil.setMessage("添加失败");
			retJsonUtil.setStatus("0");
		}

		//在对房间添加完电表之类的收费记录时，会自动为这个房间导入水电表抄表中添加一条记录
		if(chargeItemMapper.selectChargeItemsById(chargeRoomIds.getpChargeItemId()).getChargeBillingWay()==2){
			GridRecord gridRecord=new GridRecord();
			Integer pRoomId=chargeRoomIds.getpRoomId();
			RoomUserVoLin userVoLin=roomMapper.selectRoomUserByRoomId(pRoomId);

			gridRecord.setpBuildId(chargeRoomIds.getpBuildId());
			gridRecord.setpManagerId(chargeRoomIds.getpManagerId());
			gridRecord.setpRoomId(pRoomId);
			gridRecord.setpGridId(chargeRoomIds.getpChargeItemId());
			gridRecord.setRoomNum(chargeRoomIds.getRoomNum());
			gridRecord.setBuildName(userVoLin.getBuild_name());
			gridRecord.setCustomerName(userVoLin.getCustomer_name());
			gridRecord.setGridReadTime(sdf.format(new Date()));
			gridRecord.setBeginDosage(0D);
			gridRecord.setDosage(0D);
			int i=0;
			i=gridRecordMapper.insertSelective(gridRecord);
			if (i > 0) {
				retJsonUtil.setMessage("添加成功");
				retJsonUtil.setStatus("1");
			} else {
				retJsonUtil.setMessage("添加失败");
				retJsonUtil.setStatus("0");
			}
		}

		out.write(retJsonUtil.getRetJsonM());
	}

	/**
	 * @author qwc 2017年10月24日下午7:35:07
	 * @param response
	 * @param chargeRoomIds
	 * @throws IOException
	 *             void 删除房间收费项目
	 */
	@RequestMapping(value = "delete")
	public void deleteRoomCharge(HttpServletResponse response, 
			ChargeRoomIds chargeRoomIds) throws IOException {
		PrintWriter out = response.getWriter();
		RetJsonUtil retJsonUtil = new RetJsonUtil();// 构建json返回对象

		int status = chargeRoomIdsService.deleteByPrimaryKey(chargeRoomIds.getChargeRoomId());// 执行删除
		// 返回结果
		if (status > 0) {
			retJsonUtil.setStatus("1");
			retJsonUtil.setMessage("删除成功");
		} else {
			retJsonUtil.setStatus("0");
			retJsonUtil.setMessage("删除失败");
		}
		out.write(retJsonUtil.getRetJsonM());
	}

	/**
	 * @author qwc 2017年9月20日下午7:52:36 void 获取房间收费项目
	 * @throws IOException
	 */
	@RequestMapping(value = "getRCItems", method = RequestMethod.GET)
	public void getRoomChargeItems(@RequestParam(value = "roomId", required = false, defaultValue = "0") Integer roomId,
			HttpServletResponse response) throws IOException {
		PrintWriter out = response.getWriter();
		RetJsonUtil retJsonUtil = new RetJsonUtil();// 构建json返回对象

		// 查询
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("pRoomId", roomId);// 设置查询条件
		List<ChargeRoomIds> list = chargeRoomIdsService.selectChargeItemsByParam(param);// 执行查询获取

		// 返回结果
		retJsonUtil.setList(list);
		retJsonUtil.setTotal(list.size());
		out.write(retJsonUtil.getRetJsonEasyGrid());
	}

	/**
	 * @author qwc 2017年10月25日上午10:55:34
	 * @param roomId
	 * @param response
	 * @throws IOException
	 *             void 获取房间收费项目列表
	 */
	@RequestMapping(value = "getRoomItemsList")
	public void getRoomChargeItemList(
			@RequestParam(value = "roomId", required = false, defaultValue = "0") Integer roomId,
			HttpServletResponse response) throws IOException {
		PrintWriter out = response.getWriter();

		// 查询
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("pRoomId", roomId);// 设置查询条件
		List<ChargeRoomIds> list = chargeRoomIdsService.selectChargeItemsByParam(param);// 执行查询获取

		// 返回结果
		out.write(JSONArray.fromObject(list).toString());
	}
}
