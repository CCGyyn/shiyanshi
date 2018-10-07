package com.zeng.ezsh.wy.action;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;
import net.sf.json.JSONArray;

import org.apache.http.HttpResponse;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.zeng.ezsh.wy.entity.HkItem;
import com.zeng.ezsh.wy.entity.HkType;
import com.zeng.ezsh.wy.entity.HkTypeVo;
import com.zeng.ezsh.wy.entity.HouseKeepingResult;
import com.zeng.ezsh.wy.entity.Housekeeping;
import com.zeng.ezsh.wy.service.HousekeepingService;

@Controller
@RequestMapping("/housekeeping")
public class HousekeepingAction {
	
	@Resource
	private HousekeepingService housekeepingservice;
	
	//家政服务公司注册接口
	@RequestMapping(value="/register",method=RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public HouseKeepingResult register1(Housekeeping housekeeping,@RequestParam("imgs")MultipartFile[] imgs,HttpServletRequest request){
		HouseKeepingResult result = null;
		housekeeping.setCommunityId(1);//TODO
		housekeeping.setImg(uploadFiles(imgs,request));//封装图片路径
		Map<String,Object> map = new HashMap<>();
		map.put("hkPhone", housekeeping.getHkPhone());
		map.put("communityId", housekeeping.getCommunityId());
		if(housekeepingservice.insertApplyRecord(housekeeping)==1){
			int id = housekeepingservice.getId(map);//获取公司id
			housekeeping.setHkId(id);
			if(housekeepingservice.updateByImg(housekeeping)==1){//更新图片路径
				result = new HouseKeepingResult(1,"操作成功");
			}else{
				result = new HouseKeepingResult(0,"操作失败");
			}
		}else{
			result = new HouseKeepingResult(0,"操作失败");
		}
		return result;
	}
	
			//封装图片路径，可直接访问
			private String[] getImgUrl(String img,HttpServletRequest request){
				//访问文件路径
				String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort();
				if(img.indexOf(",") != -1){
					//有多张图片情况
					String[] imgUrl = img.split(",");
					for(int i=0;i<imgUrl.length;i++){
						imgUrl[i] = basePath+imgUrl[i];
					}
					return imgUrl;
				}else{
					//只有一张图片情况
					String[] imgArray = new String[1];
					imgArray[0] = basePath+img;
					return imgArray;
				}
			}
	
	//对数据审核,1表示通过，2表示拒绝
	@RequestMapping("/audite")
	@ResponseBody
	public Integer audite( int result, int id,Model model,HttpServletResponse response){
		response.setCharacterEncoding("UTF-8"); 
		Map<String,Object> map = new HashMap<>();
		map.put("result", result);
		map.put("id", id);
		int result1 = housekeepingservice.audite(map);
		if(result1 == 1){
			return 1;
		}else{
			return 0;
		}
		
	}
	//返回需要审核的数据列表
	@RequestMapping("/getList")
	@ResponseBody
	public Map<Object,Object> getList(Model model,@RequestParam("page") Integer page,Integer rows){
		Map<String,Object> map = new HashMap();
		if(page==null){
			page = 1;
		}
		page = (page-1)*10;
		if(rows==null){
			rows=10;
		}
		map.put("start", page);
		map.put("pagesize", rows);
		List<Housekeeping> list = housekeepingservice.selectNoAuditedByPage(map);
		Map<Object,Object> jo = new HashMap<>();
		JSONArray json = JSONArray.fromObject(list);
		int total = housekeepingservice.selectNoAuditedCount();
		jo.put("total", total);
		jo.put("rows", json);
		return jo;
	}
	//返回审核页面
	@RequestMapping("/auditeList")
	public String test2(){
		
		return "housekeeping/auditing";
	}
	
	
	//app获取家政服务公司数据接口
	@RequestMapping(value="/housekeepinglist",method=RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public HouseKeepingResult<List<Housekeeping>> getHouseKeepingList(@RequestParam("communityId") int communityId,@RequestParam("page") Integer page, @RequestParam("rows")Integer rows,HttpServletRequest request){
		HouseKeepingResult result = null;
		Map<String,Object> map = new HashMap();
		if(page==null){
			page = 1;
		}
		page = (page-1)*10;
		if(rows==null){
			rows=10;
		}
		map.put("start", page);
		map.put("pagesize", rows);
		map.put("communityId", communityId);//小区id
		List<Housekeeping> list = housekeepingservice.selectAuditedList(map);//分页查询
		if(list.size()!=0){
			for(int i=0;i<list.size();i++){
				Housekeeping hk = list.get(i);
				String img = hk.getImg();
				if(img != null && img != "" && img.length() != 0){//分别封装公司图标路径
					String[] imgUrl = getImgUrl(img,request);
					hk.setImgUrl(imgUrl);
				}
			}
			result = new HouseKeepingResult(1,list,"获取成功");
		}else{
			result = new HouseKeepingResult(0,"无数据");
		}
			return result;
		
	}
	
	//app获取具体家政服务公司服务项目
		@RequestMapping(value="/getHkItem",method=RequestMethod.GET, produces = "application/json")
		@ResponseBody
		public HouseKeepingResult<List<HkType>> getHouseKeepingList(int hkId){
			HouseKeepingResult result = null;
			Map<String,Object> map = new HashMap();
			//List<Housekeeping> list = housekeepingservice.selectAuditedList(map);
			List<HkType> list = housekeepingservice.getHkItems(hkId);//获取服务类型
			if(list.size()!=0){
				for(int i=0;i<list.size();i++){
					HkType hkType = list.get(i);
					int typeId = hkType.getTypeId();
					hkType.setHkItemList(housekeepingservice.getItemByHkType(typeId));//根据服务类型id获取服务项目
				}
				result = new HouseKeepingResult(1,list,"获取成功");
			}else{
				result = new HouseKeepingResult(0,"无数据");
			}
				return result;
			
		}
	
	/**
	 * 
	 * @param hkType
	 * @return
	 * Description:添加服务类型
	 * @author:HAO
	 */
	@RequestMapping(value="addHkType",method=RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public HouseKeepingResult addHkType(HkType hkType){
		HouseKeepingResult result = null;
		try{
			if(housekeepingservice.addHkType(hkType)==1){
				result = new HouseKeepingResult(1,"操作成功");
			}else{
				result = new HouseKeepingResult(0,"操作失败");
			}
		}catch(Exception e){
			e.printStackTrace();
			result = new HouseKeepingResult(-1,"内部错误");
		}
		return result;
	}
	
	
	/**
	 * 
	 * @param hkType
	 * @return
	 * Description:修改服务类型
	 * @author:HAO
	 */
	@RequestMapping(value="editHkType",method=RequestMethod.POST)
	@ResponseBody
	public HouseKeepingResult editHkType(HkType hkType){
		HouseKeepingResult result = null;
		try{
			if(housekeepingservice.editHkType(hkType)==1){
				result = new HouseKeepingResult(1,"操作成功");
			}else{
				result = new HouseKeepingResult(0,"操作失败");
			}
		}catch(Exception e){
			e.printStackTrace();
			result = new HouseKeepingResult(-1,"内部错误");
		}
		return result;
	}
	
	/**
	 * 
	 * @param hkType
	 * @return
	 * Description:删除服务类型
	 * @author:HAO
	 */
	@RequestMapping(value="deleteHkType",method=RequestMethod.POST)
	@ResponseBody
	public HouseKeepingResult deleteHkType(int id){
		HouseKeepingResult result = null;
		try{
			if(housekeepingservice.deleteHkType(id)==1){
				result = new HouseKeepingResult(1,"操作成功");
			}else{
				result = new HouseKeepingResult(0,"操作失败");
			}
		}catch(Exception e){
			e.printStackTrace();
			result = new HouseKeepingResult(-1,"内部错误");
		}
		return result;
	}
	
	/**
	 * 
	 * @param hkType
	 * @return
	 * Description:增加服务项目
	 * @author:HAO
	 */
	@RequestMapping(value="addHkItem",method=RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public HouseKeepingResult addHkItem(HkItem hkItem){
		HouseKeepingResult result = null;
		if(hkItem.getTypeId() != 0 && hkItem.getItem() != "" && hkItem.getHkId() != 0 && hkItem.getPrice() != null){
			if(housekeepingservice.addHkItem(hkItem)==1){
				result = new HouseKeepingResult(1,"操作成功");
			}else{
				result = new HouseKeepingResult(0,"操作失败");
			}
		}else{
			result = new HouseKeepingResult(0,"请填写完整数据");
		}
		return result;
	}
	
	
	public String uploadFiles(MultipartFile[] files,HttpServletRequest request){
		String imgUploadUrl = "/ezsh/"+"upload/images/housekeeping/";
		StringBuilder sb = new StringBuilder();
		if(files != null && files.length > 0){
			try{
				for(int i = 0; i < files.length; i ++){
					MultipartFile file = files[i];
					saveFile(file,request);
					if(file == files[files.length-1]){
						sb.append(imgUploadUrl + file.getOriginalFilename());
					}else{
						sb.append(imgUploadUrl + file.getOriginalFilename()+",");
					}
				}
				// TODO 定义异常
			}catch(Exception e){
				throw e;
		}
			return sb.toString();
		}else{
			return null;
		}
		
	}
	//保存文件
	public boolean saveFile(MultipartFile file,HttpServletRequest request) {  
		String bootUrl="/usr";//保存的根目录
        String uploadUrl="/ezsh/"+"upload/images/housekeeping";
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
	
	@RequestMapping(value="deleteHkItem",method=RequestMethod.POST)
	@ResponseBody
	public HouseKeepingResult deleteHkItem(String id,String typeId){
		String bootUrl="/usr";//保存的根目录
		HouseKeepingResult result = null;
		Map<String,Object> map = new HashMap<>();
		map.put("id", Integer.parseInt(id));
		map.put("typeId", Integer.parseInt(typeId));
		HkItem hkItem = housekeepingservice.getHkItemById(map);
		if(hkItem != null){
			if(hkItem.getImg()!=null){
				String img = bootUrl + hkItem.getImg();
				if(new File(img).delete()==true){
					if(housekeepingservice.deleteHkItem(Integer.parseInt(id))==1){
						result = new HouseKeepingResult(1,"删除成功");
					}else{
						result = new HouseKeepingResult(0,"删除失败");
					}
				}else{
				 result = new HouseKeepingResult(0,"删除失败");
				}
			}else{
				if(housekeepingservice.deleteHkItem(Integer.parseInt(id))==1){
					result = new HouseKeepingResult(1,"删除成功");
					}else{
						result = new HouseKeepingResult(0,"删除失败");
					}
			}}else{
				result = new HouseKeepingResult(0,"删除失败,无该项目");
			}
		return result;
		
	}
	/**
	 * 
	 * @param page
	 * @param rows
	 * @return
	 * Description:后台获取服务项目列表
	 * @author:HAO
	 */
	@RequestMapping(value="getItemList",method=RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public Map<Object,Object> getHkItemList(String page,String rows){
		Map<String,Object> map = new HashMap<>();
		map.put("start", (Integer.parseInt(page)-1)*Integer.parseInt(rows));
		map.put("pagesize", Integer.parseInt(rows));
		List<HkItem> list = housekeepingservice.getItemList(map);
		for(HkItem hkItem:list){
			hkItem.setHkName(housekeepingservice.getHkNameById(hkItem.getHkId()));
			hkItem.setHkType(housekeepingservice.getTypeById(hkItem.getTypeId()));
		}
		Map<Object,Object> jo = new HashMap<>();
		JSONArray json = JSONArray.fromObject(list);
		jo.put("total", list.size());
		jo.put("rows", json);
		return jo;
		
	}
	
	/**
	 * @param hkId
	 * @return
	 * Description:后台获取服务项目列表
	 * @author:HAO
	 */
	@RequestMapping(value="getTypeList",method=RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public JSONArray getHkItemList(int hkId){
		List<HkTypeVo> list = housekeepingservice.getHkTypes(hkId);
		//Map<Object,Object> jo = new HashMap<>();
//		for(int i=0;i<list.size();i++){
//			HkType type = list.get(i);
//			jo.put("typeId", type.getTypeId());
//			jo.put("hkType", type.getHkType());
//		}
		JSONArray json = JSONArray.fromObject(list);
		return json;
		
	}
}
