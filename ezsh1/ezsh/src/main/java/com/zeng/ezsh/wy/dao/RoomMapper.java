package com.zeng.ezsh.wy.dao;

import java.util.List;
import java.util.Map;

import com.zeng.ezsh.wy.entity.Building;
import com.zeng.ezsh.wy.entity.Room;
import com.zeng.ezsh.wy.entity.Vo.RoomUserVoLin;

public interface RoomMapper extends BaseMapper<Room> {
   public List<Room> findAll();
   
   /*查询房间给移动端*/
   List<Room> findRoomByParam(Map<String, Object> param);
   
   /*获取房间集合用于后台构建房间树*/
   List<Room> findRoomByParamToAfter(Map<String, Object> param);
   
   /*获取房间收费项目集合*/
   List<Room> findRoomChargeByParam(Map<String, Object> param);
   
   /*获取房间表计类别集合*/
   List<Room> findRoomRecordByParam(Map<String, Object> param);
   
   /*根据房间ID获取单个房间的所有信息（后台）*/
   Room findRoomByParamToAfterEnd(Map<String, Object> param);
   
   /*根据房间ID获取单个房间的所有信息（前台）*/
   Room findRoomByParamToFrontEnd(Map<String, Object> param);

   RoomUserVoLin selectRoomUserByRoomId(Integer roomId);
   
   /*查询房间以及客户名字集合*/
   List<Room> findRoomCustomer(Room room);
   
   Room findByMBR(Map<String, Object> map);
   
   /*选择性更新房间信息*/
   int updateRoomSelective(Room room);
   
   /*检测房间号是否存在*/
   int checkNameHasOn(Room room);
   
   /*通过手机号获取身份证*/
   String findIdentityCardById(Integer id);
}