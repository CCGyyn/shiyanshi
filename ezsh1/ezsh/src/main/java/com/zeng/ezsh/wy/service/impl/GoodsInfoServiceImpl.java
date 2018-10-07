package com.zeng.ezsh.wy.service.impl;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.zeng.ezsh.wy.dao.GoodsInfoMapper;
import com.zeng.ezsh.wy.dao.GoodsSlideShowMapper;
import com.zeng.ezsh.wy.entity.Goods;
import com.zeng.ezsh.wy.entity.GoodsInfo;
import com.zeng.ezsh.wy.entity.GoodsSlideShow;
import com.zeng.ezsh.wy.service.GoodsInfoService;
import com.zeng.ezsh.wy.utils.DateUtil;
@Service
public class GoodsInfoServiceImpl implements GoodsInfoService {
	@Resource
	GoodsInfoMapper goodsInfoMapperDao;
	@Resource
	GoodsSlideShowMapper goodsSlideShowMapperDao;
	
	/**
	 * @author qwc
	 * 2017年7月27日下午9:45:29
	 * @param record
	 * @return
	 * 添加商品基本信息
	 */
	@Override
	public int addGoodsInfo(GoodsInfo record) {
		// TODO Auto-generated method stub
		return goodsInfoMapperDao.addGoodsInfo(record);
	}
	
	/**
	 * @author qwc
	 * 2017年7月28日下午3:15:53
	 * @param record
	 * @return
	 * 更新商品基本信息
	 */
	@Override
	public int updateByPrimaryKeySelective(GoodsInfo record) {
		// TODO Auto-generated method stub
		return goodsInfoMapperDao.updateByPrimaryKeySelective(record);
	}
	
	/**
	 * @author qwc
	 * 2017年7月28日下午8:31:58
	 * @param param
	 * @return
	 * 获取商品集合信息
	 */
	@Override
	public List<GoodsInfo> getGoodsInfoListByParam(Map<String, Object> param) {
		// TODO Auto-generated method stub
		return goodsInfoMapperDao.getGoodsInfoListByParam(param);
	}
	
	/**
	 * @author qwc
	 * 2017年9月14日下午9:13:07
	 * @param param
	 * @return
	 * 条件获取商品集合信息（用于后台）
	 */
	@Override
	public List<GoodsInfo> getGoodsInfoListByParamToAdmin(
			Map<String, Object> param) {
		// TODO Auto-generated method stub
		return goodsInfoMapperDao.getGoodsInfoListByParamToAdmin(param);
	}
	
	/**
	 * @author qwc
	 * 2017年8月17日下午8:15:19
	 * @param goodsInfoId
	 * @return
	 * 根据商品的基本信息ID获取商品的详细信息
	 */
	@Override
	public GoodsInfo getGoodsDetailsByGoodsInfoId(Integer goodsInfoId) {
		// TODO Auto-generated method stub
		return goodsInfoMapperDao.getGoodsDetailsByGoodsInfoId(goodsInfoId);
	}

}
