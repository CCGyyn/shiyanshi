package com.zeng.ezsh.wy.dao;

import java.util.List;
import java.util.Map;

import com.zeng.ezsh.wy.entity.Customer;
import com.zeng.ezsh.wy.entity.Room;

public interface CustomerMapper {
    int deleteByPrimaryKey(Integer customerId);
    
    /*登记房间客户资料*/
    int insert(Customer record);
    
    /*房间客户资料登记管理*/
    List<Room> selectCustomerCheckInList(Room record);
    
    /*房间客户档案管理*/
    List<Customer> selectCustomerArchivesList(Customer record);
    
    /*更新客户档案资料*/
    int updateByPrimaryKeySelective(Customer record);
    
    /*迁出客户*/
    int checkOut(Customer record);
    
    int insertSelective(Customer record);

    Customer selectByPrimaryKey(Integer customerId);
    
    int updateByPrimaryKey(Customer record);
    
    List<Customer> getCustomerRoomid(Map<String, String> map); 
    
    Customer getCustomerById(Integer ptUserId);
    
    Customer getCustomerByMBR(Map<String, Object> map);
}