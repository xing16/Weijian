package com.xing.weijian.weather.db.domain;

/**
 * Created by Administrator on 2017/11/25.
 */

public class City {

    private String cityName;

    private Integer provinceId;

    private String pinyin;

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public Integer getProvinceId() {
        return provinceId;
    }

    public void setProvinceId(Integer provinceId) {
        this.provinceId = provinceId;
    }

    public String getPinyin() {
        return pinyin;
    }

    public void setPinyin(String pinyin) {
        this.pinyin = pinyin;
    }

    @Override
    public String toString() {
        return "City{" +
                "cityName='" + cityName + '\'' +
                ", provinceId=" + provinceId +
                ", pinyin='" + pinyin + '\'' +
                '}';
    }
}
