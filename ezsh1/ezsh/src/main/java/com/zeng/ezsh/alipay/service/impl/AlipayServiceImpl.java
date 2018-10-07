package com.zeng.ezsh.alipay.service.impl;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Map;
import java.util.logging.Logger;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.domain.AlipayTradeQueryModel;
import com.alipay.api.request.AlipayTradeAppPayRequest;
import com.alipay.api.request.AlipayTradeCloseRequest;
import com.alipay.api.request.AlipayTradePayRequest;
import com.alipay.api.request.AlipayTradeQueryRequest;
import com.alipay.api.response.AlipayTradeCloseResponse;
import com.alipay.api.response.AlipayTradePayResponse;
import com.alipay.api.response.AlipayTradeQueryResponse;
import com.zeng.ezsh.alipay.config.AlipayConfig;
import com.zeng.ezsh.alipay.service.AlipayService;
import com.zeng.ezsh.alipay.utils.AliPayCommonUtil;
import com.zeng.ezsh.alipay.utils.AlipayTradeAppOrderInfoUtil;
import com.zeng.ezsh.alipay.utils.AlipayTradeCloseReqParam;
import com.zeng.ezsh.alipay.utils.BizContent;
import com.zeng.ezsh.wy.dao.BerthOrderMapper;
import com.zeng.ezsh.wy.dao.PlateManagementMapper;
import com.zeng.ezsh.wy.entity.BenefitRecord;
import com.zeng.ezsh.wy.entity.BerthOrder;
import com.zeng.ezsh.wy.entity.ChargeInfo;
import com.zeng.ezsh.wy.entity.GoodsOrder;
import com.zeng.ezsh.wy.entity.ParkRecord;
import com.zeng.ezsh.wy.entity.UserTeacherFee;
import com.zeng.ezsh.wy.utils.SerializeUtil;
@Service
public class AlipayServiceImpl implements AlipayService {
	private Logger logger = Logger.getLogger("AlipayServiceImpl");
	
	@Resource
	PlateManagementMapper plateManagementMapper;
	@Resource
	BerthOrderMapper berthOrderMapper;
	// 设置app_id、charset、sign_type
	AlipayClient alipayClient = new DefaultAlipayClient(
			AlipayConfig.AliPayGateWay, AlipayConfig.APPID,
			AlipayConfig.AliPayPrivateKey, "json",
			AlipayConfig.AliPaySignCharset, AlipayConfig.AlipayPublicKey,
			"RSA2");
	// 实例化具体API对应的request类,类名称和接口名称对应,当前调用接口名称：alipay.trade.app.pay
	AlipayTradeAppPayRequest appPayRequest = new AlipayTradeAppPayRequest();
	
