package com.zeng.ezsh.wy.dao;

import java.util.List;
import java.util.Map;

import com.zeng.ezsh.wy.entity.ChargeRecord;
import com.zeng.ezsh.wy.entity.Vo.ChargeRecordVoLin;

public interface ChargeRecordMapper {
    
    /*添加应收费记录*/
    int insertChargeRecordBatch(List<ChargeRecord> recordList);
    
    /*根据条件获取收费记录集合*/
    List<ChargeRecord> selectChargeRecordByParam(ChargeRecord record);

    List<ChargeRecord> selectChargeRecordStatus(ChargeRecord record);

    List<ChargeRecord> selectChargeRecordBypRoomId(Map<String,Object> map);
    /*按照年份与是否缴费来搜索*/
    List<ChargeRecord> selectChargeRecordBypRoomId1(Map<String,Object> map);
    /*计算总价*/
    Integer selectChargeRecordBypRoomId2(Map<String,Object> map);

    List<ChargeRecord> selectUserChargeRecordByUserId(Map<String,Object> map);

    List<ChargeRecordVoLin> selectChargeRecordVoByPUserId(Map<String,Object> map);
    
    /*批量更新应收费记录*/
    int updateChargeRecordBatch(List<ChargeRecord> recordList);
    
    /*添加单个应收费记录*/
    int insertSelective(ChargeRecord record);
    
    /*删除应收费记录*/
    int deleteByPrimaryKey(ChargeRecord record);
    
    /*更新应收费记录（后台单条）*/
    int updateByPrimaryKeySelective(ChargeRecord record);
    //更新缴费记录   List
    int updateByPrimaryKeySelective2(List<ChargeRecord> listRecord);
    
    ChargeRecord selectByPrimaryKey(Integer chargeRecordId);

    List<ChargeRecord> selectChargeRecordByUserId(Integer pUserId);

    int updateByPrimaryKey(ChargeRecord record);
}