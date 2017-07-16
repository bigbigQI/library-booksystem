package com.zhang.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import org.apache.commons.lang.time.DurationFormatUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 日期工具类
 * getMonthSpace
 * @author chj DateUtil.java 2013-12-11
 */
public class DateUtils {

	private static Logger log = LoggerFactory.getLogger(DateUtils.class);

	public static String DEFAULT_FORMAT = "yyyy-MM-dd";
	
	public static String FORMAT_STR1 = "yyyy.MM.dd";

	private static String timePattern = "HH:mm";

	public static String timePattern2 = "yyyyMMddHHmmss";

	public static String dateTimePattern = "yyyy-MM-dd HH:mm:ss";
	
	

	/**
	 * 根据日期格式，返回日期按DEFAULT_FORMAT格式转换后的字符串
	 * 
	 * @param aDate
	 *            日期对象
	 * @return 格式化后的日期的页面显示字符串
	 */
	public static final String getDate(Date aDate) {
		SimpleDateFormat df = null;
		String returnValue = "";

		if (aDate != null) {
			df = new SimpleDateFormat(DEFAULT_FORMAT);
			returnValue = df.format(aDate);
		}

		return (returnValue);
	}

	public static final String parseToDateTimeStr(Date aDate) {
		SimpleDateFormat df = null;
		String returnValue = "";

		if (aDate != null) {
			df = new SimpleDateFormat(dateTimePattern);
			returnValue = df.format(aDate);
		}

		return (returnValue);
	}

	/**
	 * 计算两个日期之间相差的天数
	 * 
	 * @param smdate
	 *            较小的时间
	 * @param bdate
	 *            较大的时间
	 * @return 相差天数
	 * @throws ParseException
	 */
	public static int daysBetween(Date smdate, Date bdate){
		try {
			SimpleDateFormat sdf = new SimpleDateFormat(DEFAULT_FORMAT);
			smdate = sdf.parse(sdf.format(smdate));
			bdate = sdf.parse(sdf.format(bdate));
			Calendar cal = Calendar.getInstance();
			cal.setTime(smdate);
			long time1 = cal.getTimeInMillis(); // smdate
			cal.setTime(bdate);
			long time2 = cal.getTimeInMillis(); // bdate
			long between_days = (time2 - time1) / (1000 * 3600 * 24);

			return Integer.parseInt(String.valueOf(between_days));
		} catch (ParseException e) {
			throw new RuntimeException("daysBetween error"+smdate+";"+bdate,e);
		}
	}

	

	/**
	 * 计算两个日期之间相差的月数
	 * 
	 * @param smdate
	 *            较小的时间
	 * @param bdate
	 *            较大的时间
	 * @return 相差月数
	 * @throws ParseException
	 */
	public static int getMonthSpace(Date smdate, Date bdate)
			throws ParseException {
		return Integer.parseInt(DurationFormatUtils.formatPeriod(smdate.getTime(), bdate.getTime(), "M"));
	}

	public static int getMonth(Date smdate, Date bdate) throws ParseException {

		int result = 0;

		Calendar c1 = Calendar.getInstance();
		Calendar c2 = Calendar.getInstance();

		c1.setTime(smdate);
		c2.setTime(bdate);

		result = c2.get(Calendar.DAY_OF_MONTH) - c1.get(Calendar.DAY_OF_MONTH);

		return result == 0 ? 1 : Math.abs(result);

	}

	/**
	 * 按照日期格式，将字符串解析为日期对象
	 * 
	 * @param aMask
	 *            输入字符串的格式
	 * @param strDate
	 *            一个按aMask格式排列的日期的字符串描述
	 * @return Date 对象
	 * @see java.text.SimpleDateFormat
	 * @throws ParseException
	 */
	public static final Date convertStringToDate(String aMask, String strDate)
			throws ParseException {
		SimpleDateFormat df = null;
		Date date = null;
		df = new SimpleDateFormat(aMask);

		if (log.isDebugEnabled()) {
			log.debug("converting '" + strDate + "' to date with mask '"
					+ aMask + "'");
		}

		try {
			date = df.parse(strDate);
		} catch (ParseException pe) {
			// log.error("ParseException: " + pe);
			throw new ParseException(pe.getMessage(), pe.getErrorOffset());
		}

		return (date);
	}

