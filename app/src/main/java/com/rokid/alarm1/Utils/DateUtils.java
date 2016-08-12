package com.rokid.alarm1.Utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by ZY on 2016/6/16.
 * Description : 时间格式化工具类
 */
public class DateUtils {

    /**格式化时间（yyyy年MM月dd日 hh时mm分）*/
    public static String formatTime(long time){
        SimpleDateFormat dateformat = new SimpleDateFormat("yyyy年MM月dd日 HH时mm分");
        String format = dateformat.format(new Date(time));
        return format;
    }
}
