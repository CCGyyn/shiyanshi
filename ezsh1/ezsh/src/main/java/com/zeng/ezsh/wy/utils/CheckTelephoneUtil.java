package com.zeng.ezsh.wy.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CheckTelephoneUtil {
	
	public static boolean isMobileNum(String telephone){  
		//Pattern p = Pattern.compile("^((13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$"); 
		Pattern p = Pattern.compile("^((13[0-9])|(15[^4,\\D])|(17[0,5-8])|(18[0-9]))\\d{8}$"); 
		Matcher m = p.matcher(telephone); 
	    System.out.println(m.matches()+"---");  
		return m.matches();  
	}  
}
