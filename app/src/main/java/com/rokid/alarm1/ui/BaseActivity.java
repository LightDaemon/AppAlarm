package com.rokid.alarm1.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.rokid.alarm1.R;
import com.rokid.alarm1.Utils.LogUtils;
import com.rokid.alarm1.Utils.TTSUtil;
import com.rokid.alarm1.beans.SlotBeans;

/**
 * Created by ZY on 2016/6/13.
 * Description :
 */
public abstract class BaseActivity extends Activity implements BaseView{
    private Intent intent;
    private int res;

    public BaseActivity(int res){
        this.res = res;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(res);
        getData();
        findId();
        initView();
    }
    /**初始化控件*/
    public abstract void findId();

    /**初始化界面，参数赋值*/
    public abstract void initView();

    /**获取上层语音解析后的json数据*/
    private void getData() {
        intent = getIntent();
        String action = intent.getAction();
        if (getResources().getString(R.string.domain).equals(action)){
            String nlp = intent.getStringExtra(getString(R.string.nlp_extra));
            LogUtils.i("nlp:"+nlp);
            if (nlp == null){
                showErrorTTs();
            }else {
                showNextView(nlp);
            }
        } else {
            showErrorTTs();
        }

    }
    /**语音提示错误内容*/
    private void showErrorTTs(){
        TTSUtil.showTTS(this,getResources().getString(R.string.tts_nlp_error));
    }
    /**跳转到其他页面*/
    public void startActivity(Class c, SlotBeans slots){
        Intent intent = new Intent(this,c);
        intent.putExtra(getString(R.string.nlp_data), slots);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_SINGLE_TOP | Intent.FLAG_ACTIVITY_TASK_ON_HOME);
        startActivity(intent);
    }

}
