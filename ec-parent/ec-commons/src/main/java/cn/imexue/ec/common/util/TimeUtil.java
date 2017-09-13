package cn.imexue.ec.common.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.apache.commons.lang3.time.DateUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * 时间/日期工具类
 * 
 */
public class TimeUtil {

    private static final Log logger = LogFactory.getLog(TimeUtil.class);

    private static final String EMPTY = "";

    /** yyyy-MM-dd HH:mm:ss */
    public static final String TARGET_1 = "yyyy-MM-dd HH:mm:ss";

    /** yyyy-MM-dd HH:mm */
    public static final String TARGET_2 = "yyyy-MM-dd HH:mm";

    /** yyyy-MM-dd */
    public static final String TARGET_3 = "yyyy-MM-dd";
    
    /** yyyy年MM月dd日 */
    public static final String TARGET_4 = "yyyy年MM月dd日";

    /** yyyyMMddHHmmss */
    public static final String SOURCE_1 = "yyyyMMddHHmmss";

    /** yyyyMMdd */
    public static final String SOURCE_2 = "yyyyMMdd";
    
    /**
     * 格式化时间,从一种格式转变为另一种格式 <p> 如果源时间对象为String类型，那么认为源格式为 yyyyMMddHHmmss <p> 目标格式为 yyyy-MM-dd HH:mm:ss
     * 
     * @param source 源时间对象，可以为String或是Date类型
     * @return 格式化后的时间字符串，有任何错误返回空字符串
     */
    public static String format(Object source) {
        return format(source, null, null, null);
    }

    /**
     * 格式化时间,从一种格式转变为另一种格式 <p> 如果源时间对象为String类型，那么认为源格式为 yyyyMMddHHmmss
     * 
     * @param source 源时间对象，可以为String或是Date类型
     * @param tfmt 返回的目标格式
     * @return 格式化后的时间字符串，有任何错误返回空字符串
     */
    public static String format(Object source, String tfmt) {
        return format(source, null, tfmt, null);
    }

    /**
     * 格式化时间,从一种格式转变为另一种格式，源时间对象为空的话返回空字符串
     * 
     * @param source 源时间对象，可以为String或是Date类型
     * @param sfmt 源格式，如果source为Date类型，则此参数无用
     * @param tfmt 返回的目标格式
     * @return 格式化后的时间字符串，有任何错误返回空字符串
     */
    public static String format(Object source, String sfmt, String tfmt) {
        return format(source, sfmt, tfmt, null);
    }

    /**
     * 格式化时间,从一种格式转变为另一种格式
     * 
     * @param source 源时间对象，可以为String或是Date类型
     * @param sfmt 源格式，如果source为Date类型，则此参数无用
     * @param tfmt 返回的目标格式
     * @param nullval 如果源为null的话返回的默认值
     * @return 格式化后的时间字符串，有任何错误返回空字符串
     */
    public static String format(Object source, String sfmt, String tfmt, String nullval) {
        tfmt = (tfmt == null) ? TARGET_1 : tfmt;
        sfmt = (sfmt == null) ? SOURCE_1 : sfmt;

        SimpleDateFormat sdf = new SimpleDateFormat(tfmt);
        try {
            if (source == null) {
                return (nullval == null) ? EMPTY : nullval;
            } else if (source instanceof String) {
                SimpleDateFormat parseformat = new SimpleDateFormat(sfmt);
                return sdf.format(parseformat.parse((String) source));
            } else {
                return sdf.format(source);
            }
        } catch (ParseException e) {
            if (logger.isDebugEnabled()) {
                logger.debug("Source Parse Exception: source=" + source + ", sfmt=" + sfmt + ", tfmt=" + tfmt, e);
            }
        } catch (IllegalArgumentException e) {
            if (logger.isDebugEnabled()) {
                logger.debug("Illegal Source Exception: source=" + source + ", sfmt=" + sfmt + ", tfmt=" + tfmt, e);
            }
        } catch (Exception e) {
            if (logger.isDebugEnabled()) {
                logger.debug("Exception: source=" + source + ", sfmt=" + sfmt + ", tfmt=" + tfmt, e);
            }
        }
        return EMPTY;
    }

