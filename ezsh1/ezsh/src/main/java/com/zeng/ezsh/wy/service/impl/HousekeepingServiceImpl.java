package com.zeng.ezsh.wy.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;











import com.zeng.ezsh.wy.dao.HkItemMapper;
import com.zeng.ezsh.wy.dao.HkTypeMapper;
import com.zeng.ezsh.wy.dao.HousekeepingMapper;
import com.zeng.ezsh.wy.entity.HkItem;
import com.zeng.ezsh.wy.entity.HkType;
import com.zeng.ezsh.wy.entity.HkTypeVo;
import com.zeng.ezsh.wy.entity.Housekeeping;
import com.zeng.ezsh.wy.service.HousekeepingService;

@Service("housekeepingService")
public class HousekeepingServiceImpl implements HousekeepingService {

	@Autowired
	private HousekeepingMapper housekeepingMapper;
	@Autowired
	private HkTypeMapper hkTypeMapper;
	@Autowired
	private HkItemMapper hkItemMapper;
	//插入申请登记信息
	@Override
	public int insertApplyRecord(Housekeeping record) {
		
		return housekeepingMapper.insertSelective(record);
	}

	//审核操作，1表示通过，2表示不通过
	@Override
	public int audite(Map<String, Object> map) {
		
		return housekeepingMapper.audite(map);
	}

	//查询未审核的登记信息
	@Override
	public List<Housekeeping> selectNoAuditedByPage(Map<String,Object> map) {
		List<Housekeeping> list = housekeepingMapper.selectNoAuditedByPage(map);
		return list;
	}

	//查询通过审核的家政服务公司信息
	@Override
	public List<Housekeeping> selectAuditedList(Map<String,Object> map) {
		List<Housekeeping> list = housekeepingMapper.selectAuditedList(map);
		return list;
	}
	
	public int selectNoAuditedCount(){
		return housekeepingMapper.selectNoAuditedCount();
	}
	
	public int selectAuditedCount(){
		return housekeepingMapper.selectAuditedCount();
	}

	@Override
	public int addHkType(HkType hkType) {
		
		return hkTypeMapper.insertSelective(hkType);
	}

	@Override
	public int editHkType(HkType hkType) {
		
		return hkTypeMapper.updateByPrimaryKeySelective(hkType);
	}

	@Override
	public int deleteHkType(int id) {
		
		return hkTypeMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int addHkItem(HkItem hkItem) {
		
		return hkItemMapper.insertSelective(hkItem);
	}

	@Override
	public int editHkItem(HkItem hkItem) {
		
		return hkItemMapper.updateByPrimaryKeySelective(hkItem);
	}

	@Override
	public int deleteHkItem(int id) {
		
		return hkItemMapper.deleteByPrimaryKey(id);
	}

	@Override
	public HkItem getHkItemById(Map<String, Object> map) {
		
		return hkItemMapper.getHkItemById(map);
	}

	@Override
	public int getTypeId(String hkType) {
		
		return hkTypeMapper.getTypeId(hkType);
	}

	@Override
	public List<HkItem> getItemList(Map<String, Object> map) {
		
		return hkItemMapper.getItemList(map);
	}

	@Override
	public String getHkNameById(int hkId) {
		
		return housekeepingMapper.getHkNameById(hkId);
	}

	@Override
	public String getTypeById(int typeId) {
		
		return hkTypeMapper.getTypeById(typeId);
	}

	@Override
	public List<HkType> getHkItems(int hkId) {
		
		return hkTypeMapper.getHkItems(hkId);
	}

	@Override
	public List<HkItem> getItemByHkType(int typeId) {
		
		return hkItemMapper.getItemByHkType(typeId);
	}

	@Override
	public int updateByImg(Housekeeping record) {
		
		return housekeepingMapper.updateByImg(record);
	}

	@Override
	public int getId(Map<String, Object> map) {
		
		return housekeepingMapper.getId(map);
	}

	@Override
	public List<HkTypeVo> getHkTypes(int hkId) {
		
		return hkTypeMapper.getHkTypes(hkId);
	}


}
