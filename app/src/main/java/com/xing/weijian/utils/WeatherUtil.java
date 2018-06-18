package com.xing.weijian.utils;

import com.xing.weijian.R;

/**
 * Created by Administrator on 2018/4/6.
 */

public class WeatherUtil {

    private WeatherUtil() {
        throw new UnsupportedOperationException("cannot be instantiated");
    }

    public static int getWeatherIcon(String code) {
        int intCode = Integer.parseInt(code);
        if (intCode <= 100) {  // 晴
            return R.drawable.ic_weather_sunny;
        } else if (intCode < 200) {   // 多云
            return R.drawable.ic_weather_cloudy;
        } else if (intCode < 300) {     // 有风
            return R.drawable.ic_weather_wind;
        } else if (intCode < 400) {  // 有雨
            return R.drawable.ic_weather_rain;
        } else if (intCode < 500) {    // 雪
            return R.drawable.ic_weather_snow;
        } else if (intCode < 600) {    // 雾
            return R.drawable.ic_weather_fog;
        }
        return R.drawable.ic_weather_sunny;
    }


}
