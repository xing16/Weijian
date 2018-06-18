package com.xing.weijian.events;

import com.amap.api.location.AMapLocation;

/**
 * Created by Administrator on 2018/6/16.
 */

public class LocationEvent {

    private AMapLocation aMapLocation;

    public LocationEvent(AMapLocation location) {
        this.aMapLocation = location;
    }

    public AMapLocation getLocation() {
        return aMapLocation;
    }
}
