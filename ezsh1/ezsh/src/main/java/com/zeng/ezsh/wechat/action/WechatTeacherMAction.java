package com.zeng.ezsh.wechat.action;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Date;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import com.zeng.ezsh.wechat.official.config.WechatOfficialConfig;
import com.zeng.ezsh.wy.entity.Rent;
import com.zeng.ezsh.wy.entity.Teacher;
import com.zeng.ezsh.wy.service.TeacherService;
import com.zeng.ezsh.wy.utils.DateUtil;
import com.zeng.ezsh.wy.utils.RetJsonUtil;

@Controller
@RequestMapping("teacherW")
public class WechatTeacherMAction {
	@Resource
	TeacherService teacherService;
	
	/**
	 * @author qwc
	 * 2017年11月30日下午11:02:31
	 * @param request
	 * @param response
	 * @param record
	 * @throws IOException 
	 * void
	 */
	@RequestMapping(value = "update")
	public void updateRent(HttpServletRequest request, HttpServletResponse response,
		Teacher record, HttpSession session, MultipartFile studentCardFile) throws IOException{
		PrintWriter out = response.getWriter();
		RetJsonUtil retJsonUtil = new RetJsonUtil();
		Teacher recordSession = (Teacher) session.getAttribute(WechatOfficialConfig.WECHAT_SESSION_ID);		
		if(recordSession != null) {
			if(studentCardFile != null && !studentCardFile.isEmpty()){
				String studentCard = fileUpload(studentCardFile, "studentCard", recordSession.getTeacherId());
				record.setStudentCard(studentCard);
				record.setCheckStatus(0); //设置为待审核
			}
		} else {
			retJsonUtil.setMessage("登录过期");
			retJsonUtil.setStatus("1");
			out.write(retJsonUtil.getRetJsonM());
			return;
		}
		record.setCompleteStatus(1); //设置为已完善
		int status = teacherService.updateByPrimaryKeySelective(record);
		
		// 封装返回结果
		if (status > 0){
			// 更新session
			record.setTeacherWcOpenId(recordSession.getTeacherWcOpenId());
			record = teacherService.selectByPrimaryKey(record);
			session.setAttribute(WechatOfficialConfig.WECHAT_SESSION_ID, record);
			retJsonUtil.setMessage("更新成功");
			retJsonUtil.setStatus("1");
		} else {
			retJsonUtil.setMessage("更新失败");
			retJsonUtil.setStatus("0");
		}
		out.write(retJsonUtil.getRetJsonM());
	}
	
	
	/**
	 * @author qwc
	 * 2017年12月1日上午9:09:12
	 * @param imgStr
	 * @param teacherId 
	 * void 头像更新
	 * @throws IOException 
	 */
	@RequestMapping(value = "updateIcon")
	public void updateIcon(HttpServletRequest request, HttpServletResponse response, 
			String imgStr, HttpSession session,
			@RequestParam(value="teacherId", required=false, defaultValue = "0") Integer teacherId) throws IOException{
		PrintWriter out = response.getWriter();
		RetJsonUtil retJsonUtil = new RetJsonUtil();
		
		//保存的根目录
		String bootUrl = "/usr";
        String imgFilePath = "/ezsh/"+"upload/images/teacherIcon/";
        File dir  =  new File(bootUrl+imgFilePath);
        
      //Base64解码
		if(!dir.exists()){
			dir.mkdirs();
		}
        if (imgStr == null || teacherId == 0) //图像数据为空
			 return ;
		try {
			byte[] b = Base64.decodeBase64(imgStr.substring(imgStr.indexOf(",")+1));
			for(int i=0;i<b.length;++i){
				if(b[i]<0){//调整异常数据
					b[i]+=256;
				}
			}

			//生成jpeg图片
			String imgUrl = imgFilePath + DateUtil
					.dateToStr(new Date(), DateUtil.DATE_TIME_NO_SLASH)+teacherId+".jpg";
			OutputStream outImg = new FileOutputStream(new File(bootUrl + imgUrl));    
		    outImg.write(b);
		    outImg.flush();
		    outImg.close();
		    Teacher record = new Teacher();
		    record.setTeacherIcon(imgUrl);
		    record.setTeacherId(teacherId);
		    int status = teacherService.updateByPrimaryKeySelective(record);
		    if(status > 0){
		    	System.out.println("状态："+status);
		    	Teacher recordSession = (Teacher) session.getAttribute(WechatOfficialConfig.WECHAT_SESSION_ID);
				// 更新session
				if(recordSession != null){
					recordSession.setTeacherIcon(imgUrl);
					session.setAttribute(WechatOfficialConfig.WECHAT_SESSION_ID, recordSession);
					retJsonUtil.setMessage("上传成功");
				    retJsonUtil.setObject(imgUrl);
				    retJsonUtil.setStatus("1");
				} else {
					retJsonUtil.setMessage("登录过期");
					retJsonUtil.setStatus("-1");
				}
		    	
		    } else {
		    	retJsonUtil.setMessage("上传失败");
			    retJsonUtil.setObject(imgUrl);
			    retJsonUtil.setStatus("0");
		    }
		    out.write(retJsonUtil.getRetJsonO());
		} catch (Exception e) {
			retJsonUtil.setMessage("长传失败");
		    retJsonUtil.setStatus("0");
		    out.write(retJsonUtil.getRetJsonO());
		}
	}
	
	/**
	 * @author qwc
	 * 2017年12月1日下午9:38:30
	 * @param file
	 * @param folderName
	 * @param teacherId
	 * @return 
	 * String 上传身份证
	 */
	public String fileUpload(MultipartFile file, String folderName, int teacherId) { 
		String saveTosqlurl=null;//保存到数据库中的路径
		String bootUrl="/usr";//保存的根目录
		String uploadUrl="/ezsh/"+"upload/images/"+folderName+"/"+teacherId+"/";
		File dir = new File(bootUrl+uploadUrl);
		if(!dir.exists()){
			dir.mkdirs();
		}
		//判断file数组不能为空并且长度大于0  
		if(file != null){  
		    //循环获取file数组中得文件  
			try {
	        	saveTosqlurl=uploadUrl+DateUtil.dateToStr(new Date(), DateUtil.DATE_TIME_NO_SLASH) + ".jpg";
	            file.transferTo(new File(bootUrl + saveTosqlurl));//保存到服务器中
            } catch (IOException e) {
                e.printStackTrace();
            }
	    }
		return saveTosqlurl;  
	}
}
