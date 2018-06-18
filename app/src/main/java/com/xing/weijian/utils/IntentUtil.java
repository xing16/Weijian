package com.xing.weijian.utils;

import android.content.Context;
import android.content.Intent;

/**
 * Created by Administrator on 2018/6/9.
 */

public class IntentUtil {

    private IntentUtil() {
        throw new UnsupportedOperationException("cannot be instantiated");
    }

    public static void startActivity(Context context, Class clazz) {
        Intent intent = new Intent(context, clazz);
        context.startActivity(intent);
    }




}
