package com.rokid.alarm1.beans;

import java.io.Serializable;

/**
 * Created by Bassam on 4/9/16.
 */
public class DateTimeBean implements Serializable {
    /********绝对时间**********/
    private String AbsYear;//绝对时间年，例 “2015年”
    private String AbsMonth;//绝对时间月，例“8月”
    private String AbsDay;//绝对时间日，例“18日”
    private String AbsWeek;//绝对时间周，例“第一周”
    private String AbsHour;//绝对时间小时，例“8点”
    private String AbsMinute;//绝对时间分钟，例如“12分”
    private String AbsWeekDay;//绝对时间周几，例“周四”
    /***********相对时间**************/
    private String RelYear;//相对时间年，例“明年”
    private String RelMonth;//相对时间月，例“下月”
    private String RelDay;//相对时间日，例“明天”
    private String RelWeek;//相对时间周，例“下周”
    private String RelHour;//相对时间小时，例“2小时后”
    private String RelMinute;//相对时间分钟，例“30分钟后”
    /*********时间端范围**************/
    private String DayZone;//MORNING:早晨，AFTERNOON：下午，EVENING:晚上

    /***绝对时间持续时长******/
    private String AbsMonthDur;//绝对时间月持续时长，以月为单位
    private String AbsYearDur;//绝对时间年持续时长，以年为单位
    private String AbsDayDur;//绝对时间日持续时长，以日为单位
    private String AbsWeekDur;//绝对时间周持续时长，以周为单位
    private String AbsWeekDayDur;//绝对时间周日持续时长，以日为单位
    private String AbsHourDur;//绝对时间小时持续时长，以小时为单位
    private String AbsMinuteDur;//绝对时间分钟持续时长，以分为单位
    /***********相对时间持续时长*****************/
    private String RelYearDur;//相对时间年持续时长，以年为单位
    private String RelMonthDur;//相对时间月持续时长，以月为单位
    private String RelDayDur;//相对时间日持续时长，以日为单位
    private String RelWeekDur;//相对时间周持续时长，以周为单位
    private String RelHourDur;//相对时间小时持续时长，以小时为单位
    private String RelMinuteDur;//相对时间分钟持续时长，以分钟为单位
    /*************其他数据****************/
    private String Lunar;//是否为农历，true:农历，false:公历
    private String Around;//0:左右，1:之后， -1:之前
    private String DayText;//节日或节气的内容
    private String DayType;//特殊日子类型，节日:Festival，节气:SolerTerm
    private String text;
    private String RepeatType;//重复模式
    private String RepeatInterval;//重复模式的间隔

    private boolean Current;

    public String getAbsYear() {
        return AbsYear;
    }

    public void setAbsYear(String absYear) {
        AbsYear = absYear;
    }

    public String getAbsYearDur() {
        return AbsYearDur;
    }

    public void setAbsYearDur(String absYearDur) {
        AbsYearDur = absYearDur;
    }

    public String getAbsMonth() {
        return AbsMonth;
    }

    public void setAbsMonth(String absMonth) {
        AbsMonth = absMonth;
    }

    public String getAbsMonthDur() {
        return AbsMonthDur;
    }

    public void setAbsMonthDur(String absMonthDur) {
        AbsMonthDur = absMonthDur;
    }

    public String getAbsDay() {
        return AbsDay;
    }

    public void setAbsDay(String absDay) {
        AbsDay = absDay;
    }

    public String getAbsDayDur() {
        return AbsDayDur;
    }

    public void setAbsDayDur(String absDayDur) {
        AbsDayDur = absDayDur;
    }

    public String getAbsHour() {
        return AbsHour;
    }

    public void setAbsHour(String absHour) {
        AbsHour = absHour;
    }

    public String getAbsHourDur() {
        return AbsHourDur;
    }

    public void setAbsHourDur(String absHourDur) {
        AbsHourDur = absHourDur;
    }

    public String getAbsMinute() {
        return AbsMinute;
    }

    public void setAbsMinute(String absMinute) {
        AbsMinute = absMinute;
    }

    public String getAbsMinuteDur() {
        return AbsMinuteDur;
    }

    public void setAbsMinuteDur(String absMinuteDur) {
        AbsMinuteDur = absMinuteDur;
    }

    public String getRelYear() {
        return RelYear;
    }

    public void setRelYear(String relYear) {
        RelYear = relYear;
    }

    public String getRelYearDur() {
        return RelYearDur;
    }

    public void setRelYearDur(String relYearDur) {
        RelYearDur = relYearDur;
    }

    public String getRelMonth() {
        return RelMonth;
    }

    public void setRelMonth(String relMonth) {
        RelMonth = relMonth;
    }

    public String getRelMonthDur() {
        return RelMonthDur;
    }

    public void setRelMonthDur(String relMonthDur) {
        RelMonthDur = relMonthDur;
    }

    public String getRelDay() {
        return RelDay;
    }

    public void setRelDay(String relDay) {
        RelDay = relDay;
    }

    public String getRelDayDur() {
        return RelDayDur;
    }

