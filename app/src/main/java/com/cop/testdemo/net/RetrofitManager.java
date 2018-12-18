package com.cop.testdemo.net;


import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;


public class RetrofitManager {
    private static volatile RetrofitManager instance;
    private static final int CONNECT_TIME_OUT = 30;
    private static final int READ_TIME_OUT = 30;

    private RetrofitManager(){}

    public static RetrofitManager getInstance(){
        if(instance == null){
            synchronized (RetrofitManager.class){
                if (instance == null){
                    instance = new RetrofitManager();
                }
            }
        }
        return instance;
    }



    public OkHttpClient.Builder setOkhttpClientBuilder(){
        OkHttpClient.Builder okhttpClientBuilder = new OkHttpClient.Builder()
//                .addInterceptor(new RetryIntercepter(2))//重试
                .connectTimeout(CONNECT_TIME_OUT, TimeUnit.SECONDS)
                .readTimeout(READ_TIME_OUT, TimeUnit.SECONDS)
                .writeTimeout(READ_TIME_OUT, TimeUnit.SECONDS)
                .retryOnConnectionFailure(true);
        return okhttpClientBuilder;
    }

    public OkHttpClient.Builder setOkhttpClientBuilderWithoutCommon(){
        OkHttpClient.Builder okhttpClientBuilder = new OkHttpClient.Builder()
                .connectTimeout(CONNECT_TIME_OUT, TimeUnit.SECONDS)
                .readTimeout(READ_TIME_OUT, TimeUnit.SECONDS)
                .writeTimeout(READ_TIME_OUT, TimeUnit.SECONDS)
                .retryOnConnectionFailure(true);

        return okhttpClientBuilder;
    }

    public Retrofit initRetrofit(){
        return new Retrofit.Builder()
                .baseUrl(KApi.kUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(setOkhttpClientBuilder().build())
                .build();
    }

    public Retrofit initKR(){
        return new Retrofit.Builder()
                .baseUrl(KApi.kUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(setOkhttpClientBuilder().build())
                .build();
    }

    public Retrofit initgRetrofitWithoutIntercepter(){
        return new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(setOkhttpClientBuilderWithoutCommon().build())
                .build();
    }



}
