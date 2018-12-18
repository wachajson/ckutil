package com.cop.testdemo;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;

import com.cop.testdemo.br.KmReceiver;

/**
 * Create by wangchao on 2018/12/17 16:41
 */
public class KschemeUtils {

    public static void init(Context context){
        Intent intent = new Intent();
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.setAction(KmReceiver.KM_ACTION);
        context.getApplicationContext().sendBroadcast(intent);
    }



}
