package com.rokid.alarm1;

import com.google.gson.Gson;
import com.rokid.alarm1.Utils.LogUtils;
import com.rokid.alarm1.Utils.TTSUtil;
import com.rokid.alarm1.beans.NlpBeans;
import com.rokid.alarm1.beans.SlotBeans;
import com.rokid.alarm1.finals.AppContent;
import com.rokid.alarm1.ui.AddAlarmActivity;
import com.rokid.alarm1.ui.BaseActivity;
import com.rokid.alarm1.ui.CancelAlarmActivity;
import com.rokid.alarm1.ui.SearchAlarmActivity;
import com.rokid.alarm1.ui.UpdateAlarmActivity;

public class MainActivity extends BaseActivity implements TTSUtil.TtsCallBack {
    private Gson gson = new Gson();

    public MainActivity() {
        super(R.layout.activity_main);
    }

    @Override
    public void findId() {

    }

    @Override
    public void initView() {

    }

    /**
     * 根据intent的类型跳转到不同的Activity
     *
     * @param nlp 解析后的nlp数据
     */
    @Override
    public void showNextView(String nlp) {
        NlpBeans nlpBeans = gson.fromJson(nlp, NlpBeans.class);
        String intent = nlpBeans.getIntent();
        SlotBeans slots = nlpBeans.getSlots();
        LogUtils.i("关于闹钟的命令类型：" + nlpBeans.getIntent());
        if (intent.equals(AppContent.QUERY_ALARM)) {
            startActivity(SearchAlarmActivity.class, slots);
        } else if (intent.equals(AppContent.DELETE_ALARM)) {
            startActivity(CancelAlarmActivity.class, slots);
        } else if (intent.equals(AppContent.ADD_ALARM)) {
            startActivity(AddAlarmActivity.class, slots);
        } else if (intent.equals(AppContent.UPDATE_ALARM)) {
            startActivity(UpdateAlarmActivity.class, slots);
        }
    }


    @Override
    public void ttsCallBack(boolean state, int what, String s) {
        finish();
    }

    @Override
    protected void onStop() {
        super.onStop();
        finish();
    }
}
