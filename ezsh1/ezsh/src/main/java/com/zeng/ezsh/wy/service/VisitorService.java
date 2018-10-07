package com.zeng.ezsh.wy.service;

import java.util.List;
import java.util.Map;

import com.zeng.ezsh.wy.entity.DeviceLock;
import com.zeng.ezsh.wy.entity.VisitorCode;

public interface VisitorService {
	/* 添加访客密码 */
	public int insert(VisitorCode record, String deviceId, String devicePass);

	/* 检测是否重新设置 */
	public VisitorCode checkByParam(VisitorCode record);

	/* 获取个人设置的通行证列表 */
	public List<VisitorCode> selectCodeListByParam(VisitorCode record);

	/* 远程开门（移动端） */
	public int remoteOpenDoor(DeviceLock record);
	
	/*根据用户ID和楼宇ID获取访客码信息*/
	public VisitorCode selectCodeByParam(VisitorCode record);
}
