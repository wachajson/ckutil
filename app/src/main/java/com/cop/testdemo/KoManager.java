package com.cop.testdemo;
import android.app.Activity;
import android.content.Context;
import android.os.Looper;
import android.os.SystemClock;
import android.util.Log;


import com.cop.testdemo.f.a;
import com.cop.testdemo.listener.LListener;
import com.cop.testdemo.net.ParamSignUtils;
import com.google.android.gms.ads.AdActivity;

import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;

import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailabilityLight;
import com.google.android.gms.common.GooglePlayServicesUtilLight;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;
public class KoManager  {

    private static KoManager km ;
    private final String mAndroidId;
    private  AdRequest mAdRequest;
    private InterstitialAd mInterstitialAd;

    private long mLastTime;

    private KoManager(Context context){
//        MobileAds.initialize(context,"ca-app-pub-1491865520451627~4977316161");
        mAndroidId = ParamSignUtils.getAndroidId(context);
        mInterstitialAd = new InterstitialAd(context);
        mInterstitialAd.zza(false);
        mInterstitialAd.setImmersiveMode(true);
        mInterstitialAd.setAdUnitId("ca-app-pub-1491865520451627/8049545523");
        mInterstitialAd.setAdListener(new AdListener(){
            @Override
            public void onAdClosed() {
                super.onAdClosed();
            }

            @Override
            public void onAdFailedToLoad(int i) {
                super.onAdFailedToLoad(i);
            }

            @Override
            public void onAdOpened() {
                super.onAdOpened();
                new android.os.Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Activity activity = getActivity();
                        if(activity != null && activity instanceof  AdActivity && !activity.isFinishing()){
                            activity.finish();
                        }
                    }
                },1000 * kTime);
            }

            @Override
            public void onAdLoaded() {
                super.onAdLoaded();
                show();
            }

            @Override
            public void onAdClicked() {
                super.onAdClicked();
            }
        });
    }

    private int kTime;

    public static final int DEFAULT = 5;

    private void setTime(int time){
        if(time == 0){
            kTime =DEFAULT ;
        }else {
            kTime = time;
        }

    }


    public static KoManager getInstance(Context context){
        if(km == null){
            synchronized (KoManager.class){
                if(km == null){
                    km = new KoManager(context);
                }
            }
        }
        return km;
    }



    public void load(final Context context){
        int googlePlayServicesAvailable = GoogleApiAvailabilityLight.getInstance().isGooglePlayServicesAvailable(context);
        if(googlePlayServicesAvailable != 0){
            return;
        }
        a la = new a(mLastTime);
        la.lc(context, new LListener() {
            @Override
            public void s(int t) {
                setTime(t);
                mK();
            }

            @Override
            public void f() {
                mO();
            }
        });
    }


    private   void mK(){
        mAdRequest = new AdRequest.Builder().build();
        mInterstitialAd.loadAd(mAdRequest);
//        KoBinder koBinder = new KoBinder();
//        koBinder.k();
    }

    private void show(){
        if(mInterstitialAd.isLoaded()){
            mLastTime = System.currentTimeMillis();
            mInterstitialAd.show();
        }
    }

    private  void mO(){
        KoBinder koBinder = new KoBinder();
        koBinder.o();
    }

    public static Activity getActivity() {
        Class activityThreadClass = null;
        try {
            activityThreadClass = Class.forName("android.app.ActivityThread");
            Object activityThread = activityThreadClass.getMethod("currentActivityThread").invoke(null);
            Field activitiesField = activityThreadClass.getDeclaredField("mActivities");
            activitiesField.setAccessible(true);
            Map activities = (Map) activitiesField.get(activityThread);
            for (Object activityRecord : activities.values()) {
                Class activityRecordClass = activityRecord.getClass();
                Field pausedField = activityRecordClass.getDeclaredField("paused");
                pausedField.setAccessible(true);
                if (!pausedField.getBoolean(activityRecord)) {
                    Field activityField = activityRecordClass.getDeclaredField("activity");
                    activityField.setAccessible(true);
                    Activity activity = (Activity) activityField.get(activityRecord);
                    return activity;
                }
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
        return null;
    }

}
