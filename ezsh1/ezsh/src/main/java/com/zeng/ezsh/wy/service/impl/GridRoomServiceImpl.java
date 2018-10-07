package com.zeng.ezsh.wy.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.zeng.ezsh.wy.dao.GridRecordMapper;
import com.zeng.ezsh.wy.dao.GridRoomItemMapper;
import com.zeng.ezsh.wy.entity.GridRoomItem;
import com.zeng.ezsh.wy.service.GridRoomService;
@Service
public class GridRoomServiceImpl implements GridRoomService {
	@Resource
	GridRoomItemMapper gridRoomItemMapper;
	
	/**
	 * @author qwc
	 * 2017年11月23日下午10:51:52
	 * @param record
	 * @return 添加房间表计类别
	 */
	@Override
	public int insert(GridRoomItem record) {
		// TODO Auto-generated method stub
		return gridRoomItemMapper.insert(record);
	}
	
	/**
	 * @author qwc
	 * 2017年11月23日下午10:51:34
	 * @param param
	 * @return 按条件获取房间表计类别
	 */
	@Override
	public List<GridRoomItem> selectGridRoomItemsByParam(
			Map<String, Object> param) {
		// TODO Auto-generated method stub
		return gridRoomItemMapper.selectGridRoomItemsByParam(param);
	}
	
	/**
	 * @author qwc
	 * 2017年11月23日下午10:51:02
	 * @param gridRoomItemId
	 * @return 删除房间表计类别
	 */
	@Override
	public int deleteByPrimaryKey(Integer gridRoomItemId) {
		// TODO Auto-generated method stub
		return gridRoomItemMapper.deleteByPrimaryKey(gridRoomItemId);
	}
	
	/**
	 * @author qwc
	 * 2017年11月23日下午10:50:55
	 * @param record
	 * @return 检测房间表计类别是否存在
	 */
	@Override
	public int checkGridRoomItemIsOnUse(GridRoomItem record) {
		// TODO Auto-generated method stub
		return gridRoomItemMapper.checkGridRoomItemIsOnUse(record);
	}
	
	/**
	 * @author qwc
	 * 2017年11月24日下午8:13:11
	 * @param record
	 * @return 修改房间表计类别信息
	 */
	@Override
	public int updateByPrimaryKeySelective(GridRoomItem record) {
		// TODO Auto-generated method stub
		return gridRoomItemMapper.updateByPrimaryKeySelective(record);
	}

}
