package com.xing.weijian.utils;

import android.util.Log;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by Administrator on 2018/4/1.
 */

public class DateUtil {

    private static final String TAG = "DateUtil";

    private DateUtil() {
        throw new UnsupportedOperationException("cannot be instantiated");
    }

    public static String formatMonthDay(long millis) {
        SimpleDateFormat sdf = new SimpleDateFormat("MM月dd日");
        return sdf.format(millis);
    }

    public static String formatMonthDay(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("MM月dd日");
        return sdf.format(date);
    }

    public static Date parseDate(String dateStr) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date date = sdf.parse(dateStr);
            Log.d(TAG, "parseDate: date = " + date);
            return date;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return new Date();
    }

    public static String formatDate(long millis) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        return sdf.format(millis);
    }


    public static String getWeekOfDate(Date date) {
        String[] weekDays = {"星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六"};
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
        if (w < 0) {
            w = 0;
        }
        return weekDays[w];
    }


}
