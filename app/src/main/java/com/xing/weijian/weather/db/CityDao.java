package com.xing.weijian.weather.db;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.xing.weijian.utils.TinyPinyinUtil;
import com.xing.weijian.weather.db.domain.City;
import com.xing.weijian.weather.db.domain.Province;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/11/25.
 */

public class CityDao {

    public CityDao() {
    }


    public List<Province> getProvinceList() {
        List<Province> provinceList = new ArrayList<>();
        SQLiteDatabase database = CityDbManager.getInstance().openDatabase();
        Cursor cursor = database.query(CityDbManager.TABLE_PROVINCE, null, null, null, null, null, null);
        if (cursor != null) {
            while (cursor.moveToNext()) {
                Province province = new Province();
                province.setProvinceName(cursor.getString(cursor.getColumnIndex("provinceName")));
                province.setProvinceId(cursor.getInt(cursor.getColumnIndex("provinceId")));
                province.setPinyin(cursor.getString(cursor.getColumnIndex("pinyin")));
                provinceList.add(province);
            }
        }
        database.close();
        return provinceList;
    }

    public List<City> getCityList() {
        List<City> cityList = new ArrayList<>();
        SQLiteDatabase database = CityDbManager.getInstance().openDatabase();
        Cursor cursor = database.query(CityDbManager.TABLE_CITY, null, null, null, null, null, "pinyin asc");
        if (cursor != null) {
            while (cursor.moveToNext()) {
                City city = new City();
                city.setCityName(cursor.getString(cursor.getColumnIndex("cityName")));
                city.setProvinceId(cursor.getInt(cursor.getColumnIndex("provinceId")));
                city.setPinyin(cursor.getString(cursor.getColumnIndex("pinyin")));
                cityList.add(city);
            }
        }
        database.close();
        return cityList;
    }

    public void insert(List<City> cityList) {
        SQLiteDatabase database = CityDbManager.getInstance().openDatabase();
        for (City city : cityList) {
            String cityName = city.getCityName();
            String pinyin = TinyPinyinUtil.toPinyin(cityName);
            ContentValues contentValues = new ContentValues();
            contentValues.put("pinyin", pinyin);
            database.update(CityDbManager.TABLE_CITY, contentValues, "cityName = ?", new String[]{cityName});
        }

    }


    public void insertProvinceList(List<Province> provinceList) {
        SQLiteDatabase database = CityDbManager.getInstance().openDatabase();
        for (Province city : provinceList) {
            String provinceName = city.getProvinceName();
            String pinyin = TinyPinyinUtil.toPinyin(provinceName);
            ContentValues contentValues = new ContentValues();
            contentValues.put("pinyin", pinyin);
            database.update(CityDbManager.TABLE_PROVINCE, contentValues, "provinceName = ?", new String[]{provinceName});
        }

    }


}
