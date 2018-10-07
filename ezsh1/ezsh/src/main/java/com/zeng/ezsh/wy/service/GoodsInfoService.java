package com.zeng.ezsh.wy.service;

import java.util.List;
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

import com.zeng.ezsh.wy.entity.Goods;
import com.zeng.ezsh.wy.entity.GoodsInfo;

public interface GoodsInfoService {
	/*添加商品基本信息*/
    public int addGoodsInfo(GoodsInfo record);
    
    /*更新商品基本信息*/
    public int updateByPrimaryKeySelective(GoodsInfo record);
    
    /*根据条件获取商品集合信息*/
    public List<GoodsInfo> getGoodsInfoListByParam(Map<String,Object> param);
    
    /*通过条件获取商品基本信息列表（用于后台）*/
    public List<GoodsInfo> getGoodsInfoListByParamToAdmin(Map<String,Object> param);
    
    /*根据商品的基本信息ID获取商品的详细信息*/
    public GoodsInfo getGoodsDetailsByGoodsInfoId(Integer goodsInfoId);
    
    /*获取每日推荐商品基本信息列表*/
    /*public List<GoodsInfo> getPerDayRecommentGoodsInfoList(Map<String,Object> param);*/
}
