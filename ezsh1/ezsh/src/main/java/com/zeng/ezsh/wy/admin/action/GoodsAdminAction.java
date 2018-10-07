package com.zeng.ezsh.wy.admin.action;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zeng.ezsh.wy.entity.Goods;
import com.zeng.ezsh.wy.entity.GoodsDiscounts;
import com.zeng.ezsh.wy.entity.GoodsDistribution;
import com.zeng.ezsh.wy.entity.GoodsInfo;
import com.zeng.ezsh.wy.service.GoodsDiscountsService;
import com.zeng.ezsh.wy.service.GoodsDistributionService;
import com.zeng.ezsh.wy.service.GoodsInfoService;
import com.zeng.ezsh.wy.service.GoodsService;
import com.zeng.ezsh.wy.utils.AccessTokenUtil;
import com.zeng.ezsh.wy.utils.DateUtil;
import com.zeng.ezsh.wy.utils.RetJsonUtil;

@Controller
@RequestMapping("goodsAd")
public class GoodsAdminAction {
	private Logger logger = Logger.getLogger("GoodsAdminAction");
	@Resource
	GoodsService goodsService;
	@Resource
	GoodsInfoService goodsInfoService;
	@Resource
	GoodsDiscountsService goodsDiscountsService;
	@Resource
	GoodsDistributionService goodsDistributionService;

