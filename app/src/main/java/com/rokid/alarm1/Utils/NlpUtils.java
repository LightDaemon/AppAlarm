package com.rokid.alarm1.Utils;

import android.text.TextUtils;

import com.rokid.alarm1.beans.DateTimeBean;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by ZY on 2016/6/13.
 * Description :获取nlp中的年月日等数据
 */
public class NlpUtils {

    public static Long getTime(DateTimeBean dateTime) {
        long time = -1;
        //判断是否为空数据
        if (dateTime == null) {
            return time;
        }

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date(System.currentTimeMillis()));

        calendar.set(Calendar.YEAR, getYear(dateTime));
        calendar.set(Calendar.MONTH, getMonth(dateTime));
        calendar.set(Calendar.DAY_OF_MONTH, getDay(dateTime));
        calendar.set(Calendar.HOUR_OF_DAY, getHour(dateTime));
        calendar.set(Calendar.MINUTE, getMinute(dateTime));
        calendar.set(Calendar.SECOND, 0);
        LogUtils.i("年：" + calendar.get(Calendar.YEAR));
        LogUtils.i("月：" + calendar.get(Calendar.MONTH));
        LogUtils.i("日：" + calendar.get(Calendar.DAY_OF_MONTH));
        LogUtils.i("小时：" + calendar.get(Calendar.HOUR_OF_DAY));
        LogUtils.i("分钟：" + calendar.get(Calendar.MINUTE));

        time = calendar.getTime().getTime();
        LogUtils.i("showtime:" + time);
        return time;
    }


    private static int getYear(DateTimeBean dateTimeBean) {
        int year = -1;
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date(System.currentTimeMillis()));
        year = calendar.get(Calendar.YEAR);
        if (dateTimeBean != null) {
            if (!TextUtils.isEmpty(dateTimeBean.getRelYear())) {
                year = year + Integer.valueOf(dateTimeBean.getRelYear());
            }
            if (!TextUtils.isEmpty(dateTimeBean.getAbsYear())) {
                year = Integer.valueOf(dateTimeBean.getAbsYear());
            }
        }

        return year;
    }

    private static int getMonth(DateTimeBean dateTimeBean) {
        int month = -1;
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date(System.currentTimeMillis()));
        month = calendar.get(Calendar.MONTH);
        if (dateTimeBean != null) {
            if (!TextUtils.isEmpty(dateTimeBean.getRelMonth())) {
                month = month + Integer.valueOf(dateTimeBean.getRelMonth());
            }
            if (!TextUtils.isEmpty(dateTimeBean.getAbsMonth())) {
                month = Integer.valueOf(dateTimeBean.getAbsMonth());
            }
        }
        return month;
    }

    private static int getDay(DateTimeBean dateTimeBean) {
        int day = -1;
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date(System.currentTimeMillis()));
        day = calendar.get(Calendar.DAY_OF_MONTH);
        if (dateTimeBean != null) {
            if (!TextUtils.isEmpty(dateTimeBean.getRelDay())) {
                day = day + Integer.valueOf(dateTimeBean.getRelDay());
            }
            if (!TextUtils.isEmpty(dateTimeBean.getAbsDay())) {
                day = Integer.valueOf(dateTimeBean.getAbsDay());
            }
        }
        return day;
    }

    private static int getHour(DateTimeBean dateTimeBean) {
        int hour = -1;
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date(System.currentTimeMillis()));
        hour = calendar.get(Calendar.HOUR_OF_DAY);
        if (dateTimeBean != null) {
            if (!TextUtils.isEmpty(dateTimeBean.getRelHour())) {
                hour = hour + Integer.valueOf(dateTimeBean.getRelHour());
            }

            if (!TextUtils.isEmpty(dateTimeBean.getAbsHour())) {
                LogUtils.i(dateTimeBean.getAbsHour() + "!!!!");
                hour = Integer.valueOf(dateTimeBean.getAbsHour());
            }
        }

        return hour;
    }

    private static int getMinute(DateTimeBean dateTimeBean) {
        int minute = -1;
        if (dateTimeBean != null) {
            if (!TextUtils.isEmpty(dateTimeBean.getAbsHour())) {
                minute = 0;
                if (!TextUtils.isEmpty(dateTimeBean.getRelMinute())) {
                    minute = Integer.valueOf(dateTimeBean.getRelMinute());
                }
            } else {
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(new Date(System.currentTimeMillis()));
                minute = calendar.get(Calendar.MINUTE);
                if (!TextUtils.isEmpty(dateTimeBean.getRelMinute())) {
                    minute = minute + Integer.valueOf(dateTimeBean.getRelMinute());
                }
            }
            if (!TextUtils.isEmpty(dateTimeBean.getAbsMinute())) {
                minute = Integer.valueOf(dateTimeBean.getAbsMinute());
            }
        }

        return minute;
    }
}