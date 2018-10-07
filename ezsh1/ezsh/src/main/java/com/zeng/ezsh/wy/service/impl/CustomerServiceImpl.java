package com.zeng.ezsh.wy.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.zeng.ezsh.wy.dao.CustomerMapper;
import com.zeng.ezsh.wy.dao.RoomMapper;
import com.zeng.ezsh.wy.entity.Customer;
import com.zeng.ezsh.wy.entity.Room;
import com.zeng.ezsh.wy.service.CustomerService;
@Service
public class CustomerServiceImpl implements CustomerService {
	@Resource
	CustomerMapper customerMapperDao;
	@Resource
	RoomMapper roomMapper;
	
	/**
	 * @author qwc
	 * 2017年9月6日下午9:40:55
	 * @param record
	 * @return
	 * 登记房间客户资料
	 */
	@Override
	public int insert(Customer record) {
		// TODO Auto-generated method stub
		return customerMapperDao.insert(record);
	}
	
	/**
	 * @author qwc
	 * 2017年9月7日下午4:55:28
	 * @param record
	 * @return
	 * 房间客户资料登记管理
	 */
	@Override
	public List<Room> selectCustomerCheckInList(Room record) {
		// TODO Auto-generated method stub
		return customerMapperDao.selectCustomerCheckInList(record);
	}
    
	/**
	 * @author qwc
	 * 2017年9月7日下午5:58:15
	 * @param record
	 * @return
	 * 房间客户档案管理
	 */
	@Override
	public List<Customer> selectCustomerArchivesList(Customer record) {
		// TODO Auto-generated method stub
		return customerMapperDao.selectCustomerArchivesList(record);
	}
	
	/**
	 * @author qwc
	 * 2017年9月11日下午4:31:36
	 * @param record
	 * @return
	 * 更新客户档案资料
	 */
	@Override
	public int updateByPrimaryKeySelective(Customer record) {
		// TODO Auto-generated method stub
		return customerMapperDao.updateByPrimaryKeySelective(record);
	}
	
	/**
	 * @author qwc
	 * 2017年10月26日下午3:13:27
	 * @param record
	 * @return 房间客户迁入
	 */
	@Override
	public int insertWithUpdateRoom(Customer record) {
		// TODO Auto-generated method stub
		Room room =new Room();
		room.setRoomId(record.getpRoomId());
		room.setUserIdentityCard(record.getCustomerIdCardNumber());//设置身份证号码
		roomMapper.updateRoomSelective(room);
		int status=customerMapperDao.insert(record);
		return status;
	}
	
	/**
	 * @author qwc
	 * 2017年10月26日下午4:37:49
	 * @param record
	 * @return 房间客户迁出
	 */
	@Override
	public int checkOut(Customer record) {
		// TODO Auto-generated method stub
		Room room =new Room();
		room.setPtUserId(0);
		room.setUserIdentityCard("");
		room.setRoomId(record.getpRoomId());
		roomMapper.updateRoomSelective(room);
		record.setResideStatus(1);
		return customerMapperDao.checkOut(record);
	}

	@Override
	public List<Customer> getCustomerRoomid(Map<String, String> map) {
		return customerMapperDao.getCustomerRoomid(map);
	}

	@Override
	public Customer getCustomerById(int ptUserId) {
		return customerMapperDao.getCustomerById(ptUserId);
	}
	
	public Customer getCustomerByMBR(Map<String, Object> map){
		return customerMapperDao.getCustomerByMBR(map);
	}

	@Override
	public Customer getCustomerByCusId(Integer id) {
		
		return customerMapperDao.selectByPrimaryKey(id);
	}
	
}
