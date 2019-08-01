package com.ysc.device.service.utils;

import org.springframework.util.StringUtils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;

/**
 * @author enmonster
 */
public class DateUtil {

    public static final String SIMPLE_DATE_PARTEN = "yyyy-MM-dd";
    public static final String DATE_PARTEN = "yyyy-MM-dd HH:mm:ss";
    public static final String DATE_PARTEN_CHINESE = "yyyy年MM月dd日HH:mm";
    public static final String DATE_PARTEN_MILLI = "yyyy-MM-dd HH:mm:ss.SSS";
    public static final String DATE_PARTEN_YYMMDD = "yyyyMMdd";
    private static DateTimeFormatter DATEFORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    /**
     * 时分秒 一天开始
     */
    public static final String START_TIME_STRING = " 00:00:00";

    /**
     * 时分秒 一天结束
     */
    public static final String END_TIME_STRING = " 23:59:59";



    public static boolean isToday(Date date) {
        if (date != null) {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            Calendar today = Calendar.getInstance();
            if (calendar.get(Calendar.YEAR) == today.get(Calendar.YEAR)
                    && calendar.get(Calendar.MONTH) == today
                    .get(Calendar.MONTH)
                    && calendar.get(Calendar.DAY_OF_MONTH) == today
                    .get(Calendar.DAY_OF_MONTH)) {
                return true;
            }
        }
        return false;
    }

