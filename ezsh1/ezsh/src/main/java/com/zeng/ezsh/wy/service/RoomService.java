package com.zeng.ezsh.wy.service;

import java.io.File;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletOutputStream;

import com.zeng.ezsh.wy.entity.Building;
import com.zeng.ezsh.wy.entity.Room;

public interface RoomService extends BaseService<Room> {
	/**
	 * 导出excel
	 * @param outputStream 输出流
	 * @param rooms 需要导出的列表
	 */
    public void exportExcel(ServletOutputStream outputStream, List<Room> rooms);
   
    public int importExcel(String excelName,InputStream inputStream );
    /**
     *  查询所有
     * @return
     */
    public List<Room> findAll();
    
    /*根据条件查询房间给移动端*/
	public List<Room> findRoomByParam(Map<String, Object> param);
	
	/*获取房间集合用于后台构建房间树*/
    public List<Room> findRoomByParamToAfter(Map<String, Object> param);
    
	/*根据条件查询单个房间信息给后台*/
	public Room findRoomByParamToAfterEnd(Map<String, Object> param);
	
	/*根据房间ID获取单个房间的所有信息（前台）*/
	public  Room findRoomByParamToFrontEnd(Map<String, Object> param);
	
	/*查询房间以及客户名字集合*/
	public List<Room> findRoomCustomer(Room room);
	
	/*选择性更新房间信息*/
	public int updateRoomSelective(Room room);
	
	/*检测房间号是否存在*/
	public int checkNameHasOn(Room room);
	
	public Room getRoomByMBR(Map<String, Object> map);
}
