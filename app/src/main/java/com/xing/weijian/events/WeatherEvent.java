package com.xing.weijian.events;

import com.xing.weijian.weather.db.domain.Weather;

/**
 * Created by Administrator on 2018/6/16.
 */

public class WeatherEvent {

    private Weather weather;

    public WeatherEvent(Weather weather) {
        this.weather = weather;
    }

    public Weather getWeather() {
        return weather;
    }

}
