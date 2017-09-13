package cn.imexue.ec.common.util;

import java.sql.Timestamp;
import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;


/**
 * Date Utility
 * @author Li Jianfeng
 * @version 1.2
 */
public class DateUtil extends DateUtils{
    private DateUtil () {
    }

    public static final String DEFAULT_DATE_FORMAT = "yyyy-MM-dd";

    public static final String DATE_FORMAT_YMD = "yyyy/MM/dd";
    
    public static final String DATE_FORMAT_CN = "yyyy年MM月dd日";
    
    public static final String DATE_FORMAT_YMD_WITHOUT_SLASH = "yyyyMMdd";
    
    public static final String DATE_FORMAT_YMDHMS_WITHOUT_SLASH = "yyyyMMddHHmmss";
    
    public final static String DATE_FORMAT_YMDHMS = "yyyyMMddHHmmssSSS";

    public static final String DEFAULT_TIME_FORMAT = "HH:mm:ss";

    public static final String DEFAULT_TIME_FORMAT_SHORT = "HH:mm";
    
    public static final String TIME_STR_ONE_ZERO = "0";

    public static final String TIME_STR_TWO_ZERO = "00";

    private static String default_timezone = "CST";

    public final static String TIMESTAMP_MILLIS_FORMAT = "yyyy-MM-dd HH:mm:ss.SSS";
    
    public final static String FULL_FORMAT = "yyyy-MM-dd HH:mm:ss";

    /**
     * Returns the date instance based on the current system date and default
     * timezone.
     * 
     * @return Current System date.
     */
    public static Date getDate () {
        TimeZone tz = TimeZone.getTimeZone(default_timezone);
        Calendar calendar = Calendar.getInstance(tz);
        return calendar.getTime();
    }

    /**
     * Return the Date instance with the provided year, month ( 1 - 12 ), and
     * day ( 1 - 31 ) values.
     * <p/>
     * The date value will roll over when given a value that is greater than the
     * max possible. Eg. when getDate( 2002, 10, 32 ) is provided, the Date
     * instance will be 1st Nov 2002.
     * <p/>
     * 
     * @param year Year
     * @param month Month ( 1 - 12 )
     * @param day Day( 1 - 31 )
     * @return The Date instance created.
     */
    public static Date getDate (int year, int month, int day) {
        Calendar cal = Calendar.getInstance();

        // Clear all fields first
        cal.clear();

        cal.set(year, month - 1, day);

        return cal.getTime();
    }

    /**
     * Format the input date String to a date in the following format:
     * dd/MM/yyyy.
     * 
     * @param iDate the date String to be formatted.
     * @return the formatted date, return null if input string is null or empty.
     */
    public static Date parseDate (String iDate) {
        return parseDate(iDate, DEFAULT_DATE_FORMAT);
    }

