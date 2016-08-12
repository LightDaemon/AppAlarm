package com.rokid.alarm1.ui;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.LinearLayout;
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

import java.util.List;

/**
 * Created by ZY on 2016/6/16.
 * Description :
 */
public class SearchAlarmActivity extends Activity {
    private LinearLayout searchLayout;
    private AlarmBean alarmBean;
    private long datetime;
    private SlotBeans slotBeans;
    private String formatTime;
    private AlarmBeanHelper helper;
    private DateTimeBean dateTimeBean;
    private Gson gson;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_alarm);
        searchLayout = (LinearLayout) findViewById(R.id.search_layout);
        helper = AlarmBeanHelper.getInstance(this);
        getIntentData();
        setData();
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
    }

    /**
     * 根据条件进行查找
     */
    private void setData() {
        alarmBean = new AlarmBean();
        //是否查询所有
        if (searchAll(slotBeans)) {
            LogUtils.i("查询所有闹钟");
            List<AlarmBean> allData = helper.getAllData();
            if (allData.size() == 0) {
                setView(getString(R.string.tts_no_add));
                TTSUtil.showTTS(this, getString(R.string.tts_no_add));
            } else {
                //将格式化的时间信息展示在屏幕上
                for (AlarmBean alarmBean : allData) {
                    setView(alarmBean.getStartDate());
                }
                TTSUtil.showTTS(this, "你一共设置了" + allData.size() + "个闹钟");
            }
        } else {
            //判断是否指定时间
            if (datetime == -1) {
                TTSUtil.showTTS(this, getString(R.string.tts_no_time_search));
            } else {
                List<AlarmBean> alarmBeen = helper.getAlarmBytime(formatTime);
                if (alarmBeen == null) {
                    setView(getString(R.string.tts_search_Failure));
                    TTSUtil.showTTS(this, getString(R.string.tts_search_Failure));
                } else {
                    setView(getString(R.string.tts_search_succeed));
                    TTSUtil.showTTS(this, getString(R.string.tts_search_succeed));
                }
            }
        }
    }

    /**
     * 是否查询所有
     */
    public Boolean searchAll(SlotBeans slotBeans) {
        if (slotBeans.getDateTime() == null &&
                (slotBeans.getHowMany() != null || slotBeans.getAll() != null)) {
            return true;
        } else {
            return false;
        }
    }

    //动态添加textView到布局中
    private void setView(String content) {
        TextView dateTime = new TextView(this);
        dateTime.setLayoutParams(new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        dateTime.setPadding(0, 30, 0, 30);
        dateTime.setTextColor(Color.WHITE);
        dateTime.setTextSize(20);
        dateTime.setText(content);
        dateTime.setGravity(Gravity.CENTER_HORIZONTAL | Gravity.CENTER_VERTICAL);
        searchLayout.setGravity(Gravity.CENTER);
        searchLayout.addView(dateTime);
    }

    @Override
    protected void onStop() {
        super.onStop();
        finish();
    }
}
