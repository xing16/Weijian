package com.xing.weijian.events;

import com.xing.weijian.coder.model.CoderBean;

import java.util.List;

/**
 * Created by Administrator on 2018/6/14.
 */

public class CoderListEvent {

    private List<CoderBean> coderBeanList;

    private String type;

    public CoderListEvent(List<CoderBean> coderBeanList, String type) {
        this.coderBeanList = coderBeanList;
        this.type = type;
    }

    public List<CoderBean> getCoderBeanList() {
        return coderBeanList;
    }

    public String getType() {
        return type;
    }


}
