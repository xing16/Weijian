package com.xing.weijian.weather.presenter;

import android.util.Log;

import com.xing.weijian.base.mvp.BasePresenter;
import com.xing.weijian.events.LocationEvent;
import com.xing.weijian.events.WeatherEvent;
import com.xing.weijian.weather.model.WeatherModel;
import com.xing.weijian.weather.model.WeatherModelImpl;
import com.xing.weijian.weather.view.WeatherView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/**
 * Created by Administrator on 2018/3/10.
 */

public class WeatherPresenterImpl extends BasePresenter<WeatherView> {

    private static final String TAG = "WeatherPresenterImpl";

    private WeatherModel weatherModel;

    public WeatherPresenterImpl(WeatherView weatherView) {
        attachView(weatherView);
        weatherModel = new WeatherModelImpl();

    }

    public void getLocation() {
        if (!isViewAttached()) {
            return;
        }
        // 加载loading

        weatherModel.getLocation();
    }

    public void registerEvents() {
        EventBus.getDefault().register(this);
    }

    public void unregisterEvents() {
        EventBus.getDefault().unregister(this);
    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onLocationEvent(LocationEvent event) {
        if (event == null) {
            return;
        }
        Log.d(TAG, "onLocationEvent: " + event.getLocation());
        if (getView() != null) {
            getView().showLocation(event.getLocation());
        }
    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onWeatherEvent(WeatherEvent event) {
        if (event == null) {
            return;
        }
        Log.d(TAG, "onWeatherEvent: weather = " + event.getWeather());
        if (getView() != null) {
            getView().showWeatherInfo(event.getWeather());
        }
    }


}
