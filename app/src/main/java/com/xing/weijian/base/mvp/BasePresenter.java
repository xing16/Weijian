package com.xing.weijian.base.mvp;

import org.greenrobot.eventbus.EventBus;

/**
 * Created by Administrator on 2017/12/2.
 * <p>
 * Presenter 纯 Java 代码，不包含 Android api
 */

public class BasePresenter<V extends BaseView> {

    private V view;

    /**
     * 绑定 view,在初始化中调用该方法
     *
     * @param view
     */
    public void attachView(V view) {
        this.view = view;
    }


    /**
     * 解除绑定 view,在 View onDestroy() 调用
     */
    public void detachView() {
        this.view = null;
    }

    /**
     * 判断 view 是否绑定
     * 每次调用数据请求时，都要先调用该方法检查是否与 view 绑定了
     *
     * @return
     */
    public boolean isViewAttached() {
        return view != null;
    }

    public V getView() {
        return view;
    }


//    public void registerEventBus() {
//        EventBus.getDefault().register(this);
//    }
//
//
//    public void unregisterEventBus() {
//        EventBus.getDefault().unregister(this);
//    }


}
