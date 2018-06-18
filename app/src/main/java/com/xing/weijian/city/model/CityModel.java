package com.xing.weijian.city.model;

import com.xing.weijian.base.mvp.OnModelCallback;
import com.xing.weijian.weather.db.domain.City;
import com.xing.weijian.weather.db.domain.Province;

import java.util.List;

/**
 * Created by Administrator on 2017/11/26.
 */

public interface CityModel {

    void getCityList();

    void getProvinceList(OnModelCallback<List<Province>> callback);





}
