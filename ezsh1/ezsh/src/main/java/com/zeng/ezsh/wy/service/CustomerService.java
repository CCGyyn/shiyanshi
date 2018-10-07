package com.zeng.ezsh.wy.service;

import java.util.List;
import java.util.Map;

import com.zeng.ezsh.wy.entity.Customer;
import com.zeng.ezsh.wy.entity.Room;

public interface CustomerService {
	/*登记房间客户资料*/
    public int insert(Customer record);
    
    /*登记房间客户资料（同时更新房间相关信息）*/
    public int insertWithUpdateRoom(Customer record);
    
    /*房间客户资料登记管理*/
    public List<Room> selectCustomerCheckInList(Room record);
    
    /*房间客户档案管理*/
    public List<Customer> selectCustomerArchivesList(Customer record);
    
    /*更新客户档案资料*/
    public int updateByPrimaryKeySelective(Customer record);
    
    /*迁出客户*/
    public int checkOut(Customer record);
    
    /*获取房间客户信息*/
    public List<Customer> getCustomerRoomid(Map<String, String> map);
    
    public Customer getCustomerById(int ptUserId);
    
    public Customer getCustomerByMBR(Map<String, Object> map);
    
    public Customer getCustomerByCusId(Integer id);
}
