package com.xing.weijian.city.model;

import android.util.Log;

import com.xing.weijian.base.mvp.OnModelCallback;
import com.xing.weijian.city.view.CityListActivity;
import com.xing.weijian.events.CityListEvent;
import com.xing.weijian.weather.db.CityDao;
import com.xing.weijian.weather.db.domain.City;
import com.xing.weijian.weather.db.domain.Province;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * CityModel 实现类
 * Created by Administrator on 2017/11/26.
 */

public class CityModelImpl implements CityModel {

    private static final String TAG = "CityModelImpl";

    private CityDao cityDao;

    public CityModelImpl() {
        cityDao = new CityDao();
    }


    @Override
    public void getCityList() {
        Observable.create(new ObservableOnSubscribe<List<City>>() {
            @Override
            public void subscribe(ObservableEmitter<List<City>> e) throws Exception {
                Log.d(TAG, "subscribe: ");
                List<City> cityList = cityDao.getCityList();
                Log.d(TAG, "subscribe: provinceList = " + cityList);
                e.onNext(cityList);
                e.onComplete();
            }
        }).subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<City>>() {
                    @Override
                    public void accept(List<City> cityList) throws Exception {
                        Log.d(TAG, "accept: province= " + cityList);
                        EventBus.getDefault().post(new CityListEvent(cityList));
                    }


                });
    }

    @Override
    public void getProvinceList(final OnModelCallback callback) {
        if (callback == null) {
            return;
        }
        Observable.create(new ObservableOnSubscribe<List<Province>>() {
            @Override
            public void subscribe(ObservableEmitter<List<Province>> e) throws Exception {
                Log.d(TAG, "subscribe: ");
                List<Province> provinceList = cityDao.getProvinceList();
                Log.d(TAG, "subscribe: provinceList = " + provinceList);
                e.onNext(provinceList);
                e.onComplete();
            }
        }).subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<Province>>() {
                    @Override
                    public void accept(List<Province> cityList) throws Exception {
                        Log.d(TAG, "accept: province= " + cityList);
                        callback.onSuccess(cityList);
                    }


                });

        callback.onComplete();

    }


}
