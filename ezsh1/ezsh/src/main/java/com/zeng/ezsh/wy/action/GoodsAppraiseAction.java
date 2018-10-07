package com.zeng.ezsh.wy.action;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zeng.ezsh.wy.entity.Goods;
import com.zeng.ezsh.wy.entity.GoodsAppraise;
import com.zeng.ezsh.wy.entity.GoodsOrderDetails;
import com.zeng.ezsh.wy.service.GoodsAppraiseService;
import com.zeng.ezsh.wy.service.GoodsOrderService;
import com.zeng.ezsh.wy.utils.AccessTokenUtil;
import com.zeng.ezsh.wy.utils.DateUtil;
import com.zeng.ezsh.wy.utils.RetJsonUtil;
@Controller
@RequestMapping("appraise")
public class GoodsAppraiseAction {
	private Logger logger  = Logger.getLogger(GoodsAppraiseAction.class);
	@Resource
	GoodsAppraiseService goodsAppraiseService;
	@Resource
	GoodsOrderService goodsOrderDetailsService;
	/**
	 * @author qwc
	 * 2017年8月8日下午10:36:03
	 * @param request
	 * @param response
	 * @param contenImage
	 * @throws IOException void
	 * 用户添加商品评价
	 */
	@RequestMapping("addAppr")
	public void addAppraise(HttpServletRequest request, HttpServletResponse response,
			@RequestParam("contenImage") MultipartFile[] contenImage) throws IOException{
		PrintWriter out = response.getWriter();
		MultipartHttpServletRequest Mrequest = (MultipartHttpServletRequest)request;
		RetJsonUtil retJsonUtil = new RetJsonUtil();
		
		Map<String, Object> retMap = new HashMap<String, Object>();
		Map<String, Object> tokenMap = new HashMap<String, Object>();
		GoodsAppraise goodsAppraiseModel = new GoodsAppraise();
		GoodsOrderDetails goodsOrderDetails  =  new GoodsOrderDetails();
		
		// 获取登录token
		String token = Mrequest.getParameter("token");
		tokenMap = AccessTokenUtil.parserAccessTokenToMap(token);
		
		// 用户ID
		int userId = (int) tokenMap.get("userId");
		// 用户所在的小区ID
		int managerId = (int) tokenMap.get("managerId");
		// 评价内容
		String appraiseContent = Mrequest.getParameter("appraiseContent");
		// 所评价商品基本信息ID
		int goodsInfoId = Integer.parseInt(Mrequest.getParameter("goodsInfoId"));
		// 所评价商品的详细信息ID
		int goodsOrderDetailsId = Integer.parseInt(Mrequest.getParameter("orderDetailsId"));
		// 评价图片
		String appraiseImg = apprfileUpload(contenImage,"goodsAppraiseImg",goodsInfoId);
		
		// 设置评价操作对象
		goodsAppraiseModel.setpUserId(userId);
		goodsAppraiseModel.setpManagerId(managerId);
		goodsAppraiseModel.setpGoodsInfoId(goodsInfoId);
		goodsAppraiseModel.setAppraiseContent(appraiseContent);
		goodsAppraiseModel.setAppraiseTime(DateUtil.dateToStr(new Date(), DateUtil.DATE_HM));
		goodsAppraiseModel.setAppraiseImg(appraiseImg);
		goodsOrderDetails.setOrderDetailsId(goodsOrderDetailsId);
		
		int status = goodsAppraiseService.insertUpdateAppraise(goodsAppraiseModel, goodsOrderDetails);
		if(status>0){
			retMap.put("appraiseImg", appraiseImg);
			retJsonUtil.setStatus("1");
			retJsonUtil.setMessage("评价成功");
			retJsonUtil.setRetMap(retMap);
		}else{
			retJsonUtil.setStatus("0");
			retJsonUtil.setMessage("评价失败");
			retJsonUtil.setRetMap(retMap);
		}
		out.write(retJsonUtil.getRetJsonM());
	}
	
	/**
	 * @author qwc
	 * 2017年8月8日下午10:36:22
	 * @param request
	 * @param response
	 * @param startPage
	 * @param pageSize
	 * @throws IOException void
	 * 获取商品评价列表
	 */
	@RequestMapping("gtAppraiseList")
	public void getAppraiseList(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value = "startPage",required = true,defaultValue = "1") Integer startPage,
			@RequestParam(value = "pageSize",required = false,defaultValue = "10") Integer pageSize) throws IOException{
		PrintWriter out = response.getWriter();
		RetJsonUtil retJsonUtil  =  new RetJsonUtil();
		Map<String, Object> retMap = new HashMap<String, Object>();
		
		// token解析
		/*Map<String, Object> tokenMap = new HashMap<String, Object>();
		String token = request.getParameter("token");
		tokenMap = AccessTokenUtil.parserAccessTokenToMap(token);*/
		
		// 设置查询参数
		List<GoodsAppraise> goodsAppraiseList = new ArrayList<GoodsAppraise>();
		GoodsAppraise goodsAppraiseModel = new GoodsAppraise();
		int goodsInfoId = Integer.parseInt(request.getParameter("goodsInfoId"));
		goodsAppraiseModel.setpGoodsInfoId(goodsInfoId);//设置商品基本信息ID
		
		// 执行查询
		PageHelper.startPage(startPage, pageSize);
		goodsAppraiseList = goodsAppraiseService.getGoodsAppraiseList(goodsAppraiseModel);
		PageInfo<GoodsAppraise> page = new PageInfo<GoodsAppraise>(goodsAppraiseList);
		
		// 返回结果
		retMap.put("tatalPage", page.getPages());
		retMap.put("goodsAppraiseList", goodsAppraiseList);
		retJsonUtil.setStatus("1");
		retJsonUtil.setMessage("获取成功");
		retJsonUtil.setRetMap(retMap);
		out.write(retJsonUtil.getRetJsonM());
	}
	
	/**
	 * @author qwc
	 * 2017年8月8日下午8:22:48
	 * @param files
	 * @param folderName
	 * @param goodsInfoId
	 * @return String
	 * 评论文件上传
	 */
	public String apprfileUpload(MultipartFile[] files,String folderName,int goodsInfoId) { 
    	String saveTosqlurl = null;//保存到数据库中的路径
    	String bootUrl = "/usr";//保存的根目录
        String uploadUrl = "/ezsh/"+"upload/images/"+folderName+"/"+goodsInfoId+"/";
		String returnUrl = null;
		File dir  =  new File(bootUrl+uploadUrl);
		if(!dir.exists()){
			dir.mkdirs();
		}
        //判断file数组不能为空并且长度大于0  
        if(files != null && files.length>0){  
            //循环获取file数组中得文件  
            for(int i  =  0;i<files.length;i++){  
                MultipartFile file  =  files[i];  
                try {
                	saveTosqlurl = uploadUrl+DateUtil.dateToStr(new Date(),DateUtil.DATE_TIME_NO_SLASH)+i+".jpg";
                    file.transferTo(new File(bootUrl+saveTosqlurl));//保存到服务器中
                    
                    if(returnUrl == null){
                    	returnUrl = saveTosqlurl;
                    }else{
                    	returnUrl = returnUrl+","+saveTosqlurl;
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }     
        }
        logger.info("returnUrl>>>:"+returnUrl);
		return returnUrl;  
    }
}
