package com.rokid.alarm1.finals;

import com.rokid.alarm1.BuildConfig;

/**
 * Created by ZY on 2016/6/3.
 * Description :
 */
public class AppContent {
    /**设置闹钟*/
    public static String ADD_ALARM = "_setup_alarm";
    /**取消闹钟*/
    public static String DELETE_ALARM = "_cancle_alarm";
    /**修改闹钟*/
    public static String UPDATE_ALARM = "_update_alarm";
    /**查询闹钟*/
    public static String QUERY_ALARM = "_query_alarm";
    /**早上*/
    public static String morning = "MORNING";
    /**下午*/
    public static String afternoon = "AFTERNOON";
    /**晚上*/
    public static String evening = "EVENING";

    /**闹钟广播接收者*/
    public static String ALARM_RECEIVER = "com.rokid.alarm1.AlarmReceiver";

    public static final boolean DEBUG_MODE = BuildConfig.DEBUG;
    /**PreferenceName*/
    public static final String SPNAME = "com.rokid.alarm1.sp";
}
