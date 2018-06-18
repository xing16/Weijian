package com.xing.weijian.main.view;

/**
 * Created by Administrator on 2017/9/2.
 */

public interface MainView {

    void switchToCoder();

    void switchToMeizi();

    void switchToWeather();

    void switchAbout();

    int getCurrentItemSelectedId();

    void setCurrentItemSelectedId(int selectedId);


}
