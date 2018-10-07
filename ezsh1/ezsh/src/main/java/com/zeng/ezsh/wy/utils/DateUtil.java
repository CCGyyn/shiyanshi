package com.zeng.ezsh.wy.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class DateUtil {
	/**
	 * 变量：日期格式化类型 - 格式:yyyy/MM/dd
	 */
	public static final int DEFAULT = 0;

	/**
	 * 变量：日期格式化类型 - 格式:yyyy/MM
	 */
	public static final int YM = 1;

	/**
	 * 变量：日期格式化类型 - 格式:yyyy-MM-dd
	 * 
	 */
	public static final int YMR_SLASH = 11;

	/**
	 * 变量：日期格式化类型 - 格式:yyyyMMdd
	 * 
	 */
	public static final int NO_SLASH = 2;

	/**
	 * 变量：日期格式化类型 - 格式:yyyyMM
	 * 
	 */
	public static final int YM_NO_SLASH = 3;

	/**
	 * 变量：日期格式化类型 - 格式:yyyy/MM/dd HH:mm:ss
	 * 
	 */
	public static final int DATE_TIME = 4;

	/**
	 * 变量：日期格式化类型 - 格式:yyyyMMddHHmmss
	 * 
	 */
	public static final int DATE_TIME_NO_SLASH = 5;

	/**
	 * 变量：日期格式化类型 - 格式:yyyy/MM/dd HH:mm
	 * 
	 */
	public static final int DATE_HM = 6;

	/**
	 * 变量：日期格式化类型 - 格式:HH:mm:ss
	 * 
	 */
	public static final int TIME = 7;

	/**
	 * 变量：日期格式化类型 - 格式:HH:mm
	 * 
	 */
	public static final int HM = 8;

	/**
	 * 变量：日期格式化类型 - 格式:HHmmss
	 * 
	 */
	public static final int LONG_TIME = 9;
	/**
	 * 变量：日期格式化类型 - 格式:HHmm
	 * 
	 */

	public static final int SHORT_TIME = 10;

	/**
	 * 变量：日期格式化类型 - 格式:yyyy-MM-dd HH:mm:ss
	 */
	public static final int DATE_TIME_LINE = 12;

	public static String dateToStr(Date date, int type) {
		switch (type) {
			case DEFAULT :
				return dateToStr(date);
			case YM :
				return dateToStr(date, "yyyy/MM");
			case NO_SLASH :
				return dateToStr(date, "yyyyMMdd");
			case YMR_SLASH :
				return dateToStr(date, "yyyy-MM-dd");
			case YM_NO_SLASH :
				return dateToStr(date, "yyyyMM");
			case DATE_TIME :
				return dateToStr(date, "yyyy/MM/dd HH:mm:ss");
			case DATE_TIME_NO_SLASH :
				return dateToStr(date, "yyyyMMddHHmmss");
			case DATE_HM :
				return dateToStr(date, "yyyy/MM/dd HH:mm");
			case TIME :
				return dateToStr(date, "HH:mm:ss");
			case HM :
				return dateToStr(date, "HH:mm");
			case LONG_TIME :
				return dateToStr(date, "HHmmss");
			case SHORT_TIME :
				return dateToStr(date, "HHmm");
			case DATE_TIME_LINE :
				return dateToStr(date, "yyyy-MM-dd HH:mm:ss");
			default :
				throw new IllegalArgumentException("Type undefined : " + type);
		}
	}

	public static String dateToStr(Date date, String pattern) {
		if (date == null || date.equals(""))
			return null;
		SimpleDateFormat formatter = new SimpleDateFormat(pattern);
		return formatter.format(date);
	}

	public static String dateToStr(Date date) {
		return dateToStr(date, "yyyy/MM/dd");
	}

	/**
	 * @author qwc 2017年9月22日下午4:05:55
	 * @return String 获取前月的第一天
	 */
	public static String theSameMonthFirstDay() {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Calendar cale = Calendar.getInstance();
		cale.add(Calendar.MONTH, 0);
		cale.set(Calendar.DAY_OF_MONTH, 1);
		return format.format(cale.getTime());
	}

	/**
	 * @description 获取前月的最后一天
	 *
	 * @auhtor qwc 2017年9月22日下午4:06:03
	 * @return String
	 */
	public static String theSameMonthLastDay() {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Calendar cale = Calendar.getInstance();
		cale.add(Calendar.MONTH, 1);
		cale.set(Calendar.DAY_OF_MONTH, 0);
		return format.format(cale.getTime());
	}

	/**
	 * @author qwc 2017年11月26日下午4:31:25
	 * @param format
	 * @return 字符串转化成时间戳 String
	 */
	public static String dateTimeStamp(String dateStr, String format) {
		try {
			SimpleDateFormat sdf = new SimpleDateFormat(format);
			return String.valueOf(sdf.parse(dateStr).getTime() / 1000);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}

	/**
	 * @author qwc 2017年11月29日下午4:13:03
	 * @param seconds
	 * @param format
	 * @return 时间戳转日期字符串 String
	 */
	public static String timeStampDate(String seconds, String format) {
		if (seconds == null || seconds.isEmpty()
				|| seconds.equals("null")) { return ""; }
		if (format == null || format.isEmpty()) {
			format = "yyyyMMddHHmmss";
		}
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		return sdf.format(new Date(Long.valueOf(seconds + "000")));
	}

	public static String todayLastTime() {
		long current = System.currentTimeMillis();// 当前时间毫秒数
		long zero = current / (1000 * 3600 * 24) * (1000 * 3600 * 24)
				- TimeZone.getDefault().getRawOffset();// 今天零点零分零秒的毫秒数
		long twelve = zero + 24 * 60 * 60 * 1000 - 1000;// 今天23点59分59秒的毫秒数
		SimpleDateFormat dateFormat = new SimpleDateFormat(
				"yyyy-MM-dd HH:mm:ss");
		return dateFormat.format(twelve);
	}

	/**
	 * @description 获取几分钟后的时间
	 *
	 * @auhtor qwc 2018年10月2日 上午12:22:06
	 * @param minute
	 * @return String
	 */
	public static String gtTimeAfterMinutesUpToNow(int minute) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		Calendar nowTime = Calendar.getInstance();
		nowTime.add(Calendar.MINUTE, minute);
		return sdf.format(nowTime.getTime());
	}

	/**
	 * @description 获取多个月后的月份
	 *
	 * @auhtor qwc 2018年12月11日 上午9:12:05
	 * @param month
	 * @return String
	 */
	public static String addXMonth(int month) {
		Calendar calendar = Calendar.getInstance();// 得到Calendar实例
		calendar.add(Calendar.MONTH, month);// 把月份加三个月
		Date starDate = calendar.getTime();// 得到时间赋给Data
		return DateUtil.dateToStr(starDate, DateUtil.YM_NO_SLASH);
	}

	//获取当月的最后一天
	public static String getNowMonthLastDay(){
		// 获取Calendar
		Calendar calendar = Calendar.getInstance();
		// 设置时间,当前时间不用设置
		// calendar.setTime(new Date());
		// 设置日期为本月最大日期
		calendar.set(Calendar.DATE, calendar.getActualMaximum(Calendar.DATE));

		// 打印
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		return format.format(calendar.getTime());
	}
}
