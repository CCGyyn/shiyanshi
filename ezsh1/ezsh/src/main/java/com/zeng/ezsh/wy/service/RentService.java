package com.zeng.ezsh.wy.service;

import java.util.List;

import com.zeng.ezsh.wy.entity.Rent;

public interface RentService {
	/*添加出租记录*/
	public int insertSelective(Rent record);
	
	/*获取出租记录集合*/
	public List<Rent> selectRentList(Rent record);
	
	/*更新出租记录*/
	public int updateRent(Rent record);
	
	/*删除出租记录*/
	public int delRent(Rent record);
}