	/**
	 * @author qwc 2017年8月22日下午8:57:56
	 * @param request
	 * @param response
	 * @param filesSlideShow
	 * @param filesImageText
	 * @param filesColorImage
	 * @param fileBackgroundImage
	 * @throws ParseException void 后台添加商品
	 * @throws IOException
	 */
	@RequestMapping("addCommodity")
	public void addGoods(GoodsDiscounts goodsDiscounts,
			GoodsInfo goodsInfoModel, HttpServletRequest request,
			HttpServletResponse response,
			@RequestParam(value = "goodsSlideShowFiles", required = false) MultipartFile[] filesSlideShow,
			@RequestParam(value = "checkedAmount", required = false) int checkedAmount,
			@RequestParam(value = "goodsColorImage1", required = false) MultipartFile filesColorImage1,
			@RequestParam(value = "goodsColorImage2", required = false) MultipartFile filesColorImage2,
			@RequestParam(value = "goodsColorImage3", required = false) MultipartFile filesColorImage3,
			@RequestParam(value = "goodsColorImage4", required = false) MultipartFile filesColorImage4,
			@RequestParam(value = "goodsColorImage5", required = false) MultipartFile filesColorImage5,
			@RequestParam(value = "fileBackgroundImage", required = false) MultipartFile fileBackgroundImage)
			throws ParseException, IOException {
		PrintWriter out = response.getWriter();
		Map<String, Object> retMap = new HashMap<String, Object>();
		JSONObject retObject = new JSONObject();
		List<Goods> listGoods = new ArrayList<Goods>();
		MultipartHttpServletRequest Mrequest = (MultipartHttpServletRequest) request;
		String goodsSlideShowUrl = null;// 轮播图
		BigDecimal minPrice = null;// 最低价
		BigDecimal maxPrice = null;// 最高价

		String[] oneValue = Mrequest.getParameterValues("sectionOneValue");// 类别属性一的值集合
		String[] twoValue = Mrequest.getParameterValues("sectionTwoValue");// 类别属性二的值集合
		String[] threeValue = Mrequest.getParameterValues("sectionThreeValue");// 类别属性三的值集合
		/*
		 * String[] fourValue=Mrequest.getParameterValues("fourValue");//
		 * 类别属性四的值集合，暂未使用此字段，待扩展 String[]
		 * fiveValue=Mrequest.getParameterValues("fiveValue");//
		 * 类别属性五的值集合，暂未使用此字段，待扩展
		 */
		MultipartFile[] filesColorImage = new MultipartFile[5];
		filesColorImage[0] = filesColorImage1;
		filesColorImage[1] = filesColorImage2;
		filesColorImage[2] = filesColorImage3;
		filesColorImage[3] = filesColorImage4;
		filesColorImage[4] = filesColorImage5;
		String[] price = Mrequest.getParameterValues("price");// 价格
		String[] amount = Mrequest.getParameterValues("amount");// 数量
		int priceLength = price.length;
		int mulriple = 0;
		String[] goodColorImageArraysBypriceLength = new String[priceLength];// 颜色图片
		// System.out.println("oneValue的大小"+oneValue.length);
		// System.out.println("filesColorImage的大小"+filesColorImage.length);
		// 计算出最低价和最高价
		if (price.length > 0) {
			String minPriceString = price[0];
			String maxPriceString = price[0];
			for (int m = 0; m < price.length; m++) {
				if (price[m].compareTo(minPriceString) < 0) {
					minPriceString = price[m];
				}
			}
			for (int m = 0; m < price.length; m++) {
				if (price[m].compareTo(maxPriceString) > 0) {
					maxPriceString = price[m];
				}
			}
			minPrice = new BigDecimal(minPriceString);
			maxPrice = new BigDecimal(maxPriceString);
		}
		goodsInfoModel.setGoodsMinPrice(minPrice);// 最低价
		goodsInfoModel.setGoodsMaxPrice(maxPrice);// 最高价
		goodsInfoModel.setAddtime(
				DateUtil.dateToStr(new Date(), DateUtil.DATE_TIME_LINE));// 添加时间

		// 添加商品基本信息
		goodsInfoService.addGoodsInfo(goodsInfoModel);
		int goodsInfoId = goodsInfoModel.getGoodsInfoId();
		logger.info("goodsInfoId>>" + goodsInfoId);

		if (goodsInfoId > 0) {// 成功添加商品公共基本信息
			if (fileBackgroundImage != null && !fileBackgroundImage.isEmpty()) {// 添加优惠商品的附属资料
				String showBackgroundImage = saveBackgroundImgs(
						fileBackgroundImage, "discountsBgImage", 1,
						goodsInfoId);
				goodsDiscounts.setpGoodsInfoId(goodsInfoId);
				goodsDiscounts.setShowBackgroundImage(showBackgroundImage);
				goodsDiscounts.setShowSectionOneValue(
						goodsDiscounts.getShowSectionOneValue());
				goodsDiscounts.setShowSectionTwoValue(
						goodsDiscounts.getShowSectionTwoValue());
				goodsDiscounts.setShowSectionThreeValue(
						goodsDiscounts.getShowSectionThreeValue());
				int addDiscountsStatus = goodsDiscountsService
						.insertDiscountInfoByGoodsInfoId(goodsDiscounts);
				if (addDiscountsStatus > 0) {
					logger.info("附加商品添加成功");
				}
			}

			GoodsInfo upGoodsInfoModel = new GoodsInfo();
			if (filesSlideShow != null && filesSlideShow.length != 0) {
				goodsSlideShowUrl = fileUpload(filesSlideShow, "goodsSlideShow",
						goodsInfoId);// 商品轮播图
				logger.info("goodsSlideShow>>>:" + goodsSlideShowUrl);
				upGoodsInfoModel.setGoodsSlideShow(goodsSlideShowUrl);
			}

			/* 更新商品公共信息，添加商品信息分两次完成一是为了能够获取商品公共信息的ID */
			upGoodsInfoModel.setGoodsInfoId(goodsInfoId);
			int upStatus = goodsInfoService
					.updateByPrimaryKeySelective(upGoodsInfoModel);
			logger.info("goodsInfo更新状态>>>:" + upStatus);

			if (checkedAmount != 0) {
				mulriple = priceLength / checkedAmount;// 倍数
				for (int m = 0; m < checkedAmount; m++) {
					if (filesColorImage[m] != null
							&& !filesColorImage[m].isEmpty()) {
						String colorImg = saveColorImgs(filesColorImage[m],
								"goodsColorImg", goodsInfoId, m);
						for (int j = 0; j < mulriple; j++) {
							goodColorImageArraysBypriceLength[m * mulriple
									+ j] = colorImg;
						}
					}
				}
			}

			for (int k = 0; k < priceLength; k++) {
				Goods goodsModel = new Goods();
				if (price != null) {
					BigDecimal goodsPrice = new BigDecimal(price[k]);
					goodsPrice = goodsPrice.setScale(2,
							BigDecimal.ROUND_HALF_UP);
					goodsModel.setGoodsPrice(goodsPrice);
				}
				if (goodColorImageArraysBypriceLength != null) {
					goodsModel.setSectionOneImage(
							goodColorImageArraysBypriceLength[k]);
					System.out.println("goodColorImageArraysBypriceLength111>>"
							+ goodColorImageArraysBypriceLength[k]);
				}
				if (amount != null) {
					goodsModel.setGoodsAmount(Integer.parseInt(amount[k]));
				}
				goodsModel.setpGoodsInfoId(goodsInfoId);
				/*
				 * if(fiveValue!=null){// 暂未使用此字段，待扩展字段
				 * goodsModel.setSectionFiveValue(fiveValue[k]); }
				 * if(fourValue!=null){// 暂未使用此字段，待扩展字段
				 * goodsModel.setSectionFiveValue(fourValue[k]); }
				 */
				if (threeValue != null) {
					goodsModel.setSectionThreeValue(threeValue[k]);
				}
				if (twoValue != null) {
					goodsModel.setSectionTwoValue(twoValue[k]);
				}
				if (oneValue != null) {
					goodsModel.setSectionOneValue(oneValue[k]);
					logger.info("setSectionOneValue>>" + oneValue[k]);
				}
				listGoods.add(goodsModel);
			}

			int addStatus = goodsService.addGood(listGoods);
			if (addStatus > 0) {
				retObject.put("status", 1);
				retObject.put("message", "创建成功！");
				retObject.put("data", retMap);
			} else {
				retObject.put("status", 0);
				retObject.put("message", "创建失败！");
				retObject.put("data", retMap);
			}
			logger.info("addStatus>>:" + addStatus);
		}
		out.write((JSONObject.fromObject(retObject)).toString());
	}

