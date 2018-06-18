package com.xing.weijian.weather.db.domain;

/**
 * Created by Administrator on 2017/12/2.
 */

public class Province {

    private String provinceName;

    private Integer provinceId;

    private String pinyin;

    public String getProvinceName() {
        return provinceName;
    }

    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName;
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
        return "Province{" +
                "provinceName='" + provinceName + '\'' +
                ", provinceId=" + provinceId +
                ", pinyin='" + pinyin + '\'' +
                '}';
    }
}
