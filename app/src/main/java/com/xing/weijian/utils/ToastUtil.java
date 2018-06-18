package com.xing.weijian.utils;

import android.content.Context;
import android.widget.Toast;

import com.xing.weijian.application.App;

import io.reactivex.internal.operators.completable.CompletableOnErrorComplete;

/**
 * Created by Administrator on 2017/9/16.
 */

public class ToastUtil {

    private ToastUtil() {
        throw new UnsupportedOperationException("cannot be instantiated");
    }

    private static Toast toast;

    public static void showShortToast(CharSequence charSequence) {
        if (toast == null) {
            toast = Toast.makeText(App.getContext(), charSequence, Toast.LENGTH_SHORT);
        } else {
            toast.setText(charSequence);
        }
        toast.show();
    }

    public static void showShortToast(int resId) {
        if (toast == null) {
            toast = Toast.makeText(App.getContext(), resId, Toast.LENGTH_SHORT);
        } else {
            toast.setText(resId);
        }
        toast.show();
    }

}
