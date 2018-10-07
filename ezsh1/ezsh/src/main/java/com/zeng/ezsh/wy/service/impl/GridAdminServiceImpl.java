package com.zeng.ezsh.wy.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.zeng.ezsh.wy.dao.GridMapper;
import com.zeng.ezsh.wy.entity.Grid;
import com.zeng.ezsh.wy.entity.GridRecord;
import com.zeng.ezsh.wy.service.GridAdminService;
@Service
public class GridAdminServiceImpl implements GridAdminService {
	@Resource
	GridMapper gridMapperDao;

	/**
	 * @description 添加抄表类别
	 */
	@Override
	public int insert(Grid record) {
		return gridMapperDao.insert(record);
	}

	/**
	 * @description 检测抄表类型是否存在
	 */
	@Override
	public int checkIsExist(Grid record) {
		return gridMapperDao.checkIsExist(record);
	}

	/**
	 * @description 修改抄表类别
	 */
	@Override
	public int updateSelective(Grid record) {
		return gridMapperDao.updateSelective(record);
	}

	/**
	 * @description 获取抄表类别列表
	 */
	@Override
	public List<Grid> selectSelective(Grid record) {
		return gridMapperDao.selectSelective(record);
	}

	/**
	 * @description 获取抄表类型详细信息
	 */
	@Override
	public Grid selectGrid(Grid record) {
		return gridMapperDao.selectGrid(record);
	}

	/**
	 * @description 删除表计类别设计
	 */
	@Override
	public int deleteGrid(Grid record) {
		return gridMapperDao.deleteGrid(record);
	}

	/**
	 * @description 获取抄表类别集合（简要）
	 */
	@Override
	public List<Grid> selectSelectiveSimple(Grid record) {
		return gridMapperDao.selectSelectiveSimple(record);
	}

}
