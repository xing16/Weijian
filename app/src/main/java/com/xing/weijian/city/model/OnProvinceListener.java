package com.xing.weijian.city.model;

import com.xing.weijian.weather.db.domain.Province;

import java.util.List;

/**
 * Created by Administrator on 2017/11/26.
 */

public interface OnProvinceListener {

    void onSuccess(List<Province> provinceList);

    void onFailure(String error);
}
