package com.zeng.ezsh.wy.dao;

import java.util.List;

import com.zeng.ezsh.wy.entity.GoodsDistribution;

public interface GoodsDistributionMapper {
	/*根据配送方式ID获取配送方式集合*/
	List<GoodsDistribution> getDistributionListById(String[] gDistributionIdArrayStrings);
	
    int deleteByPrimaryKey(Integer gDistributionId);

    int insert(GoodsDistribution record);

    int insertSelective(GoodsDistribution record);

    GoodsDistribution selectByPrimaryKey(Integer gDistributionId);

    int updateByPrimaryKeySelective(GoodsDistribution record);

    int updateByPrimaryKey(GoodsDistribution record);
}