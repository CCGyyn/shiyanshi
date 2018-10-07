package com.zeng.ezsh.wy.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.zeng.ezsh.wy.entity.GoodsClassfy;

public interface GoodsClassifyService {
	/*获取商品分类集合*/
    public List<GoodsClassfy> selectByPrimaryKey(int classfyId);
}
