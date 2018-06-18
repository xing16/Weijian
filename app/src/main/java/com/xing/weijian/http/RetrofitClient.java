package com.xing.weijian.http;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Administrator on 2017/9/2.
 */

public class RetrofitClient {

    private static RetrofitClient instance;

    private OkHttpClient okHttpClient;

    private Retrofit retrofit;

    private static ApiService apiService;

    private RetrofitClient() {

        okHttpClient = initOkHttpClient();
        retrofit = new Retrofit.Builder()
                .baseUrl(HttpConfig.BASE_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
//                .addConverterFactory(GsonConverterFactory.create())
                .addConverterFactory(CustomGsonConverterFactory.create())
                .client(okHttpClient)
                .build();
        apiService = retrofit.create(ApiService.class);
    }

    public static RetrofitClient getInstance() {
        if (instance == null) {
            synchronized (RetrofitClient.class) {
                if (instance == null) {
                    instance = new RetrofitClient();
                }
            }
        }
        return instance;
    }

    private OkHttpClient initOkHttpClient() {
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .readTimeout(1000, TimeUnit.SECONDS)
                .connectTimeout(1000, TimeUnit.SECONDS)
                .writeTimeout(1000, TimeUnit.SECONDS)
                .retryOnConnectionFailure(true)
                .addInterceptor(InterceptorUtil.logInterceptor())
                .build();
        return okHttpClient;
    }

    public ApiService getApiService() {
        return apiService;
    }


}
