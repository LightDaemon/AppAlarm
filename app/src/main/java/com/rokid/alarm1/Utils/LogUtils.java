package com.rokid.alarm1.Utils;

import android.text.TextUtils;
import android.util.Log;

import com.rokid.alarm1.finals.AppContent;


public class LogUtils {
    public static boolean allowLog = AppContent.DEBUG_MODE;//是否开启日志
    public static boolean allowD = true;
    public static boolean allowE = true;
    public static boolean allowI = true;
    public static boolean allowV = true;
    public static boolean allowW = true;
    public static boolean allowWtf = true;
    public static String customTagPrefix = "Rokid";

    private static String generateTag(StackTraceElement caller) {
        String tag = "%s.%s(L:%d)";
        String callerClazzName = caller.getClassName();
        callerClazzName = callerClazzName.substring(callerClazzName.lastIndexOf(".") + 1);
        tag = String.format(tag, callerClazzName, caller.getMethodName(), caller.getLineNumber());
        tag = TextUtils.isEmpty(customTagPrefix) ? tag : customTagPrefix + ":" + tag;
        return tag;
    }

    public static void v(String content) {
        if (allowLog && allowV) {
            StackTraceElement caller = getCallerStackTraceElement();
            String tag = generateTag(caller);
            Log.v(tag, content);
        }
    }

    public static void v(String content, Throwable tr) {
        if (allowLog && allowV) {
            StackTraceElement caller = getCallerStackTraceElement();
            String tag = generateTag(caller);
            Log.v(tag, content, tr);
        }
    }

    public static void d(String content) {
        if (allowLog && allowD) {
            StackTraceElement caller = getCallerStackTraceElement();
            String tag = generateTag(caller);
            Log.d(tag, content);
        }
    }

    public static void d(String content, Throwable tr) {
        if (allowLog && allowD) {
            StackTraceElement caller = getCallerStackTraceElement();
            String tag = generateTag(caller);
            Log.d(tag, content, tr);
        }
    }

    public static void i(String content) {
        if (allowLog && allowI) {
            StackTraceElement caller = getCallerStackTraceElement();
            String tag = generateTag(caller);
            Log.i(tag, content);
        }
    }

    public static void i(String content, Throwable tr) {
        if (allowLog && allowI) {
            StackTraceElement caller = getCallerStackTraceElement();
            String tag = generateTag(caller);
            Log.i(tag, content, tr);
        }
    }

    public static void w(String content) {
        if (allowLog && allowW) {
            StackTraceElement caller = getCallerStackTraceElement();
            String tag = generateTag(caller);
            Log.w(tag, content);
        }
    }

    public static void w(String content, Throwable tr) {
        if (allowLog && allowW) {
            StackTraceElement caller = getCallerStackTraceElement();
            String tag = generateTag(caller);
            Log.w(tag, content, tr);
        }
    }

    public static void e(String content) {
        if (allowLog && allowE) {
            StackTraceElement caller = getCallerStackTraceElement();
            String tag = generateTag(caller);
            Log.e(tag, content);
        }
    }

    public static void e(String content, Throwable tr) {
        if (allowLog && allowE) {
            StackTraceElement caller = getCallerStackTraceElement();
            String tag = generateTag(caller);
            Log.e(tag, content, tr);
        }
    }

    private static StackTraceElement getCallerStackTraceElement() {
        return Thread.currentThread().getStackTrace()[4];
    }

}
