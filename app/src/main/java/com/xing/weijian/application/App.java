package com.xing.weijian.application;

import android.app.Application;
import android.content.Context;

import com.mob.MobSDK;
import com.xing.weijian.service.SDKInitializeService;

/**
 * Created by Administrator on 2017/11/25.
 */

public class App extends Application {

    private static Context mContext;

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = getApplicationContext();

        // SDK 懒加载
        SDKInitializeService.start(mContext);
    }

    public static Context getContext() {
        return mContext;
    }
}
