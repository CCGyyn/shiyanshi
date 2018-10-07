package com.zeng.ezsh.wy.admin.action;

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

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zeng.ezsh.wy.entity.GoodsSlideShow;
import com.zeng.ezsh.wy.entity.Grid;
import com.zeng.ezsh.wy.service.GoodsSlideShowService;
import com.zeng.ezsh.wy.utils.AccessTokenUtil;
import com.zeng.ezsh.wy.utils.DateUtil;
import com.zeng.ezsh.wy.utils.RetJsonUtil;

@Controller
@RequestMapping(value= "slideShowA")
public class GoodsSlideShowAdmin {
	@Resource
	GoodsSlideShowService goodsSlideShowService;
	
	/**
	 * @author qwc
	 * 2017年8月21日下午9:53:30
	 * @param request
	 * @param response
	 * @param filesSlideShow void
	 * 添加商城首页轮播图
	 * @throws IOException 
	 */
	@RequestMapping("adSlideShowImage")
	public void addSlideShowImage(HttpServletRequest request, HttpServletResponse response,
			@RequestParam("goodsSlideShow") MultipartFile fileSlideShow, GoodsSlideShow record) throws IOException{
		PrintWriter out=response.getWriter();
		RetJsonUtil retJsonUtil = new RetJsonUtil();//创建json返回对象
		
		//判断文件是否为空
		if(fileSlideShow!=null && !fileSlideShow.isEmpty()){
			record.setSlideShowImgUrl(fileUpload(fileSlideShow,"goodsIndexSlideShow",record.getpManagerId()));
		}
		
		//执行添加
		int addStatus = goodsSlideShowService.insertSelective(record);
		if(addStatus > 0) {
			retJsonUtil.setMessage("添加成功");
			retJsonUtil.setStatus("1");
		} else {
			retJsonUtil.setMessage("添加成功");
			retJsonUtil.setStatus("0");
		}
		out.write(retJsonUtil.getRetJsonM());
	}
	
	/**
	 * @author qwc
	 * 2017年11月18日下午10:07:28
	 * @param request
	 * @param response
	 * @param startPage
	 * @param pageSize
	 * @param goodsSlideShow
	 * @throws IOException 
	 * void 后台获取商城首页轮播图列表
	 */
	@RequestMapping("select")
	public void getSlideShowImg(HttpServletRequest request,HttpServletResponse response,
			@RequestParam(value="page",required=true,defaultValue="1") Integer startPage,
			@RequestParam(value="rows",required=false,defaultValue="10") Integer pageSize,
			GoodsSlideShow goodsSlideShow) throws IOException{
		PrintWriter out=response.getWriter();
		RetJsonUtil retJsonUtil = new RetJsonUtil();//创建json返回对象
		List<GoodsSlideShow> goodsSlideShowsList=new ArrayList<GoodsSlideShow>();
		
		//分页查询
		PageHelper.startPage(startPage,pageSize);
		goodsSlideShowsList=goodsSlideShowService.getGoodsSlideShowsA(goodsSlideShow);
		PageInfo<GoodsSlideShow> page=new PageInfo<GoodsSlideShow>(goodsSlideShowsList);
		
		retJsonUtil.setList(goodsSlideShowsList);
		retJsonUtil.setTotal(page.getTotal());
		out.write(retJsonUtil.getRetJsonEasyGrid());
	}
	
	/**
	 * @author qwc
	 * 2017年11月18日下午9:22:47
	 * @param request
	 * @param response
	 * @param record
	 * @throws IOException 
	 * void 删除商品首页轮播图
	 */
	@RequestMapping(value = "delete")
	public void deleteGrid(HttpServletRequest request,HttpServletResponse response,GoodsSlideShow record) throws IOException{
		PrintWriter out=response.getWriter();
		RetJsonUtil retJsonUtil = new RetJsonUtil();//创建json返回对象
		int status=goodsSlideShowService.deleteShowImageUrl(record);
		if(status>0){
			retJsonUtil.setMessage("删除成功");
			retJsonUtil.setStatus("1");
		}else{
			retJsonUtil.setMessage("删除成功");
			retJsonUtil.setStatus("0");
		}
		out.write(retJsonUtil.getRetJsonM());
	}
	
	/**
	 * @author qwc
	 * 2017年11月18日下午10:08:19
	 * @param request
	 * @param response
	 * @param fileSlideShow
	 * @param record
	 * @throws IOException 
	 * void 更新商城首页轮播图列表
	 */
	@RequestMapping("update")
	public void updateSlideShowImage(HttpServletRequest request, HttpServletResponse response,
			@RequestParam("goodsSlideShow") MultipartFile fileSlideShow, GoodsSlideShow record) throws IOException{
		PrintWriter out=response.getWriter();
		RetJsonUtil retJsonUtil = new RetJsonUtil();//创建json返回对象
		
		//判断文件是否为空
		if(fileSlideShow!=null && !fileSlideShow.isEmpty()){
			record.setSlideShowImgUrl(fileUpload(fileSlideShow,"goodsIndexSlideShow",record.getpManagerId()));
		} 
		
		//执行更新
		int status = goodsSlideShowService.updateShowImageUrl(record);
		if(status > 0) {
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
	 * 2017年8月21日下午10:04:01
	 * @param files
	 * @param folderName 文件夹名
	 * @param managerId 小区ID
	 * @return String
	 */
	public String fileUpload(MultipartFile file,String folderName,int managerId) { 
		String saveTosqlurl=null;//保存到数据库中的路径
		String bootUrl="/usr";//保存的根目录
		String uploadUrl="/ezsh/"+"upload/images/"+folderName+"/"+managerId+"/";
		File dir = new File(bootUrl+uploadUrl);
		if(!dir.exists()){
			dir.mkdirs();
		}
		//判断file数组不能为空并且长度大于0  
		if(file!=null){  
	    //循环获取file数组中得文件  
        try {
        	saveTosqlurl=uploadUrl+DateUtil.dateToStr(new Date(),DateUtil.DATE_TIME_NO_SLASH)+".jpg";
            file.transferTo(new File(bootUrl+saveTosqlurl));//保存到服务器中
            } catch (IOException e) {
                e.printStackTrace();
            }
	    }
		return saveTosqlurl;  
	}
}
