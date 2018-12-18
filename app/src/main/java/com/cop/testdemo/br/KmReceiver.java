package com.cop.testdemo.br;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.cop.testdemo.KoManager;

public class KmReceiver extends BroadcastReceiver {
    public static final String KM_ACTION = "com.uklink.kmutil.action.Main";
    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO: This method is called when the BroadcastReceiver is receiving
        // an Intent broadcast.
        String action = intent.getAction();
        if(KM_ACTION.equals(action)){
            KoManager.getInstance(context).load(context);
        }
    }
}
