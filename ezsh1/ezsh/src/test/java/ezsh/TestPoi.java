package ezsh;

import java.io.FileInputStream;
import java.io.FileOutputStream;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.junit.Test;

public class TestPoi {
	 @Test
	   public void test() throws Exception{
		   HSSFWorkbook workBook=new HSSFWorkbook();
		   HSSFSheet createSheet = workBook.createSheet("hello word");
		   HSSFRow row = createSheet.createRow(2);
		   HSSFCell cell = row.createCell(2);
		   cell.setCellValue("hello word");
		   FileOutputStream fileOutputStream=new FileOutputStream("D:\\测试.xls");
		   workBook.write(fileOutputStream);
		   workBook.close();
		   fileOutputStream.close();
	   }
	   @Test
	   public void test02() throws Exception{
		 FileInputStream fileInputStream=new FileInputStream("D:\\测试.xls");
		 HSSFWorkbook workbook=new HSSFWorkbook(fileInputStream);
		 HSSFSheet sheet = workbook.getSheetAt(0);
		 HSSFRow row = sheet.getRow(2);
		 HSSFCell cell = row.getCell(2);
		 String value = cell.getStringCellValue();
		 System.out.println("读取美容为"+value);
		 
		 
	   }
}
