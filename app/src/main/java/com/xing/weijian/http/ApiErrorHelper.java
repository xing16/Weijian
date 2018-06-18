package com.xing.weijian.http;

import android.content.Context;
import android.widget.Toast;

import com.xing.weijian.utils.ToastUtil;

import java.io.IOException;

import retrofit2.HttpException;

/**
 * Created by Administrator on 2017/9/16.
 */

public class ApiErrorHelper {

    public static void handleCommonError(Throwable throwable) {
        if (throwable instanceof HttpException) {
            ToastUtil.showShortToast("服务异常，请稍后重试");
        } else if (throwable instanceof IOException) {
            ToastUtil.showShortToast("连接失败");
        } else if (throwable instanceof ApiException) {
            // ApiException 处理
        } else {
            ToastUtil.showShortToast("未知错误");
        }

    }


}
