package com.zeng.ezsh.wy.service.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletOutputStream;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import com.zeng.ezsh.wy.entity.Building;
import com.zeng.ezsh.wy.entity.Room;
import com.zeng.ezsh.wy.service.RoomService;
import com.zeng.ezsh.wy.utils.PoiUtil;

@Service
public class RoomServiceImpl extends BaseServiceImpl<Room> implements
		RoomService {
	/**
	 * 
	 * 导出room列表
	 */
	@Override
	public void exportExcel(ServletOutputStream outputStream, List<Room> room) {
		// TODO Auto-generated method stub
		PoiUtil.exportRoomExcel(room, outputStream);
	}

	@Override
	public List<Room> findAll() {
		// TODO Auto-generated method stub
		return roomMapper.findAll();
	}

	/**
	 * @author qwc 
	 * 2017年8月23日下午9:57:50
	 * @param param
	 * @return 查询房间号给移动端
	 */
	@Override
	public List<Room> findRoomByParam(Map<String, Object> param) {
		// TODO Auto-generated method stub
		return roomMapper.findRoomByParam(param);
	}

	/**
	 * @author qwc 
	 * 2017年9月6日下午9:55:47
	 * @param param
	 * @return 根据条件查询单个房间信息给后台
	 */
	@Override
	public Room findRoomByParamToAfterEnd(Map<String, Object> param) {
		// TODO Auto-generated method stub
		return roomMapper.findRoomByParamToAfterEnd(param);
	}

	@Override
	public List<Room> findRoomByParamToAfter(Map<String, Object> param) {
		// TODO Auto-generated method stub
		return roomMapper.findRoomByParamToAfter(param);
	}

	/**
	 * @author qwc 
	 * 2017年9月27日下午1:43:09
	 * @param room
	 * @return 查询房间以及客户名字集合
	 */
	@Override
	public List<Room> findRoomCustomer(Room room) {
		// TODO Auto-generated method stub
		return roomMapper.findRoomCustomer(room);
	}

	/**
	 * @author qwc
	 * @param room
	 * @return 更新房间信息
	 */
	@Override
	public int updateRoomSelective(Room room) {
		// TODO Auto-generated method stub
		return roomMapper.updateRoomSelective(room);
	}

	/**
	 * @author qwc
	 * @param room
	 * @return 检测房间号是否存在
	 */
	@Override
	public int checkNameHasOn(Room room) {
		// TODO Auto-generated method stub
		return roomMapper.checkNameHasOn(room);
	}
	
	/**
	 * @author qwc 
	 * 2017年9月6日下午9:55:47
	 * @param param
	 * @return 根据条件查询单个房间信息给前台
	 */
	@Override
	public Room findRoomByParamToFrontEnd(Map<String, Object> param) {
		// TODO Auto-generated method stub
		return roomMapper.findRoomByParamToFrontEnd(param);
	}
	
	@Override
	public int importExcel(String excelName, InputStream inputStream) {
		// TODO Auto-generated method stub
		int i = 0;
		try {
			boolean is03Excel = excelName.matches("^.+\\.(?i)(xls)$");
			// 1、读取工作簿
			Workbook workbook = is03Excel ? new HSSFWorkbook(inputStream)
					: new XSSFWorkbook(inputStream);
			// 2、读取工作表
			Sheet sheet = workbook.getSheetAt(0);
			// 3、读取行
			if (sheet.getPhysicalNumberOfRows() > 2) {
				Room room = null;
				for (int k = 2; k < sheet.getPhysicalNumberOfRows(); k++) {
					// 4、读取单元格
					Row row = sheet.getRow(k);
					room = new Room();
					// 管理处
					Cell cell0 = row.getCell(0);
					int managerId = managementMapper.findIdByName(cell0
							.getStringCellValue());
					room.setManagerId(managerId);
					// 楼宇
					Cell cell1 = row.getCell(1);
					int buildId = buildingMapper.findIdByName(cell1
							.getStringCellValue());
					room.setBuildId(buildId);
					// 建筑面积
					Cell cell2 = row.getCell(2);
					cell2.setCellType(Cell.CELL_TYPE_STRING);
					if (StringUtils.isNotBlank(cell2.getStringCellValue())) {
						room.setBuildArea(Double.parseDouble(cell2
								.getStringCellValue()));
					}
					// 房间状态
					Cell cell3 = row.getCell(3);
					cell3.setCellType(Cell.CELL_TYPE_STRING);
					if (StringUtils.isNotBlank(cell3.getStringCellValue())) {
						if (cell3.getStringCellValue().equals("未售")) {
							room.setRoomStatus(0);
						} else if (cell3.getStringCellValue().equals("已售")) {
							room.setRoomStatus(1);
						} else if (cell3.getStringCellValue().equals("未租")) {
							room.setRoomStatus(2);
						} else if (cell3.getStringCellValue().equals("已租")) {
							room.setRoomStatus(3);
						} else if (cell3.getStringCellValue().equals("自用")) {
							room.setRoomStatus(4);
						} else if (cell3.getStringCellValue().equals("入住")) {
							room.setRoomStatus(5);
						} else if (cell3.getStringCellValue().equals("空置")) {
							room.setRoomStatus(6);
						} else if (cell3.getStringCellValue().equals("未交付")) {
							room.setRoomStatus(7);
						}
					}
					;
					// 房间类型
					Cell cell4 = row.getCell(4);
					cell4.setCellType(Cell.CELL_TYPE_STRING);
					if (StringUtils.isNotBlank(cell4.getStringCellValue())) {
						if (cell4.getStringCellValue().equals("住宅")) {
							room.setRoomType(0);
						} else if (cell4.getStringCellValue().equals("公寓")) {
							room.setRoomType(1);
						} else if (cell4.getStringCellValue().equals("办公")) {
							room.setRoomType(2);
						} else if (cell4.getStringCellValue().equals("厂房")) {
							room.setRoomType(3);
						} else if (cell4.getStringCellValue().equals("仓库")) {
							room.setRoomType(4);
						} else if (cell4.getStringCellValue().equals("宿舍")) {
							room.setRoomType(5);
						} else if (cell4.getStringCellValue().equals("其他")) {
							room.setRoomType(6);
						}
					}
					;
					// 房间楼层
					Cell cell5 = row.getCell(5);
					cell5.setCellType(Cell.CELL_TYPE_STRING);
					if (StringUtils.isNoneBlank(cell5.getStringCellValue())) {
						room.setRoomFloor(Integer.parseInt(cell5
								.getStringCellValue()));
					}
					// 房间号
					Cell cell6 = row.getCell(6);
					cell6.setCellType(Cell.CELL_TYPE_STRING);
					if (StringUtils.isNotBlank(cell6.getStringCellValue())) {
						room.setRoomNum(cell6.getStringCellValue());
					} else {
						room.setRoomNum("");
					}
					// 收费服务对象
					Cell cell7 = row.getCell(7);
					cell7.setCellType(Cell.CELL_TYPE_STRING);
					if (StringUtils.isNoneBlank(cell7.getStringCellValue())) {
						room.setChargeMan(cell7.getStringCellValue());
					} else {
						room.setChargeMan("");
					}
					// 房间用途
					Cell cell8 = row.getCell(8);
					cell8.setCellType(Cell.CELL_TYPE_STRING);
					if (StringUtils.isNoneBlank(cell8.getStringCellValue())) {
						room.setRoomUse(cell8.getStringCellValue());
					} else {
						room.setRoomUse("");
					}
					// 装修情况
					Cell cell9 = row.getCell(9);
					cell9.setCellType(Cell.CELL_TYPE_STRING);
					if (StringUtils.isNoneBlank(cell9.getStringCellValue())) {
						room.setDecorate(cell9.getStringCellValue());
					} else {
						room.setDecorate("");
					}
					// 房间位置
					Cell cell10 = row.getCell(10);
					cell10.setCellType(Cell.CELL_TYPE_STRING);
					if (StringUtils.isNoneBlank(cell10.getStringCellValue())) {
						room.setPosition(cell10.getStringCellValue());
					} else {
						room.setPosition("");
					}
					// 朝向
					Cell cell11 = row.getCell(11);
					cell11.setCellType(Cell.CELL_TYPE_STRING);
					if (StringUtils.isNoneBlank(cell11.getStringCellValue())) {
						room.setToward(cell11.getStringCellValue());
					} else {
						room.setToward("");
					}
					// 备注
					Cell cell12 = row.getCell(12);
					cell12.setCellType(Cell.CELL_TYPE_STRING);
					if (StringUtils.isNoneBlank(cell12.getStringCellValue())) {
						room.setRemark(cell12.getStringCellValue());
					} else {
						room.setRemark("");
					}
					// 租金
					Cell cell13 = row.getCell(13);
					if (cell13.getNumericCellValue() != 0) {
						room.setRent(cell13.getNumericCellValue());
					}
					// 管理费
					Cell cell14 = row.getCell(14);
					if (cell14.getNumericCellValue() != 0) {
						room.setManagementFee(cell14.getNumericCellValue());
					}
					// 单价
					Cell cell15 = row.getCell(15);
					if (cell15.getNumericCellValue() != 0) {
						room.setSinglePrice(cell15.getNumericCellValue());
					}
					// 总价
					Cell cell16 = row.getCell(16);
					if (cell16.getNumericCellValue() != 0) {
						room.setSumPrice(cell16.getNumericCellValue());
					}
					// 5、保存房间档案
					int j = roomMapper.insert(room);
					i = i + j;
				}
			}
			workbook.close();
			inputStream.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return i;
	}

	@Override
	public Room getRoomByMBR(Map<String, Object> map) {

		return roomMapper.findByMBR(map);
	}
	
	
}
