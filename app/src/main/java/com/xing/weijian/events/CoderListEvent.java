package com.xing.weijian.events;

import com.xing.weijian.coder.model.CoderBean;

import java.util.List;

/**
 * Created by Administrator on 2018/6/14.
 */

public class CoderListEvent {

    private List<CoderBean> coderBeanList;

    public CoderListEvent(List<CoderBean> coderBeanList) {
        this.coderBeanList = coderBeanList;
    }

    public List<CoderBean> getCoderBeanList(){
        return coderBeanList;
    }


}
