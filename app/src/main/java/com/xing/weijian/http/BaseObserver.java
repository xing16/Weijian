package com.xing.weijian.http;

import android.content.Context;
import android.util.Log;

import java.util.List;

import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;

/**
 * 抽象 BaseObserver, 没有实现 Observer 中 onComplete(),让 BaseObserver 子类实现。
 * Created by Administrator on 2017/9/2.
 */

public abstract class BaseObserver<T> implements Observer<BaseResponse<T>> {
    private static final String TAG = "BaseObserver";

    private Context mContext;

    public BaseObserver() {
    }

    public BaseObserver(Context context) {
        this.mContext = context;
    }

    @Override
    public void onSubscribe(@NonNull Disposable d) {
//        onRequestStart();
    }

    @Override
    public void onError(@NonNull Throwable e) {
        ApiErrorHelper.handleCommonError(e);
    }

    @Override
    public void onComplete() {

    }

    @Override
    public void onNext(@NonNull BaseResponse<T> baseResponse) {
        List<T> data = baseResponse.getData();
        Log.d(TAG, "onNext: data ========" + data);
        onObserverNext(data);

    }

    public abstract void onObserverNext(List<T> data);
}
