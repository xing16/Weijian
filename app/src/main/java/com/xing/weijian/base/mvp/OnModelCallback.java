package com.xing.weijian.base.mvp;

/**
 * Model 层请求数据回调
 * Created by Administrator on 2017/12/2.
 */

public interface OnModelCallback<T> {

    /**
     * 请求成功的回调
     *
     * @param data
     */
    void onSuccess(T data);

    /**
     * 使用接口 api 请求数据时，虽然已经成功请求了，但由于 errorCode !=0 原因无法正常返数据
     *
     * @param msg
     */
    void onFailure(String msg);

    /**
     * 请求数据失败，调用接口时，出现无法联网
     */
    void onError();

    /**
     * 当请求数据结束时，无论请求结果是成功，失败或是抛出异常都会执行此方法给用户做处理，通常做网络
     * 请求时可以在此处隐藏“正在加载”的等待控件
     */
    void onComplete();


}
