package com.zeng.ezsh.wy.dao;

import com.zeng.ezsh.wy.entity.District;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface DistrictMapper {
	/*获取区列表*/
	List<District> getDistrictList(int cityId);
	
    int deleteByPrimaryKey(Integer id);

    int insert(District record);

    int insertSelective(District record);

    District selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(District record);

    int updateByPrimaryKey(District record);
}