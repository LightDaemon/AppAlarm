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
import android.widget.TextView;

import com.google.gson.Gson;
import com.rokid.alarm1.R;
import com.rokid.alarm1.Utils.DateUtils;
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
 * Created by ZY on 2016/6/17.
 * Description : 更新闹钟
 */
public class UpdateAlarmActivity extends Activity {
    private DateTimeBean dateTimeBean;
    private DateTimeBean specificTime;
    private long mDateTime;
    private long mSpecificTime;
    private long oldId;
    private String formatDate;
    private String formatSpecific;
    private Gson gson;
    private SlotBeans slotBeans;
    private AlarmBeanHelper helper;
    private AlarmBean alarmBeans;
    private TextView updateText;
    private ImageView updateImg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.updatel_alarm);
        updateText = (TextView) findViewById(R.id.update_alarm);
        updateImg = (ImageView) findViewById(R.id.im_update);
        initView();
        getData();
        setData();
    }


    private void initView() {
        alarmBeans = new AlarmBean();
        helper = AlarmBeanHelper.getInstance(this);
        Intent intent = getIntent();
        Gson gson = new Gson();
        slotBeans = (SlotBeans) intent.getSerializableExtra(getString(R.string.nlp_data));
        try {
            dateTimeBean = gson.fromJson(slotBeans.getDateTime(), DateTimeBean.class);
            specificTime = gson.fromJson(slotBeans.getSpecificTime(), DateTimeBean.class);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void getData() {
        mDateTime = NlpUtils.getTime(dateTimeBean);
        mSpecificTime = NlpUtils.getTime(specificTime);
        formatDate = DateUtils.formatTime(mDateTime);
        formatSpecific = DateUtils.formatTime(mSpecificTime);
    }

    private void setData() {
        if (dateTimeBean == null || specificTime == null) {
            updateText.setText(getString(R.string.tts_no_time_update));
            TTSUtil.showTTS(this, getString(R.string.tts_no_time_update));
        } else {
            List<AlarmBean> myAlarm = helper.getAlarmBytime(formatDate);
            if (myAlarm == null || myAlarm.size() == 0) {
                updateText.setText(getString(R.string.tts_search_Failure));
                TTSUtil.showTTS(this, getString(R.string.tts_search_Failure));
            } else {
                //清除原有数据
                for (AlarmBean alarmBean : myAlarm) {
                    if (alarmBean != null) {
                        oldId = alarmBean.get_id();
                        helper.deleteData(oldId);
                    }
                }

                //将要修改的数据写入数据
                Calendar time = Calendar.getInstance();
                time.setTime(new Date(mSpecificTime));
                alarmBeans.setYear(time.get(Calendar.YEAR) + "");
                alarmBeans.setMonth(time.get(Calendar.MONTH) + "");
                alarmBeans.setDay(time.get(Calendar.DAY_OF_MONTH) + "");
                alarmBeans.setHour(time.get(Calendar.HOUR_OF_DAY) + "");
                alarmBeans.setMinute(time.get(Calendar.MINUTE) + "");
                alarmBeans.setShowTime(mSpecificTime);

                if (time.get(Calendar.HOUR_OF_DAY) < 12) {
                    alarmBeans.setDayZone(AppContent.morning);
                } else if (time.get(Calendar.HOUR_OF_DAY) < 18) {
                    alarmBeans.setDayZone(AppContent.afternoon);
                } else {
                    alarmBeans.setDayZone(AppContent.evening);
                }
                alarmBeans.setStartDate(formatSpecific);
                helper.addData(alarmBeans);

                updateImg.setVisibility(View.VISIBLE);
                updateText.setVisibility(View.INVISIBLE);
                //动画展示图片
                PropertyValuesHolder holderA = PropertyValuesHolder.ofFloat("alpha", 0.0f, 1.0f);
                PropertyValuesHolder holderX = PropertyValuesHolder.ofFloat("ScaleX", 0.0f, 1.0f);
                PropertyValuesHolder holderY = PropertyValuesHolder.ofFloat("ScaleY", 0.0f, 1.0f);
                ObjectAnimator.ofPropertyValuesHolder(updateImg, holderA, holderX, holderY).setDuration(1000).start();

                TTSUtil.showTTS(this, getString(R.string.tts_update_succeed));
                updateAlarm();
            }
        }
    }

    /**
     * 取消旧闹钟 添加新闹钟
     */
    private void updateAlarm() {
        List<AlarmBean> alarmNew = helper.getAlarmBytime(formatSpecific);
        long newId = 0;
        if (alarmNew != null && alarmNew.size() != 0) {
            newId = alarmNew.get(0).get_id();
            AlarmManager manager = (AlarmManager) getSystemService(ALARM_SERVICE);
            Intent intent1 = new Intent(AppContent.ALARM_RECEIVER);
            intent1.putExtra(getString(R.string.nlp_data), newId);

            Intent intent2 = new Intent(AppContent.ALARM_RECEIVER);
            intent1.putExtra(getString(R.string.nlp_data), oldId);

            PendingIntent newIntent = PendingIntent.getBroadcast(this,
                    Integer.parseInt(String.valueOf(newId)), intent1, 0);
            PendingIntent oldIntent = PendingIntent.getBroadcast(this,
                    Integer.parseInt(String.valueOf(oldId)), intent2, 0);

            manager.cancel(oldIntent);
            manager.set(AlarmManager.RTC_WAKEUP, mSpecificTime, newIntent);
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        finish();
    }
}