	/**
	 * @author qwc 2017年10月19日下午9:55:41
	 * @param goodsInfoModel
	 * @param request
	 * @param response
	 * @param filesSlideShow
	 * @param filesImageText
	 * @param filesColorImage
	 * @param fileBackgroundImage
	 * @throws IOException void 更新商品基本信息
	 */
	@RequestMapping("updateGoodsInfo")
	public void updateGoodsInfo(GoodsInfo goodsInfoModel,
			HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value = "goodsSlideShowFile1", required = false) MultipartFile filesSlideShow1,
			@RequestParam(value = "goodsSlideShowFile2", required = false) MultipartFile filesSlideShow2,
			@RequestParam(value = "goodsSlideShowFile3", required = false) MultipartFile filesSlideShow3,
			@RequestParam(value = "goodsSlideShowFile4", required = false) MultipartFile filesSlideShow4,
			@RequestParam(value = "backgroundImage", required = false) MultipartFile fileBackgroundImage)
			throws IOException {
		PrintWriter out = response.getWriter();
		RetJsonUtil retJson = new RetJsonUtil();

		MultipartHttpServletRequest Mrequest = (MultipartHttpServletRequest) request;
		String showSectionOneValue = Mrequest
				.getParameter("showSectionOneValue");// 优惠标题一(展示类型为优惠的商品才有)
		String showSectionTwoValue = Mrequest
				.getParameter("showSectionTwoValue");// 优惠标题二(展示类型为优惠的商品才有)
		String showSectionThreeValue = Mrequest
				.getParameter("showSectionThreeValue");// 优惠标题三(展示类型为优惠的商品才有)
		String goodsSlideShowUrl = null;// 轮播图
		String imgUrls = goodsInfoModel.getGoodsSlideShow();
		String imgUrl = null;

		int goodsInfoId = goodsInfoModel.getGoodsInfoId();
		logger.info("goodsInfoId>>" + goodsInfoId);// 输出商品基本信息ID

		// 更新商品优惠信息(前提展示类型是优惠商品)
		if (fileBackgroundImage != null && !fileBackgroundImage.isEmpty()) {// 添加优惠商品的附属资料
			GoodsDiscounts goodsDiscounts = new GoodsDiscounts();
			String showBackgroundImage = saveBackgroundImgs(fileBackgroundImage,
					"discountsBgImage", goodsInfoId, goodsInfoId);
			goodsDiscounts.setpGoodsInfoId(goodsInfoId);
			goodsDiscounts.setShowBackgroundImage(showBackgroundImage);
			goodsDiscounts.setShowSectionOneValue(showSectionOneValue);
			goodsDiscounts.setShowSectionTwoValue(showSectionTwoValue);
			goodsDiscounts.setShowSectionThreeValue(showSectionThreeValue);
			int update = goodsDiscountsService
					.updateDiscountInfoByGoodsInfoId(goodsDiscounts);
			if (update > 0) {
				System.out.println("优惠信息更新成功！");
			}
		}

		// 更新轮播图1
		if (filesSlideShow1 != null && !filesSlideShow1.isEmpty()) {// 轮播图1不为空
			imgUrl = request.getParameter("goodsSlideShowHidden1");// 获取轮播图1先前的保存路径
			goodsSlideShowUrl = upFileUpload(filesSlideShow1, "goodsSlideShow",
					goodsInfoId);// 新轮播1图路径
			if (StringUtils.isNotBlank(imgUrl)) {// 如何原轮播图路径不为为空
				imgUrls = imgUrls.replace(imgUrl, goodsSlideShowUrl);// 新轮播图路径1替换原轮播图1路径
				logger.info("goodsSlideShow>>>:" + goodsSlideShowUrl);
			} else {
				imgUrls = imgUrls + "," + goodsSlideShowUrl; // 新轮播图路径1附加进去
			}
		}

		// 更新轮播图2
		if (filesSlideShow2 != null && !filesSlideShow2.isEmpty()) {// 轮播图不为空
			imgUrl = request.getParameter("goodsSlideShowHidden2");
			goodsSlideShowUrl = upFileUpload(filesSlideShow2, "goodsSlideShow",
					goodsInfoId);// 轮播图
			if (StringUtils.isNotBlank(imgUrl)) {
				imgUrls = imgUrls.replace(imgUrl, goodsSlideShowUrl);
				logger.info("goodsSlideShow>>>:" + goodsSlideShowUrl);
			} else {
				imgUrls = imgUrls + "," + goodsSlideShowUrl;
			}
			logger.info("goodsSlideShow>>>:" + goodsSlideShowUrl);

		}

		// 更新轮播图3
		if (filesSlideShow3 != null && !filesSlideShow3.isEmpty()) {// 轮播图不为空
			imgUrl = request.getParameter("goodsSlideShowHidden3");
			goodsSlideShowUrl = upFileUpload(filesSlideShow3, "goodsSlideShow",
					goodsInfoId);// 轮播图
			if (StringUtils.isNotBlank(imgUrl)) {
				imgUrls = imgUrls.replace(imgUrl, goodsSlideShowUrl);
				logger.info("goodsSlideShow>>>:" + goodsSlideShowUrl);
			} else {
				imgUrls = imgUrls + "," + goodsSlideShowUrl;
			}
			logger.info("goodsSlideShow>>>:" + goodsSlideShowUrl);

		}

		// 更新轮播图4
		if (filesSlideShow4 != null && !filesSlideShow4.isEmpty()) {// 轮播图不为空
			imgUrl = request.getParameter("goodsSlideShowHidden4");
			goodsSlideShowUrl = upFileUpload(filesSlideShow4, "goodsSlideShow",
					goodsInfoId);// 轮播图
			if (StringUtils.isNotBlank(imgUrl)) {
				imgUrls = imgUrls.replace(imgUrl, goodsSlideShowUrl);
				logger.info("goodsSlideShow>>>:" + goodsSlideShowUrl);
			} else {
				imgUrls = imgUrls + "," + goodsSlideShowUrl;
			}
			logger.info("goodsSlideShow>>>:" + goodsSlideShowUrl);
		}

		// 执行更新操作
		goodsInfoModel.setGoodsSlideShow(imgUrls);// 设置好新图片路径
		int upStatus = goodsInfoService
				.updateByPrimaryKeySelective(goodsInfoModel);
		if (upStatus > 0) {
			retJson.setStatus("1");
			retJson.setMessage("更新成功");
			out.write(retJson.getRetJsonM());
		} else {
			retJson.setStatus("0");
			retJson.setMessage("更新失败");
			out.write(retJson.getRetJsonM());
		}
	}

	/**
	 * @author qwc 2017年11月20日下午7:46:35 void 更新商品详细信息
	 * @throws IOException
	 */
	@RequestMapping("updateDetail")
	public void updateDetails(HttpServletRequest request,
			HttpServletResponse response, Goods goods,
			@RequestParam(value = "sectionOneValueNew", required = true) String sectionOneValueNew,
			@RequestParam(value = "sectionTwoValueNew", required = true) String sectionTwoValueNew,
			@RequestParam(value = "sectionThreeValueNew", required = true) String sectionThreeValueNew,
			@RequestParam(value = "goodsColorImage", required = false) MultipartFile goodsColorImage)
			throws IOException {
		PrintWriter out = response.getWriter();
		RetJsonUtil retJsonUtil = new RetJsonUtil();
		Map<String, Object> paramMap = new HashMap<String, Object>();

		if (goodsColorImage != null && !goodsColorImage.isEmpty()) {// 颜色图片不为空
			String imgString = saveColorImgs(goodsColorImage, "goodsColorImg",
					goods.getpGoodsInfoId(), 0);
			goods.setSectionOneImage(imgString);
		}

		paramMap.put("sectionOneValueNew", sectionTwoValueNew);
		paramMap.put("sectionTwoValueNew", sectionTwoValueNew);
		paramMap.put("sectionThreeValueNew", sectionThreeValueNew);
		paramMap.put("record", goods);

		int status = goodsService.updateGoodsDetails(paramMap);
		if (status > 0) {
			retJsonUtil.setMessage("更新成功");
			retJsonUtil.setStatus("1");
		} else {
			retJsonUtil.setMessage("更新失败");
			retJsonUtil.setStatus("0");
		}
		out.write(retJsonUtil.getRetJsonM());
	}

	/**
	 * @author qwc 2017年9月11日下午11:00:01 void 获取商品基本信息列表
	 * @throws IOException
	 */
	@RequestMapping(value = "gtGoodsList", method = RequestMethod.POST)
	public void getGoodsList(HttpSession session, HttpServletRequest request,
			HttpServletResponse response,
			@RequestParam(value = "page", required = true, defaultValue = "1") Integer startPage,
			@RequestParam(value = "rows", required = false, defaultValue = "10") Integer pageSize)
			throws IOException {
		PrintWriter out = response.getWriter();
		Map<String, Object> paramMap = new HashMap<String, Object>();// 查询条件
		Map<String, Object> retMap = new HashMap<String, Object>();
		JSONObject retObject = new JSONObject();
		List<GoodsInfo> goodsInfoList = new ArrayList<GoodsInfo>();

		String showClassfy = request.getParameter("goodsShowClassfy");// 展示分类
		String keyWord = request.getParameter("keyWord");// 搜索关键字
		paramMap.put("showClassfy", showClassfy);// 1-普通 2-每日推荐 3-优惠活动
		paramMap.put("keyWord", keyWord);// 搜索关键字
		int managerId = 1;
		if (managerId == 0) {
			retObject.put("status", 0);
			retObject.put("message", "获取失败，用户不属于任何小区");
			retObject.put("total", 0);
			retObject.put("rows", 0);
			out.write((JSONObject.fromObject(retObject)).toString());
			return;
		} else {
			paramMap.put("managerId", managerId);
		}

		PageHelper.startPage(startPage, pageSize);// 分页获取
		goodsInfoList = goodsInfoService
				.getGoodsInfoListByParamToAdmin(paramMap);
		PageInfo<GoodsInfo> page = new PageInfo<GoodsInfo>(goodsInfoList);
		retObject.put("status", 1);
		retObject.put("message", "获取成功");
		retObject.put("rows", goodsInfoList);// 行
		retObject.put("total", page.getTotal());// 总记录
		out.write((JSONObject.fromObject(retObject)).toString());
	}

	/**
	 * @author qwc 2017年10月17日下午2:09:33
	 * @param session
	 * @param request
	 * @param response
	 * @param startPage
	 * @param pageSize
	 * @throws IOException void 获取单个商品的基本信息
	 */
	@RequestMapping(value = "gtGoodsInfo", method = RequestMethod.GET)
	public ModelAndView getGoodsInfoById(HttpServletRequest request,
			@RequestParam(value = "goodsInfoId", required = true) Integer goodsInfoId)
			throws IOException {
		ModelAndView modelView = new ModelAndView();
		GoodsInfo goodsInfo = new GoodsInfo();
		goodsInfo = goodsInfoService.getGoodsDetailsByGoodsInfoId(goodsInfoId);
		String[] goodsDistributionArray = StringUtils
				.split(goodsInfo.getGoodsDistribution(), ",");
		List<GoodsDistribution> goodsDistributionsList = goodsDistributionService
				.getDistributionListById(goodsDistributionArray);
		goodsInfo.setGoodsDistributionsList(goodsDistributionsList);

		logger.info("单个商品详细信息：" + JSONObject.fromObject(goodsInfo));
		modelView.addObject("modelView", "modelView");
		modelView.addObject("goodsInfo", goodsInfo);
		modelView.setViewName("/shopping-mall/update");
		return modelView;
	}

	/**
	 * @author qwc 2017年10月17日下午1:21:55
	 * @param request
	 * @param model
	 * @return 跳转到商品详细列表界面 String
	 */
	@RequestMapping("jumpToDetailsView")
	public String jumpToGoodsDetailsList(HttpServletRequest request,
			Model model) {
		String goodsInfoId = request.getParameter("goodsInfoId");
		model.addAttribute("goodsInfoId", goodsInfoId);
		return "shopping-mall/detailsList";
	}

	/**
	 * @author qwc 2017年10月17日下午1:00:47
	 * @param session
	 * @param request
	 * @param response
	 * @param startPage
	 * @param pageSize
	 * @throws IOException void 获取某个商品的详细信息列表
	 */
	@RequestMapping(value = "gtGoodsDetaislList", method = RequestMethod.POST)
	public void getGoodsDetailsList(HttpSession session,
			HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value = "page", required = true, defaultValue = "1") Integer startPage,
			@RequestParam(value = "rows", required = false, defaultValue = "10") Integer pageSize,
			@RequestParam(value = "goodsInfoId") Integer goodsInfoId)
			throws IOException {
		PrintWriter out = response.getWriter();
		Map<String, Object> paramMap = new HashMap<String, Object>();// 查询条件
		Map<String, Object> retMap = new HashMap<String, Object>();
		JSONObject retObject = new JSONObject();
		List<Goods> goodsDetailsList = new ArrayList<Goods>();

		String showClassfy = request.getParameter("goodsShowClassfy");// 展示分类
		String keyWord = request.getParameter("keyWord");// 搜索关键字
		paramMap.put("showClassfy", showClassfy);// 1-普通 2-每日推荐 3-优惠活动
		paramMap.put("keyWord", keyWord);// 搜索关键字
		int managerId = 1;
		if (managerId == 0) {
			retObject.put("status", 0);
			retObject.put("message", "获取失败，用户不属于任何小区");
			retObject.put("total", 0);
			retObject.put("rows", 0);
			out.write((JSONObject.fromObject(retObject)).toString());
			return;
		} else {
			paramMap.put("managerId", managerId);
		}

		PageHelper.startPage(startPage, pageSize);// 分页拦截
		goodsDetailsList = goodsService.selectGoodsListPrimaryKey(goodsInfoId);
		PageInfo<Goods> page = new PageInfo<Goods>(goodsDetailsList);
		retObject.put("status", 1);
		retObject.put("message", "获取成功");
		retObject.put("rows", goodsDetailsList);// 页面显示行数
		retObject.put("total", page.getTotal());// 总记录
		out.write((JSONObject.fromObject(retObject)).toString());
	}

	/**
	 * @author qwc 2017年11月14日下午10:58:17
	 * @param files
	 * @param folderName
	 * @param goodsInfoId
	 * @return 添加图片 String
	 */
	public String fileUpload(MultipartFile[] files, String folderName,
			int goodsInfoId) {
		String saveTosqlurl = null;// 保存到数据库中的路径
		String bootUrl = "/usr";// 保存的根目录
		String uploadUrl = "/ezsh/upload/images/" + folderName + "/"
				+ goodsInfoId + "/";
		String returnUrl = null;
		File dir = new File(bootUrl + uploadUrl);
		if (!dir.exists()) {
			dir.mkdirs();
		}
		// 判断file数组不能为空并且长度大于0
		if (files != null && files.length > 0) {
			// 循环获取file数组中得文件
			for (int i = 0; i < files.length; i++) {
				MultipartFile file = files[i];
				if (!file.isEmpty()) {
					try {
						saveTosqlurl = uploadUrl
								+ DateUtil.dateToStr(new Date(),
										DateUtil.DATE_TIME_NO_SLASH)
								+ i + ".jpg";
						file.transferTo(new File(bootUrl + saveTosqlurl));// 保存到服务器中

						if (returnUrl == null) {
							returnUrl = saveTosqlurl;
						} else {
							returnUrl = returnUrl + "," + saveTosqlurl;
						}
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		}
		return returnUrl;
	}

	/**
	 * @author qwc 2017年10月16日下午8:08:32
	 * @param files
	 * @param folderName
	 * @param goodsInfoId
	 * @return 更新商品的轮播图 String
	 */
	public String upFileUpload(MultipartFile file, String folderName,
			int goodsInfoId) {
		String saveToSqlurl = null;// 保存到数据库中的路径
		String bootUrl = "/usr";// 保存的根目录
		String uploadUrl = "/ezsh/upload/images/" + folderName + "/"
				+ goodsInfoId + "/";
		String returnUrl = null;// 返回url
		File dir = new File(bootUrl + uploadUrl);
		if (!dir.exists()) {
			dir.mkdirs();
		}
		// 判断file数组不能为空并且长度大于0
		if (file != null) {
			// 循环获取file数组中得文件
			try {
				saveToSqlurl = uploadUrl + DateUtil.dateToStr(new Date(),
						DateUtil.DATE_TIME_NO_SLASH) + ".jpg";
				file.transferTo(new File(bootUrl + saveToSqlurl));// 保存到服务器中

				if (returnUrl == null) {
					returnUrl = saveToSqlurl;
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return returnUrl;
	}

	/**
	 * @author qwc 2017年8月6日下午5:08:55
	 * @param ex void 配置了 <property name="resolveLazily" value="true" />
	 */
	/*
	 * @ExceptionHandler public void doExcepiton(Exception ex){ if(ex instanceof
	 * MaxUploadSizeExceededException){ System.out.println("文件太大"); } }
	 */
	public static String saveColorImgs(MultipartFile fileBackgroundImage,
			String folderName, int goodsInfoId, int order) {
		String bootUrl = "/usr";// 保存的根目录
		String uploadUrl = "/ezsh/upload/images/" + folderName + "/"
				+ goodsInfoId + "/";
		String saveUrl;
		File dir = new File(bootUrl + uploadUrl);
		if (!dir.exists()) {
			dir.mkdirs();
		}
		saveUrl = uploadUrl
				+ DateUtil.dateToStr(new Date(), DateUtil.DATE_TIME_NO_SLASH)
				+ goodsInfoId + order + ".jpg";
		File targetFile = new File(bootUrl + saveUrl);
		try {
			if (!(fileBackgroundImage.isEmpty())
					&& fileBackgroundImage != null) {
				fileBackgroundImage.transferTo(targetFile);
			} else {
				return null;
			}
		} catch (IllegalStateException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return saveUrl;
	}

	/**
	 * @author qwc 2017年8月22日下午5:18:19
	 * @param appPayRequest
	 * @param folderName
	 * @param goodsInfoId
	 * @return String 保存背景图
	 */
	public static String saveBackgroundImgs(MultipartFile fileBackgroundImage,
			String folderName, int managerId, int goodsInfoId) {
		String bootUrl = "/usr";// 保存的根目录
		String uploadUrl = "/ezsh/upload/images/" + folderName + "/" + managerId
				+ "/";
		String saveUrl;
		File dir = new File(bootUrl + uploadUrl);
		if (!dir.exists()) {
			dir.mkdirs();
		}
		saveUrl = uploadUrl
				+ DateUtil.dateToStr(new Date(), DateUtil.DATE_TIME_NO_SLASH)
				+ goodsInfoId + ".jpg";
		File targetFile = new File(bootUrl + saveUrl);
		try {
			if (!(fileBackgroundImage.isEmpty())) {
				fileBackgroundImage.transferTo(targetFile);
			}
		} catch (IllegalStateException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return saveUrl;
	}
}
