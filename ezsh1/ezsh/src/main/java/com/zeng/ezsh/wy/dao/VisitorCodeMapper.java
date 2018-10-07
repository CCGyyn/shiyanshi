package com.zeng.ezsh.wy.dao;

import java.util.List;
import java.util.Map;

import org.apache.poi.ss.formula.IStabilityClassifier;

import com.zeng.ezsh.wy.entity.VisitorCode;

public interface VisitorCodeMapper {

    int deleteByPrimaryKey(Integer visitorRecordId);
    
    /*添加访客密码*/
    int insertSelective(VisitorCode record);
    
    /*查询是否在有效期内重设访客码*/
    VisitorCode checkByParam(VisitorCode record);
    
    /*获取个人设置的访问码列表*/
    List<VisitorCode> selectCodeListByParam(VisitorCode record);
    
    /*获取单条访问码记录*/
    VisitorCode selectCodeById(VisitorCode record);
    
    /*获取最大的Id值 */
    Long selectMaxId();
    
    /*根据设备ID和访问码获取访问码记录*/
    VisitorCode selectCodeByDeviceIdAndCode(Map<String,Object> param);
    
    /*根据用户ID和楼宇ID获取访客码信息*/
    VisitorCode selectCodeByParam(VisitorCode record);
    
    /*int insertSelective(VisitorRecord record);

    int updateByPrimaryKeySelective(VisitorRecord record);

    int updateByPrimaryKey(VisitorRecord record);*/
}