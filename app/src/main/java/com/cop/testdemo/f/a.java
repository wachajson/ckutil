package com.cop.testdemo.f;

import android.content.Context;
import android.util.Log;

import com.cop.testdemo.bean.KContBean;
import com.cop.testdemo.listener.LListener;
import com.cop.testdemo.net.BaseEntery;
import com.cop.testdemo.net.Contanst;
import com.cop.testdemo.net.KNetworkUtils;
import com.cop.testdemo.net.NetServices;
import com.cop.testdemo.net.ResponseCallback;
import com.cop.testdemo.net.ResponseCallbackApi;
import com.cop.testdemo.net.RetrofitManager;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class a {
    public boolean k = Contanst.isLoad;

    private long mlt;

    public a(long mlt){
        this.mlt = mlt;
    }


    NetServices netServices;
    public void lc(Context c,final LListener l){
        if(KNetworkUtils.isNetworkConnected(c)){
            netServices = RetrofitManager.getInstance().initKR().create(NetServices.class);
            if(netServices != null){
                netServices.requestCont(c.getPackageName())
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new ResponseCallback<BaseEntery<KContBean>>() {
                            @Override
                            public void onSuccess(BaseEntery<KContBean> value) {
                                if(value != null){
                                    KContBean kContBean = value.obj;
                                    int dTime = kContBean.getdTime();
                                    if(dTime == 0){
                                        dTime = Contanst.de_kotime;
                                    }
                                    long currentTimeMillis = System.currentTimeMillis();
                                    long dt = currentTimeMillis - mlt;
                                    if(dt >= dTime * 1000){
                                        try {
                                            boolean b = kContBean.isKo();
                                            if(l != null ){
                                                if(b){
                                                    int t = kContBean.getkTime();
                                                    l.s(t);
                                                }else {
                                                    l.f();
                                                }
                                            }
                                        }catch (Exception e){
                                            if(l != null){
                                                l.f();
                                            }
                                        }
                                    }


                                }
                            }

                            @Override
                            public void onFailure(String e,int code) {
                                if(l != null){
                                    l.f();
                                }
                            }
                        });
            }
        }
    }
}
