package com.zeng.ezsh.wy.action;

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONArray;

import org.apache.commons.collections.CollectionUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.zeng.ezsh.wy.entity.Advice;
import com.zeng.ezsh.wy.entity.AdviceResult;
import com.zeng.ezsh.wy.entity.Feedback;
import com.zeng.ezsh.wy.entity.FeedbackResponse;
import com.zeng.ezsh.wy.service.FeedbackService;
import com.zeng.ezsh.wy.utils.DateUtil;


@RequestMapping("feedback")
@Controller
public class FeedbackAction {
	
	@Autowired
	private FeedbackService feedbackService;
	
	private Logger logger = Logger.getLogger(FeedbackAction.class);
	
	/**
	 * 
	 * @param feedback
	 * @param imgs
	 * @param request
	 * @return FeedbackResponse
	 * Description:用户提交反馈
	 * @author:HAO
	 */
	@RequestMapping(value="add",method=RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public FeedbackResponse addFeedback(Feedback feedback,@RequestParam("imgs") MultipartFile[] imgs,HttpServletRequest request){
		FeedbackResponse response = null;
		if(imgs != null && imgs.length > 0){
			//保存图片路径
			feedback.setImg(uploadFiles(imgs,request));
		}
		feedback.setTime(new Date());
		try{
			if(feedbackService.addFeedback(feedback)==1){
				response = new FeedbackResponse(1,"操作成功");
			}else{
				response = new FeedbackResponse(0,"操作失败");
			}
		}catch(Exception e){
			logger.error("用户提交反馈意见时出现异常", e);
			response = new FeedbackResponse(-1,"未知异常");
		}
		return response;
	}
	
	
	//上传图片或附件
	public String uploadFiles(MultipartFile[] files,HttpServletRequest request){
		//上传路径
		String imgUploadUrl = "/ezsh/"+"upload/images/feedback/";
		StringBuilder sb = new StringBuilder();
		if(files != null && files.length > 0){
			for(int i = 0; i < files.length; i ++){
				MultipartFile file = files[i];
				saveFile(file,request);
				if(file == files[files.length-1]){
					sb.append(imgUploadUrl + file.getOriginalFilename());
				}else{
					sb.append(imgUploadUrl + file.getOriginalFilename()+",");
				}
			}
		}
		return sb.toString();
	}
	/**
	 * 
	 * @param file
	 * @return FeedbackResponse
	 * Description:保存文件
	 * @author:HAO
	 */
	public boolean saveFile(MultipartFile file,HttpServletRequest request) {  
		//保存到服务器的路径
		String bootUrl="/usr";//保存的根目录
        String uploadUrl="/ezsh/"+"upload/images/feedback";
        String savedirpath = bootUrl + uploadUrl;
        if (!file.isEmpty()) {  
            try {  
            	File savedir = new File(savedirpath);
            	if(!savedir.exists()){
            		savedir.mkdirs();
            	}
                // 文件保存路径  
                String filePath = savedirpath + File.separator
                        + file.getOriginalFilename();  
                // 转存文件  
                file.transferTo(new File(filePath));  
             
                return true;  
            } catch (Exception e) {  
                e.printStackTrace();  
            }  
        }  
        return false;    
    }  
	
	/**
	 * 
	 * @param communityId
	 * @param page
	 * @param pagesize
	 * @return FeedbackResponse
	 * Description:客服获取用户反馈列表
	 * @author:HAO
	 */
	@RequestMapping(value="getList",method=RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public Map<Object,Object> getFeedbackList(int page,int rows,int result){
		Map<Object,Object> jo = new HashMap();
		Map<String,Object> map = new HashMap<>();
		if(page == 0){
			page = 1;
		}
		if(rows == 0){
			rows = 10;
		}
		//分页获取数据
		map.put("start", (page-1)*10);
		map.put("pagesize", rows);
		map.put("result", result);
		List<Feedback> list = feedbackService.getFeedback(map);
		for(int i=0;i<list.size();i++){
			list.get(i).setSubmitTime(DateUtil.dateToStr(list.get(i).getTime(), 12));
		}	
		JSONArray json = JSONArray.fromObject(list);
		jo.put("total", feedbackService.getFeedbackCount(map));
		jo.put("rows", json);
		return jo;
	}
	
	/**
	 * Description:删除用户意见记录
	 * @param id
	 * @return FeedbackResponse
	 */
	@RequestMapping(value="delete",method=RequestMethod.POST)
	@ResponseBody
	public FeedbackResponse deleteFeedback(int id){
		FeedbackResponse response = null;
		try{
			if(feedbackService.deleteFeedback(id)==1){
				response = new FeedbackResponse(1,"操作成功");
			}else{
				response = new FeedbackResponse(0,"操作失败");
			}
		}catch(Exception e){
			logger.error("删除用户意见记录时出现异常", e);
			response = new FeedbackResponse(-1,"未知错误");
		}
		return response;
	}
	
	/**
	 * 
	 * @param id
	 * @param result
	 * @return FeedbackResponse
	 * Description:客服审核用户反馈
	 * @author:HAO
	 */
	@RequestMapping(value="veryfy",method=RequestMethod.POST)
	@ResponseBody
	public FeedbackResponse veryfyFeedback(int id,int result){
		FeedbackResponse response = null;
		Map<String,Object> map = new HashMap<>();
		map.put("id", id);
		map.put("result", result);
		try{
			if(feedbackService.veryfyFeedback(map)==1){
				response = new FeedbackResponse(1,"操作成功");
			}else{
				response = new FeedbackResponse(0,"操作失败");
			}
		}catch(Exception e){
			logger.error("客服审核用户反馈时出现异常", e);
			response = new FeedbackResponse(-1,"未知异常");
		}
		return response;
	}
	
	/**
	 * 
	 * @param communityId
	 * @param userPhone
	 * @return FeedbackResponse
	 * Description:用户获取意见记录
	 * @author:HAO
	 */
	@RequestMapping(value="userGetFeedback",method=RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public FeedbackResponse userGetFeedback(@RequestParam("communityId") int communityId,@RequestParam("userPhone") String userPhone){
		FeedbackResponse result = null;
		Map<String,Object> map  = new HashMap<>();
		map.put("communityId", communityId);
		map.put("userPhone", userPhone);
		try{
			List<Feedback> list = feedbackService.userGetFeedback(map);
			if(CollectionUtils.isEmpty(list)){
				result = new FeedbackResponse(0,"无数据");
			}else{
				//封装图片路径
				for(Feedback advice:list){
					String img = advice.getImg();
					if(img != null && img != "" && img.length() != 0){
					if(img.indexOf(",") != -1){
						String[] imgUrl = img.split(",");
						advice.setImgUrl(imgUrl);
					}else{
						String[] imgArray = new String[1];
						imgArray[0] = img;
						advice.setImgUrl(imgArray);
					}
				}
				}
				result = new FeedbackResponse(1,list,"获取成功");
			}
		}catch(Exception e){
			logger.error("获取用户意见记录时出现异常", e);
			e.printStackTrace();
			result = new FeedbackResponse(-1,"系统内部错误");
		}
		return result;
	}
	
	/**
	 * Description:查看意见记录图片
	 * @param id
	 * @return AdviceResult
	 */
	@RequestMapping(value="getFeedbackImg",method=RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public FeedbackResponse getImgsUrlByid(int id,HttpServletRequest request){
		FeedbackResponse result = null;
		//访问文件路径
		String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort();
		String imgUrl = feedbackService.getImgsUrlById(id);
		//将图片路径以逗号分隔，构造数组
		if(imgUrl != null && imgUrl != "" && imgUrl.length() != 0){
			if(imgUrl.indexOf(",") != -1){
				String[] imgArray = imgUrl.split(",");
				for(int i=0;i<imgArray.length;i++){
					imgArray[i] = basePath + imgArray[i];
				}
				result = new FeedbackResponse(1,imgArray,"获取成功");
			}else{
				String[] imgArray = new String[1];
				imgArray[0] = basePath + imgUrl;
				result = new FeedbackResponse(1,imgArray,"获取成功");
			}
		}else{
			result = new FeedbackResponse(0,"无图片!");
		}
		return result;
	}

}
