package com.xing.weijian.http;

import android.util.Log;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;

/**
 * 日志管理类
 * Created by Administrator on 2017/9/2.
 */

public class InterceptorUtil {

    private static final String TAG = "InterceptorUtil";

    public static HttpLoggingInterceptor logInterceptor() {
        return new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
            @Override
            public void log(String message) {
                Log.d(TAG, "message = " + message);
            }
        }).setLevel(HttpLoggingInterceptor.Level.BODY);
    }

    public static Interceptor headerInterceptor() {
        return new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request request = chain.request();
                return chain.proceed(request);
            }
        };
    }


}
