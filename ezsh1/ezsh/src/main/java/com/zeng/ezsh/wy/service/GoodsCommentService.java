package com.zeng.ezsh.wy.service;

import java.util.List;

import com.zeng.ezsh.wy.entity.GoodsComment;

public interface GoodsCommentService {
	/*添加商品评价回复*/
    public int addComment(GoodsComment record);
    
    /*获取商品评价的回复集合*/
    public List<GoodsComment> selectGoodsCommentList(GoodsComment record);
}
