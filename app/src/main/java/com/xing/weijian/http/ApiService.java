package com.xing.weijian.http;

import com.xing.weijian.coder.model.CoderBean;
import com.xing.weijian.weather.db.domain.Weather;

import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.QueryMap;

/**
 * Created by Administrator on 2017/9/2.
 */

public interface ApiService {

//    @GET("{type}/{pageSize}/{curPage}")
//    Observable<BaseResponse<List<CoderBean>>> getCoderText(@Path("type") String type,
//                                                           @Path("pageSize") int pageSize,
//                                                           @Path("curPage") int curPage);

    @GET("{type}/{pageSize}/{curPage}")
    Observable<BaseResponse<CoderBean>> getCoderText(@Path("type") String type,
                                                     @Path("pageSize") int pageSize,
                                                     @Path("curPage") int curPage);


    @GET("https://free-api.heweather.com/s6/weather/forecast")
    Observable<Weather> getWeatherByCity(@QueryMap Map<String, String> params);


}
