package com.zeng.ezsh.wy.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javassist.expr.NewArray;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.bouncycastle.jcajce.provider.symmetric.Serpent.TKeyGen;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.zeng.ezsh.wy.entity.Customer;
import com.zeng.ezsh.wy.entity.DeviceLock;
import com.zeng.ezsh.wy.entity.ResidentialUser;
import com.zeng.ezsh.wy.entity.Room;
import com.zeng.ezsh.wy.entity.UMsIds;
import com.zeng.ezsh.wy.entity.VisitorCode;
import com.zeng.ezsh.wy.service.CustomerService;
import com.zeng.ezsh.wy.service.DeviceLockService;
import com.zeng.ezsh.wy.service.ResidentialUserService;
import com.zeng.ezsh.wy.service.RoomService;
import com.zeng.ezsh.wy.service.UMsIdsService;
import com.zeng.ezsh.wy.service.VisitorService;
import com.zeng.ezsh.wy.utils.AccessTokenUtil;
import com.zeng.ezsh.wy.utils.CheckTelephoneUtil;
import com.zeng.ezsh.wy.utils.DateUtil;
import com.zeng.ezsh.wy.utils.RetJsonUtil;

@Controller
@RequestMapping("owner")
public class OwnerMAction {
	public static Logger logger = Logger.getLogger(OwnerMAction.class);
	@Resource
	ResidentialUserService residentialUserService;
	@Resource
	UMsIdsService uMsIdsMapperService;
	@Resource
	RoomService roomService;
	@Resource
	CustomerService customerService;
	/**
	 * @author qwc 2017年8月4日下午8:22:30
	 * @param uMsIdsModel
	 * @param request
	 * @param token
	 * @param response
	 * @throws IOException
	 *             void 业主提交房屋认证
	 */
	@RequestMapping("subIdentify")
	public void addCommunity(UMsIds uMsIdsModel, HttpServletRequest request,
			@RequestParam(value = "token", required = true) String token,
			HttpServletResponse response) throws IOException {
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		RetJsonUtil retJsonUtil = new RetJsonUtil();

		// token解析
		ResidentialUser tokenModel = AccessTokenUtil
				.parserAccessTokenToModel(token);

		// 提交参数异常
		if (uMsIdsModel.getpRoomId() == null) {
			retJsonUtil.setStatus("3");
			retJsonUtil.setMessage("添加失败");
			out.write(retJsonUtil.getRetJsonM());
			return;
		}

		// 检测该房间是否已有归属
//		Map<String, Object> paramMap = new HashMap<String, Object>();
//		paramMap.put("ptRoomId", uMsIdsModel.getpRoomId());
//		Room room = roomService.findRoomByParamToFrontEnd(paramMap);
//		if (room != null && room.getPtUserId() != 0) {
//			retJsonUtil.setStatus("4");
//			retJsonUtil.setMessage("添加失败，房间已有归属");
//			out.write(retJsonUtil.getRetJsonM());
//			return;
//		}
		
		logger.info("-----------------开始审核-------------------");
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("pManagerId", uMsIdsModel.getpManagerId());
		paramMap.put("pBuildId", uMsIdsModel.getpBuildId());
		paramMap.put("pRoomId", uMsIdsModel.getpRoomId());
		Room room = roomService.getRoomByMBR(paramMap);
		String name = tokenModel.getUserName();			//姓名
		String telephone1 = tokenModel.getUserAccount();		//电话号码
//		String telephone = uMsIdsModel.getRegisterTelephone();
		logger.info("name:"+name+"-------------"+"telephone:"+telephone1);
		if(room != null && tokenModel != null){
			// 添加状态
			int addStatus = 0;
			try {
				uMsIdsModel.setpUserId(tokenModel.getUserId());// 设置用户ID
				uMsIdsModel.setIdentifyClassify(1);// 设置业主身份
				uMsIdsModel.setPrivilege(true);
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			// 检测是否重复添加同一个房屋认证 
			if (uMsIdsMapperService.checkIsOn(uMsIdsModel) == 0) {
				/**
				 * 开始审核
				 */
				Customer customer = customerService.getCustomerByMBR(paramMap);

				if (customer == null) {
					retJsonUtil.setStatus("99");
					retJsonUtil.setMessage("房间的主人找不到了？");
					out.write(retJsonUtil.getRetJsonM());
					return;
				} else {
					logger.info("CustomerName:" + customer.getCustomerName());
					logger.info("CustomerTelephone:"
							+ customer.getCustomerTelephone());
					if (customer.getCustomerName().equals(name)
							&& customer.getCustomerTelephone()
									.equals(telephone1)) {
						retJsonUtil.setStatus("1");
						retJsonUtil.setMessage("验证通过");

						// 迁入时间房间与用户关联关系对应起来
						uMsIdsModel
								.setApplyTime(DateUtil.dateToStr(new Date()));
						uMsIdsModel.setpBuildId(customer.getPtBuildId());
						uMsIdsModel.setpManagerId(customer.getpManagerId());
						uMsIdsModel.setpRoomId(customer.getpRoomId());
						uMsIdsModel.setpOwnerId(customer.getCustomerId());
						uMsIdsModel.setCheckStatus(1);// 验证通过需要将状态置1
						uMsIdsModel.setRegisterName(customer.getCustomerName());
						uMsIdsModel.setRegisterTelephone(customer.getCustomerTelephone());
						
						uMsIdsMapperService.addRoomUser(uMsIdsModel);
					} else {
						retJsonUtil.setStatus("0");
						retJsonUtil.setMessage("验证不通过，请检查输入信息和所选的楼宇是否有误。");
					}
				}
				/**
				 * 审核结束
				 */

				// 验证通过数据插入关联表
//				uMsIdsModel.setApplyTime(DateUtil.dateToStr(new Date(),
//						DateUtil.YMR_SLASH));// 申请时间
//				addStatus = uMsIdsMapperService.insert(uMsIdsModel);// 添加小区
//				if (addStatus > 0) {// 添加成功
//					logger.info("已成功插入数据");
//				} else {
//					logger.info("插入数据库失败");
//				}
			} else {
				retJsonUtil.setStatus("2");
				retJsonUtil.setMessage("不能重复添加同一个房屋认证");
			}

		}

		out.write(retJsonUtil.getRetJsonM());
	}

	/**
	 * @description 审核失败，重新提交审核
	 * 
	 * @auhtor qwc 2017年8月4日下午8:17:12
	 * @param response
	 * @param request
	 * @param token
	 * @throws IOException
	 *             void
	 */
	@RequestMapping("agSubCommunity")
	public void checkCommunity(HttpServletResponse response,
			HttpServletRequest request,
			@RequestParam(value = "token", required = true) String token)
			throws IOException {
		PrintWriter out = response.getWriter();
		RetJsonUtil retJsonUtil = new RetJsonUtil();

		// token解析
		ResidentialUser tokenModel = AccessTokenUtil
				.parserAccessTokenToModel(token);

		// 重新提交申请
		String communityId = request.getParameter("communityId");
		int agSubStatus = uMsIdsMapperService.agSubCommunityCheck(
				tokenModel.getUserId(), Integer.parseInt(communityId));
		if (agSubStatus > 0) {
			retJsonUtil.setStatus("1");
			retJsonUtil.setMessage("重新提交申请成功");
		} else {
			retJsonUtil.setStatus("0");
			retJsonUtil.setMessage("重新提交申请失败");
		}
		out.write(retJsonUtil.getRetJsonM());
	}

	/**
	 * @description 添加房下用户
	 * 
	 * @auhtor qwc 2017年9月2日下午3:35:06
	 * @param response
	 * @param request
	 * @param token
	 * @throws IOException
	 *             void
	 */
	@RequestMapping("addRoomUser")
	public void RoomUnderTheUser(HttpServletResponse response,
			HttpServletRequest request,
			@RequestParam(value = "token", required = true) String token)
			throws IOException {
		PrintWriter out = response.getWriter();
		RetJsonUtil retJsonUtil = new RetJsonUtil();

		// token解析
		ResidentialUser tokenModel = AccessTokenUtil
				.parserAccessTokenToModel(token);
		// 检测该账号下是否有小区通过审核
		if (tokenModel.getUmsIdsInfo() == null) {
			retJsonUtil.setStatus("-1");
			retJsonUtil.setMessage("此账号未有小区通过审核");
			out.write(retJsonUtil.getRetJsonM());
			return;
		}
		int checkStatus = tokenModel.getUmsIdsInfo().getCheckStatus();

		String registerName = request.getParameter("registerName");
		String registerTelephone = request.getParameter("registerTelephone");
		String identifyClassify = request.getParameter("identifyClassify");

		// 设置待添加记录 uMsIdsModel
		UMsIds uMsIdsModel = new UMsIds();
		uMsIdsModel.setpOwnerId(tokenModel.getUserId()); // 设置业主ID
		uMsIdsModel.setApplyTime(DateUtil.dateToStr(new Date(),
				DateUtil.YMR_SLASH));
		uMsIdsModel.setpManagerId(tokenModel.getUmsIdsInfo().getpManagerId()); // 设置小区ID
		uMsIdsModel.setpBuildId(tokenModel.getUmsIdsInfo().getpBuildId()); // 设置楼栋ID
		uMsIdsModel.setpRoomId(tokenModel.getUmsIdsInfo().getpRoomId()); // 设置房间ID
		uMsIdsModel.setIdentifyClassify(Integer.parseInt(identifyClassify));
		uMsIdsModel.setRegisterName(registerName);// 房下用户姓名
		uMsIdsModel.setRegisterTelephone(registerTelephone);// 房下用户联系方式
		uMsIdsModel.setPrivilege(true);
		
		if (!CheckTelephoneUtil.isMobileNum(registerTelephone)) {
			retJsonUtil.setStatus("-3");
			retJsonUtil.setMessage("手机号码不合法");
			out.write(retJsonUtil.getRetJsonM());
			return;
		}
		if (checkStatus != 1) {
			retJsonUtil.setStatus("-2");
			retJsonUtil.setMessage("业主身份未通过审核");
			out.write(retJsonUtil.getRetJsonM());
			return;
		}
		if (identifyClassify != null && Integer.parseInt(identifyClassify) == 1) {
			retJsonUtil.setStatus("-1");
			retJsonUtil.setMessage("非法添加");
			out.write(retJsonUtil.getRetJsonM());
			return;
		}
		int addStatus = uMsIdsMapperService.addRoomUser(uMsIdsModel);
		if (addStatus == 1) {
			retJsonUtil.setStatus("1");
			retJsonUtil.setMessage("添加成功");
		} else if (addStatus == 2) {
			retJsonUtil.setStatus("2");
			retJsonUtil.setMessage("不能重复添加");
		} else if (addStatus == 0) {
			retJsonUtil.setStatus("0");
			retJsonUtil.setMessage("添加失败");
		}
		out.write(retJsonUtil.getRetJsonM());
	}

	/**
	 * @description 删除房下用户登记
	 * 
	 * @auhtor qwc 2017年9月2日下午8:59:59
	 * @param response
	 * @param request
	 * @param token
	 * @throws IOException
	 *             void
	 */
	@RequestMapping("delRoomUser")
	public void delRoomUserReg(HttpServletResponse response,
			HttpServletRequest request,
			@RequestParam(value = "token", required = true) String token)
			throws IOException {
		PrintWriter out = response.getWriter();
		RetJsonUtil retJsonUtil = new RetJsonUtil();

		// token解析
		ResidentialUser tokenModel = AccessTokenUtil
				.parserAccessTokenToModel(token);
		// 检测该账号下是否有小区通过审核
		if (tokenModel.getUmsIdsInfo() == null) {
			retJsonUtil.setStatus("-1");
			retJsonUtil.setMessage("此账号未有小区通过审核");
			out.write(retJsonUtil.getRetJsonM());
			return;
		}
		// 取出身份类型
		int identifyClassify = tokenModel.getUmsIdsInfo().getIdentifyClassify();

		if (identifyClassify != 1) {
			retJsonUtil.setStatus("-1");
			retJsonUtil.setMessage("非业主，没有权限");
			out.write(retJsonUtil.getRetJsonM());
			return;
		}

		// 当身份为业主时进行以下操作
		int delStatus = 0;
		String uMsId = request.getParameter("uMsId");
		Map<String, Object> param = new HashMap<String, Object>();
		if (identifyClassify == 1) {
			param.put("uMsId", uMsId);
			param.put("ownerId", tokenModel.getUserId());
			delStatus = uMsIdsMapperService.deleteByParam(param);
		}
		if (delStatus == 1) {
			retJsonUtil.setStatus("1");
			retJsonUtil.setMessage("删除成功");
		} else if (delStatus == 0) {
			retJsonUtil.setStatus("0");
			retJsonUtil.setMessage("删除失败");
		}
		out.write(retJsonUtil.getRetJsonM());
	}

	/**
	 * @description 房下用户一键开门权限
	 * 
	 * @auhtor qwc 2017年9月2日下午8:36:12
	 * @param response
	 * @param request
	 * @param token
	 * @throws IOException
	 *             void
	 */
	@RequestMapping("privilege")
	public void addRoomUserPrivilege(HttpServletResponse response,
			HttpServletRequest request,
			@RequestParam(value = "token", required = true) String token)
			throws IOException {
		UMsIds uMsIdsModel = new UMsIds();
		PrintWriter out = response.getWriter();
		RetJsonUtil retJsonUtil = new RetJsonUtil();

		// token解析
		ResidentialUser tokenModel = AccessTokenUtil
				.parserAccessTokenToModel(token);
		// 检测该账号下是否有小区通过审核
		if (tokenModel.getUmsIdsInfo() == null) {
			retJsonUtil.setStatus("-1");
			retJsonUtil.setMessage("此账号未有小区通过审核");
			out.write(retJsonUtil.getRetJsonM());
			return;
		}
		// 取出身份类型
		int identifyClassify = tokenModel.getUmsIdsInfo().getIdentifyClassify();

		int upSatatus = 0;
		String method = request.getParameter("method"); // 授权类型: on or off
		if (identifyClassify != 1) {
			retJsonUtil.setStatus("0");
			retJsonUtil.setMessage("非业主身份,没有权限");
		}
		if (identifyClassify == 1) {
			if (method != null && method.equals("on")) {
				uMsIdsModel.setPrivilege(true); // 设置拥有开门权限
				uMsIdsModel.setpOwnerId(tokenModel.getUserId());
//				uMsIdsModel.setuMsId(tokenModel.getUmsIdsInfo().getuMsId());
				uMsIdsModel.setuMsId(Integer.parseInt(request.getParameter("uMsId")));
				upSatatus = uMsIdsMapperService.updateUMsSelective(uMsIdsModel);
			} else if (method != null && method.equals("off")) {
				uMsIdsModel.setPrivilege(false);
				uMsIdsModel.setpOwnerId(tokenModel.getUserId());
//				uMsIdsModel.setuMsId(tokenModel.getUmsIdsInfo().getuMsId());
				uMsIdsModel.setuMsId(Integer.parseInt(request.getParameter("uMsId")));
				upSatatus = uMsIdsMapperService.updateUMsSelective(uMsIdsModel);
			}
			if (upSatatus > 0) {
				retJsonUtil.setStatus("1");
				retJsonUtil.setMessage("成功授权");
			} else {
				retJsonUtil.setStatus("0");
				retJsonUtil.setMessage("授权失败");
			}
		}
		out.write(retJsonUtil.getRetJsonM());
	}

	/**
	 * @description 获取房下用户列表
	 * 
	 * @auhtor qwc 2017年9月2日下午5:28:16
	 * @param response
	 * @param request
	 * @param token
	 * @throws IOException
	 *             void
	 */
	@RequestMapping("gtRoomUsers")
	public void getRoomUserList(HttpServletResponse response,
			HttpServletRequest request,
			@RequestParam(value = "token", required = true) String token)
			throws IOException {
		PrintWriter out = response.getWriter();
		RetJsonUtil retJsonUtil = new RetJsonUtil();
		// token解析
		ResidentialUser tokenModel = AccessTokenUtil
				.parserAccessTokenToModel(token);
		// 检测该账号下是否有小区通过审核
		if (tokenModel.getUmsIdsInfo() == null) {
			retJsonUtil.setStatus("-1");
			retJsonUtil.setMessage("此账号未有小区通过审核");
			out.write(retJsonUtil.getRetJsonM());
			return;
		}

		int roomId = Integer.parseInt(request.getParameter("roomId"));
		// 取出身份类型
		int identifyClassify = tokenModel.getUmsIdsInfo().getIdentifyClassify();
		if (identifyClassify != 1) {
			retJsonUtil.setStatus("0");
			retJsonUtil.setMessage("获取失败，非业主身份");
			out.write(retJsonUtil.getRetJsonM());
			return;
		}

		// 设置查询条件
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("userId", tokenModel.getUserId()); // 业主userID
		param.put("pRoomId", roomId);
		// 执行房下用户列表查询
		List<UMsIds> uMsIdsList = uMsIdsMapperService.selectUMsList(param);

		// 封装返回结果
		retJsonUtil.setStatus("1");
		retJsonUtil.setMessage("获取成功");
		retJsonUtil.setList(uMsIdsList);
		out.write(retJsonUtil.getRetJsonL());
	}

	/**
	 * @description 业主获取自己的房间集合
	 * 
	 * @auhtor qwc 2017年10月20日下午3:42:42
	 * @param response
	 * @param request
	 * @param token
	 * @throws IOException
	 *             void
	 */
	@RequestMapping("gtUserRooms")
	public void getUserRoomList(HttpServletResponse response,
			HttpServletRequest request,
			@RequestParam(value = "token", required = true) String token)
			throws IOException {
		PrintWriter out = response.getWriter();
		RetJsonUtil retJsonUtil = new RetJsonUtil();

		// token解析
		ResidentialUser tokenModel = AccessTokenUtil
				.parserAccessTokenToModel(token);
		// 判断账号是否小区通过审核
		if (tokenModel.getUmsIdsInfo() == null) {
			retJsonUtil.setStatus("-1");
			retJsonUtil.setMessage("此账号未有小区通过审核");
			out.write(retJsonUtil.getRetJsonM());
			return;
		}

		/*// 判断身份类型
		int identifyClassify = tokenModel.getUmsIdsInfo().getIdentifyClassify();
		if (identifyClassify != 1) {
			retJsonUtil.setStatus("0");
			retJsonUtil.setMessage("获取失败，非业主身份");
			out.write(retJsonUtil.getRetJsonM());
			return;
		}*/

		// 设置查询条件
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("pUserId", tokenModel.getUserId());
		/*param.put("pManagerId", tokenModel.getUmsIdsInfo().getpManagerId());*/
		/*param.put("pUserId", 48);
		//param.put("pManagerId",);*/

		// 执行自己所拥有的房间列表查询
		List<UMsIds> uMsIdsList = uMsIdsMapperService.selectUMsRoomList(param);

		// 封装返回json
		retJsonUtil.setStatus("1");
		retJsonUtil.setList(uMsIdsList);
		retJsonUtil.setMessage("获取成功");
		out.write(retJsonUtil.getRetJsonL());
	}
}
