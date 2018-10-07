package com.zeng.ezsh.wy.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.zeng.ezsh.wy.dao.NewsMapper;
import com.zeng.ezsh.wy.entity.News;
import com.zeng.ezsh.wy.entity.Notice;
import com.zeng.ezsh.wy.service.NewsService;
import com.zeng.ezsh.wy.service.NoticeService;
@Service
public class NewsServiceImpl implements NewsService {
	@Resource
	NewsMapper newsMapper;
	
	/**
	 * @author qwc
	 * 2017年10月27日下午4:47:32
	 * @param record
	 * @return 添加消息
	 */
	@Override
	public int insert(News record) {
		// TODO Auto-generated method stub
		return newsMapper.insert(record);
	}
	
	/**
	 * @author qwc
	 * 2017年10月27日下午4:47:43
	 * @param record
	 * @return 删除消息
	 */
	@Override
	public int deleteByPrimaryKey(News record) {
		// TODO Auto-generated method stub
		return newsMapper.deleteByPrimaryKey(record);
	}

	/**
	 * @author qwc
	 * 2017年10月27日下午4:47:56
	 * @param record
	 * @return 获取消息
	 */
	@Override
	public List<News> selectByPrimaryKey(News record) {
		// TODO Auto-generated method stub
		return newsMapper.selectByPrimaryKey(record);
	}
	
	/**
	 * @author qwc
	 * 2017年10月27日下午4:48:08
	 * @param record
	 * @return 更新消息
	 */
	@Override
	public int updateByPrimaryKeySelective(News record) {
		// TODO Auto-generated method stub
		return newsMapper.updateByPrimaryKeySelective(record);
	}
	
	/**
	 * 获取个人信息移动端
	 */
	@Override
	public List<News> selectByPrimaryKeyM(News record) {
		// TODO Auto-generated method stub
		return newsMapper.selectByPrimaryKeyM(record);
	}
	
}
