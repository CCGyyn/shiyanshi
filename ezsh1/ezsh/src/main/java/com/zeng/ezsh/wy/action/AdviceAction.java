package com.zeng.ezsh.wy.action;

import java.io.File;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.PageContext;

import net.sf.json.JSONArray;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.zeng.ezsh.wy.entity.Advice;
import com.zeng.ezsh.wy.entity.AdviceResult;
import com.zeng.ezsh.wy.entity.ParkRecord;
import com.zeng.ezsh.wy.service.AdviceManageService;
import com.zeng.ezsh.wy.utils.DateUtil;

/**
 * Title:AdviceAction
 * Description:投诉管理接口
 * @author HAO
 * @date:2017年8月13日 下午8:39:22
 */
@RequestMapping(value="/advice")
@Controller
public class AdviceAction {

	@Resource
	AdviceManageService adviceService;
	/**
	 * 
	 * @param advice
	 * @return AdviceResult
	 * Description:用户提交投诉接口
	 * @author:HAO
	 * @throws ParseException 
	 */
	@RequestMapping(method=RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public AdviceResult addAdvice(Advice advice,@RequestParam("files") MultipartFile[] files,HttpServletRequest request) throws ParseException{
		String imgUploadUrl = "/ezsh/"+"upload/images/advice/";
		AdviceResult adviceResult = null;
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
			advice.setImg(sb.toString());
		}
		Date createTime = new Date();
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		createTime = format.parse(format.format(createTime));
		advice.setCreateTime(createTime);
		try{
			if(adviceService.addAdvice(advice)==1){
				adviceResult = new AdviceResult(1,"操作成功");
			}else{
				adviceResult = new AdviceResult(0,"操作失败");
			}
		}catch(Exception e){
			e.printStackTrace();
			adviceResult = new AdviceResult(0,"系统内部错误");
		}
		return adviceResult;
	}
	/**
	 * 
	 * @param file
	 * @return
	 * Description:保存文件
	 * @author:HAO
	 */
	public boolean saveFile(MultipartFile file,HttpServletRequest request) {  
        //String savedirpath = request.getSession().getServletContext().getRealPath("/") + "upload"+File.separator+"image"+File.separator+"adviceimg";
		String bootUrl="/usr";//保存的根目录
        String uploadUrl="/ezsh/"+"upload/images/advice";
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
	 * @return AdviceResult
	 * Description:客服获取用户投诉接口
	 * @author:HAO
	 */
	@RequestMapping(value="/customservice",method=RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public AdviceResult getAdviceByCommunity(@RequestParam("communityId")int communityId,@RequestParam("page")int page,@RequestParam("pagesize")int pagesize,HttpServletRequest request){
		String path =request.getContextPath();
		String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+File.separator+"upload"+File.separator+"image"+File.separator+"adviceimg";
		AdviceResult adviceResult = null;
		Map<String,Object> map = new HashMap<>();
		if(page == 0){
			page = 1;
		}
		if(pagesize == 0){
			pagesize = 10;
		}
		map.put("communityId", communityId);
		map.put("start", (page-1)*pagesize);
		map.put("pagesize", pagesize);
		try{
			List<Advice> list = adviceService.getAdvice(map);
			if(list.size()!=0){
				for(Advice advice:list){
					String img = advice.getImg();
					if(img != null && img != "" && img.length() != 0){
					if(img.indexOf(",") != -1){
						String[] imgUrl = img.split(",");
//						for(int i = 0;i<imgUrl.length;i++){
//							imgUrl[i] = basePath + File.separator + imgUrl[i];
//							
//						}
						advice.setImgUrl(imgUrl);
					}else{
						//..img = basePath + File.separator + img;
						String[] imgArray = new String[1];
						imgArray[0] = img;
						advice.setImgUrl(imgArray);
					}
				}
				}
				adviceResult = new AdviceResult(1,list,"获取成功");
			}
			else{
				adviceResult = new AdviceResult(0,"无数据");
			}
		}catch(Exception e){
			e.printStackTrace();
			adviceResult = new AdviceResult(-1,"系统内部错误");
		}
		return adviceResult;
	}
	
	
	/**
	 * 
	 * @param id
	 * @param result
	 * @return AdviceResult
	 * Description:客服审核接口
	 * @author:HAO
	 */
	@RequestMapping(value="audite",method=RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public AdviceResult auditeAdvice(@RequestParam("id") int id,@RequestParam("result")int result){
		AdviceResult adviceResult = null;
		Map<String,Object> map = new HashMap<>();
		map.put("id", id);
		map.put("result", result);
		try{
			if(adviceService.auditeAdvice(map) == 1){
				adviceResult = new AdviceResult(1,"操作成功");
			}else{
				adviceResult = new AdviceResult(0,"操作失败");
			}
		}catch(Exception e){
			e.printStackTrace();
			adviceResult = new AdviceResult(0,"操作失败");
		}
		return adviceResult;
	}
	
	/**
	 * 
	 * @param id
	 * @return AdviceResult
	 * Description:后台删除记录接口
	 * @author:HAO
	 */
	@RequestMapping(method=RequestMethod.DELETE)
	@ResponseBody
	public AdviceResult deleteAdviceById(@RequestParam("id") int id){
		AdviceResult adviceResult = null;
		try{
			if(adviceService.deleteByPrimaryKey(id) == 1){
				adviceResult = new AdviceResult(1,"操作成功");
			}else{
				adviceResult = new AdviceResult(0,"操作失败");
			}
		}catch(Exception e){
			e.printStackTrace();
			adviceResult = new AdviceResult(0,"系统内部错误");
		}
		return adviceResult;
	}
	
	/**
	 * 
	 * @param id
	 * @return AdviceResult
	 * Description:后台获取某记录接口
	 * @author:HAO
	 */
	@RequestMapping(value="/manage/{id}",method=RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public AdviceResult getAdviceById(@PathVariable("id") int id){
		AdviceResult adviceResult = null;
		try{
			Advice advice = adviceService.getAdviceById(id);
			if(advice != null){
				adviceResult = new AdviceResult(1,advice,"获取成功");
			}else{
				adviceResult = new AdviceResult(0,"无数据");
			}
		}catch(Exception e){
			e.printStackTrace();
			adviceResult = new AdviceResult(0,"系统内部错误");
		}
		return adviceResult;
	}
	
	/**
	 * 
	 * @param page
	 * @param rows
	 * @return
	 * Description:后台获取数据接口
	 * @author:HAO
	 */
	@RequestMapping(value="/manage",method=RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public Map<Object,Object> getAdviceByPage(@RequestParam("page") int page,@RequestParam("rows") int rows,@RequestParam("result")int result){
		Map<String,Object> map = new HashMap<>();
		map.put("start",(page-1)*10 );
		map.put("rows", rows);
		map.put("result", result);
		List<Advice> list = adviceService.getAdviceByPage(map);
		for(int i=0;i<list.size();i++){
			list.get(i).setCreatetime(DateUtil.dateToStr(list.get(i).getCreateTime(), 12));
		}
		Map<Object,Object> jo = new HashMap();
		JSONArray json = JSONArray.fromObject(list);
		jo.put("total", adviceService.getAdviceCount());
		jo.put("rows", json);
		return jo;
	}
	
	/**
	 * 
	 * @param communityId
	 * @param userPhone
	 * @return
	 * Description:用户获取投诉记录
	 * @author:HAO
	 */
	@RequestMapping(value="userGetAdvice",method=RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public AdviceResult userGetAdvice(@RequestParam("communityId") int communityId,@RequestParam("userPhone") String userPhone){
		AdviceResult result = null;
		Map<String,Object> map  = new HashMap<>();
		map.put("communityId", communityId);
		map.put("userPhone", userPhone);
		try{
			List<Advice> list = adviceService.getAdviceByIdAndPhone(map);
			if(list.size()==0){
				result = new AdviceResult(0,"无数据");
			}else{
				for(Advice advice:list){
					String img = advice.getImg();
					if(img != null && img != "" && img.length() != 0){
					if(img.indexOf(",") != -1){
						String[] imgUrl = img.split(",");
//						for(int i = 0;i<imgUrl.length;i++){
//							imgUrl[i] = basePath + File.separator + imgUrl[i];
//							
//						}
						advice.setImgUrl(imgUrl);
					}else{
						//..img = basePath + File.separator + img;
						String[] imgArray = new String[1];
						imgArray[0] = img;
						advice.setImgUrl(imgArray);
					}
				}
				}
				result = new AdviceResult(1,list,"获取成功");
			}
		}catch(Exception e){
			e.printStackTrace();
			result = new AdviceResult(-1,"系统内部错误");
		}
		return result;
	}
	
	/**
	 * Description:查看投诉记录图片
	 * @param id
	 * @return AdviceResult
	 */
	@RequestMapping(value="getAdviceImg",method=RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public AdviceResult getImgsUrlByid(int id,HttpServletRequest request){
		AdviceResult result = null;
		//访问文件路径
		String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort();
		String imgUrl = adviceService.getImgsUrlById(id);
		//将图片路径以逗号分隔，构造数组
		if(imgUrl != null && imgUrl != "" && imgUrl.length() != 0){
			if(imgUrl.indexOf(",") != -1){
				String[] imgArray = imgUrl.split(",");
				for(int i=0;i<imgArray.length;i++){
					imgArray[i] = basePath + imgArray[i];
				}
				result = new AdviceResult(1,imgArray,"获取成功");
			}else{
				String[] imgArray = new String[1];
				imgArray[0] = basePath + imgUrl;
				result = new AdviceResult(1,imgArray,"获取成功");
			}
		}else{
			result = new AdviceResult(0,"无图片!");
		}
		return result;
	}
}
