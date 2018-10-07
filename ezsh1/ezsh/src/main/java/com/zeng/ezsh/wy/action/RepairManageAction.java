package com.zeng.ezsh.wy.action;

import java.io.File;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONArray;
import net.sf.json.JsonConfig;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.zeng.ezsh.wy.entity.RepairRecord;
import com.zeng.ezsh.wy.entity.RepairRecordVO;
import com.zeng.ezsh.wy.entity.RepairResponse;
import com.zeng.ezsh.wy.service.RecordManageService;
import com.zeng.ezsh.wy.utils.DateUtil;
import com.zeng.ezsh.wy.utils.JsonDateValueProcessor;

@Controller
@RequestMapping("/repair")
public class RepairManageAction {
	
	@Autowired
	private RecordManageService recordManageService;
	
	/**
	 * 
	 * @param repairRecord
	 * @return
	 * Description:提交报单
	 * @author:HAO
	 * @throws ParseException 
	 */
	@ResponseBody
	@RequestMapping(method=RequestMethod.POST, produces = "application/json")
	public RepairResponse submitRepairRecord(RepairRecord repairRecord,String expectTime,@RequestParam("files") MultipartFile[] files,HttpServletRequest request) throws ParseException{
		String imgUploadUrl = "/ezsh/"+"upload/images/repair/";
		RepairResponse repairResponse = null;
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
		repairRecord.setRepairImages(sb.toString());
		}
		Date repairTime = new Date();
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		repairTime = format.parse(format.format(repairTime));
		repairRecord.setRepairTime(repairTime);
		repairRecord.setExpectTime(expectTime);
		repairRecord.setRepairNumber(DateUtil.dateToStr(new Date(), 5)+repairRecord.getProprietorPhone());
		if(recordManageService.submitRepair(repairRecord)==1){
			repairResponse = new RepairResponse(1,"提交成功");
		}else{
			repairResponse = new RepairResponse(0,"提交失败");
		}
		return repairResponse;
	}
	
	/**
	 * 
	 * @param file
	 * @return
	 * Description:保存文件
	 * @author:HAO
	 */
	public boolean saveFile(MultipartFile file,HttpServletRequest request) {  
		String bootUrl="/usr";//保存的根目录
        String uploadUrl="/ezsh/"+"upload/images/repair";
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
	 * @param repairNumber
	 * @param result
	 * @return
	 * Description:派工后及完工后修改报单状态
	 * @author:HAO
	 * @throws ParseException 
	 */
	@ResponseBody
	@RequestMapping(value="/modifystatus",method=RequestMethod.POST)
	public RepairResponse changeRecordStatus(String repairNumber,int result,String repairUnit,String repairStaff,String repairSphone,String completeStatus) throws ParseException{
		RepairResponse repairResponse = null;
		Map<String,Object> map = new HashMap<>();
		map.put("repairNumber", repairNumber);
		map.put("result", result);
		if(recordManageService.changeRepairStatus(map)==1){
			RepairRecord repairRecord = recordManageService.getRepairRecordByNumber(repairNumber);
			if(result == 1){
				repairRecord.setRepairUnit(repairUnit);
				repairRecord.setRepairStaff(repairStaff);
				repairRecord.setRepairSphone(repairSphone);
			}
			if(result == 2){
				Date completeTime = new Date();
				DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				completeTime = format.parse(format.format(completeTime));
				repairRecord.setCompleteTime(completeTime);
				if(completeStatus != null){
					repairRecord.setCompleteStatus(completeStatus);
				}
				recordManageService.updateByPrimaryKeySelective(repairRecord);
			}
			repairResponse = new RepairResponse(1,"操作成功");
		}else{
			repairResponse = new RepairResponse(0,"操作失败");
		}
		return repairResponse;
	}
	
	/**
	 * 
	 * @param communityId
	 * @param page
	 * @param rows
	 * @return
	 * Description:客服获取报单数据
	 * @author:HAO
	 */
	@ResponseBody
	@RequestMapping(value="/record",method=RequestMethod.POST)
	public Map<Object,Object> csGetRecordList(@RequestParam("page")int page,@RequestParam("rows")int rows,@RequestParam("communityId")String communityId,@RequestParam("communityName")String communityName,@RequestParam("proprietorPhone")String proprietorPhone,@RequestParam("roomNumber")String roomNumber,@RequestParam("repairStatus")int repairStatus){
		RepairResponse repairResponse = null;
		if(rows == 0){
			rows = 10;
		}
		//TODO
//		int id = 0;
//		if(communityId == ""){
//			id = 2;
//		}else{
//			id = Integer.parseInt(communityId);
//		}
		Map<String,Object> map = new HashMap<>();
		map.put("communityId", communityId);
		map.put("start", (page-1)*rows);
		map.put("rows", rows);
		map.put("communityName", communityName);
		map.put("proprietorPhone", proprietorPhone);
		map.put("roomNumber", roomNumber);
		map.put("repairStatus", repairStatus);
		List<RepairRecord> list = recordManageService.csGetRepairs(map);
		JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.registerJsonValueProcessor(Date.class, new JsonDateValueProcessor());
		Map<Object,Object> jo = new HashMap();
		JSONArray json = JSONArray.fromObject(list,jsonConfig);
		//jo.put("total", recordManageService.getCountBycommumityId(id));
		jo.put("total",list.size());
		jo.put("rows", json);
		return jo;
	}
	
	//TODO
	//客服转发给工作人员
	
	//将图片名封装成图片路径
	public RepairRecord getImgUrls(RepairRecord repairRecord,HttpServletRequest request){
		String path =request.getContextPath();
		String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+File.separator+"upload"+File.separator+"image"+File.separator+"repairimg";
		String img = repairRecord.getRepairImages();
		if(img != null && img != "" && img.length() != 0){
		if(img.indexOf(",") != -1){
			String[] imgUrl = img.split(",");
//			for(int i = 0;i<imgUrl.length;i++){
//				imgUrl[i] = basePath + File.separator + imgUrl[i];
//				
//			}
			repairRecord.setImgurls(imgUrl);
		}else{
//			img = basePath + File.separator + img;
			String[] imgArray = new String[1];
			imgArray[0] = img;
			repairRecord.setImgurls(imgArray);
		}
		}
		return repairRecord;
		
	}
	/**
	 * 
	 * @param repairNumber
	 * @param result
	 * @return
	 * Description:物管审核通过后自动把结果推送给业主
	 * @author:HAO
	 */
	@ResponseBody
	@RequestMapping(value="/audite",method=RequestMethod.POST)
	public RepairResponse pushResultToProprietor(String repairNumber,int result,HttpServletRequest request){
		RepairResponse repairResponse = null;
		Map<String,Object> map = new HashMap<>();
		map.put("repairNumber", repairNumber);
		map.put("result", result);
		if(recordManageService.getRepairRecordByNumber(repairNumber)==null){
			return new RepairResponse(-1,"报单号错误");
		}
		if(result==3 || result ==4){
			RepairRecord repairRecord = recordManageService.pushResultToProprietor(map);
			repairRecord = getImgUrls(repairRecord,request);
			if(result==3){
				repairResponse = new RepairResponse(1,repairRecord,"审核通过");
			}else{
				repairResponse = new RepairResponse(0,repairRecord,"审核不通过");
			}
		}else{
			repairResponse = new RepairResponse(-2,"状态码错误");
		}
		return repairResponse;
	}
	
	/**
	 * 
	 * @param communityId
	 * @param proprietorPhone
	 * @return
	 * Description:用户获取报单信息
	 * @author:HAO
	 */
	@RequestMapping(value="/getRecordByUser",method=RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public RepairResponse getRepairRecordByUser(int communityId,String proprietorPhone){
		Map<String,Object> map = new HashMap<>();
		RepairResponse response = null;
		map.put("communityId",communityId);
		map.put("proprietorPhone", proprietorPhone);
		List<RepairRecord> list = recordManageService.getRecordByCommunityIdAndPhone(map);
		if(list.size()!=0){
			response = new RepairResponse(1,list,"获取信息成功");
		}else{
			response = new RepairResponse(0,"","无报单");
		}
		return response;
	}
	
	/**
	 * 
	 * @param page
	 * @param rows
	 * @return
	 * Description:后台获取维修数量统计
	 * @author:HAO
	 */
	@RequestMapping(value="/count",method=RequestMethod.POST)
	@ResponseBody
	public Map<Object,Object> getRecordCount(@RequestParam("page")int page,@RequestParam("rows")int rows,@RequestParam("communityName")String communityName){
		Map<String,Object> map = new HashMap<>();
		map.put("start", (page-1)*rows);
		map.put("rows", rows);
		map.put("communityName", communityName);
		List<Integer> ids = recordManageService.getCommunityIds(map);
		List<RepairRecordVO> volist = new ArrayList<>();
		for(int id : ids){
			String name = recordManageService.getNameByCommunityId(id);
			int total = recordManageService.getAllCountByCommunityId(id);
			Map<String,Object> waitForWorker = new HashMap<>();
			waitForWorker.put("communityId", id);
			waitForWorker.put("repairStatus", 0);
			int waitForWorkerCount = recordManageService.getCountByCommunityIdAndStatus(waitForWorker);
			
			Map<String,Object> sendWorker = new HashMap<>();
			sendWorker.put("communityId", id);
			sendWorker.put("repairStatus", 1);
			int sendWorkerCount = recordManageService.getCountByCommunityIdAndStatus(sendWorker);
			
			Map<String,Object> complete = new HashMap<>();
			complete.put("communityId", id);
			complete.put("repairStatus", 2);
			int completeCount = recordManageService.getCountByCommunityIdAndStatus(complete);
			
			Map<String,Object> audited = new HashMap<>();
			audited.put("communityId", id);
			audited.put("repairStatus", 3);
			int auditedCount = recordManageService.getCountByCommunityIdAndStatus(audited);
			
			RepairRecordVO recordVO = new RepairRecordVO(name,total,waitForWorkerCount,sendWorkerCount,completeCount,auditedCount);
			volist.add(recordVO);
			
		}
		Map<Object,Object> jo = new HashMap();
		JSONArray json = JSONArray.fromObject(volist);
		jo.put("total", ids.size());
		jo.put("rows", json);
		
		return jo;
	}
	
	

}
