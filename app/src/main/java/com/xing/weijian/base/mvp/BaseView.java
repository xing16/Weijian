package com.xing.weijian.base.mvp;

/**
 * Created by Administrator on 2017/12/2.
 */

public interface BaseView {

    /**
     * 显示正在加载 view
     */
    void showLoading();

    /**
     * 关闭正在加载view
     */
    void hideLoading();

    void showToast(String msg);

    void showError();


}
