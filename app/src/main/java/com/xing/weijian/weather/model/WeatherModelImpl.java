package com.xing.weijian.weather.model;

import android.util.Log;

import com.amap.api.location.AMapLocation;
import com.xing.weijian.events.LocationEvent;
import com.xing.weijian.events.WeatherEvent;
import com.xing.weijian.http.RetrofitClient;
import com.xing.weijian.utils.LocationHelper;
import com.xing.weijian.weather.db.domain.Weather;

import org.greenrobot.eventbus.EventBus;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;
import java.util.WeakHashMap;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Administrator on 2018/3/10.
 */

public class WeatherModelImpl implements WeatherModel {

    private static final String TAG = "WeatherModelImpl";

    @Override
    public void getLocation() {
        Observable.create(new ObservableOnSubscribe<AMapLocation>() {
            @Override
            public void subscribe(final ObservableEmitter<AMapLocation> e) throws Exception {
                LocationHelper.getInstance().setLocationCallback(new LocationHelper.OnLocationCallback() {
                    @Override
                    public void onSuccess(AMapLocation amapLocation) {
                        e.onNext(amapLocation);
                        EventBus.getDefault().post(new LocationEvent(amapLocation));
                        Log.d(TAG, "onSuccess: am=====" + amapLocation + "city = " + amapLocation.getCityCode());
                    }

                    @Override
                    public void onFailure() {

                    }
                });
                LocationHelper.getInstance().startLocation();

            }
        }).flatMap(new Function<AMapLocation, ObservableSource<Weather>>() {
            @Override
            public ObservableSource<Weather> apply(AMapLocation location) throws Exception {
                return getWeatherByCity(location);
            }
        }).subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Weather>() {
                    @Override
                    public void accept(Weather weather) throws Exception {
                        Log.d(TAG, "accept: HeWeather6 = " + weather.getHeWeather6());
                        if (weather != null) {
                            EventBus.getDefault().post(new WeatherEvent(weather));
                        }
                    }
                });
    }


    private Observable<Weather> getWeatherByCity(final AMapLocation amapLocation) {
        Map<String, String> map = new HashMap<>();
        map.put("location", amapLocation.getCity());
        map.put("key", "dcacca46ad534001b4d45d425d0aa008");
        return RetrofitClient.getInstance()
                .getApiService()
                .getWeatherByCity(map)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread());
    }


}
