package com.zeng.ezsh.wy.service;

import java.beans.IntrospectionException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.text.ParseException;
import java.util.List;

import com.zeng.ezsh.wy.entity.ChargeItem;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import com.zeng.ezsh.wy.entity.Grid;
import com.zeng.ezsh.wy.entity.GridRecord;

public interface GridRecordService {
	/*添加抄表记录*/
    public int insert(GridRecord record);

    /*更新单条抄表记录*/
    public int updateByPrimaryKeySelective(GridRecord record);
    
    /*删除单条抄表记录*/
    public int deletePrimaryKeySelective(GridRecord record);
    
    /*获取抄表记录集合*/
    public List<GridRecord> selectGridRecord(GridRecord record);
    
    /*批量更新抄表记录*/
    public int updateGridRecordBatch(List<GridRecord> gridRcord);
    
    /*批量插入抄表记录集合*/
    public int insertGridRecordBatch();
    
    /*导入抄表记录*/
	public int importGridRecordExcel(InputStream in, ChargeItem chargeItem, MultipartFile file, String importDate) throws Exception;
	
	/*导出抄表记录*/
	public XSSFWorkbook exportGridRecord(GridRecord record) throws InvocationTargetException, ClassNotFoundException, IntrospectionException, ParseException, IllegalAccessException;

}
