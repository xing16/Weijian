package com.xing.weijian.utils;

import android.util.Log;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.xing.weijian.application.App;

/**
 * 高德地图定位工具类
 * Created by Administrator on 2017/11/26.
 */

public class LocationHelper {

    private static final String TAG = "LocationHelper";

    //声明AMapLocationClient类对象
    public AMapLocationClient mLocationClient = null;

    //声明AMapLocationClientOption对象
    public AMapLocationClientOption mLocationOption = null;

    //声明定位回调监听器
    public LocationListener mLocationListener;

    private static LocationHelper instance;

    private LocationHelper() {
        //初始化定位
        mLocationClient = new AMapLocationClient(App.getContext());
        initLocaitonClientOption();
        mLocationListener = new LocationListener();
        mLocationClient.setLocationListener(mLocationListener);
    }

    public static LocationHelper getInstance() {
        if (instance == null) {
            synchronized (LocationHelper.class) {
                if (instance == null) {
                    instance = new LocationHelper();
                }
            }
        }
        return instance;
    }


    private void initLocaitonClientOption() {
        mLocationOption = new AMapLocationClientOption();
        mLocationOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);
        //设置是否返回地址信息（默认返回地址信息）
        mLocationOption.setNeedAddress(true);
        //设置是否强制刷新WIFI，默认为true，强制刷新。
        mLocationOption.setWifiActiveScan(false);
        mLocationOption.setHttpTimeOut(20000);
        mLocationOption.setOnceLocation(true);
        //关闭缓存机制
        mLocationOption.setLocationCacheEnable(false);
        //给定位客户端对象设置定位参数
        mLocationClient.setLocationOption(mLocationOption);
    }


    /**
     * 开始定位
     */
    public void startLocation() {
        //启动定位
        mLocationClient.startLocation();
    }

    /**
     * 停止定位
     */
    public void stopLocation() {
        mLocationClient.onDestroy();//销毁定位客户端，同时销毁本地定位服务。
    }


    private class LocationListener implements AMapLocationListener {

        @Override
        public void onLocationChanged(AMapLocation amapLocation) {
            if (amapLocation != null) {
                if (amapLocation.getErrorCode() == 0) {
                    if (callback != null) {
                        callback.onSuccess(amapLocation);
                    }
                } else {
                    if (callback != null) {
                        callback.onFailure();
                    }
                    //定位失败时，可通过ErrCode（错误码）信息来确定失败的原因，errInfo是错误信息，详见错误码表。
                    Log.e("debug", "location Error, ErrCode:" + amapLocation.getErrorCode()
                            + ", errInfo:" + amapLocation.getErrorInfo());
                }
            } else {
                if (callback != null) {
                    callback.onFailure();
                }
            }
        }
    }


    private OnLocationCallback callback;


    public interface OnLocationCallback {
        void onSuccess(AMapLocation amapLocation);

        void onFailure();
    }

    public void setLocationCallback(OnLocationCallback callback) {
        this.callback = callback;
    }


}
