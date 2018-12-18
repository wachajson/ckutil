package com.cop.testdemo.net;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * 重试拦截器
 */
public class RetryIntercepter implements Interceptor {

    public int maxRetry;//最大重试次数
    private int retryNum = 0;
    public RetryIntercepter(int maxRetry) {
        this.maxRetry = maxRetry;
    }

    @Override
    public Response intercept(Chain chain) {
        Response response = null;
        try {
            Request request = chain.request();
            System.out.println("retryNum=" + retryNum);
            response = chain.proceed(request);
            while (!response.isSuccessful() && retryNum < maxRetry) {
                retryNum++;
                System.out.println("retryNum=" + retryNum);
                response = chain.proceed(request);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return response;
    }
}
