package com.zeng.ezsh.wy.dao;

import com.zeng.ezsh.wy.entity.Rent;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface RentMapper {
	/*添加出租记录*/
	int insertSelective(Rent record);
	
	/*获取出租记录集合*/
	List<Rent> selectRentList(Rent record);
	

	/*更新出租记录*/
	int updateRent(Rent record);
	
	/*删除出租记录*/
	int delRent(Rent record);
}