    /**
     * 返回当前时间
     * 
     * @return yyyyMMddHHmmss格式的当前时间字符串
     */
    public static String now() {
        return now(SOURCE_1);
    }
    
    /**
     * 返回当前日期
     * @return yyyyMMdd格式的当前时间字符串
     */
    public static String nowDate(){
    	return now(SOURCE_2);
    }

    /**
     * 返回当前时间
     * 
     * @param fmt 格式化字符
     * @return fmt指定样式的当前时间字符串
     */
    public static String now(String fmt) {
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat(fmt);
        return sdf.format(cal.getTime());
    }
    
    public static String format(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat(SOURCE_1);
        return sdf.format(date);
    }
    
    public static String formatDate(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat(TARGET_3);
        return sdf.format(date);
    }
    
    /**
     * 获得指定时间之后或者之前N小时的14位时间字符串
     * @param strTime
     * @param hours
     * @return
     */
    public static String addHours(String strTime,int hours){
    	SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
    	try {
	        Date d = sdf.parse(strTime);
	        return TimeUtil.format(DateUtils.addHours(d, hours),SOURCE_1);
        }
        catch (ParseException e) {
	       return "";
        }
    }
    
    public static String addMinutes(String strTime,int amount){
    	SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
    	try {
	        Date d = sdf.parse(strTime);
	        return TimeUtil.format(DateUtils.addMinutes(d, amount),SOURCE_1);
        }
        catch (ParseException e) {
	       return "";
        }
    }
    
  
    
    public static int compareTime(String date1,String date2) {
    	SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
    	try {
	        Date d1 = sdf.parse(date1);
	        Date d2 = sdf.parse(date2);
	        return d1.compareTo(d2);
        }
        catch (ParseException e) {
	       return -2;
        }
    }
    
    /**
     * 获得指定时间之后或者之前N天的14位时间字符串
     * @param strTime 处理的比较时间，14位时间字符串
     * @param days N天，可以是负数
     * @return 14位时间字符串
     */
    public static String addDays(String strTime,int days){
    	SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
    	try {
	        Date d = sdf.parse(strTime);
	        return TimeUtil.format(DateUtils.addDays(d,days),SOURCE_1);
        }
        catch (ParseException e) {
	       return "";
        }
    }
    public static String addDays(int days){
    	Calendar cal = Calendar.getInstance();
    	return TimeUtil.addDays(cal.getTime(),days);
    }
    public static String addDays(Date d,int days){
    	return TimeUtil.format(DateUtils.addDays(d,days),SOURCE_1);
    }
    public static long getTime(String source , String tfmt) throws ParseException{
   	 SimpleDateFormat simpleDateFormat =new SimpleDateFormat(tfmt);
	     Date date=simpleDateFormat .parse(source);
	     return   date.getTime();
   }
    /**
     * 获得指定时间之后的N月的14位时间字符串
     * @param strTime
     * @param m
     * @return 14位时间字符串，异常返回空字符串
     */
    public static String addMonths(String strTime,int m){
    	SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
    	try {
	        Date d = sdf.parse(strTime);
	        return TimeUtil.format(DateUtils.addMonths(d,m),SOURCE_1);
        }
        catch (ParseException e) {
	       return "";
        }
    }
    
    public static String addMonths(int m){
    	Calendar cal = Calendar.getInstance();
    	return TimeUtil.addMonths(cal.getTime(),m);
    }
    
    public static String addMonths(Date d,int m){
    	return TimeUtil.format(DateUtils.addMonths(d, m),SOURCE_1);
    }
    
