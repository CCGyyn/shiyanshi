package com.zeng.ezsh.wy.service.impl;
import java.beans.IntrospectionException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.zeng.ezsh.wy.entity.*;
import net.sf.json.JSONArray;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.zeng.ezsh.wy.dao.BuildingMapper;
import com.zeng.ezsh.wy.dao.ChargeRecordMapper;
import com.zeng.ezsh.wy.dao.GridRecordMapper;
import com.zeng.ezsh.wy.dao.ManagementMapper;
import com.zeng.ezsh.wy.dao.RoomMapper;
import com.zeng.ezsh.wy.service.GridRecordService;
import com.zeng.ezsh.wy.utils.CalcChargePriceUtil;
import com.zeng.ezsh.wy.utils.DateUtil;
import com.zeng.ezsh.wy.utils.ExportExcelUtil;
import com.zeng.ezsh.wy.utils.ImportExcelUtil;
@Service
public class GridRecordAdminServiceImpl implements GridRecordService {
	@Resource
	GridRecordMapper gridRecordMapperDao;
	@Resource
	ChargeRecordMapper chargeRecordMapperDao;
	@Resource
	ManagementMapper managementMapperDao;
	@Resource
	BuildingMapper buildMapperDao;
	@Resource
	RoomMapper roomMapperDao;
	/**
	 * @author qwc
	 * 2017年9月22日下午9:05:53
	 * @param in
	 * @param file
	 * @param importDate
	 * @return
	 * @throws Exception
	 * 导入抄表记录
	 */
	@Override
	public int importGridRecordExcel(InputStream in, ChargeItem chargeItem, MultipartFile file,
									 String importDate) throws Exception {
	 	List<List<Object>> listobject = ImportExcelUtil.getBankListByExcel(in,file.getOriginalFilename());
	    List<GridRecord> gridRecordList = new ArrayList<GridRecord>();
	    int chargeBillWay=chargeItem.getChargeBillingWay();//计费方式
	    System.out.println(JSONArray.fromObject(listobject).toString());
	    //遍历listobject数据，把数据放到List中
	    for (int i = 0; i < listobject.size(); i++) {
	        List<Object> obj = listobject.get(i);
	        GridRecord gridRecord = new GridRecord();
	        //gridRecord.setpManagerId(chargeItem.getpManagerId());
	        //gridRecord.setpBuildId(chargeItem);
	        //gridRecord.setRoomNum(String.valueOf(obj.get(2).toString()));
			gridRecord.setGridRecordId(Integer.valueOf(obj.get(0).toString()));
			gridRecord.setBeginDosage(Double.valueOf(obj.get(7).toString()));
	        gridRecord.setDosage(Double.valueOf(obj.get(8).toString()));//用量
			gridRecord.setGridReadPeople(obj.get(5).toString());
	        gridRecord.setEnteringTime(DateUtil.dateToStr(new Date(), DateUtil.YMR_SLASH));
	        gridRecordList.add(gridRecord);
	    }
	    int update=gridRecordMapperDao.updateGridRecordBatch(gridRecordList);
	    if(chargeBillWay==2){//抄表按单价计算
	    	List<ChargeRecord> chargeRecordList = new ArrayList<ChargeRecord>();
	    	BigDecimal unitPrice=chargeItem.getChargeBillingUnitPrice();
			for (int i = 0; i < listobject.size(); i++) {
			    List<Object> obj = listobject.get(i);
			    ChargeRecord chargeRecord=new ChargeRecord();
			    chargeRecord.setUnitPrice(unitPrice);
			    chargeRecord.setTotalPrice((new BigDecimal(obj.get(8).toString())).multiply(unitPrice));//计算总价钱
			    chargeRecord.setpRoomId(Integer.parseInt(obj.get(2).toString()));//房间ID
				chargeRecord.setChargeOfDate(String.valueOf(obj.get(6).toString()));
				chargeRecord.setpChargeItemId(chargeItem.getChargeId());
			    //chargeRecord.setpManagerId(chargeItem.getpManagerId());//管理处ID
			    //chargeRecord.setpBuildingId(13);
			    chargeRecordList.add(chargeRecord);
			}
			System.out.println("收费更新：》》"+JSONArray.fromObject(chargeRecordList).toString());
			int updateChargeRecord=chargeRecordMapperDao.updateChargeRecordBatch(chargeRecordList);//更新抄表记录
	    }
	    
	    System.out.println("导入更新：》》"+JSONArray.fromObject(gridRecordList).toString());
	    //批量插入
	    //gridRecordMapperDao.insertGridRecordBatch(gridRecordList);
		return 0;
	}
	
