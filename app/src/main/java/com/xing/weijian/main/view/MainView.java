package com.xing.weijian.main.view;

import com.xing.weijian.base.mvp.BaseView;

/**
 * Created by Administrator on 2017/9/2.
 */

public interface MainView extends BaseView {

    void switchToCoder();

    void switchToMeizi();

    void switchToWeather();

    void switchAbout();

    int getCurrentItemSelectedId();

    void setCurrentItemSelectedId(int selectedId);


}
