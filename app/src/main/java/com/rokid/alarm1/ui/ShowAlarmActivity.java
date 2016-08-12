package com.rokid.alarm1.ui;

import android.animation.ObjectAnimator;
import android.app.Activity;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.animation.Interpolator;
import android.widget.LinearLayout;

import com.rokid.alarm1.R;
import com.rokid.alarm1.Utils.LogUtils;
import com.rokid.alarm1.Utils.TTSUtil;
import com.rokid.alarm1.db.AlarmBeanHelper;

/**
 * Created by ZY on 2016/6/14.
 * Description :播放闹钟铃声
 */
public class ShowAlarmActivity extends Activity implements TTSUtil.TtsCallBack, Interpolator {
    private LinearLayout showAlarm;
    private MediaPlayer mediaPlayer;
    private AlarmBeanHelper helper;
    private long id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.show_alarm);
        id = getIntent().getLongExtra(getString(R.string.nlp_data), 1l);
        initView();
        setData();
    }

    private void setData() {
        //设置动画
        float translationY = showAlarm.getTranslationY();
        ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(showAlarm, "translationY", -50f, translationY);
        objectAnimator.setDuration(2000);
        objectAnimator.start();
        //播放器初始化
        mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.wake);


    }

    private void initView() {
        showAlarm = (LinearLayout) findViewById(R.id.showAlarmLayout);
        mediaPlayer = new MediaPlayer();
        helper = AlarmBeanHelper.getInstance(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        mediaPlayer.start();
        //当播发音乐发送错误的时候
        mediaPlayer.setOnErrorListener(new MediaPlayer.OnErrorListener() {
            @Override
            public boolean onError(MediaPlayer mp, int what, int extra) {
                mediaPlayer.stop();   //停止播放音乐
                mediaPlayer.release();//释放资源
                mediaPlayer = null;
                return true;
            }
        });
    }

    /**
     * 停止播放音乐
     */
    public void stop() {
        if (mediaPlayer != null) {
            mediaPlayer.stop();
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }

    @Override
    protected void onStop() {
        helper.deleteData(id);
        mediaPlayer.stop();
        mediaPlayer = null;
        super.onStop();
        finish();
    }

    @Override
    public void ttsCallBack(boolean state, int what, String s) {
        LogUtils.i("音乐播放服务的语音回调：" + what);
        stop();
    }

    @Override
    public float getInterpolation(float input) {
        return 0;
    }
}
