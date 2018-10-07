package com.zeng.ezsh.wy.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.zeng.ezsh.wy.dao.NoticeMapper;
import com.zeng.ezsh.wy.entity.Notice;
import com.zeng.ezsh.wy.service.NoticeService;
@Service
public class NoticeServiceImpl implements NoticeService {
	@Resource
	NoticeMapper noticeMapper;
	/**
	 * @author qwc
	 * 2017年10月26日下午9:09:56
	 * @param record
	 * @return 添加公告
	 */
	@Override
	public int insert(Notice record) {
		// TODO Auto-generated method stub
		return noticeMapper.insert(record);
	}
	
	/**
	 * @author qwc
	 * 2017年10月26日下午9:10:03
	 * @param record
	 * @return 删除公告
	 */
	@Override
	public int deleteByPrimaryKey(Notice record) {
		// TODO Auto-generated method stub
		return noticeMapper.deleteByPrimaryKey(record);
	}
	
	/**
	 * @author qwc
	 * 2017年10月26日下午9:10:21
	 * @param record
	 * @return 获取公告
	 */
	@Override
	public List<Notice> selectByPrimaryKey(Notice record) {
		// TODO Auto-generated method stub
		return noticeMapper.selectByPrimaryKey(record);
	}
	
	/**
	 * @author qwc
	 * 2017年10月26日下午9:10:32
	 * @param record
	 * @return 更新公告
	 */
	@Override
	public int updateByPrimaryKeySelective(Notice record) {
		// TODO Auto-generated method stub
		return noticeMapper.updateByPrimaryKeySelective(record);
	}

}
