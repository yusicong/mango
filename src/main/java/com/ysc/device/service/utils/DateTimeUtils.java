package com.ysc.device.service.utils;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;


public class DateTimeUtils {
    private static DateTimeFormatter DATEFORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public static DateTimeFormatter DATEFORMATTER_MS = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");

    public static DateTimeFormatter DATEFORMATTER_COMPACT = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");

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

    public static final LocalDateTime getTodayBegin() {
        LocalDateTime today = LocalDateTime.now();
        LocalDateTime localDateTime = LocalDateTime.of(today.getYear(), today.getMonthValue(), today.getDayOfMonth(), 0, 0, 0);
        return localDateTime;
    }

    public static final LocalDateTime getTodayEnd() {
        LocalDateTime today = LocalDateTime.now();
        LocalDateTime localDateTime = LocalDateTime.of(today.getYear(), today.getMonthValue(), today.getDayOfMonth(), 23, 59, 59);
        return localDateTime;
    }

    /**
     * time转换成毫秒
     * @param localDateTime
     * @return
     */
    public static final Long toMilliTime(LocalDateTime localDateTime) {
        if (localDateTime == null) {
            return null;
        }

        return Timestamp.valueOf(localDateTime).getTime();
    }

    /**
     * string转换成localDateTime
     * @param timeStr
     * @return
     */
    public static final LocalDateTime strToLocalDateTime(String timeStr)
    {
        return LocalDateTime.parse(timeStr,DATEFORMATTER);
    }

    /**
     * string转换成LocalDateTime
     * @param timeStr
     * @return
     */
    public static final Long stringToMilliTime(String timeStr)
    {
        try {
            Timestamp t = Timestamp.valueOf(timeStr);
            return t.getTime();

        }catch (Exception e)
        {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 毫秒转换成time
     * @param milliTime
     * @return
     */
    public static final LocalDateTime millToLocalDateTime(Long milliTime) {
        if (milliTime == null) {
            return null;
        }
        Timestamp timestamp = new Timestamp(milliTime);
        return timestamp.toLocalDateTime();
    }

    /**
     * 毫秒转换成格式化字符串
     * @param milliTime
     * @return
     */
    public static String millToDateTimeString(Long milliTime) {
        if (milliTime == null) {
            return null;
        }
        Date date = new Date(milliTime);
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return format.format(date);
    }

    /**
     * 毫秒转换成毫秒格式化字符串
     * @param milliTime
     * @return
     */
    public static String millToMillDateTimeString(Long milliTime) {
        if (milliTime == null) {
            return null;
        }
        Date date = new Date(milliTime);
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return format.format(date);
    }

    public static Integer weekOfYear(LocalDateTime localDateTime) {
        Calendar calendar = Calendar.getInstance();
        //设置周一为一周的第一天
        calendar.setFirstDayOfWeek(Calendar.MONDAY);
        //这样可以与数据库中weekOfYear保持一致Mysql-->weekOfYear(date)//周一为一周的第一天
        calendar.setMinimalDaysInFirstWeek(4);
        calendar.setTimeInMillis(toMilliTime(localDateTime));
        int week = calendar.get(Calendar.WEEK_OF_YEAR);
        return week;
    }
    
}
