package com.zeng.ezsh.wy.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zeng.ezsh.wy.dao.HireMapper;
import com.zeng.ezsh.wy.entity.Hire;
import com.zeng.ezsh.wy.entity.Page;
import com.zeng.ezsh.wy.service.HireService;

@Service
public class HireServiceImpl implements HireService {
	@Autowired
	private HireMapper hiremapper;

	// 根据条件分页查询
	public Page<Hire> SelectByPage(Page<Hire> page) {
		List<Hire> list = hiremapper.selectHireByPage(page);
		int count = hiremapper.getCount(page);
		Map<String, Object> map = page.getPageMap();
		map.put("list", list);
		map.put("count", count);
		return page;

	}

	@Override
	public int getCount(Page<Hire> page) {
		// TODO Auto-generated method stub
		return 0;
	}
//添加租聘信息
	@Override
	public int insert(List<Hire>hire) {
		
		return hiremapper.insert(hire);
	}
//删除租聘信息
	@Override
	public int deleteByPrimaryKey(int i) {
		
		return hiremapper.deleteByPrimaryKey(i);
	}

	@Override
	public int updataHire(Hire hire) {
		
		return hiremapper.updateByPrimaryKey(hire);
	}

}
