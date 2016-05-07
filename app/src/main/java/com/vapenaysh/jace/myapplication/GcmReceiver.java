package com.vapenaysh.jace.myapplication;

import android.content.ComponentName;
import android.support.v4.content.WakefulBroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.app.Activity;

/**
 * Created by XuanpeiEstherOuyang on 5/6/16.
 */
public class GcmReceiver extends WakefulBroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent){
        ComponentName comp = new ComponentName(context.getPackageName(), GcmMessageHandler.class.getName());

        startWakefulService(context, (intent.setComponent(comp)));
        setResultCode(Activity.RESULT_OK);
    }
}
