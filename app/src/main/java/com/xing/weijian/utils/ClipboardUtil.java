package com.xing.weijian.utils;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;

/**
 * Created by Administrator on 2018/7/15.
 */

public class ClipboardUtil {

    private ClipboardUtil() {
        throw new UnsupportedOperationException("cannot be instantiated");
    }

    public static void copyText(Context context, String text) {
        ClipboardManager clipboardManager = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
        ClipData clipData = ClipData.newPlainText("text", text);
        clipboardManager.setPrimaryClip(clipData);
    }

}
