package com.xing.weijian.utils;

import android.os.Environment;

/**
 * Created by Administrator on 2018/7/15.
 */

public class SdcardUtil {

    private SdcardUtil() {
        throw new UnsupportedOperationException("cannot be instantiated");
    }

    public static boolean isSdcardAvailable() {
        return Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState());
    }

}
