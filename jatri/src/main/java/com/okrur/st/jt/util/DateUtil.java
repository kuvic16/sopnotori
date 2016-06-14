package com.okrur.st.jt.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
/**
 * @File DateUtil.java: utility for date and time operation 
 * @author shaiful islam palash | kuvic16@gmail.com
 * @CreationDate Nov 22, 2015
 */
public class DateUtil {
	
	public static final String DATE_FORMAT_YYYY_MM_DD = "yyyy-MM-dd";
	public static final String DATE_FORMAT_DD_MM_YYYY = "dd-MM-yyyy";
	public static final String DATE_FORMAT_YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss";
	public static final String DATE_FORMAT_DD_MMM_YY = "dd-MMM-yy";
	
	public static String getDateString(Calendar calender){
		String dateString = "";
		try{
			Date date = calender.getTime();
			//dateString = new SimpleDateFormat(DATE_FORMAT_YYYY_MM_DD).format(date);
			dateString = new SimpleDateFormat(DATE_FORMAT_DD_MM_YYYY).format(date);
		}catch(Throwable t){
			
		}
		return dateString;
	}
	
	public static String formatDateString(String dateString){
		try{
			Calendar cl = getCalender(dateString, DATE_FORMAT_YYYY_MM_DD_HH_MM_SS);
			return getDateString(cl);
		}catch(Throwable t){
			
		}
		return "";
	}
	
	public static String getDateString(Calendar calender, String dateFormat){
		String dateString = "";
		try{
			Date date = calender.getTime();
			dateString = new SimpleDateFormat(dateFormat).format(date);
		}catch(Throwable t){
			
		}
		return dateString;
	}
	
	public static String getTodayDateString(String dateFormat){
		String dateString = "";
		try{
			Calendar calender = Calendar.getInstance();
			Date date = calender.getTime();
			dateString = new SimpleDateFormat(dateFormat).format(date);
		}catch(Throwable t){
			
		}
		return dateString;
	}
	
	public static int getCurrentMonth(){
		int month = -1;
		try{
			Calendar calender = Calendar.getInstance();
			Date date = calender.getTime();
			String dateString = new SimpleDateFormat("MM").format(date);
			month = Integer.parseInt(dateString);
		}catch(Throwable t){
			
		}
		return month;
	}
	
	public static int getCurrentYear(){
		int year = -1;
		try{
			Calendar calender = Calendar.getInstance();
			Date date = calender.getTime();
			String dateString = new SimpleDateFormat("YYYY").format(date);
			year = Integer.parseInt(dateString);
		}catch(Throwable t){
			
		}
		return year;
	}
	
	public static Calendar getCalender(String dateString){
		Calendar calender = null;
		try{
			calender = Calendar.getInstance();
			SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT_YYYY_MM_DD_HH_MM_SS);
			calender.setTime(sdf.parse(dateString));
		}catch(Throwable t){
			calender = null;
		}
		return calender;
	}
	
	public static Calendar getCalender(String dateString, String dateFormat){
		Calendar calender = null;
		try{
			calender = Calendar.getInstance();
			SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
			calender.setTime(sdf.parse(dateString));
		}catch(Throwable t){
			calender = null;
		}
		return calender;
	}
	
	public static Calendar getTodayCalender(String dateFormat){
		Calendar calender = null;
		try{
			calender = Calendar.getInstance();
			SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
			calender.setTime(sdf.parse(getTodayDateString(dateFormat)));
		}catch(Throwable t){
			calender = null;
		}
		return calender;
	}
	
	
	public static void main(String[] args){
		Calendar cal = Calendar.getInstance();
		//SimpleDateFormat sdf = new SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy", Locale.EN_US);
		//System.out.println(new DateUtil().getDateString(cal));
		System.out.println(new DateUtil().getTodayDateString("M"));
		
		//System.out.println(new DateUtil().getDateString(getCalender("2015-12-08 12:01:00")));
	}
}
