/**
 * FastFunCommon
 */
package com.klw.fastfun.pay.common.tool;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @author klwplayer.com
 *
 * 2015年3月30日
 */
public class DateTool {
	private static SimpleDateFormat format = new SimpleDateFormat("yyyyMMddhhmmss");
	private static SimpleDateFormat format2 = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
	private static SimpleDateFormat format3 = new SimpleDateFormat("yyyy-MM-dd");
	private static SimpleDateFormat format4 = new SimpleDateFormat("yyyyMMdd");
	private static SimpleDateFormat format5 = new SimpleDateFormat("yyyyMMddHHmmss");
	
	/***
	 * 提取当前时间的月份
	 */
	public static String getMonth() {
		Date now = Calendar.getInstance().getTime();
		String dateStr = format.format(now);
		return dateStr.substring(4, 6);
	}
	
	public static Date convertString(String value) {
		Date now = new Date();
		try {
			now = format.parse(value);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return now;
	}
	
	/** 
	 * 获取精确到秒的时间戳 
	 * @param date 
	 * @return 
	 */  
	public static String getSecondTimestampTwo(Date date){  
		if (null == date) {  
			return "0";  
		}  
		String timestamp = String.valueOf(date.getTime()/1000);  
		return timestamp;  
	}
	
	/** 
	 * 获取精确到毫秒秒的时间戳 
	 * @param date 
	 * @return 
	 */  
	public static String getTimestamp(Date date){  
	    if (null == date) {  
	        return "0";  
	    }  
	    String timestamp = String.valueOf(date.getTime());  
	    return timestamp;  
	}
	
	/***
	 * 获取当前时间戳(24小时制)
	 */
	public static String getNow() {
		Calendar cal = Calendar.getInstance();
		return format5.format(cal.getTime());
	}
	
	public static String convertDate(Date date) {
		return format2.format(date);
	}
	
	/***
	 * 获取当前月份第一天
	 */
	public static String getMonFirstDay() {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.MONTH, 0);
		cal.set(Calendar.DAY_OF_MONTH, 1);
		return format3.format(cal.getTime());
	}
	
	/***
	 * 获取当前月份第一天
	 */
	public static String getMonFirstDayNum() {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.MONTH, 0);
		cal.set(Calendar.DAY_OF_MONTH, 1);
		return format4.format(cal.getTime());
	}
	
	/***
	 * 获取下月第一天
	 */
	public static String getNextMonFirDay() {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.MONTH, 1);
		cal.set(Calendar.DAY_OF_MONTH, 1);
		return format3.format(cal.getTime());
	}
	
	/***
	 * 获取下月第一天
	 */
	public static String getNextMonFirDayNum() {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.MONTH, 1);
		cal.set(Calendar.DAY_OF_MONTH, 1);
		return format4.format(cal.getTime());
	}
	
	/***
	 * 获取今天
	 */
	public static String getToday() {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DAY_OF_MONTH, 0);
		return format3.format(cal.getTime());
	}
	
	/***
	 * 获取今天
	 */
	public static String getTodayNum() {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DAY_OF_MONTH, 0);
		return format4.format(cal.getTime());
	}
	
	/***
	 * 获取今天
	 */
	public static String getTodayTime() {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DAY_OF_MONTH, 0);
		return format.format(cal.getTime());
	}
	
	/***
	 * 获取明天
	 */
	public static String getTomorrow() {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DAY_OF_MONTH, 1);
		return format3.format(cal.getTime());
	}
	
}