	AlipayTradePayRequest request = new AlipayTradePayRequest();
	// SDK已经封装掉了公共参数，这里只需要传入业务参数。
	// 以下方法为sdk的model入参方式(model和biz_content同时存在的情况下取biz_content)。
	/**
	 * @description 商品支付(返回orderInfo供移动端请求唤起支付宝支付)
	 */
	@Override
	public String goodsPayment(GoodsOrder goodsOrder, String goodsName) {
		AlipayTradeAppOrderInfoUtil alipayTradeAppOrderInfoUtil = new AlipayTradeAppOrderInfoUtil();
		BizContent bizContent = new BizContent();
		bizContent.setBody("ezsh"); // 对一笔交易的具体描述信息，选填
		bizContent.setOut_trade_no(goodsOrder.getOrderSerialNum());// 商户网站唯一订单号，必填
		bizContent.setTimeout_express("30m");// 订单半个小时未交易将导致交易关闭，选
		bizContent.setTotal_amount("0.01");// 订单总金额，单位为元，精确到小数点后两位，取值范围，必填
		bizContent.setSubject("milks"); // 商品的标题/交易标题/订单标题/订单关键字等，必填
		alipayTradeAppOrderInfoUtil
				.setNotify_url(AlipayConfig.VERIFY_ORDER_ASYN_URL);
		String reqContent = alipayTradeAppOrderInfoUtil
				.gtAlipayTradeAppPayReqOrderInfo(bizContent);
		String orderInfo = AliPayCommonUtil.gtOrderInfo(reqContent);
		logger.info("AliPay orderString>>" + orderInfo);
		try {
			logger.info("请求参数解码>>" + URLDecoder.decode(orderInfo, "utf-8"));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return orderInfo;
	}

	/**
	 * @description 商品支付交易查询
	 */
	@Override
	public boolean goodsPaymentTradeQuery(GoodsOrder goodsOrder) {
		AlipayTradeQueryRequest request = new AlipayTradeQueryRequest();
		AlipayTradeQueryModel model = new AlipayTradeQueryModel();
		model.setOutTradeNo(goodsOrder.getTransactionNum());
		try {
			// 通过alipayClient调用API，获得对应的response类
			AlipayTradeQueryResponse response = alipayClient.execute(request);
			if (response.getBody() == null) {
				return false;
			} else {
				return true;
			}
		} catch (AlipayApiException e) {
			e.printStackTrace();
		}
		return false;
	}

	/**
	 * @description 生成物业费支付支付订单信息(返回orderInfo供移动端请求唤起支付宝支付)
	 */
	@Override
	public String PropertyCosts(ChargeInfo chargeInfo, String totalPrice,
			Map<String, Object> additionMap) {
		AlipayTradeAppOrderInfoUtil alipayTradeAppOrderInfoUtil = new AlipayTradeAppOrderInfoUtil();
		BizContent bizContent = new BizContent();
		bizContent.setBody("物业费"); // 对一笔交易的具体描述信息，选填
		bizContent.setOut_trade_no(additionMap.get("outTradeNo").toString());// 商户网站唯一订单号，必填
		bizContent.setTimeout_express("30m");// 订单半个小时未交易将导致交易关闭，选
		bizContent.setTotal_amount("0.01");// 订单总金额，单位为元，精确到小数点后两位，取值范围，必填
		bizContent.setSubject("e众智慧社区"); // 商品的标题/交易标题/订单标题/订单关键字等，必填
		alipayTradeAppOrderInfoUtil
				.setNotify_url(AlipayConfig.VERIFY_PROPERTY_PAY_ASYN_URL);
		String reqContent = alipayTradeAppOrderInfoUtil
				.gtAlipayTradeAppPayReqOrderInfo(bizContent);
		String orderInfo = AliPayCommonUtil.gtOrderInfo(reqContent);
		logger.info("AliPay orderString>>" + orderInfo);
		try {
			logger.info("请求参数解码>>" + URLDecoder.decode(orderInfo, "utf-8"));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return orderInfo;
	}

	/**
	 * @description 家长缴纳家教费用(返回orderInfo供移动端请求唤起支付宝支付)
	 */
	@Override
	public String teacherCosts(UserTeacherFee record, String totalPrice,
			Map<String, Object> additionMap) {
		AlipayTradeAppOrderInfoUtil alipayTradeAppOrderInfoUtil = new AlipayTradeAppOrderInfoUtil();
		BizContent bizContent = new BizContent();
		bizContent.setBody("家教平台使用费"); // 对一笔交易的具体描述信息，选填
		bizContent.setOut_trade_no(record.getOutTradeNo());// 商户网站唯一订单号，必填
		bizContent.setTimeout_express("30m");// 订单半个小时未交易将导致交易关闭，选
		bizContent.setTotal_amount("0.01");// 订单总金额，单位为元，精确到小数点后两位，取值范围，必填
		bizContent.setSubject("e众智慧社区"); // 商品的标题/交易标题/订单标题/订单关键字等，必填
		alipayTradeAppOrderInfoUtil
				.setNotify_url(AlipayConfig.VERIFY_TEACHER_PAY_ASYN_URL);
		String reqContent = alipayTradeAppOrderInfoUtil
				.gtAlipayTradeAppPayReqOrderInfo(bizContent);
		String orderInfo = AliPayCommonUtil.gtOrderInfo(reqContent);
		logger.info("AliPay orderString>>" + orderInfo);
		try {
			logger.info("请求参数解码>>" + URLDecoder.decode(orderInfo, "utf-8"));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return orderInfo;
	}

	/**
	 * @description 公益基金支付
	 */
	@Override
	public String benefitCosts(BenefitRecord record, String totalPrice,
			Map<String, Object> additionMap) {
		AlipayTradeAppOrderInfoUtil alipayTradeAppOrderInfoUtil = new AlipayTradeAppOrderInfoUtil();
		BizContent bizContent = new BizContent();
		bizContent.setBody("公益基金费用"); // 对一笔交易的具体描述信息，选填
		bizContent.setOut_trade_no(record.getOutTradeNo());// 商户网站唯一订单号，必填
		bizContent.setTimeout_express("30m");// 订单半个小时未交易将导致交易关闭，选
		bizContent.setTotal_amount("0.01");// 订单总金额，单位为元，精确到小数点后两位，取值范围，必填
		bizContent.setSubject("e众智慧社区"); // 商品的标题/交易标题/订单标题/订单关键字等，必填
		alipayTradeAppOrderInfoUtil
				.setNotify_url(AlipayConfig.VERIFY_BENEFIT_PAY_ASYN_URL);
		String reqContent = alipayTradeAppOrderInfoUtil
				.gtAlipayTradeAppPayReqOrderInfo(bizContent);
		String orderInfo = AliPayCommonUtil.gtOrderInfo(reqContent);
		logger.info("AliPay orderString>>" + orderInfo);
		try {
			logger.info("请求参数解码>>" + URLDecoder.decode(orderInfo, "utf-8"));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return orderInfo;
	}

	/**
	 * @description 关闭支付宝交易订单
	 */
	@Override
	public boolean closeAliPayOrder(
			AlipayTradeCloseReqParam alipayTradeReqParam) {
		AlipayClient alipayClient = new DefaultAlipayClient(
				AlipayConfig.AliPayGateWay, AlipayConfig.APPID,
				AlipayConfig.AliPayPrivateKey, "json",
				AlipayConfig.AliPaySignCharset, AlipayConfig.AlipayPublicKey,
				"RSA2");
		AlipayTradeCloseRequest request = new AlipayTradeCloseRequest();
		request.setBizContent(alipayTradeReqParam.gtString());
		AlipayTradeCloseResponse response;
		try {
			response = alipayClient.execute(request);
			if (response.isSuccess()) {
				System.out.println("操作成功");
				return true;
			} else {
				System.out.println("操作失败");
				return false;
			}
		} catch (AlipayApiException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	/**
	 * 停车费支付(APP端)
	 */
	@Override
	public String ParkingCharges(ParkRecord parkRecord) {
		AlipayTradeAppOrderInfoUtil alipayTradeAppOrderInfoUtil = new AlipayTradeAppOrderInfoUtil();
		BizContent bizContent = new BizContent();
		
		//生成订单号并保存进数据库
		String orderNum = SerializeUtil.generateUUID();
		parkRecord.setOrderNum(orderNum);
//		System.err.println(parkRecord.getPayWay());
		if(parkRecord != null){
			plateManagementMapper.updateParkRecord(parkRecord);
			//获取需要缴纳的金额
			String money = String.valueOf(parkRecord.getAdvancePay());//需要缴纳的金额
			//封装参数
			bizContent.setBody("停车费用"); // 对一笔交易的具体描述信息，选填
			bizContent.setOut_trade_no(orderNum);// 商户网站唯一订单号，必填
			bizContent.setTimeout_express("30m");// 订单半个小时未交易将导致交易关闭，选
			bizContent.setTotal_amount("0.01");// 订单总金额，单位为元，精确到小数点后两位，取值范围，必填//money
			bizContent.setSubject("e众智慧社区"); // 商品的标题/交易标题/订单标题/订单关键字等，必填
			alipayTradeAppOrderInfoUtil
					.setNotify_url(AlipayConfig.VERIFY_PARKING_PAY_ASYN_URL);
			String reqContent = alipayTradeAppOrderInfoUtil
					.gtAlipayTradeAppPayReqOrderInfo(bizContent);
			String orderInfo = AliPayCommonUtil.gtOrderInfo(reqContent);
			logger.info("AliPay orderString>>" + orderInfo);
			try {
				logger.info("请求参数解码>>" + URLDecoder.decode(orderInfo, "utf-8"));
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			} 
			return orderInfo;
		}
		return "参数传入异常";
	}

	/**
	 * 停车费支付(Wap端)
	 */
	@Override
	public String ParkingChargesByWeb(ParkRecord parkRecord) {
		AlipayTradeAppOrderInfoUtil alipayTradeAppOrderInfoUtil = new AlipayTradeAppOrderInfoUtil();
		BizContent bizContent = new BizContent();
		final String RETURN_URL = "https://szykcz.com/base/goURL/platemanage/paysuccess";
		
		//生成订单号并保存进数据库
		String orderNum = SerializeUtil.generateUUID();
		parkRecord.setOrderNum(orderNum);
//		System.err.println(parkRecord.getPayWay());
		if(parkRecord != null){
			plateManagementMapper.updateParkRecord(parkRecord);
			//获取需要缴纳的金额
			String money = String.valueOf(parkRecord.getAdvancePay());//需要缴纳的金额
			//封装参数
			bizContent.setBody("停车费用"); // 对一笔交易的具体描述信息，选填
			bizContent.setOut_trade_no(orderNum);// 商户网站唯一订单号，必填
			bizContent.setTimeout_express("30m");// 订单半个小时未交易将导致交易关闭，选
			bizContent.setTotal_amount("0.01");// 订单总金额，单位为元，精确到小数点后两位，取值范围，必填//money
			bizContent.setSubject("e众智慧社区"); // 商品的标题/交易标题/订单标题/订单关键字等，必填
			bizContent.setSeller_id(AlipayConfig.SellerId);
			
			alipayTradeAppOrderInfoUtil
					.setReturn_url(RETURN_URL);
			alipayTradeAppOrderInfoUtil
					.setNotify_url(AlipayConfig.VERIFY_PARKING_PAY_ASYN_URL);
			String reqContent = alipayTradeAppOrderInfoUtil
					.gtAlipayTradeWapPayReqOrderInfo(bizContent);
			String orderInfo = AliPayCommonUtil.gtOrderInfo(reqContent);
			logger.info("AliPay orderString>>" + orderInfo);
			try {
				logger.info("请求参数解码>>" + URLDecoder.decode(orderInfo, "utf-8"));
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
			return orderInfo;
		}
		return "参数传入异常";
	}
	//提交车位管理费（续费）
	public String BerthCosts(Map<String, Object> map){
		AlipayTradeAppOrderInfoUtil alipayTradeAppOrderInfoUtil = new AlipayTradeAppOrderInfoUtil();
		BizContent bizContent = new BizContent();
		BerthOrder berthOrder = (BerthOrder) map.get("berthOrder"); 
		//生成订单号并保存进数据库
		String orderNum = SerializeUtil.generateUUID();
		berthOrder.setOutTradeNo(orderNum);
		
		if(berthOrder != null){
			berthOrderMapper.updateBerthOrder(berthOrder);
			//获取需要缴纳的金额
			String money = String.valueOf(berthOrder.getMoney());//需要缴纳的金额
			//封装参数
			bizContent.setBody("车位管理费用"); // 对一笔交易的具体描述信息，选填
			bizContent.setOut_trade_no(orderNum);// 商户网站唯一订单号，必填
			bizContent.setTimeout_express("30m");// 订单半个小时未交易将导致交易关闭，选
			bizContent.setTotal_amount("0.01");// 订单总金额，单位为元，精确到小数点后两位，取值范围，必填//money
			bizContent.setSubject("e众智慧社区"); // 商品的标题/交易标题/订单标题/订单关键字等，必填
			alipayTradeAppOrderInfoUtil
					.setNotify_url(AlipayConfig.VERIFY_BERTH_PAY_ASYN_URL);
			String reqContent = alipayTradeAppOrderInfoUtil
					.gtAlipayTradeAppPayReqOrderInfo(bizContent);
			String orderInfo = AliPayCommonUtil.gtOrderInfo(reqContent);
			logger.info("AliPay orderString>>" + orderInfo);
			try {
				logger.info("请求参数解码>>" + URLDecoder.decode(orderInfo, "utf-8"));
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
			return orderInfo;
		}
		return "参数传入异常";
	}

	@Override
	public String ParkingChargesByBarCode(Map<String, Object> addtionmap) {
		//参数封装
		AlipayTradeAppOrderInfoUtil alipayTradeAppOrderInfoUtil = new AlipayTradeAppOrderInfoUtil();
		BizContent bizContent = new BizContent();
		
		String orderNum = SerializeUtil.generateUUID();//生成商家订单号
		ParkRecord parkRecord = new ParkRecord();
		parkRecord = (ParkRecord) addtionmap.get("parkRecord");
		String auth_code = (String) addtionmap.get("payCode");
		parkRecord.setOrderNum(orderNum);
		plateManagementMapper.updateParkRecord(parkRecord);//更新订单号
		
		bizContent.setBody("扫码支付停车费用");
		bizContent.setSubject("e众智慧社区");
		bizContent.setOut_trade_no(orderNum);
		bizContent.setProduct_code("FACE_TO_FACE_PAYMENT");
		bizContent.setTimeout_express("15m");
		bizContent.setTotal_amount("0.01");
		bizContent.setScene("bar_code");
		bizContent.setAuth_code(auth_code);
		bizContent.setSeller_id(AlipayConfig.SellerId);
		
		alipayTradeAppOrderInfoUtil.setNotify_url(AlipayConfig.VERIFY_PARKING_PAY_ASYN_URL);
		String reqString = alipayTradeAppOrderInfoUtil.gtAlipayTradeAppPayReqOrderInfo2(bizContent);
		String encryptOrderInfo = AliPayCommonUtil.gtOrderInfo(reqString);
		logger.info(encryptOrderInfo);
		String orderInfo;
		try {
			orderInfo = URLDecoder.decode(encryptOrderInfo, "utf-8");
			logger.info(orderInfo);
			request.setBizContent(orderInfo);
			AlipayTradePayResponse response = alipayClient.execute(request);
			if(response.isSuccess()){
				return "Success";
			} else {
				return "Fail";
			}
			
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			return "Fail";
		} catch (AlipayApiException e) {
			e.printStackTrace();
			return "Fail";
		}
	}


}