    /**
     * 获得指定年之后N年的14位时间字符串
     * @param strTime 14时间字符串
     * @param years N年，可以是负数
     * @return 14位时间字符串
     */
    public static String addYears(String strTime,int years){
    	SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
    	try {
	        Date d = sdf.parse(strTime);
	        return TimeUtil.format(DateUtils.addYears(d,years),SOURCE_1);
        }
        catch (ParseException e) {
	       return strTime;
        }
    }
    public static String addYears(int years){
    	Calendar cal = Calendar.getInstance();
    	return TimeUtil.addYears(cal.getTime(),years);
    }    
    public static String addYears(Date d,int years){
    	return TimeUtil.format(DateUtils.addDays(d,years),SOURCE_1);
    }
    
    /**
     * 获得当前是星期几的数字
     *  WEEK_ZH_MAP.put("1", "星期日");
		WEEK_ZH_MAP.put("2", "星期一");
		WEEK_ZH_MAP.put("3", "星期二");
		WEEK_ZH_MAP.put("4", "星期三");
		WEEK_ZH_MAP.put("5", "星期四");
		WEEK_ZH_MAP.put("6", "星期五");
		WEEK_ZH_MAP.put("7", "星期六");
     * @return 表示星期的数字
     */
    public static int getWeek(){
    	Calendar cal = Calendar.getInstance(); // 创建一个日历对象。
		cal.setTime(new Date());
		return cal.get(Calendar.DAY_OF_WEEK);
    }
    
    /**
     * 获取当前的年
     * @return
     */
    public static int getYear(){
    	Calendar cal = Calendar.getInstance(); // 创建一个日历对象。
    	cal.setTime(new Date());
    	return cal.get(Calendar.YEAR);
    }
    
    /**
     * 获取当前的月
     */
    public static int getMonth(){
    	Calendar cal = Calendar.getInstance(); // 创建一个日历对象。
    	cal.setTime(new Date());
    	return cal.get(Calendar.MONTH)+1;
    }

