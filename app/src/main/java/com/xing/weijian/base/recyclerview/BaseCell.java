package com.xing.weijian.base.recyclerview;

/**
 * Created by Administrator on 2017/10/5.
 */

public abstract class BaseCell<T> implements Cell {

    protected T data;

    public BaseCell(T data) {
        this.data = data;
    }


    /**
     * 如需回收资源，子类重写实现该方法
     */
    @Override
    public void releaseResource() {

    }
}