    public void setRelDayDur(String relDayDur) {
        RelDayDur = relDayDur;
    }

    public String getRelHour() {
        return RelHour;
    }

    public void setRelHour(String relHour) {
        RelHour = relHour;
    }

    public String getRelHourDur() {
        return RelHourDur;
    }

    public void setRelHourDur(String relHourDur) {
        RelHourDur = relHourDur;
    }

    public String getRelMinute() {
        return RelMinute;
    }

    public void setRelMinute(String relMinute) {
        RelMinute = relMinute;
    }

    public String getRelMinuteDur() {
        return RelMinuteDur;
    }

    public void setRelMinuteDur(String relMinuteDur) {
        RelMinuteDur = relMinuteDur;
    }

    public String getLunar() {
        return Lunar;
    }

    public void setLunar(String lunar) {
        Lunar = lunar;
    }

    public String getAround() {
        return Around;
    }

    public void setAround(String around) {
        Around = around;
    }

    public String getDayZone() {
        return DayZone;
    }

    public void setDayZone(String dayZone) {
        DayZone = dayZone;
    }

    public String getRepeatType() {
        return RepeatType;
    }

    public void setRepeatType(String repeatType) {
        RepeatType = repeatType;
    }

    public String getRepeatInterval() {
        return RepeatInterval;
    }

    public void setRepeatInterval(String repeatInterval) {
        RepeatInterval = repeatInterval;
    }

    public String getAbsWeek() {
        return AbsWeek;
    }

    public void setAbsWeek(String absWeek) {
        AbsWeek = absWeek;
    }

    public String getAbsWeekDur() {
        return AbsWeekDur;
    }

    public void setAbsWeekDur(String absWeekDur) {
        AbsWeekDur = absWeekDur;
    }

    public String getAbsWeekDay() {
        return AbsWeekDay;
    }

    public void setAbsWeekDay(String absWeekDay) {
        AbsWeekDay = absWeekDay;
    }

    public String getAbsWeekDayDur() {
        return AbsWeekDayDur;
    }

    public void setAbsWeekDayDur(String absWeekDayDur) {
        AbsWeekDayDur = absWeekDayDur;
    }

    public String getRelWeek() {
        return RelWeek;
    }

    public void setRelWeek(String relWeek) {
        RelWeek = relWeek;
    }

    public String getRelWeekDur() {
        return RelWeekDur;
    }

    public void setRelWeekDur(String relWeekDur) {
        RelWeekDur = relWeekDur;
    }

    public String getDayText() {
        return DayText;
    }

    public void setDayText(String dayText) {
        DayText = dayText;
    }

    public String getDayType() {
        return DayType;
    }

    public void setDayType(String dayType) {
        DayType = dayType;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public boolean isCurrent() {
        return Current;
    }

    public void setCurrent(boolean current) {
        Current = current;
    }

    @Override
    public String toString() {
        return "DateTimeBean{" +
                "AbsYear='" + AbsYear + '\'' +
                ", AbsYearDur='" + AbsYearDur + '\'' +
                ", AbsMonth='" + AbsMonth + '\'' +
                ", AbsMonthDur='" + AbsMonthDur + '\'' +
                ", AbsDay='" + AbsDay + '\'' +
                ", AbsDayDur='" + AbsDayDur + '\'' +
                ", AbsHour='" + AbsHour + '\'' +
                ", AbsHourDur='" + AbsHourDur + '\'' +
                ", AbsMinute='" + AbsMinute + '\'' +
                ", AbsMinuteDur='" + AbsMinuteDur + '\'' +
                ", RelYear='" + RelYear + '\'' +
                ", RelYearDur='" + RelYearDur + '\'' +
                ", RelMonth='" + RelMonth + '\'' +
                ", RelMonthDur='" + RelMonthDur + '\'' +
                ", RelDay='" + RelDay + '\'' +
                ", RelDayDur='" + RelDayDur + '\'' +
                ", RelHour='" + RelHour + '\'' +
                ", RelHourDur='" + RelHourDur + '\'' +
                ", RelMinute='" + RelMinute + '\'' +
                ", RelMinuteDur='" + RelMinuteDur + '\'' +
                ", Lunar='" + Lunar + '\'' +
                ", Around='" + Around + '\'' +
                ", DayZone='" + DayZone + '\'' +
                ", RepeatType='" + RepeatType + '\'' +
                ", RepeatInterval='" + RepeatInterval + '\'' +
                ", AbsWeek='" + AbsWeek + '\'' +
                ", AbsWeekDur='" + AbsWeekDur + '\'' +
                ", AbsWeekDay='" + AbsWeekDay + '\'' +
                ", AbsWeekDayDur='" + AbsWeekDayDur + '\'' +
                ", RelWeek='" + RelWeek + '\'' +
                ", RelWeekDur='" + RelWeekDur + '\'' +
                ", DayText='" + DayText + '\'' +
                ", DayType='" + DayType + '\'' +
                ", text='" + text + '\'' +
                ", Current='" + Current + '\'' +
                '}';
    }
}
