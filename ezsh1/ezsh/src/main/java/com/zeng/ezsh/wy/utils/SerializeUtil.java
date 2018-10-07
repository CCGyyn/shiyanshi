package com.zeng.ezsh.wy.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.UUID;

import org.apache.commons.lang3.StringUtils;

public class SerializeUtil {

	/**
	 * @author qwc 2017年8月16日下午5:23:40
	 * @return String 生产订单编号（年月日格式化+固定8位+当前线程的ID号）
	 */
	public static String OrderSerialize() {
		String date = new SimpleDateFormat("yyyyMMdd").format(new Date());//8
		String seconds = new SimpleDateFormat("HHmmss").format(new Date());//6
		String result = null;
		for (int i = 0; i < 10000; i++) {
			result = date + "000" + getTwo() + "00" + seconds + getTwo()
					+ Thread.currentThread().getId();
		}
		return result;
	}

	/**
	 * @author qwc 2017年8月16日下午5:23:22
	 * @return String 随机产生两位随机数
	 */
	public static String getTwo() {
		Random rad = new Random();
		String result = "";
		for (int i = 0; i < 2; i++) {
			result = result + rad.nextInt(100);
		}
		return result;
	}

	/**
	 * @author qwc 2017年10月21日下午9:36:28
	 * @return String 随机生成3位码
	 */
	public static String getSix() {
		Random rad = new Random();
		String result = "";
		for (int i = 0; i < 6; i++) {
			result = result + rad.nextInt(9);
		}
		return result;
	}

	/**
	 * 生成 uuid， 即用来标识一笔单
	 * 
	 * @return
	 */
	public static String generateUUID() {
		return UUID.randomUUID().toString().replaceAll("-", "").substring(0,
				32);
	}

	public static synchronized String getSixNum(Long reLong) {
		long startTime = System.currentTimeMillis() + reLong;
		long lg = Long.valueOf(
				StringUtils.substring(String.valueOf(startTime), 4, 10));
		long lg1 = Long
				.valueOf(StringUtils.substring(String.valueOf(startTime), 10));
		lg = lg + lg1;
		return String.valueOf(lg);
	}
}
