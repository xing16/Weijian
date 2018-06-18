package com.xing.weijian.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BlurMaskFilter;
import android.support.v8.renderscript.Allocation;
import android.support.v8.renderscript.Element;
import android.support.v8.renderscript.RenderScript;
import android.support.v8.renderscript.ScriptIntrinsicBlur;

/**
 * Created by Administrator on 2017/4/15.
 */

public class BlurUtil {

    private BlurUtil() {
        throw new UnsupportedOperationException("cannot be instantiated");
    }

    /**
     * @param radius 取值(0,25]
     * @param bitmap
     * @return
     */
    public static Bitmap blur(Context context, Bitmap bitmap, int radius) {
        RenderScript rs = RenderScript.create(context);
        // 创建输出bitmap
        Bitmap outputBitmap = Bitmap.createBitmap(bitmap);
        // 创建用于输入的脚本类型
        Allocation input = Allocation.createFromBitmap(rs, bitmap);
        // 创建用于输出的脚本类型
        Allocation output = Allocation.createFromBitmap(rs, outputBitmap);
        ScriptIntrinsicBlur scriptIntrinsicBlur = ScriptIntrinsicBlur.create(rs, Element.U8_4(rs));
        scriptIntrinsicBlur.setRadius(radius);
        scriptIntrinsicBlur.setInput(input);
        scriptIntrinsicBlur.forEach(output);
        output.copyTo(outputBitmap);
        return outputBitmap;
    }


}
