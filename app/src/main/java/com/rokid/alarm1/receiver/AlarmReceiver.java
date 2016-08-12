package com.rokid.alarm1.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.rokid.alarm1.R;
import com.rokid.alarm1.ui.ShowAlarmActivity;

/**
 * Created by ZY on 2016/6/14.
 * Description :
 */
public class AlarmReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        long id = intent.getLongExtra(context.getString(R.string.nlp_data), 1l);
        if (action.equals(context.getString(R.string.alarm_receiver))) {
            Intent intent1 = new Intent(context, ShowAlarmActivity.class);
            intent1.putExtra(context.getString(R.string.nlp_data), id);
            intent1.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.
                    FLAG_ACTIVITY_SINGLE_TOP | Intent.FLAG_ACTIVITY_TASK_ON_HOME);
            context.startActivity(intent1);
        }

    }
}
