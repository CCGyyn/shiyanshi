package com.zeng.ezsh.wy.service;

import java.util.List;
import java.util.Map;

import com.zeng.ezsh.wy.entity.HkItem;
import com.zeng.ezsh.wy.entity.HkType;
import com.zeng.ezsh.wy.entity.HkTypeVo;
import com.zeng.ezsh.wy.entity.Housekeeping;

public interface HousekeepingService {
	
	public int insertApplyRecord(Housekeeping record);
	
	public int audite(Map<String,Object> map);
	
	public List<Housekeeping> selectNoAuditedByPage(Map<String,Object> map);
	
	public List<Housekeeping> selectAuditedList(Map<String,Object> map);
	
	public int selectNoAuditedCount();
	
	public int selectAuditedCount();
	
	//根据hkId查询类型
	//List<HkType> getTypeByHkId(int hkId);
	//家政公司增加服务类型
	public int addHkType(HkType hkType);
	
	//家政公司修改服务类型
	public int editHkType(HkType hkType);
	
	//家政公司删除服务类型
	public int deleteHkType(int id);
	
	//家政公司增加服务项目
	public int addHkItem(HkItem hkItem);
	
	//家政公司修改服务项目
	public int editHkItem(HkItem hkItem);
	
	//家政公司删除服务项目
	public int deleteHkItem(int id);
	
	//根据id查询服务项目
	public HkItem getHkItemById(Map<String,Object> map);
	
	//查询typeId
	public int getTypeId(String hkType);
	
	//分页获取项目列表
	public List<HkItem> getItemList(Map<String,Object> map);
	
	//根据id获取公司名称
	public String getHkNameById(int hkId);
	
	//根据typeId获取服务类型
	public String getTypeById(int typeId);
	
	//根据服务公司id获取具体服务类型
	public List<HkType> getHkItems(int hkId);
	
	//根据服务类型id获取具体服务项目
	public List<HkItem> getItemByHkType(int typeId);
	
	//更新
	public int updateByImg(Housekeeping record);
	
	//根据小区id和服务电话获取服务公司id
	public int getId(Map<String,Object> map);
	
	//根据服务公司id获取具体服务类型
	List<HkTypeVo> getHkTypes(int hkId);

}