    /**
     * 获取当前的日
     * @return
     */
    public static int getDay(){
    	Calendar cal = Calendar.getInstance(); // 创建一个日历对象。
    	cal.setTime(new Date());
    	return cal.get(Calendar.DATE);
    }
    
   
    
    
	@SuppressWarnings("unused")
	private static String randomDate(String beginDate, String endDate) {
	    try {
	        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	        Date start = format.parse(beginDate);// 开始日期
	        Date end = format.parse(endDate);// 结束日期
	        if (start.getTime() >= end.getTime()) {
	            return null;
	        }
	        long date = random(start.getTime(), end.getTime());
	
	        return format.format(date);
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    return null;
	}
	
	private static long random(long begin, long end) {
	    long rtnn = begin + (long) (Math.random() * (end - begin));
	    if (rtnn == begin || rtnn == end) {
	        return random(begin, end);
	    }
	    return rtnn;
	}
	
	//获得当天0点时间
    public static long getTimesmorning(){
	    Calendar cal = Calendar.getInstance();
	    cal.set(Calendar.HOUR_OF_DAY, 0);
	    cal.set(Calendar.SECOND, 0);
	    cal.set(Calendar.MINUTE, 0);
	    cal.set(Calendar.MILLISECOND, 0);
	    return  cal.getTimeInMillis();
    }
    //获得当天特定点时间
    public static long getTimesmorning(int hour,int min){
    	Calendar cal = Calendar.getInstance();
    	cal.set(Calendar.HOUR_OF_DAY, hour);
    	cal.set(Calendar.SECOND, 0);
    	cal.set(Calendar.MINUTE, min);
    	cal.set(Calendar.MILLISECOND, 0);
    	return  cal.getTimeInMillis();
    }
    
    //获得当天24点时间
    public static long getTimesnight(){
	    Calendar cal = Calendar.getInstance();
	    cal.set(Calendar.HOUR_OF_DAY, 24);
	    cal.set(Calendar.SECOND, 0);
	    cal.set(Calendar.MINUTE, 0);
	    cal.set(Calendar.MILLISECOND, 0);
	    return cal.getTimeInMillis();
    } 
	
    //获取前一天0点时间
    public static long getLastMorningTime(){
    	Calendar cal = Calendar.getInstance();
    	cal.add(Calendar.DAY_OF_MONTH, -1); //天数向前倒一天
    	cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return  cal.getTimeInMillis();
    }
    
    //获取前一天24点时间，即当天0点时间
    public static long getLastNightTime(){
    	Calendar cal = Calendar.getInstance();
    	cal.add(Calendar.DAY_OF_MONTH, -1); //天数向前倒一天
    	cal.set(Calendar.HOUR_OF_DAY, 24);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return  cal.getTimeInMillis();
    }
     public static int getDays(String to,String from){
	      long time1;
		  long time2;
		try {
			time1 = TimeUtil.getTime(from, TimeUtil.TARGET_3);
			time2 = TimeUtil.getTime(to, TimeUtil.TARGET_3);
		} catch (ParseException e) {
			return  0;
		}
		return  (int) ((time1-time2)/(1000 * 60 * 60 * 24));
     }
     public static float getAge(int days){
    	 float a = days/365f;
     	return (float)(Math.round(a*10))/10;
     }
     
     /**  
      * 计算两个日期之间相差的天数  
      * @param smdate 较小的时间 
      * @param bdate  较大的时间 
      * @return 相差天数 
      * @throws ParseException  
      */    
     public static int daysBetween(Date smdate,Date bdate) throws ParseException    
     {    
         SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");  
         smdate=sdf.parse(sdf.format(smdate));  
         bdate=sdf.parse(sdf.format(bdate));  
         Calendar cal = Calendar.getInstance();    
         cal.setTime(smdate);    
         long time1 = cal.getTimeInMillis();                 
         cal.setTime(bdate);    
         long time2 = cal.getTimeInMillis();         
         long between_days=(time2-time1)/(1000*3600*24);  
             
        return Integer.parseInt(String.valueOf(between_days));           
     }  
     
     /** 
     *字符串的日期格式的计算 
     */  
      public static int daysBetween(String smdate,String bdate) throws ParseException{  
 		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
 		Calendar cal = Calendar.getInstance();
 		cal.setTime(sdf.parse(smdate));
 		long time1 = cal.getTimeInMillis();
 		cal.setTime(sdf.parse(bdate));
 		long time2 = cal.getTimeInMillis();
 		long between_days = (time2 - time1) / (1000 * 3600 * 24);

 		return Integer.parseInt(String.valueOf(between_days));
 	}
      
      /** 
       * 获取某年第一天日期 
       */  
      public static Date getCurrYearFirst(int year){  
          Calendar calendar = Calendar.getInstance();  
          calendar.clear();  
          calendar.set(Calendar.YEAR, year);  
          Date currYearFirst = calendar.getTime();  
          return currYearFirst;  
      }  
        
      /** 
       * 获取某年最后一天日期 
       */  
      public static Date getCurrYearLast(int year){  
          Calendar calendar = Calendar.getInstance();  
          calendar.clear();  
          calendar.set(Calendar.YEAR, year);  
          calendar.roll(Calendar.DAY_OF_YEAR, -1);  
          Date currYearLast = calendar.getTime();  
            
          return currYearLast;  
      }  
      
      /**
       * 根据time stamp字符创获取Date型日期
       * @param strTime
       * @return
       * @throws ParseException
       */
      public static Date getDateByTimestamp(String strTime) throws ParseException {
			SimpleDateFormat simpleDateFormat  = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Long createTime = new Long(strTime);
			String strDate = simpleDateFormat.format(createTime);
			Date date = simpleDateFormat.parse(strDate);
			return date;
      }
}