	/**
	 * This method returns the current date time in the format: yyyy/MM/dd HH:MM
	 * a
	 * 
	 * @param theTime
	 *            the current time
	 * @return the current date/time
	 */
	public static String getTimeNow(Date theTime) {
		return getDateTime(timePattern, theTime);
	}

	/**
	 * This method returns the current date in the format: yyyy-MM-dd
	 * 
	 * @return the current date
	 * @throws ParseException
	 */
	public static Calendar getToday() throws ParseException {
		Date today = new Date();
		SimpleDateFormat df = new SimpleDateFormat(DEFAULT_FORMAT);

		// This seems like quite a hack (date -> string -> date),
		// but it works ;-)
		String todayAsString = df.format(today);
		Calendar cal = new GregorianCalendar();
		cal.setTime(convertStringToDate(todayAsString));

		return cal;
	}

	/**
	 * This method generates a string representation of a date's date/time in
	 * the format you specify on input
	 * 
	 * @param aMask
	 *            the date pattern the string is in
	 * @param aDate
	 *            a date object
	 * @return a formatted string representation of the date
	 * 
	 * @see java.text.SimpleDateFormat
	 */
	public static final String getDateTime(String aMask, Date aDate) {
		SimpleDateFormat df = null;
		String returnValue = "";

		if (aDate == null) {
			log.error("aDate is null!");
		} else {
			df = new SimpleDateFormat(aMask);
			returnValue = df.format(aDate);
		}

		return (returnValue);
	}

	/**
	 * 根据日期格式，返回日期按DEFAULT_FORMAT格式转换后的字符串
	 * 
	 * @param aDate
	 * @return
	 */
	public static final String convertDateToString(Date aDate) {
		return getDateTime(DEFAULT_FORMAT, aDate);
	}

	/**
	 * 按照日期格式，将字符串解析为日期对象
	 * 
	 * @param strDate
	 *            (格式 yyyy-MM-dd)
	 * @return
	 * 
	 * @throws ParseException
	 */
	public static Date convertStringToDate(String strDate)
			throws ParseException {
		Date aDate = null;

		try {
			if (log.isDebugEnabled()) {
				log.debug("converting date with pattern: " + DEFAULT_FORMAT);
			}

			aDate = convertStringToDate(DEFAULT_FORMAT, strDate);
		} catch (ParseException pe) {
			log.error("Could not convert '" + strDate
					+ "' to a date, throwing exception");
			
			throw new ParseException(pe.getMessage(), pe.getErrorOffset());

		}

		return aDate;
	}

	/**
	 * 时间相加
	 * 
	 * @param date
	 * @param day
	 * @return
	 */
	public static Date addDay(Date date, int day) {
		GregorianCalendar calendar = new GregorianCalendar();
		calendar.setTime(date);
		calendar.add(Calendar.DAY_OF_YEAR, day);
		return calendar.getTime();
	}

	public static Date addHour(Date date, int hour) {
		GregorianCalendar calendar = new GregorianCalendar();
		calendar.setTime(date);
		calendar.add(Calendar.HOUR, hour);
		return calendar.getTime();
	}

	/**
	 * 月相加
	 * 
	 * @param date
	 * @param month
	 * @return
	 */
	public static Date addMonth(Date date, int month) {
		GregorianCalendar calendar = new GregorianCalendar();
		calendar.setTime(date);
		calendar.add(Calendar.MONTH, month);
		return calendar.getTime();
	}

