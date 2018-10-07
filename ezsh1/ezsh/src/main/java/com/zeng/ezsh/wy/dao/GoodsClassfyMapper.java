package com.zeng.ezsh.wy.dao;

import com.zeng.ezsh.wy.entity.GoodsClassfy;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface GoodsClassfyMapper {
	/*获取商品所属分类详细信息*/
	GoodsClassfy getGoodsClassfyInfoById(String classfyId);
	
    /*获取商品分类集合*/
    List<GoodsClassfy> selectByPrimaryKey(int classfyId);
    
    int deleteByPrimaryKey(String classfyId);

    int insert(GoodsClassfy record);

    int insertSelective(GoodsClassfy record);

    int updateByPrimaryKeySelective(GoodsClassfy record);

    int updateByPrimaryKey(GoodsClassfy record);
}