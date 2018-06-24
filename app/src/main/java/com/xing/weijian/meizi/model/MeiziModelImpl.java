package com.xing.weijian.meizi.model;

import android.util.Log;

import com.xing.weijian.events.MeiziListEvent;
import com.xing.weijian.http.BaseObserver;
import com.xing.weijian.http.RetrofitClient;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Administrator on 2018/6/23.
 */

public class MeiziModelImpl implements MeiziModel {

    private static final String TAG = "MeiziModelImpl";

    @Override
    public void loadData(int pageSize, int curPage) {
        RetrofitClient.getInstance()
                .getApiService()
                .getMeizis(pageSize, curPage)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseObserver<MeiziBean>() {
                    @Override
                    public void onObserverNext(List<MeiziBean> data) {
                        Log.d(TAG, "onObserverNext: meizis = " + data);
                        EventBus.getDefault().post(new MeiziListEvent(data));

                    }
                });
    }
}
