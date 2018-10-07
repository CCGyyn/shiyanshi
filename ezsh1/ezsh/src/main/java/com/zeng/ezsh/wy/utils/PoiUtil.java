package com.zeng.ezsh.wy.utils;

import java.io.File;
import java.io.FileInputStream;
import java.math.BigDecimal;
import java.util.List;

import javax.servlet.ServletOutputStream;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;

import com.zeng.ezsh.wy.entity.Room;



public class PoiUtil  {
	/**
	 * 导出房间档案的所有列表到excel
	 * @param rooms 
	 * @param outputStream 输出流
	 */
	public static void exportRoomExcel(List<Room> rooms, ServletOutputStream outputStream) {
		try {
			//1、创建工作簿
			HSSFWorkbook workbook = new HSSFWorkbook();
			//1.1、创建合并单元格对象
			CellRangeAddress cellRangeAddress = new CellRangeAddress(0, 0, 0, 16);//起始行号，结束行号，起始列号，结束列号
			//1.2、头标题样式
			HSSFCellStyle style1 = createCellStyle(workbook, (short)16);
			//1.3、列标题样式
			HSSFCellStyle style2 = createCellStyle(workbook, (short)13);
			//2、创建工作表
			HSSFSheet sheet = workbook.createSheet("房间列表");
			//2.1、加载合并单元格对象
			sheet.addMergedRegion(cellRangeAddress);
			//设置默认列宽
			sheet.setDefaultColumnWidth(25);
			
			//3、创建行
			//3.1、创建头标题行；并且设置头标题
			HSSFRow row1 = sheet.createRow(0);
			HSSFCell cell1 = row1.createCell(0);
			//加载单元格样式
			cell1.setCellStyle(style1);
			cell1.setCellValue("房间列表");
			
			//3.2、创建列标题行；并且设置列标题
			HSSFRow row2 = sheet.createRow(1);
			String[] titles = {"管理处", "楼宇", "建筑面积", "房间状态","房间类型","房间楼层","房间号","收费服务对象","房间用途","装修情况","房间位置","朝向","备注","租金","管理费","单价","总价"};
			for(int i = 0; i < titles.length; i++){
				HSSFCell cell2 = row2.createCell(i);
				//加载单元格样式
				cell2.setCellStyle(style2);
				cell2.setCellValue(titles[i]);
			}
			
			//4、操作单元格；将用户列表写入excel
			if(rooms != null){
				for(int j = 0; j < rooms.size(); j++){
					HSSFRow row = sheet.createRow(j+2);
					HSSFCell cell11 = row.createCell(0);
					cell11.setCellValue(rooms.get(j).getManagement().getManagerName());
					HSSFCell cell12 = row.createCell(1);
					cell12.setCellValue(rooms.get(j).getBuilding().getBuildName());
					HSSFCell cell13 = row.createCell(2);
					if(rooms.get(j).getBuildArea()!=null){
					cell13.setCellValue(rooms.get(j).getBuildArea());
					}
					HSSFCell cell14 = row.createCell(3);
					if(rooms.get(j).getRoomStatus()!=null){
					if(rooms.get(j).getRoomStatus()==0){
					cell14.setCellValue("未售");}
					else if(rooms.get(j).getRoomStatus()==1){
						cell14.setCellValue("已售");
						}else if(rooms.get(j).getRoomStatus()==2){
							cell14.setCellValue("未租");
							}else if (rooms.get(j).getRoomStatus()==3){
								cell14.setCellValue("已租");
						}else if (rooms.get(j).getRoomStatus()==4){
							cell14.setCellValue("自用");
					  }else if (rooms.get(j).getRoomStatus()==5){
						cell14.setCellValue("入住");
				     }else if(rooms.get(j).getRoomStatus()==6){
					      cell14.setCellValue("空置");
			           }else if(rooms.get(j).getRoomStatus()==7){
					      cell14.setCellValue("未交付");
			           }
					}
					HSSFCell cell15 = row.createCell(4);
					if(rooms.get(j).getRoomType()!=null){
					if(rooms.get(j).getRoomType()==0){
						cell15.setCellValue("住宅");}
						else if(rooms.get(j).getRoomType()==1){
							cell15.setCellValue("公寓");
					}	else if(rooms.get(j).getRoomType()==2){
						cell15.setCellValue("办公");
				   }	else if(rooms.get(j).getRoomType()==3){
					cell15.setCellValue("厂房");
			       }	else if(rooms.get(j).getRoomType()==4){
				      cell15.setCellValue("仓库");
		           }	else if(rooms.get(j).getRoomType()==5){
		            	cell15.setCellValue("宿舍");
	               }	else if(rooms.get(j).getRoomType()==6){
	             	cell15.setCellValue("其他");
                     }
					}
					HSSFCell cell16 = row.createCell(5);
					if(rooms.get(j).getRoomFloor()!=null){
					cell16.setCellValue(rooms.get(j).getRoomFloor());
					}
					HSSFCell cell17 = row.createCell(6);
					if(rooms.get(j).getRoomNum()!=null){
					cell17.setCellValue(rooms.get(j).getRoomNum());
					}
					HSSFCell cell18 = row.createCell(7);
					if(rooms.get(j).getChargeMan()!=null){
					cell18.setCellValue(rooms.get(j).getChargeMan());
					}
					HSSFCell cell19 = row.createCell(8);
					if(rooms.get(j).getRoomUse()!=null){
					cell19.setCellValue(rooms.get(j).getRoomUse());
					}
					HSSFCell cell110 = row.createCell(9);
					if(rooms.get(j).getDecorate()!=null){
					cell110.setCellValue(rooms.get(j).getDecorate());
					}
					HSSFCell cell111 = row.createCell(10);
					if(rooms.get(j).getPosition()!=null){
					cell111.setCellValue(rooms.get(j).getPosition());
					}
					HSSFCell cell112 = row.createCell(11);
					if(rooms.get(j).getToward()!=null){
					cell112.setCellValue(rooms.get(j).getToward());
					}
					HSSFCell cell113 = row.createCell(12);
					if(rooms.get(j).getRemark()!=null){
					cell113.setCellValue(rooms.get(j).getRemark());
					}
					HSSFCell cell114 = row.createCell(13);
					if(rooms.get(j).getRent()!=null){
					cell114.setCellValue(rooms.get(j).getRent());
					}
					HSSFCell cell115 = row.createCell(14);
					if(rooms.get(j).getManagementFee()!=null){
					cell115.setCellValue(rooms.get(j).getManagementFee());
					}
					HSSFCell cell116 = row.createCell(15);
					if(rooms.get(j).getSinglePrice()!=null){
					cell116.setCellValue(rooms.get(j).getSinglePrice());
					}
					HSSFCell cell117 = row.createCell(16);
					if(rooms.get(j).getSumPrice()!=null){
					cell117.setCellValue(rooms.get(j).getSumPrice());
					}
				}
			}
			//5、输出
			workbook.write(outputStream);
			workbook.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 创建单元格样式
	 * @param workbook 工作簿
	 * @param fontSize 字体大小
	 * @return 单元格样式
	 */
	private static HSSFCellStyle createCellStyle(HSSFWorkbook workbook, short fontSize) {
		HSSFCellStyle style = workbook.createCellStyle();
		style.setAlignment(HSSFCellStyle.ALIGN_CENTER);//水平居中
		style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);//垂直居中
		//创建字体
		HSSFFont font = workbook.createFont();
		font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);//加粗字体
		font.setFontHeightInPoints(fontSize);
		//加载字体
		style.setFont(font);
		return style;
	}
	
		
}