    public static boolean isAfterToday(Date date) {
        if (date != null) {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);

            Calendar today = Calendar.getInstance();
            today.set(Calendar.HOUR_OF_DAY, 23);
            today.set(Calendar.MINUTE, 59);
            today.set(Calendar.SECOND, 59);

            return calendar.after(today);
        }
        return false;
    }

    public static Date parseDate(String dateStr) {
        if (dateStr != null) {
            try {
                if (dateStr.length() <= 10) {
                    return getSimpleFormat().parse(dateStr);
                } else {
                    return getFormat().parse(dateStr);
                }
            } catch (Exception e) {
                return null;
            }

        }
        return null;
    }

    public static String formatDate(Long date) {
        if (date == null) {
            return null;
        }
        return formatDate(new Date(date));
    }

    public static String formatDate(Date date) {
        if (date == null) {
            return null;
        }
        try {
            return getFormat().format(date);
        } catch (Exception e) {
            return null;
        }
    }


    public static String formatYYMMDDDate(Date date) {
        if (date == null) {
            return null;
        }
        try {
            return getYYMMDDFormat().format(date);
        } catch (Exception e) {
            return null;
        }
    }



    public static String formatChineseDate(Date date) {
        if (date == null) {
            return null;
        }
        try {
            return getChineseFormat().format(date);
        } catch (Exception e) {
            return null;
        }
    }

    public static String formatSimpleDate(Date date) {
        if (date == null) {
            return null;
        }
        try {
            return getSimpleFormat().format(date);
        } catch (Exception e) {
            return null;
        }
    }

    public static String formatMillisecondDate(Date date) {
        if (date == null) {
            return null;
        }

        try {
            return getMilliFormat().format(date);
        } catch (Exception e) {
            return null;
        }

    }

    private static ThreadLocal<SimpleDateFormat> threadLocal = new ThreadLocal<SimpleDateFormat>() {
        @Override
        protected SimpleDateFormat initialValue() {
            return new SimpleDateFormat(DATE_PARTEN);
        }
    };

    private static ThreadLocal<SimpleDateFormat> threadLocal_simple = new ThreadLocal<SimpleDateFormat>() {
        @Override
        protected SimpleDateFormat initialValue() {
            return new SimpleDateFormat(SIMPLE_DATE_PARTEN);
        }
    };
    private static ThreadLocal<SimpleDateFormat> threadLocal_milli = new ThreadLocal<SimpleDateFormat>() {
        @Override
        protected SimpleDateFormat initialValue() {
            return new SimpleDateFormat(DATE_PARTEN_MILLI);
        }
    };
    private static ThreadLocal<SimpleDateFormat> threadLocal_chinese = new ThreadLocal<SimpleDateFormat>() {
        @Override
        protected SimpleDateFormat initialValue() {
            return new SimpleDateFormat(DATE_PARTEN_CHINESE);
        }
    };

    private static ThreadLocal<SimpleDateFormat> threadLocal_yymmdd = new ThreadLocal<SimpleDateFormat>() {
        @Override
        protected SimpleDateFormat initialValue() {
            return new SimpleDateFormat(DATE_PARTEN_YYMMDD);
        }
    };

    private static DateFormat getFormat() {
        return threadLocal.get();
    }

    private static DateFormat getSimpleFormat() {
        return threadLocal_simple.get();
    }

    private static DateFormat getMilliFormat() {
        return threadLocal_milli.get();
    }

    private static DateFormat getChineseFormat() {
        return threadLocal_chinese.get();
    }


    private static DateFormat getYYMMDDFormat() {
        return threadLocal_yymmdd.get();
    }



    public static Date getDate(Long millisecond) {
        return millisecond != null && millisecond > 0 ? new Date(millisecond)
                : null;
    }

    public static Date getDay(Integer dayNumOffet) {
        if (dayNumOffet == null) {
            return null;
        }
        return getDay(Calendar.getInstance().getTime(), dayNumOffet);
    }

    public static Date getDay(Date theDate, int dayNumOffet) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(theDate);

        calendar.add(Calendar.DAY_OF_MONTH, dayNumOffet);
        Date date = calendar.getTime();

        return date;
    }

    public static Date convertStr2Date(String str, String format) throws ParseException {
        if (StringUtils.isEmpty(str)) {
            return null;
        }
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.parse(str);
    }

    /**
     * @param date
     * @return
     */
    public static final String ymdFormat(Date date) {
        if (date == null) {
            return "";
        }

        DateFormat ymdFormat = new SimpleDateFormat("yyyy-MM-dd");
        return ymdFormat.format(date);
    }

    public static final String hmsFormat(Date date) {
        if (date == null) {
            return "";
        }

        DateFormat hmsFormat = new SimpleDateFormat("HH:mm");
        return hmsFormat.format(date);
    }

    public static String formatDate(Date showTime, String dateformat) {
        if (showTime == null) {
            return "";
        }
        SimpleDateFormat sdf = new SimpleDateFormat(dateformat);
        return sdf.format(showTime);
    }

    public static int getField(Date date, int field) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(field);
    }

    /**
     * 指定日期00:00:00
     *
     * @param date      指定日期
     * @param dayAmount 日期偏移量
     * @return
     */
    public static Date getDateLeft(Date date, int dayAmount) {
        if (date == null) {
            return null;
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_YEAR, dayAmount);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        return calendar.getTime();
    }

    /**
     * 指定日期23:59:59
     *
     * @param date      指定日期
     * @param dayAmount 日期偏移量
     * @return
     */
    public static Date getDateRight(Date date, int dayAmount) {
        if (date == null) {
            return null;
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_YEAR, dayAmount);
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        return calendar.getTime();
    }

    /**
    * String转localdatatime
    */
    public static LocalDateTime  Str2LocalDateTime(String date) {
        if (date == null) {
            return null;
        }
        DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime ldt = LocalDateTime.parse(date,df);

        return ldt;

    }


    // 将字符串转为毫秒级时间戳
    public static long getTime(String user_time) {
        String re_time = null;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date d;
        try {
            d = sdf.parse(user_time);
            long l = d.getTime();
            String str = String.valueOf(l);
            re_time = str.substring(0, 13);
        } catch (ParseException e) {
        // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return Long.parseLong(re_time);
    }

    /**
     * 转化为yyyy-MM-dd HH:mm:ss格式
     *
     * @param localDateTime
     * @return
     */
    public static final String toDateTimeString(LocalDateTime localDateTime) {
        if (localDateTime == null) {
            return null;
        }
        return localDateTime.format(DATEFORMATTER);


    }

    //获取指定日期的毫秒
    public static Long getMilliByTime(LocalDateTime time) {
        return time.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
    }
}