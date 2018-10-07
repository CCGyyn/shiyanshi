package com.zeng.ezsh.quart.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CronUtil {
	
	public static String getCron(String dateStr){
		Date date = str2date(dateStr);
        SimpleDateFormat sdf = new SimpleDateFormat("ss mm HH dd MM ? yyyy");  
        String formatTimeStr = null;  
        if (date != null) {  
            formatTimeStr = sdf.format(date);  
        }  
        return formatTimeStr;  
    }
	
	public static Date str2date(String dateStr){
		Date date = null;
		try {
			date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(dateStr);
		} catch (ParseException e) {
			System.out.println("注意时间格式");
			e.printStackTrace();
		}
		return date;
	}
	
}