	public static int getDay(Date d) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(d);
		return calendar.get(Calendar.DATE);
	}

	/**
	 * 格式化日期
	 * 
	 * @param date
	 *            日期对象
	 * @return String 日期字符串
	 */

	/**
	 * 本年的第一天
	 */
	public static String getYearFirst(Integer year) {
		return formatDate(getCurrYearFirst(year));
	}

	/**
	 * 本年的最后一天
	 * 
	 * @author cjx 2013-2-25 DateUtil String
	 * @param year
	 * @return
	 * 
	 *         getYearLast
	 */
	public static String getYearLast(Integer year) {
		return formatDate(getCurrYearLast(year));
	}

	public static Integer getYear() {
		Date date = new Date();
		SimpleDateFormat f = new SimpleDateFormat("yyyy");
		String year = f.format(date);
		return Integer.valueOf(year);

	}

	/**
	 * 
	 * return yyyy-MM-dd
	 * 
	 * @param date
	 * @return
	 */
	public static String formatDate(Date date) {
		SimpleDateFormat f = new SimpleDateFormat(DEFAULT_FORMAT);
		String sDate = f.format(date);
		return sDate;
	}

	public static String formatDate(String formatPattern, Date date) {
		SimpleDateFormat f = new SimpleDateFormat(formatPattern);
		String sDate = f.format(date);
		return sDate;
	}

	/**
	 * 获取某年第一天日期
	 * 
	 * @param year
	 *            年份
	 * @return Date
	 */
	public static Date getCurrYearFirst(int year) {
		Calendar calendar = Calendar.getInstance();
		calendar.clear();
		calendar.set(Calendar.YEAR, year);
		Date currYearFirst = calendar.getTime();
		return currYearFirst;
	}

	/**
	 * 只保留时间中的年月日
	 * @param d
	 * @return
	 */
	public static Date preciseToDay(Date d) {
		String day = getDateTime(DEFAULT_FORMAT, d);//yyyy-MM-dd 格式转换成字符串
		try {
			return convertStringToDate(DEFAULT_FORMAT, day);
		} catch (Exception e) {
			
			return null;
		}
	}


	/**
	 * 获取某年最后一天日期
	 * 
	 * @param year
	 *            年份
	 * @return Date
	 */
	public static Date getCurrYearLast(int year) {
		Calendar calendar = Calendar.getInstance();
		calendar.clear();
		calendar.set(Calendar.YEAR, year);
		calendar.roll(Calendar.DAY_OF_YEAR, -1);
		Date currYearLast = calendar.getTime();

		return currYearLast;
	}
	
	public static String getLastDay(){

		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		  Calendar calendar = Calendar.getInstance();//此时打印它获取的是系统当前时间
		        calendar.add(Calendar.DATE, -1); //
		        Date theDate = calendar.getTime();
		        String s = df.format(theDate);
		        System.out.println(s);

		      return s;

		}
	
	/**
	 * 获取本月日期
	 * @return
	 * 2014-7-24
	 * fx
	 */
	public static String[] findMonthDate(){
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd");  
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMinimum(Calendar.DAY_OF_MONTH));
		String startDate=format.format(calendar.getTime());
		calendar.set(Calendar.DATE, calendar.getActualMaximum(Calendar.DATE));
		String endDate=format.format(calendar.getTime());
		String[] monthDate = {startDate,endDate};
		return monthDate;
	}
	
	 /**
	  * 得到上月1号到月底日期
	  * @return String[] 
	  * 2014-7-24
	  * fx
	  */
	 public static String[] findLastMonth() {
		  SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		  Calendar cal = Calendar.getInstance();
		  GregorianCalendar gcLast = (GregorianCalendar) Calendar.getInstance();
		  Calendar calendar = Calendar.getInstance();
		  calendar.setTime(new Date());
		  calendar.add(Calendar.MONTH, -1);
		  Date theDate = calendar.getTime();
		  gcLast.setTime(theDate);
		  gcLast.set(Calendar.DAY_OF_MONTH, 1);
		  String day_first_prevM = df.format(gcLast.getTime());
		  StringBuffer str = new StringBuffer().append(day_first_prevM).append(
		    " 00:00:00");
		  day_first_prevM = str.toString();

		  calendar.add(cal.MONTH, 1);
		  calendar.set(cal.DATE, 1);
		  calendar.add(cal.DATE, -1);
		  String day_end_prevM = df.format(calendar.getTime());
		  StringBuffer endStr = new StringBuffer().append(day_end_prevM).append(
		    " 23:59:59");
		  day_end_prevM = endStr.toString();

		  String[] map = {day_first_prevM,day_end_prevM};
		  return map;
		 }
	 
	 /**
	  * 得到上周一到周日日期
	  * @return String[] 0 上周一 1 上周日
	  * 2014-7-24
	  * fx
	  */
	public static String[] getLastWeekDay(){
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		String[] day = new String[2];
		Calendar cal=Calendar.getInstance();
		cal.set(Calendar.DAY_OF_WEEK, 1);
		cal.add(Calendar.WEEK_OF_MONTH, -1);
		day[0]=sdf.format(cal.getTime());
		cal.set(Calendar.DAY_OF_WEEK, 2);
		day[1]=sdf.format(cal.getTime());
		return day;
	}
	/**
	 * 获取上一周的时间
	 * @param date
	 * @return
	 */
	 public static String[] getNewLastWeekDay(Date date){
		 String[] day = new String[2];
		 SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		 Calendar cl = Calendar.getInstance();
         cl.setTime(date); //nd为传过来的日期，Date 型，此步可省为当前日期
         int day_of_week = cl.get(Calendar.DAY_OF_WEEK) - 1;
         if (day_of_week == 0)
          day_of_week = 7;
         cl.add(Calendar.WEEK_OF_MONTH, -1); //idx 参数，0为当前，1为下周 -1为上周以此类推      
         cl.add(Calendar.DATE, -day_of_week + 1);
         day[0] = sdf.format(cl.getTime());//周一
	     cl.add(Calendar.DATE, +6);
	     day[1]=sdf.format(cl.getTime());//周日
	     return day;
	 }
	
	 /**
	  * 得到本周一到周日日期
	  * @return String[] 0 本周一 1 本周日
	  * 2014-7-24
	  * fx
	  */
	public static String [] getWeekDay(){
		  SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		  String[] day=new String[2];
		  Calendar c = Calendar.getInstance();
		  int dayofweek = c.get(Calendar.DAY_OF_WEEK) - 1;
		  if (dayofweek == 0)
		  dayofweek = 7;
		  c.add(Calendar.DATE, -dayofweek + 1);
		  day[0]=sdf.format(c.getTime());
		  int dayweek = c.get(Calendar.DAY_OF_WEEK) - 1;
		  if (dayweek == 0)
			  dayweek = 7;
		  c.add(Calendar.DATE, -dayweek + 7);
		  day[1]=sdf.format(c.getTime());
		  
		  return day;
	}
	/**
	 * 计算连续天数
	 * @param days
	 * @return
	 */
	public static int calContinueDays(List<Date> days){
		int continueDay = 0;
		Date lDay = null;
		for(Date day : days){
			if(lDay != null){
				if(!isContinueDay(day, lDay)){
					return continueDay;
				}
			}
			lDay = day;
			continueDay ++;
		}
		return continueDay;
	}
	public static boolean isContinueDay(Date preDay,Date curDay){
		String d1 = formatDate(addDay(preDay,1));
		String d2 = formatDate(curDay);
		
		return d1.equals(d2);
	}
	public static void main(String[] args) throws Exception {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date sdate = sdf.parse("2015-01-01 14:00:00");
		Date bdate = sdf.parse("2015-01-01 13:00:00");
		System.out.println(DateUtils.daysBetween(sdate, bdate));
	}
}