	/**
	 * @author qwc
	 * 2017年9月23日下午3:39:50
	 * @param record
	 * @return
	 * GridRecordAdminServiceImpl.java
	 * @param record
	 * @return 添加抄表记录
	 */
	@Override
	public int insert(GridRecord record) {
		// TODO Auto-generated method stub
		return gridRecordMapperDao.insert(record);
	}
	
	/**
	 * @author qwc
	 * 2017年9月23日下午3:28:03
	 * @return
	 * @throws InvocationTargetException
	 * @throws ClassNotFoundException
	 * @throws IntrospectionException
	 * @throws ParseException
	 * @throws IllegalAccessException
	 * 导出抄表记录
	 */
	@Override
	public XSSFWorkbook exportGridRecord(GridRecord record) throws InvocationTargetException,
			ClassNotFoundException, IntrospectionException, ParseException,
			IllegalAccessException {
		// TODO Auto-generated method stub
		List<GridRecord> list = gridRecordMapperDao.selectGridRecord(record);
		if(list.size() == 0){
			 System.out.println("数据为空！");
			 return null;
		}
        List<ExcelBean> excel = new ArrayList<>();
        Map<Integer,List<ExcelBean>> map = new LinkedHashMap<>();
        XSSFWorkbook xssfWorkbook = null;
        excel.add(new ExcelBean("抄表记录ID","gridRecordId",0));
        excel.add(new ExcelBean("楼栋名称","buildName",0));
        excel.add(new ExcelBean("房间ID","PRoomId",0));
        excel.add(new ExcelBean("房间编号","roomNum",0));
        excel.add(new ExcelBean("客户名字","customerName",0));
        excel.add(new ExcelBean("抄表人员","gridReadPeople",0));
        excel.add(new ExcelBean("抄表日期","gridReadTime",0));
        excel.add(new ExcelBean("起数","beginDosage",0));
        excel.add(new ExcelBean("用量","dosage",0));
        System.out.println("ExcelBean>>"+JSONArray.fromObject(excel).toString());
        map.put(0, excel);
        String sheetName = "抄表管理";
        try {
			xssfWorkbook = ExportExcelUtil.createExcelFileUpGrade(record.getClass(), list, map, sheetName);
		} catch (IllegalArgumentException | NoSuchMethodException
				| SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return xssfWorkbook;
	}
	
	/**
	 * @author qwc
	 * 2017年9月23日下午4:20:46
	 * @param record
	 * @return
	 * 获取抄表记录集合
	 */
	@Override
	public List<GridRecord> selectGridRecord(GridRecord record) {
		// TODO Auto-generated method stub
		return gridRecordMapperDao.selectGridRecord(record);
	}
	
	/**
	 * @author qwc
	 * 2017年9月24日下午3:14:03
	 * @param gridRcord
	 * @return 批量更新抄表记录
	 */
	@Override
	public int updateGridRecordBatch(List<GridRecord> gridRcord) {
		// TODO Auto-generated method stub
		return gridRecordMapperDao.updateGridRecordBatch(gridRcord);
	}
	
	/**
	 * @author qwc
	 * 2017年9月26日下午8:42:58
	 * @param record
	 * @return 更新单条抄表记录
	 */
	@Override
	public int updateByPrimaryKeySelective(GridRecord record) {
		// TODO Auto-generated method stub
		return gridRecordMapperDao.updateByPrimaryKeySelective(record);
	}
	
	/**
	 * @author qwc
	 * 2017年9月26日下午10:35:41
	 * @param record
	 * @return 删除单条抄表记录
	 */
	@Override
	public int deletePrimaryKeySelective(GridRecord record) {
		// TODO Auto-generated method stub
		return gridRecordMapperDao.deletePrimaryKeySelective(record);
	}
	
	/**
	 * @author qwc
	 * 2017年11月24日下午11:12:5
	 * @return 批量插入抄表记录
	 */
	@Override
	public int insertGridRecordBatch() {
		// TODO Auto-generated method stub
		int amount=0;
		List<Management> manageList = new ArrayList<Management>();
		List<Building> buildingList = new ArrayList<Building>();
		List<Room> roomList = new ArrayList<Room>();
		
		manageList=managementMapperDao.findAll();//获取出所有的小区
		Iterator<Management> it = manageList.iterator();
		while (it.hasNext()) {
			Map<String,Object> param = new HashMap<String, Object>();
			Management management = it.next();//获取出管理处
			param.put("buildManagerId", management.getManagerId());
			buildingList = buildMapperDao.findBuildingByParamToAdmin(param);//根据小区ID获取出所有楼栋
			
			Iterator<Building> buildIt = buildingList.iterator();
			while (buildIt.hasNext()){// 遍历楼宇
				Building build = new Building();
				build = buildIt.next(); 
				Map<String,Object> paramOne = new HashMap<String, Object>();
				paramOne.put("buildId", build.getBuildId());
				roomList = roomMapperDao.findRoomRecordByParam(paramOne);//根据楼栋ID获取出所有的房间
				
				List<GridRecord> gridRecordList = new ArrayList<GridRecord>();//应收费记录集合
				
				for (int i = 0; i<roomList.size(); i++) {// 遍历楼宇的所有房间
					Room room = new Room();
					room = roomList.get(i);
					if(room.getCustomerInfo() != null && !(room.getGridRoomItemList()).isEmpty()) {				
						for(int k = 0;k<(room.getGridRoomItemList()).size();k++){
							GridRecord gridRecord = new GridRecord();
							gridRecord.setpRoomId(room.getRoomId()); // 设置房间ID
							gridRecord.setpManagerId(room.getManagerId()); //设置管理处ID
							gridRecord.setpBuildId(room.getBuildId()); //设置管理处ID
							gridRecord.setpRoomId(room.getRoomId()); //设置房间ID
							
							SimpleDateFormat dateSDF = new SimpleDateFormat("yyyy-MM");
							gridRecord.setGridReadTime(dateSDF.format(new Date())); // 设置应收费用基本信息所属月份
							gridRecord.setpGridId(room.getGridRoomItemList().get(k).getPtGridId());
							gridRecord.setRoomNum(room.getRoomNum()); //设置房间号码
							gridRecord.setCustomerName(room.getCustomerInfo().getCustomerName());
							
							gridRecordList.add(gridRecord);
						}
					}
					
					if (!gridRecordList.isEmpty() && (gridRecordList.size() == 50 
							|| (gridRecordList.size()<50 && i == (roomList.size()-1)))) {
						//批量插入
						amount+=gridRecordMapperDao.insertGridRecordBatch(gridRecordList);// 批量插入抄表记录集合
						gridRecordList.clear();
					} else if (gridRecordList.size() > 50 && i != (roomList.size()-1)) {
						amount+=gridRecordMapperDao.insertGridRecordBatch(gridRecordList);// 批量插入抄表记录集合
						gridRecordList.clear();
					}
				}
			}
        }
		return amount;
		
	}
}
