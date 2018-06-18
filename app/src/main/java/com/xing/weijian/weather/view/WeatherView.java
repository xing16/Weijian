package com.xing.weijian.weather.view;

import com.amap.api.location.AMapLocation;
import com.xing.weijian.base.mvp.BaseView;
import com.xing.weijian.weather.db.domain.Weather;

/**
 * Created by Administrator on 2018/3/10.
 */

public interface WeatherView extends BaseView {

    // 显示定位信息
    void showLocation(AMapLocation location);

    // 显示天气信息
    void showWeatherInfo(Weather weather);


}
