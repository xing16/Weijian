package com.xing.weijian.service;

import android.app.IntentService;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;

import com.mob.MobSDK;

/**
 * Created by Administrator on 2018/7/8.
 */

public class SDKInitializeService extends IntentService {

    public SDKInitializeService() {
        super("SDKInitializeService");
    }

    public static void start(Context context) {
        Intent intent = new Intent(context, SDKInitializeService.class);
        context.startService(intent);
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        // 初始化 sharesdk
        MobSDK.init(this.getApplicationContext());
    }
}
