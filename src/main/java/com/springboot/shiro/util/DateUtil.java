package com.springboot.shiro.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @author xutianhong
 * @Date 2021/3/4 5:19 下午
 */
public class DateUtil {
    private static final Logger LOGGER = LoggerFactory.getLogger(DateUtil.class);
    /**
     * 通用的一个DateFormat
     */
    public final static SimpleDateFormat commonDateFormat = new SimpleDateFormat("yyyy-MM-dd");

    /**
     * 处理时分秒的DateFormat
     */
    public final static SimpleDateFormat timeFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    /**
     * 不显示秒的DateFormat
     */
    public final static SimpleDateFormat noSecondFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");

    /**
     * utc时间格式
     */
    public final static SimpleDateFormat UTC_FORMAT = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");

    public final static int MILLISECONDS_OF_SECOND = 1000;
    public final static int MILLISECONDS_OF_MINUTE = 60000;
    public final static int MILLISECONDS_OF_HOUR = 3600000;
    public final static int MILLISECONDS_OF_DAY = 86400000;
    public final static int SECONDS_OF_MINUTE = 60;
    public final static int MINUTES_OF_HOUR = 60;
    public final static int HOURS_OF_DAY = 24;

    public final static String TIME_UNIT_SECOND = "s";
    public final static String TIME_UNIT_MINUTE = "m";
    public final static String TIME_UNIT_HOUR = "h";
    public final static String TIME_UNIT_DAY = "d";

    public static Date getCurrentUtcTime() {

        SimpleDateFormat adf = new SimpleDateFormat("yyyy-MM-dd");

        StringBuffer UTCTimeBuffer = new StringBuffer();
        // 1、取得本地时间：
        Calendar cal = Calendar.getInstance();
        // 2、取得时间偏移量：
        int zoneOffset = cal.get(java.util.Calendar.ZONE_OFFSET);
        // 3、取得夏令时差：
        int dstOffset = cal.get(java.util.Calendar.DST_OFFSET);
        // 4、从本地时间里扣除这些差量，即可以取得UTC时间：
        cal.add(java.util.Calendar.MILLISECOND, -(zoneOffset + dstOffset));
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH) + 1;
        int day = cal.get(Calendar.DAY_OF_MONTH);
        int hour = cal.get(Calendar.HOUR_OF_DAY);
        int minute = cal.get(Calendar.MINUTE);
        int second = cal.get(Calendar.SECOND);
        UTCTimeBuffer.append(year).append("-").append(month).append("-").append(day);
        UTCTimeBuffer.append("T").append(hour).append(":").append(minute).append(":").append(second).append("Z");
        Date date = null;
        try {
            date = adf.parse(UTCTimeBuffer.toString());
        } catch (ParseException e) {
            LOGGER.warn("获取CurrentUtcTime失败", e);
        }
        return date;
    }
}
