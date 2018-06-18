package com.xing.weijian.application;

import android.app.Application;
import android.content.Context;

import com.mob.MobSDK;

/**
 * Created by Administrator on 2017/11/25.
 */

public class App extends Application {

    private static Context mContext;

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = getApplicationContext();

        // 初始化 sharesdk
        MobSDK.init(this);

    }

    public static Context getContext() {
        return mContext;
    }
}
