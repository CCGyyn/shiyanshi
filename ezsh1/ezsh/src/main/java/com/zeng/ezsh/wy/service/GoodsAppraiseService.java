package com.zeng.ezsh.wy.service;

import java.util.List;

import com.zeng.ezsh.wy.entity.GoodsAppraise;
import com.zeng.ezsh.wy.entity.GoodsOrderDetails;

public interface GoodsAppraiseService {
	/*添加商品评价*/
    public int addGoodsAppraise(GoodsAppraise record);
    
    /*获取商品评价列表*/
    public List<GoodsAppraise> getGoodsAppraiseList(GoodsAppraise record);
    
    /*操作评价（添加以及更新）*/
    public int insertUpdateAppraise(GoodsAppraise goodsAppraiseModel, GoodsOrderDetails goodsOrderDetails);
}
