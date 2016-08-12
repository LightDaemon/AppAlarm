package com.example;

import de.greenrobot.daogenerator.DaoGenerator;
import de.greenrobot.daogenerator.Entity;
import de.greenrobot.daogenerator.Schema;

public class DreenDaoGenerator {

    public static void main(String[] args) throws Exception{
        Schema schema = new Schema(1,"com.rokid.alarm1.beans");
        schema.setDefaultJavaPackageDao("com.rokid.alarm1.db");
        Alarm(schema);
        new DaoGenerator().generateAll(schema, "../Alarm2.0/app/src/main/java");
    }

    private static void Alarm(Schema schema){
        Entity alarmBean = schema.addEntity("AlarmBean");
        alarmBean.addLongProperty("_id").primaryKey().autoincrement()  ; //id：设置为主键，自动增量
        alarmBean.addStringProperty("year");
        alarmBean.addStringProperty("month");
        alarmBean.addStringProperty("day");
        alarmBean.addStringProperty("hour");
        alarmBean.addStringProperty("minute");
        alarmBean.addStringProperty("startDate");   //理论上下一次闹钟开始时间， 日期格式：yyyy年MM月dd日
        alarmBean.addBooleanProperty("isRepeat"); //是否是多次闹钟，即（每天，每周等）
        alarmBean.addIntProperty("isRepeatUnit"); //重复闹钟单位 1.天 2.工作日 3.周 4.月 5.年
        alarmBean.addStringProperty("dayZone"); // 每天的时间段 上午，下午，晚上
        alarmBean.addLongProperty("showTime");  //下一次触发闹钟的时间 单位毫秒
        alarmBean.addBooleanProperty("isWakeUpAlarm"); //boolean NLP中是否包含WakeUp字段

    }
}
