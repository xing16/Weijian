package com.xing.weijian.events;

import com.xing.weijian.weather.db.domain.City;

import java.util.List;

/**
 * Created by Administrator on 2018/6/14.
 */

public class CityListEvent {

    private List<City> cityList;

    public CityListEvent(List<City> cityList){
        this.cityList = cityList;
    }

    public List<City> getCityList() {
        return cityList;
    }

}
