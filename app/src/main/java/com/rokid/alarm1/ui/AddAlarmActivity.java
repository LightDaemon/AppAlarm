package com.rokid.alarm1.ui;

import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.rokid.alarm1.R;
import com.rokid.alarm1.Utils.DateUtils;
import com.rokid.alarm1.Utils.LogUtils;
import com.rokid.alarm1.Utils.NlpUtils;
import com.rokid.alarm1.Utils.TTSUtil;
import com.rokid.alarm1.beans.AlarmBean;
import com.rokid.alarm1.beans.DateTimeBean;
import com.rokid.alarm1.beans.SlotBeans;
import com.rokid.alarm1.db.AlarmBeanHelper;
import com.rokid.alarm1.finals.AppContent;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by ZY on 2016/6/14.
 * Description :添加闹钟模块
 */
public class AddAlarmActivity extends Activity implements TTSUtil.TtsCallBack {
    private AlarmBean alarmBean;
    private long datetime;
    private SlotBeans slotBeans;
    private String formatTime;
    private TextView result;
    private ImageView addSuccess;
    private RelativeLayout addlayout;
    private DateTimeBean dateTimeBean;
    private Gson gson;
    private AlarmBeanHelper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_alarm);
        LogUtils.i("进入添加闹钟模块");
        init();
        getIntentData();
        setData();

    }

    private void init() {
        result = (TextView) findViewById(R.id.add_alarm);
        addSuccess = (ImageView) findViewById(R.id.add_success);
        addlayout = (RelativeLayout) findViewById(R.id.add_alarm_layout);
        helper = AlarmBeanHelper.getInstance(this);
    }


    /**
     * 获取上层穿来的数据
     */
    private void getIntentData() {
        Intent intent = getIntent();
        Gson gson = new Gson();
        slotBeans = (SlotBeans) intent.getSerializableExtra(getString(R.string.nlp_data));
        try {
            dateTimeBean = gson.fromJson(slotBeans.getDateTime(), DateTimeBean.class);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        datetime = NlpUtils.getTime(dateTimeBean);
        formatTime = DateUtils.formatTime(datetime);
        LogUtils.i("要设置的闹钟时间为:" + formatTime);
    }

    /**
     * 向实体类中添加数据,并存入数据库
     */
    private void setData() {
        alarmBean = new AlarmBean();
        if (datetime != -1) {
            LogUtils.i("向AlarmBean实体类中加载数据");
            Calendar time = Calendar.getInstance();
            time.setTime(new Date(datetime));
            alarmBean.setYear(time.get(Calendar.YEAR) + "");
            alarmBean.setMonth(time.get(Calendar.MONTH) + "");
            alarmBean.setDay(time.get(Calendar.DAY_OF_MONTH) + "");
            alarmBean.setHour(time.get(Calendar.HOUR_OF_DAY) + "");
            alarmBean.setMinute(time.get(Calendar.MINUTE) + "");
            alarmBean.setShowTime(datetime);

            if (time.get(Calendar.HOUR_OF_DAY) < 12) {
                alarmBean.setDayZone(AppContent.morning);
            } else if (time.get(Calendar.HOUR_OF_DAY) < 18) {
                alarmBean.setDayZone(AppContent.afternoon);
            } else {
                alarmBean.setDayZone(AppContent.evening);
            }

            alarmBean.setStartDate(formatTime);
            //判断时间是否正确
            if (datetime < System.currentTimeMillis()) {
                TTSUtil.showTTS(this, getString(R.string.tts_error_time));
                addSuccess.setVisibility(View.INVISIBLE);
                result.setText("我现在还不能穿梭时空，回到过去");

            } else {
                List<AlarmBean> alarmId = helper.getAlarmBytime(formatTime);
                if (alarmId.size() != 0) {
                    result.setText("你已经设置过这个时间点的闹钟");
                    addSuccess.setVisibility(View.INVISIBLE);
                    TTSUtil.showTTS(this, "你已经设置过这个时间点的闹钟");
                } else {
                    depositDB();
                    setAlarm();
                    //动画的形式加载图片
                    addSuccess.setVisibility(View.VISIBLE);
                    PropertyValuesHolder holderA = PropertyValuesHolder.ofFloat("alpha", 0.0f, 1.0f);
                    PropertyValuesHolder holderX = PropertyValuesHolder.ofFloat("ScaleX", 0.0f, 1.0f);
                    PropertyValuesHolder holderY = PropertyValuesHolder.ofFloat("ScaleY", 0.0f, 1.0f);
                    ObjectAnimator.ofPropertyValuesHolder(addSuccess, holderA, holderX, holderY).setDuration(1000).start();
                    TTSUtil.showTTS(this, getString(R.string.tts_add_alarm));
                }
            }
        } else {
            TTSUtil.showTTS(this, getString(R.string.tts_no_time));
            addSuccess.setVisibility(View.INVISIBLE);
            result.setText("不告诉我时间，我怎么给你设置闹钟呢");

        }
    }

    /**
     * 设置闹钟
     */
    private void setAlarm() {
        LogUtils.i("开启闹钟控制器，设置闹钟");
        long id = getId(alarmBean.getStartDate());
        AlarmManager manager = (AlarmManager) getSystemService(ALARM_SERVICE);
        Intent intent = new Intent(AppContent.ALARM_RECEIVER);
        intent.putExtra(getString(R.string.nlp_data), id);
        PendingIntent pIntent = PendingIntent.getBroadcast(this,
                Integer.parseInt(String.valueOf(id)), intent, 0);
        manager.set(AlarmManager.RTC_WAKEUP, datetime, pIntent);
        result.setVisibility(View.INVISIBLE);
    }

    /**
     * 将数据存入数据库
     */
    private void depositDB() {
        helper.addData(alarmBean);
    }

    /**
     * 获取id
     */
    private long getId(String showtime) {
        List<AlarmBean> alarmId = helper.getAlarmBytime(showtime);
        AlarmBean alarmBean = alarmId.get(0);
        return alarmBean.get_id();
    }

    @Override
    protected void onStop() {
        super.onStop();
        finish();
    }

    @Override
    public void ttsCallBack(boolean state, int what, String s) {
        finish();
    }
}
