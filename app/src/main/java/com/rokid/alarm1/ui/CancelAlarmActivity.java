package com.rokid.alarm1.ui;

import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
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

import java.util.List;

/**
 * Created by ZY on 2016/6/15.
 * Description :
 */
public class CancelAlarmActivity extends Activity {
    private TextView cancelAction;
    private ImageView cancelSuccess;
    private SlotBeans slotBeans;
    private AlarmBeanHelper helper;
    private AlarmBean alarmBean;
    private DateTimeBean dateTimeBean;
    private Gson gson;
    private long time;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cancel_alarm);
        helper = AlarmBeanHelper.getInstance(this);
        cancelAction = (TextView) findViewById(R.id.tv_cancel);
        cancelSuccess = (ImageView) findViewById(R.id.im_cancel);
        getdata();
    }

    private void getdata() {
        Intent intent = getIntent();
        Gson gson = new Gson();
        slotBeans = (SlotBeans) intent.getSerializableExtra(getString(R.string.nlp_data));
        try {
            dateTimeBean = gson.fromJson(slotBeans.getDateTime(), DateTimeBean.class);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        //是否取消全部
        if (cancelAll(slotBeans)) {
            //取消全部闹钟
            cancelAll();
            //清空数据库
            helper.deleteAll();
            //成功动画
            cancelAction.setVisibility(View.INVISIBLE);
            cancelSuccess.setVisibility(View.VISIBLE);
            PropertyValuesHolder holderA = PropertyValuesHolder.ofFloat("alpha", 0.0f, 1.0f);
            PropertyValuesHolder holderX = PropertyValuesHolder.ofFloat("ScaleX", 0.0f, 1.0f);
            PropertyValuesHolder holderY = PropertyValuesHolder.ofFloat("ScaleY", 0.0f, 1.0f);
            ObjectAnimator.ofPropertyValuesHolder(cancelSuccess, holderA, holderX, holderY).setDuration(1000).start();

            LogUtils.i("已经取消了所有闹钟");
            TTSUtil.showTTS(this, "已经取消了所有闹钟");
        } else {
            if (slotBeans.getDateTime() != null) {
                time = NlpUtils.getTime(dateTimeBean);
                String datetime = DateUtils.formatTime(time);
                //在数据库是否有要删除的数据
                if (searchAlarm(datetime)) {
                    //获取数据库中数据的id
                    long id = getid(datetime);
                    //取消选中的闹钟
                    cancelAlarm(id);
                    //删除数据库
                    helper.deleteData(id);
                    cancelAction.setText(getString(R.string.tts_cancel_alarm));
                    TTSUtil.showTTS(this, getString(R.string.tts_cancel_alarm));
                    LogUtils.i("已经取消了指定闹钟");
                } else {
                    cancelAction.setText(getString(R.string.tts_cancel_alarm));
                    TTSUtil.showTTS(this, getString(R.string.tts_error_time_delete));
                    LogUtils.i("没有找到指定闹钟");
                }
            } else {
                cancelAction.setText(getString(R.string.tts_no_time_delete));
                TTSUtil.showTTS(this, getString(R.string.tts_no_time_delete));
            }

        }
    }

    /**
     * 获取id
     */
    private long getid(String showtime) {
        List<AlarmBean> alarmId = helper.getAlarmBytime(showtime);
        AlarmBean alarmBean1 = alarmId.get(0);
        return alarmBean1.get_id();
    }

    private void cancelAll() {
        List<AlarmBean> alarmBeanList = helper.getAllData();
        for (AlarmBean alarm : alarmBeanList) {
            long id = getid(alarm.getStartDate());
            cancelAlarm(id);
        }
    }

    /**
     * 判断是否取消全部
     */
    private Boolean cancelAll(SlotBeans slotBeans) {
        if (TextUtils.isEmpty(slotBeans.getDateTime()) &&
                !TextUtils.isEmpty(slotBeans.getAll())) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 判断数据库中是否有需要删除的闹钟
     */
    private Boolean searchAlarm(String time) {
        List<AlarmBean> list = helper.getAlarmBytime(time);
        if (list == null || list.size() == 0) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * 取消闹钟
     */
    private void cancelAlarm(long id) {
        AlarmManager manager = (AlarmManager) getSystemService(ALARM_SERVICE);
        Intent cancel = new Intent(AppContent.ALARM_RECEIVER);
        cancel.putExtra(getString(R.string.nlp_data),id);
        PendingIntent pIntent = PendingIntent.getBroadcast(this,
                Integer.parseInt(String.valueOf(id)), cancel, 0);
        manager.cancel(pIntent);
    }

    @Override
    protected void onStop() {
        super.onStop();
        finish();
    }
}
