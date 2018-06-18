package com.xing.weijian.city.view;

import com.xing.weijian.base.mvp.BaseView;
import com.xing.weijian.weather.db.domain.City;
import com.xing.weijian.weather.db.domain.Province;

import java.util.List;

/**
 * Created by Administrator on 2017/11/26.
 */

public interface CityView extends BaseView {

    void showCityList(List<City> list);

}
