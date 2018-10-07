package com.zeng.ezsh.wy.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.zeng.ezsh.wy.entity.GoodsReceiptAddress;
import com.zeng.ezsh.wy.entity.ResidentialUser;
import com.zeng.ezsh.wy.service.GoodsReceiptAddressService;
import com.zeng.ezsh.wy.utils.AccessTokenUtil;
/**
 * 
 * @author quanweicong
 * (弃用)
 */
@Controller
@RequestMapping("address")
public class GoodsReceiptAddressAction {
	@Resource
	GoodsReceiptAddressService goodsReceiptAddressService;

	/**
	 * @author qwc 2017年8月8日下午5:17:16
	 * @param request
	 * @param response
	 * @throws IOException
	 * void 添加收货地址(弃用)
	 */
	@RequestMapping("addAddr")
	public void addAddress(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		PrintWriter out = response.getWriter();
		JSONObject retJson = new JSONObject();
		Map<String, Object> retMap = new HashMap<String, Object>();

		String addressContent = request.getParameter("addressContent");
		String linkPhone = request.getParameter("linkPhone");
		String linkMan = request.getParameter("linkMan");

		// 保存解密后的token信息
		String accessToken = request.getParameter("token");// 获取登录token
		ResidentialUser residentialUser = AccessTokenUtil
				.parserAccessTokenToModel(accessToken);
		
		GoodsReceiptAddress goodsReceiptAddress = new GoodsReceiptAddress();
		goodsReceiptAddress.setLinkMan(linkMan);
		goodsReceiptAddress.setLinkPhone(linkPhone);
		goodsReceiptAddress.setpUserId(residentialUser.getUserId());
		goodsReceiptAddress.setpManagerId(residentialUser.getUmsIdsInfo().getpManagerId());
		goodsReceiptAddress.setReceiptAddress(addressContent);
		
		int addStatus = goodsReceiptAddressService
				.addGoodsReceiptAddress(goodsReceiptAddress);
		
		if (addStatus > 0) {
			retJson.put("status", 1);
			retJson.put("data", retMap);
			retJson.put("message", "添加成功！");
		} else {
			retJson.put("status", 0);
			retJson.put("data", retMap);
			retJson.put("message", "添加失败！");
		}
		out.write(JSONObject.fromObject(retJson).toString());
	}
    
	// (弃用)
	@RequestMapping("alertAddr")
	public void alertAddress(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		PrintWriter out = response.getWriter();
		JSONObject retJson = new JSONObject();
		Map<String, Object> retMap = new HashMap<String, Object>();
		Map<String, Object> tokenMap = new HashMap<String, Object>();
		String addressContent = request.getParameter("addressContent");
		String linkMan = request.getParameter("linkMan");
		String linkPhone = request.getParameter("linkPhone");
		String receiptAddressId = request.getParameter("receiptAddressId");
		String token = request.getParameter("token");
		GoodsReceiptAddress goodsReceiptAddress = new GoodsReceiptAddress();
		goodsReceiptAddress.setReceiptAddress(addressContent);
		tokenMap = AccessTokenUtil.parserAccessTokenToMap(token);
		int userId = (int) tokenMap.get("userId");
		int managerId = (int) tokenMap.get("managerId");
		goodsReceiptAddress.setpUserId(userId);
		goodsReceiptAddress.setpManagerId(managerId);
		goodsReceiptAddress.setLinkMan(linkMan);
		goodsReceiptAddress.setLinkPhone(linkPhone);
		goodsReceiptAddress.setReceiptAddressId(Integer
				.parseInt(receiptAddressId));
		int addStatus = goodsReceiptAddressService
				.updateGoodsReceiptAddress(goodsReceiptAddress);
		if (addStatus > 0) {
			retJson.put("status", 1);
			retJson.put("data", retMap);
			retJson.put("message", "修改成功！");
		} else {
			retJson.put("status", 0);
			retJson.put("data", retMap);
			retJson.put("message", "修改失败！");
		}
		out.write(JSONObject.fromObject(retJson).toString());
	}

	/**
	 * @author qwc 2017年8月8日下午5:53:19
	 * @param request
	 * @param response
	 * @throws IOException
	 * void 获取收货地址列表(弃用)
	 */
	@RequestMapping("getAddr")
	public void gtAddressList(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		PrintWriter out = response.getWriter();
		JSONObject retJson = new JSONObject();
		Map<String, Object> retMap = new HashMap<String, Object>();
		Map<String, Object> tokenMap = new HashMap<String, Object>();
		GoodsReceiptAddress record = new GoodsReceiptAddress();
		List<GoodsReceiptAddress> goodsReceiptAddressList = new ArrayList<GoodsReceiptAddress>();
		String token = request.getParameter("token");
		GoodsReceiptAddress goodsReceiptAddress = new GoodsReceiptAddress();
		tokenMap = AccessTokenUtil.parserAccessTokenToMap(token);
		int userId = (int) tokenMap.get("userId");
		int manageId = (int) tokenMap.get("managerId");
		record.setpUserId(userId);
		record.setpManagerId(manageId);
		goodsReceiptAddressList = goodsReceiptAddressService
				.selectGoodsReceiptAddressList(record);
		retJson.put("status", 1);
		retJson.put("data", goodsReceiptAddressList);
		retJson.put("message", "获取成功");
		out.write(JSONObject.fromObject(retJson).toString());
	}

}
