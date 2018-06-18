package com.xing.weijian.coder.model;

import android.util.Log;

import com.xing.weijian.base.mvp.OnModelCallback;
import com.xing.weijian.events.CoderListEvent;
import com.xing.weijian.http.BaseObserver;
import com.xing.weijian.http.RetrofitClient;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Administrator on 2017/9/2.
 */

public class CoderModelImpl implements CoderModel {

    private static final String TAG = "CoderModelImpl";

    @Override
    public void loadData(String type, int pageSize, int curPage) {
        RetrofitClient.getInstance()
                .getApiService()
                .getCoderText(type, pageSize, curPage)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseObserver<CoderBean>() {
                    @Override
                    public void onObserverNext(List<CoderBean> data) {
                        Log.d(TAG, "onObserverNext: data = " + data);
                        EventBus.getDefault().post(new CoderListEvent(data));
                    }
                });

    }


}