    /**
     * Format the input date String to a date in the pass in date format.
     * 
     * @param iDate the date String to be formatted.
     * @param sDatePattern format pattern
     * @return the formatted date, return null if input string is null or empty.
     */
    public static Date parseDate (String iDate, String sDatePattern) {
        try {
            if (iDate == null || iDate.trim().equals("")) {
                return null;
            }

            SimpleDateFormat oFormat = new SimpleDateFormat(sDatePattern);
            oFormat.setLenient(false);
            return oFormat.parse(iDate);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Format the input date to a date string in the following format:
     * dd/MM/yyyy.
     * 
     * @param iDate the date value to be formatted into a date string.
     * @return the formatted date time string; an empty String if the input date
     *         is <code>null</code> or if there is error during formatting.
     */
    public static String formatDate (Date iDate) {
        return formatDate(iDate, DEFAULT_DATE_FORMAT);
    }

    /**
     * Format the input date to a date string in the pass in date pattern.
     * 
     * @param iDate the date value to be formatted into a date string.
     * @param sDatePattern pass in date pattern.
     * @return the formatted date time string; an empty String if the input date
     *         is <code>null</code> or if there is error during formatting.
     */
    public static String formatDate (Date iDate, String sDatePattern) {
        if (iDate == null) {
            return null;
        }
        SimpleDateFormat oFormat = new SimpleDateFormat(sDatePattern);
        oFormat.setLenient(false);
        return oFormat.format(iDate);
    }

    /**
     * set input time to the input date.
     * 
     * @param iDate input date
     * @param iTime input time
     * @return Date with input time.
     */
    public static Date setTime (Date iDate, String iTime) {
        if (iDate == null) {
            return null;
        }
        if (iTime == null) {
            return iDate;
        }
        Calendar oCalendar = Calendar.getInstance();
        oCalendar.setTime(iDate);
        SimpleDateFormat oFormat = new SimpleDateFormat(DEFAULT_TIME_FORMAT);
        oFormat.setLenient(false);
        try {
            Date oDate1 = oFormat.parse(iTime);
            Calendar oCalendar1 = Calendar.getInstance();
            oCalendar1.setTime(oDate1);
            oCalendar.set(Calendar.HOUR_OF_DAY, oCalendar1
                    .get(Calendar.HOUR_OF_DAY));
            oCalendar.set(Calendar.MINUTE, oCalendar1.get(Calendar.MINUTE));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return oCalendar.getTime();
    }

    /**
     * get input date's time with default time format.
     * 
     * @param oDate input date
     * @return time string with default foramt.
     */
    public static String formatTime (Date oDate) {
        if (oDate == null) {
            return null;
        }
        SimpleDateFormat oFormat = new SimpleDateFormat(DEFAULT_TIME_FORMAT);
        oFormat.setLenient(false);
        return oFormat.format(oDate);
    }

    /**
     * Returns the timestamp instance based on the current system date and
     * default timezone.
     * 
     * @return the current timestamp
     */
    public static Timestamp getCurrentTimestamp () {
        return new Timestamp(getDate().getTime());
    }

    /**
     * Returns the total number of days for the month and year (i.e. 28, 29, 30
     * or 31)
     * 
     * @param iMonth input month
     * @param iYear input year
     * @return number of days
     */
    public static int getTotalDaysOfMonth (int iMonth, int iYear) {
        if (iMonth == 1 || iMonth == 3 || iMonth == 5 || iMonth == 7
                || iMonth == 8 || iMonth == 10 || iMonth == 12) {
            return 31;
        } else if (iMonth == 4 || iMonth == 6 || iMonth == 9 || iMonth == 11) {
            return 30;
        } else if (iYear % 4 != 0) {
            return 28;
        } else if (iYear % 100 != 0) {
            return 28;
        } else {
            return 29;
        }
    }

    /**
     * Return the date x months later. e.g. current date is 2010-1-1
     * addMonthsToDate(date,3) is 2010-4-1 If months ago is required, set the
     * monthNum parameter to negative
     * 
     * @author lijianfeng
     * @param date input date
     * @param monthNum input the number of month
     * @return new date
     */
    public static Date addMonthsToDate (Date date, int monthNum) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.MONTH, monthNum);
        return c.getTime();
    }
    
    /**
     * Return the date x days later. e.g. current date is 2010-1-1
     * addDaysToDate(date,3) is 2010-3-5 If days ago is required, set the dayNum
     * parameter to negative
     * 
     * @author lijianfeng
     * @param date input date
     * @param dayNum input the number of day
     * @return new date
     */
    public static Date addDaysToDate (Date date, int dayNum) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.DATE, dayNum);
        return c.getTime();
    }

    /**
     * The method will compares input date with system date (excluding the HH MM
     * SS).
     * 
     * @param date1 date
     * @return int int value
     */
    public static int compareSystemDate (Date date1) {
        Date date2 = new Date(System.currentTimeMillis());
        return compareDates(date1, date2);
    }

    /**
     * The method will compares 2 dates (excluding the HH MM SS).
     * 
     * @param date1 1st date parameter
     * @param date2 2nd date parameter
     * @return returns -1 if 1st date parameter is earlier than 2nd date
     *         parameter retuns 0 if both dates parameter is the same day retuns
     *         1 if 1st date parameter is later than 2nd date parameter
     */
    public static int compareDates (Date date1, Date date2) {
        if ((date1 == null) && (date2 == null)) {
            return 0;
        }

        if (date1 == null) {
            return -1;
        }

        if (date2 == null) {
            return 1;
        }

        String strFormat = "yyyyMMdd";
        SimpleDateFormat dateFormat = new SimpleDateFormat(strFormat);

        int intDate1 = Integer.parseInt(dateFormat.format(date1));
        int intDate2 = Integer.parseInt(dateFormat.format(date2));

        if (intDate1 == intDate2) {
            return 0;
        }

        if (intDate1 > intDate2) {
            return 1;
        }

        return -1;
    }

    /**
     * The method will compares 2 times (including the HH MM SS).
     * 
     * @param time1 1st date parameter
     * @param time2 2nd date parameter
     * @return returns -1 if 1st date parameter is earlier than 2nd date
     *         parameter retuns 0 if both dates parameter is the same day retuns
     *         1 if 1st date parameter is later than 2nd date parameter
     */
    public static int compareTimes (Date time1, Date time2) {
        if ((time1 == null) && (time2 == null)) {
            return 0;
        }

        if (time1 == null) {
            return -1;
        }

        if (time2 == null) {
            return 1;
        }

        String strFormat = "yyyyMMddHHmmssSSS";
        SimpleDateFormat dateFormat = new SimpleDateFormat(strFormat);

        Long t1 = Long.parseLong(dateFormat.format(time1));
        Long t2 = Long.parseLong(dateFormat.format(time2));

        if (t1.equals(t2)) {
            return 0;
        }

        if (t1 > t2) {
            return 1;
        }else{

        	return -1;
        }
    } 
    
    /**
     * Tests the input value to ensure that a valid Date instance can be created
     * from it. Roll over dates are not allowed here and will return a false
     * value. Eg. isValidDate(2002, 10, 32) will return false.
     * <p/>
     * 
     * @param year The year value.
     * @param month The month value. ( 1 - 12 )
     * @param day The day value. ( 1 - 31 )
     * @return True if all values can be used to create a single valid Date
     *         instance.
     */
    public static boolean isValidDate (int year, int month, int day) {

        if (day <= 0 || month <= 0 || year <= 0)
            return false;
        if (month > 12 || day > 31)
            return false;

        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, year);
        cal.set(Calendar.MONTH, month - 1);

        // Find the maximum field value possible for the day with the year and
        // month.
        int maxDay = cal.getActualMaximum(Calendar.DAY_OF_MONTH);

        return (day <= maxDay);
    }

    /**
     * Tests the input string to ensure that a valid Date instance can be
     * created according to the date format specified in the System property.
     * <p/>
     * If the properties file is not available or the dateformat property has
     * not been specified, the default format "dd/MM/yyyy" will be used.
     * <p/>
     * 
     * @param dateStr A date string.
     * @return True if it conforms to the system date format; False otherwise.
     */
    public static boolean isValidDate (String dateStr) {

        StringUtil.deNull(dateStr);
        if (dateStr.length() != 10) {
            return false;
        }

        boolean validDelimiter = (StringUtil.getCount(dateStr, '-') == 2
                || StringUtil.getCount(dateStr, '/') == 2 || StringUtil
                .getCount(dateStr, '.') == 2);

        if (!validDelimiter) {
            return false;
        }

        String dd = dateStr.substring(0, 2);
        String mm = dateStr.substring(3, 5);
        String yyyy = dateStr.substring(6, 10);

        try {
            return isValidDate(Integer.parseInt(yyyy), Integer.parseInt(mm),
                    Integer.parseInt(dd));
        } catch (Throwable t) {
            return false;
        }

    }

    /**
     * Tests if the inputs are valid time. When the ampm parameter is true, the
     * input hour will be tested for 12-hour format ( 1 ? 12 ). When it is
     * false, the input hour will be tested for 24-hour format ( 0 ? 23 ).
     * <p/>
     * 
     * @param hour The Hour value. ( 0 - 23 )
     * @param minute The Minute value. ( 0 - 59 )
     * @return True if the time inputs can be used to create a valid time
     *         instance.
     */
    public static boolean isValidTime (int hour, int minute) {
        return minute >= 0 && minute <= 59 && hour >= 0 && hour <= 23;
    }

    /**
     * Set the time component as the last seconds of the day.
     * <p/>
     * The Time Component of the date returned will be set to 23:59:59.
     * <p/>
     * 
     * @param date The Date to get the last seconds
     * @return The date with the time component set to the last seconds of the
     *         day.
     */
    public static Date getEndOfDay (Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);

        // Clear the time component
        cal.set(Calendar.HOUR_OF_DAY, cal
                .getActualMaximum(Calendar.HOUR_OF_DAY));
        cal.set(Calendar.MINUTE, cal.getActualMaximum(Calendar.MINUTE));
        cal.set(Calendar.SECOND, cal.getActualMaximum(Calendar.SECOND));
        cal.set(Calendar.MILLISECOND, cal
                .getActualMaximum(Calendar.MILLISECOND));

        // System.out.println( "cal.toString() = " + cal.toString() );

        return cal.getTime();
    }

    /**
     * Set the time component as the start of the day.
     * <p/>
     * The Time Component of the date returned will be set to 00:00:00.
     * <p/>
     * 
     * @param date The Date to get the start of the day
     * @return The date with the time component reset to 00:00:00.
     */
    public static Date getStartOfDay (Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);

        // Clear the time component
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH) + 1;
        int day = cal.get(Calendar.DAY_OF_MONTH);

        return getDate(year, month, day);
    }

    /**
     * Get the start day of month of the input date. e.g. input date 27-03-2007
     * return date 01-03-2007 00:00:00
     * 
     * @param aDate aDate
     * @return start day of month with time 00:00:00
     */
    public static Date getStartDayOfMonth (Date aDate) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(aDate);
        cal.set(Calendar.DAY_OF_MONTH, cal
                .getActualMinimum(Calendar.DAY_OF_MONTH));
        return DateUtil.getStartOfDay(cal.getTime());
    }

    /**
     * Get the start day of month of the input date added the specified (signed)
     * amount to the given field, based on the calendar's rules.
     * <p>
     * For example, to get start of the last month, it can be achieved by
     * calling:
     * <p>
     * getStartDayOfMonth(aDate, Calendar.MONTH, -1).
     * <p>
     * e.g. input <br>
     * date: 27-03-2007,<br>
     * field: Calendar.MONTH,<br>
     * offset: -1<br>
     * return date: 01-02-2007 00:00:00
     * <p>
     * 
     * @param aDate The date to perform the arithmetic function on
     * @param field A Calendar constant to retrieve the field value from the
     *            Date object.
     * @param offset the amount of date or time to be added to the field
     * @return The date as a result of the execution of the arithmetic function.
     */
    public static Date getStartDayOfMonth (Date aDate, int field, int offset) {
        return getStartDayOfMonth(DateUtil.add(aDate, field, offset));
    }

    /**
     * Get the end day of month of the input date. e.g. input date 27-03-2007
     * return date 31-03-2007 23:59:59
     * 
     * @param aDate aDate
     * @return end day of month with time 23:59:59
     */
    public static Date getEndDayOfMonth (Date aDate) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(aDate);
        cal.set(Calendar.DAY_OF_MONTH, cal
                .getActualMaximum(Calendar.DAY_OF_MONTH));
        return DateUtil.getEndOfDay(cal.getTime());
    }

    /**
     * Get the end day of month of the input date added the specified (signed)
     * amount to the given field, based on the calendar's rules.
     * <p>
     * For example, to get end of the last month, it can be achieved by calling:
     * <p>
     * getEndDayOfMonth(aDate, Calendar.MONTH, -1).
     * <p>
     * e.g. input <br>
     * date: 27-03-2007,<br>
     * field: Calendar.MONTH,<br>
     * offset: -1<br>
     * return date: 28-02-2007 23:59:59
     * <p>
     * 
     * @param aDate The date to perform the arithmetic function on
     * @param field A Calendar constant to retrieve the field value from the
     *            Date object.
     * @param offset the amount of date or time to be added to the field
     * @return The date as a result of the execution of the arithmetic function.
     */
    public static Date getEndDayOfMonth (Date aDate, int field, int offset) {
        return getEndDayOfMonth(DateUtil.add(aDate, field, offset));
    }

    /**
     * Get the start day of week of the input date added the specified (signed)
     * amount to the given field, based on the calendar's rules.
     * 
     * @param aDate aDate
     * @return start day of week
     */
    public static Date getStartDayOfWeek (Date aDate) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(aDate);
        cal.set(Calendar.DAY_OF_WEEK, cal.getFirstDayOfWeek());
        return cal.getTime();
    }

    /**
     * Get the start day of week of the input date added the specified (signed)
     * amount to the given field, based on the calendar's rules.
     * 
     * @param aDate aDate
     * @param offset offset
     * @return start day of week
     */
    public static Date getStartDayOfWeek (Date aDate, int offset) {
        return DateUtil.add(getStartDayOfWeek(aDate), Calendar.WEEK_OF_YEAR,
                offset);
    }

    /**
     * Get the end day of week of the input date added the specified (signed)
     * amount to the given field, based on the calendar's rules.
     * 
     * @param aDate aDate
     * @return end day of week
     */
    public static Date getEndDayOfWeek (Date aDate) {
        return DateUtil.add(getStartDayOfWeek(aDate), Calendar.DATE, 6);
    }

    /**
     * Get the end day of week of the input date added the specified (signed)
     * amount to the given field, based on the calendar's rules.
     * 
     * @param aDate aDate
     * @param offset offset
     * @return end day of week
     */
    public static Date getEndDayOfWeek (Date aDate, int offset) {
        return DateUtil.add(getEndDayOfWeek(aDate), Calendar.WEEK_OF_YEAR,
                offset);
    }

    /**
     * Date Arithmetic function. Adds the specified (signed) amount of time to
     * the given time field, based on the calendar's rules.
     * <p>
     * For example, to subtract 5 days from a specific date, it can be achieved
     * by calling:
     * <p>
     * DateUtil.add(date, Calendar.DATE, -5).
     * <p>
     * 
     * @param date The date to perform the arithmetic function on
     * @param field A Calendar constant to retrieve the field value from the
     *            Date object. Same as for {@link #get get()}.
     * @param amount the amount of date or time to be added to the field
     * @return The date as a result of the execution of the arithmetic function.
     */
    public static Date add (Date date, int field, int amount) {
        TimeZone tz = TimeZone.getTimeZone(default_timezone);
        Calendar cal = Calendar.getInstance(tz);
        cal.setTime(date);
        cal.add(field, amount);

        return cal.getTime();
    }

    /**
     * Return current year.
     * 
     * @return current year
     */
    public static int getCurrentYear () {

        Calendar c = Calendar.getInstance();

        return c.get(Calendar.YEAR);
    }

    /**
     * Return next year.
     * 
     * @param i the year before/after current year
     * @return next year
     */
    public static int addYearToCurrent (int i) {

        Calendar c = Calendar.getInstance();

        return c.get(Calendar.YEAR) + i;
    }

    /**
     * Return input date year.
     * 
     * @param date the input date
     * @return input date year
     */
    public static int getYear (Date date) {

        Calendar c = Calendar.getInstance();
        c.setTime(date);
        return c.get(Calendar.YEAR);
    }

    /**
     * Return current month.
     * 
     * @return current month
     */
    public static int getCurrentMonth () {

        Calendar c = Calendar.getInstance();

        return c.get(Calendar.MONTH);
    }

    /**
     * Return current month.
     * 
     * @return current month
     */
    public static int getCurrentDay () {

        Calendar c = Calendar.getInstance();

        return c.get(Calendar.DAY_OF_MONTH);
    }

    /**
     * Get the start day of current year.
     * 
     * @return start day of year
     */
    public static Date getStartDayOfYear () {
        return DateUtil.getDate(DateUtil.getCurrentYear(), 1, 1);
    }

    /**
     * Get the end day of current year.
     * 
     * @return end day of year
     */
    public static Date getEndDayOfYear () {
        return DateUtil.getDate(DateUtil.getCurrentYear(), 12, 31);
    }

    /**
     * Get the start day of input year.
     * 
     * @param date input date
     * @return start day of year
     */
    public static Date getStartDayOfYear (Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        return DateUtil.getDate(c.get(Calendar.YEAR), 1, 1);
    }

    /**
     * Get the end day of current year.
     * 
     * @param date input date
     * @return end day of year
     */
    public static Date getEndDayOfYear (Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        return DateUtil.getDate(c.get(Calendar.YEAR), 12, 31);
    }

    /**
     * Get the start day of input year.
     * 
     * @param year input year
     * @return start day of year
     */
    public static Date getStartDayOfYear (int year) {
        return DateUtil.getDate(year, 1, 1);
    }

    /**
     * Get the end day of current year.
     * 
     * @param year input year
     * @return end day of year
     */
    public static Date getEndDayOfYear (int year) {
        return DateUtil.getDate(year, 12, 31);
    }

    /**
     * check is the date the special date in week.
     * 
     * @param date input date
     * @param numOfDay see Calendar class
     * @see #Calendar
     * @see #Calendar
     * @see #Calendar
     * @see #Calendar
     * @see #Calendar
     * @see #Calendar
     * @see #Calendar
     * @return boolean check result
     */
    public static boolean isDayOfWeek (Date date, int numOfDay) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        return c.get(Calendar.DAY_OF_WEEK) == numOfDay;
    }

    /**
     * get days of two date , date2 must greater than date1.
     * 
     * @param date1 date1
     * @param date2 date2
     * @return quot
     */
    public static long getBetween (Date date1, Date date2) {

        long quot = 0;

        quot = date2.getTime() - date1.getTime();
        quot = quot / 1000 / 60 / 60 / 24;

        // get the absolute value
        return Math.abs(quot);
    }

    /**
     * judge two date if the one year , date2 must greater than date1.
     * 
     * @param date1 date1
     * @param date2 date2
     * @return isOneYear
     */
    public static boolean isOneYearBetween (Date date1, Date date2) {

        long quot = getBetween(date1, date2);

        if (quot > 366)
            return false;
        else
            return true;
    }

    /**
     * Format the input date String from one pattern to another pattern in the
     * following format: "dd/MM/yyyy" to "yyyy/MM/dd" or "yyyy/MM/dd" to
     * "dd/MM/yyyy".
     * 
     * @param dateStr the date String to be formatted.
     * @param sDatePattern the pattern of date string.
     * @return the formatted date string, return null if input string is null or
     *         empty.
     */
    public static String formatDateStr (String dateStr, String sDatePattern) {
        if (StringUtils.isEmpty(dateStr)) {
            return null;
        }

        if (StringUtils.equalsIgnoreCase(sDatePattern, DEFAULT_DATE_FORMAT)) {
            return formatDate(parseDate(dateStr, DATE_FORMAT_YMD),
                    DEFAULT_DATE_FORMAT);

        } else if (StringUtils.equalsIgnoreCase(sDatePattern, DATE_FORMAT_YMD)) {
            return formatDate(parseDate(dateStr, DEFAULT_DATE_FORMAT),
                    DATE_FORMAT_YMD);

        } else {
            return null;
        }
     }
    
    /**
     * Format the input date String from one pattern to another pattern in the
     * following format: "dd/MM/yyyy" to "yyyyMMdd" or "yyyyMMdd" to "dd/MM/yyyy".
     * 
     * @param dateStr the date String to be formatted.
     * @param sDatePattern the pattern of date string.
     * @return the formatted date string, return null if input string is null or
     *         empty.
     */
    public static String formatDateStrWithoutSlash (String dateStr, String sDatePattern) {
        if (StringUtils.isEmpty(dateStr)) {
            return null;
        }

        if (StringUtils.equalsIgnoreCase(sDatePattern, DEFAULT_DATE_FORMAT)) {
            return formatDate(parseDate(dateStr, DATE_FORMAT_YMD_WITHOUT_SLASH), DEFAULT_DATE_FORMAT);

        } else if (StringUtils.equalsIgnoreCase(sDatePattern, DATE_FORMAT_YMD_WITHOUT_SLASH)) {
            return formatDate(parseDate(dateStr, DEFAULT_DATE_FORMAT), DATE_FORMAT_YMD_WITHOUT_SLASH);

        } else {
            return null;
        }
     }

    /**
     * Format the input date to a date time string in the following format: <br>
     * <code>dd/MM/yyyy HH:mm:ss.SSS</code>.
     * 
     * @param ts the TimeStamp value to be formatted into a date time string.
     * @return the formatted date time string; an empty String if the input date
     *         is <code>null</code> or if there is error during formatting.
     */
    public static String formatTimestampMillis (Timestamp ts) {
        return formatTimestampMillis(ts, TIMESTAMP_MILLIS_FORMAT);
    }

    /**
     * Format the input date to a date time string in the specified date
     * pattern.
     * 
     * @param ts the TimeStamp value to be formatted into a date time string.
     * @param sDatePattern pass in date pattern.
     * @return the formatted date time string; an empty String if the input date
     *         is <code>null</code> or if there is error during formatting.
     */
    public static String formatTimestampMillis (Timestamp ts,
            String sDatePattern) {
        if (ts == null) {
            return null;
        }
        SimpleDateFormat oFormat = new SimpleDateFormat(sDatePattern);
        oFormat.setLenient(false);
        return oFormat.format(ts);
    }

    /**
     * Parse the input date to a <code>java.sql.Timestamp</code> object. The
     * expected input date is of format <code>dd/MM/yyyy HH:mm:ss.SSS</code>
     * 
     * @param dateStr the date string.
     * @return the Timestamp instance created; <code>null</code> if the date
     *         string is <code>null</code> or it does not conform to the format
     *         <code>dd/MM/yyyy HH:mm:ss.SSS</code>
     */
    public static Timestamp parseTimestampMillis (String dateStr) {
        return parseTimestampMillis(dateStr, TIMESTAMP_MILLIS_FORMAT);
    }

    /**
     * Parse the input date to a <code>java.sql.Timestamp</code> object. The
     * expected input date is of format <code>dd/MM/yyyy HH:mm:ss.SSS</code>
     * 
     * @param dateStr the date string.
     * @param sDatePattern pass in date pattern.
     * @return the Timestamp instance created; <code>null</code> if the date
     *         string is <code>null</code> or it does not conform to the format
     *         <code>dd/MM/yyyy HH:mm:ss.SSS</code>
     */
    public static Timestamp parseTimestampMillis (String dateStr,
            String sDatePattern) {
        Timestamp ts = null;
        try {
            if (dateStr == null || dateStr.trim().equals("")) {
                return null;
            }
            SimpleDateFormat oFormat = new SimpleDateFormat(sDatePattern);
            oFormat.setLenient(false);
            Date date = oFormat.parse(dateStr);
            ts = new Timestamp(date.getTime());
        } catch (Exception e) {
            // Do nothing
        }
        return ts;
    }

    /**
     * Format the time string by the specified hour, minute, second as below
     * format: (25,13,14) -> "12:13:14" (12,13,14) -> "12:13:14" (12,13,"") ->
     * "12:13:00".
     * 
     * @param hour the hour
     * @param minute the minute
     * @param second the second
     * @return time string.
     */
    public static String formatTimeStr (String hour, String minute,
            String second) {
        try {
            int h = 0, m = 0, s = 0;
            if (StringUtils.isEmpty(hour)) {
                hour = TIME_STR_TWO_ZERO;
            } else {
                h = Integer.parseInt(hour);
            }
            if (StringUtils.isEmpty(minute)) {
                minute = TIME_STR_TWO_ZERO;
            } else {
                m = Integer.parseInt(minute);
            }
            if (StringUtils.isEmpty(second)) {
                second = TIME_STR_TWO_ZERO;
            } else {
                s = Integer.parseInt(second);
            }
            if (h < 0 || h > 24 || m < 0 || m > 59 || s < 0 || s > 59) {
                return null;
            }
            String hourStr = hour.length() > 1 ? hour : TIME_STR_ONE_ZERO
                    + hour;

            String minuteStr = minute.length() > 1 ? minute : TIME_STR_ONE_ZERO
                    + minute;

            String secondStr = second.length() > 1 ? second : TIME_STR_ONE_ZERO
                    + second;

            return hourStr + ":" + minuteStr + ":" + secondStr;
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * Get current time, format HH:MM.
     * 
     * @return
     */
    public static String getCurrentTime () {
        return formatDate(getCurrentTimestamp(), DEFAULT_TIME_FORMAT_SHORT);
    }

    /**
     * Get current date, format yyyy/MM/dd.
     * 
     * @return
     */
    public static String getCurrentDate () {
        return formatDate(getCurrentTimestamp());
    }
    
    /**
     * Get current date, format yyyy/MM/dd.
     * 
     * @return
     */
    public static String getCurrentDateWithoutSlash () {
        return formatDate(getCurrentTimestamp(), DATE_FORMAT_YMD_WITHOUT_SLASH);
    }


    /**
     * get timestamp by date and time
     * 
     * @param dateStr: format: yyyy/MM/dd
     * @param hour
     * @param minute
     * @param second
     * @return
     */
    public static Timestamp getTimestampByDateAndTime (String dateStr,
            int hour, int minute, int second) {
        Calendar c = Calendar.getInstance();
        c.setTime(DateUtil.parseDate(dateStr, DateUtil.DATE_FORMAT_YMD_WITHOUT_SLASH));
        c.set(Calendar.HOUR, hour);
        c.set(Calendar.MINUTE, minute);
        c.set(Calendar.SECOND, second);
        return new Timestamp(c.getTimeInMillis());
    }
    
    public static int getHourFromTimestamp(Timestamp ts){
        Calendar c = Calendar.getInstance();
        c.setTime(ts);
        return c.get(Calendar.HOUR_OF_DAY);
    }
    
    public static int getMinFromTimestamp(Timestamp ts){
        Calendar c = Calendar.getInstance();
        c.setTime(ts);
        return c.get(Calendar.MINUTE);
    }
    
    /**
     * Only for dd/MM/yyyy
     * @param date
     * @return
     */
    public static Date parseDateForCsv(String date){
        if(date != null){
            String[] arr = date.split("/");
            if(arr.length >= 3){
                try {
                    int day = Integer.parseInt(arr[0]);
                    int month = Integer.parseInt(arr[1]);
                    int year = Integer.parseInt(arr[2]);
                    Calendar c = Calendar.getInstance();
                    c.set(year, month, day);
                    return c.getTime();
                } catch (NumberFormatException e) {
                    return null;
                }
            }
        }
        return null;
    }
    
	public static int getDay(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		return cal.get(Calendar.DAY_OF_WEEK);
	}
    
    public static String getDisplayDate(boolean isCN){
    	Date currentDate = getDate();
    	
    	StringBuffer str = new StringBuffer();
//    	str.append("&nbsp;&nbsp;&nbsp;");
//    	str.append(formatDate(currentDate, isCN?DATE_FORMAT_CN:DEFAULT_DATE_FORMAT));
//    	str.append("&nbsp;");
    	int day = getDay(currentDate);
    	if(Calendar.SUNDAY == day){
    		str.append(isCN?"星期日":"Sunday");
    	}else if(Calendar.MONDAY == day){
    		str.append(isCN?"星期一":"Monday");
    	}else if(Calendar.TUESDAY == day){
    		str.append(isCN?"星期二":"Tuesday");
    	}else if(Calendar.WEDNESDAY == day){
    		str.append(isCN?"星期三":"Wendesday");
    	}else if(Calendar.THURSDAY == day){
    		str.append(isCN?"星期四":"Thursday");
    	}else if(Calendar.FRIDAY == day){
    		str.append(isCN?"星期五":"Friday");
    	}else if(Calendar.SATURDAY == day){
    		str.append(isCN?"星期六":"Saturday");
    	}
    	return str.toString();
    }
    
    /**
     * 将格式为sDatePattern的时间iDate转换成Timestamp形式
     * @param iDate
     * @param sDatePattern
     * @return
     */
    public static Timestamp getTimestamp(String iDate,String sDatePattern ){
    	Date date = DateUtil.parseDate(iDate, sDatePattern);
    	return new Timestamp(date.getTime());
    }
    /**
     * 将时间差转换成时分秒
     * 
     * @author Dai Dong
     * @param between
     * @return
     * @return String
     */
	public static String getSecondsWhen(long between){
    	if(between<=0){
    		return String.valueOf(-1);
    	}
    	 between = between/1000;
    	   long day=between/(24*3600);
    	   between=between-(day*24*60*60);
    	   long hour=between/3600;
    	   between=between-(hour*60*60);
    	   long minute=between/60;
    	   between=between-(minute*60);
    	   long second=between;
    	   StringBuffer time = new StringBuffer();
    	  
    	   if(day!=0){
    		   time.append(day).append("天");
    	   }
    	   if(hour!=0){
    		   time.append(hour).append("时");
    	   }
    	   if(minute!=0){
    		   time.append(minute).append("分");
    	   }
    	   if(second!=0){
    		   time.append(second).append("秒");
    	   }
    	   return time.toString();
    }
	
	// 获取本季度第一天
	public static String getFirstDayofThisSeasonTime(int month) {
		int array[][] = { { 1, 2, 3 }, { 4, 5, 6 }, { 7, 8, 9 }, { 10, 11, 12 } };
		int season = 1;
		if (month >= 1 && month <= 3) {
			season = 1;
		}
		if (month >= 4 && month <= 6) {
			season = 2;
		}
		if (month >= 7 && month <= 9) {
			season = 3;
		}
		if (month >= 10 && month <= 12) {
			season = 4;
		}
		int start_month = array[season - 1][0];

		Date date = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy");// 可以方便地修改日期格式
		String years = dateFormat.format(date);
		int years_value = Integer.parseInt(years);

		String start_days = "01";
		String seasonDate = years_value + "-" + start_month + "-" + start_days;
		return seasonDate;
	}
	
	public static String getEndDayofThisSeasonTime(int month) {
		int array[][] = { { 1, 2, 3 }, { 4, 5, 6 }, { 7, 8, 9 }, { 10, 11, 12 } };
		int season = 1;
		if (month >= 1 && month <= 3) {
			season = 1;
		}
		if (month >= 4 && month <= 6) {
			season = 2;
		}
		if (month >= 7 && month <= 9) {
			season = 3;
		}
		if (month >= 10 && month <= 12) {
			season = 4;
		}
		int end_month = array[season - 1][2];

		Date date = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy");// 可以方便地修改日期格式
		String years = dateFormat.format(date);
		int years_value = Integer.parseInt(years);

		int end_days = getLastDayOfMonth(years_value, end_month);
		String seasonDate = years_value + "-" + end_month + "-" + end_days;
		return seasonDate;

	}

	private static int getLastDayOfMonth(int year, int month) {
		if (month == 1 || month == 3 || month == 5 || month == 7 || month == 8
				|| month == 10 || month == 12) {
			return 31;
		}
		if (month == 4 || month == 6 || month == 9 || month == 11) {
			return 30;
		}
		if (month == 2) {
			if (isLeapYear(year)) {
				return 29;
			} else {
				return 28;
			}
		}
		return 0;
	}

	public static boolean isLeapYear(int year) {
		return (year % 4 == 0 && year % 100 != 0) || (year % 400 == 0);
	}

	public static String[] getStartAndEndTime(String startTime,String endTime ) {
		String[] time = new String[2];
		Date date = DateUtil.getDate();
		
		// 获取当天情况
		if ("today".equals(startTime)) {
			time[0] = DateUtil.formatDate(DateUtil.getStartOfDay(date),
					DateUtil.FULL_FORMAT);
			time[1] = DateUtil.formatDate(DateUtil.getEndOfDay(date),
					DateUtil.FULL_FORMAT);
		} 
		// 获取本周情况
		else if ("week".equals(startTime)) {
			Date firstDay = DateUtil.addDaysToDate(
					DateUtil.getStartDayOfWeek(date), 1);
			Date endDay = DateUtil.addDaysToDate(
					DateUtil.getEndDayOfWeek(date), 1);
			time[0] = DateUtil.formatDate(DateUtil.getStartOfDay(firstDay),
					DateUtil.FULL_FORMAT);
			time[1] = DateUtil.formatDate(DateUtil.getEndOfDay(endDay),
					DateUtil.FULL_FORMAT);
		} 
		// 获取本月情况
		else if ("month".equals(startTime)) {
			time[0] = DateUtil.formatDate(
					DateUtil.getStartOfDay(DateUtil.getStartDayOfMonth(date)),
					DateUtil.FULL_FORMAT);
			time[1] = DateUtil.formatDate(
					DateUtil.getEndOfDay(DateUtil.getEndDayOfMonth(date)),
					DateUtil.FULL_FORMAT);
		} 
		// 获取上周情况
		else if ("lastWeek".equals(startTime)) {
			Date firstDay = DateUtil.addDaysToDate(
					DateUtil.getStartDayOfWeek(date), -6);
			Date endDay = DateUtil.addDaysToDate(
					DateUtil.getEndDayOfWeek(date), -6);
			time[0] = DateUtil.formatDate(DateUtil.getStartOfDay(firstDay),
					DateUtil.FULL_FORMAT);
			time[1] = DateUtil.formatDate(DateUtil.getEndOfDay(endDay),
					DateUtil.FULL_FORMAT);
		} 
		// 获取上月情况
		else if ("lastMonth".equals(startTime)) {
			Date lastMonth = DateUtil.addMonthsToDate(date, -1);
			time[0] = DateUtil.formatDate(DateUtil.getStartOfDay(DateUtil
					.getStartDayOfMonth(lastMonth)), DateUtil.FULL_FORMAT);
			time[1] = DateUtil.formatDate(
					DateUtil.getEndOfDay(DateUtil.getEndDayOfMonth(lastMonth)),
					DateUtil.FULL_FORMAT);
		} 
		// 获取本季度情况
		else if ("quarter".equals(startTime)) {
			time[0] = DateUtil.getFirstDayofThisSeasonTime(DateUtil.getCurrentMonth()) + " 00:00:00";
			time[1] = DateUtil.getEndDayofThisSeasonTime(DateUtil.getCurrentMonth()) + " 23:59:59";
		} 
		// 获取上季度情况
		else if ("lastQuarter".equals(startTime)) {
			String firstQuarter = DateUtil.getFirstDayofThisSeasonTime(DateUtil.getCurrentMonth()) + " 00:00:00";
			String endQuarter = DateUtil.getEndDayofThisSeasonTime(DateUtil.getCurrentMonth()) + " 23:59:59";
			Date first = DateUtil.addMonthsToDate(DateUtil.parseDate(firstQuarter), -3);
			Date end = DateUtil.addMonthsToDate(DateUtil.parseDate(endQuarter), -3);
			time[0] = DateUtil.formatDate(DateUtil.getStartOfDay(first),
					DateUtil.FULL_FORMAT);
			time[1] = DateUtil.formatDate(DateUtil.getEndOfDay(end),
					DateUtil.FULL_FORMAT);
		} 
		// 假如上面情况都不是，则从页面传递过来的时间中获取开始和结束时间
		else {
			// 首次查询时不会携带开始和结束时间，所以默认搜索本月情况
			if(StringUtil.isEmpty(startTime) && StringUtil.isEmpty(endTime)){
				time[0] = DateUtil.formatDate(
						DateUtil.getStartOfDay(DateUtil.getStartDayOfMonth(date)),
						DateUtil.FULL_FORMAT);
				time[1] = DateUtil.formatDate(
						DateUtil.getEndOfDay(DateUtil.getEndDayOfMonth(date)),
						DateUtil.FULL_FORMAT);
			} else {
				if(StringUtil.isEmpty(startTime)){
					time[0] = "2010-01-01 00:00:00";
				}else{
					time[0] = startTime;
				}
				if(StringUtil.isEmpty(endTime)){
					time[1] = DateUtil.formatDate(DateUtil.getEndOfDay(date),
							DateUtil.FULL_FORMAT);
				}else{
					time[1] = endTime;
				}
			}
		}
		return time;
	}
	
	public static Date formatStringToDay(String date, String datePattern) {
		 try {
	         Format f = new SimpleDateFormat(datePattern);
	         Date d = (Date) f.parseObject(date);
	         
	         return d;
	     } catch (ParseException e) {
	         e.printStackTrace();
	         
	         return null;
	     }
	}

    /** 获取两个时间的时间查 如1天2小时30分钟 */
    public static String getDatePoor(Date endDate, Date nowDate) {
        long nd = 1000 * 24 * 60 * 60;
        long nh = 1000 * 60 * 60;
        // long ns = 1000;
        // 获得两个时间的毫秒时间差异
        long diff = endDate.getTime() - nowDate.getTime();
        // 计算差多少天
        long day = diff / nd;
        // 计算差多少小时
        long hour = diff % nd / nh;
        // 计算差多少分钟
        // long min = diff % nd % nh / nm;
        // 计算差多少秒//输出结果
        // long sec = diff % nd % nh % nm / ns;
        return (day > 0 ? (day + "天") : "") + hour + "小时";
    }

    /** 获取两个时间的时间查 如1天2小时30分钟 */
    public static String getDatePoorWithMin(Date endDate, Date nowDate) {
        long nd = 1000 * 24 * 60 * 60;
        long nh = 1000 * 60 * 60;
        long nm = 1000 * 60;
        // long ns = 1000;
        // 获得两个时间的毫秒时间差异
        long diff = endDate.getTime() - nowDate.getTime();
        // 计算差多少天
        long day = diff / nd;
        // 计算差多少小时
        long hour = diff % nd / nh;
        // 计算差多少分钟
        long min = diff % nd % nh / nm;
        // 计算差多少秒//输出结果
        // long sec = diff % nd % nh % nm / ns;
        return (day > 0 ? (day + "天") : "") + hour + "小时" + min + "分";
    }
    
    /**
     * 验证是否为合法的生日
     *
     *@param str 格式必须为yyyy-MM-dd
     *@return true 是， false - 否
     */
    public static boolean isValidBirthday(String str) {
   	  	Date targetDate = parseDate(str);
   	  	if (targetDate == null) {
   	  		return false;
   	  	} else {
   	  		return compareDates(targetDate, getDate()) <= 0;
   	  	}
    }
    
    /**
     * 验证是否为合法的生日
     *
     *@param str 格式必须为yyyy-MM-dd
     *@return true 是， false - 否
     */
    public static boolean isValidBirthday(Date targetDate) {
    	return compareDates(targetDate, getDate()) <= 0;
    }
}
