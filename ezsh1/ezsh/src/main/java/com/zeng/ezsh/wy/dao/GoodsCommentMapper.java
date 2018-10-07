package com.zeng.ezsh.wy.dao;

import com.zeng.ezsh.wy.entity.GoodsComment;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface GoodsCommentMapper {
    int deleteByPrimaryKey(Integer gCommentId);
    
    /*添加商品评价回复*/
    int addComment(GoodsComment record);
    
    /*获取商品评价的回复集合*/
    List<GoodsComment> selectGoodsCommentList(GoodsComment record);
    
    int insertSelective(GoodsComment record);

    

    int updateByPrimaryKeySelective(GoodsComment record);

    int updateByPrimaryKey(GoodsComment record);
}