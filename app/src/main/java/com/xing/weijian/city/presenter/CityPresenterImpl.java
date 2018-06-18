package com.xing.weijian.city.presenter;

import android.support.annotation.UiThread;

import com.xing.weijian.base.mvp.BasePresenter;
import com.xing.weijian.base.mvp.OnModelCallback;
import com.xing.weijian.city.model.CityModel;
import com.xing.weijian.city.model.CityModelImpl;
import com.xing.weijian.city.view.CityView;
import com.xing.weijian.events.CityListEvent;
import com.xing.weijian.weather.db.domain.City;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

/**
 * Created by Administrator on 2017/11/26.
 */

public class CityPresenterImpl extends BasePresenter<CityView> {

    private CityModel cityModel;

    public CityPresenterImpl() {
        cityModel = new CityModelImpl();
    }

    public void getData() {
        if (!isViewAttached()) {
            return;
        }
        getView().showLoading();
        cityModel.getCityList();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onCityListEvent(CityListEvent event) {
        if (event == null || !isViewAttached()) {
            return;
        }
        getView().hideLoading();
        getView().showCityList(event.getCityList());
    }
}
