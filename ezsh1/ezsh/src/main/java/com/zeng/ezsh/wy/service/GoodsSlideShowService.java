package com.zeng.ezsh.wy.service;

import java.util.List;
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

import com.zeng.ezsh.wy.entity.GoodsSlideShow;
public interface GoodsSlideShowService {
	 /*添加商城首页轮播图（后台）*/
    /*public int addSlideShowImage(int managerId,MultipartFile[] filesSlideShow);*/
	
	/*通过条件获取轮播图集合(后台)*/
	public List<GoodsSlideShow> getGoodsSlideShowsA(GoodsSlideShow record);
    
    /*通过条件获取轮播图集合(前台 )*/
	public List<GoodsSlideShow> getGoodsSlideShowsByParam(Map<String,Object> param);
	
	/*添加商城首页轮播图（后台）*/
	public int  insertSelective(GoodsSlideShow record);
	
	/*删除商城首页轮播图（后台）*/
	public int deleteShowImageUrl(GoodsSlideShow record);
	
	/*更新商城首页轮播图（后台）*/
	public int updateShowImageUrl(GoodsSlideShow record);
}
