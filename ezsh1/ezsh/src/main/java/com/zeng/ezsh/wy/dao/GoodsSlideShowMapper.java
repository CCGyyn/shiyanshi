package com.zeng.ezsh.wy.dao;

import com.zeng.ezsh.wy.entity.GoodsSlideShow;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

public interface GoodsSlideShowMapper {
	/*添加商城轮播图*/
	/*int addSlideShowImg(List<GoodsSlideShow> record);*/
	
	/*通过条件获取轮播图集合（前台）*/
	List<GoodsSlideShow> getGoodsSlideShowsByParam(Map<String,Object> param);
	
	/*通过条件获取轮播图集合（后台）*/
	List<GoodsSlideShow> getGoodsSlideShowsA(GoodsSlideShow record);
	
	/*添加商城首页轮播图（后台）*/
	int  insertSelective(GoodsSlideShow record);
	
	/*删除商城首页轮播图（后台）*/
	int deleteShowImageUrl(GoodsSlideShow record);
	
	/*更新商城首页轮播图（后台）*/
	int updateShowImageUrl(GoodsSlideShow record);
    /*int deleteByPrimaryKey(Integer goodsSlideShowId);

    int insertSelective(GoodsSlideShow record);

    int updateByPrimaryKeySelective(GoodsSlideShow record);

    int updateByPrimaryKey(GoodsSlideShow record);*/
}