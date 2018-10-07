package com.zeng.ezsh.wy.service;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.List;
import java.util.Map;

import com.zeng.ezsh.wy.entity.ChargeInfo;
import com.zeng.ezsh.wy.entity.ChargeRecord;
import com.zeng.ezsh.wy.entity.ResidentialUser;

public interface ChargeRecordService {
	/* 添加应收费记录（批量） */
	public int insertChargeRecordBatch();

	/* 添加单个应收费记录 */
	public int insertSelective(ChargeRecord record);

	/* 根据条件获取收费记录集合 */
	public List<ChargeRecord> selectChargeRecordByParam(ChargeRecord record);

	/* 批量更新应收费记录 */
	public int updateChargeRecordBatch(List<ChargeRecord> recordList);

	/* 获取缴费记录 */
	List<ChargeInfo> selectChargeListByParam(Map<String, Object> param);

	/* 创建物业缴费支付订单信息 */
	String ceratePayInfo(ChargeInfo chargeInfo, String payMethod , ResidentialUser tokenModel,Map<String, Object> additionMap);

	/* 检测缴费状态 */
	public int checkPayStatus(ChargeInfo record);

	/* 获取单个缴费信息 */
	public ChargeInfo selectChargeInfoReacord(ChargeInfo record);

	/* 更新支付状态 */
	public int updateChargeInfoPayStatus(ChargeInfo record);

	/* 删除应收费记录 */
	public int deleteByPrimaryKey(ChargeRecord record);

	/* 更新应收费记录（后台单条） */
	public int updateByPrimaryKeySelective(ChargeRecord record);

}
