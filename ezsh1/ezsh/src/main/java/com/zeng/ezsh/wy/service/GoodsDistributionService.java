package com.zeng.ezsh.wy.service;

import java.util.List;

import com.zeng.ezsh.wy.entity.GoodsDistribution;

public interface GoodsDistributionService {
	/*根据配送方式ID获取配送方式集合*/
	public List<GoodsDistribution> getDistributionListById(String[] gDistributionIdArrayStrings);
}
