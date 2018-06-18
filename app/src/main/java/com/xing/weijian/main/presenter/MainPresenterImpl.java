package com.xing.weijian.main.presenter;

import com.xing.weijian.R;
import com.xing.weijian.main.view.MainView;

/**
 * Created by Administrator on 2017/9/2.
 */

public class MainPresenterImpl implements MainPresenter {

    private MainView mainView;

    public MainPresenterImpl(MainView mainView) {
        this.mainView = mainView;
    }


    @Override
    public void switchNavigationView(int id) {
        if (id == R.id.nav_coder || id == R.id.nav_meizi) {
            // 如果点击的 item 是当前已经选中的 item,则不处理
            if (id == mainView.getCurrentItemSelectedId()) {
                return;
            }
            mainView.setCurrentItemSelectedId(id);
        }
        switch (id) {
            case R.id.nav_coder:
                mainView.switchToCoder();
                break;
            case R.id.nav_meizi:
                mainView.switchToMeizi();
                break;
            case R.id.nav_weather:
                mainView.switchToWeather();
                break;
            case R.id.nav_about:
                mainView.switchAbout();
                break;
        }

    }
}
