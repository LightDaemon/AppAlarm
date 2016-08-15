package com.rokid.alarm1;

import android.content.Context;
import android.support.multidex.MultiDex;
import android.support.multidex.MultiDexApplication;


/**
 * Created by ZY on 2016/6/14.
 * Description :继承MultiDexApplication，解决64k问题
 */
public class MyApplication extends MultiDexApplication {

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }
}
